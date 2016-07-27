package com.jmadero.entregablefirebase.model;

import java.util.List;

/**
 * Created by digitalhouse on 25/07/16.
 */
public class ArtistContainer {

    private List<Artist> artists;

    public ArtistContainer(){
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
