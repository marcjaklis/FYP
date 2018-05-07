package com.example.fypgymapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Lenovo User on 5/7/2018.
 */

public class CustomAdapterClasses extends ArrayAdapter<MyCategory> {

    CustomAdapterClasses(Context context, MyCategory[] resource) {
        super(context, R.layout.view_registered_classes, resource);
    }

    public final String TAG = "Cust: ";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.view_registered_classes, parent, false);


        TextView className = (TextView) customView.findViewById(R.id.className);
        TextView classTime = (TextView) customView.findViewById(R.id.classTime);
        TextView classInstructor = (TextView) customView.findViewById(R.id.classInstructor);

        final MyCategory current = getItem(position);

        String name=current.name;
        final String time = current.url;
        final String instructor = current.urlBigPicture;


        className.setText(name);
        classTime.setText(time);
        classInstructor.setText(instructor);

        return customView;
    }

}
