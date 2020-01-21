/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;

public class EditBioFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_bio, container, false);

        ImageView closeBtn = view.findViewById(R.id.edit_close);
        closeBtn.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_close:
                getActivity().onBackPressed();
                break;
        }
    }
}
