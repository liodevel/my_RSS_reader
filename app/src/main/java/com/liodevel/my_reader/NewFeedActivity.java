package com.liodevel.my_reader;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liodevel.my_reader.Models.ItemFeed;
import com.liodevel.my_reader.Utils.StaticObjects;


public class NewFeedActivity extends Activity {

    EditText textNombre, textUrl;
    TextView labelNombre, labelUrl;
    Typeface tf;
    Activity currentActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feed);
        currentActivity = this;

        // Tipografia
        tf = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_LIGHT);

        // Tipografia nombre App
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTittle = (TextView) findViewById(titleId);
        actionBarTittle.setTextColor(Color.WHITE);
        actionBarTittle.setTypeface(tf);

        textNombre = (EditText) findViewById(R.id.text_nombre);
        textNombre.setTypeface(tf);

        textUrl = (EditText) findViewById(R.id.text_url);
        textUrl.setTypeface(tf);

        labelNombre = (TextView) findViewById(R.id.label_name_feed);
        labelNombre.setTypeface(tf);

        labelUrl = (TextView) findViewById(R.id.label_url_feed);
        labelUrl.setTypeface(tf);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_accept_new_feed) {

            // Comprobar que los campos tienen datos
            if (textNombre.getText().length() > 0 && textUrl.getText().length() > 0) {
                StaticObjects.getArrayFeeds().add(
                        new ItemFeed(textNombre.getText().toString(), textUrl.getText().toString())
                );


                /// Guardar Array de Feeds en Preferences
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPrefs.edit();

                Gson gson = new Gson();
                String jsonArrayFeeds = gson.toJson(StaticObjects.getArrayFeeds());
                editor.putString("array_feeds", jsonArrayFeeds);
                Log.i("--- PREFS", "array_feeds: " + jsonArrayFeeds);

                editor.commit();

                // Volver a la pantalla de Manage Feeds
                currentActivity.finish();


            } else {
                if (textNombre.getText().length() < 1) {
                    labelNombre.setTextColor(Color.RED);
                } else {
                    labelNombre.setTextColor(Color.DKGRAY);
                }
                if (textUrl.getText().length() < 1) {
                    labelUrl.setTextColor(Color.RED);
                } else {
                    labelUrl.setTextColor(Color.DKGRAY);
                }
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
