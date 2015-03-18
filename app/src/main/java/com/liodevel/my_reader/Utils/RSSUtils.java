package com.liodevel.my_reader.Utils;

import android.util.Log;

import com.liodevel.my_reader.Models.ItemCollection;
import com.liodevel.my_reader.Models.ItemNoticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Utilidades RSS
 * Created by emilio on 17/02/15.
 */
public class RSSUtils {


    /**
     * Convierte el texto del los Feeds capturados a un ArrayList de ItemNoticia
     *
     * @param parser
     * @return ArrayList de ItemNoticia con los Feeds
     * @throws org.xmlpull.v1.XmlPullParserException
     * @throws java.io.IOException
     */
    public static ArrayList<ItemNoticia> readRss(XmlPullParser parser, String category) throws XmlPullParserException, IOException {

        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, "", "rss");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("channel")) {
                if (StaticObjects.getArrayNoticias() != null) {
                    StaticObjects.getArrayNoticias().addAll(readChannel(parser, category));
                } else {
                    StaticObjects.arrayNoticias = new ArrayList<>();
                    StaticObjects.getArrayNoticias().addAll(readChannel(parser, category));
                }
            } else {
                skip(parser);
            }
        }
        return StaticObjects.getArrayNoticias();
    }


    /**
     * Lee un canal de Feeds y lo retorna en un ArrayList
     *
     * @param parser
     * @return ArrayList con ItemNoticia
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static ArrayList<ItemNoticia> readChannel(XmlPullParser parser, String category) throws IOException, XmlPullParserException {

        ArrayList<ItemNoticia> items = new ArrayList<>();
        boolean actualizarHora = false;
        parser.require(XmlPullParser.START_TAG, "", "channel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item")) {
                //Log.i("RSS_ITEM", "Item encontrado");
                ItemNoticia feed = readItem(parser, category);
                if (!StaticObjects.existFeed(feed)) {
                    if (feed.getFechaPublicacion().getTime() > StaticObjects.getUltimaActualizacion()) {
                        items.add(feed);
                        StaticObjects.actualizarHora = true;
                    }
                }
            } else {
                skip(parser);
            }
        }

        return items;
    }


    /**
     * Lee un Item del Feed
     *
     * @param parser
     * @return ItemNoticia con el Item leído
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static ItemNoticia readItem(XmlPullParser parser, String category) throws XmlPullParserException, IOException {
        ItemNoticia noticia = new ItemNoticia();

        parser.require(XmlPullParser.START_TAG, "", "item");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                //Log.i("RSS_ITEM", "Título encontrado");
                noticia.setTitulo(readTitle(parser));
            } else if (name.equals("description")) {
                //Log.i("RSS_ITEM", "Desc encontrado");
                noticia.setDescripcion(readDesc(parser));
            } else if (name.equals("pubDate")) {
                //Log.i("RSS_ITEM", "Fecha encontrada");
                noticia.setFechaPublicacion(readDate(parser));
            } else if (name.equals("link")) {
                //Log.i("RSS_ITEM", "Link encontrado");
                noticia.setLink(readLink(parser));
            } else if (name.equals("author")) {
                //Log.i("RSS_ITEM", "Autor encontrado");
                noticia.setAutor(readAuthor(parser));
            } else if (name.equals("image")) {
                //Log.i("RSS_ITEM", "Imagen encontrada");

            } else if (name.equals("guid")) {
                Log.i("RSS_ITEM", "GUID encontrado");
                noticia.setId(readGuid(parser));
                noticia.setOrigen(getOriginFromGuid(noticia.getId()));
                Log.i("RSS_ITEM", "GUID encontrado: " + noticia.getId());
            } else {
                skip(parser);
            }

            if (noticia.getTitulo().equals("")) {
                noticia.setTitulo("No title");
            }
            if (noticia.getDescripcion().equals("")) {
                noticia.setDescripcion("No description");
            }

            noticia.setCategory(category);
        }
        return noticia;
    }



    /**
     * Leer Título
     *
     * @param parser
     * @return Título de la notícia
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, "", "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, "", "title");
        Log.i("----TITLE", title);
        return title;
    }


    /**
     * Leer descripción
     *
     * @param parser
     * @return Descripción del Feed
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static String readDesc(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, "", "description");
        String desc = readText(parser);
        parser.require(XmlPullParser.END_TAG, "", "description");
        return desc;
    }

    /**
     * Leer la Fecha del Feed
     *
     * @param parser
     * @return Fecha del Feed
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static Date readDate(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, "", "pubDate");
        String fechaString = readText(parser);
        Log.i("--FECHA", fechaString);
        Date date = stringToDate(fechaString);
        parser.require(XmlPullParser.END_TAG, "", "pubDate");
        return date;
    }

    /**
     * Leer Link del Feed
     *
     * @param parser
     * @return Link del Feed
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, "", "link");
        String link = readText(parser);
        parser.require(XmlPullParser.END_TAG, "", "link");
        return link;
    }

    /**
     * Leer Autor del Feed
     *
     * @param parser
     * @return Autor del Feed
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static String readAuthor(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, "", "author");
        String author = readText(parser);
        parser.require(XmlPullParser.END_TAG, "", "author");
        return author;
    }

    /**
     * Leer GUID del Feed
     *
     * @param parser
     * @return GUID del Feed
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static String readGuid(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, "", "guid");
        String guid = readText(parser);
        parser.require(XmlPullParser.END_TAG, "", "guid");
        return guid;
    }

    /**
     * Leer texto genérico del Feed
     *
     * @param parser
     * @return Texto leído
     * @throws IOException
     * @throws XmlPullParserException
     */
    public static String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }


    /**
     * Saltar etiqueta XML, pasa a la siguiente etiqueta
     *
     * @param parser
     * @throws XmlPullParserException
     * @throws IOException
     */
    public static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {

        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


    /**
     * Comprueba si un Feed ha sido leído
     *
     * @param lista
     * @param link
     * @return True si el Feed ha sido leído
     */
    public static boolean isReaded(ArrayList<String> lista, String link) {
        boolean ret = false;
        int i = 0;
        for (String linkLista : lista) {
            if (linkLista.equals(link)) {
                //Log.i("--LISTA", "Elemento leido: " + i);
                ret = true;
                break;
            }
            i++;
        }

        return ret;
    }

    /**
     * Convierte una fecha capturada de un Feed a formato Fecha
     *
     * @param stringDate
     * @return Fecha en formato correcto
     */
    public static Date stringToDate(String stringDate) {

        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        Date date;

        try {

            date = formatter.parse(stringDate);
            System.out.println("-1: " + date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            Log.w("--FECHA", "Error Fecha: " + stringDate);

            formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
            date = new Date(0);

            try {
                date = formatter.parse(stringDate);
                System.out.println("-2: " + date);
                System.out.println(formatter.format(date));

            } catch (ParseException ee) {
                ee.printStackTrace();
                Log.w("--FECHA", "Error Fecha: " + stringDate);
            }
        }

        return date;
    }


    /**
     * Recupera el dominio web del GUID
     *
     * @param guid
     * @return Dominio Web del origen del Feed
     */
    public static String getOriginFromGuid(String guid) {
        String ret = "";

        try {
            ret = guid.split("/")[2];
        } catch (Exception e) {
            ret = "";
        }

        return ret;
    }


    public static String formatDescription(String description) {
        String descFormatted = "";

        descFormatted = description.replaceAll("\\<.*?>", "");

        return descFormatted;

    }

    public static boolean feedsConfigured(){
        boolean ret = false;

        if (StaticObjects.getArrayFeeds().size() >0){
            ret = true;
        }

        for (ItemCollection item:StaticCollections.getCollectionArt()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionBooks()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionBusiness()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionCars()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionDesign()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionFame()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionFood()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionFunny()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionGaming()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionHistories()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionInternet()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionMusic()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionNews()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionPhoto()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionPolitics()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionScience()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionSports()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionStyle()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionTV()){ if (item.isAdded()){ret = true;}}
        for (ItemCollection item:StaticCollections.getCollectionTechnology()){ if (item.isAdded()){ret = true;}}


        return ret;
    }

}
