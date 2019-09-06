package com.angeltek.volunteered_trail.search;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.angeltek.volunteered_trail.R;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";

    //widgets
    ImageView ivBackButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ivBackButton = (ImageView) findViewById(R.id.back_btn);

        setupbackButton();
    }

    /**
     * closes the activity and goes back
     */
    private void setupbackButton() {

        ivBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
