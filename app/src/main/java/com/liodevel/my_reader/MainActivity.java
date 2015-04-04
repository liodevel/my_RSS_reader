package com.liodevel.my_reader;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liodevel.my_reader.Adapters.ItemNoticiaAdapter;
import com.liodevel.my_reader.Models.ItemCollection;
import com.liodevel.my_reader.Models.ItemFeed;
import com.liodevel.my_reader.Models.ItemNoticia;
import com.liodevel.my_reader.Models.XMLContent;
import com.liodevel.my_reader.SwipeDismiss.SwipeDismissListViewTouchListener;
import com.liodevel.my_reader.Utils.RSSUtils;
import com.liodevel.my_reader.Utils.StaticCollections;
import com.liodevel.my_reader.Utils.StaticObjects;

import io.fabric.sdk.android.Fabric;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Actividad Principal
 */
public class MainActivity extends Activity {

    SharedPreferences prefs;

    public ArrayList<ItemNoticia> arrayNoticias = new ArrayList<>();
    public ArrayList<String> noticiasLeidas = new ArrayList<>();
    private ItemNoticiaAdapter adapter;
    ListView lista;
    ImageView iv;
    SwipeRefreshLayout swipeLayout;
    TextView textoNoNews, buttonBrowse, buttonAdd, textoNoFeeds, lastUpdate;

    XmlPullParserFactory factory;
    XmlPullParser xpp;

    Activity currentActivity;
    Intent noticiaActivity;

    Typeface tf, fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        // Recuperar prederences
        getPreferences();

        /// Inicializar XPP Parser
        try {
            factory = XmlPullParserFactory.newInstance();
            xpp = factory.newPullParser();
        } catch (Exception e) {

        }

