package com.photogram.modelo;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conversacion implements Serializable {
    private Mensaje lastMensaje;
    private String me;
    private String destinatario;
    private List<Mensaje> mensajes;

    public Conversacion(String destinatario, String me){
        this.destinatario = destinatario;
        this.me = me;
    }

    public Mensaje getLastMensaje() {
        return lastMensaje;
    }

    public void setLastMensaje(Mensaje lastMensaje) {
        this.lastMensaje = lastMensaje;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
