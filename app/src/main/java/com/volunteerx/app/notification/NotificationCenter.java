/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/3/20 6:47 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/3/20 6:47 PM
 *
 */

package com.volunteerx.app.notification;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.volunteerx.app.R;
import com.volunteerx.app.utils.BottomNavigationViewHelper;
import com.volunteerx.app.utils.SectionsPagerAdapter;

public class NotificationCenter extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public NotificationCenter() {
    }

    public static NotificationCenter newInstance() {

        Bundle args = new Bundle();

        NotificationCenter fragment = new NotificationCenter();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tabs);

        setupViewPager();
        setupBottomNavigationView(view);
    }

    private void setupViewPager() {

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        sectionsPagerAdapter.addFragment(new NotificationFragment());
        sectionsPagerAdapter.addFragment(new RequestFragment());
        viewPager.setAdapter(sectionsPagerAdapter);


        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.tab_notification);
        tabLayout.getTabAt(1).setText(R.string.tab_request);

    }

    /**
     * Setting up BottomNavigationView
     * @param view
     */
    private void setupBottomNavigationView(View view) {

        BottomNavigationViewEx bottomNavigationViewEx = view.findViewById(R.id.bottomNavViewBar);
        FloatingActionButton pingFab = view.findViewById(R.id.ping_fab);

        bottomNavigationViewEx.setCurrentItem(3);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(getContext(), bottomNavigationViewEx, pingFab, getFragmentManager());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
