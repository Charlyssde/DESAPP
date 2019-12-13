package com.photogram.servicesnetwork;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.photogram.modelo.Foto;
import com.photogram.modelo.FotoModerador;
import com.photogram.modelo.Usuario;
import com.photogram.pojo.LoginPOJO;
import com.photogram.modelo.Comentario;
import com.photogram.modelo.Reaccion;

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
            foto.setFotoId(jsonObject.getString("_id"));
            foto.setUsuario(jsonObject.getString("username"));
            foto.setPath(jsonObject.getString("path"));
            int x = foto.getPath().length();
            final String path = foto.getPath().substring(7, x);
            foto.setPath(path);
            fotos.add(foto);
        }
        return fotos;
    }

    public static List<Foto> allFotosFeedAdapter(JSONArray response) throws JSONException {

        final List<Foto> fotos = new ArrayList<>();
        final JSONArray res = response;
        final List<Comentario> comentarios = new ArrayList<>();
        final List<Reaccion> reacciones = new ArrayList<>();
        for(int i = 0; i < response.length(); i++) {

            JSONObject jsonObject = res.getJSONObject(i);
            final Foto foto = new Foto();
            String fechaJS = jsonObject.getString("fecha");
            foto.setFotoId(jsonObject.getString("_id"));
            //Long milisegundos = Long.parseLong(fechaJS);
            //Date fecha = new Date(milisegundos);
            //foto.setFecha(fecha);
            foto.setUsuario(jsonObject.getString("username"));
            foto.setPath(jsonObject.getString("path"));
            JSONArray jsonComentarios = jsonObject.getJSONArray("comentarios");
            foto.setFotoId(jsonObject.getString("_id"));
            JSONArray jsonReacciones = jsonObject.getJSONArray("comentarios");
            foto.setReacciones(reacciones);
            foto.setComentarios(comentarios);
            int x = foto.getPath().length();
            final String path = foto.getPath().substring(7, x);
            foto.setPath(path);
            fotos.add(foto);
        }
        return fotos;

    }

    public static List<Usuario> usersAdapter(JSONArray response) throws JSONException{

        List<Usuario> usuarios =  new ArrayList<>();

        for( int i = 0; i < response.length(); i++){
            final Usuario usuario = new Usuario();
            JSONObject jsonObject = response.getJSONObject(i);
            usuario.setUsername(jsonObject.getString("username"));
            usuarios.add(usuario);
        }
        return usuarios;
    }

    public static List<Usuario> oneUserAdapter(JSONObject jsonObject){
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        try{
            usuario.setUsername(jsonObject.getString("username"));
            usuarios.add(usuario);
        }catch (Exception ex){

        }
        return usuarios;
    }


}
