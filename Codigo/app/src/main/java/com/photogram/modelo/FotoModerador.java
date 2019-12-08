package com.photogram.modelo;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;

public class FotoModerador {
    private String usuario;
    private Date fecha;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FotoModerador() {
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public String getUsuario() {
        return usuario;
    }


}
