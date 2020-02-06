/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 8:03 PM
 *
 */

package com.volunteerx.app.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.IOonBackPressed;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

//    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        Log.d(TAG, "onCreate: Starting");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(), "MainFragment")
                .commit();

//        viewPager = findViewById(R.id.container);
//
//        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
//
//        Runnable runnable = () -> {
//
//            adapter.addFragment(NearbyFragment.newInstance(), "NearbyFragment");
//            adapter.addFragment(HomeFragment.newInstance(), "HomeFragment");
//            adapter.addFragment(ForumsFragment.newInstance(), "ForumsFragment");
//
//        };
//
//        runnable.run();
//
//        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(1);


    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (!(fragment instanceof IOonBackPressed) || !((IOonBackPressed) fragment).onBackPressed()){
            super.onBackPressed();
        }
        else return;

//        if (viewPager.getCurrentItem() == 1) {
//            Log.d(TAG, "onBackPressed: super");
//            super.onBackPressed();
//        }else {
//            viewPager.setCurrentItem(1);
//        }

    }

//    @Override
//    public void buttonClick(int type) {
//        switch (type) {
//            case NEARBY_VIEW: viewPager.setCurrentItem(0);
//                break;
//            case FORUM_VIEW:  viewPager.setCurrentItem(2);
//                break;
//            case HOME_VIEW: viewPager.setCurrentItem(1);
//        }
//    }

}
