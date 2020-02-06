/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.utils;

import android.content.Context;
import android.content.Intent;

import android.util.Log;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.volunteerx.app.explore.ExploreFragment;
import com.volunteerx.app.home.HomeActivity;
import com.volunteerx.app.R;
import com.volunteerx.app.notification.NotificationCenter;
import com.volunteerx.app.ping.PingFragment;
import com.volunteerx.app.profile.UserActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {

        Log.d(TAG, "setupBottomNavigationView: Setting up Bottom Navigation View");

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);

    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx viewEx, FloatingActionButton pingFab, FragmentManager fragmentManager) {

        Log.d(TAG, "enableNavigation: Enabling Navigation");
//        pingFab.bringToFront();

        pingFab.setOnClickListener(view -> {
            replaceFragment(PingFragment.newInstance(), "PingFragment", fragmentManager);
        });

        viewEx.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.ic_home:
                    Intent intentHome = new Intent(context, HomeActivity.class);
                    context.startActivity(intentHome);

                    break;

                case R.id.ic_search:
//                    Intent intentLocation = new Intent(context, ExploreActivity.class);
//                    context.startActivity(intentLocation);

                    replaceFragment(ExploreFragment.newInstance(), "ExploreFragment", fragmentManager);

                    break;

                case R.id.ic_notification:
//                    Intent intentNotifications = new Intent(context, NotificationActivity.class);
//                    context.startActivity(intentNotifications);

                    replaceFragment(NotificationCenter.newInstance(), "NotificationCenter", fragmentManager);

                    break;

                case R.id.ic_user:
                    Intent intentUser = new Intent(context, UserActivity.class);
                    context.startActivity(intentUser);
                    break;
            }

            return  false;
        });

    }


}
