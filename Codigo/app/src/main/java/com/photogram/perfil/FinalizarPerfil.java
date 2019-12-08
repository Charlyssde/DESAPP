package com.photogram.perfil;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.photogram.R;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.photogram.servicesnetwork.VolleyS;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class FinalizarPerfil extends AppCompatActivity {
    public static final int REQUEST_CAPTURE = 1;
    public static final int PICK_IMAGE = 100;
    private ImageView img_foto;

    private FloatingActionButton btn_enviar;
    private EditText estado;
    private Bitmap foto;
    private Uri photoURI;
    private Response resws;
    private ProgressDialog espera;
    private String path;
    private File photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_foto);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        img_foto = findViewById(R.id.img_foto);

        btn_enviar = findViewById(
                R.id.btn_enviar);
        estado = findViewById(R.id.txt_idfoto);
        estado.setHint("Estado");

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

    private String encodeFileToBase64Binary(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file);
        byte[] encoded = Base64.getEncoder().encode(bytes);
        String encodedString = new String(encoded);

        return encodedString;
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(imgBytes, android.util.Base64.DEFAULT);
    }

    private void uploadImage() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiEndPoint.subirFoto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Log.i("hola", Response);
                            Toast.makeText(FinalizarPerfil.this, Response, Toast.LENGTH_LONG).show();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("comment", estado.getText().toString());
                params.put("image", imageToString(foto));
                return params;
            }
        };

        VolleyS.getInstance(FinalizarPerfil.this).addToQueue(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data){

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data !=null ) {
            this.photoURI = data.getData();
            Log.d("******************", String.valueOf(this.photoURI));
            path = photoURI.getPath();
            Toast.makeText(FinalizarPerfil.this, path, Toast.LENGTH_LONG).show();
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
