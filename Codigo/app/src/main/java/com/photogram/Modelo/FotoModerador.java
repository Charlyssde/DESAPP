package com.photogram.Modelo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.util.Date;

public class FotoModerador {
    private String usuario;
    private Date fecha;
    private byte[] bytes;
    private String path;
    private Bitmap bitmap;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public FotoModerador() {

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    private void setBitMap() {
        this.bitmap = BitmapFactory.decodeByteArray(getBytes(), 0, getBytes().length);
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

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
        setBitMap();

    }

    public String getUsuario() {
        return usuario;
    }


}
