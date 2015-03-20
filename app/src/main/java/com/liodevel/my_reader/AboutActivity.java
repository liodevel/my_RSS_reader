package com.liodevel.my_reader;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.liodevel.my_reader.Utils.StaticObjects;


public class AboutActivity extends Activity {
    Typeface tf;
    TextView aboutTitle, aboutWeb, aboutVersion, aboutDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Tipografia
        tf = Typeface.createFromAsset(getAssets(), StaticObjects.FONT_LIGHT);

        // Tipografia nombre App
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTittle = (TextView) findViewById(titleId);
        actionBarTittle.setText(getResources().getString(R.string.about));
        actionBarTittle.setTextColor(Color.WHITE);
        actionBarTittle.setTypeface(tf);

        aboutTitle = (TextView) findViewById(R.id.about_title);
        aboutTitle.setTypeface(tf);
        aboutDesc = (TextView) findViewById(R.id.about_desc);
        aboutDesc.setTypeface(tf);
        aboutWeb = (TextView) findViewById(R.id.about_web);
        aboutWeb.setTypeface(tf);
        aboutVersion = (TextView) findViewById(R.id.about_version);
        aboutVersion.setTypeface(tf);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
