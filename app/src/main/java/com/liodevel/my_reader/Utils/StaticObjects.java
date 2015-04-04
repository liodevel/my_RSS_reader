package com.liodevel.my_reader.Utils;

import com.liodevel.my_reader.Models.ItemFeed;
import com.liodevel.my_reader.Models.ItemNoticia;
import com.liodevel.my_reader.Models.XMLContent;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by emilio on 21/02/2015.
 */
public class StaticObjects {

    //static ArrayList<String> listaFeeds = new ArrayList<>();

    static ArrayList<XMLContent> contenidoFeeds = new ArrayList<>();

    static ArrayList<ItemNoticia> arrayNoticias = new ArrayList<>();
    static ArrayList<ItemFeed> arrayFeeds = new ArrayList<>();
    static ArrayList<ItemNoticia> arrayNoticiasGuardadas = new ArrayList<>();

    static boolean delete_readed = false;
    static boolean update_start = false;

    public static Set<String> setFeeds;

    static long ultimaActualizacion = 0;
    static boolean actualizarHora = false;

    public static final String FONT_LIGHT = "fonts/Raleway-Light.ttf";
    public static final String FONT_AWESOME = "fonts/fontawesome-webfont.ttf";

    public static ArrayList<XMLContent> contenidosXML = new ArrayList<>();


/*
    public static ArrayList<String> getListaFeeds() {
        return listaFeeds;
    }

    public static void setListaFeeds(ArrayList<String> listaFeeds) {
        StaticObjects.listaFeeds = listaFeeds;
    }
*/

    public static boolean isDelete_readed() {
        return delete_readed;
    }

    public static void setDelete_readed(boolean delete_readed) {
        StaticObjects.delete_readed = delete_readed;
    }

    public static ArrayList<XMLContent> getContenidoFeeds() {
        return contenidoFeeds;
    }

    public static void setContenidoFeeds(ArrayList<XMLContent> contenidoFeeds) {
        StaticObjects.contenidoFeeds = contenidoFeeds;
    }

    public static Set<String> getSetFeeds() {
        return setFeeds;
    }

    public static void setSetFeeds(Set<String> setFeeds) {
        StaticObjects.setFeeds = setFeeds;
    }

    public static ArrayList<ItemNoticia> getArrayNoticias() {
        return arrayNoticias;
    }

    public static void setArrayNoticias(ArrayList<ItemNoticia> arrayNoticias) {
        StaticObjects.arrayNoticias = arrayNoticias;
    }



    public static boolean existFeed(ItemNoticia feedEnt) {
        boolean ret = false;
        if (arrayNoticias != null) {
            for (ItemNoticia feed : arrayNoticias) {
                if (feedEnt.getId().equals(feed.getId())) {
                    ret = true;
                    break;
                }
            }
        }

        return ret;
    }

    public static long getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public static void setUltimaActualizacion(long ultimaActualizacion) {
        StaticObjects.ultimaActualizacion = ultimaActualizacion;
    }

    public static ArrayList<ItemFeed> getArrayFeeds() {
        return arrayFeeds;
    }

    public static void setArrayFeeds(ArrayList<ItemFeed> arrayFeeds) {
        StaticObjects.arrayFeeds = arrayFeeds;
    }

    public static boolean isActualizarHora() {
        return actualizarHora;
    }

    public static void setActualizarHora(boolean actualizarHora) {
        StaticObjects.actualizarHora = actualizarHora;
    }

    public static ArrayList<XMLContent> getContenidosXML() {
        return contenidosXML;
    }

    public static void setContenidosXML(ArrayList<XMLContent> contenidosXML) {
        StaticObjects.contenidosXML = contenidosXML;
    }

    public static boolean isUpdate_start() {
        return update_start;
    }

    public static void setUpdate_start(boolean update_start) {
        StaticObjects.update_start = update_start;
    }

    public static ArrayList<ItemNoticia> getArrayNoticiasGuardadas() {
        return arrayNoticiasGuardadas;
    }

    public static void setArrayNoticiasGuardadas(ArrayList<ItemNoticia> arrayNoticiasGuardadas) {
        StaticObjects.arrayNoticiasGuardadas = arrayNoticiasGuardadas;
    }
}

