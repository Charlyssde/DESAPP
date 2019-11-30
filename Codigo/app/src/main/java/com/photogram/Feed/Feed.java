package com.photogram.Feed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.photogram.Adapters.FotoFeedAdapter;
import com.photogram.Modelo.Foto;
import com.photogram.Moderador.FeedModerador;
import com.photogram.R;

import java.util.ArrayList;
import java.util.List;

public class Feed extends AppCompatActivity {

    private String TAG = "FEED";

    private RecyclerView rv;
    private FotoFeedAdapter adapter;

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

    private void setMenu() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bnvMenuFeed);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_perfil:
                        //TODO
                        //Aquí se abre la activity del perfil
                        break;

                    case R.id.menu_subir_foto:
                        //TODO
                        //Aquí se hace lo que se deba hacer para subir la foto al feed
                        break;
                }
                return true;
            }
        });

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
