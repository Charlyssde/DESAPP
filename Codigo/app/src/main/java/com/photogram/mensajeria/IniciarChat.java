package com.photogram.mensajeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.photogram.R;
import com.photogram.adapters.UsuariosListAdapter;
import com.photogram.feed.Feed;
import com.photogram.inicio.Iniciar_Sesion;
import com.photogram.modelo.Usuario;
import com.photogram.pojo.LoginPOJO;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.JSONAdapter;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class IniciarChat extends AppCompatActivity {

    private EditText txtBuscar;
    private Button btnBuscar;

    private RecyclerView rv;
    private UsuariosListAdapter adapter;

    private VolleyS volley;
    private RequestQueue fRequestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_chat);

        volley = VolleyS.getInstance(IniciarChat.this);
        fRequestQueue = volley.getRequestQueue();

        rv = findViewById(R.id.rvUsuarios);

        txtBuscar = findViewById(R.id.txt_buscar_usuario);
        btnBuscar = findViewById(R.id.btn_buscar_usuario);

        LinearLayoutManager llm = new LinearLayoutManager(IniciarChat.this);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtBuscar.getText().toString().isEmpty()){
                    getAllUsuarios();
                }else{
                    buscarUsuario();
                }
            }
        });
    }

    private void getAllUsuarios() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ApiEndPoint.getAllUsuers, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    final List<Usuario> users = JSONAdapter.usersAdapter(response);
                    Log.e("SIZEEEE", "" + users.size());
                    adapter = new UsuariosListAdapter(users, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Usuario usuario = users.get(rv.getChildAdapterPosition(view));
                            Intent intent =  new Intent(IniciarChat.this, ChatIndividualGUI.class);
                            intent.putExtra("username", usuario.getUsername());
                            startActivity(intent);
                        }
                    });
                    rv.setAdapter(adapter);
                }catch (Exception ex){
                    Log.e("ERROR", ex.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        volley.addToQueue(jsonArrayRequest);

    }

    private void buscarUsuario() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiEndPoint.getUser + txtBuscar.getText().toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                        final List<Usuario> users = JSONAdapter.oneUserAdapter(response);
                        Log.e("SIZEEE", "" + users.size());
                        adapter = new UsuariosListAdapter(users, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Usuario usuario = users.get(rv.getChildAdapterPosition(view));
                                Intent intent =  new Intent(IniciarChat.this, ChatIndividualGUI.class);
                                intent.putExtra("username", usuario.getUsername());
                                startActivity(intent);
                            }
                        });
                            rv.setAdapter(adapter);
                        }catch (Exception ex){
                            Log.e("ERROR", ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IniciarChat.this, "No encontrado nada", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "" + error.getMessage());
            }
        });
        volley.addToQueue(jsonObjectRequest);
    }
}
