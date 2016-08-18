package com.jmadero.entregablefirebase.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.jmadero.entregablefirebase.R;
import com.squareup.picasso.Picasso;

public class PaintsDetailActivity extends AppCompatActivity {

    public static final String NAME = "Name";
    public static final String ARTIST = "Artist";
    public static final String LINK = "Link";

    private CallbackManager callbackManager;
    ShareButton shareButton;
    private String link;
    private String artist;
    private String name;
//    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
//        shareDialog = new ShareDialog(this);


        setContentView(R.layout.activity_paints_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        ImageView paintingImage = (ImageView) findViewById(R.id.imageView_activityDetail_painting);
        TextView artistName = (TextView) findViewById(R.id.textView_activityDetail_artistName);
        TextView paintingName = (TextView) findViewById(R.id.textView_activityDetail_paintingName);

        link = bundle.getString(LINK);
        Picasso.with(this).load(link).into(paintingImage);
        artist = bundle.getString(ARTIST);
        artistName.setText(artist);
        name = bundle.getString(NAME);
        paintingName.setText(name);


        shareButton = (ShareButton) findViewById(R.id.fb_share_button);

        configureShareButton();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void configureShareButton() {
        //Aca seteamos el contenido que queremos mostrar
//        if (ShareDialog.canShow(ShareLinkContent.class)) {
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle("MOMA")
                .setContentDescription("Artist: " + artist + " | Painting: " + name)
                .setImageUrl(Uri.parse(link))
                .build();

        //Aca asociamos el contenido que queremos mostrar al share button
//            shareDialog.show(linkContent);

            shareButton.setShareContent(linkContent);
//    }
}
}
