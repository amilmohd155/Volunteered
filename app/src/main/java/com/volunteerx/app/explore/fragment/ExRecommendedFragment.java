/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/24/20 2:10 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/24/20 2:10 AM
 *
 */

package com.volunteerx.app.explore.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volunteerx.app.R;
import com.volunteerx.app.explore.binder.ExHeaderItemBinder;
import com.volunteerx.app.explore.binder.HorizontalScrollBinder;
import com.volunteerx.app.explore.binder.PostCardBinder;
import com.volunteerx.app.explore.binder.PostCardModel;
import com.volunteerx.app.explore.model.ActivityCardModel;
import com.volunteerx.app.explore.model.EventCardModel;
import com.volunteerx.app.explore.model.ExHeaderTextModel;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.ItemSection;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.NestedSection;

public class ExRecommendedFragment extends Fragment {

    private static final String TAG = "ExRecommendedFragment";

    private List<PostCardModel> postCardModels = new ArrayList<>();
    private List<ActivityCardModel> activityCardModels = new ArrayList<>();
    private List<EventCardModel> eventCardModels = new ArrayList<>();

    private RecyclerView recyclerView;


    public ExRecommendedFragment() {
    }

    public static ExRecommendedFragment newInstance() {

        Bundle args = new Bundle();

        ExRecommendedFragment fragment = new ExRecommendedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view_swipeable, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: starting rfragmet");

        recyclerView = view.findViewById(R.id.recycler_view);

        setupLayout();

    }

    private void getData() {

        postCardModels.add(new PostCardModel(
                "https://i.postimg.cc/zvgC906b/Assassin-s-Creed-Odyssey-10-29-2019-6-20-54-PM.png",
                "John Doe",
                "Assassin's Creed is an action-adventure stealth video game franchise created by Patrice Désilets, Jade Raymond and Corey May, developed and published by Ubisoft using the game engine Anvil",
                "https://i.postimg.cc/zvgC906b/Assassin-s-Creed-Odyssey-10-29-2019-6-20-54-PM.png",
                100,
                2,
                false,
                true,
                new int[] {1, 2, 3, 14, 12, 11}
        ));

        postCardModels.add(new PostCardModel(
                "https://i.postimg.cc/zvgC906b/Assassin-s-Creed-Odyssey-10-29-2019-6-20-54-PM.png",
                "John Doe",
                "Assassin's Creed is an action-adventure stealth video game franchise created by Patrice Désilets, Jade Raymond and Corey May, developed and published by Ubisoft using the game engine Anvil",
                "https://i.postimg.cc/zvgC906b/Assassin-s-Creed-Odyssey-10-29-2019-6-20-54-PM.png",
                100,
                2,
                true,
                true,
                new int[] {1, 2, 3, 14, 12, 11}
        ));



        activityCardModels.add(new ActivityCardModel(
                "Execute Order 66",
                "Star Wars",
                "https://i.postimg.cc/d3FHpQqX/Screenshot-14.png",
                2.5f,
                2
        ));

        activityCardModels.add(new ActivityCardModel(
                "Star Wars - The clone wars",
                "Star Wars",
                "https://i.postimg.cc/QdYzCrb9/Screenshot-11.png",
                5.0f,
                123450
        ));
        activityCardModels.add(new ActivityCardModel(
                "Alexander Describe Heart Donation Campaign",
                "UNESCO",
                "https://www.mymyeo.com/uploads/unescologoen-SODPrdlv1BQVNh8xpBNVV4TtmN0ul3xS.jpg",
                4.5f,
                23456
        ));

    }

    private void setupLayout() {

        getData();

        MultiViewAdapter adapter = new MultiViewAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ExHeaderItemBinder headerItemBinder = new ExHeaderItemBinder();

        PostCardBinder postCardBinder = new PostCardBinder(getContext(), Glide.with(getContext()));

        HorizontalScrollBinder horizontalScrollBinder = new HorizontalScrollBinder(Glide.with(getContext()), (ArrayList<ActivityCardModel>)activityCardModels);
//        EventCardBinder eventCardBinder = new EventCardBinder(getContext(), Glide.with(getContext()));

        recyclerView.addItemDecoration(adapter.getItemDecoration());

        adapter.unRegisterAllItemBinders();
        adapter.registerItemBinders(headerItemBinder, postCardBinder, horizontalScrollBinder);

        ItemSection<ExHeaderTextModel> headerSection1 = new ItemSection<>();
        headerSection1.setItem(new ExHeaderTextModel("Posts for you", "Discover"));

        ItemSection<ExHeaderTextModel> headerSection2 = new ItemSection<>();
        headerSection2.setItem(new ExHeaderTextModel("Activity you would be interested in", "Find more"));

        ListSection<PostCardModel> postCardSection = new ListSection<>();
        postCardSection.addAll(postCardModels);

        ItemSection<Integer> activityCardSection = new ItemSection<>();
        activityCardSection.setItem(1);

        NestedSection nestedSection = new NestedSection();

        nestedSection.addSection(headerSection1);
        nestedSection.addSection(postCardSection);
        nestedSection.addSection(headerSection2);
        nestedSection.addSection(activityCardSection);


        adapter.addSection(nestedSection);
        adapter.setHasStableIds(true);


        recyclerView.setItemViewCacheSize(10);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);


    }

}
