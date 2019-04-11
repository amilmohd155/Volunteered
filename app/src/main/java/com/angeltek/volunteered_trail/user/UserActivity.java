package com.angeltek.volunteered_trail.user;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.fragments.EditProfileFragment;
import com.angeltek.volunteered_trail.fragments.FollowStaticFragment;
import com.angeltek.volunteered_trail.utils.BottomNavigationViewHelper;
import com.angeltek.volunteered_trail.utils.UserSpinnerAdapter;
import com.angeltek.volunteered_trail.utils.WrapContentStatePagerAdapter;
import com.angeltek.volunteered_trail.utils.WrapContentViewPager;
import com.angeltek.volunteered_trail.utils.SectionsStatePagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";

    final Context mContext = UserActivity.this;

    //Variables
    public static int selectedPosition = -1;

    //widgets
    private WrapContentViewPager viewPager;
    private Toolbar toolbar;
    private Spinner accountSpinner;
    private LinearLayout following, followers, linearLayout, linearLayout1, linearLayout2;
    private TextView editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Log.d(TAG, "onCreate: Starting");

        followers = (LinearLayout) findViewById(R.id.followers);
        following = (LinearLayout) findViewById(R.id.following);
        editProfileBtn = (TextView) findViewById(R.id.edit_profile);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);

        accountSpinner = (Spinner) findViewById(R.id.user_spinner);
        toolbar = (Toolbar) findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);


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

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new EditProfileFragment();
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.relLayout, fragment, fragment.getClass().getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

    }

    /**
     * handles all following and followers work
     */
    private void setupFollowStatics() {


        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Following clicked",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("tabSelector", 1);
                Fragment fragment = new FollowStaticFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.relLayout, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Followers clicked",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("tabSelector", 0);
                Fragment fragment = new FollowStaticFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.relLayout, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }


    /**
     * used to change account to user to organisation
     */
    private void setupAccountChanging() {

        ArrayList<String> username = new ArrayList<>();
        username.add("Docren155");
        username.add("VolunteerHut");
        ArrayList<Integer> profilePicture = new ArrayList<>();
        profilePicture.add(R.drawable.user);
        profilePicture.add(R.drawable.icon1);

        UserSpinnerAdapter adapter = new UserSpinnerAdapter(mContext,
                R.layout.layout_spinner_item,
                R.layout.layout_spinner_dropdown_user,
                username,
                profilePicture);

        accountSpinner.setAdapter(adapter);

        accountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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
        statePagerAdapter.addFragment(new UserMediaFragment(), getResources().getString(R.string.tab_media));

        viewPager.setAdapter(statePagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_user);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.tab_post);
        tabLayout.getTabAt(1).setText(R.string.tab_activities);
        tabLayout.getTabAt(2).setText(R.string.tab_media);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            // Animate fade in and out with transition and scaling

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() != 0) {
                    linearLayout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                }
                else {
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.VISIBLE);
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

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);

    }

}
