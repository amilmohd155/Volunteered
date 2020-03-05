/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.storage.FirebaseStorage;
import com.volunteerx.app.R;
import com.volunteerx.app.databinding.FragmentEditProfileBinding;
import com.volunteerx.app.firebase.FirebaseMethods;
import com.volunteerx.app.models.User;
import com.volunteerx.app.profile.viewmodel.UserViewModel;
import com.volunteerx.app.utils.GlideApp;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

//todo complete layout, connect media to change dp and coverP.
public class EditProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "EditProfileFragment";
    private static final String PARAM_1 = "User_Value";

    private final String TXT_USERNAME = "EditUsernameFragment";
    private final String  TXT_BIO= "EditBioFragment";
    private final String TXT_EMAIL = "EditEmailFragment";
    private final String TXT_PHONE = "EditPhoneFragment";
    private final String TXT_GENDER = "EditGenderFragment";
    private final String TXT_CHARACTER = "EditCharacterFragment";


    private TextInputEditText name, username, bio, website, emailAddress, phoneNumber, gender;
    private LinearLayout character, llProfilePicture;
    private CircleImageView civProfile;
    private User user;
    private UserViewModel userViewModel;

    public static Fragment newInstance(User user) {

        Bundle args = new Bundle();
        args.putParcelable(PARAM_1, user);

        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(PARAM_1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentEditProfileBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.setUserViewModel(userViewModel);
        binding.setUser(user);
        binding.setFragment(this);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        name = view.findViewById(R.id.edit_name);
        username = view.findViewById(R.id.edit_username);
        bio = view.findViewById(R.id.edit_bio);
        website = view.findViewById(R.id.edit_website);
        emailAddress = view.findViewById(R.id.edit_email);
        phoneNumber = view.findViewById(R.id.edit_phone);
        gender = view.findViewById(R.id.edit_gender);
        character = view.findViewById(R.id.edit_character);
        llProfilePicture = view.findViewById(R.id.linearLayoutProfile);
        civProfile = view.findViewById(R.id.profilePhoto);

//        updateUi();


        username.setOnClickListener(this);
        bio.setOnClickListener(this);
        emailAddress.setOnClickListener(this);
        phoneNumber.setOnClickListener(this);
        gender.setOnClickListener(this);
        character.setOnClickListener(this);
        llProfilePicture.setOnClickListener(this);

        toolbar.setNavigationOnClickListener(view1 -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });

    }

    private void updateUi() {

        username.setText(user.getUsername());
        name.setText(user.getName());
        if(!user.getEmail().isEmpty()) emailAddress.setText(user.getEmail());
        if(!user.getPhone().isEmpty()) phoneNumber.setText(user.getPhone());
        if (!user.getBio().isEmpty()) bio.setText(user.getBio());
        //Gender





    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.edit_username:
                Log.d(TAG, "onClick: username");
                setupChildFragment(new EditUsernameFragment(), TXT_USERNAME);
                break;
            case R.id.edit_bio:
                setupChildFragment(new EditBioFragment(), TXT_BIO);
                break;
            case R.id.edit_email:
                setupChildFragment(new EditEmailFragment(), TXT_EMAIL);
                break;
            case R.id.edit_phone:
                setupChildFragment(new EditPhoneFragment(), TXT_PHONE);
                break;
            case R.id.edit_gender:
                setupChildFragment(new EditGenderFragment(), TXT_GENDER);
                break;
            case R.id.edit_character:
                setupChildFragment(new EditCharacterFragment(), TXT_CHARACTER);
                break;
            case R.id.linearLayoutProfile:
                setupFilePicker();
                break;
        }
    }

    private void setupFilePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImage = data.getData();

                GlideApp.with(this)
                        .load(selectedImage)
                        .into(civProfile);

                String photoUrl = new FirebaseMethods().addProfilePicture(selectedImage, user.getPhotoUrl());
                user.setPhotoUrl(photoUrl);
                Log.d(TAG, "onActivityResult: " + photoUrl);

            }
        }
    }

    /**
     * Sets up the child fragment based on the constant send to the fragment
     * @param fragment
     * @param fragmentName
     */
    private void setupChildFragment(Fragment fragment, String fragmentName) {

        Log.d(TAG, "setupChildFragment: edit profile child fragments::" + fragmentName);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.container, fragment, fragmentName)
                .addToBackStack(null);
        fragmentTransaction.commit();

    }
}

