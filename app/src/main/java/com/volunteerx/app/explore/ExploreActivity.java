/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/18/19 11:54 AM
 *
 */

package com.volunteerx.app.explore;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;

public class ExploreActivity extends AppCompatActivity implements ClickListener {

    private static final String TAG = "ExploreActivity";

    private final Context context = ExploreActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_container);
//
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.container, ExploreFragment.newInstance())
//                .commit();

    }


    @Override
    public void buttonClick(int type) {

    }

    @Override
    public boolean onLongClick(int args) {
        return false;
    }
}
