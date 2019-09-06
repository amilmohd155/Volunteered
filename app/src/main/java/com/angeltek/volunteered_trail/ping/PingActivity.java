package com.angeltek.volunteered_trail.ping;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;


import com.angeltek.volunteered_trail.R;

public class PingActivity extends AppCompatActivity {

    private static final String TAG = "PingActivity";

    final Context mContext = PingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        Log.d(TAG, "onCreate: Starting Ping Activity");


    }



}
