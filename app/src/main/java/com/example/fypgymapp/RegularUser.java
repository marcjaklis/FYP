package com.example.fypgymapp;

/**
 * Created by Lenovo User on 11/22/2017.
 */

public class RegularUser {

    public String uID;
    public String firstName;
    public String lastName;


    public RegularUser(){}

    public RegularUser(String uID, String firstName, String lastName) {
        this.uID = uID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
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
