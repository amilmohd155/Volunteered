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
import java.util.List;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;
import mva2.adapter.util.OnSelectionChangedListener;

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


        characterModels.add(new CharacterModel(getResources().getString(R.string.animal), R.color.colorAnimal, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.art), R.color.colorArt, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.children), R.color.colorChildren, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.civil), R.color.colorCivil, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.disaster), R.color.colorDisaster, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.economic), R.color.colorEconomics, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.education), R.color.colorEducation, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.environment), R.color.colorEnvironment, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.health), R.color.colorHealth, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.human), R.color.colorHuman, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.politics), R.color.colorPolitics, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.poverty), R.color.colorPoverty, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.science), R.color.colorScience, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.social), R.color.colorSocial, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.women), R.color.colorWomen, R.drawable.icon1));

        MultiViewAdapter adapter = new MultiViewAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);


        adapter.registerItemBinders(new CharacterBinder(getContext()));

        ListSection<CharacterModel> gridSection = new ListSection<>();
        gridSection.addAll(characterModels);

        adapter.addSection(gridSection);
        adapter.setSelectionMode(Mode.MULTIPLE);

        gridSection.setOnSelectionChangedListener(new OnSelectionChangedListener<CharacterModel>() {
            @Override
            public void onSelectionChanged(CharacterModel item, boolean isSelected, List<CharacterModel> selectedItems) {
                //Todo COde for handling the selected item
            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
