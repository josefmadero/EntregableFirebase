package com.jmadero.entregablefirebase.model;

import java.util.List;

/**
 * Created by digitalhouse on 25/07/16.
 */
public class Artist {

    private String name;
    private List<Paint> paints;

    public Artist(){
    }

    public Artist(String name, List<Paint> paints) {
        this.name = name;
        this.paints = paints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Paint> getPaints() {
        return paints;
    }

    public void setPaints(List<Paint> paints) {
        this.paints = paints;
    }
}
