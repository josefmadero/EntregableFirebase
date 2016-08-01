package com.jmadero.entregablefirebase.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class UploadPictureActivity extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageRef;
    StorageReference uploadRef;
    StorageReference downloadRef;
    ImageView downloadImage;
    private EasyImage easyImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadImage = (ImageView)findViewById(R.id.imageDownload);

        //Aca obtengo mi instancia del Storage en FireBase
        storage = FirebaseStorage.getInstance();

        //Aca obtengo mi referencia al Storage en FireBase, seria como pararme dentro del la carpeta raiz
        storageRef = storage.getReferenceFromUrl("gs://mystorage-425f1.appspot.com");

        //Aca configuro mi easy image para decirle donde quiero que cargue la imagen que luego se va a pedir
        easyImage = EasyImage.getInstance();
        easyImage.with(this).into(downloadImage);
    }


    public void getImage(View view){
        //Aca le pido al easyImage que me mediante un popup de donde quiero obtener la imagen, si la quiero obtener
        //de la camara o de la galeria.
        //Cuando seleccione de donde quiero obtenerlo y termine el proceso se va a llamar a mi activity on result.
        easyImage.openSelector(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Aca tengo que llamar al easyImage y pasarle los resultados que van a venir del activity de la camara o el de la galeria
        easyImage.onActivityResult(requestCode,resultCode,data);
    }


    public void upload(View view){
        //Ya una vez obtenia la imagen que quiero subir tengo que obtener el bitmap de la misma
        Bitmap bitmap = easyImage.getBitmap();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();


        //Aca creo el path del archivo que voy a subir y obtengo su referencia. Ya storageRef apunta a la raiz de mi
        //storage, asi que vamos a pedirle que cree ahi la referencia al archivo imagen.jpg
        uploadRef = storageRef.child("casa/algo/imagen.jpg");

        //Aca le informo a la referencia de donde voy a obtener la informacion
        UploadTask uploadTask = uploadRef.putBytes(data);

        //Aca hago el pedido y utilizo un listener que se va a llamar una vez que: se haya completado la subida o esta fallo
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(),"Upload Failed",Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"Upload Successful",Toast.LENGTH_LONG).show();
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });

    }

    public void download(View view){
        try {
            //Para bajar una imagen creo un archivo local el cual voy a llenar con la informacion del archivo en FireBase
            final File localTempFile = File.createTempFile("pelota", "png");

            //Aca es donde busco la referencia al archivo en el Storage de FireBase, en este caso estoy
            //En la carpeta raiz y busco al archivo de nombre pelota.png
            downloadRef = storageRef.child("pelota.png");

            //Aca bajo el archivo de firebase al archivo localTemporal y seteo el listener que me avisara si logro cargar el archivo.
            downloadRef.getFile(localTempFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    downloadImage.setImageURI(Uri.fromFile(localTempFile));
                    Toast.makeText(getApplicationContext(),"Download Successful",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(getApplicationContext(),"Download Failed",Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


