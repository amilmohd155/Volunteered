/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/23/20 2:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/25/19 9:32 PM
 *
 */

package com.volunteerx.app.profile.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volunteerx.app.R;

public class UserPostFragment extends Fragment {

    private static final String TAG = "UserPostFragment";

    private Context context = getContext();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Fragment Created");

        View view = inflater.inflate(R.layout.fragment_user_post, container, false);

        setupPostView();

        return view;


    }

    private void setupPostView() {

        

    }
}
