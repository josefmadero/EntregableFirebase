package com.jmadero.entregablefirebase.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jmadero.entregablefirebase.R;
import com.jmadero.entregablefirebase.model.Paint;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres on 22/06/16.
 */
public class PaintsAdapter extends RecyclerView.Adapter{

    private List<Paint> paintsList;
    private Context context;
    FirebaseStorage storage;
    StorageReference storageRef;

    public PaintsAdapter(Context context, List<Paint> paintsList) {

        this.context = context;
        this.paintsList = new ArrayList<>();
    }

    public void setPaintsList(List<Paint> paintsList) {
        this.paintsList = paintsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_detalle, parent, false);
        PaintViewHolder unTitularViewHolder = new PaintViewHolder(itemView);
        return unTitularViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Paint aPaint = paintsList.get(position);
        if(aPaint.getLink().equals(null)){
            storageRef.child(aPaint.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // Got the download URL
                    aPaint.setLink(uri.toString());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });

        }
        PaintViewHolder aPaintViewHolder = (PaintViewHolder) holder;
        aPaintViewHolder.bindPaint(aPaint, context);



//        aca llamas al controller y le pedis el url, si esta null lo pide a firebase,
//                y se lo setea a la paint (agregar atributo url que por defecto es null porque no esta en el json)
//        si no es null, que use ese.
    }

    @Override
    public int getItemCount() {
        return paintsList.size();
    }

    private static class PaintViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private TextView textViewArtist;
        private ImageView imageViewThumbnail;

        public PaintViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewArtist = (TextView) itemView.findViewById(R.id.textViewArtist);
            imageViewThumbnail = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
        }

        public void bindPaint(Paint aPaint, Context context){
            textViewName.setText(aPaint.getName());
            textViewArtist.setText(aPaint.getArtist());
            Picasso.with(context).load(aPaint.getLink()).into(imageViewThumbnail);

        }
    }
}
