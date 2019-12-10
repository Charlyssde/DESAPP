package com.photogram.servicesnetwork;

public class ApiEndPoint {

    private static String host = "10.0.2.2:7777"; //10.0.2.2 es para hacer referencia al localhost de la máquina, no a la dirección del emulador
    private static String baseURL = "http://" + host + "/api";

    public static String hostDownloads = "http://10.0.2.2:7777/static/";

    public static String login = baseURL + "/login";
    public static String loginModerador = baseURL + "/";
    public static String registrarModerador = baseURL + "/";
    public static String registrarUsuario = baseURL + "/user";
    public static String getAllPhotos = baseURL + "/img/all";
    public static String eliminarFoto = baseURL + "/img/";
    public static String reportarCuenta = baseURL + "/mod/";
    public static String subirFoto = baseURL + "/img/prueba";
    public static String comentar = baseURL + "/comment";
    public static String reaccionar = baseURL + "/reaction";
    //public static String descargaImg = baseURL + "/static/";
}
