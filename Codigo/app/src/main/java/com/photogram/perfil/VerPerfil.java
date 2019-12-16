package com.photogram.perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.photogram.R;
import com.photogram.modelo.Usuario;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.JSONAdapter;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONException;
import org.json.JSONObject;

public class VerPerfil extends AppCompatActivity {

    private TextView username, estado, nombre;
    private String user;

    private VolleyS volley;
    private RequestQueue requestQueue;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        username = findViewById(R.id.txt_username_ver_perfil);
        nombre = findViewById(R.id.txt_nombre_ver_perfil);
        estado = findViewById(R.id.txt_estado_ver_perfil);

        volley = VolleyS.getInstance(getApplicationContext());
        requestQueue = volley.getRequestQueue();
        SharedPreferences preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        user = preferences.getString("USERNAME", "");
        username.setText(user);

        Usuario u = getUsuario(user);
        if(u != null){
            estado.setText(u.getEstado());
            nombre.setText(u.getNombre());
        }

        Fragment fragmentPerfil = null;
        fragmentPerfil = new FotosPerfilFragment(user);

        BottomNavigationView bottomNavigationView =findViewById(R.id.bnv_favoritos);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_perfil:
                        FotosPerfilFragment fotosPerfilFragment = new FotosPerfilFragment(user);
                        openFragment(fotosPerfilFragment);
                        break;
                    case R.id.menu_favoritos:
                        FavoritosFragment favoritosFragment = new FavoritosFragment(user);
                        openFragment(favoritosFragment);
                        break;
                }

                return true;
            }
        });


        /*
        * Al cargar el linearlayoutmanager, hacerlo con un gridlayout, no un linearlayout
        * Se cargan todas las fotos
        *
        * Añadirle al boton de opcioens de la foto el evento para eliminar/marcar favorito la foto
        * */


    }

    private Usuario getUsuario(final String user) {
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET, ApiEndPoint.getUser + user, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            usuario = JSONAdapter.userAdapter(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        volley.addToQueue(jsonObjectRequest);

        return usuario;
    }

    private void openFragment(Fragment fragment) {

        FragmentManager fragmentManager = VerPerfil.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment_fotos, fragment, "tag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
