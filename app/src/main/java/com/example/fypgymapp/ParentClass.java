package com.example.fypgymapp;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by Lenovo User on 5/6/2018.
 */

public class ParentClass implements ParentObject {
    /* Create an instance variable for your list of children */
    private List<Object> mChildrenList;
    private String title;
    public String dateAndTime;

    public ParentClass(String title, String dateAndTime) {
        this.title = title;
        this.dateAndTime = dateAndTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    /**
     * Your constructor and any other accessor
     *  methods should go here.
     */



    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }
}
