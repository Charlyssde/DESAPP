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
import com.photogram.Inicio.LoginModerador;
import com.photogram.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class subir_foto extends AppCompatActivity {

    public static final int REQUEST_CAPTURE = 1;
    public static final int PICK_IMAGE = 100;
    private ImageView img_foto;
    private FloatingActionButton btn_guardar;
    private FloatingActionButton btn_enviar;
    private EditText txt_idfoto;
    private Bitmap foto;
    private Uri photoURI;
    private Response resws;
    private ProgressDialog espera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_foto);

        img_foto = (ImageView)findViewById(R.id.img_foto);
        btn_guardar = (FloatingActionButton)findViewById(
                R.id.btn_guardar);
        btn_enviar = (FloatingActionButton)findViewById(
                R.id.btn_enviar);
        txt_idfoto = (EditText)findViewById(R.id.txt_idfoto);

        btn_guardar.setEnabled(false);
        btn_enviar.setEnabled(false);
       /*FloatingActionButton btn2 = (FloatingActionButton) findViewById(R.id.btn_camara);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto(v);
            }
        }); */

        if(!validarCamara()){
            Toast.makeText(this,
                    "El dispositivo no cuenta con camara",
                    Toast.LENGTH_LONG).show();
        }
        validarPermisosAlmacenamiento();
    }

    private boolean validarCamara(){

        return getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_ANY);

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
        }
    }

    public void tomarFoto(View v){
        Toast.makeText(this,
                "El dispositivo hola",
                Toast.LENGTH_LONG).show();
        btn_guardar.setEnabled(false);
        btn_enviar.setEnabled(false);
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Crear archivo temporal de imagen
        Toast.makeText(this,
                "El dispositivo hola",
                Toast.LENGTH_LONG).show();
        File tmp = null;
        try {
            tmp = crearArchivoTemporal();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (tmp != null) {
            // Acceder a la ruta completa del archivo
            photoURI = FileProvider.getUriForFile(this,
                    getApplicationContext().getPackageName(), tmp);
            i.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            // Lanzar camara
            startActivityForResult(i, REQUEST_CAPTURE);
        }
    }

    private File crearArchivoTemporal() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMdd_HHmmss");
        String nombre = sdf.format(new Date()) + ".jpg";
        File path = getExternalFilesDir(
                Environment.DIRECTORY_PICTURES + "/tmps");
        File archivo = File.createTempFile(nombre,
                ".jpg",path);
        return archivo;
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data){
        //--------DESDE CAMARA----------//
        if(requestCode == REQUEST_CAPTURE){
            if (resultCode == RESULT_OK) {
                img_foto.setImageURI(this.photoURI);
                foto = ((BitmapDrawable)img_foto.getDrawable()).
                        getBitmap();
                btn_guardar.setEnabled(true);
                btn_enviar.setEnabled(true);
            }
        }
        //----------DESDE GALERIA----------//
        if(requestCode == PICK_IMAGE){
            if (resultCode == RESULT_OK){
                this.photoURI = data.getData();
                img_foto.setImageURI(this.photoURI);
                foto = ((BitmapDrawable)img_foto.getDrawable()).
                        getBitmap();
                btn_enviar.setEnabled(true);
            }
        }
    }

    public void abrirGaleria(View v){
        this.btn_guardar.setEnabled(false);
        btn_enviar.setEnabled(false);
        Intent g = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(g, PICK_IMAGE);
    }

    public void guardarFoto(View v) {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMdd_HHmmss");
        String nombre = sdf.format(new Date()) + ".jpg";
        Bitmap bitmap = foto;
        String base = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File path = new File(base + "/FotoApp");
        path.mkdirs();
        File archivofinal = new File(path, nombre);
        if (archivofinal.exists()) {
            archivofinal.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(archivofinal);
            bitmap.compress(Bitmap.CompressFormat.JPEG,
                    100, out);
            out.flush();
            out.close();
            Toast.makeText(this,
                    "Foto guardada correctamente",
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        /* Invoca el scanner de archivos, para que la nueva foto
           sea visible desde la galería del teléfono.
         */
        MediaScannerConnection.scanFile(this,
                new String[]{archivofinal.toString()},
                null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " +
                                path + ":");
                        Log.i("ExternalStorage", "-> uri=" +
                                uri);
                    }
                }
        );
    }

    private void showProgressDialog() {
        espera = new ProgressDialog(this);
        espera.setMessage("Espera por favor...");
        espera.setCancelable(false);
        espera.show();
    }
    private void hideProgressDialog() {
        if (espera.isShowing())
            espera.hide();
    }
  /*  public void subirAServidor(View v){
        String id = null;
        if(!txt_idfoto.getText().toString().isEmpty()){
            id = txt_idfoto.getText().toString();
        }else{
            txt_idfoto.setError("Introduce el id de la foto");
            return;
        }
        WSPOSTFotosTask task = new WSPOSTFotosTask();
        task.execute(id,this.foto);
    } */


    private void mostrarAlertDialog(String titulo, String mensaje){
        AlertDialog dialog = new AlertDialog.Builder(subir_foto.this).create();
        dialog.setMessage(mensaje);
        dialog.setTitle(titulo);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }
}

