package com.photogram.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.photogram.adapters.ComentariosAdapter;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.VolleyS;
import com.squareup.picasso.Picasso;
import com.photogram.modelo.Foto;

public class VerFoto extends AppCompatActivity {

    private RecyclerView rv;
    private ComentariosAdapter adapter;
    private Button btnComentar;
    private EditText etComentarioNuevo;
    private ImageView ivFoto;
    private TextView tvUsername;

    private VolleyS volley;
    private RequestQueue fRequestQueue;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_foto);

        Gson gson = new Gson();
        String json = getIntent().getStringExtra("jsonFoto");
        Foto foto = gson.fromJson(json, Foto.class);
        volley = VolleyS.getInstance(VerFoto.this);
        fRequestQueue = volley.getRequestQueue();

        rv = findViewById(R.id.rvComentarios);
        etComentarioNuevo = findViewById(R.id.txt_new_comentario);
        ivFoto = findViewById(R.id.imageView_comentarios);
        tvUsername = findViewById(R.id.txtUsername);
        btnComentar = findViewById(R.id.btn_comentar);

        tvUsername.setText(foto.getUsuario());
        Picasso.get().load(ApiEndPoint.hostDownloads + foto.getPath()).into(ivFoto);

        //Bundle b = getIntent().getExtras();
        //this.path = getIntent().getStringExtra("PATH");

        //setFoto();

        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aquí se sube un comentario
                etComentarioNuevo.setText("");
            }
        });
    }


    private void setFoto() {
        Picasso.get().load(ApiEndPoint.hostDownloads + this.path).into(ivFoto);

    }
}
