package com.liodevel.my_reader;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.liodevel.my_reader.Utils.StaticObjects;


public class NoticiaActivity extends Activity {

    NetworkImageView imagenNoticiaNetworkImg;
    TextView tituloNoticiaView, botonAbrir, botonGuardar, contenidoNoticiaView, iconoView;
    RelativeLayout layoutNoticia;
    String barraTituloNoticia = "";
    String linkNoticia = "";
    String icono = "";
    int idNoticia = 0;
    float density = 0;
    float x1,x2;
    ImageLoader imageLoader;
    Context context;

    // Banner Mopup
    //private static final String MOPUB_BANNER_AD_UNIT_ID = "2cc14d224b4a409ab2d25b9b130ac479";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        context = this;


        ImageLoader.ImageCache imageCache = new BitmapLruCache();
        this.imageLoader = new ImageLoader(Volley.newRequestQueue(context), imageCache);
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

        // Layout Noticia
        layoutNoticia = (RelativeLayout) findViewById(R.id.layout_noticia);

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


        // Botón GUARDAR
        botonGuardar = (TextView) findViewById(R.id.boton_guardar);
        botonGuardar.setTextColor(Color.WHITE);
        botonGuardar.setText(getResources().getString(R.string.fa_floppy_o));
        botonGuardar.setTypeface(fa);
        botonGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    // Guardar Noticia
                    Animation rotation = AnimationUtils.loadAnimation(context, R.anim.rotate_refresh);
                    rotation.setRepeatCount(Animation.ABSOLUTE);
                    v.startAnimation(rotation);

                    StaticObjects.getArrayNoticiasGuardadas().add(StaticObjects.getArrayNoticias().get(idNoticia));
                    StaticObjects.getArrayNoticias().get(idNoticia).setGuardada(true);

