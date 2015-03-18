package com.liodevel.my_reader.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.liodevel.my_reader.Models.ItemFeed;
import com.liodevel.my_reader.R;
import com.liodevel.my_reader.Utils.StaticObjects;

import java.util.ArrayList;

/**
 * Created by emilio on 08/02/2015.
 */
public class ItemFeedAdapter extends ArrayAdapter<Object> {

    Context context;
    private ArrayList<ItemFeed> feeds;

    public ItemFeedAdapter(Context context, ArrayList<ItemFeed> feeds) {
        super(context, R.layout.item_feed);
        this.context = context;
        this.feeds = feeds;
    }

    public int getCount() {
        return feeds.size();
    }

    private static class PlaceHolder {

        TextView nombre;
        TextView url;
        TextView botonBorrar;

        public static PlaceHolder generate(View convertView) {
            PlaceHolder placeHolder = new PlaceHolder();
            placeHolder.nombre = (TextView) convertView
                    .findViewById(R.id.nombre_feed);

            placeHolder.url = (TextView) convertView
                    .findViewById(R.id.url_feed);
            placeHolder.botonBorrar = (TextView) convertView
                    .findViewById(R.id.button_delete_feed);

            return placeHolder;
        }
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(final int position, View convertView, ViewGroup parent) {
        PlaceHolder placeHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_feed, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
        } else {
            placeHolder = (PlaceHolder) convertView.getTag();
        }

        placeHolder.nombre.setText(feeds.get(position).getNombre());
        placeHolder.url.setText(feeds.get(position).getUrl());


        Typeface tf = Typeface.createFromAsset(context.getAssets(), StaticObjects.FONT_LIGHT);
        placeHolder.nombre.setTypeface(tf);
        placeHolder.url.setTypeface(tf);
        placeHolder.botonBorrar.setTypeface(tf);

        return (convertView);
    }

}
