package com.example.fypgymapp;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Button;
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

    public Button monday;
    public Button tuesday;
    public Button wednesday;
    public Button thursday;
    public Button friday;
    public boolean [] days;

    public ArrayList<ParentObject> parentObjects;
    public ArrayList<MyGymClass> gymClassArrayList;

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




        ////////////////////// Get Buttons /////////////////////
        days = new boolean[]{true, true, true, true, true};

        monday = (Button) findViewById(R.id.monday);
        monday.setBackgroundColor(Color.DKGRAY);
        monday.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (days[0])
                    monday.setBackgroundColor(Color.LTGRAY);
                else
                    monday.setBackgroundColor(Color.DKGRAY);
                days[0]=!days[0];
                displayAdapter();
            }
        });

        tuesday = (Button) findViewById(R.id.tuesday);
        tuesday.setBackgroundColor(Color.DKGRAY);
        tuesday.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (days[1])
                    tuesday.setBackgroundColor(Color.LTGRAY);
                else
                    tuesday.setBackgroundColor(Color.DKGRAY);
                days[1]=!days[1];
                displayAdapter();
            }
        });

        wednesday = (Button) findViewById(R.id.wednesday);
        wednesday.setBackgroundColor(Color.DKGRAY);
        wednesday.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (days[2])
                    wednesday.setBackgroundColor(Color.LTGRAY);
                else
                    wednesday.setBackgroundColor(Color.DKGRAY);
                days[2]=!days[2];
                displayAdapter();
            }
        });

        thursday = (Button) findViewById(R.id.thursday);
        thursday.setBackgroundColor(Color.DKGRAY);
        thursday.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (days[3])
                    thursday.setBackgroundColor(Color.LTGRAY);
                else
                    thursday.setBackgroundColor(Color.DKGRAY);
                days[3]=!days[3];
                displayAdapter();
            }
        });

        friday = (Button) findViewById(R.id.friday);
        friday.setBackgroundColor(Color.DKGRAY);
        friday.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (days[4])
                    friday.setBackgroundColor(Color.LTGRAY);
                else
                    friday.setBackgroundColor(Color.DKGRAY);
                days[4]=!days[4];
                displayAdapter();
            }
        });

        ////////////////////// End of Get Buttons /////////////////



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


        gymClassArrayList = new ArrayList<MyGymClass>();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
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
                        gymClassArrayList.add(myGymClass);
                    }
                    displayAdapter();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //////////// Done with Sample Classes /////////////////////
        //////////// Done with List of Classes In recycler View ///////////////
    }

    void displayAdapter() {

        parentObjects = new ArrayList<>();
        for (int i=0; i<gymClassArrayList.size(); i++)
        {
            boolean allow=true;
            MyGymClass myGymClass=gymClassArrayList.get(i);
            switch (myGymClass.classDays)
            {
                case "M":
                    if (!days[0])
                        allow=false;
                    Log.d(TAG, "Class is monday, allow is: "+String.valueOf(allow));
                    break;
                case "T":
                    if (!days[1])
                        allow=false;
                    Log.d(TAG, "Class is tuesday, allow is: "+String.valueOf(allow));
                    break;
                case "W":
                    if (!days[2])
                        allow=false;
                    Log.d(TAG, "Class is wednesday, allow is: "+String.valueOf(allow));
                    break;
                case "R":
                    if (!days[3])
                        allow=false;
                    Log.d(TAG, "Class is thursday, allow is: "+String.valueOf(allow));
                    break;
                case "F":
                    if (!days[4])
                        allow=false;
                    Log.d(TAG, "Class is friday, allow is: "+String.valueOf(allow));
                    break;

                default:
                    Log.d(TAG, "Class Day not found");
                    break;
            }

            if (allow)
            {
                Log.d(TAG,"Class Name: "+myGymClass.title);
                ArrayList<Object> childList = new ArrayList<>();
                String newTitle = myGymClass.title + " - " + myGymClass.facility;
                String dateAndTime = myGymClass.classDays + ": " + myGymClass.time;
                ParentClass parentClass = new ParentClass(newTitle,dateAndTime);
                ChildClass childClass = new ChildClass("Instructor: "+myGymClass.instructor,
                        "Remaining seats: " + myGymClass.remainingSeats);
                childClass.myGymClass=myGymClass;
                childClass.setCategory(categoryName);
                childList.add(childClass);
                parentClass.setChildObjectList(childList);
                parentObjects.add(parentClass);
            }
        }


        MyClassExpandableAdapter myClassExpandableAdapter = new MyClassExpandableAdapter(DisplayClassesFromCategories.this,
                parentObjects);
        myClassExpandableAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
        myClassExpandableAdapter.setParentClickableViewAnimationDefaultDuration();
        myClassExpandableAdapter.setParentAndIconExpandOnClick(true);
        recyclerView.setAdapter(myClassExpandableAdapter);

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
            case R.id.nav_my_classes:
                Intent j = new Intent(DisplayClassesFromCategories.this, RegisteredClasses.class);
                startActivity(j);
                break;
            case R.id.nav_search:
                Intent k = new Intent(DisplayClassesFromCategories.this, GymSearch.class);
                startActivity(k);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
