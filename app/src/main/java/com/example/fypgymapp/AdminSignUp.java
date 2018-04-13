package com.example.fypgymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminSignUp extends AppCompatActivity {

    private Button adminButton;
    public String fitnessCenterName;
    public String location;
    public String description;
    public String UID;
    public String firstName;
    public String lastName;
    public EditText myCenterName;
    public EditText myCenterLocation;
    public EditText myCenterDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminsignup);

        adminButton = (Button) findViewById(R.id.adminButton);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///////////////  Fitness Center Name //////////////
                myCenterName = (EditText) findViewById(R.id.centerName);
                fitnessCenterName = myCenterName.getText().toString();
                if(TextUtils.isEmpty(fitnessCenterName)) {
                    myCenterName.setError("Required");
                    return;
                }

                ////////////// Location ///////////////
                myCenterLocation = (EditText) findViewById(R.id.centerLocation);
                location = myCenterLocation.getText().toString();
                if(TextUtils.isEmpty(location)) {
                    myCenterLocation.setError("Required");
                    return;
                }

                ////////////// Description ///////////////
                myCenterDescription = (EditText) findViewById(R.id.centerDescription);
                description = myCenterDescription.getText().toString();



                ////////////// Get info from previous activity /////////////
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    UID = extras.getString("ID");
                    firstName = extras.getString("FirstName");
                    lastName = extras.getString("LastName");
                }

                ////////////// Upload to Firebase ///////
                AdminUser adminUser = new AdminUser(UID, firstName, lastName, fitnessCenterName, location, description);
                DatabaseReference databaseReference = FirebaseDatabase
                        .getInstance()
                        .getReference("adminUsers");
                databaseReference.push().setValue(adminUser);
                Toast.makeText(AdminSignUp.this, "User Saved",
                        Toast.LENGTH_SHORT).show();

                Intent i = new Intent(AdminSignUp.this, WelcomePage.class);
                startActivity(i);
            }
        });
    }


}
