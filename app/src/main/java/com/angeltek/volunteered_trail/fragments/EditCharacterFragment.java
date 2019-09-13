package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.binder.CharacterBinder;
import com.angeltek.volunteered_trail.models.CharacterModel;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

class EditCharacterFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<CharacterModel> characterModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_character, container, false);

        recyclerView = view.findViewById(R.id.grid_view);

        characterModels = new ArrayList<>();

        setupRecyclerView();

        return view;

    }

    private void setupRecyclerView() {


        characterModels.add(new CharacterModel(getResources().getString(R.string.animal), R.color.colorAnimal, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.art), R.color.colorArt, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.children), R.color.colorChildren, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.civil), R.color.colorCivil, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.disaster), R.color.colorDisaster, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.economic), R.color.colorEconomics, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.education), R.color.colorEducation, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.environment), R.color.colorEnvironment, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.health), R.color.colorHealth, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.human), R.color.colorHuman, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.politics), R.color.colorPolitics, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.poverty), R.color.colorPoverty, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.science), R.color.colorScience, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.social), R.color.colorSocial, R.drawable.ic_search));
        characterModels.add(new CharacterModel(getResources().getString(R.string.women), R.color.colorWomen, R.drawable.ic_search));

        MultiViewAdapter adapter = new MultiViewAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);


        adapter.registerItemBinders(new CharacterBinder(getContext()));

        ListSection<CharacterModel> gridSection = new ListSection<>();
        gridSection.addAll(characterModels);

        adapter.addSection(gridSection);

    }

    @Override
    public void onClick(View view) {

    }
}
