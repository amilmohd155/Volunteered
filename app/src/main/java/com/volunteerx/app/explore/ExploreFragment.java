/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 9:48 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 9:48 PM
 *
 */

package com.volunteerx.app.explore;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.volunteerx.app.R;
import com.volunteerx.app.explore.fragment.ExRecommendedFragment;
import com.volunteerx.app.utils.BottomNavigationViewHelper;
import com.volunteerx.app.utils.ClickListener;
import com.volunteerx.app.utils.NonSwipeableViewPager;
import com.volunteerx.app.utils.SectionsStatePagerAdapter;

public class ExploreFragment extends Fragment {

    private static final String TAG = "ExploreFragment";

    //widgets
    private FloatingActionButton pingFab;
    private BottomNavigationViewEx bottomNavigationViewEx;
    private TabLayout tabLayout;
    private NonSwipeableViewPager viewPager;
    private ClickListener listener;

    public ExploreFragment() {
    }

    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationViewEx = view.findViewById(R.id.bottomNavViewBar);
        pingFab = view.findViewById(R.id.ping_fab);
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);


        setupBottomNavigationView();
        setupTabs();

    }


    private void setupTabs() {

        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getChildFragmentManager());
        adapter.addFragment(ExRecommendedFragment.newInstance(), getString(R.string.fragment_act_recommended));
        adapter.addFragment(ExRecommendedFragment.newInstance(), getString(R.string.fragment_act_recommended));
        adapter.addFragment(ExRecommendedFragment.newInstance(), getString(R.string.fragment_act_recommended));
        adapter.addFragment(ExRecommendedFragment.newInstance(), getString(R.string.fragment_act_recommended));
        adapter.addFragment(ExRecommendedFragment.newInstance(), getString(R.string.fragment_act_recommended));
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(R.string.tab_for_you);
        tabLayout.getTabAt(1).setText(R.string.tab_top);
        tabLayout.getTabAt(2).setText(R.string.tab_people);
        tabLayout.getTabAt(3).setText(R.string.tab_organisation);
        tabLayout.getTabAt(4).setText(R.string.tab_character);

    }


    /**
     * Setting up BottomNavigationView
     */
    private void setupBottomNavigationView() {

        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

        bottomNavigationViewEx.setCurrentItem(1);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(getContext(), bottomNavigationViewEx, pingFab);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (getActivity() instanceof ClickListener) {
            listener = (ClickListener) getActivity();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ClickListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}

