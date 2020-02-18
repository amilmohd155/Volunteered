/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/13/20 12:38 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/13/20 12:38 AM
 *
 */

package com.volunteerx.app.profile.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.volunteerx.app.R;
import com.volunteerx.app.binder.CharacterBinder;
import com.volunteerx.app.models.CharacterModel;
import com.volunteerx.app.profile.binder.UserCharacterBinder;
import com.volunteerx.app.startup.CharacterWizardFragment;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;

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

public class UserCharacterBSFragment extends BottomSheetDialogFragment {

    private RecyclerView recyclerView;

    public UserCharacterBSFragment() {
    }

    public static UserCharacterBSFragment newInstance() {

        Bundle args = new Bundle();

        UserCharacterBSFragment fragment = new UserCharacterBSFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_char_bs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);

        initRecyclerView();

    }

    private void initRecyclerView() {

        ArrayList<CharacterModel> characters = new ArrayList<>();

        characters.add(new CharacterModel(CHAR_ANI, R.string.animal, R.color.colorAnimal));
        characters.add(new CharacterModel(CHAR_ART, R.string.art, R.color.colorArt));
        characters.add(new CharacterModel(CHAR_CLD, R.string.children, R.color.colorChildren));
        characters.add(new CharacterModel(CHAR_CVL, R.string.civil, R.color.colorCivil));
        characters.add(new CharacterModel(CHAR_DSR, R.string.disaster, R.color.colorDisaster));
        characters.add(new CharacterModel(CHAR_ECO, R.string.economic, R.color.colorEconomics));
//        characters.add(new CharacterModel(CHAR_EDU, R.string.education, R.color.colorEducation));
//        characters.add(new CharacterModel(CHAR_ENV, R.string.environment, R.color.colorEnvironment));
//        characters.add(new CharacterModel(CHAR_HLH, R.string.health, R.color.colorHealth));
//        characters.add(new CharacterModel(CHAR_HMN, R.string.human, R.color.colorHuman));
//        characters.add(new CharacterModel(CHAR_POL, R.string.politics, R.color.colorPolitics));
//        characters.add(new CharacterModel(CHAR_POV, R.string.poverty, R.color.colorPoverty));
//        characters.add(new CharacterModel(CHAR_SCI, R.string.science, R.color.colorScience));
//        characters.add(new CharacterModel(CHAR_SCL, R.string.social, R.color.colorSocial));
//        characters.add(new CharacterModel(CHAR_WMN, R.string.women, R.color.colorWomen));

        MultiViewAdapter adapter = new MultiViewAdapter();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(8));

        adapter.registerItemBinders(new UserCharacterBinder(getContext()));

        ListSection<CharacterModel> listSection = new ListSection<>();
        listSection.addAll(characters);

        adapter.addSection(listSection);

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
            outRect.top = mSpace;
        }
    }

}
