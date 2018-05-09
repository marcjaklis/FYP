package com.example.fypgymapp;

import android.content.Intent;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
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
    public Button myCenterLocation;
    public EditText myCenterDescription;
    public Double latitude;
    public Double longitude;




    ///////////// For PlacePicker //////////////
    final int PLACE_PICKER_REQUEST = 1;
    PlacePicker.IntentBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminsignup);

        myCenterLocation = (Button) findViewById(R.id.centerLocation);
        /*myCenterDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(AdminSignUp.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });*/

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


                AdminUser adminUser = new AdminUser(UID, firstName, lastName, fitnessCenterName, 33.888630,35.495480, location, description, 0);
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

    /*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                LatLng coords = place.getLatLng();
                latitude = coords.latitude;
                longitude = coords.longitude;
                location = place.getName().toString();
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }*/


}
