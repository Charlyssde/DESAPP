package com.photogram.Feed;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.photogram.Adapters.FotoFeedAdapter;
import com.photogram.Inicio.Iniciar_Sesion;
import com.photogram.Inicio.LoginModerador;
import com.photogram.Modelo.Foto;
import com.photogram.Moderador.FeedModerador;
import com.photogram.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feed extends AppCompatActivity {

    private String TAG = "FEED";

    private RecyclerView rv;
    private FotoFeedAdapter adapter;
    private ImageButton  btnSubir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        this.setTitle("Photogram");

        rv = findViewById(R.id.rvFotodFeed);

        setMenu();

        setFotos();

        LinearLayoutManager llm = new LinearLayoutManager(Feed.this);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);


    }







    private void setFotos() {
        final List<Foto> fotos = getFotos();

        adapter = new FotoFeedAdapter(fotos, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Foto foto = fotos.get(rv.getChildAdapterPosition(view));

                //TODO
                //Aquí se amplia la vista de la foto, con el visualizar foto
            }
        });

        rv.setAdapter(adapter);


    }

    private void setMenu() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bntSubirFotoFeed);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.menu_subir_foto) {
                    Intent intent = new Intent(getApplicationContext(), subir_foto.class);
                    startActivity(intent);

                }
                return true;
            }
        });

    }




    private List<Foto> getFotos() {
        List<Foto> fotos = new ArrayList<>();
        Foto foto = new Foto();
        fotos.add(foto);
        fotos.add(foto);
        fotos.add(foto);
        fotos.add(foto);

        return fotos;
    }
}



