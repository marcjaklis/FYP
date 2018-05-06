package com.example.fypgymapp;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

/**
 * Created by Lenovo User on 5/6/2018.
 */

public class MyClassParentViewHolder extends ParentViewHolder {

    public TextView title;
    public ImageButton dropDownArrow;

    public MyClassParentViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.parent_list_item_crime_title_text_view);
        dropDownArrow = (ImageButton) itemView.findViewById(R.id.parent_list_item_expand_arrow);
    }
}
