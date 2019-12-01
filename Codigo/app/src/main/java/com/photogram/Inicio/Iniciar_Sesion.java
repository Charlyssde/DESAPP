package com.photogram.Inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.photogram.Feed.Feed;
import com.photogram.Mensajeria.ChatIndividualGUI;
import com.photogram.Moderador.FeedModerador;
import com.photogram.R;
import com.photogram.pojo.LoginPOJO;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.JSONAdapter;
import com.photogram.servicesnetwork.VolleyS;
import com.photogram.servicesnetwork.persistence.Default;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Iniciar_Sesion extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnIngresar;

    private static String TAG = "MainActivity"; //Para comentar los mensajes en las bitácoras

    private VolleyS volley;
    protected RequestQueue fRequestQueue;

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

            }
        });

        SharedPreferences myPreferences = getPreferences(Context.MODE_PRIVATE);
        String token = myPreferences.getString("TOKEN", "unknown");
        if (!token.equals("unknown")) {
            Toast.makeText(Iniciar_Sesion.this, "TK: " + token, Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(Iniciar_Sesion.this, Feed.class);
            Iniciar_Sesion.this.startActivity(intent);
            finish();
        }
    }

    private void loginRequest (){
        btnIngresar.setEnabled(false);
        Map<String, String> param = new HashMap<>();
        param.put ("username", txtUsername.getText().toString());
        param.put ("password", txtPassword.getText().toString());

        JSONObject jsonObject = new JSONObject(param);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.login, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        LoginPOJO result = JSONAdapter.loginAdapter(response);


                        SharedPreferences myPreferences = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor myEditor = myPreferences.edit();
                        myEditor.putString("TOKEN", "" + result.getToken());
                        myEditor.commit();

                        Toast.makeText(Iniciar_Sesion.this, "TK: " + result.getToken(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Iniciar_Sesion.this, Feed.class);
                        Iniciar_Sesion.this.startActivity(intent);
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Iniciar_Sesion.this, "Error al ingresar", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Testing network");
                btnIngresar.setEnabled(true);
            }
        });

        volley.addToQueue(jsonObjectRequest);


    }
}

