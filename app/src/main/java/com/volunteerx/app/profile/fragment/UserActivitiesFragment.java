/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/23/20 2:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/25/19 9:32 PM
 *
 */

package com.volunteerx.app.profile.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volunteerx.app.R;
import com.volunteerx.app.explore.model.ActivityCardModel;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class UserActivitiesFragment extends Fragment {

    private static final String TAG = "UserActivitiesFragment";

    private RecyclerView recyclerView;

    private MultiViewAdapter adapter;
//    private ActivityBinder activityBinder;
    private ListSection<ActivityCardModel> activitySection;

    public static UserActivitiesFragment newInstance() {

        Bundle args = new Bundle();

        UserActivitiesFragment fragment = new UserActivitiesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.snippet_card_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recyclerView = view.findViewById(R.id.recycler_view);





    }
}
