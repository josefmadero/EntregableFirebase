package com.jmadero.entregablefirebase.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.facebook.share.widget.ShareButton;
import com.jmadero.entregablefirebase.R;
import com.jmadero.entregablefirebase.controller.ArtistController;
import com.jmadero.entregablefirebase.model.Paint;

import java.util.ArrayList;
import java.util.List;

import com.jmadero.entregablefirebase.util.ResultListener;


public class PaintsActivity extends AppCompatActivity {

//    ver fotos del miercoles, y en el main pedirle al controller la lista de artistas, y para cada artista(for) pedir sus obras y setearle el nombre del artista como author (poner atributo author a la obra)
//
//    modelo: artistContainer, artist, obra,
//
//    artist tiene un list de obras, y obras tiene un nombre y un author.



    private PaintsAdapter paintsRecyclerAdapter;
    private List<Paint> paintsList;
    private RecyclerView paintsRecycler;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paints);

        this.context = this;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
//        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Entregable Firebase</font>"));




        ArtistController artistController = new ArtistController();
        artistController.getPaintsList(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> paintList) {

                paintsList = paintList ;

                updateRecyclerView(paintsList);
            }
        });
//
//        paintsRecycler = (RecyclerView) findViewById(R.id.recyclerView);
//        paintsRecycler.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        paintsRecycler.setLayoutManager(layoutManager);
//        paintsRecyclerAdapter = new PaintsAdapter(context, new ArrayList<Paint>());
//        paintsRecycler.setAdapter(paintsRecyclerAdapter);
//        PaintListener paintListener = new PaintListener();
//        paintsRecyclerAdapter.setOnClickListener(paintListener);

    }

    private class PaintListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(context, PaintsDetailActivity.class);
            Bundle bundle = new Bundle();
            Paint actualPaint = paintsList.get(paintsRecycler.getChildPosition(v));

            bundle.putString(PaintsDetailActivity.NAME, actualPaint.getName());
            bundle.putString(PaintsDetailActivity.ARTIST,actualPaint.getArtist());
            bundle.putString(PaintsDetailActivity.LINK,actualPaint.getLink());
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }

    private void updateRecyclerView(List<Paint> paints) {

        paintsRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        paintsRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        paintsRecycler.setLayoutManager(layoutManager);
        paintsRecyclerAdapter = new PaintsAdapter(getApplicationContext(), new ArrayList<Paint>());
        paintsRecyclerAdapter.setPaintsList(paints);
        paintsRecycler.setAdapter(paintsRecyclerAdapter);

        PaintListener paintListener = new PaintListener();
        paintsRecyclerAdapter.setOnClickListener(paintListener);


        paintsRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_action_login:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            case R.id.app_bar_action_uploadPicture:
                startActivity(new Intent(this, UploadPictureActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }



}
