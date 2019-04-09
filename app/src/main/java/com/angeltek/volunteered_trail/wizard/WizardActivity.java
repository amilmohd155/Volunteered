package com.angeltek.volunteered_trail.wizard;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.utils.SectionsPagerAdapter;


public class WizardActivity extends AppCompatActivity {

    private static final String TAG = "WizardActivity";

    final Context mContext = WizardActivity.this;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        Log.d(TAG, "onCreate: Starting");

        //call
        setupFragmentCall();

    }

    /**
     * Starts the fragments
     */
    private  void setupFragmentCall() {

        Log.d(TAG, "setupFragmentCall: Fragment call started");
        viewPager = (ViewPager) findViewById(R.id.container);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                sectionsPagerAdapter.addFragment(new CharacterWizardFragment());
                sectionsPagerAdapter.addFragment(new ProfileWizardFragment());
                viewPager.setAdapter(sectionsPagerAdapter);
                viewPager.setOffscreenPageLimit(1);
            }
        }).start();

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            Log.d(TAG, "onBackPressed: Back to previous Activity");
            super.onBackPressed();
        }
        else {

            Log.d(TAG, "onBackPressed: Back to previous fragment");
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

}
