package com.photogram.Moderador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.photogram.Adapters.FotoModeradorAdapter;
import com.photogram.Modelo.FotoModerador;
import com.photogram.R;

import java.util.ArrayList;
import java.util.List;

public class FeedModerador extends AppCompatActivity {

    private String TAG = "FEED_MODERADOR";
    private RecyclerView rv;
    private FotoModeradorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_moderador);

        FeedModerador.this.setTitle(R.string.titleModerdadorFeed);

        rv = findViewById(R.id.rvFotosModerador);
        setFotos();


        LinearLayoutManager llm = new LinearLayoutManager(FeedModerador.this);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.m_registrar_moderador:
                DialogFragment dialog = new RegistrarModeradorDialog();
                dialog.show(getSupportFragmentManager(), "Registro");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void setFotos() {
        final List<FotoModerador> fotosList = getLista();
        adapter = new FotoModeradorAdapter(fotosList, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FotoModerador e = fotosList.get(rv.getChildAdapterPosition(view));

                AlertDialog.Builder builder = new AlertDialog.Builder(FeedModerador.this);
                builder.setNegativeButton("Reportar cuenta", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(FeedModerador.this, "Negativo", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setPositiveButton("Eliminar foto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(FeedModerador.this, "Neutral", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setMessage("Cuadro de dialogo");
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        rv.setAdapter(adapter);
    }

    private List<FotoModerador> getLista(){
        List<FotoModerador> lista = new ArrayList<>();
        FotoModerador foto = new FotoModerador();
        lista.add(foto);
        lista.add(foto);
        lista.add(foto);
        lista.add(foto);
        return lista;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_feed_moderador, menu);
        return true;
    }

}
