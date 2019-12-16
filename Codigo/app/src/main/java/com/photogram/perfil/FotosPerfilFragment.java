package com.photogram.perfil;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.photogram.R;
import com.photogram.adapters.FotoFeedAdapter;
import com.photogram.adapters.ProfilePhotosAdapter;
import com.photogram.modelo.Foto;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.JSONAdapter;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FotosPerfilFragment extends Fragment {
    private RecyclerView rv;
    private List<Foto> fotos;
    private String user;

    private VolleyS volley;
    private RequestQueue requestQueue;

    ProfilePhotosAdapter adapter;
    public FotosPerfilFragment(String user){
        this.user = user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        volley = VolleyS.getInstance(getContext());
        requestQueue = volley.getRequestQueue();

        rv = view.findViewById(R.id.rv_fotos_perfil);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        getFotos();

        return view;
    }

    private void getFotos() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ApiEndPoint.getPhotoByUser + this.user, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    fotos = JSONAdapter.allFotosFeedAdapter(response);
                    adapter = new ProfilePhotosAdapter(fotos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                rv.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERRRORRR", error.getMessage());
            }
        });
        volley.addToQueue(jsonArrayRequest);
    }

}
