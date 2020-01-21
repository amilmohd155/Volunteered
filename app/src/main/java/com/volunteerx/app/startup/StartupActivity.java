/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 8:33 PM
 *
 */

package com.volunteerx.app.startup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.volunteerx.app.R;
import com.volunteerx.app.home.HomeActivity;
import com.volunteerx.app.utils.SharedPrefManager;

public class StartupActivity extends AppCompatActivity {

    private static final String TAG = "StartupActivity";

    private final Context context = StartupActivity.this;

    //UI
    private FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_container);

        container = findViewById(R.id.container);

        setupSplashFragment();

    }

    private void setupSplashFragment() {
        Log.d(TAG, "setupSplashFragment: app starting");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .replace(container.getId(), new SplashFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        for (Fragment fragment : fragmentManager.getFragments()) {

            if (fragment.isVisible()) {
                FragmentManager childFm = fragment.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 1) {
                    childFm.popBackStack();
                    return;
                }
            }
        }

        super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}