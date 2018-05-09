package com.example.fypgymapp;

import android.content.Intent;
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

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecommendedClasses extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;


    public ArrayList<ParentObject> parentObjects;
    public ArrayList<MyGymClass> gymClassArrayList;
    RecyclerView recyclerView;

    public final String TAG="Ryan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_classes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gymClassArrayList = new ArrayList<MyGymClass>();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Classes").child("bodybuilding");
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
    }


    void displayAdapter() {

        parentObjects = new ArrayList<>();
        for (int i=0; i<gymClassArrayList.size(); i++)
        {
            boolean allow=true;
            MyGymClass myGymClass=gymClassArrayList.get(i);


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
                childClass.setCategory("boxing");
                childList.add(childClass);
                parentClass.setChildObjectList(childList);
                parentObjects.add(parentClass);
            }
        }


        MyClassExpandableAdapter myClassExpandableAdapter = new MyClassExpandableAdapter(RecommendedClasses.this,
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
                Intent h = new Intent(RecommendedClasses.this, Featured.class);
                startActivity(h);
                break;
            case R.id.nav_categories:
                Intent i = new Intent(RecommendedClasses.this, Categories.class);
                startActivity(i);
                break;
            case R.id.nav_my_classes:
                Intent j = new Intent(RecommendedClasses.this, RegisteredClasses.class);
                startActivity(j);
                break;
            case R.id.nav_search:
                Intent k = new Intent(RecommendedClasses.this, GymSearch.class);
                startActivity(k);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
