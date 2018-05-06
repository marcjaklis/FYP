package com.example.fypgymapp;

import android.widget.Button;

import java.sql.Time;

/**
 * Created by Lenovo User on 5/6/2018.
 */

public class ChildClass {

    private String time;
    private String instructor;
    private String days;
    private int remainingSeats;
    private Button reserve;

    public ChildClass(String time, String instructor, String days, int remainingSeats) {
        this.time = time;
        this.instructor = instructor;
        this.days = days;
        this.remainingSeats = remainingSeats;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public Button getReserve() {
        return reserve;
    }

    public void setReserve(Button reserve) {
        this.reserve = reserve;
    }
}
