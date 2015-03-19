package com.liodevel.my_reader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liodevel.my_reader.Adapters.ItemFeedAdapter;
import com.liodevel.my_reader.Models.ItemFeed;
import com.liodevel.my_reader.SwipeDismiss.SwipeDismissListViewTouchListener;
import com.liodevel.my_reader.Utils.StaticObjects;

import java.util.ArrayList;


public class ManageFeedsActivity extends Activity {

    ListView lista;
    Activity currentActivity;

    private ItemFeedAdapter adapter;

    Typeface tf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_feeds);

        // Tipografia
        tf = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_LIGHT);
        // Tipografia nombre App
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTittle = (TextView) findViewById(titleId);
        actionBarTittle.setTextColor(Color.WHITE);
        actionBarTittle.setTypeface(tf);

        this.setTitle(getResources().getString(R.string.title_activity_manage_feeds));


        /// Preparar Lista
        currentActivity = this;
        lista = (ListView) findViewById(R.id.lista_feeds);

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
                                    Log.i("--- ADAPTER", "Position: " + position);
                                    StaticObjects.getArrayFeeds().remove(position);
                                    //adapter.remove(adapter.getItem(position));
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

        lista.setOnTouchListener(touchListener);

        /// Cargar ArrayFeeds en la lista

        if (StaticObjects.getArrayFeeds() != null) {
            adapter = new ItemFeedAdapter(this, StaticObjects.getArrayFeeds());
            lista.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            StaticObjects.setArrayFeeds(new ArrayList<ItemFeed>());
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        /// Guardar Preferences
        //StaticObjects.getArrayFeeds().add(new ItemFeed("Meneame", "http://meneame.feedsportal.com/rss"));

        // Array de Feeds
        Gson gson = new Gson();
        String jsonArrayFeeds = gson.toJson(StaticObjects.getArrayFeeds());
        editor.putString("array_feeds", jsonArrayFeeds);
        Log.i("--- PREFS", "array_feeds: " + jsonArrayFeeds);

        editor.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_feeds, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new_feed) {
            currentActivity.startActivity(new Intent(currentActivity, NewFeedActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
