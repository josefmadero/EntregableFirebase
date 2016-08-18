package com.jmadero.entregablefirebase.view;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.jmadero.entregablefirebase.R;
import com.jmadero.entregablefirebase.model.Paint;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres on 22/06/16.
 */
public class PaintsAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private List<Paint> paintsList;
    private Context context;

    private View.OnClickListener listener;

//    FirebaseStorage storage;
//    StorageReference storageRef;


    public PaintsAdapter(Context context, List<Paint> paintsList) {

        this.context = context;
        this.paintsList = new ArrayList<>();
    }

    public void setPaintsList(List<Paint> paintsList) {
        this.paintsList = paintsList;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_detalle, parent, false);
        PaintViewHolder unTitularViewHolder = new PaintViewHolder(itemView);
        itemView.setOnClickListener(this);
        return unTitularViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Aca obtengo mi instancia del Storage en FireBase
//        storage = FirebaseStorage.getInstance();
//        //Aca obtengo mi referencia al Storage en FireBase, seria como pararme dentro del la carpeta raiz
//        storageRef = storage.getReferenceFromUrl("gs://entregablefirebase-50c58.appspot.com");
        Paint aPaint = paintsList.get(position);
//        if(aPaint.getLink() == null){
//            storageRef.child(aPaint.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    // Got the download URL
//                    aPaint.setLink(uri.toString());
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    // Handle any errors
//                }
//            });
//
//        }
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

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }



    private static class PaintViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewArtist;
        private ImageView imageViewThumbnail;
//        private LinearLayout shareButtonLinear;
//        private ShareButton shareButton;

        public PaintViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewArtist = (TextView) itemView.findViewById(R.id.textViewArtist);
            imageViewThumbnail = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
//            shareButtonLinear = (LinearLayout) itemView.findViewById(R.id.layout_RecyclerView_detalle_shareButton);
        }

        public void bindPaint(Paint aPaint, Context context) {
            Picasso.with(context).load(aPaint.getLink()).into(imageViewThumbnail);
            textViewName.setText(aPaint.getName());
            textViewArtist.setText(aPaint.getArtist());
//            configureShareButton(aPaint);


        }

//        public void configureShareButton(Paint aPaint) {
//            //Aca seteamos el contenido que queremos mostrar
//            ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                    .setContentTitle(aPaint.getName())
//                    .setContentDescription("AUTHOR: " + aPaint.getArtist())
//                    .setContentUrl(Uri.parse("http://www.moma.org/"))
//                    .setImageUrl(Uri.parse(aPaint.getLink()))
//                    .build();
//
//            //Aca asociamos el contenido que queremos mostrar al share button
//            shareButton.setShareContent(linkContent);
    }





    }

