package com.example.fypgymapp;

import android.widget.Button;

import java.sql.Time;

/**
 * Created by Lenovo User on 5/6/2018.
 */

public class ChildClass {

    private String instructor;
    private String remainingSeats;

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

}