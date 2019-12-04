package com.photogram.Modelo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.util.Date;

public class FotoModerador {
    private String usuario;
    private Date fecha;
    private String path;
    private Bitmap bitmap;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FotoModerador() {
        usuario = "User";
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitMap(Bitmap bitMap) {
        this.bitmap = bitmap;
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
