/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 8:03 PM
 *
 */

package com.volunteerx.app.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.volunteerx.app.R;
import com.volunteerx.app.forum.ForumsFragment;
import com.volunteerx.app.utils.ClickListener;
import com.volunteerx.app.utils.SectionsStatePagerAdapter;

import static com.volunteerx.app.utils.Constants.FORUM_VIEW;
import static com.volunteerx.app.utils.Constants.HOME_VIEW;
import static com.volunteerx.app.utils.Constants.NEARBY_VIEW;

public class HomeActivity extends AppCompatActivity  implements ClickListener {

    private static final String TAG = "HomeActivity";

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG, "onCreate: Starting");

        viewPager = findViewById(R.id.main_view_pager);

        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        Runnable runnable = () -> {

            adapter.addFragment(NearbyFragment.newInstance(), "NearbyFragment");
            adapter.addFragment(HomeFragment.newInstance(), "HomeFragment");
            adapter.addFragment(ForumsFragment.newInstance(), "ForumsFragment");

        };

        runnable.run();

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);


    }

    @Override
    public void onBackPressed() {

        if (viewPager.getCurrentItem() == 1) {
            Log.d(TAG, "onBackPressed: super");
            super.onBackPressed();
        }else {
            viewPager.setCurrentItem(1);
        }

    }

    @Override
    public void buttonClick(int type) {
        switch (type) {
            case NEARBY_VIEW: viewPager.setCurrentItem(0);
                break;
            case FORUM_VIEW:  viewPager.setCurrentItem(2);
                break;
            case HOME_VIEW: viewPager.setCurrentItem(1);
        }
    }

    @Override
    public boolean onLongClick(int args) {
        return false;
    }

}
