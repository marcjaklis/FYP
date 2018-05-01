package com.example.fypgymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

public class RegularFirstPage extends AppCompatActivity {

    private Button topGyms;
    private ImageButton yogaBest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.featured);

        topGyms = (Button) findViewById(R.id.topgyms);
        yogaBest = (ImageButton) findViewById(R.id.yogabest);
        yogaBest.setBackground(null);
        Picasso.with(this).load("https://firebasestorage.googleapis.com/v0/b/fyptest2-38a21.appspot.com/o/bestofyoga.png?alt=media&token=8b19f30e-30ae-46a9-984b-b7b968eece44")
                .placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(yogaBest);
    }
}
