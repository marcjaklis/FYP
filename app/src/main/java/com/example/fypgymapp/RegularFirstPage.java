package com.example.fypgymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class RegularFirstPage extends AppCompatActivity {

    private Button topGyms;
    private Button featured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.featured);

        topGyms = (Button) findViewById(R.id.topgyms);
        featured = (Button) findViewById(R.id.yogabest);
    }
}
