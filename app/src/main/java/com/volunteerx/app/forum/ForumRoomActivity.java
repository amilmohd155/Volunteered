/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.forum;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.volunteerx.app.R;

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

    private void setupForumInfoFragment() {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.forum_room_container, new ForumInfoFragment(), getResources().getString(R.string.forum_info_fragment))
                .remove(getSupportFragmentManager().findFragmentByTag(getResources().getString(R.string.forum_room_fragment)))
                .addToBackStack(null);
        fragmentTransaction.commit();

    }


}
