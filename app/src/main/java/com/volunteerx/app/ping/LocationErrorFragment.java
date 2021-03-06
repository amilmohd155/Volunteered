/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.ping;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickInterface;

public class LocationErrorFragment extends Fragment {

    private static final String TAG = "LocationErrorFragment";

    private OnFragmentInteractionListener listener;

    public LocationErrorFragment() {
    }

    public static LocationErrorFragment newInstance() {

        Bundle args = new Bundle();

        LocationErrorFragment fragment = new LocationErrorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: fragment started");
        View view = inflater.inflate(R.layout.fragment_fine_location_error, container, false);

        Button enableBtn = view.findViewById(R.id.btn_enable);

        enableBtn.setOnClickListener(view1 -> listener.onFragmentInteraction());

        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(getParentFragment().getClass().getName()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener{
        void onFragmentInteraction();
    }

}
