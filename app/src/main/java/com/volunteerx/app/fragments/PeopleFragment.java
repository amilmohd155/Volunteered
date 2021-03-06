/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.fragments;

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
import com.volunteerx.app.binder.PeopleBinder;
import com.volunteerx.app.binder.PollBinder;
import com.volunteerx.app.models.PeopleListModel;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class PeopleFragment extends Fragment {

    private static final String TAG = "PeopleFragment";

    //UI
    private RecyclerView recyclerView;

    //Variable
    private ArrayList<PeopleListModel> model = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_people, container, false);

        recyclerView = view.findViewById(R.id.people_list_view);

//        model.add(new PeopleListModel("ABC", "abc123", R.drawable.car_wan, true, 1));
//        model.add(new PeopleListModel("CBE", "cbe344", R.drawable.astronaut, true, 1));
//        model.add(new PeopleListModel("ASD", "asd668", R.drawable.avatar0, false,1));
//        model.add(new PeopleListModel("QWE", "qwe490", R.drawable.car_wan, true, 1));

        initView();

        return view;

    }

    private void initView() {

        Log.d(TAG, "initView: initializing recycler_view");

        MultiViewAdapter adapter = new MultiViewAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.registerItemBinders(new PeopleBinder(getContext(), Glide.with(getContext())));

        ListSection<PeopleListModel> listSection = new ListSection<>();
        listSection.addAll(model);

        adapter.addSection(listSection);

    }
}
