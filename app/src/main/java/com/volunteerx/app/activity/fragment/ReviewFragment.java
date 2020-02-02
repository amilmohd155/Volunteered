/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/30/20 1:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/30/20 1:37 PM
 *
 */

package com.volunteerx.app.activity.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.volunteerx.app.R;
import com.volunteerx.app.activity.binder.ReviewBinder;
import com.volunteerx.app.activity.model.ReviewModel;

import java.util.ArrayList;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;

public class ReviewFragment extends Fragment {

    private RecyclerView rvReview;
    private ListSection<ReviewModel> section;
    private ArrayList<ReviewModel> reviewModels = new ArrayList<>();
    private MultiViewAdapter adapter = new MultiViewAdapter();

    private ReviewAsync async;

    public ReviewFragment() {
    }

    public static ReviewFragment newInstance() {

        Bundle args = new Bundle();

        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        async = new ReviewAsync();
        section = new ListSection<>();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar_review);
        toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }else if (getParentFragment()!= null) {
                //Todo fragment back function
            }
        });

        rvReview = view.findViewById(R.id.review_list);

        async.execute();

        setRvReview();

    }

    private void  setRvReview(){

        rvReview.setLayoutManager(new LinearLayoutManager(getContext()));

        ReviewBinder reviewBinder = new ReviewBinder(Glide.with(getContext()));

        adapter.registerItemBinders(reviewBinder);

        adapter.addSection(section);

        rvReview.setAdapter(adapter);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        async = null;
    }

    //trail
    class ReviewAsync extends AsyncTask<Void, Integer, String> {


        @Override
        protected String doInBackground(Void... voids) {

            reviewModels.add(new ReviewModel(1,
                    "https://www.gstatic.com/webp/gallery/1.jpg",
                    "John Doe",
                    "john_doe123",
                    4.5f,
                    "Android is a mobile operating system based on a modified version of the Linux kernel and other open source software, designed primarily for touchscreen mobile devices such as smart phones and tablets."));

            reviewModels.add(new ReviewModel(1,
                    "https://i.postimg.cc/SRGTh0RK/Screenshot-12.png",
                    "Jane Doe",
                    "jane@qwer",
                    4.0f,
                    "Android is a mobile operating system developed by Google. It is used by several smartphones and tablets. ... The Android operating system (OS) is based on the Linux kernel."));

            section.addAll(reviewModels);
            adapter.notifyDataSetChanged();

            return null;
        }
    }

}
