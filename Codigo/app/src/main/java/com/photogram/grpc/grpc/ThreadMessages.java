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
    private ChatGrpc.ChatBlockingStub stub;

    private ChatOuterClass.Usuario me;

    public List<Mensaje> mensajes;

    public ThreadMessages(String me) {
        mChannel = ManagedChannelBuilder.forAddress(ApiEndPoint.hostGrpc, ApiEndPoint.portGrpc).usePlaintext(true).build();
        stub = ChatGrpc.newBlockingStub(mChannel);
        this.me = ChatOuterClass.Usuario.newBuilder().setUsername(me).build();
    }

    @Override
    public void run() {

        mensajes = new ArrayList<>();
        Log.e("GETS", "IN HERE");
        Iterator<ChatOuterClass.Mensaje> mensajes;
        mensajes = stub.recibirMensajes(this.me);

        while(mensajes.hasNext()){
            ChatOuterClass.Mensaje msj = mensajes.next();
            Log.e("MENSAJEEE", msj.getContent());
        }
    }
}
