package com.photogram.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.photogram.modelo.Comentario;
import com.photogram.modelo.Reaccion;

public class Foto {
    private String path;
    private String usuario;
    private Date fecha;
    private List<Comentario> comentarios;
    private List<Reaccion> reacciones;
    private String fotoId;

    public Foto() {

        comentarios = new ArrayList<>();
        reacciones = new ArrayList<>();

    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setFotoId(String fotoId) { this.fotoId = fotoId; }

    public String getFotoId() {return this.fotoId; }

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
