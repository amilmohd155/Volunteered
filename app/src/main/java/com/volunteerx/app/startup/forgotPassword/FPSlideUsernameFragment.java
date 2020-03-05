/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/19/20 6:29 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/27/19 4:05 PM
 *
 */

package com.volunteerx.app.startup.forgotPassword;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;

import static com.volunteerx.app.utils.Constants.FP_EMAIL;
import static com.volunteerx.app.utils.Constants.FP_PHONE;


public class FPSlideUsernameFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FPSlideUsernameFragment";

    private final Context context = getContext();
    private ClickListener listener;

    //var
    private String email, phone;

    //UI
    private Toolbar toolbar;
    private CardView cvEmail, cvPhone;
    private TextView absEmail, absPhone;

    public FPSlideUsernameFragment() {
        //Required null constructor
    }

    //    Use this factory method to create a new instance of
//    this fragment using the provided parameters.
    public static FPSlideUsernameFragment newInstance() {
        FPSlideUsernameFragment fragment = new FPSlideUsernameFragment();

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

        View view = inflater.inflate(R.layout.fragment_fp_slide_username, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        cvEmail = view.findViewById(R.id.email_card);
        cvPhone = view.findViewById(R.id.phone_card);
        cvPhone.setOnClickListener(this);
        cvEmail.setOnClickListener(this);

        setupBackButton();

        return view;

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
                    getActivity().onBackPressed();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_card: {
                listener.buttonClick(FP_EMAIL);
            }break;
            case R.id.phone_card: {
                listener.buttonClick(FP_PHONE);
            }break;
        }
    }
}
