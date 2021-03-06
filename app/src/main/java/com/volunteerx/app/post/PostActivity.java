/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.post;

import android.content.Context;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.volunteerx.app.R;
import com.volunteerx.app.fragments.PrivacyDialogFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";

    final Context mContext = PostActivity.this;

    //Constants
    private static final int VERIFY_PERMISSION_REQUEST_CODE = 1;


    //Variables
    private int flag = 0;
    private int privacy_setting_const = 0; //0 - Public | 1 - Private | 2 - Custom

    //Widgets
    private ImageView closeBtnPost;
    private EditText etPost;
    private TabLayout attachmentTab;
    private CircleImageView profilePhoto;
    private Button postBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post);

        Log.d(TAG, "onCreate: Starting Post Activity");

        //Variable Init
        etPost = findViewById(R.id.post_description);
        closeBtnPost = findViewById(R.id.post_close);
        attachmentTab = findViewById(R.id.attachment_tab);
        profilePhoto = findViewById(R.id.profilePhoto);
        postBtn = findViewById(R.id.submit_post);


        //function Call
//        setupList();
        setupAttachmentTab();
        setupClosingPost();
        setupPrivacySetting();
        setupFontSize();
        setupPostBtn();


    }

    /**
     * Post submitting Button function.
     */
    private void setupPostBtn() {
    }

    /**
     * Post text font change when lines exceed 5
     */
    private void setupFontSize() {
        etPost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 50) {
                    etPost.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f);
                }
                else {
                    etPost.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f);
                }
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

        Log.d(TAG, "setupPrivacySetting: privacy setting selected");

        profilePhoto.setOnClickListener(v -> {

            Log.d(TAG, "onClick: privacySetting selected");

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Fragment prev = getSupportFragmentManager().findFragmentByTag(getResources().getString(R.string.dialog_privacy));

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

                Log.d(TAG, "onTabSelected: position:: " + position);


                if (true) {
                    switch (position) {

                        case 0:
                            setupFragment(new GalleryFragment(), getString(R.string.gallery_fragment)); //photos
                            break;
                        case 1:
                            setupFragment(new CameraFragment(), getString(R.string.camera_fragment)); //Camera
                            break;
                        case 2:
                            setupFragment(new DocumentFragment(), getString(R.string.document_fragment)); //document
                            break;
                        case 3:
                            setupFragment(new PollFragment(), getString(R.string.poll_fragment)); //poll
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
                int position = tab.getPosition();

                Log.d(TAG, "onTabReselected: positon" + position);

                switch (position) {

                    case 0:
                        setupFragment(new GalleryFragment(), getString(R.string.gallery_fragment)); //photos
                        break;
                    case 1:
                        setupFragment(new CameraFragment(), getString(R.string.camera_fragment)); //Camera
                        break;
                    case 2:
                        setupFragment(new DocumentFragment(), getString(R.string.document_fragment)); //document
                        break;
                    case 3:
                        setupFragment(new PollFragment(), getString(R.string.poll_fragment)); //poll
                        break;

                }
            }
        });

    }


    /**
     * SetUp Fragment
     * @param fragment
     * @param fragmentName
     */
    private void setupFragment(Fragment fragment, String fragmentName) {

        Log.d(TAG, "setupFragment: fragment backStacking and process");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.relLayout, fragment, fragmentName)
                .addToBackStack(null);
        fragmentTransaction.commit();

    }




    /**
     * Close button Connection
     */
    private void setupClosingPost() {

        //Todo check if the text area or the media area is empty or not.

        closeBtnPost.setOnClickListener(v -> finish());
    }




}
