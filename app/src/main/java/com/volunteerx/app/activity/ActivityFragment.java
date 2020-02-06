/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/30/20 1:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/30/20 1:37 PM
 *
 */

package com.volunteerx.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.volunteerx.app.R;
import com.volunteerx.app.activity.fragment.ActAboutFragment;
import com.volunteerx.app.utils.WrapContentViewPager;

import java.sql.RowId;

public class ActivityFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ActivityFragment";

    //UI

    public ActivityFragment() {
    }

    public static ActivityFragment newInstance() {

        Bundle args = new Bundle();

        ActivityFragment fragment = new ActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_act, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        view.findViewById(R.id.cv_about).setOnClickListener(this);


        toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null) getActivity().onBackPressed();
            else if (getParentFragment() != null) {
                //
            }
        });

    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_about:
                replaceFragment(ActAboutFragment.newInstance(), "ActAboutFragment");
                break;

        }
    }

    private void replaceFragment(Fragment fragment, String fragmentName) {

        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container,fragment , fragmentName)
                    .addToBackStack(fragmentName)
                    .commit();
        }else {
            throw new RuntimeException("FragmentManager is null");
        }

    }
}
