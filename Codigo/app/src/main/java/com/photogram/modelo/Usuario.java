package com.photogram.modelo;

import java.util.Random;

public class Usuario {
    private String username;

    public Usuario() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private int generateRndm(){
        Random rd = new Random();
        return rd.nextInt();
    }
}
