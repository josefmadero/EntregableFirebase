package com.jmadero.entregablefirebase.model;

import android.provider.MediaStore;

/**
 * Created by digitalhouse on 6/06/16.
 */
public class Paint {


    private String image;
    private String name;
    private String artist;
    private String link;


    public Paint(){
    }

    public Paint(String image, String name, String artist, String link) {
        this.image = image;
        this.name = name;
        this.artist = artist;
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
