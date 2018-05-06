package com.example.fypgymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button sendData;
    private DatabaseReference mDatabase;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*

        sendData = (Button) findViewById(R.id.temp);
        key =addClass();






        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                myRef = myRef.child("Classes").child(key).child("remainingSeats");
                reserveSeat(myRef);
            }
        });
        */
    }
/*
    public String addClass ()
    {
        MyGymClass myGymClass = new MyGymClass("Friday", "Pilates", "8:30 p.m.",
                "Fitness Zone", "Joe", 10, "7:00 p.m.");
        DatabaseReference tempRef = FirebaseDatabase.getInstance().getReference();
        String key = tempRef.child("Classes").push().getKey();;
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Classes/" + key, myGymClass);

        tempRef.updateChildren(childUpdates);
        return key;
    }







    private void reserveSeat(DatabaseReference myRef) {




        //myRef=myRef.child("remainingSeats");
        myRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                double seats = mutableData.getValue(Double.class);
                if (seats==0)
                {
                    // Display Error Message
                    return Transaction.success(mutableData);
                }
                seats--;
                mutableData.setValue(seats);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.d("Ryan: ", "postTransaction:onComplete:" + databaseError);
            }
        });


    }
*/
}
