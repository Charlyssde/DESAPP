package com.photogram.modelo;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conversacion implements Serializable {
    private Mensaje lastMensaje;
    private String me;
    private String destinatario;
    private ArrayList<Mensaje> mensajes;
    private String key;

    public Conversacion(String destinatario, String me){
        this.destinatario = destinatario;
        this.me = me;
        this.key = this.destinatario + this.me;
        mensajes = new ArrayList<>();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
