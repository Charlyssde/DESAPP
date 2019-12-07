package com.photogram.Feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.NetworkClient;
import com.photogram.servicesnetwork.UploadAPIs;
import com.photogram.servicesnetwork.VolleyS;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStream;
import java.io.FileInputStream;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class subir_foto extends AppCompatActivity {

    public static final int REQUEST_CAPTURE = 1;
    public static final int PICK_IMAGE = 100;
    private ImageView img_foto;

    private FloatingActionButton btn_enviar;
    private EditText txt_idfoto;
    private Bitmap foto;
    private Uri photoURI;
    private Response resws;
    private ProgressDialog espera;
    private String path;
    private File photo;
    private Boolean bool = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_foto);
        Bundle b = getIntent().getExtras();
        if(b != null)
            this.bool = b.getBoolean("key");
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        img_foto = (ImageView)findViewById(R.id.img_foto);

        btn_enviar = (FloatingActionButton)findViewById(
                R.id.btn_enviar);
        txt_idfoto = (EditText)findViewById(R.id.txt_idfoto);
        txt_idfoto.setHint(this.bool ? "Comentario" : "Estado");

        validarPermisosAlmacenamiento();
        btn_enviar.setEnabled(true);
    }

    private void validarPermisosAlmacenamiento() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,
                    "SIN ACCESO AL ALMACENAMIENTO INTERNO",
                    Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);
        }else{
            Toast.makeText(this,
                    "CON ACCESO AL ALMACENAMIENTO INTERNO",
                    Toast.LENGTH_LONG).show();
        }
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 35, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgBytes, android.util.Base64.DEFAULT);
    }

    private void uploadImage() {
        SharedPreferences preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
        final String username = preferences.getString("USERNAME", "");
        Log.i("hola", username);

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("image", imageToString(foto));
        JSONObject jsonObject = new JSONObject(params);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.subirFoto, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String Response = response.getString("response");
                            Log.i("hola", Response);
                            Toast.makeText(subir_foto.this, Response, Toast.LENGTH_LONG).show();
                            img_foto.setImageResource(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
        })
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content Type", "application/json");

                SharedPreferences myPreferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE);
                String token = myPreferences.getString("TOKEN", "unknown");
                System.out.println(token);
                headers.put("Authorization", token);
                return headers;
            }
        };

        VolleyS.getInstance(subir_foto.this).addToQueue(stringRequest);
    }

    //ya no sirve uploadToServer
    private void uploadToServer(String filePath) {
        Retrofit retrofit = NetworkClient.getRetrofitClient(this);
        UploadAPIs uploadAPIs = retrofit.create(UploadAPIs.class);
        //Create a file object using file path
        File file = new File(filePath);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("newImage", file.getName(), fileReqBody);
        //Create request body with text description and text media type
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "rodrigo");
        Toast.makeText(subir_foto.this, "Cargando foto", Toast.LENGTH_SHORT).show();


        //
        Call call = uploadAPIs.uploadImage(part, description);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                Toast.makeText(subir_foto.this, "Foto arriba", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(subir_foto.this, t.getMessage(), Toast.LENGTH_LONG).show();
                System.err.print(t.getMessage());
                t.printStackTrace();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data){

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data !=null ) {
                this.photoURI = data.getData();
                Log.d("******************", String.valueOf(this.photoURI));
                 path = photoURI.getPath();
                Toast.makeText(subir_foto.this, path, Toast.LENGTH_LONG).show();
                img_foto.setImageURI(this.photoURI);
                foto = ((BitmapDrawable)img_foto.getDrawable()).
                        getBitmap();
                btn_enviar.setEnabled(true);


            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            // Get the cursor
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();
            //Get the column index of MediaStore.Images.Media.DATA
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //Gets the String value in the column
            String imgDecodableString = cursor.getString(columnIndex);
            cursor.close();
            Log.d("file1", String.valueOf(filePathColumn));
            Log.d("file2", imgDecodableString);
            this.path = imgDecodableString;


        }
    }

    public void subirFoto(View v){
        //btn_enviar.setEnabled(false);
        uploadImage();
        //uploadToServer(this.path);

    }

    public void abrirGaleria(View v){
        btn_enviar.setEnabled(true);
        Intent g = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(g, PICK_IMAGE);

    }


}

