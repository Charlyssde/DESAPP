package com.photogram.modelo;

import java.io.Serializable;
import java.util.Date;

public class Mensaje implements Serializable {
    private String content = "cont";
    private Date date;
    private Usuario sender;
    private Usuario receiver;

    public Mensaje() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Usuario getSender() {
        return sender;
    }

    public void setSender(Usuario sender) {
        this.sender = sender;
    }

    public Usuario getReceiver() {
        return receiver;
    }

    public void setReceiver(Usuario receiver) {
        this.receiver = receiver;
    }




}
