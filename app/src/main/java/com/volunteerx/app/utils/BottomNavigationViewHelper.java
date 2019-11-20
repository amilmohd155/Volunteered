package com.volunteerx.app.utils;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.volunteerx.app.home.HomeActivity;
import com.volunteerx.app.location.LocationActivity;
import com.volunteerx.app.notification.NotificationActivity;
import com.volunteerx.app.R;
import com.volunteerx.app.ping.PingActivity;
import com.volunteerx.app.profile.UserActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {

        Log.d(TAG, "setupBottomNavigationView: Setting up Bottom Navigation View");

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }

    public static void enableNavigation (final Context context, BottomNavigationViewEx viewEx) {

        Log.d(TAG, "enableNavigation: Enabling Navigation");

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.ic_home:
                        Intent intentHome = new Intent(context, HomeActivity.class);
                        context.startActivity(intentHome);
                        break;

                    case R.id.ic_location:
                        Intent intentLocation = new Intent(context, LocationActivity.class);
                        context.startActivity(intentLocation);
                        break;

                    case R.id.ic_ping:
                        Intent intentPing = new Intent(context, PingActivity.class);
                        context.startActivity(intentPing);
                        break;

                    case R.id.ic_notification:
                        Intent intentNotifications = new Intent(context, NotificationActivity.class);
                        context.startActivity(intentNotifications);
                        break;

                    case R.id.ic_user:
                        Intent intentUser = new Intent(context, UserActivity.class);
                        context.startActivity(intentUser);
                        break;
                }

                return  false;
            }
        });

    }

}
