/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 7:44 PM
 *
 */

package com.volunteerx.app.MediaSwipeVew.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;


public class EmptyViewFragment extends Fragment {

    private static final String TAG = "EmptyViewFragment";

    @LayoutRes
    private int layout;

    public EmptyViewFragment() {
    }

    public static EmptyViewFragment newInstance(@LayoutRes int layout) {
        Bundle args = new Bundle();
        args.putInt("layout", layout);
        EmptyViewFragment fragment = new EmptyViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = getArguments().getInt("layout");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_empty_media, container, false);

        Log.d(TAG, "onCreateView: empty view created media error");

        return view;

    }
}
