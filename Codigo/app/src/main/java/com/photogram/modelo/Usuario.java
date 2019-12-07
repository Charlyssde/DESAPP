package com.photogram.modelo;

import java.util.Random;

public class Usuario {
    private String username;

    public Usuario() {
        this.username = "username test";
    }

    public String getUsername() {
        return username + generateRndm() ;
    }

    private int generateRndm(){
        Random rd = new Random();
        return rd.nextInt();
    }
}
