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

    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (!(fragment instanceof IOonBackPressed) || !((IOonBackPressed) fragment).onBackPressed()){
            super.onBackPressed();
        }
        else return;

    }

}
