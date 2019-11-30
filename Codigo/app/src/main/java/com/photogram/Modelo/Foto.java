package com.photogram.Modelo;

public class Foto {
    private String id;
    private String path;
    private Usuario owner;

    public Foto() {
        owner = new Usuario();
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Usuario getOwner() {
        return owner;
    }
}
