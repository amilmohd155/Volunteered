/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/3/20 10:18 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/3/20 6:05 PM
 *
 */

package com.volunteerx.app.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;

public class NearbyFragment extends Fragment {

    public static NearbyFragment newInstance() {
        return new NearbyFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_nearby, container, false);

        return view;

    }


}
