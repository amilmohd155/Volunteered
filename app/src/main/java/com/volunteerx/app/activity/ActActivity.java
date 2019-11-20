package com.volunteerx.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.volunteerx.app.R;


public class ActActivity extends AppCompatActivity {

    private  final Context context = ActActivity.this;

    //UI
    private ImageView coverPhoto;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        coverPhoto = findViewById(R.id.cover_photo);



    }
}
