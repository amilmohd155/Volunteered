package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.angeltek.volunteered_trail.R;

class EditGenderFragment extends Fragment implements View.OnClickListener {

    private RadioGroup radioGroup;
    private EditText specificGender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_gender, container, false);

        radioGroup = view.findViewById(R.id.gender_group);
        specificGender = view.findViewById(R.id.specify_gender);
        ImageView closeBtn = view.findViewById(R.id.edit_close);
        closeBtn.setOnClickListener(this);

        setupGenderEditor();

        return view;

    }


    /**
     * Gender selection radio group functions
     */
    void setupGenderEditor() {

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedID = radioGroup.getCheckedRadioButtonId();

                if (selectedID == R.id.radio_other) {
                    specificGender.setVisibility(View.VISIBLE);
                    specificGender.setEnabled(true);
                }else {
                    if (specificGender.getText().toString().isEmpty()) {
                        specificGender.setVisibility(View.GONE);
                    }
                    else {
                        specificGender.setEnabled(false);
                    }
                }

            }
        });

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
