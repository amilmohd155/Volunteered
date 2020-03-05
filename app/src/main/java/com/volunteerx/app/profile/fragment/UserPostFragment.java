/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/23/20 2:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/25/19 9:32 PM
 *
 */

package com.volunteerx.app.profile.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.volunteerx.app.MediaSwipeVew.media.ImageModel;
import com.volunteerx.app.MediaSwipeVew.media.MediaModel;
import com.volunteerx.app.R;
import com.volunteerx.app.binder.PostBinder;
import com.volunteerx.app.models.PostModel;
import com.volunteerx.app.profile.SectionDecorator;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class UserPostFragment extends Fragment {

    private static final String TAG = "UserPostFragment";

    private MultiViewAdapter adapter;
    private ListSection<PostModel> postSection;
    private PostBinder postBinder;
    private LinearLayoutManager layoutManager;

    private RecyclerView recyclerView;

    public UserPostFragment() {
    }

    public static UserPostFragment newInstance() {

        Bundle args = new Bundle();

        UserPostFragment fragment = new UserPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {

        }

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new MultiViewAdapter();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view_only, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setNestedScrollingEnabled(true);


        setupPostView();
    }

    private void setupPostView() {

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new MultiViewAdapter();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(adapter.getItemDecoration());

        postBinder = new PostBinder(getContext(), Glide.with(this));
        postSection = new ListSection<>();

        postSection.addDecorator(new SectionDecorator(adapter, getContext(), 20));

        adapter.registerItemBinders(postBinder);
//        adapter.addSection(postSection);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter = null;
        layoutManager = null;
    }
}
