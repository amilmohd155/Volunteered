/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 8:06 PM
 *
 */

package com.volunteerx.app.startup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.home.HomeActivity;
import com.volunteerx.app.utils.SharedPrefManager;
import com.volunteerx.app.utils.StatusColorClass;

import static com.volunteerx.app.utils.PreferenceCheckerClass.restorePrefsData;
import static com.volunteerx.app.utils.StatusColorClass.DARK_STATUS_BAR_ICON;

public class SplashFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final ViewGroup mContainer = container;

        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        StatusColorClass.setStatusColor(getActivity(), getContext().getColor(R.color.colorSplash), DARK_STATUS_BAR_ICON);

        view.setBackgroundColor(getResources().getColor(R.color.colorSplash, getContext().getTheme()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SharedReference

//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(((ViewGroup) getView().getParent()).getId(), new StartupFragment())
//                .commit();

    }
}
