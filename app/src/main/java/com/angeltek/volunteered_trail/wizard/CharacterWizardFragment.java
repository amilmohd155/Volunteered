package com.angeltek.volunteered_trail.wizard;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.models.CharacterModel;

import java.util.ArrayList;

public class CharacterWizardFragment extends Fragment implements CharacterRecyclerViewAdapter.ItemListener {

    private static final String TAG = "CharacterWizardFragment";

    //Constants
    private final int NUM_CHARACTERS = 15;
    private final int NUM_COL = 3;
    private final CharacterRecyclerViewAdapter.ItemListener listener = this;

    //Variables
    private ArrayList<CharacterModel> characterModels = new ArrayList<>();

    private Handler handler;
    private Runnable runnable;

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

        Log.d(TAG, "setupCharacterModels: Character setup");

        runnable = new Runnable() {
            @Override
            public void run() {
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

                CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(characterModels, getContext(), listener);
                recyclerView.setAdapter(adapter);

                GridLayoutManager manager = new GridLayoutManager(getContext(), NUM_COL, GridLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);

            }
        };


        handler = new Handler(getContext().getMainLooper());
        handler.post(runnable);

    }


    /**
     * setting up next button
     */
    private void setupFAB() {

    }

    @Override
    public void onItemClick(CharacterModel characterModel, ImageView checkBox) {
        Toast.makeText(getActivity(), characterModel.getCharacterName() + " is clicked", Toast.LENGTH_SHORT).show();
        if(characterModel.isSelectedCharacter()) {
            checkBox.setVisibility(View.GONE);
            characterModel.setSelectedCharacter(false);
        }
        else {
            checkBox.setVisibility(View.VISIBLE);
            characterModel.setSelectedCharacter(true);
        }
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
