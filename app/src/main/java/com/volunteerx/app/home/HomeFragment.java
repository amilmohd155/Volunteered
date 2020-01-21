/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/3/20 10:18 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/3/20 9:50 PM
 *
 */

package com.volunteerx.app.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.volunteerx.app.R;
import com.volunteerx.app.post.PostActivity;
import com.volunteerx.app.utils.BottomNavigationViewHelper;
import com.volunteerx.app.utils.ClickListener;

import static com.volunteerx.app.utils.Constants.FORUM_VIEW;
import static com.volunteerx.app.utils.Constants.NEARBY_VIEW;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private ClickListener listener;
    private FloatingActionButton pingFab;
    private BottomNavigationViewEx bottomNavigationViewEx;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        bottomNavigationViewEx = view.findViewById(R.id.bottomNavViewBar);
        pingFab = view.findViewById(R.id.ping_fab);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ImageView forumBtn = view.findViewById(R.id.forum_btn);
        FloatingActionButton  fab = view.findViewById(R.id.post_fab);

        setupBottomNavigationView();

        toolbar.setNavigationOnClickListener(view1 -> listener.buttonClick(NEARBY_VIEW));
        forumBtn.setOnClickListener(view12 -> listener.buttonClick(FORUM_VIEW));
        fab.setOnClickListener(v -> startActivity(new Intent(getActivity(), PostActivity.class)));

        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ClickListener) {
            listener = (ClickListener) getActivity();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ClickListener");
        }
    }

    /**
     * Setting up BottomNavigationView
     */
    private void setupBottomNavigationView() {

        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

        bottomNavigationViewEx.setCurrentItem(0);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(getContext(), bottomNavigationViewEx, pingFab);

    }
}
