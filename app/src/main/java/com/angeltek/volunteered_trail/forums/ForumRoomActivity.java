package com.angeltek.volunteered_trail.forums;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.angeltek.volunteered_trail.R;

public class ForumRoomActivity extends AppCompatActivity {

    private static final String TAG = "ForumRoomActivity";

    //Constants
    final Context mContext = ForumRoomActivity.this;

    //Variables

    //Widgets


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_room);

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
