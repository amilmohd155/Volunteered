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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;


public class FPSlideEmailFragment extends Fragment {
    //FPSlideEmailFragment
    private static final String TAG = "FPSlideEmailFragment";

    private final Context context = getContext();
    private ClickListener listener;

    //UI
    private Toolbar toolbar;

    public FPSlideEmailFragment() {
        //Required null constructor
    }

//    Use this factory method to create a new instance of
//    this fragment using the provided parameters.
    public static FPSlideEmailFragment newInstance() {

        FPSlideEmailFragment fragment = new FPSlideEmailFragment();

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

        View view = inflater.inflate(R.layout.fragment_fp_slide_email, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        setupBackButton();

        return view;

    }

    private void setupBackButton() {

        Log.d(TAG, "setupBackButton: closing fragment");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getFragmentManager().popBackStack();
                }catch (NullPointerException e) {
                    e.getStackTrace();
                }
            }
        });
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

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
