package com.photogram.servicesnetwork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import com.photogram.Modelo.Foto;
import com.photogram.Modelo.FotoModerador;
import com.photogram.Modelo.Usuario;
import com.photogram.pojo.LoginPOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONAdapter {

    public static LoginPOJO loginAdapter(JSONObject jsonObject){
        LoginPOJO res = new LoginPOJO();
        try {
            res.setToken(jsonObject.getString("token"));
            res.setUsername(jsonObject.getString("username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static List<FotoModerador> allFotosAdapter(JSONArray response) throws JSONException{
        final List<FotoModerador> fotos = new ArrayList<>();
        final JSONArray res = response;
        for(int i = 0; i < response.length(); i++) {

            JSONObject jsonObject = res.getJSONObject(i);
            final FotoModerador foto = new FotoModerador();
            foto.setUsuario(jsonObject.getString("username"));
            foto.setPath(jsonObject.getString("path"));
            int x = foto.getPath().length();
            final String path = foto.getPath().substring(5, x);
            foto.setPath(path);
            fotos.add(foto);
        }
        return fotos;
    }

    public static List<Foto> allFotosFeedAdapter(JSONArray response) throws JSONException {

        final List<Foto> fotos = new ArrayList<>();
        final JSONArray res = response;
        for(int i = 0; i < response.length(); i++) {

            JSONObject jsonObject = res.getJSONObject(i);
            final Foto foto = new Foto();
            //foto.setFecha((Date)jsonObject.get("fecha"));
            foto.setUsuario(jsonObject.getString("username"));
            foto.setPath(jsonObject.getString("path"));
            int x = foto.getPath().length();
            final String path = foto.getPath().substring(5, x);
            foto.setPath(path);
            fotos.add(foto);
        }
        return fotos;

    }
}
