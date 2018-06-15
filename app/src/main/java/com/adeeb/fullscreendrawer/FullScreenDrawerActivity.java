package com.adeeb.fullscreendrawer;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adeeb.fullscreendrawer.Adapter.DrawerAdapter;
import com.adeeb.fullscreendrawer.model.Drawer;
import com.adeeb.fullscreendrawer.utils.FullDrawerLayout;

import java.util.ArrayList;


public class FullScreenDrawerActivity extends AppCompatActivity  {

    ActionBarDrawerToggle mDrawerToggle;
    FullDrawerLayout mDrawer;
    ListView mDrawerListView;
    DrawerAdapter adapter;
    private Drawer drawerMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_drawer);

        mDrawer = (FullDrawerLayout) findViewById(R.id.drawer_layout);

        // Replace the default action bar with a Toolbar so the navigation drawer appears above it
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // These lines are needed to display the top-left hamburger button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Make the hamburger button work
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.app_name, R.string.app_name);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.options_btn);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    View mV = getCurrentFocus();

                    if (imm.isAcceptingText()) {
                        imm.hideSoftInputFromWindow(mV.getWindowToken(), 0);
                    }
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
//        mDrawer.setDrawerListener(mDrawerToggle);
//        mDrawerToggle.syncState();

        // Change the TextView message when ListView item is clicked
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, mDrawerListView, false);
        header.findViewById(R.id.imv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                    mDrawer.closeDrawer(GravityCompat.START);
                }
            }
        });
        mDrawerListView.addHeaderView(header, null, false);
        mDrawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  selectItem(position);
                mDrawer.closeDrawer(GravityCompat.START);
            }
        });

        ArrayList<Drawer> listDrawer = addItemsToDrawer();
        adapter = new DrawerAdapter(this, R.layout.drawer_list_item, listDrawer);
        mDrawerListView.setAdapter(adapter);

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private ArrayList<Drawer> addItemsToDrawer() {

        String[] mMenuListItem = null;

        ArrayList<Drawer> listDrawer = new ArrayList<Drawer>();

        mMenuListItem = getResources().getStringArray(R.array.menu_items);
        if (mMenuListItem != null) {

            drawerMenu = new Drawer();
            drawerMenu.setDrawerValue(mMenuListItem[0]);
            drawerMenu.setDrawerImage(this.getResources().getDrawable(
                    this.getResources().getIdentifier("menu_item_" + 1, "mipmap", this.getPackageName())));
            listDrawer.add(drawerMenu);

            drawerMenu = new Drawer();
            drawerMenu.setDrawerValue(mMenuListItem[1]);
            drawerMenu.setDrawerImage(this.getResources().getDrawable(
                    this.getResources().getIdentifier("menu_item_" + 2, "mipmap", this.getPackageName())));
            listDrawer.add(drawerMenu);

            drawerMenu = new Drawer();
            drawerMenu.setDrawerValue(mMenuListItem[2]);
            drawerMenu.setDrawerImage(this.getResources().getDrawable(
                    this.getResources().getIdentifier("menu_item_" + 3, "mipmap", this.getPackageName())));
            listDrawer.add(drawerMenu);

            drawerMenu = new Drawer();
            drawerMenu.setDrawerValue(mMenuListItem[3]);
            drawerMenu.setDrawerImage(this.getResources().getDrawable(
                    this.getResources().getIdentifier("menu_item_" + 4, "mipmap", this.getPackageName())));
            listDrawer.add(drawerMenu);

        }

        return listDrawer;
    }





    public void cancelDialog(View view) {
        finish();
    }

    public void openSetting(View view) {
        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(myIntent, 1);
    }



}