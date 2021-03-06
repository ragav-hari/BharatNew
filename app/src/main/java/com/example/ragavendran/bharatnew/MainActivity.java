package com.example.ragavendran.bharatnew;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.adapter.ExpandableListAdapter;
import demo.pojo.Brand;
import demo.pojo.BrandAttribute;
import demo.pojo.TabContent;
import demo.pojo.Type;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();



        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        // Adding parent data
        listDataHeader.add("Salt");
        listDataHeader.add("Sugar");
        listDataHeader.add("Jaggery");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);


        TabContent tabContent = new TabContent();
        TabContent tabContent1 = new TabContent();
        ArrayList<TabContent> tabContents = new ArrayList<TabContent>();

        Type salt  = new Type();
        Type sugar = new Type();

        Brand ionisedsalt   = new Brand();
        Brand ordinarysalt  = new Brand();
        Brand refinedsugar  = new Brand();
        Brand ordinarysugar = new Brand();

        BrandAttribute ionsaltattr1 = new BrandAttribute();
        BrandAttribute ionsaltattr2 = new BrandAttribute();
        BrandAttribute ordsaltattr1 = new BrandAttribute();
        BrandAttribute ordsaltattr2 = new BrandAttribute();
        BrandAttribute refsugrattr1 = new BrandAttribute();
        BrandAttribute refsugrattr2 = new BrandAttribute();
        BrandAttribute ordsugrattr1 = new BrandAttribute();
        BrandAttribute ordsugrattr2 = new BrandAttribute();

        ArrayList<Brand>  sugarlist = new ArrayList<Brand>();
        ArrayList<Brand>  saltlist  = new ArrayList<Brand>();

        ArrayList<BrandAttribute> sugarlistattr = new ArrayList<BrandAttribute>();
        ArrayList<BrandAttribute> saltlistattr = new ArrayList<BrandAttribute>();

        salt.setType_id(1);
        salt.setType_name("Salt");

        sugar.setType_id(2);
        sugar.setType_name("Sugar");

        ionsaltattr1.setWeight("10 KG");
        ionsaltattr1.setPrice("Rs 50");

        ionsaltattr2.setWeight("5 KG");
        ionsaltattr2.setPrice("Rs 80");

        refsugrattr1.setWeight("15 KG");
        refsugrattr2.setPrice("Rs 100");

        refsugrattr2.setWeight("20 KG");
        refsugrattr2.setPrice("Rs 150");

        saltlistattr.add(ionsaltattr1);
        saltlistattr.add(ionsaltattr2);

        sugarlistattr.add(refsugrattr1);
        sugarlistattr.add(refsugrattr2);

        ionisedsalt.setBrand_id(1);
        ionisedsalt.setBrand_name("Ionised Salt");
        ionisedsalt.setBrandAttributeList(saltlistattr);

        refinedsugar.setBrand_id(2);
        refinedsugar.setBrand_name("Refined Sugar");
        refinedsugar.setBrandAttributeList(sugarlistattr);

        saltlist.add(ionisedsalt);
        sugarlist.add(refinedsugar);

        tabContent.setType(salt);
        tabContent.setBrandList(saltlist);

        tabContent1.setType(sugar);
        tabContent1.setBrandList(sugarlist);

        tabContents.add(tabContent);
        tabContents.add(tabContent1);

    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
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
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
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
            getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
