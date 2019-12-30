/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 8:58 PM
 *
 */

package com.volunteerx.app.startup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.volunteerx.app.R;

public class CharacterBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String TAG = "CharacterBottomSheet";
    private static final String params = "characterID";

    //Var
    private int characterID;

    //UI
    private TextView tvCharacterName, tvCharacterDescription;
    private ImageView ivCharacterImage;


    private View view;

    public CharacterBottomSheetFragment() {
    }

    public static CharacterBottomSheetFragment newInstance(int characterID) {
        CharacterBottomSheetFragment fragment = new CharacterBottomSheetFragment();
        Bundle args = new Bundle();
        args.putInt(params, characterID);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        characterID = args.getInt(params, 0);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: bottomSheet created");

        view = inflater.inflate(R.layout.fragment_character_bottom_sheet, container, false);

        tvCharacterName = view.findViewById(R.id.tv_character_name);
        tvCharacterDescription = view.findViewById(R.id.tv_character_description);
        ivCharacterImage = view.findViewById(R.id.circleImageView);

        setupFragmentLayout();

        return view;

    }

    private void setupFragmentLayout() {

        switch (characterID) {
            case 0: {
                tvCharacterName.setText("Animal Welfare");
                tvCharacterDescription.setText(getString(R.string.placebo_s));
                Glide.with(getContext())
                        .load(R.drawable.avatar0)
                        .into(ivCharacterImage);
            }
            break;

            case 1: {
//todo complete the characters
            }break;
            case 2: {

            }
        }

    }

}
