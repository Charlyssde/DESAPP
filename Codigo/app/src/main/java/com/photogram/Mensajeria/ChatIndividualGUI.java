package com.photogram.Mensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.photogram.R;

public class ChatIndividualGUI extends AppCompatActivity {

    ImageButton btnEnviar;
    ImageButton btnFoto;
    ImageButton btnAudio;

    ListView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_individual_gui);





        btnEnviar = findViewById(R.id.btn_enviar_mensaje);
        btnFoto = findViewById(R.id.btn_enviar_foto);
        btnAudio = findViewById(R.id.btn_enviar_audio);
    }

    public void insertText(View view) {
        btnEnviar.setVisibility(View.VISIBLE);
        btnAudio.setVisibility(View.INVISIBLE);
        btnFoto.setVisibility(View.INVISIBLE);
    }

    public void enviarMensaje(View view) {
        btnEnviar.setVisibility(View.INVISIBLE);
        btnAudio.setVisibility(View.VISIBLE);
        btnFoto.setVisibility(View.VISIBLE);
    }
}
