package com.liodevel.my_reader.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.liodevel.my_reader.Models.ItemNoticia;
import com.liodevel.my_reader.R;
import com.liodevel.my_reader.Utils.StaticObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by emilio on 08/02/2015.
 */
public class ItemNoticiaGuardadaAdapter extends ArrayAdapter<Object> {

    Context context;
    private ArrayList<ItemNoticia> noticias;

    ImageLoader imageLoader;

    public ItemNoticiaGuardadaAdapter(Context context, ArrayList<ItemNoticia> noticias) {
        super(context, R.layout.item_noticia);
        this.context = context;
        this.noticias = noticias;
        ImageLoader.ImageCache imageCache = new BitmapLruCache();
        this.imageLoader = new ImageLoader(Volley.newRequestQueue(context), imageCache);

    }

    public int getCount() {
        return noticias.size();
    }

    private static class PlaceHolder {

        TextView titulo;
        TextView link;
        TextView origen;
        TextView category;
        NetworkImageView imagen;


        public static PlaceHolder generate(View convertView) {
            PlaceHolder placeHolder = new PlaceHolder();

            placeHolder.titulo = (TextView) convertView.findViewById(R.id.titulo_noticia);
            placeHolder.link = (TextView) convertView.findViewById(R.id.link_noticia);
            placeHolder.origen = (TextView) convertView.findViewById(R.id.autor_noticia);
            placeHolder.category = (TextView) convertView.findViewById(R.id.category_noticia);
            placeHolder.imagen = (NetworkImageView) convertView.findViewById(R.id.imagen);


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
            convertView = View.inflate(context, R.layout.item_noticia, null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
        } else {
            placeHolder = (PlaceHolder) convertView.getTag();
        }


        placeHolder.titulo.setText(noticias.get(position).getTitulo());
        placeHolder.origen.setText(noticias.get(position).getOrigen());
//        placeHolder.category.setText(noticias.get(position).getCategory());

        SimpleDateFormat dateFormat = new SimpleDateFormat("KK:mm  MMM dd");

        placeHolder.link.setText(dateFormat.format(noticias.get(position).getFechaPublicacion()));
        if (noticias.get(position).isLeida()) {
            //Log.i("--LISTA_ADAPTER", "Noticia Leida en array: " + position);
            //convertView.findViewById(R.id.titulo_noticia).setBackgroundColor(Color.GREEN);
        }

        placeHolder.imagen.setImageUrl(noticias.get(position).getImagenURL(), imageLoader);

        //placeHolder.imagen.setImageBitmap();


        /*
        placeHolder.titulo.bringToFront();
        placeHolder.category.bringToFront();
        placeHolder.link.bringToFront();
        placeHolder.origen.bringToFront();
*/
        Typeface tf = Typeface.createFromAsset(context.getAssets(), StaticObjects.FONT_LIGHT);
        Typeface fa = Typeface.createFromAsset(context.getAssets(), StaticObjects.FONT_AWESOME);

        placeHolder.titulo.setTypeface(tf);
        placeHolder.link.setTypeface(tf);
        placeHolder.origen.setTypeface(tf);
        placeHolder.category.setTypeface(fa);

        //Log.i("CAT", noticias.get(position).getCategory());

        switch (noticias.get(position).getCategory()){
            case"Art":
                placeHolder.category.setText(R.string.fa_paint_brush);
                //placeHolder.category.setTextColor(Color.parseColor("#BA6735"));
                break;
            case"Books":
                placeHolder.category.setText(R.string.fa_book);
                //placeHolder.category.setTextColor(Color.parseColor("#593A1E"));
                break;
            case"Business":
                placeHolder.category.setText(R.string.fa_briefcase);
                //placeHolder.category.setTextColor(Color.parseColor("#006400"));
                break;
            case"Cars":
                placeHolder.category.setText(R.string.fa_car);
                //placeHolder.category.setTextColor(Color.parseColor("#EE3500"));
                break;
            case"Design":
                placeHolder.category.setText(R.string.fa_pencil);
                //placeHolder.category.setTextColor(Color.parseColor("#374E65"));
                break;
            case"Fame":
                placeHolder.category.setText(R.string.fa_diamond);
                //placeHolder.category.setTextColor(Color.parseColor("#E37A7D"));
                break;
            case"Food":
                placeHolder.category.setText(R.string.fa_coffee);
                //placeHolder.category.setTextColor(Color.parseColor("#353439"));
                break;
            case"Funny":
                placeHolder.category.setText(R.string.fa_comments);
                //placeHolder.category.setTextColor(Color.parseColor("#ABFD4E"));
                break;
            case"Gaming":
                placeHolder.category.setText(R.string.fa_gamepad);
                //placeHolder.category.setTextColor(Color.parseColor("#FFA624"));
                break;
            case"Histories":
                placeHolder.category.setText(R.string.fa_language);
                //placeHolder.category.setTextColor(Color.parseColor("#454C47"));
                break;
            case"Internet":
                placeHolder.category.setText(R.string.fa_desktop);
                //placeHolder.category.setTextColor(Color.parseColor("#BA6735"));
                break;
            case"Music":
                placeHolder.category.setText(R.string.fa_music);
                // placeHolder.category.setTextColor(Color.parseColor("#7C5936"));
                break;
            case"News":
                placeHolder.category.setText(R.string.fa_newspaper);
                //placeHolder.category.setTextColor(Color.parseColor("#403D45"));
                break;
            case"Photo":
                placeHolder.category.setText(R.string.fa_camera_retro);
                //placeHolder.category.setTextColor(Color.parseColor("#FFA624"));
                break;
            case"Politics":
                placeHolder.category.setText(R.string.fa_university);
                //placeHolder.category.setTextColor(Color.parseColor("#482120"));
                break;
            case"Science":
                placeHolder.category.setText(R.string.fa_rocket);
                //placeHolder.category.setTextColor(Color.parseColor("#7263E9"));
                break;
            case"Sports":
                placeHolder.category.setText(R.string.fa_futbol);
                //placeHolder.category.setTextColor(Color.parseColor("#353439"));
                break;
            case"Style":
                placeHolder.category.setText(R.string.fa_picture_o);
                //placeHolder.category.setTextColor(Color.parseColor("#C3E679"));
                break;
            case"TV":
                placeHolder.category.setText(R.string.fa_video_camera);
                //placeHolder.category.setTextColor(Color.parseColor("#353439"));
                break;
            case"Technology":
                placeHolder.category.setText(R.string.fa_cogs);
                //placeHolder.category.setTextColor(Color.parseColor("#132245"));
                break;
        }

        noticias.get(position).setIcono(placeHolder.category.getText().toString());

        return (convertView);
    }


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
