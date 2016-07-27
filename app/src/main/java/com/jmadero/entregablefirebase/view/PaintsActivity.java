package com.jmadero.entregablefirebase.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.jmadero.entregablefirebase.R;
import com.jmadero.entregablefirebase.controller.ArtistController;
import com.jmadero.entregablefirebase.model.Artist;
import com.jmadero.entregablefirebase.model.Paint;

import java.util.ArrayList;
import java.util.List;

import util.ResultListener;


public class PaintsActivity extends AppCompatActivity {

//    ver fotos del miercoles, y en el main pedirle al controller la lista de artistas, y para cada artista(for) pedir sus obras y setearle el nombre del artista como author (poner atributo author a la obra)
//
//    modelo: artistContainer, artist, obra,
//
//    artist tiene un list de obras, y obras tiene un nombre y un author.



    private PaintsAdapter paintsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paints);

        RecyclerView productsRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        productsRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productsRecycler.setLayoutManager(layoutManager);

        paintsRecyclerAdapter = new PaintsAdapter(getApplicationContext(), new ArrayList<Paint>());
        productsRecycler.setAdapter(paintsRecyclerAdapter);

        ArtistController artistController = new ArtistController();
        artistController.getPaintsList(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> paintsList) {
                updateRecyclerView(paintsList);
            }
        });
    }

    private void updateRecyclerView(List<Paint> paints) {
        paintsRecyclerAdapter.setPaintsList(paints);
        paintsRecyclerAdapter.notifyDataSetChanged();
    }
}
