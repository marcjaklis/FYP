package com.example.fypgymapp;

/**
 * Created by Lenovo User on 4/11/2018.
 */

public class AdminUser {
    public String uID;
    public String firstName;
    public String lastName;
    public String fitnessCenterName;
    public String location;
    public String description;
    public int score;


    public AdminUser(){}


    public AdminUser(String uID, String firstName, String lastName, String fitnessCenterName, String location, String description, int score) {
        this.uID = uID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fitnessCenterName = fitnessCenterName;
        this.location = location;
        this.description = description;
        this.score = score;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getFitnessCenterName() {
        return fitnessCenterName;
    }

    public void setFitnessCenterName(String fitnessCenterName) {
        this.fitnessCenterName = fitnessCenterName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
