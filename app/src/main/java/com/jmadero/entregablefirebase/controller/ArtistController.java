package com.jmadero.entregablefirebase.controller;


import com.jmadero.entregablefirebase.dao.ArtistDAO;
import com.jmadero.entregablefirebase.model.Artist;
import com.jmadero.entregablefirebase.model.Paint;

import java.util.List;

import util.ResultListener;


/**
 * Created by digitalhouse on 6/06/16.
 */
public class ArtistController {

    List<Paint> paintsList;

    public void getPaintsList(final ResultListener<List<Paint>> listenerFromView) {
        ArtistDAO artistDAO = new ArtistDAO();
        artistDAO.getArtistsFromFireBase(new ResultListener<List<Artist>>() {
            @Override
            public void finish(List<Artist> artistsList) {
                for (Artist artist:artistsList) {
                    for (Paint paint:artist.getPaints()) {
                        paint.setArtist(artist.getName());
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