                    Toast.makeText(context, getResources().getString(R.string.article_saved), Toast.LENGTH_LONG).show();

                } catch (Exception e) {  }
            }
        });


        // Botón BORRAR
        botonGuardar = (TextView) findViewById(R.id.boton_borrar);
        botonGuardar.setTextColor(Color.WHITE);
        botonGuardar.setText(getResources().getString(R.string.fa_trash_o));
        botonGuardar.setTypeface(fa);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Borrar Noticia
                    Animation rotation = AnimationUtils.loadAnimation(context, R.anim.rotate_refresh);
                    rotation.setRepeatCount(Animation.ABSOLUTE);
                    v.startAnimation(rotation);

                    StaticObjects.getArrayNoticias().remove(idNoticia);
                    if (StaticObjects.getArrayNoticias().isEmpty()){
                        finish();
                    } else if (idNoticia < StaticObjects.getArrayNoticias().size()){
                        // Enseñar siguiente
                        reloadNewNextDelete(idNoticia);
                    } else {
                        // Enseñar anterior
                        reloadNewPreviousDelete(idNoticia - 1);
                    }
                } catch (Exception e) {  }
            }
        });


        // Imagen Noticia
        String imagenNoticia = "";
        imagenNoticia = getIntent().getExtras().getString("imagen");
        Log.i("--IMAGEN--", imagenNoticia);

        imagenNoticiaNetworkImg = (NetworkImageView) findViewById(R.id.imagenNoticia);

        if (imagenNoticia.contains("png") || imagenNoticia.contains("jpg") || imagenNoticia.contains("gif") || imagenNoticia.contains("jpeg")) {

            imagenNoticiaNetworkImg.setImageUrl(imagenNoticia, imageLoader);

        } else {
            imagenNoticiaNetworkImg.getLayoutParams().height = 0;
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
                    if (idNoticia < StaticObjects.getArrayNoticias().size()-1){
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
        layoutNoticia.animate().translationX(-1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintLayoutNext(idNoticia);
                    }
                }).start();
    }


    /**
     * Recarga la vista de la siguiente noticia al eliminar
     * @param id id de la noticia a cargar
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void reloadNewNextDelete(int id){

        this.idNoticia = id;
        this.linkNoticia = StaticObjects.getArrayNoticias().get(id).getLink();
        this.barraTituloNoticia = StaticObjects.getArrayNoticias().get(id).getTitulo();

        // Animaciones
        layoutNoticia.animate().scaleX(0).scaleY(0).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintLayoutNext(idNoticia);
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
        layoutNoticia.animate().translationX(1500).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintLayoutPrevious(idNoticia);
                    }
                }).start();

    }


    /**
     * Recarga la vista de la noticia anterior
     * @param id id de la noticia a cargar
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    void reloadNewPreviousDelete(int id){

        this.idNoticia = id;
        this.linkNoticia = StaticObjects.getArrayNoticias().get(id).getLink();
        this.barraTituloNoticia = StaticObjects.getArrayNoticias().get(id).getTitulo();

        // Animaciones
        layoutNoticia.animate().scaleX(0).scaleY(0).setDuration(200).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        rePaintLayoutPrevious(idNoticia);
                    }
                }).start();

    }





    /**
     * Repintar layout siguiente
     * @param id
     */
    void rePaintLayoutNext (int id){
        this.tituloNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getTitulo());
        // Imagen de la noticia
        String imagenNoticia = StaticObjects.getArrayNoticias().get(id).getImagenURL();
        imagenNoticiaNetworkImg.getLayoutParams().height = Math.round(200 * density + 0.5f);
        if (imagenNoticia.contains("png") || imagenNoticia.contains("jpg") || imagenNoticia.contains("gif") || imagenNoticia.contains("jpeg")) {
            imagenNoticiaNetworkImg.setImageUrl(imagenNoticia, imageLoader);

        } else {
            imagenNoticiaNetworkImg.getLayoutParams().height = 0;
            tituloNoticiaView.bringToFront();
        }
        this.contenidoNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getContenidoFormateado());
        this.iconoView.setText(StaticObjects.getArrayNoticias().get(id).getIcono());

        layoutNoticia.animate().scaleX(1).scaleY(1).setDuration(0).start();
        layoutNoticia.animate().translationX(1500).setDuration(0).start();
        layoutNoticia.animate().translationX(0).setDuration(200).start();

        this.setTitle(StaticObjects.getArrayNoticias().get(id).getOrigen());
    }


    /**
     * Repintar layout anterior
     * @param id
     */
    void rePaintLayoutPrevious (int id){
        this.tituloNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getTitulo());
        // Imagen de la noticia
        String imagenNoticia = StaticObjects.getArrayNoticias().get(id).getImagenURL();
        imagenNoticiaNetworkImg.getLayoutParams().height = Math.round(200 * density + 0.5f);
        if (imagenNoticia.contains("png") || imagenNoticia.contains("jpg") || imagenNoticia.contains("gif") || imagenNoticia.contains("jpeg")) {
            imagenNoticiaNetworkImg.setImageUrl(imagenNoticia, imageLoader);

        } else {
            imagenNoticiaNetworkImg.getLayoutParams().height = 0;
            tituloNoticiaView.bringToFront();
        }
        this.contenidoNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getContenidoFormateado());
        this.iconoView.setText(StaticObjects.getArrayNoticias().get(id).getIcono());

        layoutNoticia.animate().scaleX(1).scaleY(1).setDuration(0).start();
        layoutNoticia.animate().translationX(-1500).setDuration(0).start();
        layoutNoticia.animate().translationX(0).setDuration(200).start();

        this.setTitle(StaticObjects.getArrayNoticias().get(id).getOrigen());
    }



    /**
     *
     */
    public static class BitmapLruCache
            extends LruCache<String, Bitmap>
            implements ImageLoader.ImageCache {

        public BitmapLruCache() {
            this(getDefaultLruCacheSize());
        }

        public BitmapLruCache(int sizeInKiloBytes) {
            super(sizeInKiloBytes);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight() / 1024;
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }

        public static int getDefaultLruCacheSize() {
            final int maxMemory =
                    (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int cacheSize = maxMemory / 8;

            return cacheSize;
        }
    }

}
