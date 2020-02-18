/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/7/20 12:59 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/7/20 12:59 AM
 *
 */

package com.volunteerx.app.explore.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.volunteerx.app.R;
import com.volunteerx.app.explore.binder.ExCharacterBinder;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.OnItemClickListener;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExCharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExCharacterFragment extends Fragment {


    public ExCharacterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExCharacterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExCharacterFragment newInstance() {
        ExCharacterFragment fragment = new ExCharacterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view_only, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setNestedScrollingEnabled(true);
        MultiViewAdapter adapter = new MultiViewAdapter();
        ExCharacterBinder exCharacterBinder = new ExCharacterBinder(Glide.with(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);

        adapter.registerItemBinders(exCharacterBinder);

        ListSection<Integer> characterSection = new ListSection<>();
        characterSection.add(0);
        characterSection.add(0);
        characterSection.add(0);

        adapter.addSection(characterSection);

        characterSection.setOnItemClickListener((position, item) -> replaceFragment(TopCharacterStoriesFragment.newInstance(item), "TopCharacterStoriesFragment", getParentFragment().getFragmentManager()));


    }
}
