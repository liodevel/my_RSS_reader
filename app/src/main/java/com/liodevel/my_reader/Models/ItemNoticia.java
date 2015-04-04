package com.liodevel.my_reader.Models;

import android.graphics.Bitmap;
import android.util.Log;

import com.liodevel.my_reader.Utils.RSSUtils;

import java.util.Date;


public class ItemNoticia {
    String titulo;
    String descripcion;
    Date fechaPublicacion;
    String link;
    String origen;
    String autor;
    String contenidoFormateado;
    String category;
    String icono;

    String imagenURL;
    Bitmap imagen;

    int orden;
    String id;

    boolean leida;
    boolean guardada;

    public ItemNoticia(String titulo, String descripcion, String link, String origen, Date fechaPublicacion, int orden, String id,
                       String autor, String contenidoFormateado, String category) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.link = link;
        this.origen = origen;
        this.fechaPublicacion = fechaPublicacion;
        this.autor = autor;
        this.contenidoFormateado = contenidoFormateado;
        this.orden = orden;
        this.id = id;
        this.leida = false;
        this.guardada = false;
        this.category = category;
    }

    public ItemNoticia(String titulo, String descripcion, String link) {
        Log.i("NOTICIA", "Nueva Noticia: " + titulo);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.link = link;
        this.origen = "";
        this.fechaPublicacion = new Date(0);
        this.autor = "";
        this.contenidoFormateado = "";
        this.orden = -1;
        this.id = "";
        this.leida = false;
        this.guardada = false;
        this.category = "Custom";
        this.imagenURL = "";
    }

    public ItemNoticia() {
        this.titulo = "";
        this.descripcion = "";
        this.link = "";
        this.origen = "";
        this.fechaPublicacion = new Date(0);
        this.autor = "";
        this.contenidoFormateado = "";
        this.orden = -1;
        this.id = "";
        this.leida = false;
        this.guardada = false;
        this.category = "Custom";
        this.imagenURL = "";

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        this.contenidoFormateado = RSSUtils.formatDescription(descripcion);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public String getContenidoFormateado() {
        return contenidoFormateado;
    }

    public void setContenidoFormateado(String contenidoFormateado) {
        this.contenidoFormateado = contenidoFormateado;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public boolean isGuardada() {
        return guardada;
    }

    public void setGuardada(boolean guardada) {
        this.guardada = guardada;
    }
}
