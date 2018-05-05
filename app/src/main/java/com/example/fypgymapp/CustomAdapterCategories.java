package com.example.fypgymapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Lenovo User on 5/5/2018.
 */

public class CustomAdapterCategories extends ArrayAdapter<MyCategory> {

    CustomAdapterCategories(Context context, MyCategory[] resource) {
        super(context, R.layout.view_categories, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.view_categories, parent, false);



        ImageButton imageButton = (ImageButton) customView.findViewById(R.id.imagebutton2);

        MyCategory current = getItem(position);

        String url=current.url;

        imageButton.setBackground(null);
        Picasso.with(this.getContext()).load(url)
                .placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageButton);

        return customView;
    }
}
