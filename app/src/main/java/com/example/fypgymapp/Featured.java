package com.example.fypgymapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.location.places.ui.PlacePicker;
import com.squareup.picasso.Picasso;

public class Featured extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ////////////// For Navigation //////////
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    ////////////// End Of For Navigation /////////



    private ImageButton topGyms;
    private ImageButton yogaBest;
    private ImageButton recommended;
    private String firstString;
    private String secondString;
    private String recommendedURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


 


        topGyms = (ImageButton) findViewById(R.id.topgyms);
        yogaBest = (ImageButton) findViewById(R.id.yogabest);
        recommended = (ImageButton) findViewById(R.id.recommended);



        yogaBest.setBackground(null);
        topGyms.setBackground(null);
        recommended.setBackground(null);
        firstString = "https://firebasestorage.googleapis.com/v0/b/fyptest2-38a21.appspot.com/o/" +
                "bodybuildingfeatured.png?alt=media&token=06bd4cf9-9b76-4280-a987-398301ef7b28";
        Picasso.with(this).load(firstString)
                .placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(yogaBest);

        secondString = "https://firebasestorage.googleapis.com/v0/b/fyptest2-38a21.appspot.com/o/" +
                "toptengyms.png?alt=media&token=d99e68fd-b197-4771-902d-8dabea72a970";

        Picasso.with(this).load(secondString)
                .placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(topGyms);

        recommendedURL="https://firebasestorage.googleapis.com/v0/b/fyptest2-38a21.appspot.com/o/" +
                "recomendedclasses.png?alt=media&token=23b27991-0de2-465f-88b0-c565ca0a88ba";

        Picasso.with(this).load(recommendedURL)
                .placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(recommended);






        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Featured.this, RecommendedClasses.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.featured, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_featured:
                Intent h = new Intent(Featured.this, Featured.class);
                startActivity(h);
                break;
            case R.id.nav_categories:
                Intent i = new Intent(Featured.this, Categories.class);
                startActivity(i);
                break;
            case R.id.nav_my_classes:
                Intent j = new Intent(Featured.this, RegisteredClasses.class);
                startActivity(j);
                break;
            case R.id.nav_search:
                Intent k = new Intent(Featured.this, GymSearch.class);
                startActivity(k);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
