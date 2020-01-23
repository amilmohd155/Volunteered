/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/23/20 12:05 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/23/20 12:05 PM
 *
 */

package com.volunteerx.app.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.volunteerx.app.R;
import com.volunteerx.app.utils.WrapContentStatePagerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private LinearLayout llFollowers, llFollowing, llContactInfo;
    private Button followBtn;
    private ImageView ivCoverPhoto;
    private CircleImageView civProfile;
    private RecyclerView rely;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ProfileFragment() {
    }


    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        llFollowers =  view.findViewById(R.id.ll_followers);
        llFollowing = view.findViewById(R.id.ll_following);
        llContactInfo = view.findViewById(R.id.ll_contact_info);
        followBtn = view.findViewById(R.id.follow_btn);
        ivCoverPhoto = view.findViewById(R.id.iv_cover_photo);
        civProfile = view.findViewById(R.id.civ_profile_picture);
        rely = view.findViewById(R.id.user_dropdown);
        viewPager = view.findViewById(R.id.user_tab_container);
        tabLayout = view.findViewById(R.id.tabs_user);

        llFollowers.setOnClickListener(this);
        llFollowing.setOnClickListener(this);

        toolbar.setTitle(R.string.john_doe);


        setupTabs();


    }


    /**
     * Setting up fragments(tabs)
     */
    private void setupTabs() {

        WrapContentStatePagerAdapter statePagerAdapter = new WrapContentStatePagerAdapter(getChildFragmentManager());
        statePagerAdapter.addFragment(new UserPostFragment(), getResources().getString(R.string.tab_post));
        statePagerAdapter.addFragment(new UserActivitiesFragment(), getResources().getString(R.string.tab_activities));

        viewPager.setAdapter(statePagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.tab_post);
        tabLayout.getTabAt(1).setText(R.string.tab_activities);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            // Animate fade in and out with transition and scaling

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() != 0) {
//                    linearLayout1.setVisibility(View.GONE);
                    llContactInfo.setVisibility(View.GONE);
                }
                else {
//                    linearLayout1.setVisibility(View.VISIBLE);
                    llContactInfo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_following:
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.relLayout, FollowStaticFragment.newInstance(1))
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.ll_followers:
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.relLayout, FollowStaticFragment.newInstance(0))
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
