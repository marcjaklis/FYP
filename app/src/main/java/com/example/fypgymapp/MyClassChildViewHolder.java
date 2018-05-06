package com.example.fypgymapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

/**
 * Created by Lenovo User on 5/6/2018.
 */

public class MyClassChildViewHolder extends ChildViewHolder {

    public TextView time;
    public TextView instructor;
    public TextView days;
    public TextView remainingSeats;
    public Button reserve;


    public MyClassChildViewHolder(View itemView) {
        super(itemView);

        time = (TextView) itemView.findViewById(R.id.time);
        instructor = (TextView) itemView.findViewById(R.id.instructor);
        days = (TextView) itemView.findViewById(R.id.days);
        remainingSeats = (TextView) itemView.findViewById(R.id.remainingseats);
        reserve = (Button) itemView.findViewById(R.id.reserve);

    }
}
