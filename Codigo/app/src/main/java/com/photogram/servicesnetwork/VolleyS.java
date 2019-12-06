package com.photogram.servicesnetwork;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyS {
    private static VolleyS mVolleyS = null;

    private RequestQueue mRequestQueue;

    private VolleyS (Context context){ mRequestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyS getInstance(Context context){ //Context hace referencia al contexto(clase/actividad)en la que se ejecuta la función

        if(mVolleyS == null){
            mVolleyS = new VolleyS(context);
        }
        return mVolleyS;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public void addToQueue(Request request){
        if(request != null){
            request.setTag(this);
            if(mRequestQueue == null){
                mRequestQueue = getRequestQueue();

            }

            mRequestQueue.add(request);
        }
    }
}
