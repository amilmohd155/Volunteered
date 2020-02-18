/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/12/20 1:45 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/12/20 1:45 AM
 *  
 */

package com.volunteerx.app.profile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.volunteerx.app.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserContactBSFragment extends BottomSheetDialogFragment {

    private CircleImageView civProfilePicture;
    private TextView tvName, tvUserName, tvWebsite, tvPhone, tvMail;
    private Button btnFollow;


    public UserContactBSFragment() {
    }

    public static UserContactBSFragment newInstance() {

        Bundle args = new Bundle();

        UserContactBSFragment fragment = new UserContactBSFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user_contact_bs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        civProfilePicture = view.findViewById(R.id.civ_profile_picture);
        tvName = view.findViewById(R.id.tv_name);
        tvUserName = view.findViewById(R.id.tv_user_name);
        btnFollow = view.findViewById(R.id.btn_follow);
        tvWebsite = view.findViewById(R.id.tv_web);
        tvMail = view.findViewById(R.id.tv_mail);
        tvPhone = view.findViewById(R.id.tv_phone);


    }
}
