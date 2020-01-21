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

import com.bumptech.glide.Glide;
import com.volunteerx.app.R;
import com.volunteerx.app.profile.binder.FollowBinder;

import mva2.adapter.MultiViewAdapter;

import static com.volunteerx.app.utils.Constants.FOLLOWERS_LIST;

public class FollowersChildFragment extends Fragment {

    private static final String TAG = "FollowingChildFragment";

    private RecyclerView rvFollowersList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_followers, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFollowersList = view.findViewById(R.id.followers_list);

        setupRV();

    }

    private void setupRV() {

        rvFollowersList.setLayoutManager(new LinearLayoutManager(getContext()));
        MultiViewAdapter adapter = new MultiViewAdapter();

        adapter.registerItemBinders(new FollowBinder(Glide.with(getContext()), FOLLOWERS_LIST));

    }
}
