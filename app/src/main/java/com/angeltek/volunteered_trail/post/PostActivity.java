package com.angeltek.volunteered_trail.post;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.fragments.PrivacyDialogFragment;
import com.angeltek.volunteered_trail.utils.Permissions;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";

    final Context mContext = PostActivity.this;

    //Constants
    private static final int VERIFY_PERMISSION_REQUEST_CODE = 1;


    //Variables
    private int flag = 0;
    private int privacy_setting_const = 0; //0 - Public | 1 - Private | 2 - Custom

    //Widgets
    private RelativeLayout relativeLayout, relativeLayout2;
    private ImageView closeBtnPost;
    private EditText postDescEditText;
    private TabLayout attachmentTab;
    LinearLayout privacySetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Log.d(TAG, "onCreate: Starting Post Activity");

        //Variable Init
        relativeLayout = (RelativeLayout) findViewById(R.id.relLayout);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.relLayout2);
        postDescEditText = (EditText) findViewById(R.id.post_description);
        closeBtnPost = (ImageView) findViewById(R.id.post_close);
        attachmentTab = (TabLayout) findViewById(R.id.attachment_tab);
        privacySetting = (LinearLayout) findViewById(R.id.privacy_settings);


        //function Call
//        setupList();
        setupAttachmentTab();
        setupClosingPost();
        setupPrivacySetting();


    }


    /**
     * Privacy Setting dialog and custom selector pathway
     */
    private void setupPrivacySetting() {

        Log.d(TAG, "setupPrivacySetting: privacy setting selected");

        privacySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

            }
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

                if (Permissions.checkPermissionArray(Permissions.PERMISSIONS, mContext)) {
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
                else Permissions.verifyPermission(Permissions.PERMISSIONS, PostActivity.this, VERIFY_PERMISSION_REQUEST_CODE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    setupFragment(new GalleryFragment(), getString(R.string.gallery_fragment));
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
                .addToBackStack(fragmentName);
        fragmentTransaction.commit();

    }




    /**
     * Close button Connection
     */
    private void setupClosingPost() {

        closeBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




}
