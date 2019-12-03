package com.photogram.servicesnetwork;

import com.photogram.Modelo.FotoModerador;
import com.photogram.Modelo.Usuario;
import com.photogram.pojo.LoginPOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        for(int i = 0; i < response.length(); i++){
            JSONObject jsonObject = response.getJSONObject(i);
            FotoModerador foto = new FotoModerador();
            foto.setUsuario(jsonObject.getString("username"));
            foto.setBytes(jsonObject.getString("bytes").getBytes());
            foto.setFecha((Date)jsonObject.get("fecha"));
            foto.setPath(jsonObject.getString("path"));
            fotos.add(foto);
        }

        return fotos;
    }
}
