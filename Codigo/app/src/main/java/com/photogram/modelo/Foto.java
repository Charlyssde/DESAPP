package com.photogram.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Foto {
    private String path;
    private String usuario;
    private Date fecha;
    private List<Comentario> comentarios;
    private List<Reaccion> reacciones;

    public Foto() {

        comentarios = new ArrayList<>();
        reacciones = new ArrayList<>();

    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }


    public void setPath(String path) {
        this.path = path;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void setReacciones(List<Reaccion> reacciones) {
        this.reacciones = reacciones;
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


}
