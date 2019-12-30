/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:23 PM
 *
 */

package com.volunteerx.app.forums;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.models.ForumListModel;

import java.util.ArrayList;

public class ForumsActivity extends AppCompatActivity {

    private static final String TAG = "ForumsActivity";

    private final Context mContext = ForumsActivity.this;
    private static final float MINIMUM = 25;


    //Variable
    private ArrayList<ForumListModel> forumListModels = new ArrayList<>();
    private int scrollDist = 0;
    private boolean isVisible = true;

    //Widget
    RecyclerView recyclerView;
    TextView searchView;
    ImageView backButton;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forums);

        Log.d(TAG, "onCreate: Starting");

        recyclerView = (RecyclerView) findViewById(R.id.forum_list_recycler_view);
        searchView = (TextView) findViewById(R.id.searchView);
        backButton = (ImageView) findViewById(R.id.back_btn);

        setupForumListRecyclerView();
        setupbackButton();


    }

    private void setupbackButton() {

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setupForumListRecyclerView() {

        Log.d(TAG, "setupForumListRecyclerView: Starting");

        forumListModels.add(new ForumListModel(R.drawable.user,"Save Ganga", "12hrs ago", null, 0, null));
        forumListModels.add(new ForumListModel(R.drawable.avatar0,"Lorem Ipsum", "Closed", "Job is done", R.drawable.user, "user"));

        new Thread(new Runnable() {
            @Override
            public void run() {

                ForumListRecyclerView adapter = new ForumListRecyclerView(mContext, forumListModels);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            }
        }).start();

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerView, new ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//                if (position == 0) {
//                    Intent intent = new Intent(mContext, SearchActivity.class);
//                    mContext.startActivity(intent);
//                }
//                else {
//                    Intent intent = new Intent(mContext, ForumRoomActivity.class);
//                    mContext.startActivity(intent);
//                }
//
//            }

//            @Override
//            public void onLongClick(View view, int position) {
//
////Todo long click dialog box
//
////                if (position != 0) {
////
////                    Log.d(TAG, "onLongClick: DialogFragment Opening");
////
////                    dialog = new Dialog(mContext);
////                    dialog.setContentView(R.layout.dialog_more_options);
////                    if (dialog.getWindow() != null) {
////                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////                        dialog.show();
////                    }
////
////                    TextView deleteForum = (TextView) dialog.findViewById(R.id.delete_forum);
////                    TextView reportForum = (TextView) dialog.findViewById(R.id.report_forum);
////                    TextView closeForum = (TextView) dialog.findViewById(R.id.close_forum);
////
////                    //Delete Forum
////                    deleteForum.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////
////                        }
////                    });
////
////                    //Report Forum
////                    reportForum.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////
////                        }
////                    });
////
////                    //Close Forum
////                    closeForum.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////
////                        }
////                    });
////
////                }
//
//            }
//        }));

    }



}
