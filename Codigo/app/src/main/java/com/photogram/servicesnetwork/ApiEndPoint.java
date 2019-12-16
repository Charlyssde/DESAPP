package com.photogram.servicesnetwork;

public class ApiEndPoint {

    public static int portGrpc = 1337;
    public static String hostGrpc ="10.0.2.2" ;
    private static String[] ips = {"35.238.91.65:7777","35.231.27.235:7777"};

    private static String host = "35.238.91.65:7777";
    public static String alive = baseURL + "/alive";
    private static String baseURL = "http://" + host + "/api";
    JSONObject jsonObject = new JSONObject();
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiEndPoint.alive,
            new Response.Listener<JSONObject>() {
                public void onResponse(JSONObject response) {

                } new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        host = "35.231.27.235:7777";
                        baseURL = "http://" + host + "/api";
                    }

            }
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
