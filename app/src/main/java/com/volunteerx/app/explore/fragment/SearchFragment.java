/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/7/20 9:45 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/7/20 9:45 PM
 *
 */

package com.volunteerx.app.explore.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.volunteerx.app.R;
import com.volunteerx.app.explore.binder.DefaultSearchBinder;

import mva2.adapter.MultiViewAdapter;

public class SearchFragment extends Fragment {

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        MultiViewAdapter adapter = new MultiViewAdapter();

        recyclerView.setAdapter(adapter);

        DefaultSearchBinder binder;


    }
}
