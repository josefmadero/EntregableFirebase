package com.jmadero.entregablefirebase.model;

/**
 * Created by digitalhouse on 6/06/16.
 */
public class Obra {

    //Solo incluimos aca las variables del JSON que queremos leer. Solo me interesa el id, el titulo,el precio y la imagen.. Lo demás lo ignoramos.


    private String id;
    private String title;
    private Double price;
    private String thumbnail;

    public Obra(){
    }

    public Obra(String id, String title, Double price, String thumbnail) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
