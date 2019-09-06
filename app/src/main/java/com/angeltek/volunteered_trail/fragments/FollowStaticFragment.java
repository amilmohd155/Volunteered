package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.utils.SectionsPagerAdapter;

public class FollowStaticFragment extends Fragment {

    private static final String TAG = "FollowStaticFragment";

    //variable
    private int tabSelector;

    //widgets
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView topBarTitle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_follow_static, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.follow_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.follow_tabs);
        topBarTitle = (TextView) view.findViewById(R.id.top_bar_title);
        topBarTitle.setText("Username");


        if (getArguments() != null) {
            tabSelector = getArguments().getInt("tabSelector", 0);
        }


        setupViewpager();

        return view;
    }


    /**
     * Sets up the viewpager and nested fragment
     */
    private void setupViewpager() {

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

        sectionsPagerAdapter.addFragment(new FollowersChildFragment());
        sectionsPagerAdapter.addFragment(new FollowingChildFragment());
        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Followers");
        tabLayout.getTabAt(1).setText("Following");

        tabLayout.getTabAt(tabSelector).select();

    }
}
