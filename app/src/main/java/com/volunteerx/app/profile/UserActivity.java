/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 7:58 PM
 *
 */

package com.volunteerx.app.profile;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.volunteerx.app.utils.RoundedCorner;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.volunteerx.app.R;
import com.volunteerx.app.fragments.EditProfileFragment;
import com.volunteerx.app.fragments.FollowStaticFragment;
import com.volunteerx.app.utils.BottomNavigationViewHelper;
import com.volunteerx.app.utils.WrapContentStatePagerAdapter;
import com.volunteerx.app.utils.WrapContentViewPager;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";

    private final Context mContext = UserActivity.this;
    private final int curveRadius = 30;

    //Variables
    public static int selectedPosition = -1;

    //UI
    private WrapContentViewPager viewPager;
    private Toolbar toolbar;
    private LinearLayout llFollowing, llFollowers;
    private Button editProfileBtn;
    private ImageView ivCoverPhoto;
    private LinearLayout llContactInfo;

    public UserActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        llFollowers =  findViewById(R.id.followers);
        llFollowing = findViewById(R.id.following);
        llContactInfo = findViewById(R.id.ll_contact_info);
        editProfileBtn = findViewById(R.id.edit_profile);
        ivCoverPhoto = findViewById(R.id.cover_photo);

        toolbar = findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);


        RoundedCorner.setRoundedCorner(ivCoverPhoto, curveRadius, RoundedCorner.ROUNDED_TOP);

        setupBottomNavigationView();
        setupFragments();
        setupAccountChanging();
        setupFollowStatics();
        setupEditFragment();

    }


    /**
     * gateway into edit profile fragment
     */
    private void setupEditFragment() {

        editProfileBtn.setOnClickListener((View v) -> {
                Fragment fragment = new EditProfileFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.relLayout, fragment, fragment.getClass().getSimpleName())
                            .addToBackStack(null)
                            .commit();
        });

    }

    /**
     * handles all llFollowing and llFollowers work
     */
    private void setupFollowStatics() {


        llFollowing.setOnClickListener((View v) -> {
                Toast.makeText(mContext, "Following clicked",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("tabSelector", 1);
                Fragment fragment = new FollowStaticFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.relLayout, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
        });

        llFollowers.setOnClickListener((View view) -> {
                Toast.makeText(mContext, "Followers clicked",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("tabSelector", 0);
                Fragment fragment = new FollowStaticFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.relLayout, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();

        });

    }


    /**
     * used to change account to user to organisation
     */
    private void setupAccountChanging() {

        final RelativeLayout rely = findViewById(R.id.user_dropdown);

        rely.setOnClickListener(view -> {

            final AccountBottomSheetFragment fragment = AccountBottomSheetFragment.newInstance();
            fragment.show(getSupportFragmentManager(), fragment.getTag());

        });

    }

    /**
     * Setting up fragments(tabs)
     */
    private void setupFragments() {

        viewPager = findViewById(R.id.user_tab_container);
        WrapContentStatePagerAdapter statePagerAdapter = new WrapContentStatePagerAdapter(getSupportFragmentManager());
        statePagerAdapter.addFragment(new UserPostFragment(), getResources().getString(R.string.tab_post));
        statePagerAdapter.addFragment(new UserActivitiesFragment(), getResources().getString(R.string.tab_activities));

        viewPager.setAdapter(statePagerAdapter);

        final TabLayout tabLayout = findViewById(R.id.tabs_user);
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


    /**
     * Setting up BottomNavigationView
     */
    private void setupBottomNavigationView() {

        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        FloatingActionButton pingFab = findViewById(R.id.ping_fab);

        bottomNavigationViewEx.setCurrentItem(4);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx, pingFab);

    }

}
