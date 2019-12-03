package com.photogram.Modelo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Foto {
    private String path;
    private String owner;
    private Date fecha;
    private byte[] bytes;
    private Bitmap bitmap;
    private List<Comentario> comentarios;
    private List<Reaccion> reacciones;

    public Foto() {

        comentarios = new ArrayList<>();
        reacciones = new ArrayList<>();

    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
        setBitmap();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    private void setBitmap() {
        this.bitmap = BitmapFactory.decodeByteArray(getBytes(), 0, getBytes().length);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPath() {
        return path;
    }

    public String getOwner() {
        return owner;
    }
}
