package com.liodevel.my_reader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.liodevel.my_reader.Adapters.ItemNoticiaGuardadaAdapter;
import com.liodevel.my_reader.SwipeDismiss.SwipeDismissListViewTouchListener;
import com.liodevel.my_reader.Utils.StaticObjects;

/**
 * Actividad Principal
 */
public class NoticiasGuardadasActivity extends Activity {

    private ItemNoticiaGuardadaAdapter adapter;
    ListView lista;
    Activity currentActivity;
    Intent noticiaGuardadaActivity;

    Typeface tf;
    SwipeRefreshLayout swipeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_guardadas);

        // Tipografia
        tf = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_LIGHT);

        // Tipografia nombre App
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTittle = (TextView) findViewById(titleId);
        actionBarTittle.setText(getResources().getString(R.string.saved_news));
        actionBarTittle.setTextColor(Color.WHITE);
        actionBarTittle.setTypeface(tf);

        /// Preparar Lista
        currentActivity = this;
        noticiaGuardadaActivity = new Intent(currentActivity, NoticiaGuardadaActivity.class);
        lista = (ListView) findViewById(R.id.lista_noticias_guardadas);

        /// Click en elemento de la lista
        lista.setClickable(true);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.drawer_layout_guardadas);
        swipeLayout.setEnabled(false);

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
                                    StaticObjects.getArrayNoticiasGuardadas().remove(position);
                                    //adapter.remove(adapter.getItem(position));
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

        // Abrir al hacer click en un elemento de la lista
        lista.setOnTouchListener(touchListener);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Log.i("LISTA", "Pulsado: " + position);

                // Preparar envio a pantalla Noticia Guardada
                noticiaGuardadaActivity.putExtra("httpContent", StaticObjects.getArrayNoticiasGuardadas().get(position).getDescripcion());
                noticiaGuardadaActivity.putExtra("tituloNoticia", StaticObjects.getArrayNoticiasGuardadas().get(position).getTitulo());
                noticiaGuardadaActivity.putExtra("linkNoticia", StaticObjects.getArrayNoticiasGuardadas().get(position).getLink());
                noticiaGuardadaActivity.putExtra("origenNoticia", StaticObjects.getArrayNoticiasGuardadas().get(position).getOrigen());
                noticiaGuardadaActivity.putExtra("contenidoFormNoticia", StaticObjects.getArrayNoticiasGuardadas().get(position).getContenidoFormateado());
                noticiaGuardadaActivity.putExtra("icono",StaticObjects.getArrayNoticiasGuardadas().get(position).getIcono());
                noticiaGuardadaActivity.putExtra("imagen",StaticObjects.getArrayNoticiasGuardadas().get(position).getImagenURL());
                noticiaGuardadaActivity.putExtra("idNoticia","" + position);

                // Abrir pantalla con la noticia
                currentActivity.startActivity(noticiaGuardadaActivity);
            }
        });



        /// Cargar ArrayNoticias en la lista
        adapter = new ItemNoticiaGuardadaAdapter(this, StaticObjects.getArrayNoticiasGuardadas());
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        /// Guardar Preferences

        // Array de Noticias Guardadas
        Gson gson = new Gson();
        String jsonArrayNoticias = gson.toJson(StaticObjects.getArrayNoticiasGuardadas());
        editor.putString("array_noticias_guardadas", jsonArrayNoticias);
        Log.i("--- PREFS", "array_noticias_guardadas: " + jsonArrayNoticias);
        editor.commit();
    }

}

