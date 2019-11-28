package com.photogram.servicesnetwork;

import com.photogram.pojo.LoginPOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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


}
