package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angeltek.volunteered_trail.R;

//Todo CustomSelectionFrgment to be completed

public class CustomSelectionFragment extends Fragment {

    private static final String TAG = "CustomSelectionFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_custom_selection, container, false);

        return view;

    }

    //till first selection the done button is not in focus ...
}
