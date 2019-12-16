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
    public static String[] ips = {"35.238.91.65:7777","35.231.27.235:7777"};

    public static String  host2 = "localhost:7777";
    public  static String baseURL3 = "http://" + host2 + "/api";
    public static String alive = baseURL3 + "/alive";
    public static String hostDownloads = "http://10.0.2.2:7777/static/";


    public static String host = "35.238.91.65:7777";
    public static String host1 = "35.231.27.235:7777";

    public static String hostDownloads = "http://10.0.2.2:7777/static/";

    public static String baseURL = "http://" + host + "/api";
    public static String baseURL2 = "http://" + host1 + "/api";

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



    public static String login2 = baseURL2 + "/login";
    public static String loginModerador2 = baseURL2 + "/loginModerador/";
    public static String registrarModerador2 = baseURL2 + "/Moderador/";
    public static String registrarUsuario2 = baseURL2 + "/user";
    public static String getAllPhotos2 = baseURL2 + "/img/all";
    public static String getPhotoByUser2 = baseURL2 + "/img/";
    public static String eliminarFoto2 = baseURL2 + "/img/";
    public static String reportarCuenta2 = baseURL2 + "/mod/";
    public static String subirFoto2 = baseURL2 + "/img/prueba";
    public static String comentar2 = baseURL2 + "/comment";
    public static String reaccionar2 = baseURL2 + "/reaction";
    public static String getUser2 = baseURL2 + "/user/";
    public static String getAllUsuers2 = baseURL2 + "/user/all";
    //public static String descargaImg = baseURL + "/static/";
}
