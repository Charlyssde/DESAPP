package com.photogram.servicesnetwork;

public class ApiEndPoint {

    private static String host = "10.0.2.2:7777"; //10.0.2.2 es para hacer referencia al localhost de la máquina, no a la dirección del emulador
    private static String baseURL = "http://" + host + "/api";

    public static String hostDownloads = "http://10.0.2.2:7777/static/";

    public static String login = baseURL + "/login";
    public static String loginModerador = baseURL + "/loginModerador";
    public static String registrarModerador = baseURL + "/Moderador/";
    public static String registrarUsuario = baseURL + "/registro";
    public static String getAllPhotos = baseURL + "/img/getAllImages";
    public static String eliminarFoto = baseURL + "";
    public static String reportarCuenta = baseURL + "/user";
    public static String subirFoto = baseURL + "/img/prueba";
    //public static String descargaImg = baseURL + "/static/";
}
