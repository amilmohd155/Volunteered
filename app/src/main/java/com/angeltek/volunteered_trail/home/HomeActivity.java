package com.angeltek.volunteered_trail.home;

import android.content.Context;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.angeltek.volunteered_trail.forums.ForumsActivity;
import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.post.PostActivity;
import com.angeltek.volunteered_trail.utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class HomeActivity extends AppCompatActivity {

  private static final String TAG = "HomeActivity";

  final Context mContext = HomeActivity.this;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    Log.d(TAG, "onCreate: Starting");

    setupBottomNavigationView();

    TextView forumButton = (TextView) findViewById(R.id.forum);


    forumButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d(TAG, "onClick: Navigating to Forum Activity");

        Intent intent = new Intent(mContext, ForumsActivity.class);
        startActivity(intent);
      }
    });

    FloatingActionButton fab = findViewById(R.id.post_fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(mContext, PostActivity.class);
        startActivity(intent);
      }
    });

  }




  /**
   * Setting up BottomNavigationView
   */
  private void setupBottomNavigationView() {

    Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");

    BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);

    BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
    BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);

  }

}
