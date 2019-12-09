package com.photogram.modelo;

public class Comentario {

    private String contenido;
    private String username;

    public Comentario(String username, String contenido) {
        this.username = username;
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUsuario() {
        return username;
    }

    public void setUsuario(String username) {
        this.username = username;
    }
}
