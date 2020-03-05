/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/19/20 6:29 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/25/19 9:32 PM
 *
 */

package com.volunteerx.app.startup.forgotPassword;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.goodiebag.pinview.Pinview;
import com.google.android.material.snackbar.Snackbar;
import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;

import static com.volunteerx.app.utils.Constants.FP_BETA;


public class FPSlidePhoneFragment extends Fragment {

    private static final String TAG = "FPSlidePhoneFragment";

    private final Context context = getContext();
    private ClickListener listener;

    //UI
    private Toolbar toolbar;
    private Pinview pinview;

    public FPSlidePhoneFragment() {
        //Required null constructor
    }

    //    Use this factory method to create a new instance of
//    this fragment using the provided parameters.
    public static FPSlidePhoneFragment newInstance() {

        FPSlidePhoneFragment fragment = new FPSlidePhoneFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //bundle values;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fp_slide_phone, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        pinview = view.findViewById(R.id.pin_view);

        handleOtp();
        setupBackButton();

        return view;

    }

    private void handleOtp() {

        pinview.setPinViewEventListener((Pinview pinview, boolean fromUser) -> {
                Toast.makeText(getContext(), pinview.getValue(), Toast.LENGTH_LONG).show();
                String pinValue = pinview.getValue();

                if (verifyOtp(pinValue)) {
                    listener.buttonClick(FP_BETA);
                }else {
                    Snackbar.make(getView(), "Wrong OTP entered", Snackbar.LENGTH_LONG).show();
                }
        });

    }

    private boolean verifyOtp(String pinValue) {

        if (pinValue.equals("1234")) {
            return true;
        }
        return false;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof ClickListener) {
            listener = (ClickListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ClickListener");
        }
    }

    private void setupBackButton() {

        Log.d(TAG, "setupBackButton: closing fragment");
        toolbar.setNavigationOnClickListener((View view) -> {
                try {
                    getFragmentManager().popBackStack();
                }catch (NullPointerException e) {
                    e.getStackTrace();
                }
        });
    }



    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
