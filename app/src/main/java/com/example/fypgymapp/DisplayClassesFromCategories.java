package com.example.fypgymapp;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DisplayClassesFromCategories extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ////////////// For Navigation //////////
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    ////////////// End Of For Navigation /////////


    public final String TAG="Ryan";

    public String imageURL;
    public String categoryName;
    public ImageView imageView;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_classes_from_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        /////////////////// Load proper image //////////////////
        Bundle extras = getIntent().getExtras();
        if (extras!=null)
        {
            imageURL = extras.getString("url");
            categoryName = extras.getString("name");
            Log.d(TAG,"Extras not null, imageURL is: "+imageURL);
            Log.d(TAG, "Category is: " +categoryName);
        }
        else{
            Log.d(TAG, "Extras is null");
        }
        imageView = (ImageView) findViewById(R.id.CategoryPic);

        Picasso.with(DisplayClassesFromCategories.this).load(imageURL)
                .placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);


        /////////////////// End of Load proper image ///////////




        ///////////////// Display List Of Classes in Recycler View //////////////
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /////   Get List of classes for the Category ////////

        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Classes").child(categoryName);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Log.d(TAG,"In Child");
                    MyGymClass myGymClass =snapshot.getValue(MyGymClass.class);
                    if (myGymClass==null)
                        Log.d(TAG,"GymClass is null");
                    else
                    {
                        Log.d(TAG,"Class Name: "+myGymClass.title);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for (int i=0; i<7; i++)
        {
            ArrayList<Object> childList = new ArrayList<>();
            ParentClass parentClass = new ParentClass("Class Number: " + String.valueOf(i+1), "Monday: 3-4");
            ChildClass childClass = new ChildClass("Ryan Chedrawi", i+10);
            childList.add(childClass);
            parentClass.setChildObjectList(childList);
            parentObjects.add(parentClass);
        }

        MyClassExpandableAdapter myClassExpandableAdapter = new MyClassExpandableAdapter(DisplayClassesFromCategories.this,
                parentObjects);
        myClassExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        myClassExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        myClassExpandableAdapter.setParentAndIconExpandOnClick(true);
        recyclerView.setAdapter(myClassExpandableAdapter);
        //////////// Done with Sample Classes /////////////////////
        //////////// Done with List of Classes In recycler View ///////////////
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
                Intent h = new Intent(DisplayClassesFromCategories.this, Featured.class);
                startActivity(h);
                break;
            case R.id.nav_categories:
                Intent i = new Intent(DisplayClassesFromCategories.this, Categories.class);
                startActivity(i);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
