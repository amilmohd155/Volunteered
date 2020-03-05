/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/13/20 12:38 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/13/20 12:38 AM
 *
 */

package com.volunteerx.app.profile.fragment;

import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.volunteerx.app.R;
import com.volunteerx.app.models.CharacterModel;
import com.volunteerx.app.models.User;
import com.volunteerx.app.profile.binder.UserCharacterBinder;
import com.volunteerx.app.utils.CharacterHelper;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

import static com.volunteerx.app.utils.Constants.CHAR_ANI;
import static com.volunteerx.app.utils.Constants.CHAR_ART;
import static com.volunteerx.app.utils.Constants.CHAR_CLD;
import static com.volunteerx.app.utils.Constants.CHAR_CVL;
import static com.volunteerx.app.utils.Constants.CHAR_DSR;
import static com.volunteerx.app.utils.Constants.CHAR_ECO;

public class UserCharacterBSFragment extends BottomSheetDialogFragment {

    private static final String PARAM_1 = "User_Value";

    private RecyclerView recyclerView;

    private User user;

    public UserCharacterBSFragment() {
    }

    public static UserCharacterBSFragment newInstance(User user) {

        Bundle args = new Bundle();
        args.putParcelable(PARAM_1, user);

        UserCharacterBSFragment fragment = new UserCharacterBSFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(PARAM_1);
        }
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

        ArrayList<CharacterModel> characters;
        characters = new ArrayList<>();

//        characters.add(new CharacterModel(CHAR_ANI, R.string.animal, R.color.colorAnimal));


        MultiViewAdapter adapter = new MultiViewAdapter();

        AsyncTask.execute(() -> {

            CharacterHelper helper = new CharacterHelper(getContext());
            for (int characterID : user.getUserCharacter()) {
                characters.add(new CharacterModel(characterID, helper.getCharacterColor(characterID), helper.getCharacterName(characterID)));
            }

            helper.recycle();

            adapter.registerItemBinders(new UserCharacterBinder(getContext()));

            ListSection<CharacterModel> listSection = new ListSection<>();
            listSection.addAll(characters);

            adapter.addSection(listSection);

            getActivity().runOnUiThread(() -> {

                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.addItemDecoration(new SpacesItemDecoration(8));

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
            outRect.top = mSpace;
        }
    }

}
