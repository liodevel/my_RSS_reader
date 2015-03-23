package com.liodevel.my_reader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

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


    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();

                //if left to right sweep event on screen
                if (x1 < x2)
                {
                    Toast.makeText(this, getResources().getString(R.string.previous_new), Toast.LENGTH_LONG).show();
                    if (idNoticia > 0){
                        idNoticia--;
                        reloadNew(idNoticia);
                    }

                }

                // if right to left sweep event on screen
                if (x1 > x2)
                {
                    Toast.makeText(this, getResources().getString(R.string.next_new), Toast.LENGTH_LONG).show();
                    if (idNoticia < StaticObjects.getArrayNoticias().size()){
                        idNoticia++;
                        reloadNew(idNoticia);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

    void reloadNew(int id){
        this.idNoticia = id;
        this.tituloNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getTitulo());
        this.contenidoNoticiaView.setText(StaticObjects.getArrayNoticias().get(id).getContenidoFormateado());
        this.linkNoticia = StaticObjects.getArrayNoticias().get(id).getLink();

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
            String style = "style=\"-webkit-filter: grayscale(100%);\"";

            webView.loadDataWithBaseURL("", "<img src=\"" + imagenNoticia + "\" width=\"115%\"" + style + "></img>", "text/html", "UTF-8", "");

        } else {
            webView.getLayoutParams().height = 0;
            tituloNoticiaView.bringToFront();
        }




    }


}
