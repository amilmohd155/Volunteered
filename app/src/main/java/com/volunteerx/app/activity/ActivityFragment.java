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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.volunteerx.app.R;
import com.volunteerx.app.activity.fragment.ActAboutFragment;
import com.volunteerx.app.profile.UserFragment;
import com.volunteerx.app.utils.IOonBackPressed;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;

public class ActivityFragment extends Fragment implements View.OnClickListener, IOonBackPressed {

    private static final String TAG = "ActivityFragment";

    //UI
    private DrawerLayout drawerLayout;

    private boolean mSlideState;

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
        return inflater.inflate(R.layout.fragment_act, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drawerLayout = view.findViewById(R.id.drawable_layout);
        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);

        Toolbar toolbar = view.findViewById(R.id.toolbar);


        view.findViewById(R.id.cv_about).setOnClickListener(this);
        view.findViewById(R.id.iv_more).setOnClickListener(this);
        view.findViewById(R.id.ll_creator).setOnClickListener(this);
        headerView.findViewById(R.id.ib_header_nav).setOnClickListener(this);


        toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null) getActivity().onBackPressed();
            else if (getParentFragment() != null) {
                //
            }
        });

        setDrawerLayout();

    }

    private void setDrawerLayout() {

        drawerLayout.addDrawerListener(new ActionBarDrawerToggle(getActivity(), drawerLayout, 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mSlideState = false;
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mSlideState = true;
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
                replaceFragment(ActAboutFragment.newInstance(), "ActAboutFragment", getFragmentManager());
                break;
            case R.id.iv_more:
            case R.id.ib_header_nav:
                if (mSlideState) drawerLayout.closeDrawer(GravityCompat.END);
                else drawerLayout.openDrawer(GravityCompat.END);
                break;
            case R.id.ll_creator:
                replaceFragment(UserFragment.newInstance(false), "UserFragment", getFragmentManager());
        }
    }

//    private void replaceFragment(Fragment fragment, String fragmentName) {
//
//        FragmentManager fragmentManager = getFragmentManager();
//
//        if (fragmentManager != null) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.container,fragment , fragmentName)
//                    .addToBackStack(fragmentName)
//                    .commit();
//        }else {
//            throw new RuntimeException("FragmentManager is null");
//        }
//
//    }

    @Override
    public boolean onBackPressed() {
        if (mSlideState) {
            drawerLayout.closeDrawer(GravityCompat.END);
            return true;
        }
        return false;
    }
}
