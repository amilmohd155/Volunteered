package com.angeltek.volunteered_trail.startup;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    //Constants
    private final Context mContext = ForgotPasswordActivity.this;

    //Widget
    private EditText email;
    private Button sendLink;
    private TextView signUp;
    private LinearLayout backToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.recoveryEmail);

        sendLink = findViewById(R.id.sendLink);

        signUp = findViewById(R.id.signUp);
        backToLogin = (LinearLayout) findViewById(R.id.back_logIn);


        sendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordActivity.this).toBundle());
            }
        });


        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(ForgotPasswordActivity.this).toBundle());
            }
        });

    }
}
