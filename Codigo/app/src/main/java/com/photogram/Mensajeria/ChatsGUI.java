package com.photogram.Mensajeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.photogram.Adapters.ChatsListAdapter;
import com.photogram.Adapters.FotoModeradorAdapter;
import com.photogram.Modelo.Conversacion;
import com.photogram.Modelo.FotoModerador;
import com.photogram.Modelo.Usuario;
import com.photogram.Moderador.FeedModerador;
import com.photogram.R;

import java.util.ArrayList;
import java.util.List;

public class ChatsGUI extends AppCompatActivity {

    private String TAG = "CHATS_GUI";
    private RecyclerView rv;
    private ChatsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_gui);
        this.setTitle("Chats");

        rv = findViewById(R.id.rvChats);

        setChats();

        LinearLayoutManager llm = new LinearLayoutManager(ChatsGUI.this);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);


    }

    private void setChats() {
        final List<Conversacion> chatsList = getChats();
        adapter = new ChatsListAdapter(chatsList, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Intent intent = new Intent(ChatsGUI.this, ChatIndividualGUI.class);
                ChatsGUI.this.startActivity(intent);
            }
        });
        rv.setAdapter(adapter);
    }

    private List<Conversacion> getChats(){
        List<Conversacion> lista = new ArrayList<>();

        Usuario u = new Usuario();
        Conversacion c = new Conversacion(u);
        lista.add(c);
        lista.add(c);
        lista.add(c);
        lista.add(c);

        return lista;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat_list, menu);
        return true;
    }
}
