package com.photogram.inicio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.photogram.feed.Feed;
import com.photogram.R;
import com.photogram.pojo.LoginPOJO;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.JSONAdapter;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Iniciar_Sesion extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnIngresar;
    private Button btnModeradorInicio;
    private Button btnRegistrarCuenta;

    private static String TAG = "MainActivity"; //Para comentar los mensajes en las bitácoras

    private VolleyS volley;
    protected RequestQueue fRequestQueue;

    private boolean res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsername = findViewById(R.id.editText2Usuario);
        txtPassword = findViewById(R.id.editTextContraseña);
        btnIngresar = findViewById(R.id.button);

        volley = VolleyS.getInstance(Iniciar_Sesion.this);
        fRequestQueue = volley.getRequestQueue();
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginRequest();
                btnIngresar.setEnabled(false);
            }
        });

        SharedPreferences myPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        //TODO cambiar aquí
        String token = myPreferences.getString("USERNAMED", "unknown");

        if(!Objects.equals(token, "unknown")){
            Intent intent = new Intent(Iniciar_Sesion.this, Feed.class);
            startActivity(intent);
            finish();
        }
        Button btn = findViewById(R.id.buttonCuentaNueva);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrarUsuario.class);
                startActivity(intent);
            }
        });
       Button btn2 = findViewById(R.id.buttonModeradorInicio);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), LoginModerador.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }

    private void loginRequest () {

        btnIngresar.setEnabled(false);
        Map<String, String> param = new HashMap<>();
        param.put("username", txtUsername.getText().toString());
        param.put("password", txtPassword.getText().toString());

        JSONObject jsonObject = new JSONObject(param);
        boolean resultado = Alive();
        if (resultado == true) {

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.login, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            LoginPOJO result = JSONAdapter.loginAdapter(response);


                            //SharedPreferences myPreferences = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences mPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                            SharedPreferences.Editor spEditor = mPreferences.edit();
                            spEditor.putString("USERNAME", result.getUsername());
                            spEditor.apply();
                            //SharedPreferences.Editor myEditor = myPreferences.edit();
                            //myEditor.putString("TOKEN", "" + result.getToken());
                            //myEditor.putString("USERNAME", result.getUsername());
                            //myEditor.commit();

                            Toast.makeText(Iniciar_Sesion.this, "Iniciando en la ip  " + ApiEndPoint.login, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Iniciar_Sesion.this, Feed.class);
                            startActivity(intent);
                            finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Iniciar_Sesion.this, "Error al ingresar", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "" + error.getMessage());
                    btnIngresar.setEnabled(true);
                }
            });
            volley.addToQueue(jsonObjectRequest);


        } else {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.login2, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            LoginPOJO result = JSONAdapter.loginAdapter(response);


                            //SharedPreferences myPreferences = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences mPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                            SharedPreferences.Editor spEditor = mPreferences.edit();
                            spEditor.putString("USERNAME", result.getUsername());
                            spEditor.apply();
                            //SharedPreferences.Editor myEditor = myPreferences.edit();
                            //myEditor.putString("TOKEN", "" + result.getToken());
                            //myEditor.putString("USERNAME", result.getUsername());
                            //myEditor.commit();

                            Toast.makeText(Iniciar_Sesion.this, "Conexion la IP " +  ApiEndPoint.login2, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Iniciar_Sesion.this, Feed.class);
                            startActivity(intent);
                            finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Iniciar_Sesion.this, "Error al ingresar", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "" + error.getMessage());
                    btnIngresar.setEnabled(true);
                }
            });
            volley.addToQueue(jsonObjectRequest);
        }

    }
    public  boolean Alive () {



    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiEndPoint.alive, null,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(Iniciar_Sesion.this, "Entro" + ApiEndPoint.alive, Toast.LENGTH_LONG).show();
                    res = true;
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(Iniciar_Sesion.this, "Error ip" + ApiEndPoint.alive, Toast.LENGTH_LONG).show();
            res = false;

        }
    });
        volley.addToQueue(jsonObjectRequest);
        return res;


}

}

