package com.photogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.photogram.Mensajeria.ChatIndividualGUI;
import com.photogram.Mensajeria.ChatsGUI;
import com.photogram.Moderador.FeedModerador;

public class Iniciar_Sesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Iniciar_Sesion.this, ChatIndividualGUI.class);
        Iniciar_Sesion.this.startActivity(intent);
        finish();
    }
}
