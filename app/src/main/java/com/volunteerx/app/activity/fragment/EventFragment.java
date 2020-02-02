/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/30/20 10:17 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/30/20 10:17 PM
 *
 */

package com.volunteerx.app.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class EventFragment extends Fragment {

    public EventFragment() {
    }

    public static EventFragment newInstance() {

        Bundle args = new Bundle();

        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }



}
