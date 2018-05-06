package com.example.fypgymapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo User on 10/15/2017.
 */

public class MyGymClass {

    public String classDays;
    public String facility;
    public String instructor;
    public int remainingSeats;
    public String time;
    public String title;



    public MyGymClass(){}

    public MyGymClass(String classDays, String facility, String instructor, int remainingSeats, String time, String title) {
        this.classDays = classDays;
        this.facility = facility;
        this.instructor = instructor;
        this.remainingSeats = remainingSeats;
        this.time = time;
        this.title = title;
    }

    public String getClassDays() {
        return classDays;
    }

    public void setClassDays(String classDays) {
        this.classDays = classDays;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
