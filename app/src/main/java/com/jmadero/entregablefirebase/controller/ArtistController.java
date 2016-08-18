package com.jmadero.entregablefirebase.controller;


import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jmadero.entregablefirebase.dao.ArtistDAO;
import com.jmadero.entregablefirebase.model.Artist;
import com.jmadero.entregablefirebase.model.Paint;

import java.util.ArrayList;
import java.util.List;

import com.jmadero.entregablefirebase.util.ResultListener;


/**
 * Created by digitalhouse on 6/06/16.
 */
public class ArtistController {

    List<Paint> paintsList = new ArrayList<>();
    FirebaseStorage storage;
    StorageReference storageRef;

    public void getPaintsList(final ResultListener<List<Paint>> listenerFromView) {
        ArtistDAO artistDAO = new ArtistDAO();
        artistDAO.getArtistsFromFireBase(new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> artistsList) {
                for (Artist artist:artistsList) {
                    for (final Paint paint:artist.getPaints()) {
                        paint.setArtist(artist.getName());

                        //Aca obtengo mi instancia del Storage en FireBase
                        storage = FirebaseStorage.getInstance();
                        //Aca obtengo mi referencia al Storage en FireBase, seria como pararme dentro del la carpeta raiz
                        storageRef = storage.getReferenceFromUrl("gs://entregablefirebase-50c58.appspot.com");

                        if(paint.getLink() == null){
                            storageRef.child(paint.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    // Got the download URL
                                    paint.setLink(uri.toString());
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });

                        }

                        paintsList.add(paint);
                    }
                }
                listenerFromView.finish(paintsList);
            }
        });
    }



//    public void addMovie(final ResultListener<Paint> listenerFromView,Paint product) {
//        ArtistDAO productDAO = new ArtistDAO();
//        productDAO.addProduct(new ResultListener<Paint>() {
//            @Override
//            public void finish(Paint resultado) {
//                listenerFromView.finish(resultado);
//            }
//        }, product);
//    }
}
