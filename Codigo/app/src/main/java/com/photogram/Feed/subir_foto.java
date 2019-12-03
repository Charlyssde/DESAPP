package com.photogram.Feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.photogram.Inicio.Iniciar_Sesion;
import com.photogram.Inicio.LoginModerador;
import com.photogram.R;
import com.photogram.servicesnetwork.NetworkClient;
import com.photogram.servicesnetwork.UploadAPIs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_foto);

        img_foto = (ImageView)findViewById(R.id.img_foto);

        btn_enviar = (FloatingActionButton)findViewById(
                R.id.btn_enviar);
        txt_idfoto = (EditText)findViewById(R.id.txt_idfoto);


        btn_enviar.setEnabled(false);
    }

    public void subirFoto(View v){
        uploadToServer(this.path);

    }


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

        //
        Call call = uploadAPIs.uploadImage(part, description);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                Toast.makeText(subir_foto.this, "Foto subida", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(subir_foto.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data){

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data !=null ) {


                this.photoURI = data.getData();
                 path = photoURI.getPath();
                img_foto.setImageURI(this.photoURI);
                foto = ((BitmapDrawable)img_foto.getDrawable()).
                        getBitmap();
                btn_enviar.setEnabled(true);

        }
    }

    public void abrirGaleria(View v){

        btn_enviar.setEnabled(false);
        Intent g = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(g, PICK_IMAGE);

    }


}

