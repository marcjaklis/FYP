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

    public ParentClass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
