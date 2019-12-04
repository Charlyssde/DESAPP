package com.photogram.Mensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.photogram.Adapters.MensajesAdapter;
import com.photogram.Modelo.Mensaje;
import com.photogram.R;

import java.util.ArrayList;
import java.util.List;

public class ChatIndividualGUI extends AppCompatActivity {

    ImageButton btnEnviar;
    ImageButton btnAudio;

    ListView view;
    private MensajesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_individual_gui);
        this.setTitle("Chat");

        view = findViewById(R.id.lista_mensajes);

        setMensajes();

        btnEnviar = findViewById(R.id.btn_enviar_mensaje);
        btnAudio = findViewById(R.id.btn_enviar_audio);
    }

    private void setMensajes() {
        final List<Mensaje> msj = getMensajes();
        adapter = new MensajesAdapter(msj, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        }, ChatIndividualGUI.this);

        view.setAdapter(adapter);
    }

    private List<Mensaje> getMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();
        Mensaje m;
        m = new Mensaje();
        m.setMine(true);
        mensajes.add(m);
        m = new Mensaje();
        m.setMine(false);
        mensajes.add(m);
        m = new Mensaje();
        m.setMine(true);
        mensajes.add(m);
        m = new Mensaje();
        m.setMine(false);
        mensajes.add(m);
        m = new Mensaje();
        m.setMine(false);
        mensajes.add(m);

        return mensajes;
    }

    public void insertText(View view) {
        btnEnviar.setVisibility(View.VISIBLE);
        btnAudio.setVisibility(View.GONE);
    }

    public void enviarMensaje(View view) {
        btnEnviar.setVisibility(View.GONE);
        btnAudio.setVisibility(View.VISIBLE);
    }
}
