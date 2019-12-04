package com.photogram.servicesnetwork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static List<FotoModerador> allFotosAdapter(JSONArray response) throws JSONException{
        List<FotoModerador> fotos = new ArrayList<>();
        System.out.println("Entra aquí");
        URL url = null;

        for(int i = 0; i < response.length(); i++){
            JSONObject jsonObject = response.getJSONObject(i);
            FotoModerador foto = new FotoModerador();
            foto.setUsuario(jsonObject.getString("username"));
            foto.setFecha((Date)jsonObject.get("fecha"));
            foto.setPath(jsonObject.getString("path"));
            /*
            String path = foto.getPath().substring(5,foto.getPath().length() - 1);
            try {

                url = new URL(ApiEndPoint.descargaImg + path);
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                foto.setBitMap(bmp);
                System.out.println("Finaliza lo de los bitmaps");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            fotos.add(foto);
        }

        return fotos;
    }
}
