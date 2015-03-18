package com.liodevel.my_reader.Models;

public class ItemFeed {
    String nombre;
    String url;
    String color;

    int id;
    boolean activada;


    public ItemFeed(String nombre, String url, String color, int id, boolean activada) {
        this.nombre = nombre;
        this.url = url;
        this.color = color;
        this.id = id;
        this.activada = activada;
    }

    public ItemFeed(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
        this.color = "";
        this.id = -1;
        this.activada = true;
    }

    public ItemFeed() {
        this.nombre = "";
        this.url = "";
        this.color = "";
        this.id = -1;
        this.activada = true;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivada() {
        return activada;
    }

    public void setActivada(boolean activada) {
        this.activada = activada;
    }
}
