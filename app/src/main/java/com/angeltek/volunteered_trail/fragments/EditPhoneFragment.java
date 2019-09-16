package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.angeltek.volunteered_trail.R;
import com.hbb20.CountryCodePicker;

class EditPhoneFragment extends Fragment implements View.OnClickListener {

    private CountryCodePicker picker;
    private EditText phoneNumber;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_phone, container, false);

        picker = view.findViewById(R.id.ccp);
        phoneNumber = view.findViewById(R.id.edit_phone);
        ImageView closeBtn = view.findViewById(R.id.edit_close);
        closeBtn.setOnClickListener(this);

        picker.registerCarrierNumberEditText(phoneNumber);

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_close:
                getActivity().onBackPressed();
                break;
        }
    }
}