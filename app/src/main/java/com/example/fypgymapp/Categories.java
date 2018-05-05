package com.example.fypgymapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Categories extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;



    private final String TAG = "Ryan";
    DatabaseReference databaseReference;
    public ArrayList<MyCategory> myCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);








        ///////// Generate list of Categories /////////

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Categories");
        myCategories = new ArrayList<MyCategory>();
        databaseReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of categories in datasnapshot
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            String name;
                            name = snapshot.child("name").getValue().toString();
                            Log.d(TAG, "Name is: " +name);
                            String url;
                            url = snapshot.child("image").getValue().toString();
                            Log.d(TAG, "Link is: " +url);

                            MyCategory temp = new MyCategory(url, name);
                            myCategories.add(temp);
                        }
                        ///////////// Code to convert ArrayList to Array of MyCategory
                        assert myCategories != null;
                        MyCategory[] list = new MyCategory[myCategories.size()];
                        Log.i(TAG, "Size of Array is: " + Integer.toString(myCategories.size()));
                        myCategories.toArray(list);


                        /// Now generate the list of Imagebuttons
                        ListAdapter myAdapter = new CustomAdapterCategories(Categories.this,list);
                        android.widget.ListView myList = (android.widget.ListView) findViewById(R.id.categoriesList);
                        myList.setAdapter(myAdapter);

                        myList.setOnItemClickListener(
                                new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        MyCategory temporary = (MyCategory) parent.getItemAtPosition(position);
                                        String name = temporary.name;
                                        Toast.makeText(Categories.this, name, Toast.LENGTH_LONG).show();
                                    }
                                }
                        );



                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });








        /////////////////////////////////////////////////
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
                Intent h = new Intent(Categories.this, Featured.class);
                startActivity(h);
                break;
            case R.id.nav_categories:
                Intent i = new Intent(Categories.this, Categories.class);
                startActivity(i);
                break;

        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
