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
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.volunteerx.app.R;
import com.volunteerx.app.home.HomeActivity;
import com.volunteerx.app.startup.onBoarding.OnBoardingFragment;
import com.volunteerx.app.startup.wizard.UserNameWizardFragment;
import com.volunteerx.app.utils.SharedPrefManager;

import java.util.Timer;
import java.util.TimerTask;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;
import static com.volunteerx.app.utils.PreferenceCheckerClass.restorePrefsData;

public class StartupActivity extends AppCompatActivity {

    private static final String TAG = "StartupActivity";

    private final Context context = StartupActivity.this;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash_screen);

        mAuth = FirebaseAuth.getInstance();

    }

    private void ifAlreadyLoggedIn(FirebaseUser user) {

        if (user != null) {
            if (SharedPrefManager.getInstance(context).isWizardComplete()) {
                findViewById(R.id.iv_splash).setVisibility(View.GONE);
                replaceFragment(UserNameWizardFragment.newInstance(), "UserNameWizardFragment", getSupportFragmentManager());
            }else {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }
        else {
            findViewById(R.id.iv_splash).setVisibility(View.GONE);
            if (restorePrefsData(context, getString(R.string.on_boarding_screen_opened_key), getString(R.string.saved_on_boarding_key)))
                replaceFragment(new StartupFragment(), "StartupFragment", getSupportFragmentManager());
            else
                replaceFragment(OnBoardingFragment.newInstance(), "OnBoardingFragment", getSupportFragmentManager());
        }

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
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        ifAlreadyLoggedIn(currentUser);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
