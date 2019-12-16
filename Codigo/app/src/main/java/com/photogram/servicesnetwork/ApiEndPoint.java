package com.photogram.servicesnetwork;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONObject;

public class ApiEndPoint {

    public static int portGrpc = 1337;
    public static String hostGrpc ="10.0.2.2" ;
    private static String[] ips = {"35.238.91.65:7777","35.231.27.235:7777"};

    private static String  host2 = "localhost:7777";
    private static String baseURL2 = "http://" + host2 + "/api";
    private String alive = baseURL2 + "/alive";

    public void Alive () {
            volley = VolleyS.getInstance(ApiEndPoint.this);
        fRequestQueue = volley.getRequestQueue();

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiEndPoint.alive, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse() {
                   public static host = "35.238.91.65:7777";
                    public static baseURL = "http://" + host + "/api";
                }
            }, new Response.ErrorListener() {
                    @Override


                        public void onErrorResponse(VolleyError error) {
                        public static  host = "35.231.27.235:7777";
                        public static  baseURL = "http://" + host + "/api";
                        }
                    });
                    volley.addToQueue(jsonObjectRequest);
    }


    public static String hostDownloads = "http://10.0.2.2:7777/static/";

    public static String login = baseURL + "/login";
    public static String loginModerador = baseURL + "/loginModerador/";
    public static String registrarModerador = baseURL + "/Moderador/";
    public static String registrarUsuario = baseURL + "/user";
    public static String getAllPhotos = baseURL + "/img/all";
    public static String getPhotoByUser = baseURL + "/img/";
    public static String eliminarFoto = baseURL + "/img/";
    public static String reportarCuenta = baseURL + "/mod/";
    public static String subirFoto = baseURL + "/img/prueba";
    public static String comentar = baseURL + "/comment";
    public static String reaccionar = baseURL + "/reaction";
    public static String getUser = baseURL + "/user/";
    public static String getAllUsuers = baseURL + "/user/all";
    //public static String descargaImg = baseURL + "/static/";
}
