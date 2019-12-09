package com.photogram.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.photogram.adapters.ComentariosAdapter;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.VolleyS;
import com.squareup.picasso.Picasso;
import com.photogram.modelo.Foto;
import com.photogram.modelo.Reaccion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerFoto extends AppCompatActivity {

    private RecyclerView rv;
    private ComentariosAdapter adapter;
    private Button btnComentar;
    private ImageButton btnReaccionar;
    private EditText etComentarioNuevo;
    private ImageView ivFoto;
    private TextView tvUsername;
    private String username;
    private List<com.photogram.modelo.Comentario> comentarios;
    private List<Reaccion> reacciones;
    private boolean reaccion = false;

    private VolleyS volley;
    private RequestQueue fRequestQueue;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_foto);

        Gson gson = new Gson();
        String json = getIntent().getStringExtra("jsonFoto");
        final Foto foto = gson.fromJson(json, Foto.class);
        volley = VolleyS.getInstance(VerFoto.this);
        fRequestQueue = volley.getRequestQueue();
        rv = findViewById(R.id.rvComentarios);
        etComentarioNuevo = findViewById(R.id.txt_new_comentario);
        ivFoto = findViewById(R.id.imageView_comentarios);
        tvUsername = findViewById(R.id.txtUsername);
        btnComentar = findViewById(R.id.btn_comentar);
        btnReaccionar = findViewById(R.id.btnReaccionar);

        this.username = getIntent().getStringExtra("username");
        tvUsername.setText(foto.getUsuario());
        this.comentarios = foto.getComentarios();
        Log.i("HOLAHOLAHOLA","" + foto.getComentarios().size());
        this.reacciones = foto.getReacciones();

        LinearLayoutManager llm = new LinearLayoutManager(VerFoto.this);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

        for(Reaccion reaccion : foto.getReacciones()) {
            Log.i("hola", reaccion.getUsername() + " " + this.username);
            if(reaccion.getUsername() == this.username) {
                btnReaccionar.setBackgroundColor(Color.parseColor("#666bff"));
            }
        }

        Picasso.get().load(ApiEndPoint.hostDownloads + foto.getPath()).into(ivFoto);

        setComentarios();
        Log.i("hola", json);

        //Bundle b = getIntent().getExtras();
        //this.path = getIntent().getStringExtra("PATH");

        //setFoto();

        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aquí se sube un comentario
                comentar(foto);
            }
        });

        btnReaccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reaccionar(foto);
            }
        });
    }


    private void setComentarios() {
        adapter = new ComentariosAdapter(comentarios, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("hola", "hola");

            }
        });
        rv.setAdapter(adapter);
    }

    private void reaccionar(Foto foto) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("img_id", foto.getFotoId());
        JSONObject jsonObject = new JSONObject(params);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.reaccionar, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String Response = response.getString("message");
                            Log.i("hola", Response);
                            Toast.makeText(VerFoto.this, Response, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            Log.e("hola", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VerFoto.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content Type", "application/json");

                SharedPreferences myPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                String token = myPreferences.getString("TOKEN", "unknown");
                System.out.println(token);
                headers.put("Authorization", token);
                return headers;
            }
        };
        VolleyS.getInstance(VerFoto.this).addToQueue(stringRequest);
    }

    private void comentar(Foto foto) {
        String contenido = etComentarioNuevo.getText().toString();
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("contenido", contenido);
        params.put("img_id", foto.getFotoId());
        JSONObject jsonObject = new JSONObject(params);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.comentar, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String Response = response.getString("message");
                            Log.i("hola", Response);
                            Toast.makeText(VerFoto.this, Response, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            Log.e("hola", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VerFoto.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content Type", "application/json");

                SharedPreferences myPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                String token = myPreferences.getString("TOKEN", "unknown");
                System.out.println(token);
                headers.put("Authorization", token);
                return headers;
            }
        };
        VolleyS.getInstance(VerFoto.this).addToQueue(stringRequest);
    }
}
