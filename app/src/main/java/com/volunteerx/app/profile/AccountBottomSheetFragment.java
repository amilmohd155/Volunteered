/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/3/20 11:07 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/3/20 11:07 PM
 *
 */

package com.volunteerx.app.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.volunteerx.app.R;

//Todo incomplete
public class AccountBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String TAG = "AccountBottomSheetFrag";

    public AccountBottomSheetFragment() {
    }

    public static AccountBottomSheetFragment newInstance() {
        AccountBottomSheetFragment fragment = new AccountBottomSheetFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_account_bottom_sheet, container, false);

        return view;

    }
}
