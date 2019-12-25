package com.volunteerx.app.startup;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.volunteerx.app.R;

public class StartupActivity extends AppCompatActivity {

    private static final String TAG = "StartupActivity";

    //UI
    private FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_container);

        container = findViewById(R.id.container);

        setupSplashFragment();

    }

    private void setupSplashFragment() {
        Log.d(TAG, "setupSplashFragment: app starting");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .replace(container.getId(), new SplashFragment());
        fragmentTransaction.commit();

    }

    /**
     *
     * @param fragment f
     * @param fragmentName String as fragment name
     * @param fragmentType for backStack fragment -- true && ! == false
     */
    private void loadFragment(Fragment fragment, String fragmentName, Boolean fragmentType) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;

        if (fragmentType) {
            fragmentTransaction= fragmentManager.beginTransaction()
                    .replace(container.getId(), fragment, fragmentName)
                    .addToBackStack(fragmentName);
            fragmentTransaction.commit();
        }else  {
            fragmentTransaction= fragmentManager.beginTransaction()
                    .replace(container.getId(), fragment, fragmentName);
            fragmentTransaction.commit();
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
