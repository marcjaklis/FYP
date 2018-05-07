package com.example.fypgymapp;

import android.widget.Button;

import java.sql.Time;

/**
 * Created by Lenovo User on 5/6/2018.
 */

public class ChildClass {

    private String instructor;
    private String remainingSeats;
    public MyGymClass myGymClass;
    public String category;

    public ChildClass(String instructor, String remainingSeats) {
        this.instructor = instructor;
        this.remainingSeats = remainingSeats;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(String remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public MyGymClass getMyGymClass() {
        return myGymClass;
    }

    public void setMyGymClass(MyGymClass myGymClass) {
        this.myGymClass = myGymClass;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
