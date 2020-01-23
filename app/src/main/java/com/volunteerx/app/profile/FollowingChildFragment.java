/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/21/20 7:48 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/25/19 9:32 PM
 *
 */

package com.volunteerx.app.profile;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.volunteerx.app.R;
import com.volunteerx.app.profile.binder.FollowBinder;
import com.volunteerx.app.profile.binder.HeaderItemBinder;
import com.volunteerx.app.profile.model.ProfilesModel;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.HeaderSection;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;

public class FollowingChildFragment extends Fragment {

    private static final String TAG = "FollowingChildFragment";

    private RecyclerView rvFollowingList;
    private List<ProfilesModel> profilesList, suggestionList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_follow_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFollowingList = view.findViewById(R.id.follow_list);

        profilesList = new ArrayList<>();
        suggestionList = new ArrayList<>();

        setupRV();

    }

    private void setProfiles(){

        profilesList.add(new ProfilesModel("Christopher Hut",
                "chrity@123",
                "https://i.postimg.cc/N0yzrPJN/Screenshot-10.png",
                true, false, false));


        suggestionList.add(new ProfilesModel("Lorem ipsum",
                "lorem@ipsum123",
                "https://i.postimg.cc/SRGTh0RK/Screenshot-12.png",
                false, false, true));

    }

    private void setupRV() {

        setProfiles();

        rvFollowingList.setLayoutManager(new LinearLayoutManager(getContext()));
        MultiViewAdapter adapter = new MultiViewAdapter();

        FollowBinder followBinder = new FollowBinder(Glide.with(getContext()), getContext());
        HeaderItemBinder headerBinder = new HeaderItemBinder();

        adapter.setSelectionMode(Mode.SINGLE);
        adapter.unRegisterAllItemBinders();

        adapter.registerItemBinders(followBinder, headerBinder);

        rvFollowingList.setAdapter(adapter);

        ListSection<ProfilesModel> followingSection = new ListSection<>();
        HeaderSection<String> headerSection = new HeaderSection<>(getContext().getString(R.string.suggestions));
        ListSection<ProfilesModel>  suggestionsSection = new ListSection<>();

        followingSection.addAll(profilesList);
//        headerSection.addSection(suggestionsSection);
        suggestionsSection.addAll(suggestionList);


        followingSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {

            //go to profile of selected profile

            Toast.makeText(getContext(), "profile selected " + item.getUsername(), Toast.LENGTH_SHORT).show();

        });

        suggestionsSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {
            // go to profile of selected profile
            Toast.makeText(getContext(), "profile selected " + item.getUsername(), Toast.LENGTH_SHORT).show();
        });



        adapter.addSection(followingSection);
        adapter.addSection(headerSection);
        adapter.addSection(suggestionsSection);

    }
}
