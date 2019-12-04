package com.photogram.servicesnetwork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

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
        final List<FotoModerador> fotos = new ArrayList<>();
        final JSONArray res = response;
        for(int i = 0; i < response.length(); i++) {

            JSONObject jsonObject = res.getJSONObject(i);
            final FotoModerador foto = new FotoModerador();
            foto.setUsuario(jsonObject.getString("username"));
            //foto.setFecha((Date)jsonObject.get("fecha"));
            foto.setPath(jsonObject.getString("path"));
            int x = foto.getPath().length();
            final String path = foto.getPath().substring(5, x);
            foto.setPath(path);
            /*Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {

                        try {

                            URL url = new URL("http://10.0.2.2:7777/static/"+ path);
                            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            System.out.println(bmp);
                            foto.setBitMap(bmp);
                            System.out.println("Finaliza lo de los bitmaps" + path);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        fotos.add(foto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
*/
        }
        return fotos;
    }
}
