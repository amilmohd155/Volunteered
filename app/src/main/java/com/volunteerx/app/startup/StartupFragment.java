/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:14 PM
 *
 */

package com.volunteerx.app.startup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.volunteerx.app.R;
import com.volunteerx.app.databinding.FragmentStartupBinding;
import com.volunteerx.app.startup.forgotPassword.ForgotPasswordFragment;
import com.volunteerx.app.startup.wizard.WizardFragment;
import com.volunteerx.app.utils.ClickListener;
import com.volunteerx.app.utils.RoundedCorner;
import com.volunteerx.app.utils.SectionsPagerAdapter;

import static com.volunteerx.app.utils.Constants.FP_INIT;
import static com.volunteerx.app.utils.Constants.INVITE_LOGIN;
import static com.volunteerx.app.utils.Constants.INVITE_SIGN_UP;
import static com.volunteerx.app.utils.Constants.WIZARD;
import static com.volunteerx.app.utils.StatusColorClass.LIGHT_STATUS_BAR_ICON;
import static com.volunteerx.app.utils.StatusColorClass.setStatusColor;


public class StartupFragment extends Fragment implements ClickListener {

    private static final String TAG = "StartupFragment";

    //UI
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewGroup mContainer;

    FragmentStartupBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContainer = container;

        binding = FragmentStartupBinding.inflate(inflater, container, false);

        viewPager = binding.pager;
        tabLayout = binding.tabLayout;

        RoundedCorner.setRoundedCorner(binding.curvyLayout, 25 , RoundedCorner.ROUNDED_TOP);

        setupViewPagerViews();

        return binding.getRoot();

    }

    private void setupViewPagerViews() {

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        sectionsPagerAdapter.addFragment(LoginFragment.newInstance());
        sectionsPagerAdapter.addFragment(SignUpFragment.newInstance());

        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.login_tab);
        tabLayout.getTabAt(1).setText(R.string.sign_up_tab);

    }

    @Override
    public void buttonClick(int type) {
        switch (type) {
            case FP_INIT:{
                try {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(mContainer.getId(), ForgotPasswordFragment.newInstance())
                            .addToBackStack(getContext().getString(R.string.forgot_password_fragment))
                            .commit();
                }catch (NullPointerException e) {
                    e.getStackTrace();
                }
            }
                break;
            case INVITE_SIGN_UP: {
                try {
                    tabLayout.getTabAt(1).select();
                }catch (NullPointerException e) {e.getStackTrace(); }
            }
                break;
            case INVITE_LOGIN: {
                try {
                    tabLayout.getTabAt(0).select();
                }catch (NullPointerException e) {e.getStackTrace(); }
            }break;
            case WIZARD: {
                try {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(mContainer.getId(), WizardFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                }catch (NullPointerException e) {
                    e.getStackTrace();
                    Log.d(TAG, "signUpFunc: failed");
                }
            }
        }
    }

    //does nothing
    @Override
    public boolean onLongClick(int args) {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
