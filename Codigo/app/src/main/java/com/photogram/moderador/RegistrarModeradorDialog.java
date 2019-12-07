package com.photogram.moderador;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrarModeradorDialog extends DialogFragment {

    private String TAG = "RegistrarModerador";

    private EditText txtusername;
    private EditText txtpassword;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final VolleyS volley = VolleyS.getInstance(RegistrarModeradorDialog.super.getContext());
        final RequestQueue fRequestQue;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        View MyView = inflater.inflate(R.layout.dialog_registrar_moderador, null);
        txtusername = MyView.findViewById(R.id.usernameMod);
        txtpassword = MyView.findViewById(R.id.passwordMod);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(MyView)
                // Add action buttons
                .setPositiveButton(R.string.registrar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                            Map<String, String> param = new HashMap<>();
                            param.put ("username", txtusername.getText().toString());
                            param.put ("password", txtpassword.getText().toString());

                            JSONObject jsonObject = new JSONObject(param);

                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.registrarModerador, jsonObject,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            //Hacer un toast de exito4
                                            //Toast.makeText(getApplicationContext(),
                                            //                    "Toast por defecto", Toast.LENGTH_SHORT).show;
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e(TAG, "" + error.networkResponse);
                                        }
                                    });
                        volley.addToQueue(jsonObjectRequest);
                    }

                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        RegistrarModeradorDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }


}
