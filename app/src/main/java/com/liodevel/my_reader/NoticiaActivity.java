package com.liodevel.my_reader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    private static final String MOPUB_BANNER_AD_UNIT_ID = "2cc14d224b4a409ab2d25b9b130ac479";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        // Tipografía
        Typeface tf = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_LIGHT);
        Typeface fa = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_AWESOME);

        // BARRA Título
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

        // Texto OPEN
        botonAbrir = (TextView) findViewById(R.id.boton_abrir);
        botonAbrir.setTextColor(Color.WHITE);
        botonAbrir.setTypeface(tf);
        botonAbrir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getExtras().getString("linkNoticia")));
                    startActivity(browserIntent);

                } catch (Exception e) {

                }
            }

        });

        // WebView
        /*
        webView = (WebView) findViewById(R.id.webViewNoticia);
        webView.getSettings().setJavaScriptEnabled(true);
        String httpContent = "Error";
        httpContent = getIntent().getExtras().getString("httpContent");
        webView.loadDataWithBaseURL("", httpContent, "text/html", "UTF-8", "");
        */
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
}
