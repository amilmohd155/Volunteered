/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/7/20 12:33 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/7/20 12:33 AM
 *
 */

package com.volunteerx.app.post;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.volunteerx.app.R;
import com.volunteerx.app.databinding.FragmentPostBinding;
import com.volunteerx.app.fragments.PrivacyDialogFragment;
import com.volunteerx.app.post.viewModel.PostViewModel;

import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.fancybuttons.FancyButton;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {

    private int privacy_setting_const = 0; //0 - Public | 1 - Private | 2 - Custom

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//
//    private String mParam1;
//    private String mParam2;
//

    private FragmentPostBinding binding;
    private PostViewModel postViewModel;

    //Widgets
    private ImageView closeBtnPost;
    private EditText etPost;
    private TabLayout attachmentTab;
    private CircleImageView profilePhoto;
    private FancyButton postBtn;



    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance() {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);

        binding.setPostViewModel(postViewModel);
        binding.setLifecycleOwner(this);

        postViewModel.getIsPostReady().observe(getViewLifecycleOwner(), aBoolean -> {});

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Variable Init
        etPost = view.findViewById(R.id.post_description);
        closeBtnPost = view.findViewById(R.id.post_close);
        attachmentTab = view.findViewById(R.id.attachment_tab);
        profilePhoto = view.findViewById(R.id.profilePhoto);
        postBtn = view.findViewById(R.id.submit_post);

        //function Call
//        setupList();
//        setupAttachmentTab();
//        setupPrivacySetting();
//        setupFontSize();
//        setupPostBtn();

    }

    /**
     * Post submitting Button function.
     */
    private void setupPostBtn() {
    }

    /**
     * Post text
     * size  change when lines exceed 5
     */
    private void setupFontSize() {

        etPost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.length() > 50) {
//                    etPost.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
//                }
//                else {
//                    etPost.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f);
//                }
                if (charSequence.length() > 0) {
                    postBtn.setEnabled(true);
                }else postBtn.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    /**
     * Privacy Setting dialog and custom selector pathway
     */
    private void setupPrivacySetting() {


        profilePhoto.setOnClickListener(v -> {

            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            Fragment prev = getChildFragmentManager().findFragmentByTag(getResources().getString(R.string.dialog_privacy));

            if (prev != null) {
                fragmentTransaction.remove(prev);
            }

            PrivacyDialogFragment privacyDialogFragment = new PrivacyDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Privacy", privacy_setting_const);
            privacyDialogFragment.setArguments(bundle);
            privacyDialogFragment.show(fragmentTransaction, getResources().getString(R.string.dialog_privacy));

        });

    }


    /**
     * Attachment Tab
     */
    private void setupAttachmentTab() {

        attachmentTab.addTab(attachmentTab.newTab().setIcon(R.drawable.ic_photo));//media
        attachmentTab.addTab(attachmentTab.newTab().setIcon(R.drawable.ic_photo_camera));//camera
        attachmentTab.addTab(attachmentTab.newTab().setIcon(R.drawable.ic_document));//documents
        attachmentTab.addTab(attachmentTab.newTab().setIcon(R.drawable.ic_poll));//poll

        attachmentTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();


                if (true) {
                    switch (position) {

                        case 0:
                            replaceFragment(new GalleryFragment(), getString(R.string.gallery_fragment), getFragmentManager()); //photos
                            break;
                        case 1:
                            replaceFragment(new CameraFragment(), getString(R.string.camera_fragment), getFragmentManager()); //Camera
                            break;
                        case 2:
                            replaceFragment(new DocumentFragment(), getString(R.string.document_fragment), getFragmentManager()); //document
                            break;
                        case 3:
                            replaceFragment(new PollFragment(), getString(R.string.poll_fragment), getFragmentManager()); //poll
                            break;

                    }
                }
//                else Permissions.verifyPermission(Permissions.PERMISSIONS, PostActivity.this, VERIFY_PERMISSION_REQUEST_CODE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
