package com.example.fypgymapp;

import android.graphics.Color;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Lenovo User on 5/6/2018.
 */


public class MyClassChildViewHolder extends ChildViewHolder {

    public TextView instructor;
    public TextView remainingSeats;
    public Button reserve;
    public MyGymClass myGymClass;
    final public String TAG="Ryan";
    final public String TAG2="Hello";


    public MyClassChildViewHolder(View itemView) {
        super(itemView);

        instructor = (TextView) itemView.findViewById(R.id.instructor);
        remainingSeats = (TextView) itemView.findViewById(R.id.remainingseats);
        reserve = (Button) itemView.findViewById(R.id.reserve);

        Log.d(TAG2, "ViewHolder Called");

        reserve.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                uploadClassToDatabase();
                Log.d(TAG2, "Instructor is: "+myGymClass.instructor);
            }
        });

    }

    public void uploadClassToDatabase()
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("userClasses");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null)
        {
            Log.d(TAG, "User is null");
            return;
        }
        String uid = user.getUid();
        databaseReference.child(uid).push().setValue(myGymClass);
        Log.d(TAG,"Uploaded User");
    }

    public void checkIfUserRegistered()
    {

        Log.d(TAG2,"In checkIfUserRegistered");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("userClasses");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null)
        {
            Log.d(TAG, "User is null in CheckIfRegistered");
            return;
        }
        String uid = user.getUid();
        databaseReference = databaseReference.child(uid);
        databaseReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren())
                        {
                            Log.d(TAG2,"Checking First Class");
                            MyGymClass temp = snapshot.getValue(MyGymClass.class);
                            if (temp.isEqualTo(myGymClass)) {
                                Log.d(TAG2,"User Registered Class");
                                reserve.setText("Already Reserved");
                                reserve.setBackgroundColor(Color.GRAY);
                                reserve.setOnClickListener(null);
                                Log.d(TAG2,"Button Changed");
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }

    public MyGymClass getMyGymClass() {
        return myGymClass;
    }

    public void setMyGymClass(MyGymClass myGymClass) {
        this.myGymClass = myGymClass;
    }
}
