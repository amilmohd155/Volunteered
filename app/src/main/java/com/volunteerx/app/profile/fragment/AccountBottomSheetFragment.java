/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/23/20 2:38 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/3/20 11:45 PM
 *
 */

package com.volunteerx.app.profile.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.volunteerx.app.R;
import com.volunteerx.app.models.User;
import com.volunteerx.app.profile.DividerDecorator;
import com.volunteerx.app.profile.binder.AccountBinder;
import com.volunteerx.app.profile.model.AccountModel;
import com.volunteerx.app.utils.GlideApp;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.decorator.Decorator;
import mva2.extension.decorator.DividerDecoration;

//Todo Logical inconsistency found
public class AccountBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String TAG = "AccountBottomSheetFrag";
    private static final String PARAM_1 = "UserValue";

    private RecyclerView recyclerView;
    private LinearLayout llAdd;

    private MultiViewAdapter adapter;

    private User user;
    private FirebaseFirestore rootDef = FirebaseFirestore.getInstance();


    public AccountBottomSheetFragment() {
    }

    public static AccountBottomSheetFragment newInstance(User user) {
        
        Bundle args = new Bundle();
        args.putParcelable(PARAM_1, user);
        
        AccountBottomSheetFragment fragment = new AccountBottomSheetFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(PARAM_1);
        }
        adapter = new MultiViewAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_account_bottom_sheet, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        llAdd = view.findViewById(R.id.ll_add);

//        listSection.add(new AccountModel("https://i.postimg.cc/9Q6FvPRv/thought.jpg",
//                "JohnDoe",
//                true));


        AsyncTask.execute(() -> {

            AccountBinder accountBinder = new AccountBinder(getContext());
            accountBinder.addDecorator(new DividerDecorator(adapter, getContext()));

            adapter.registerItemBinders(accountBinder);

            ListSection<AccountModel> listSection = new ListSection<>();

            listSection.add(new AccountModel(user.getPhotoUrl(), user.getName(), true));

            //check if linked account exits

            adapter.addSection(listSection);

            getActivity().runOnUiThread(() -> {

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(adapter.getItemDecoration());

            });
        });

    }

}
