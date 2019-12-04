package com.photogram.Moderador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.photogram.Adapters.FotoModeradorAdapter;
import com.photogram.Modelo.FotoModerador;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.JSONAdapter;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedModerador extends AppCompatActivity {

    private String TAG = "FEED_MODERADOR";
    private RecyclerView rv;
    private FotoModeradorAdapter adapter;
    private VolleyS volley;
    private RequestQueue fRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_moderador);
        FeedModerador.this.setTitle(R.string.titleModerdadorFeed);

        volley = VolleyS.getInstance(FeedModerador.this);
        fRequestQueue = volley.getRequestQueue();

        rv = findViewById(R.id.rvFotosModerador);



        LinearLayoutManager llm = new LinearLayoutManager(FeedModerador.this);
        rv.setLayoutManager(llm);

        setFotos();
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), llm.getOrientation());
        //rv.addItemDecoration(dividerItemDecoration);

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
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ApiEndPoint.getAllPhotos,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    final List<FotoModerador> fotosList = JSONAdapter.allFotosAdapter(response);
                    adapter = new FotoModeradorAdapter(fotosList, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final FotoModerador foto = fotosList.get(rv.getChildAdapterPosition(view));

                            AlertDialog.Builder builder = new AlertDialog.Builder(FeedModerador.this);
                            builder.setNegativeButton("Reportar cuenta", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.setPositiveButton("Eliminar foto", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });

                            builder.setMessage("Opciones:");
                            builder.setCancelable(true);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    Toast.makeText(FeedModerador.this, "Cannot parse data", Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_feed_moderador, menu);
        return true;
    }

}
