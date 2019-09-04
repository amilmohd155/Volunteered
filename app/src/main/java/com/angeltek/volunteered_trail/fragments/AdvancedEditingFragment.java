package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angeltek.volunteered_trail.R;

public class AdvancedEditingFragment extends Fragment {

    private static final String TAG = "AdvancedEditingFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_advancedediting, container, false);

        int type = getArguments().getInt("Position");

//        Solution in case clickable attribute doesn't  work properly
//        view.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        return view;
    }
}
