package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.angeltek.volunteered_trail.R;

public class PrivacyDialogFragment extends DialogFragment {

    private static final String TAG = "PrivacyDialogFragment";

    //Variables
    private int privacy_setting_const;

    //Widgets
    Switch privateSwitch, publicSwitch, customSwitch;
    Button OKButton, discardButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_privacy_setting, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            privacy_setting_const = bundle.getInt("Privacy", 0);
        }

        privateSwitch = (Switch) view.findViewById(R.id.switch_private);
        publicSwitch = (Switch) view.findViewById(R.id.switch_public);
        customSwitch = (Switch) view.findViewById(R.id.switch_custom);

        OKButton = (Button) view.findViewById(R.id.save_btn);
        discardButton = (Button) view.findViewById(R.id.discard_btn);

        //closing dialog
        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: Closing dialog");

                getDialog().dismiss();
            }
        });

        //Saving Dialog change and closing
        OKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Saving Details and closing dialog");

            }
        });


        //Function Calls
        setupInitialSwitch();
        setupPrivacySelection();

        return view;

    }

    /**
     * Sets up initial privacy settings
     */
    private void setupInitialSwitch() {

        switch (privacy_setting_const) {
            case 0:
                publicSwitch.setChecked(true);
                break;
            case 1:
                privateSwitch.setChecked(true);
                break;
            case 2:
                customSwitch.setChecked(true);
        }

    }

    private void setupPrivacySelection() {

        customSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getContext(), "Custom Selected", Toast.LENGTH_SHORT).show();
                    privateSwitch.setChecked(false);
                    publicSwitch.setChecked(false);

                    //goes to new fragment for selecting followers list



                }
            }
        });

        privateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getContext(), "Private Selected", Toast.LENGTH_SHORT).show();
                    publicSwitch.setChecked(false);
                    customSwitch.setChecked(false);

                    //makes the post private only to followers and forum chat members

                }
            }
        });

        publicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getContext(), "Public Selected", Toast.LENGTH_SHORT).show();
                    customSwitch.setChecked(false);
                    privateSwitch.setChecked(false);

                    //makes the post public to anyone who sees the user profile

                }
            }
        });



    }


}
