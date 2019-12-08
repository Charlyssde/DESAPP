package com.photogram.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conversacion implements Serializable {
    private String lastMensaje;
    private Usuario remitente;
    private Usuario destinatario;
    private List<Mensaje> mensajes;

    public Conversacion(Usuario destinatario) {
        this.destinatario = destinatario;
        mensajes = new ArrayList<>();
        Mensaje m = new Mensaje();
        lastMensaje = m.getContenido();
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
