/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 8:58 PM
 *
 */

package com.volunteerx.app.startup;

import android.content.res.TypedArray;
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

public class CharacterInfoBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String TAG = "CharacterBottomSheet";
    private static final String params = "characterID";

    //Var
    private int characterID;

    //UI
    private TextView tvCharacterName, tvCharacterDescription;
    private ImageView ivCharacterImage;


    private View view;

    public CharacterInfoBottomSheetFragment() {
    }

    public static CharacterInfoBottomSheetFragment newInstance(int characterID) {
        CharacterInfoBottomSheetFragment fragment = new CharacterInfoBottomSheetFragment();
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

        view = inflater.inflate(R.layout.fragment_character_info_bs, container, false);

        tvCharacterName = view.findViewById(R.id.tv_character_name);
        tvCharacterDescription = view.findViewById(R.id.tv_character_description);
        ivCharacterImage = view.findViewById(R.id.circleImageView);

        characterTypeFunction();

        return view;

    }

    private void characterTypeFunction() {

        String[] characterName = getResources().getStringArray(R.array.characterName);
        String[] characterDescription = getResources().getStringArray(R.array.character_description);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.characterIcon);

        int[] characterIcon = new int[typedArray.length()];
        for (int i =0; i < characterIcon.length; ++i) {
            characterIcon[i] = typedArray.getResourceId(i, 0);
        }

        switch (characterID) {
            case 0: {   //Animal welfare
                layoutFix(characterName[0], characterDescription[0], characterIcon[0]);
            }
            break;
            case 1:    //Art and Culture
                layoutFix(characterName[1], characterDescription[1], characterIcon[1]);
                break;
            case 2: //Children
                layoutFix(characterName[2], characterDescription[2], characterIcon[2]);
            break;
            case 3: //
                layoutFix(characterName[3], characterDescription[3], characterIcon[3]);
                break;
            case 4:
                layoutFix(characterName[4], characterDescription[4], characterIcon[4]);
                break;
            case 5:
                layoutFix(characterName[5], characterDescription[5], characterIcon[5]);
                break;
            case 6:
                layoutFix(characterName[6], characterDescription[6], characterIcon[6]);
                break;
            case 7:
                layoutFix(characterName[7], characterDescription[7], characterIcon[7]);
                break;
            case 8:
                layoutFix(characterName[8], characterDescription[8], characterIcon[8]);
                break;
            case 9:
                layoutFix(characterName[9], characterDescription[9], characterIcon[9]);
                break;
            case 10:
                layoutFix(characterName[10], characterDescription[10], characterIcon[10]);
                break;
            case 11:
                layoutFix(characterName[11], characterDescription[11], characterIcon[11]);
                break;
            case 12:
                layoutFix(characterName[12], characterDescription[12], characterIcon[12]);
                break;
            case 13:
                layoutFix(characterName[13], characterDescription[13], characterIcon[13]);
                break;
            case 14:
                layoutFix(characterName[14], characterDescription[14], characterIcon[14]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + characterID);
        }

        typedArray.recycle();

    }

    private void layoutFix(String characterName, String characterDescription, int imageRes) {

        tvCharacterName.setText(characterName);
        tvCharacterDescription.setText(characterDescription);
        Glide.with(getContext())
                .load(imageRes)
                .into(ivCharacterImage);

    }



}
