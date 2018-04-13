package com.example.fypgymapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo User on 10/15/2017.
 */

public class MyGymClass {

    public String classDays;
    public String classType;
    public String endTime;
    public String facility;
    public String instructor;
    public double remainingSeats;
    public String startTime;

    public MyGymClass(){}

    public MyGymClass(String classDays, String classType, String endTime, String facility,
                      String instructor, double remainingSeats, String startTime)
    {
        this.startTime=startTime;
        this.endTime=endTime;
        this.instructor=instructor;
        this.classType=classType;
        this.facility=facility;
        this.remainingSeats=remainingSeats;
        this.classDays=classDays;
    }


    public String getClassDays() {
        return classDays;
    }

    public void setClassDays(String classDays) {
        this.classDays = classDays;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public double getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(double remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
