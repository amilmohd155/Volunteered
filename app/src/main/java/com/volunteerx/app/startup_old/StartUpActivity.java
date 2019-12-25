package com.volunteerx.app.startup_old;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.volunteerx.app.R;


public class StartUpActivity extends AppCompatActivity {


    private static final String TAG = "StartUpActivity";

    final Context mContext = StartUpActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_startup);

    }
}
