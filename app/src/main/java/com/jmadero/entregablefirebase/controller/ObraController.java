package com.jmadero.entregablefirebase.controller;

import com.ejemplo.mercadoLibre.dao.ProductDAO;
import com.ejemplo.mercadoLibre.model.Product;

import java.util.List;

import util.ResultListener;

/**
 * Created by digitalhouse on 6/06/16.
 */
public class ObraController {

    public void getProductsList(final ResultListener<List<Product>> listenerFromView) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.getProductsFromFireBase(new ResultListener<List<Product>>() {
            @Override
            public void finish(List<Product> resultado) {
                listenerFromView.finish(resultado);
            }
        });
    }


//    public void addMovie(final ResultListener<Obra> listenerFromView,Obra product) {
//        ObraDAO productDAO = new ObraDAO();
//        productDAO.addProduct(new ResultListener<Obra>() {
//            @Override
//            public void finish(Obra resultado) {
//                listenerFromView.finish(resultado);
//            }
//        }, product);
//    }
}
