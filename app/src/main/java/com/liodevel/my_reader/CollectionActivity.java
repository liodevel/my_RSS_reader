package com.liodevel.my_reader;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.liodevel.my_reader.Adapters.ItemCollectionAdapter;
import com.liodevel.my_reader.Utils.StaticCollections;


public class CollectionActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    ListView lista;
    ItemCollectionAdapter adapter;
    int listaActiva = 0;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        Log.i("--COLLECTION", "List Adapter...");
        lista = (ListView) findViewById(R.id.lista_feeds_collection);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("LISTA_ACTIVA", "" + listaActiva);
                switch (listaActiva) {
                    case 0:
                        if (StaticCollections.getCollectionArt().get(position).isAdded()) {
                            StaticCollections.getCollectionArt().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionArt().get(position).setAdded(true);
                        }
                        break;
                    case 1:
                        if (StaticCollections.getCollectionBooks().get(position).isAdded()) {
                            StaticCollections.getCollectionBooks().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionBooks().get(position).setAdded(true);
                        }
                        break;
                    case 2:
                        if (StaticCollections.getCollectionBusiness().get(position).isAdded()) {
                            StaticCollections.getCollectionBusiness().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionBusiness().get(position).setAdded(true);
                        }
                        break;
                    case 3:
                        if (StaticCollections.getCollectionCars().get(position).isAdded()) {
                            StaticCollections.getCollectionCars().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionCars().get(position).setAdded(true);
                        }
                        break;
                    case 4:
                        if (StaticCollections.getCollectionDesign().get(position).isAdded()) {
                            StaticCollections.getCollectionDesign().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionDesign().get(position).setAdded(true);
                        }
                        break;
                    case 5:
                        if (StaticCollections.getCollectionFame().get(position).isAdded()) {
                            StaticCollections.getCollectionFame().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionFame().get(position).setAdded(true);
                        }
                        break;
                    case 6:
                        if (StaticCollections.getCollectionFood().get(position).isAdded()) {
                            StaticCollections.getCollectionFood().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionFood().get(position).setAdded(true);
                        }
                        break;
                    case 7:
                        if (StaticCollections.getCollectionFunny().get(position).isAdded()) {
                            StaticCollections.getCollectionFunny().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionFunny().get(position).setAdded(true);
                        }
                        break;
                    case 8:
                        if (StaticCollections.getCollectionGaming().get(position).isAdded()) {
                            StaticCollections.getCollectionGaming().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionGaming().get(position).setAdded(true);
                        }
                        break;
                    case 9:
                        if (StaticCollections.getCollectionHistories().get(position).isAdded()) {
                            StaticCollections.getCollectionHistories().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionHistories().get(position).setAdded(true);
                        }
                        break;
                    case 10:
                        if (StaticCollections.getCollectionInternet().get(position).isAdded()) {
                            StaticCollections.getCollectionInternet().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionInternet().get(position).setAdded(true);
                        }
                        break;
                    case 11:
                        if (StaticCollections.getCollectionMusic().get(position).isAdded()) {
                            StaticCollections.getCollectionMusic().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionMusic().get(position).setAdded(true);
                        }
                        break;
                    case 12:
                        if (StaticCollections.getCollectionNews().get(position).isAdded()) {
                            StaticCollections.getCollectionNews().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionNews().get(position).setAdded(true);
                        }
                        break;
                    case 13:
                        if (StaticCollections.getCollectionPhoto().get(position).isAdded()) {
                            StaticCollections.getCollectionPhoto().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionPhoto().get(position).setAdded(true);
                        }
                        break;
                    case 14:
                        if (StaticCollections.getCollectionPolitics().get(position).isAdded()) {
                            StaticCollections.getCollectionPolitics().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionPolitics().get(position).setAdded(true);
                        }
                        break;
                    case 15:
                        if (StaticCollections.getCollectionScience().get(position).isAdded()) {
                            StaticCollections.getCollectionScience().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionScience().get(position).setAdded(true);
                        }
                        break;
                    case 16:
                        if (StaticCollections.getCollectionSports().get(position).isAdded()) {
                            StaticCollections.getCollectionSports().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionSports().get(position).setAdded(true);
                        }
                        break;
                    case 17:
                        if (StaticCollections.getCollectionStyle().get(position).isAdded()) {
                            StaticCollections.getCollectionStyle().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionStyle().get(position).setAdded(true);
                        }
                        break;
                    case 18:
                        if (StaticCollections.getCollectionTV().get(position).isAdded()) {
                            StaticCollections.getCollectionTV().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionTV().get(position).setAdded(true);
                        }
                        break;
                    case 19:
                        if (StaticCollections.getCollectionTechnology().get(position).isAdded()) {
                            StaticCollections.getCollectionTechnology().get(position).setAdded(false);
                        } else {
                            StaticCollections.getCollectionTechnology().get(position).setAdded(true);
                        }
                        break;
                    default:
                }
                adapter.notifyDataSetChanged();

            }
        });


        Log.i("--COLLECTION", StaticCollections.getCollectionArt().toString());
        adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionArt());
        lista.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Log.i("--DRAWER", "Position: " + position);
        if (adapter != null) {

            switch (position) {
                case 0:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionArt());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 1:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionBooks());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 2:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionBusiness());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 3:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionCars());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 4:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionDesign());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 5:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionFame());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 6:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionFood());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 7:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionFunny());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 8:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionGaming());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 9:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionHistories());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 10:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionInternet());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 11:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionMusic());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 12:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionNews());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 13:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionPhoto());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 14:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionPolitics());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 15:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionScience());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 16:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionSports());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 17:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionStyle());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 18:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionTV());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                case 19:
                    adapter = new ItemCollectionAdapter(this, StaticCollections.getCollectionTechnology());
                    lista.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                default:
            }
            listaActiva = position;

        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                break;
            case 8:
                mTitle = getString(R.string.title_section8);
                break;
            case 9:
                mTitle = getString(R.string.title_section9);
                break;
            case 10:
                mTitle = getString(R.string.title_section10);
                break;
            case 11:
                mTitle = getString(R.string.title_section11);
                break;
            case 12:
                mTitle = getString(R.string.title_section12);
                break;
            case 13:
                mTitle = getString(R.string.title_section13);
                break;
            case 14:
                mTitle = getString(R.string.title_section14);
                break;
            case 15:
                mTitle = getString(R.string.title_section15);
                break;
            case 16:
                mTitle = getString(R.string.title_section16);
                break;
            case 17:
                mTitle = getString(R.string.title_section17);
                break;
            case 18:
                mTitle = getString(R.string.title_section18);
                break;
            case 19:
                mTitle = getString(R.string.title_section19);
                break;
            case 20:
                mTitle = getString(R.string.title_section20);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.collection, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_collection, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((CollectionActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        /// Guardar Preferences

        Gson gsonArt = new Gson();
        String jsonArrayArt = gsonArt.toJson(StaticCollections.getCollectionArt());
        editor.putString("array_art", jsonArrayArt);

        Gson gsonBooks = new Gson();
        String jsonArrayBooks = gsonBooks.toJson(StaticCollections.getCollectionBooks());
        editor.putString("array_books", jsonArrayBooks);

        Gson gsonBusiness = new Gson();
        String jsonArrayBusiness = gsonBusiness.toJson(StaticCollections.getCollectionBusiness());
        editor.putString("array_business", jsonArrayBusiness);

        Gson gsonCars = new Gson();
        String jsonArrayCars = gsonCars.toJson(StaticCollections.getCollectionCars());
        editor.putString("array_cars", jsonArrayCars);

        Gson gsonDesign = new Gson();
        String jsonArrayDesign = gsonDesign.toJson(StaticCollections.getCollectionDesign());
        editor.putString("array_design", jsonArrayDesign);

        Gson gsonFame = new Gson();
        String jsonArrayFame = gsonFame.toJson(StaticCollections.getCollectionFame());
        editor.putString("array_fame", jsonArrayFame);

        Gson gsonFood = new Gson();
        String jsonArrayFood = gsonFood.toJson(StaticCollections.getCollectionFood());
        editor.putString("array_food", jsonArrayFood);

        Gson gsonFunny = new Gson();
        String jsonArrayFunny = gsonFunny.toJson(StaticCollections.getCollectionFunny());
        editor.putString("array_funny", jsonArrayFunny);

        Gson gsonGaming = new Gson();
        String jsonArrayGaming = gsonGaming.toJson(StaticCollections.getCollectionGaming());
        editor.putString("array_gaming", jsonArrayGaming);

        Gson gsonHistories = new Gson();
        String jsonArrayHistories = gsonHistories.toJson(StaticCollections.getCollectionHistories());
        editor.putString("array_histories", jsonArrayHistories);

        Gson gsonInternet = new Gson();
        String jsonArrayInternet = gsonInternet.toJson(StaticCollections.getCollectionInternet());
        editor.putString("array_internet", jsonArrayInternet);

        Gson gsonMusic = new Gson();
        String jsonArrayMusic = gsonMusic.toJson(StaticCollections.getCollectionMusic());
        editor.putString("array_music", jsonArrayMusic);

        Gson gsonNews = new Gson();
        String jsonArrayNews = gsonNews.toJson(StaticCollections.getCollectionNews());
        editor.putString("array_news", jsonArrayNews);

        Gson gsonPhoto = new Gson();
        String jsonArrayPhoto = gsonPhoto.toJson(StaticCollections.getCollectionPhoto());
        editor.putString("array_photo", jsonArrayPhoto);

        Gson gsonPolitics = new Gson();
        String jsonArrayPolitics = gsonPolitics.toJson(StaticCollections.getCollectionPolitics());
        editor.putString("array_politics", jsonArrayPolitics);

        Gson gsonScience = new Gson();
        String jsonArrayScience = gsonScience.toJson(StaticCollections.getCollectionScience());
        editor.putString("array_science", jsonArrayScience);

        Gson gsonSports = new Gson();
        String jsonArraySports = gsonSports.toJson(StaticCollections.getCollectionSports());
        editor.putString("array_sports", jsonArraySports);

        Gson gsonStyle = new Gson();
        String jsonArrayStyle = gsonStyle.toJson(StaticCollections.getCollectionStyle());
        editor.putString("array_style", jsonArrayStyle);

        Gson gsonTV = new Gson();
        String jsonArrayTV = gsonTV.toJson(StaticCollections.getCollectionTV());
        editor.putString("array_tv", jsonArrayTV);

        Gson gsonTechnology = new Gson();
        String jsonArrayTechnology = gsonTechnology.toJson(StaticCollections.getCollectionTechnology());
        editor.putString("array_technology", jsonArrayTechnology);


        editor.commit();
    }
}
