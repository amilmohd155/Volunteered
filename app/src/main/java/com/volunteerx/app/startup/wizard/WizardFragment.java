/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/19/20 6:30 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/30/19 5:47 PM
 *
 */

package com.volunteerx.app.startup.wizard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.StatusColorClass;

import static com.volunteerx.app.utils.StatusColorClass.DARK_STATUS_BAR_ICON;

//Todo obsolete
public class WizardFragment extends Fragment {

    private static final String TAG = "WizardFragment";

    //UI


    public WizardFragment() {
    }

    public static WizardFragment newInstance() {
        return new WizardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_container, container, false);

        StatusColorClass.setStatusColor(getActivity(), getContext().getColor(R.color.colorOnBoardingScreen), DARK_STATUS_BAR_ICON);

        Log.d(TAG, "onCreateView: created");
        final FrameLayout flContainer = view.findViewById(R.id.container);

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction()
                .replace(flContainer.getId(), UserNameWizardFragment.newInstance());
        fragmentTransaction.commit();

        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
