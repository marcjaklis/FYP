package com.example.fypgymapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by Lenovo User on 5/6/2018.
 */

public class MyClassExpandableAdapter extends ExpandableRecyclerAdapter<MyClassParentViewHolder, MyClassChildViewHolder> {

    LayoutInflater mInflater;
    public final String TAG2 = "NewTag: ";

    public MyClassExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
        Log.d(TAG2, "MyClassExpandableAdapter constructor called" );
    }

    @Override
    public MyClassParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_parent_class_from_category, viewGroup, false);
        Log.d(TAG2, "MyClassExpandableAdapter onCreateParentViewHolder called" );
        return new MyClassParentViewHolder(view);

    }

    @Override
    public MyClassChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_child_class_from_category, viewGroup, false);
        Log.d(TAG2, "MyClassExpandableAdapter onCreateChildViewHolder called" );
        return new MyClassChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(MyClassParentViewHolder myClassParentViewHolder, int i, Object o) {

        ParentClass parentClass = (ParentClass) o;
        myClassParentViewHolder.title.setText(parentClass.getTitle());
        myClassParentViewHolder.dateAndTime.setText(parentClass.getDateAndTime());
        Log.d(TAG2, "MyClassExpandableAdapter onBindParentViewHolder called" );
    }

    @Override
    public void onBindChildViewHolder(MyClassChildViewHolder myClassChildViewHolder, int i, Object o) {
        ChildClass childClass = (ChildClass) o;
        myClassChildViewHolder.instructor.setText(childClass.getInstructor());
        myClassChildViewHolder.remainingSeats.setText(childClass.getRemainingSeats());

        myClassChildViewHolder.setCategory(childClass.getCategory());
        myClassChildViewHolder.setMyGymClass(childClass.getMyGymClass());

        myClassChildViewHolder.checkIfFull();
        myClassChildViewHolder.checkIfUserRegistered();
        Log.d("TempTest", "MyClassExpandableAdapter onBindParentViewHolder called" );

    }
}
