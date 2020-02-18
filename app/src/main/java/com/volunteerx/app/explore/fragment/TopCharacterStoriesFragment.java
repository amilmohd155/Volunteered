/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/7/20 7:15 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/7/20 7:15 PM
 *
 */

package com.volunteerx.app.explore.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;

//TOdo set layout and complete
public class TopCharacterStoriesFragment extends Fragment {

    private static final String param = "Character ID";

    private int characterId;

    public TopCharacterStoriesFragment() {
    }

    public static TopCharacterStoriesFragment newInstance(int characterId) {

        Bundle args = new Bundle();

        args.putInt(param, characterId);

        TopCharacterStoriesFragment fragment = new TopCharacterStoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            characterId = getArguments().getInt(param, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_act_about, container, false);
    }
}
