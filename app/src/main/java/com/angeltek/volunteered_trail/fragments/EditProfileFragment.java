package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.angeltek.volunteered_trail.R;

//todo complete layout, connect media to change dp and coverP.
public class EditProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "EditProfileFragment";

    private final int NUM_USERNAME = 0;
    private final int NUM_BIO= 1;
    private final int NUM_EMAIL = 2;
    private final int NUM_PHONE = 3;
    private final int NUM_GENDER = 4;
    private final int NUM_CHARACTER = 5;


    private ImageView closeBtn;
    private TextInputEditText name, username, bio, website, emailAddress, phoneNumber, gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        closeBtn = view.findViewById(R.id.edit_profile_close);

        name = view.findViewById(R.id.edit_name);
        username = view.findViewById(R.id.edit_profile_username);
        bio = view.findViewById(R.id.edit_bio);
        website = view.findViewById(R.id.edit_website);
        emailAddress = view.findViewById(R.id.edit_email);
        phoneNumber = view.findViewById(R.id.edit_phone);
        gender = view.findViewById(R.id.edit_gender);


        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
                else Log.d(TAG, "onClick: NPE on getActivity()");
            }
        });

        return view;
    }

    /**
     * Sets up the child fragment based on the constant send to the fragment
     * params : position(int)
     */
    private void setupChildFragment(int position) {



    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.edit_profile_username:
                setupChildFragment(NUM_USERNAME);
                break;
            case R.id.edit_bio:
                setupChildFragment(NUM_BIO);
                break;
            case R.id.edit_email:
                setupChildFragment(NUM_EMAIL);
                break;
            case R.id.edit_phone:
                setupChildFragment(NUM_PHONE);
                break;
            case R.id.edit_gender:
                setupChildFragment(NUM_GENDER);
                break;
        }

    }
}
