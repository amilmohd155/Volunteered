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
import com.volunteerx.app.binder.CharacterBinder;
import com.volunteerx.app.models.CharacterModel;
import com.volunteerx.app.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;
import mva2.adapter.util.OnSelectionChangedListener;

import static com.volunteerx.app.utils.Constants.MIN_NUM_CHARACTER;

public class CharacterWizardFragment extends Fragment implements ClickListener {

    private static final String TAG = "CharacterWizardFragment";

    private List<CharacterModel> characters;

    //UI
    private RecyclerView recyclerView;
    private FancyButton nextBtn;

    public CharacterWizardFragment() {
    }

    public static CharacterWizardFragment newInstance() {
        return new CharacterWizardFragment();
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

        Log.d(TAG, "initRecyclerView: creating recylerview staggered horizontal scrolling");

        characters = new ArrayList<>();

//        characters.add(new CharacterModelOld("Animal", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Art", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Children", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Civil", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Disaster", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Economic", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Education", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Environment", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Health", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Human rights", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Politics", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Poverty", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Technology", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Social", R.color.color_volunteer_x));
//        characters.add(new CharacterModelOld("Women", R.color.color_volunteer_x));

        characters.add(new CharacterModel(0, R.string.app_name, R.color.colorVolunteerX));
        characters.add(new CharacterModel(1, R.string.art, R.color.colorVolunteerX));
        characters.add(new CharacterModel(2, R.string.science, R.color.colorVolunteerX));
        //TOdo complete the characters

        MultiViewAdapter adapter = new MultiViewAdapter();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(16));

        adapter.registerItemBinders(new CharacterBinder(getContext(),this));

        ListSection<CharacterModel> listSection = new ListSection<>();
        listSection.addAll(characters);

        adapter.addSection(listSection);
        adapter.setSelectionMode(Mode.MULTIPLE);

        listSection.setOnSelectionChangedListener(new OnSelectionChangedListener<CharacterModel>() {
            @Override
            public void onSelectionChanged(CharacterModel item, boolean isSelected, List<CharacterModel> selectedItems) {

                if (selectedItems.size() >= MIN_NUM_CHARACTER) enableButton(true);
                else enableButton(false);

            }
        });


    }

    private void enableButton(boolean val) {
        if (val) {
            nextBtn.setEnabled(true);
            nextBtn.setBackgroundColor(getContext().getColor(R.color.colorButtonBGEnable));
            nextBtn.setTextColor(getContext().getColor(R.color.colorButtonTextEnabled));

        }else {
            nextBtn.setEnabled(false);
            nextBtn.setBackgroundColor(getContext().getColor(R.color.colorButtonBGDisabled));
            nextBtn.setTextColor(getContext().getColor(R.color.colorButtonTextDisabled));

        }
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

        final CharacterBottomSheetFragment fragment = CharacterBottomSheetFragment.newInstance(characterID);
        fragment.show(getChildFragmentManager(), fragment.getTag());

        return true;
    }

    private void setupNextButton() {

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Doorway to Home activity
            }
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
