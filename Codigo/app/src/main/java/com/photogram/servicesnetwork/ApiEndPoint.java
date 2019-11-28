package com.photogram.servicesnetwork;

public class ApiEndPoint {
    private static String host = "35.238.91.65:8080"; //10.0.2.2 es para hacer referencia al localhost de la máquina, no a la dirección del emulador
    private static String baseURL = "http://" + host + "/api";

    public static String login = baseURL + "/login";
}
