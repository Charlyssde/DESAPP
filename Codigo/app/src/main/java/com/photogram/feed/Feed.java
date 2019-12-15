package com.photogram.feed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.photogram.adapters.FotoFeedAdapter;
import com.photogram.grpc.grpc.ThreadMessages;
import com.photogram.mensajeria.ChatsGUI;
import com.photogram.modelo.Foto;
import com.photogram.perfil.VerPerfil;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.JSONAdapter;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Feed extends AppCompatActivity {

    private String TAG = "FEED";

    private RecyclerView rv;
    private FotoFeedAdapter adapter;
    private ImageButton  btnSubir;

    private VolleyS volley;
    private RequestQueue fRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        this.setTitle("Photogram");

        volley = VolleyS.getInstance(Feed.this);
        fRequestQueue = volley.getRequestQueue();

        rv = findViewById(R.id.rvFotodFeed);

        setMenu();

        setFotos();

        LinearLayoutManager llm = new LinearLayoutManager(Feed.this);
        rv.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

        SharedPreferences myPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        String username = myPreferences.getString("USERNAME", "unknown");

        (new ThreadMessages(username)).run();
    }

    private void setFotos() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ApiEndPoint.getAllPhotos,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    final List<Foto> fotosList = JSONAdapter.allFotosFeedAdapter(response);
                    //Log.e("TEST", "Pan " + fotosList.size());
                    adapter = new FotoFeedAdapter(fotosList, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Foto foto = fotosList.get(rv.getChildAdapterPosition(view));
                            Gson gson = new Gson();
                            String jsonFoto = gson.toJson(foto);
                            Log.i("JSOOOONNNN","" + jsonFoto);
                            SharedPreferences preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                            final String username = preferences.getString("USERNAME", "");
                            Intent intent = new Intent(Feed.this, VerFoto.class);
                            intent.putExtra("jsonFoto", jsonFoto);
                            intent.putExtra("username", username);
                            //Bundle b = new Bundle();
                            //b.putString("PATH", foto.getPath());
                            //intent.putExtra("PATH", foto.getPath());
                            startActivity(intent);

                        }
                    });
                    rv.setAdapter(adapter);

                } catch (JSONException e) {
                    Toast.makeText(Feed.this, "Cannot parse data", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Testing Network");
            }


        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content Type", "application/json");
                return headers;
            }
        };
        volley.addToQueue(jsonArrayRequest);


    }

    private void setMenu() {

        BottomNavigationView bottomNavigationView = findViewById(R.id.bntSubirFotoFeed);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id){
                    case R.id.menu_subir_foto:
                        Intent intent = new Intent(getApplicationContext(), subir_foto.class);
                        startActivity(intent);
                        break;
                    case R.id.menu_perfil:
                        Intent intent2 = new Intent(getApplicationContext(), VerPerfil.class);
                        startActivity(intent2);
                }
                return true;
            }

        });

    }

    public void abrirFotos(View view) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_chats:
                Intent intent3 = new Intent(Feed.this, ChatsGUI.class);
                startActivity(intent3);
        }
        return true;
    }
}




