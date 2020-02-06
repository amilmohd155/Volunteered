/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:01 PM
 *
 */

package com.volunteerx.app.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.volunteerx.app.R;
import com.volunteerx.app.binder.CharacterTypeABinder;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;

//todo correct the chsracter error
public class EditCharacterFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "EditCharacterFragment";

    private RecyclerView recyclerView;
    private ImageView closeBtn;
//    private ArrayList<ICharacterModel> characterModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_character, container, false);

        recyclerView = view.findViewById(R.id.grid_view);
        closeBtn = view.findViewById(R.id.edit_close);
        closeBtn.setOnClickListener(this);

        setupRecyclerView();

        return view;

    }

    private void setupRecyclerView() {

        Log.d(TAG, "setupRecyclerView: populating recyclerView with character cards");

//        characterModels.add(new ICharacterModel(getResources().getString(R.string.animal), R.color.colorAnimal, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.art), R.color.colorArt, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.children), R.color.colorChildren, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.civil), R.color.colorCivil, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.disaster), R.color.colorDisaster, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.economic), R.color.colorEconomics, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.education), R.color.colorEducation, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.environment), R.color.colorEnvironment, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.health), R.color.colorHealth, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.human), R.color.colorHuman, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.politics), R.color.colorPolitics, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.poverty), R.color.colorPoverty, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.science), R.color.colorScience, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.social), R.color.colorSocial, R.drawable.icon1));
//        characterModels.add(new ICharacterModel(getResources().getString(R.string.women), R.color.colorWomen, R.drawable.icon1));

        MultiViewAdapter adapter = new MultiViewAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);


        adapter.registerItemBinders(new CharacterTypeABinder(getContext()));

//        ListSection<ICharacterModel> gridSection = new ListSection<>();
//        gridSection.addAll(characterModels);

//        adapter.addSection(gridSection);
//        adapter.setSelectionMode(Mode.MULTIPLE);
//
//        gridSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {
//            //Todo COde for handling the selected item
//        });

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
