package com.jmadero.entregablefirebase.controller;

import com.ejemplo.mercadoLibre.dao.ProductDAO;
import com.ejemplo.mercadoLibre.model.Product;

import java.util.List;

import util.ResultListener;

/**
 * Created by digitalhouse on 6/06/16.
 */
public class ArtistController {

    public void getProductsList(final ResultListener<List<Product>> listenerFromView) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.getProductsFromFireBase(new ResultListener<List<Product>>() {
            @Override
            public void finish(List<Product> resultado) {
                listenerFromView.finish(resultado);
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
