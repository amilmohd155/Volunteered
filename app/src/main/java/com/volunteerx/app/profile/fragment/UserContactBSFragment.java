/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/12/20 1:45 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/12/20 1:45 AM
 *  
 */

package com.volunteerx.app.profile.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.storage.FirebaseStorage;
import com.volunteerx.app.R;
import com.volunteerx.app.databinding.FragmentUserContactBsBinding;
import com.volunteerx.app.models.User;
import com.volunteerx.app.utils.GlideApp;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserContactBSFragment extends BottomSheetDialogFragment {

    private static final String TAG = "UserContactBSFragment";
    public static final String PARAM_1 = "userValue";
    public static final String PARAM_2 = "isUserFragment";

    private CircleImageView civProfilePicture;
    private TextView tvName, tvUserName, tvWebsite, tvPhone, tvMail;
    private Button btnFollow;

    private User user;
    private boolean isUserFragment;

    private ElementClickListener listener;


    public UserContactBSFragment() {
    }

    public static UserContactBSFragment newInstance(User user, boolean isUserFragment) {

        Bundle args = new Bundle();
        args.putParcelable(PARAM_1, user);
        args.putBoolean(PARAM_2, isUserFragment);

        UserContactBSFragment fragment = new UserContactBSFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(PARAM_1);
            isUserFragment = getArguments().getBoolean(PARAM_2, true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentUserContactBsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_contact_bs, container, false);
        binding.setUser(user);
        binding.setIsUserFragment(isUserFragment);
        binding.setFragment(this);

        return binding.getRoot();
    }

    public void travelToEditProfile() {
        if (isUserFragment)
            listener.onElementClickListener(ElementClickListener.ELEMENT_TYPE_A);
        listener.onElementClickListener(ElementClickListener.ELEMENT_TYPE_B);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ElementClickListener) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().getClass().getCanonicalName() + " must implement ElementClickListener");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        listener = null;
    }

    public interface ElementClickListener {

        int ELEMENT_TYPE_A = 1;
        int ELEMENT_TYPE_B = 2;

        void onElementClickListener(int ElementType);
    }

}
