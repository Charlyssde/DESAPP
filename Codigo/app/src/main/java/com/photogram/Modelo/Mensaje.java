package com.photogram.Modelo;

import java.io.Serializable;
import java.util.Date;

public class Mensaje implements Serializable {
    private String contenido = "cont";
    private Date fecha;
    private Usuario remitente;
    private boolean isMine;

    public Mensaje() {
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public Usuario getRemitente() {
        return remitente;
    }
}
