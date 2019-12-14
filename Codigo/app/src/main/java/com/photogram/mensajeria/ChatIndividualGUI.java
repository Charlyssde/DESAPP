package com.photogram.mensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.photogram.adapters.MensajesAdapter;
import com.photogram.modelo.Mensaje;
import com.photogram.R;

import java.util.ArrayList;
import java.util.List;

public class ChatIndividualGUI extends AppCompatActivity {

    private ImageButton btnEnviar;
    private ImageButton btnAudio;
    private String contact;
    private String me;
    private EditText txtMensaje;

    private ListView view;
    private MensajesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_individual_gui);

        this.contact = getIntent().getStringExtra("username");
        this.setTitle(this.contact);

        SharedPreferences myPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        this.me = myPreferences.getString("USERNAME", "unknown");

        view = findViewById(R.id.lista_mensajes);

        setMensajes();

        btnEnviar = findViewById(R.id.btn_enviar_mensaje);
        btnAudio = findViewById(R.id.btn_enviar_audio);
        txtMensaje = findViewById(R.id.txt_mensaje);
    }

    private void setMensajes() {
        final List<Mensaje> msj = getMensajes();
        adapter = new MensajesAdapter(this.me,msj, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        }, ChatIndividualGUI.this);

        view.setAdapter(adapter);
    }

    private List<Mensaje> getMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();

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
        txtMensaje.setText("");

        //Se envía el mensaje
    }
}
