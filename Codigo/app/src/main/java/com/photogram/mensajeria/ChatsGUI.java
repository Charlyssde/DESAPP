package com.photogram.mensajeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.photogram.adapters.ChatsListAdapter;
import com.photogram.feed.Feed;
import com.photogram.grpc.grpc.ChatGrpc;
import com.photogram.grpc.grpc.ChatOuterClass;
import com.photogram.modelo.Conversacion;
import com.photogram.modelo.Mensaje;
import com.photogram.modelo.Usuario;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ChatsGUI extends AppCompatActivity {

    private String TAG = "CHATS_GUI";
    private RecyclerView rv;
    private ChatsListAdapter adapter;
    public List<Conversacion> conversaciones;

    private String me_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_gui);
        this.setTitle("Chats");

        this.conversaciones = new ArrayList<>();
        SharedPreferences myPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        this.me_user = myPreferences.getString("USERNAME", "unknown");


        rv = findViewById(R.id.rvChats);

        LinearLayoutManager llm = new LinearLayoutManager(ChatsGUI.this);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

        new TaskReceiveMessages().execute();
    }

    private void setChats() {
        final List<Conversacion> chatsList = this.conversaciones;
        Log.e("CONV", "" + this.conversaciones.size() );
        adapter = new ChatsListAdapter(chatsList, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conversacion conv = chatsList.get(rv.getChildAdapterPosition(view));
                //TODO
                Intent intent = new Intent(ChatsGUI.this, ChatIndividualGUI.class);
                intent.putExtra("desti", conv.getDestinatario());
                intent.putExtra("mensajes", conv.getMensajes());
                ChatsGUI.this.startActivity(intent);
            }
        });
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chat_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.m_nuevo_chat:
                Intent intent = new Intent(ChatsGUI.this, IniciarChat.class);
                startActivity(intent);
        }
        return true;
    }

    private class TaskReceiveMessages extends AsyncTask {

        private ManagedChannel mChannel;
        private ChatGrpc.ChatBlockingStub stub;

        private ChatOuterClass.Usuario me;

        public List<Mensaje> mensajes;
        @Override
        protected Object doInBackground(Object[] objects) {
            mChannel = ManagedChannelBuilder.forAddress(ApiEndPoint.hostGrpc, ApiEndPoint.portGrpc).usePlaintext(true).build();
            stub = ChatGrpc.newBlockingStub(mChannel);
            this.me = ChatOuterClass.Usuario.newBuilder().setUsername(me_user).build();

            conversaciones = new ArrayList<>();
            mensajes = new ArrayList<>();
            Iterator<ChatOuterClass.Mensaje> mensajes;
            mensajes = stub.recibirMensajes(this.me);
            int cont = 0;
            while(mensajes.hasNext()){
                ChatOuterClass.Mensaje msj = mensajes.next();
                Mensaje mensaje =  new Mensaje();
                mensaje.setReceiver(msj.getReceiver());
                mensaje.setSender(msj.getSender());
                mensaje.setContent(msj.getContent());
                if(conversaciones.size() == 0){
                    Conversacion conv = new Conversacion(mensaje.getSender(), mensaje.getReceiver());
                    conv.getMensajes().add(mensaje);
                    conv.setLastMensaje(mensaje);
                    conversaciones.add(conv);
                }else{
                    for(Conversacion conv : conversaciones){
                        if(conv.getKey().contains(msj.getSender())){
                            conv.setLastMensaje(mensaje);
                            conv.getMensajes().add(mensaje);
                        }else{
                            Conversacion c = new Conversacion(msj.getSender(), me_user);
                            conv.getMensajes().add(mensaje);
                            conv.setLastMensaje(mensaje);
                            conversaciones.add(c);
                        }
                    }
                }
                cont++;
                Log.e("MENSAJEEE", "" + msj);
            }
            Log.e("CONVERSACIONES FEED", "" + conversaciones.size());

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.e("FINSHED", "" + conversaciones.size());
            setChats();
        }
    }
}
