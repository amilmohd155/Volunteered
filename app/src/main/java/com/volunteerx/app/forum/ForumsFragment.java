/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/10/20 1:00 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/10/20 12:56 AM
 *
 */

package com.volunteerx.app.forum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.volunteerx.app.R;
import com.volunteerx.app.forum.binder.ForumListBinder;
import com.volunteerx.app.forum.room.ForumRoomActivity;
import com.volunteerx.app.forum.room.fragment.ForumRoomFragment;
import com.volunteerx.app.models.ForumListModel;
import com.volunteerx.app.utils.ClickListener;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;

import static com.volunteerx.app.utils.Constants.HOME_VIEW;

public class ForumsFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "ForumsFragment";

    private ViewGroup mContainer;
    private ClickListener listener;
    private RecyclerView forumListView;
    private List<ForumListModel> forums = new ArrayList<>();


    public static ForumsFragment newInstance() {
        return new ForumsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContainer = container;

        View view = inflater.inflate(R.layout.fragment_forum, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        forumListView = view.findViewById(R.id.forums_list);

        toolbar.setNavigationOnClickListener(view1 -> listener.buttonClick(HOME_VIEW));

        setupForumList();

        return view;

    }

    private void getForums(){

        forums.add(new ForumListModel(1,
                getString(R.string.john_doe),
                "Golden Bo",
                "20 dec 2019",
                "https://www.gstatic.com/webp/gallery/1.jpg",
                34,
                true,
                false));

    }

    private void setupForumList() {

        //Todo recycler view Forum list implementation
        Log.d(TAG, "setupForumList: recycler starting");

        MultiViewAdapter adapter = new MultiViewAdapter();

        Runnable runnable = this::getForums;
        runnable.run();

        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);

        adapter.registerItemBinders(new ForumListBinder(getContext(), Glide.with(this).applyDefaultRequestOptions(options)));

        ListSection<ForumListModel> listSection = new ListSection<>();
        listSection.addAll(forums);

        adapter.addSection(listSection);
        adapter.setSelectionMode(Mode.SINGLE);


        listSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {

//            move to new fragment
            Intent intent = new Intent(getActivity(), ForumRoomActivity.class);
            intent.putExtra("Forum ID", item.getForumId());
            startActivity(intent);

        });


        forumListView.setLayoutManager(new LinearLayoutManager(getContext()));
        forumListView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
        }

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

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }
}
