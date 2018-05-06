package com.example.fypgymapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

/**
 * Created by Lenovo User on 5/6/2018.
 */

public class MyClassChildViewHolder extends ChildViewHolder {

    public TextView instructor;
    public TextView remainingSeats;
    public Button reserve;


    public MyClassChildViewHolder(View itemView) {
        super(itemView);

        instructor = (TextView) itemView.findViewById(R.id.instructor);
        remainingSeats = (TextView) itemView.findViewById(R.id.remainingseats);
        reserve = (Button) itemView.findViewById(R.id.reserve);

    }
}
