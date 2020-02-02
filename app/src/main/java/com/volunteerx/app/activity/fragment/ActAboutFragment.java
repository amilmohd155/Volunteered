/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/30/20 10:18 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/30/20 10:18 PM
 *
 */

package com.volunteerx.app.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;

public class ActAboutFragment extends Fragment {

    public ActAboutFragment() {
    }

    public static ActAboutFragment newInstance() {

        Bundle args = new Bundle();

        ActAboutFragment fragment = new ActAboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_act_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
