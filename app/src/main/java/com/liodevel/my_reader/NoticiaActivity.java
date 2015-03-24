package com.liodevel.my_reader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.liodevel.my_reader.Utils.StaticObjects;


public class NoticiaActivity extends Activity {

    WebView webView;
    TextView tituloNoticiaView, botonAbrir, contenidoNoticiaView, iconoView;
    String barraTituloNoticia = "";
    String linkNoticia = "";
    String icono = "";
    int idNoticia = 0;
    float density = 0;
    float x1,x2;


    // Banner Mopup
    //private static final String MOPUB_BANNER_AD_UNIT_ID = "2cc14d224b4a409ab2d25b9b130ac479";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        density = getResources().getDisplayMetrics().density;

        idNoticia = Integer.parseInt(getIntent().getExtras().getString("idNoticia"));

        // Tipografía
        Typeface tf = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_LIGHT);
        Typeface fa = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_AWESOME);

        // Action Bar
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTittle = (TextView) findViewById(titleId);
        this.setTitle(getIntent().getExtras().getString("origenNoticia"));
        actionBarTittle.setTextColor(Color.WHITE);
        actionBarTittle.setTypeface(tf);

        // Título Notícia
        barraTituloNoticia = getIntent().getExtras().getString("tituloNoticia");
        linkNoticia = getIntent().getExtras().getString("linkNoticia");
        tituloNoticiaView = (TextView) findViewById(R.id.titulo_noticia);
        tituloNoticiaView.setText(barraTituloNoticia);
        tituloNoticiaView.setTypeface(tf);

        // Icono Notícia
        icono = getIntent().getExtras().getString("icono");
        iconoView = (TextView) findViewById(R.id.category_titulo_noticia);
        iconoView.setText(icono);
        iconoView.setTypeface(fa);

        // Contenido Notícia
        String contenidoNoticia = "";
        contenidoNoticia = getIntent().getExtras().getString("contenidoFormNoticia");
        contenidoNoticiaView = (TextView) findViewById(R.id.contenido_noticia);
        contenidoNoticiaView.setText(contenidoNoticia);
        contenidoNoticiaView.setTypeface(tf);



        // Botón OPEN
        botonAbrir = (TextView) findViewById(R.id.boton_abrir);
        botonAbrir.setTextColor(Color.WHITE);
        botonAbrir.setTypeface(tf);
        botonAbrir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkNoticia));
                    startActivity(browserIntent);
                } catch (Exception e) {  }
            }
        });


        // Imagen Noticia
        String imagenNoticia = "";
        imagenNoticia = getIntent().getExtras().getString("imagen");
        Log.i("--IMAGEN--", imagenNoticia);

        webView = (WebView) findViewById(R.id.webViewNoticia);

        if (imagenNoticia.contains("png") || imagenNoticia.contains("jpg") || imagenNoticia.contains("gif") || imagenNoticia.contains("jpeg")) {
            // disable scroll on touch
            webView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return (event.getAction() == MotionEvent.ACTION_MOVE);
                }
            });
            // esconder Scrolls
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);

            // Filtro blanco y negro
            String style = "style=\"-webkit-filter: grayscale(100%);\"";

            webView.loadDataWithBaseURL("", "<img src=\"" + imagenNoticia + "\" width=\"115%\"" + style + "></img>", "text/html", "UTF-8", "");

        } else {
            webView.getLayoutParams().height = 0;
            tituloNoticiaView.bringToFront();
        }
    }


    /**
     * Controlador del Swipe para pasar de noticia
     * @param touchevent
     * @return
     */
    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            // Cuando se empieza a tocar la pantalla
            case MotionEvent.ACTION_DOWN:{
                x1 = touchevent.getX();
                break;
            }
            // Al dejar de tocar
            case MotionEvent.ACTION_UP:{
                x2 = touchevent.getX();

                // Gesto de izquierda a derecha
                if (x1 < x2 - 150){
                    if (idNoticia > 0){
                        idNoticia--;
                        reloadNewPrevious(idNoticia);
                    }
                }

                // Gesto de derecha a izquierda
                if (x1 > x2 + 150) {
                    if (idNoticia < StaticObjects.getArrayNoticias().size()){
                        idNoticia++;
                        reloadNewNext(idNoticia);
                    }
                }
                break;
            }
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_noticia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // Botón Compartir
        if (id == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Read this, \'" + barraTituloNoticia + "\' - " + linkNoticia);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    // Funciones para Animaciones de elementos

    /**
     * Recarga la vista de la siguiente noticia
     * @param id id de la noticia a cargar
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void reloadNewNext(int id){

        this.idNoticia = id;
        this.linkNoticia = StaticObjects.getArrayNoticias().get(id).getLink();
        this.barraTituloNoticia = StaticObjects.getArrayNoticias().get(id).getTitulo();

        // Animaciones
        tituloNoticiaView.animate().translationX(-1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintTitleNext(idNoticia);
                    }
                }).start();

        webView.animate().translationX(-1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintWebViewNext(idNoticia);
                    }
                }).start();

        contenidoNoticiaView.animate().translationX(-1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintContenidoNext(idNoticia);
                    }
                }).start();
        iconoView.animate().translationX(-1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintIconoNext(idNoticia);
                    }
                }).start();

    }


    /**
     * Recarga la vista de la noticia anterior
     * @param id id de la noticia a cargar
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void reloadNewPrevious(int id){

        this.idNoticia = id;
        this.linkNoticia = StaticObjects.getArrayNoticias().get(id).getLink();
        this.barraTituloNoticia = StaticObjects.getArrayNoticias().get(id).getTitulo();

        // Animaciones
        tituloNoticiaView.animate().translationX(1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintTitlePrevious(idNoticia);
                    }
                }).start();

        webView.animate().translationX(1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintWebViewPrevious(idNoticia);
                    }
                }).start();

        contenidoNoticiaView.animate().translationX(1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintContenidoPrevious(idNoticia);
                    }
                }).start();
        iconoView.animate().translationX(1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintIconoPrevious(idNoticia);
                    }
                }).start();

    }

    /**
     * Repintar título siguiente
     * @param id
     */
    void rePaintTitleNext (int id){
        this.tituloNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getTitulo());
        tituloNoticiaView.animate().translationX(1500).setDuration(0).start();
        tituloNoticiaView.animate().translationX(0).setDuration(200).start();
        this.setTitle(StaticObjects.getArrayNoticias().get(id).getOrigen());
    }

    /**
     * Repintar título anterior
     * @param id
     */
    void rePaintTitlePrevious (int id){
        this.tituloNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getTitulo());
        tituloNoticiaView.animate().translationX(-1500).setDuration(0).start();
        tituloNoticiaView.animate().translationX(0).setDuration(200).start();
        this.setTitle(StaticObjects.getArrayNoticias().get(id).getOrigen());
    }

    /**
     * Repintar imagen webView siguiente
     * @param id
     */
    void rePaintWebViewNext (int id){
        // Imagen de la noticia
        String imagenNoticia = StaticObjects.getArrayNoticias().get(id).getImagenURL();
        webView.getLayoutParams().height = Math.round(200 * density + 0.5f);
        if (imagenNoticia.contains("png") || imagenNoticia.contains("jpg") || imagenNoticia.contains("gif") || imagenNoticia.contains("jpeg")) {
            // disable scroll on touch
            webView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return (event.getAction() == MotionEvent.ACTION_MOVE);
                }
            });
            // esconder Scrolls
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);

            // Filtro blanco y negro
            //String style = "style=\"-webkit-filter: grayscale(100%);\"";
            String style = "";

            webView.loadDataWithBaseURL("", "<img src=\"" + imagenNoticia + "\" width=\"115%\"" + style + "></img>", "text/html", "UTF-8", "");

        } else {
            webView.getLayoutParams().height = 0;
            tituloNoticiaView.bringToFront();
        }
        webView.animate().translationX(1500).setDuration(0).start();
        webView.animate().translationX(0).setDuration(200).start();
    }

    /**
     * Repintar imagen webView amterios
     * @param id
     */
    void rePaintWebViewPrevious (int id){
        // Imagen de la noticia
        String imagenNoticia = StaticObjects.getArrayNoticias().get(id).getImagenURL();
        webView.getLayoutParams().height = Math.round(200 * density + 0.5f);
        if (imagenNoticia.contains("png") || imagenNoticia.contains("jpg") || imagenNoticia.contains("gif") || imagenNoticia.contains("jpeg")) {
            // disable scroll on touch
            webView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return (event.getAction() == MotionEvent.ACTION_MOVE);
                }
            });
            // esconder Scrolls
            webView.setVerticalScrollBarEnabled(false);
            webView.setHorizontalScrollBarEnabled(false);

            // Filtro blanco y negro
            //String style = "style=\"-webkit-filter: grayscale(100%);\"";
            String style = "";

            webView.loadDataWithBaseURL("", "<img src=\"" + imagenNoticia + "\" width=\"115%\"" + style + "></img>", "text/html", "UTF-8", "");

        } else {
            webView.getLayoutParams().height = 0;
            tituloNoticiaView.bringToFront();
        }
        webView.animate().translationX(-1500).setDuration(0).start();
        webView.animate().translationX(0).setDuration(200).start();
    }

    /**
     * Repintar contenido siguiente
     * @param id
     */
    void rePaintContenidoNext (int id){
        this.contenidoNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getContenidoFormateado());
        contenidoNoticiaView.animate().translationX(1500).setDuration(0).start();
        contenidoNoticiaView.animate().translationX(0).setDuration(200).start();
    }

    /**
     * Repintar contenido anterior
     * @param id
     */
    void rePaintContenidoPrevious (int id){
        this.contenidoNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getContenidoFormateado());
        contenidoNoticiaView.animate().translationX(-1500).setDuration(0).start();
        contenidoNoticiaView.animate().translationX(0).setDuration(200).start();
    }

    /**
     * Repintar icono siguiente
     * @param id
     */
    void rePaintIconoNext (int id){
        this.iconoView.setText(StaticObjects.getArrayNoticias().get(id).getIcono());
        iconoView.animate().translationX(1500).setDuration(0).start();
        iconoView.animate().translationX(0).setDuration(200).start();
    }

    /**
     * Repintar icono anterior
     * @param id
     */
    void rePaintIconoPrevious (int id){
        this.iconoView.setText(StaticObjects.getArrayNoticias().get(id).getIcono());
        iconoView.animate().translationX(-1500).setDuration(0).start();
        iconoView.animate().translationX(0).setDuration(200).start();
    }



}
