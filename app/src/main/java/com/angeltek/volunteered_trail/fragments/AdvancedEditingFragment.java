package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.angeltek.volunteered_trail.R;
import com.hbb20.CountryCodePicker;

public class AdvancedEditingFragment extends Fragment {

    private static final String TAG = "AdvancedEditingFragment";

    private CountryCodePicker picker;
    private EditText phoneNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_advancedediting, container, false);

        int type = getArguments().getInt("Position");

        picker = view.findViewById(R.id.ccp);
        phoneNumber = view.findViewById(R.id.edit_phone);

        picker.registerCarrierNumberEditText(phoneNumber);

//        Solution in case clickable attribute doesn't  work properly
//        view.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        return view;
    }
}
