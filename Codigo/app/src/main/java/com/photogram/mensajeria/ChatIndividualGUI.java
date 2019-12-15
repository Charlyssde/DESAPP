package com.photogram.mensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.photogram.adapters.MensajesAdapter;
import com.photogram.grpc.grpc.ChatGrpc;
import com.photogram.grpc.grpc.ChatOuterClass;
import com.photogram.modelo.Mensaje;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;

import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ChatIndividualGUI extends AppCompatActivity {

    private ImageButton btnEnviar;
    private ImageButton btnAudio;
    private String contact;
    private String me;
    private EditText txtMensaje;

    private ListView view;
    private MensajesAdapter adapter;
    private List<Mensaje> mensajes;

    private ManagedChannel mChannel;
    private ChatGrpc.ChatStub stub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_individual_gui);

        this.contact = getIntent().getStringExtra("username");
        this.setTitle(this.contact);

        SharedPreferences myPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        this.me = myPreferences.getString("USERNAME", "unknown");

        view = findViewById(R.id.lista_mensajes);

        mensajes = new ArrayList<>();
        setMensajes();

        btnEnviar = findViewById(R.id.btn_enviar_mensaje);
        btnAudio = findViewById(R.id.btn_enviar_audio);
        txtMensaje = findViewById(R.id.txt_mensaje);
    }

    private void setMensajes() {
        mensajes = getMensajes();
        adapter = new MensajesAdapter(this.me,mensajes, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        }, ChatIndividualGUI.this);

        view.setAdapter(adapter);
    }

    private List<Mensaje> getMensajes() {
        Mensaje m = new Mensaje();
        m.setSender(me);
        m.setContent("Message test");
        Mensaje m2 = new Mensaje();
        m2.setSender("Other");
        m2.setContent("Message test response");
        mensajes.add(m);
        mensajes.add(m2);
        mensajes.add(m);
        mensajes.add(m2);
        mensajes.add(m);
        mensajes.add(m2);
        mensajes.add(m);
        mensajes.add(m2);
        mensajes.add(m);
        mensajes.add(m2);
        mensajes.add(m);
        mensajes.add(m2);


        return mensajes;
    }

    public void insertText(View view) {
        btnEnviar.setVisibility(View.VISIBLE);
        btnAudio.setVisibility(View.GONE);
    }

    public void enviarMensaje(View view) {
        btnEnviar.setVisibility(View.GONE);
        btnAudio.setVisibility(View.VISIBLE);

        mChannel = ManagedChannelBuilder.forAddress(ApiEndPoint.hostGrpc, ApiEndPoint.portGrpc).usePlaintext(true).build();
        stub = ChatGrpc.newStub(mChannel);
        final ChatOuterClass.Mensaje mensaje = ChatOuterClass.Mensaje.newBuilder()
                .setSender(me)
                .setReceiver(this.contact)
                .setContent(txtMensaje.getText().toString())
                .build();
        stub.enviarMensaje(mensaje, new StreamObserver<ChatOuterClass.Empty>() {
            @Override
            public void onNext(ChatOuterClass.Empty value) {
                Log.e("VALUEEEE", value.getResponse());
                System.out.println(mensaje);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("VALUEEEE", t.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        });
        Log.e("MENSAJEEEE", "ENVIADO");
        txtMensaje.setText("");
        Mensaje msj =  new Mensaje();
        msj.setReceiver(mensaje.getReceiver());
        msj.setSender(mensaje.getSender());
        msj.setContent(mensaje.getContent());
        mensajes.add(msj);
        //Se envía el mensaje
    }
}
