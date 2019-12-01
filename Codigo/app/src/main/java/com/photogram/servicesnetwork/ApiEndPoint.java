package com.photogram.servicesnetwork;

public class ApiEndPoint {
    private static String host = "192.168.100.10:7777"; //10.0.2.2 es para hacer referencia al localhost de la máquina, no a la dirección del emulador
    private static String baseURL = "http://" + host + "/api";

    public static String login = baseURL + "/login";
    public static String loginModerador = baseURL + "/loginModerador";
    public static String registrarModerador = baseURL + "/Moderador/";
    public static String registrarUsuario = baseURL + "/registro";
}
