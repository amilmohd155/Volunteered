/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/11/20 12:28 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/11/20 12:28 AM
 *
 */

package com.volunteerx.app.profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.volunteerx.app.R;
import com.volunteerx.app.fragments.EditProfileFragment;
import com.volunteerx.app.profile.fragment.AccountBottomSheetFragment;
import com.volunteerx.app.profile.fragment.FollowStaticFragment;
import com.volunteerx.app.profile.fragment.UserActivitiesFragment;
import com.volunteerx.app.profile.fragment.UserCharacterBSFragment;
import com.volunteerx.app.profile.fragment.UserContactBSFragment;
import com.volunteerx.app.profile.fragment.UserPostFragment;
import com.volunteerx.app.utils.BottomNavigationViewHelper;
import com.volunteerx.app.utils.SectionsStatePagerAdapter;
import com.volunteerx.app.utils.WrapContentStatePagerAdapter;
import com.volunteerx.app.utils.WrapContentViewPager;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "isUserFragment";
    private static final String ARG_PARAM2 = "param2";



    //Variables
    private boolean isUserFragment; //true :: user false:: profile


    //UI
    private ViewPager viewPager;
    private Toolbar toolbar;
    private CircleImageView civProfile;
    private RelativeLayout rely;
    private TabLayout tabLayout;
    private Button genButton, btnContact;


    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment UserFragment.
     */
    public static UserFragment newInstance(boolean isUserFragment) {
        UserFragment fragment = new UserFragment();

        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isUserFragment);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isUserFragment = getArguments().getBoolean(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!isUserFragment) {
            return inflater.inflate(R.layout.fragment_profile, container, false); //profile fragment
        }
        return inflater.inflate(R.layout.fragment_user, container, false); //user fragment

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        civProfile = view.findViewById(R.id.civ_profile_picture);
        rely = view.findViewById(R.id.user_dropdown);
        viewPager = view.findViewById(R.id.user_tab_container);
        tabLayout = view.findViewById(R.id.tabs_user);
        genButton = view.findViewById(R.id.follow_btn);
        btnContact = view.findViewById(R.id.contact_btn);
        toolbar = view.findViewById(R.id.toolbar);

        view.findViewById(R.id.ll_followers).setOnClickListener(this);
        view.findViewById(R.id.ll_following).setOnClickListener(this);
        view.findViewById(R.id.iv_character).setOnClickListener(this);
        genButton.setOnClickListener(this);
        btnContact.setOnClickListener(this);


        if (isUserFragment) {
            setupBottomNavigationView(view);
            setupUserView(view);

        } else setupProfileView(view);

        setupViewPager();

    }

    /**
     * used to setup user views
     * @param view the root view
     */
    private void setupUserView(View view) {

        view.findViewById(R.id.user_dropdown).setOnClickListener(this);

        genButton.setText(getContext().getString(R.string.edit_profile));


    }

    /**
     * used to setup profile views
     * @param view the root view
     */
    private void setupProfileView(View view) {

        //if following
        genButton.setText(getContext().getString(R.string.follow));

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);


        final AppBarLayout appBarLayout = view.findViewById(R.id.app_bar_layout);

        //Showing user name on collapsed and swipe refresh
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    swipeRefreshLayout.setOnChildScrollUpCallback((parent, child) -> false);
                }

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setTitle("Title");
                    swipeRefreshLayout.setOnChildScrollUpCallback((parent, child) -> true);
                    isShow = true;
                } else if(isShow) {
                    toolbar.setTitle(null);//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });


        toolbar.setNavigationOnClickListener(view1 -> {
            if (getActivity()!=null) {
                getActivity().onBackPressed();
            }
        });

    }

    /**
     * Setting up fragments(tabs)
     */
    private void setupViewPager() {

        SectionsStatePagerAdapter statePagerAdapter = new SectionsStatePagerAdapter(getChildFragmentManager());
        statePagerAdapter.addFragment(UserPostFragment.newInstance(), getResources().getString(R.string.tab_post));
        statePagerAdapter.addFragment(UserActivitiesFragment.newInstance(), getResources().getString(R.string.tab_activities));

        viewPager.setAdapter(statePagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.tab_post);
        tabLayout.getTabAt(1).setText(R.string.tab_activities);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_followers:
                replaceFragment(FollowStaticFragment.newInstance(0), "FollowStaticFragment", getFragmentManager());
                break;
            case R.id.ll_following:
                replaceFragment(FollowStaticFragment.newInstance(1), "FollowStaticFragment", getFragmentManager());
                break;
            case R.id.follow_btn:
                if (isUserFragment)
                replaceFragment(new EditProfileFragment(), "EditProfileFragment", getFragmentManager());
                //else follow option
                break;
            case R.id.user_dropdown: //used to change account to user to organisation
                final AccountBottomSheetFragment fragment = AccountBottomSheetFragment.newInstance();
                fragment.show(getChildFragmentManager(), fragment.getTag());
                break;
            case R.id.iv_character:
                //show character dialog
                final UserCharacterBSFragment uCFragment = UserCharacterBSFragment.newInstance();
                uCFragment.show(getChildFragmentManager(), uCFragment.getTag());
                break;
            case R.id.contact_btn:
                //show contact bottom fragment
                final UserContactBSFragment ucFragment =  UserContactBSFragment.newInstance();
                ucFragment.show(getChildFragmentManager(), ucFragment.getTag());
                break;
        }
    }

    /**
     * Setting up BottomNavigationView
     * @param view
     */
    private void setupBottomNavigationView(View view) {


        BottomNavigationViewEx bottomNavigationViewEx = view.findViewById(R.id.bottomNavViewBar);
        FloatingActionButton pingFab = view.findViewById(R.id.ping_fab);

        bottomNavigationViewEx.setCurrentItem(4);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(getContext(), bottomNavigationViewEx, pingFab, getFragmentManager());

    }
}
