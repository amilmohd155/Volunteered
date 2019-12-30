/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.volunteerx.app.R;

import java.util.List;

public class DirectorySpinnerAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int mResource;
    private List<String> item;
    private int textViewResourceId;


    public DirectorySpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
        this.mContext = context;
        this.mResource = resource;
        this.item = objects;
        this.textViewResourceId = textViewResourceId;
    }


    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view;

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.layout_spinner_dropdown, null);

        TextView textView = (TextView) view.findViewById(R.id.list_item_dropdown);
        textView.setText(item.get(position));

        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();

        int widthDP = displayMetrics.widthPixels;

        view.setMinimumWidth(widthDP);

        return view;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view;

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(mResource, null);

        TextView textView = (TextView) view.findViewById(textViewResourceId);
        textView.setText(item.get(position));

        return view;
    }
}
