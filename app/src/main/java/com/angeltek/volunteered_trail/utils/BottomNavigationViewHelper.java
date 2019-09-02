package com.angeltek.volunteered_trail.utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.angeltek.volunteered_trail.home.HomeActivity;
import com.angeltek.volunteered_trail.location.LocationActivity;
import com.angeltek.volunteered_trail.notification.NotificationActivity;
import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.ping.PingActivity;
import com.angeltek.volunteered_trail.profile.UserActivity;
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
