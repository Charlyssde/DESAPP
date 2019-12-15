package com.photogram.grpc.grpc;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.photogram.feed.Feed;
import com.photogram.modelo.Mensaje;
import com.photogram.modelo.Usuario;
import com.photogram.servicesnetwork.ApiEndPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ThreadMessages implements Runnable {

    private ManagedChannel mChannel;
    private ChatGrpc.ChatStub stub;

    private ChatOuterClass.Usuario me;

    public List<Mensaje> mensajes;

    public ThreadMessages(String me) {
        mChannel = ManagedChannelBuilder.forAddress(ApiEndPoint.hostGrpc, ApiEndPoint.portGrpc).usePlaintext(true).build();
        stub = ChatGrpc.newStub(mChannel);
        this.me = ChatOuterClass.Usuario.newBuilder().setUsername(me).build();
    }

    @Override
    public void run() {

        mensajes = new ArrayList<>();
        Log.e("GETS", "IN HERE");
            stub.recibirMensajes(this.me, new StreamObserver<ChatOuterClass.Mensaje>() {
                @Override
                public void onNext(ChatOuterClass.Mensaje value) {
                    Mensaje msj = new Mensaje();
                    msj.setSender(value.getSender());
                    msj.setContent(value.getContent());
                    msj.setReceiver(value.getReceiver());
                    //msj.setId(value.getId());
                    mensajes.add(msj);
                }

                @Override
                public void onError(Throwable t) {
                    Log.e("THREAD",t.getMessage());
                }

                @Override
                public void onCompleted() {
                    Log.e("THREAD", "COMPLETED");
                }
            });
    }
}
