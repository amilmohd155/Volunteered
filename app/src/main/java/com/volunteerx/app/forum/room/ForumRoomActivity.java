/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/29/20 9:21 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/10/20 4:08 PM
 *
 */

package com.volunteerx.app.forum.room;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.volunteerx.app.R;
import com.volunteerx.app.forum.info.ForumInfoFragment;
import com.volunteerx.app.forum.room.fragment.ForumRoomFragment;

public class ForumRoomActivity extends AppCompatActivity {

    private static final String TAG = "ForumRoomActivity";

    //Constants
    final Context mContext = ForumRoomActivity.this;

    //Variables
    private int forumId;

    //Widgets




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_room);

        forumId = getIntent().getIntExtra("Forum Id", -1);

        setupForumRoomFragment();

    }

    private void setupForumRoomFragment() {

        Log.d(TAG, "setupForumRoomFragment: creating forumRoom(defualt)");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.forum_room_container, new ForumRoomFragment(), getResources().getString(R.string.forum_room_fragment));
        fragmentTransaction.commit();

    }

}
