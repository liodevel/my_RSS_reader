package com.liodevel.my_reader.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.liodevel.my_reader.Models.ItemCollection;
import com.liodevel.my_reader.R;

import java.util.ArrayList;

/**
 * Created by emilio on 08/02/2015.
 */
public class ItemCollectionAdapter extends ArrayAdapter<Object> {

    Context context;
    private ArrayList<ItemCollection> collections;


    public ItemCollectionAdapter(Context context, ArrayList<ItemCollection> collections) {
        super(context, R.layout.item_feed);
        this.context = context;
        this.collections = collections;
    }

    public int getCount() {
        return collections.size();
    }

    private static class PlaceHolder {

        TextView nombre;
        TextView descripcion;
        TextView botonAdd;


        public static PlaceHolder generate(View convertView) {
            PlaceHolder placeHolder = new PlaceHolder();

            placeHolder.nombre = (TextView) convertView
                    .findViewById(R.id.nombre_collection);

            placeHolder.descripcion = (TextView) convertView
                    .findViewById(R.id.desc_collection);

            placeHolder.botonAdd = (TextView) convertView
                    .findViewById(R.id.button_add_feed);

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
            convertView = View.inflate(context, R.layout.item_collection, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);

            placeHolder.nombre = (TextView) convertView.findViewById(R.id.nombre_collection);
            placeHolder.descripcion = (TextView) convertView.findViewById(R.id.desc_collection);
            placeHolder.botonAdd = (TextView) convertView.findViewById(R.id.button_add_feed);

        } else {
            placeHolder = (PlaceHolder) convertView.getTag();
        }

        placeHolder.nombre.setText(collections.get(position).getName());
        placeHolder.descripcion.setText(collections.get(position).getDescription());

        if (collections.get(position).isAdded()) {
            placeHolder.botonAdd.setBackgroundColor(Color.parseColor("#991111"));
            placeHolder.botonAdd.setText("Remove");
            Log.i("---POS", ": " + position);
        } else {
            placeHolder.botonAdd.setBackgroundColor(Color.parseColor("#838e73"));
            placeHolder.botonAdd.setText("Add");
        }

        /*
        Typeface tf = Typeface.createFromAsset(context.getAssets(), StaticObjects.FONT_LIGHT);
        placeHolder.nombre.setTypeface(tf);
        placeHolder.descripcion.setTypeface(tf);
        placeHolder.botonAdd.setTypeface(tf);
*/
        return (convertView);
    }

}
