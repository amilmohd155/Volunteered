package com.angeltek.volunteered_trail.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.search.SearchActivity;
import com.angeltek.volunteered_trail.utils.BottomNavigationViewHelper;
import com.angeltek.volunteered_trail.utils.SectionsPagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class NotificationActivity extends AppCompatActivity {

    private static final String TAG = "NotificationActivity";

    //constants
    final Context mContext = NotificationActivity.this;

    //widgets
    RelativeLayout searchTab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Log.d(TAG, "onCreate: Starting");

        searchTab = (RelativeLayout) findViewById(R.id.search_tab);

        setupFragment();
        setupBottomNavigationView();
        setupSearchActivity();

    }

    private void setupSearchActivity() {

        Log.d(TAG, "setupSearchActivity: going to search activity");

        searchTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);

            }
        });


    }


    private void setupFragment() {

        Log.d(TAG, "setupFragmentCall: Fragment call started");

        viewPager = (ViewPager) findViewById(R.id.container);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new NotificationFragment());
        sectionsPagerAdapter.addFragment(new RequestFragment());
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.tab_notification);
        tabLayout.getTabAt(1).setText(R.string.tab_request);

    }


    /**
     * Setting up BottomNavigationView
     */
    private void setupBottomNavigationView() {

        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);

    }

}
