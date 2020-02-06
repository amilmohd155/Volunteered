/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.ping;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.VolunteerXDialog.VolunteerXDialog;
import com.volunteerx.app.utils.IOonBackPressed;

public class PingFragmentB extends Fragment implements IOonBackPressed {

    /**
     * 0 == 'BackPressable'
     * 1 == 'Not BackPressable'
     */
    private int isBackPressable = 0;

    private RelativeLayout relativeLayout;
    private IOBackPressed listener;

    public PingFragmentB() {
    }

    public static PingFragmentB newInstance() {

        Bundle args = new Bundle();

        PingFragmentB fragment = new PingFragmentB();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ping_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(view1 -> {

            new VolunteerXDialog.Builder(getActivity())
                    .setMessage("Discard your ping")
                    .setTitle("Discard")
                    .isCancellable(false)
                    .setPositiveBtnText("Discard")
                    .setNegativeBtnText("Cancel")
                    .OnPositiveClicked(dialog -> isBackPressable = 0)
                    .OnNegativeClicked(dialog -> isBackPressable = 1)
                    .build();

            listener.onBackPressed();
        });

    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
