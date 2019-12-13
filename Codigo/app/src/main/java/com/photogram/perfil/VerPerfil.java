package com.photogram.perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;
import com.photogram.Modelo.Perfil;
import com.photogram.R;
import com.photogram.feed.VerFoto;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.URISyntaxException;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class VerPerfil extends AppCompatActivity {

    private String username;
    private Perfil perfil;
    private TextView textViewUsername,
                    textViewNombre,
                    textViewEstado;
    private EditText editText;
    private Button enviar1;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.100.161:7777/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.i("hola", e.toString());
        }
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            VerPerfil.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("destinatary");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }

                    Log.i("hola", "recibido: " + message + ", de: " + username);

                    // add the message to view
                    //addMessage(username, message);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);

        mSocket.on("mobile", onNewMessage);
        mSocket.connect();

        username = getIntent().getStringExtra("username");
        getPerfil();
        textViewUsername = (TextView)findViewById(R.id.txt_username_ver_perfil);
        textViewNombre = (TextView)findViewById(R.id.txt_nombre_ver_perfil);
        textViewEstado = (TextView)findViewById(R.id.txt_estado_ver_perfil);
        editText = (EditText)findViewById(R.id.message1);
        enviar1 = (Button)findViewById(R.id.enviar1);

        enviar1Listener();

        perfil = new Perfil();

        /*
        * Al cargar el linearlayoutmanager, hacerlo con un gridlayout, no un linearlayout
        * Se cargan todas las fotos
        *
        * Añadirle al boton de opcioens de la foto el evento para eliminar/marcar favorito la foto
        * */
    }

    public void enviar1Listener() {
        enviar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSend();
            }
        });
    }

    private void attemptSend() {
        String message = editText.getText().toString().trim();
        Log.i("hola", "enviado: " + message + ", a: " + "desktop");
        if(TextUtils.isEmpty(message)) {
            Log.i("hola", "empty");
            return;
        }

        editText.setText("");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("destinatary", "desktop");
            jsonObject.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("message", jsonObject);
    }

    private void getPerfil() {
        String uri = String.format(ApiEndPoint.usuarioPorNombre + "/" + username);
        JsonObjectRequest userReq = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                try {
                    perfil.setNombre(response.getString("nombre"));
                    perfil.setUsername(response.getString("username"));
                    perfil.setEstado(response.getString("estado"));
                    textViewUsername.setText(perfil.getUsername());
                    textViewNombre.setText(perfil.getNombre());
                    textViewEstado.setText(perfil.getEstado());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                perfil = gson.fromJson(response.toString(), Perfil.class);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                error.printStackTrace();
            }
        });
        VolleyS.getInstance(VerPerfil.this).addToQueue(userReq);
    }
}
