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
import com.volunteerx.app.profile.binder.EmptyStateBinder;
import com.volunteerx.app.profile.binder.FollowBinder;
import com.volunteerx.app.profile.binder.HeaderItemBinder;
import com.volunteerx.app.profile.model.ProfilesModel;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.HeaderSection;
import mva2.adapter.ItemSection;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;

public class FollowersChildFragment extends Fragment {

    private static final String TAG = "FollowingChildFragment";

    private ViewGroup container;
    private RecyclerView rvFollowersList;
    private List<ProfilesModel> profilesList, suggestionList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.container = container;

        return inflater.inflate(R.layout.fragment_follow_container, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFollowersList = view.findViewById(R.id.follow_list);

        profilesList = new ArrayList<>();
        suggestionList = new ArrayList<>();

        setupRV();

    }

    private void setProfiles(){

        profilesList.add(new ProfilesModel("Christopher Hut",
                "chrity@123",
                "https://i.postimg.cc/N0yzrPJN/Screenshot-10.png",
                false,
                true,
                false));


        suggestionList.add(new ProfilesModel("Lorem ipsum",
                "lorem@ipsum123",
                "https://i.postimg.cc/SRGTh0RK/Screenshot-12.png",
                false, false, true));

    }

    private void setupRV() {

        setProfiles();

        rvFollowersList.setLayoutManager(new LinearLayoutManager(getContext()));
        MultiViewAdapter adapter = new MultiViewAdapter();

        FollowBinder followBinder = new FollowBinder(Glide.with(getContext()), getContext());
        HeaderItemBinder headerBinder = new HeaderItemBinder();

        adapter.setSelectionMode(Mode.SINGLE);
        adapter.unRegisterAllItemBinders();

        adapter.registerItemBinders(followBinder, headerBinder, new EmptyStateBinder(R.layout.layout_empty_followers));

        rvFollowersList.setAdapter(adapter);

        ListSection<ProfilesModel> followersSection = new ListSection<>();
        HeaderSection<String> headerSection = new HeaderSection<>(getContext().getString(R.string.suggestions));
        ListSection<ProfilesModel>  suggestionsSection = new ListSection<>();
        ItemSection<String> emptyStateSection = new ItemSection<>();


        followersSection.addAll(profilesList);
//        headerSection.addSection(suggestionsSection);
        suggestionsSection.addAll(suggestionList);

        if (profilesList.isEmpty()) {

            followersSection.hideSection();
            emptyStateSection.showSection();

        }else {
            followersSection.showSection();
            emptyStateSection.hideSection();
        }

        followersSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {

            //go to profile of selected profile

            Toast.makeText(getContext(), "profile selected " + item.getUsername(), Toast.LENGTH_SHORT).show();

        });

        suggestionsSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {
            // go to profile of selected profile
            Toast.makeText(getContext(), "profile selected " + item.getUsername(), Toast.LENGTH_SHORT).show();
        });



        adapter.addSection(followersSection);
        adapter.addSection(emptyStateSection);
        adapter.addSection(headerSection);
        adapter.addSection(suggestionsSection);

    }

}
