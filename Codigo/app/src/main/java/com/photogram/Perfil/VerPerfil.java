package com.photogram.Perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.photogram.R;

public class VerPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        /*
        * Al cargar el linearlayoutmanager, hacerlo con un gridlayout, no un linearlayout
        * Se cargan todas las fotos
        *
        * Añadirle al boton de opcioens de la foto el evento para eliminar/marcar favorito la foto
        * */
    }
}
