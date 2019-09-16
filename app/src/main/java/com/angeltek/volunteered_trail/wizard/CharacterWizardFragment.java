package com.angeltek.volunteered_trail.wizard;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.angeltek.volunteered_trail.binder.CharacterBinder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.CharacterModel;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;
import mva2.adapter.util.OnSelectionChangedListener;

public class CharacterWizardFragment extends Fragment {

    private static final String TAG = "CharacterWizardFragment";

    //Constants
    private final int NUM_CHARACTERS = 15;
    private final int NUM_COL = 3;

    //Variables
    private ArrayList<CharacterModel> characterModels = new ArrayList<>();

    //Widgets
    RecyclerView recyclerView;
    FloatingActionButton nextFab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Fragment Created");

        View view = inflater.inflate(R.layout.fragment_character_wizard, container, false);

        //init
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        nextFab = (FloatingActionButton) view.findViewById(R.id.wizard_next);


        //call
        setupCharacterCards();
        setupFAB();


        return view;
    }

    /**
     * character model and grid layout setup
     */
    void setupCharacterCards() {

        Log.d(TAG, "setupCharacterCards: character card RecyclerView is populated");

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
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), NUM_COL));
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

    /**
     * setting up next button
     */
    private void setupFAB() {

    }
}
