package com.photogram.Modelo;

import java.util.List;

public class Conversacion{
    private String lastMensaje;
    private Usuario remitente;
    private Usuario destinatario;

    public Conversacion(Usuario destinatario) {
        this.destinatario = destinatario;
        this.lastMensaje = "Last messagge";
    }

    public String getLastMensaje() {
        return lastMensaje;
    }

    public void setLastMensaje(String lastMensaje) {
        this.lastMensaje = lastMensaje;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }
}
