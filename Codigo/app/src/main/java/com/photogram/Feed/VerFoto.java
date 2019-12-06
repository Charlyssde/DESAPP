package com.photogram.Feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.photogram.Adapters.ComentariosAdapter;
import com.photogram.R;
import com.photogram.servicesnetwork.VolleyS;
import com.squareup.picasso.Picasso;

public class VerFoto extends AppCompatActivity {

    private RecyclerView rv;
    private ComentariosAdapter adapter;
    private Button btnComentar;
    private EditText etComentarioNuevo;
    private ImageView ivFoto;

    private VolleyS volley;
    private RequestQueue fRequestQueue;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_foto);

        volley = VolleyS.getInstance(VerFoto.this);
        fRequestQueue = volley.getRequestQueue();

        rv = findViewById(R.id.rvComentarios);
        etComentarioNuevo = findViewById(R.id.txt_new_comentario);
        ivFoto = findViewById(R.id.imageView_comentarios);
        btnComentar = findViewById(R.id.btn_comentar);

        //Bundle b = getIntent().getExtras();
        this.path = getIntent().getStringExtra("PATH");

        setFoto();

        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aquí se sube un comentario
                etComentarioNuevo.setText("");
            }
        });
    }

    private void setFoto() {
        Picasso.get().load("http://10.0.2.2:7777/static/" + this.path).into(ivFoto);

    }
}
