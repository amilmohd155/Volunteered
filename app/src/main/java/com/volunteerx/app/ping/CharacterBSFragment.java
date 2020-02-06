/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/3/20 10:41 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/3/20 10:41 PM
 *
 */

package com.volunteerx.app.ping;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.volunteerx.app.R;
import com.volunteerx.app.binder.CharacterTypeBBinder;
import com.volunteerx.app.models.CharacterModel;
import com.volunteerx.app.utils.RoundedCorner;

import java.util.concurrent.atomic.AtomicInteger;

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

public class CharacterBSFragment extends BottomSheetDialogFragment {

    private static final String TAG = "CharacterBSFragment";

    private RecyclerView recyclerView;
    private FloatingActionButton nextFab;

    private MultiViewAdapter adapter = new MultiViewAdapter();
    private ListSection<CharacterModel> characterSection = new ListSection<>();
    private OnFragmentInteractionListener listener;


    public CharacterBSFragment() {
    }

    public static CharacterBSFragment newInstance() {

        Bundle args = new Bundle();

        CharacterBSFragment fragment = new CharacterBSFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character_bs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RoundedCorner.setRoundedCorner(view, 20, RoundedCorner.ROUNDED_TOP);

        nextFab = view.findViewById(R.id.next_fab);
        recyclerView = view.findViewById(R.id.character_grid);



        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.character_spacing)));

        recyclerView.setAdapter(adapter);

        adapter.registerItemBinders(new CharacterTypeBBinder(getContext(), Glide.with(this)));

        characterSection.add(new CharacterModel(CHAR_ANI, R.string.animal, R.drawable.art));
        characterSection.add(new CharacterModel(CHAR_ART, R.string.art, R.drawable.astronaut));
        characterSection.add(new CharacterModel(CHAR_CLD, R.string.children, R.drawable.fairy_tales));
        characterSection.add(new CharacterModel(CHAR_CVL, R.string.civil, R.drawable.fernando_nunes));
        characterSection.add(new CharacterModel(CHAR_DSR, R.string.disaster, R.drawable.art));
        characterSection.add(new CharacterModel(CHAR_ECO, R.string.economic, R.drawable.fernando_nunes));
        characterSection.add(new CharacterModel(CHAR_EDU, R.string.education, R.drawable.astronaut));
        characterSection.add(new CharacterModel(CHAR_ENV, R.string.environment, R.drawable.thought));
        characterSection.add(new CharacterModel(CHAR_HLH, R.string.health, R.drawable.car_wan));
        characterSection.add(new CharacterModel(CHAR_HMN, R.string.human, R.drawable.astronaut));
        characterSection.add(new CharacterModel(CHAR_POL, R.string.politics, R.drawable.fairy_tales));
        characterSection.add(new CharacterModel(CHAR_POV, R.string.poverty, R.drawable.astronaut));
        characterSection.add(new CharacterModel(CHAR_SCI, R.string.science, R.drawable.fernando_nunes));
        characterSection.add(new CharacterModel(CHAR_SCL, R.string.social, R.drawable.fairy_tales));
        characterSection.add(new CharacterModel(CHAR_WMN, R.string.women, R.drawable.thought));

        adapter.addSection(characterSection);
        adapter.setSelectionMode(Mode.MULTIPLE);

        characterSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {
            if (!selectedItems.isEmpty()) {
                nextFab.show();
            }
            else nextFab.hide();

        });

        nextFab.setOnClickListener(view1 -> listener.onBSFragmentInteraction());

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(getParentFragment().getClass().getName()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }

    public interface OnFragmentInteractionListener{
        void onBSFragmentInteraction();
    }

}