        // Tipografia
        tf = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_LIGHT);
        fa = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_AWESOME);

        // Tipografia nombre App
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTittle = (TextView) findViewById(titleId);

        buttonBrowse = (TextView) findViewById(R.id.button_browse_categories);
        buttonAdd = (TextView) findViewById(R.id.button_add_feeds);
        textoNoFeeds = (TextView) findViewById(R.id.text_no_feeds);
        actionBarTittle.setTextColor(Color.WHITE);
        actionBarTittle.setTypeface(tf);
        buttonAdd.setTypeface(tf);
        buttonBrowse.setTypeface(tf);
        textoNoFeeds.setTypeface(tf);

        // LAST UPDATE
        lastUpdate = (TextView) findViewById(R.id.last_update);
        lastUpdate.setTypeface(tf);
        String dateString = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date(StaticObjects.getUltimaActualizacion()));
        lastUpdate.setText(getResources().getString(R.string.last_update) + dateString);

        // Texto NO NEWS
        textoNoNews = (TextView) findViewById(R.id.text_no_news);
        textoNoNews.setTypeface(tf);
        if (StaticObjects.getArrayNoticias().size() > 0) {
            textoNoNews.setVisibility(View.INVISIBLE);
        }

        // Texto y Botones NO FEEDS
        if (RSSUtils.feedsConfigured()){
            textoNoFeeds.setVisibility(View.INVISIBLE);
            buttonBrowse.setVisibility(View.INVISIBLE);
            buttonAdd.setVisibility(View.INVISIBLE);
        } else {
            buttonBrowse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentActivity.startActivity(new Intent(currentActivity, CollectionActivity.class));
                } });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentActivity.startActivity(new Intent(currentActivity, ManageFeedsActivity.class));
                } });
        }


        /// Swipe Layout, Deslizar para actualizar
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.drawer_layout);
        //swipeLayout.setColorSchemeColors(Color.);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                new GetRssFeedsTask().execute();
            }
        });

        /// Preparar Lista
        currentActivity = this;
        noticiaActivity = new Intent(currentActivity, NoticiaActivity.class);
        lista = (ListView) findViewById(R.id.lista_noticias_1);

        /// Click en elemento de la lista
        lista.setClickable(true);

        // Eliminar al desplazar elemento
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        lista,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    //Log.i("--- ADAPTER", "Position: " + position);
                                    StaticObjects.getArrayNoticias().remove(position);
                                    //adapter.remove(adapter.getItem(position));
                                }
                                adapter.notifyDataSetChanged();
                                if (StaticObjects.getArrayNoticias().size() <= 0) {
                                    textoNoNews.setVisibility(View.VISIBLE);
                                }
                            }
                        });

        // Abrir al hacer click en un elemento de la lista
        lista.setOnTouchListener(touchListener);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Log.i("LISTA", "Pulsado: " + position);

                // Preparar envio a pantalla Noticias
                noticiaActivity.putExtra("httpContent", StaticObjects.getArrayNoticias().get(position).getDescripcion());
                noticiaActivity.putExtra("tituloNoticia", StaticObjects.getArrayNoticias().get(position).getTitulo());
                noticiaActivity.putExtra("linkNoticia", StaticObjects.getArrayNoticias().get(position).getLink());
                noticiaActivity.putExtra("origenNoticia", StaticObjects.getArrayNoticias().get(position).getOrigen());
                noticiaActivity.putExtra("contenidoFormNoticia", StaticObjects.getArrayNoticias().get(position).getContenidoFormateado());
                noticiaActivity.putExtra("icono",StaticObjects.getArrayNoticias().get(position).getIcono());
                noticiaActivity.putExtra("imagen",StaticObjects.getArrayNoticias().get(position).getImagenURL());
                noticiaActivity.putExtra("fecha",StaticObjects.getArrayNoticias().get(position).getFechaPublicacion());
                noticiaActivity.putExtra("idNoticia","" + position);

                // Abrir pantalla con la noticia
                currentActivity.startActivity(noticiaActivity);

            }
        });

        // Actualizar en caso de que la lista esté arriba
        lista.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition = (lista == null || lista.getChildCount() == 0) ?
                        0 : lista.getChildAt(0).getTop();
                swipeLayout.setEnabled((topRowVerticalPosition >= 0));
            }
        });


        /// Cargar ArrayNoticias en la lista
        adapter = new ItemNoticiaAdapter(this, StaticObjects.getArrayNoticias());
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        /// Recuperar Feeds Nuevos (Si está activado en las opciones)
        if (StaticObjects.isUpdate_start()) {
            new GetRssFeedsTask().execute();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Texto y Botones NO FEEDS
        if (RSSUtils.feedsConfigured()){
            textoNoFeeds.setVisibility(View.INVISIBLE);
            buttonBrowse.setVisibility(View.INVISIBLE);
            buttonAdd.setVisibility(View.INVISIBLE);
        } else {
            textoNoFeeds.setVisibility(View.VISIBLE);
            buttonBrowse.setVisibility(View.VISIBLE);
            buttonAdd.setVisibility(View.VISIBLE);
            buttonBrowse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentActivity.startActivity(new Intent(currentActivity, CollectionActivity.class));
                } });

            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentActivity.startActivity(new Intent(currentActivity, ManageFeedsActivity.class));
                } });
        }

        /// Cargar ArrayNoticias en la lista
        adapter = new ItemNoticiaAdapter(this, StaticObjects.getArrayNoticias());
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        /// Guardar Preferences
        // Hora Actualización
        editor.putLong("ultima_actualizacion", StaticObjects.getUltimaActualizacion());
        Log.i("--- PREFS", "Noticias ultima_actualizacion: " + new Date(StaticObjects.getUltimaActualizacion()));
        // Array de Noticias
        Gson gson = new Gson();
        String jsonArrayNoticias = gson.toJson(StaticObjects.getArrayNoticias());
        editor.putString("array_noticias", jsonArrayNoticias);
        Log.i("--- PREFS", "array_noticias: " + jsonArrayNoticias);
        editor.commit();
    }

    /**
     * Actualiza ActionBar
     */
    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem savedNewsButton = menu.findItem(R.id.action_saved_news);
        if (savedNewsButton  == null) {
            savedNewsButton  = menu.add(0, R.id.action_saved_news, 0, R.string.saved_news);
        }
        TextView tv = new TextView(currentActivity);
        tv.setText(getResources().getString(R.string.fa_folder_open_o));
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(30);
        tv.setTypeface(fa);
        tv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currentActivity.startActivity(new Intent(currentActivity, NoticiasGuardadasActivity.class));
                    }
                }
        );
        savedNewsButton.setActionView(tv);

        restoreActionBar();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Click en Settings
        if (id == R.id.action_settings) {
            currentActivity.startActivity(new Intent(currentActivity, SettingsActivity.class));
        } else if (id == R.id.manage_feeds) {
            currentActivity.startActivity(new Intent(currentActivity, ManageFeedsActivity.class));
        } else if (id == R.id.add_feeds) {
            currentActivity.startActivity(new Intent(currentActivity, CollectionActivity.class));
        } else if (id == R.id.about) {
            currentActivity.startActivity(new Intent(currentActivity, AboutActivity.class));
        } else if (id == R.id.clear_news) {
            // Actualizar StaticObjects
            StaticObjects.getArrayNoticias().clear();
            // StaticObjects.setUltimaActualizacion(System.currentTimeMillis());

            /// Guardar Array de Noticias en Preferences
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPrefs.edit();

            Gson gson = new Gson();
            String jsonArrayNoticias = gson.toJson(StaticObjects.getArrayNoticias());
            editor.putString("array_noticias", jsonArrayNoticias);
            Log.i("--- PREFS", "array_noticias: " + jsonArrayNoticias);

            editor.commit();

            adapter.notifyDataSetChanged();
            textoNoNews.setVisibility(View.VISIBLE);

        }

        // Click en actualizar
        else if (item.getItemId() == R.id.action_update) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.iv = (ImageView) inflater.inflate(R.layout.iv_refresh, null);
            Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
            rotation.setRepeatCount(Animation.INFINITE);
            this.iv.startAnimation(rotation);
            item.setActionView(this.iv);

            new GetRssFeedsTask().execute();
            return true;
        }
        // Click en Noticias guardadas
        else if (item.getItemId() == R.id.action_saved_news) {
            currentActivity.startActivity(new Intent(currentActivity, NoticiasGuardadasActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Refrescar contenido Lista
     */
    public void refreshList() {
        Log.i("REFRESH_LIST", "Actualizando lista");

        // Ordenar lista
        Collections.sort(StaticObjects.getArrayNoticias(), new Comparator<ItemNoticia>() {
            @Override
            public int compare(ItemNoticia p1, ItemNoticia p2) {
                return p1.getFechaPublicacion().compareTo(p2.getFechaPublicacion());
            }
        });
        Collections.reverse(StaticObjects.getArrayNoticias());

        // Comprobar noticias leídas
        for (ItemNoticia noticia : StaticObjects.getArrayNoticias()) {
            if (RSSUtils.isReaded(noticiasLeidas, noticia.getLink())) {
                noticia.setLeida(true);
            }
        }

        // Añadir Array al Adapter de la ListView
        adapter = new ItemNoticiaAdapter(this, arrayNoticias);
        lista = (ListView) findViewById(R.id.lista_noticias_1);
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    /**
     * Recuperar contenido de un RSS en AsyncTask
     */
    private class GetRssFeedsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            Log.i("Async", "Iniciando AsyncTask");
            String feed = "";
            // CUSTOM FEEDS
            for (ItemFeed URLFeed : StaticObjects.getArrayFeeds()) {
                feed = feed.concat(getRssFeed(URLFeed.getUrl(), "Custom"));
            }

            // COLLECTION FEEDS
            for (ItemCollection URLCollection : StaticCollections.getCollectionArt()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Art"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionBooks()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Books"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionBusiness()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Business"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionCars()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Cars"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionDesign()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Design"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionFame()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Fame"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionFood()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Food"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionFunny()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Funny"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionGaming()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Gaming"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionHistories()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Histories"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionInternet()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Internet"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionMusic()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Music"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionNews()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "News"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionPhoto()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Photo"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionPolitics()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Politics"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionScience()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Science"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionSports()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Sports"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionStyle()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Style"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionTV()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "TV"));
                }
            }

            for (ItemCollection URLCollection : StaticCollections.getCollectionTechnology()) {
                if (URLCollection.isAdded()) {
                    feed = feed.concat(getRssFeed(URLCollection.getUrl(), "Technology"));
                }
            }

            //Log.i("-----FEED", feed);
            return feed;
        }

        @Override
        protected void onPostExecute(String dummy) {

            arrayNoticias = new ArrayList<>();

            XmlPullParserFactory factory = null;
            for (XMLContent URLFeed : StaticObjects.getContenidoFeeds()) {

                try {
                    factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = null;
                    xpp = factory.newPullParser();
                    StringReader entrada = new StringReader(URLFeed.getContent());

                    xpp.setInput(entrada);

                    arrayNoticias = RSSUtils.readRss(xpp, URLFeed.getCategory());

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    prefs.edit().putLong("ultima_actualizacion", StaticObjects.getUltimaActualizacion());
                    Log.i("--- PREFS", "Hora guardada: " + new Date(StaticObjects.getUltimaActualizacion()));
                }
                //Log.i("ON_POST", rssFeed);
            }

            if (StaticObjects.isActualizarHora()) {
                StaticObjects.setUltimaActualizacion(System.currentTimeMillis());
                String dateString = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date(StaticObjects.getUltimaActualizacion()));
                lastUpdate.setText(getResources().getString(R.string.last_update) + dateString);
            }
            Log.i("---ACTUALIZACION", "HORA: " + new Date(StaticObjects.getUltimaActualizacion()));

            if (StaticObjects.getArrayNoticias() != null) {
                refreshList();
            }
            if (iv != null) {
                iv.clearAnimation();
            }
            invalidateOptionsMenu();
            swipeLayout.setRefreshing(false);
            if (StaticObjects.getArrayNoticias().size() > 0) {
                textoNoNews.setVisibility(View.INVISIBLE);
            }
        }
    }




    /**
     * Descargar XML de un Feed
     *
     * @return
     */
    String getRssFeed(String URLFeed, String category) {
        InputStream in = null;
        String rssFeed = "";

        try {

            Log.i("--RSS", "FETCH: " + URLFeed);
            URL url = new URL(URLFeed);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];

            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }

            byte[] response = out.toByteArray();
            rssFeed = new String(response, "UTF-8");
            StaticObjects.getContenidoFeeds().add(new XMLContent(rssFeed, category));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Log.i("--RSS", "\n\n" + rssFeed.substring(1,500));
        return (rssFeed);

    }

    /**
     * Guarda las preferencias en la StaticsObjects
     */
    public void getPreferences() {

        Set<String> defaultSet = new Set<String>() {
            @Override
            public boolean add(String object) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> collection) {
                return false;
            }

            @Override
            public void clear() {
            }

            @Override
            public boolean contains(Object object) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @NonNull
            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public boolean remove(Object object) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(T[] array) {
                return null;
            }
        };


        // Cargar preferencias
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // OPCIONES
        // Delete Readed
        StaticObjects.setDelete_readed(this.prefs.getBoolean("delete_readed", false));
        Log.i("--PREFS", "delete_readed = " + StaticObjects.isDelete_readed());
        // Update Start
        StaticObjects.setUpdate_start(this.prefs.getBoolean("update_start", false));
        Log.i("--PREFS", "update_start = " + StaticObjects.isDelete_readed());


        // Última Actualización
        StaticObjects.setUltimaActualizacion(this.prefs.getLong("ultima_actualizacion", 0));
        Log.i("--PREFS", "ultima_actualizacion = " + StaticObjects.getUltimaActualizacion());

        // Array de Noticias
        Gson gsonNoticias = new Gson();
        String jsonNoticias = this.prefs.getString("array_noticias", "");
        Type typeNoticias = new TypeToken<ArrayList<ItemNoticia>>() {
        }.getType();
        ArrayList<ItemNoticia> arrayListNoticias = gsonNoticias.fromJson(jsonNoticias, typeNoticias);
        if (arrayListNoticias != null) {
            StaticObjects.setArrayNoticias(arrayListNoticias);
        } else {
            StaticObjects.setArrayNoticias(new ArrayList<ItemNoticia>());
        }
        Log.i("--PREFS", "array_noticias = " + StaticObjects.getArrayNoticias());

        // Array de Noticias Guardadas
        Gson gsonNoticiasGuardadas = new Gson();
        String jsonNoticiasGuardadas = this.prefs.getString("array_noticias_guardadas", "");
        Type typeNoticiasGuardadas = new TypeToken<ArrayList<ItemNoticia>>() {
        }.getType();
        ArrayList<ItemNoticia> arrayListNoticiasGuardadas = gsonNoticiasGuardadas.fromJson(jsonNoticiasGuardadas, typeNoticiasGuardadas);
        if (arrayListNoticiasGuardadas != null) {
            StaticObjects.setArrayNoticiasGuardadas(arrayListNoticiasGuardadas);
        } else {
            StaticObjects.setArrayNoticiasGuardadas(new ArrayList<ItemNoticia>());
        }
        Log.i("--PREFS", "array_noticias_guardadas = " + StaticObjects.getArrayNoticiasGuardadas());


        // Array de Feeds
        Gson gsonFeeds = new Gson();
        String jsonFeeds = this.prefs.getString("array_feeds", "");
        Type typeFeeds = new TypeToken<ArrayList<ItemFeed>>() {
        }.getType();
        ArrayList<ItemFeed> arrayListFeeds = gsonFeeds.fromJson(jsonFeeds, typeFeeds);
        if (arrayListFeeds != null) {
            StaticObjects.setArrayFeeds(arrayListFeeds);
        } else {
            StaticObjects.setArrayFeeds(new ArrayList<ItemFeed>());
        }
        Log.i("--PREFS", "array_feeds = " + StaticObjects.getArrayFeeds());

        // Arrays de Collections
        // Art
        Gson gsonArt = new Gson();
        String jsonArt = this.prefs.getString("array_art", "");
        Type typeArt = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListArt = gsonArt.fromJson(jsonArt, typeArt);

        if (arrayListArt != null && arrayListArt.size() > 0) {
            StaticCollections.setCollectionArt(arrayListArt);
        } else {
            StaticCollections.updateCollectionArt();
        }

        // Books
        Gson gsonBooks = new Gson();
        String jsonBooks = this.prefs.getString("array_books", "");
        Type typeBooks = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListBooks = gsonBooks.fromJson(jsonBooks, typeBooks);

        if (arrayListBooks != null && arrayListBooks.size() > 0) {
            StaticCollections.setCollectionBooks(arrayListBooks);
        } else {
            StaticCollections.updateCollectionBooks();
        }

        // Gaming
        Gson gsonBusiness = new Gson();
        String jsonBusiness = this.prefs.getString("array_business", "");
        Type typeBusiness = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListBusiness = gsonBusiness.fromJson(jsonBusiness, typeBusiness);

        if (arrayListBusiness != null && arrayListBusiness.size() > 0) {
            StaticCollections.setCollectionBusiness(arrayListBusiness);
        } else {
            StaticCollections.updateCollectionBusiness();
        }

        // Cars
        Gson gsonCars = new Gson();
        String jsonCars = this.prefs.getString("array_cars", "");
        Type typeCars = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListCars = gsonCars.fromJson(jsonCars, typeCars);

        if (arrayListCars != null && arrayListCars.size() > 0) {
            StaticCollections.setCollectionCars(arrayListCars);
        } else {
            StaticCollections.updateCollectionCars();
        }


        // Design
        Gson gsonDesign = new Gson();
        String jsonDesign = this.prefs.getString("array_design", "");
        Type typeDesign = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListDesign = gsonDesign.fromJson(jsonDesign, typeDesign);

        if (arrayListDesign != null && arrayListDesign.size() > 0) {
            StaticCollections.setCollectionDesign(arrayListDesign);
        } else {
            StaticCollections.updateCollectionDesign();
        }


        // Fame
        Gson gsonFame = new Gson();
        String jsonFame = this.prefs.getString("array_fame", "");
        Type typeFame = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListFame = gsonFame.fromJson(jsonFame, typeFame);

        if (arrayListFame != null && arrayListFame.size() > 0) {
            StaticCollections.setCollectionFame(arrayListFame);
        } else {
            StaticCollections.updateCollectionFame();
        }


        // Food
        Gson gsonFood = new Gson();
        String jsonFood = this.prefs.getString("array_food", "");
        Type typeFood = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListFood = gsonFood.fromJson(jsonFood, typeFood);

        if (arrayListFood != null && arrayListFood.size() > 0) {
            StaticCollections.setCollectionFood(arrayListFood);
        } else {
            StaticCollections.updateCollectionFood();
        }


        // Funny
        Gson gsonFunny = new Gson();
        String jsonFunny = this.prefs.getString("array_funny", "");
        Type typeFunny = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListFunny = gsonFunny.fromJson(jsonFunny, typeFunny);

        if (arrayListFunny != null && arrayListFunny.size() > 0) {
            StaticCollections.setCollectionFunny(arrayListFunny);
        } else {
            StaticCollections.updateCollectionFunny();
        }

        // Gaming
        Gson gsonGaming = new Gson();
        String jsonGaming = this.prefs.getString("array_gaming", "");
        Type typeGaming = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListGaming = gsonGaming.fromJson(jsonGaming, typeGaming);

        if (arrayListGaming != null && arrayListGaming.size() > 0) {
            StaticCollections.setCollectionGaming(arrayListGaming);
        } else {
            StaticCollections.updateCollectionGaming();
        }


        // Histories
        Gson gsonHistories = new Gson();
        String jsonHistories = this.prefs.getString("array_histories", "");
        Type typeHistories = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListHistories = gsonHistories.fromJson(jsonHistories, typeHistories);

        if (arrayListHistories != null && arrayListHistories.size() > 0) {
            StaticCollections.setCollectionHistories(arrayListHistories);
        } else {
            StaticCollections.updateCollectionHistories();
        }


        // Internet
        Gson gsonInternet = new Gson();
        String jsonInternet = this.prefs.getString("array_internet", "");
        Type typeInternet = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListInternet = gsonInternet.fromJson(jsonInternet, typeInternet);

        if (arrayListInternet != null && arrayListInternet.size() > 0) {
            StaticCollections.setCollectionInternet(arrayListInternet);
        } else {
            StaticCollections.updateCollectionInternet();
        }


        // Music
        Gson gsonMusic = new Gson();
        String jsonMusic = this.prefs.getString("array_music", "");
        Type typeMusic = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListMusic = gsonMusic.fromJson(jsonMusic, typeMusic);

        if (arrayListMusic != null && arrayListMusic.size() > 0) {
            StaticCollections.setCollectionMusic(arrayListMusic);
        } else {
            StaticCollections.updateCollectionMusic();
        }


        // News
        Gson gsonNews = new Gson();
        String jsonNews = this.prefs.getString("array_news", "");
        Type typeNews = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListNews = gsonNews.fromJson(jsonNews, typeNews);

        if (arrayListNews != null && arrayListNews.size() > 0) {
            StaticCollections.setCollectionNews(arrayListNews);
        } else {
            StaticCollections.updateCollectionNews();
        }


        // Photo
        Gson gsonPhoto = new Gson();
        String jsonPhoto = this.prefs.getString("array_photo", "");
        Type typePhoto = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListPhoto = gsonPhoto.fromJson(jsonPhoto, typePhoto);

        if (arrayListPhoto != null && arrayListPhoto.size() > 0) {
            StaticCollections.setCollectionPhoto(arrayListPhoto);
        } else {
            StaticCollections.updateCollectionPhoto();
        }


        // Politics
        Gson gsonPolitics = new Gson();
        String jsonPolitics = this.prefs.getString("array_politics", "");
        Type typePolitics = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListPolitics = gsonPolitics.fromJson(jsonPolitics, typePolitics);

        if (arrayListPolitics != null && arrayListPolitics.size() > 0) {
            StaticCollections.setCollectionPolitics(arrayListPolitics);
        } else {
            StaticCollections.updateCollectionPolitics();
        }


        // Science
        Gson gsonScience = new Gson();
        String jsonScience = this.prefs.getString("array_science", "");
        Type typeScience = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListScience = gsonScience.fromJson(jsonScience, typeScience);

        if (arrayListScience != null && arrayListScience.size() > 0) {
            StaticCollections.setCollectionScience(arrayListScience);
        } else {
            StaticCollections.updateCollectionScience();
        }

        // Sports
        Gson gsonSports = new Gson();
        String jsonSports = this.prefs.getString("array_sports", "");
        Type typeSports = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListSports = gsonSports.fromJson(jsonSports, typeSports);

        if (arrayListSports != null && arrayListSports.size() > 0) {
            StaticCollections.setCollectionSports(arrayListSports);
        } else {
            StaticCollections.updateCollectionSports();
        }


        // Style
        Gson gsonStyle = new Gson();
        String jsonStyle = this.prefs.getString("array_style", "");
        Type typeStyle = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListStyle = gsonStyle.fromJson(jsonStyle, typeStyle);

        if (arrayListStyle != null && arrayListStyle.size() > 0) {
            StaticCollections.setCollectionStyle(arrayListStyle);
        } else {
            StaticCollections.updateCollectionStyle();
        }


        // TV
        Gson gsonTV = new Gson();
        String jsonTV = this.prefs.getString("array_tv", "");
        Type typeTV = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListTV = gsonTV.fromJson(jsonTV, typeTV);

        if (arrayListTV != null && arrayListTV.size() > 0) {
            StaticCollections.setCollectionTV(arrayListTV);
        } else {
            StaticCollections.updateCollectionTV();
        }


        // Technology
        Gson gsonTechnology = new Gson();
        String jsonTechnology = this.prefs.getString("array_technology", "");
        Type typeTechnology = new TypeToken<ArrayList<ItemCollection>>() {
        }.getType();
        ArrayList<ItemCollection> arrayListTechnology = gsonTechnology.fromJson(jsonTechnology, typeTechnology);

        if (arrayListTechnology != null && arrayListTechnology.size() > 0) {
            StaticCollections.setCollectionTechnology(arrayListTechnology);
        } else {
            StaticCollections.updateCollectionTechnology();
        }

    }


}

