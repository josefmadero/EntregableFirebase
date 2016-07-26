package com.jmadero.entregablefirebase.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ejemplo.mercadoLibre.R;
import com.ejemplo.mercadoLibre.controller.ProductController;
import com.ejemplo.mercadoLibre.model.Product;

import java.util.ArrayList;
import java.util.List;

import util.ResultListener;

public class PaintsActivity extends AppCompatActivity {

//    ver fotos del miercoles, y en el main pedirle al controller la lista de artistas, y para cada artista(for) pedir sus obras y setearle el nombre del artista como author (poner atributo author a la obra)
//
//    modelo: artistContainer, artist, obra,
//
//    artist tiene un list de obras, y obras tiene un nombre y un author.




    private AdapterObras productsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        RecyclerView productsRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        productsRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productsRecycler.setLayoutManager(layoutManager);

        productsRecyclerAdapter = new AdapterObras(getApplicationContext(), new ArrayList<Product>());
        productsRecycler.setAdapter(productsRecyclerAdapter);

        ProductController productController = new ProductController();
        productController.getProductsList(new ResultListener<List<Product>>() {
            @Override
            public void finish(List<Product> resultado) {
                updateRecyclerView(resultado);
            }
        });
    }

    private void updateRecyclerView(List<Product> products) {
        productsRecyclerAdapter.setProductList(products);
        productsRecyclerAdapter.notifyDataSetChanged();
    }
}
