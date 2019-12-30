/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:16 PM
 *
 */

package com.volunteerx.app.startup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;
import com.volunteerx.app.R;
import com.volunteerx.app.models.ScreenItem;
import com.volunteerx.app.utils.OnBoardingViewPagerAdapter;
import com.volunteerx.app.utils.StatusColorClass;

import java.util.ArrayList;
import java.util.List;

import static com.volunteerx.app.utils.PreferenceCheckerClass.savePrefsData;
import static com.volunteerx.app.utils.StatusColorClass.DARK_STATUS_BAR_ICON;

public class OnBoardingFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "OnBoardingFragment";

    //Var
    private OnBoardingViewPagerAdapter adapter;
    private ViewGroup container;

    //UI
    private ViewPager viewPager;
    private WormDotsIndicator indicator;
    private Button btnGetStarted, btnNext;
    private int position = 0;
    private List<ScreenItem> screenItemList;

    public OnBoardingFragment() {}

    public static OnBoardingFragment newInstance() {
        return new OnBoardingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.onboarding_fragment, container, false);

        StatusColorClass.setStatusColor(getActivity(), getContext().getColor(R.color.colorOnBoardingScreen), DARK_STATUS_BAR_ICON);

        this.container = container;
        viewPager = view.findViewById(R.id.on_boarding_view_pager);
        indicator = view.findViewById(R.id.worm_dots_indicator);
        btnGetStarted = view.findViewById(R.id.btn_get_started);
        btnNext = view.findViewById(R.id.btn_next);

        btnGetStarted.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        setOnBoardingItems();

        return view;

    }

    private void setOnBoardingItems() {

        screenItemList = new ArrayList<>();
        screenItemList.add(new ScreenItem("Select your destination", getString(R.string.placebo_s), R.drawable.onboarding_1));
        screenItemList.add(new ScreenItem("Choose a hotel", getString(R.string.placebo_s), R.drawable.onboarding_2));
        screenItemList.add(new ScreenItem("Enjoy your holiday", getString(R.string.placebo_s), R.drawable.onboarding_3));

        adapter = new OnBoardingViewPagerAdapter(getContext(), screenItemList);
        viewPager.setAdapter(adapter);

        indicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == screenItemList.size() -1) {

                    loadLastScreen();

                }else {
                    loadOtherScreens();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next: {
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        position = viewPager.getCurrentItem();

                        Log.d(TAG, "onClick: next button clicked going to:: " + position);

                        if (position < screenItemList.size()) {
                            position ++;
                            viewPager.setCurrentItem(position, true);
                        }

                        if (position == screenItemList.size() -1) {
                            loadLastScreen();
                        }
                        else {
                            loadOtherScreens();
                        }
                    }

                });
            }
            break;
            case R.id.btn_get_started: {

                //Shared Preference for storing isOnBoardingFragmentOpened value @boolean
                savePrefsData(getContext(),
                        getString(R.string.on_boarding_screen_opened_key),
                        getString(R.string.saved_on_boarding_key));

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(container.getId(), new StartupFragment())
                        .commit();
            }
            break;
        }
    }

    private void loadLastScreen() {

        btnNext.setVisibility(View.GONE);
        btnGetStarted.setVisibility(View.VISIBLE);
        indicator.setVisibility(View.GONE);

    }

    private void loadOtherScreens() {
        btnNext.setVisibility(View.VISIBLE);
        btnGetStarted.setVisibility(View.GONE);
        indicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        screenItemList = null;
    }
}
