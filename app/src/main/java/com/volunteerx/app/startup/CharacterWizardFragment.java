/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:14 PM
 *
 */

package com.volunteerx.app.startup;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.volunteerx.app.R;
import com.volunteerx.app.api.APIInterface;
import com.volunteerx.app.api.ServiceGenerator;
import com.volunteerx.app.api.model.PureErrorResponse;
import com.volunteerx.app.binder.CharacterBinder;
import com.volunteerx.app.models.CharacterModel;
import com.volunteerx.app.utils.ClickListener;
import com.volunteerx.app.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.volunteerx.app.utils.Constants.CHAR_ANI;
import static com.volunteerx.app.utils.Constants.CHAR_ART;
import static com.volunteerx.app.utils.Constants.CHAR_CLD;
import static com.volunteerx.app.utils.Constants.CHAR_CVL;
import static com.volunteerx.app.utils.Constants.CHAR_DSR;
import static com.volunteerx.app.utils.Constants.CHAR_ECO;
import static com.volunteerx.app.utils.Constants.CHAR_EDU;
import static com.volunteerx.app.utils.Constants.CHAR_ENV;
import static com.volunteerx.app.utils.Constants.CHAR_HLH;
import static com.volunteerx.app.utils.Constants.CHAR_HMN;
import static com.volunteerx.app.utils.Constants.CHAR_POL;
import static com.volunteerx.app.utils.Constants.CHAR_POV;
import static com.volunteerx.app.utils.Constants.CHAR_SCI;
import static com.volunteerx.app.utils.Constants.CHAR_SCL;
import static com.volunteerx.app.utils.Constants.CHAR_WMN;
import static com.volunteerx.app.utils.Constants.MIN_NUM_CHARACTER;

public class CharacterWizardFragment extends Fragment implements ClickListener {

    private static final String TAG = "CharacterWizardFragment";

    private int userId;
    private List<CharacterModel> characters = new ArrayList<>();
    private ArrayList<Integer> characterSet = new ArrayList<>();
    private ListSection<CharacterModel> listSection;

    //UI
    private RecyclerView recyclerView;
    private FancyButton nextBtn;


    public CharacterWizardFragment() {
    }

    public static CharacterWizardFragment newInstance() {
        return new CharacterWizardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = SharedPrefManager.getInstance(getContext()).getUserId();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_character_wizard, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        nextBtn = view.findViewById(R.id.btn_next);

        initRecyclerView();
        setupNextButton();

        return view;

    }

    private void initRecyclerView() {

        Log.d(TAG, "initRecyclerView: creating recyclerView staggered horizontal scrolling");

        characters.add(new CharacterModel(CHAR_ANI, R.string.animal, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_ART, R.string.art, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_CLD, R.string.children, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_CVL, R.string.civil, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_DSR, R.string.disaster, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_ECO, R.string.economic, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_EDU, R.string.education, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_ENV, R.string.environment, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_HLH, R.string.health, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_HMN, R.string.human, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_POL, R.string.politics, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_POV, R.string.poverty, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_SCI, R.string.science, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_SCL, R.string.social, R.color.colorVolunteerX));
        characters.add(new CharacterModel(CHAR_WMN, R.string.women, R.color.colorVolunteerX));

        MultiViewAdapter adapter = new MultiViewAdapter();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(16));

        adapter.registerItemBinders(new CharacterBinder(getContext(),this));

        listSection = new ListSection<>();
        listSection.addAll(characters);

        adapter.addSection(listSection);
        adapter.setSelectionMode(Mode.MULTIPLE);

        listSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {

            if (selectedItems.size() >= MIN_NUM_CHARACTER) nextBtn.setEnabled(true);
            else nextBtn.setEnabled(false);

        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    //does nothing
    @Override
    public void buttonClick(int type) {

    }

    @Override
    public boolean onLongClick(int characterID) {

        final CharacterInfoBottomSheetFragment fragment = CharacterInfoBottomSheetFragment.newInstance(characterID);
        fragment.show(getChildFragmentManager(), fragment.getTag());

        return true;
    }

    private void setupNextButton() {

        nextBtn.setOnClickListener(view -> {
            //Doorway to Home activity
            //tODO handle db insertion and enter wizardComplete to sharedPreferences
            characterSet.clear();

            for (CharacterModel model: listSection.getSelectedItems()) {
                characterSet.add(model.getCharacterID());
            }

            APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);

            Call<PureErrorResponse> call = apiInterface.setCharacter(userId, characterSet);

            call.enqueue(new Callback<PureErrorResponse>() {
                @Override
                public void onResponse(Call<PureErrorResponse> call, Response<PureErrorResponse> response) {
                    if (!response.body().getError()) {
                        SharedPrefManager.getInstance(getContext()).wizardCompleted(true);
                        //TODO go to home page with welcome note
                    }
                }

                @Override
                public void onFailure(Call<PureErrorResponse> call, Throwable t) {

                }
            });

        });

    }

    private class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private final int mSpace;

        public SpacesItemDecoration(int space) {
            this.mSpace = space;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;

            // Add top margin only for the first item to avoid double mSpace between items
            if (parent.getChildAdapterPosition(view) == 0)
                outRect.top = mSpace;
        }
    }
}
