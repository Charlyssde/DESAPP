package com.photogram.modelo;

import java.io.Serializable;
import java.util.Date;

public class Mensaje implements Serializable {
    private String content = "cont";
    private Date date;
    private String sender;
    private String receiver;

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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }




}
