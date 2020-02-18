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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.volunteerx.app.MediaSwipeVew.media.ImageModel;
import com.volunteerx.app.MediaSwipeVew.media.MediaModel;
import com.volunteerx.app.MediaSwipeVew.media.VideoModel;
import com.volunteerx.app.R;
import com.volunteerx.app.binder.PostBinder;
import com.volunteerx.app.models.PostModel;
import com.volunteerx.app.post.PostActivity;
import com.volunteerx.app.post.PostFragment;
import com.volunteerx.app.utils.BottomNavigationViewHelper;
import com.volunteerx.app.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

import static com.volunteerx.app.utils.Constants.FORUM_VIEW;
import static com.volunteerx.app.utils.Constants.NEARBY_VIEW;
import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private OnFragmentInteractionListener listener;
    private FloatingActionButton pingFab;
    private BottomNavigationViewEx bottomNavigationViewEx;
    private RecyclerView recyclerView;
    private MultiViewAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MultiViewAdapter();

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
        recyclerView = view.findViewById(R.id.home_recycler_view);

        setupBottomNavigationView();
        setRecyclerView();



        toolbar.setNavigationOnClickListener(view1 -> listener.onFragmentInteraction(NEARBY_VIEW));
        forumBtn.setOnClickListener(view2 -> listener.onFragmentInteraction(FORUM_VIEW));
        fab.setOnClickListener(v -> {
            replaceFragment(PostFragment.newInstance(), "PostFragment", getParentFragment().getFragmentManager());
//            startActivity(new Intent(getActivity(), PostActivity.class));
        });

        return view;

    }

    private void setRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        PostBinder postBinder = new PostBinder(getContext(), Glide.with(this));

        adapter.registerItemBinders(new PostBinder(getContext(), Glide.with(this)));

        ListSection<PostModel> postSection = new ListSection<>();

        ArrayList<MediaModel> mediaModels = new ArrayList<>();
        mediaModels.add(new ImageModel("https://i.postimg.cc/zvgC906b/Assassin-s-Creed-Odyssey-10-29-2019-6-20-54-PM.png"));
        mediaModels.add(new ImageModel("https://i.postimg.cc/N0RfYyRL/fairy-tales.png"));
        mediaModels.add(new ImageModel("https://i.postimg.cc/9Q6FvPRv/thought.jpg"));

        if (postSection.size() == 0){

            postSection.add(new PostModel(mediaModels,
                    "https://i.postimg.cc/DZLpx4KL/Screenshot-13.png",
                    "Jane Doe",
                    "Android is a mobile operating system based on a modified version of the Linux kernel and other open source software, designed primarily for touchscreen mobile devices such as smartphones and tablets.",
                    "02-01-2020"));

            postSection.add(new PostModel(mediaModels,
                    "https://i.postimg.cc/DZLpx4KL/Screenshot-13.png",
                    "Jane Doe",
                    "Android is a mobile operating system based on a modified version of the Linux kernel and other open source software, designed primarily for touchscreen mobile devices such as smartphones and tablets.",
                    "02-01-2020"));

        }

        adapter.addSection(postSection);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) getParentFragment();
        } else {

            throw new RuntimeException(getParentFragment().getClass().getName()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * Setting up BottomNavigationView
     */
    private void setupBottomNavigationView() {

        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

        bottomNavigationViewEx.setCurrentItem(0);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(getContext(), bottomNavigationViewEx, pingFab, getParentFragment().getFragmentManager());

    }
}
