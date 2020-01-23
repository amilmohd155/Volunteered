/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/23/20 2:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/23/20 2:16 PM
 *
 */

package com.volunteerx.app.profile.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.SectionsPagerAdapter;

public class FollowStaticFragment extends Fragment{

    private static final String TAG = "FollowStaticFragment";
    private static final String param = "tabSelector";

    //variable
    private int tabSelector;

    //widgets
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView topBarTitle;

    public FollowStaticFragment() {
    }

    public static FollowStaticFragment newInstance(int tabSelector) {

        FollowStaticFragment fragment = new FollowStaticFragment();

        Bundle args = new Bundle();
        args.putInt(param, tabSelector);

        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            tabSelector = getArguments().getInt(param, 0);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_follow_static, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.follow_viewpager);
        tabLayout = view.findViewById(R.id.follow_tabs);

        final Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(view1 -> getActivity().onBackPressed());

        setupViewpager();

    }

    /**
     * Sets up the viewpager and nested fragment
     */
    private void setupViewpager() {

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        sectionsPagerAdapter.addFragment(new FollowersChildFragment());
        sectionsPagerAdapter.addFragment(new FollowingChildFragment());
        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Followers");
        tabLayout.getTabAt(1).setText("Following");

        tabLayout.getTabAt(tabSelector).select();

    }
}
