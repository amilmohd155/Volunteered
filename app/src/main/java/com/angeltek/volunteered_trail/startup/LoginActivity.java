package com.angeltek.volunteered_trail.startup;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;

public class LoginActivity extends AppCompatActivity {

    //constants
    private final Context mContext = LoginActivity.this;

    //Widgets
    private TextView forgotPasswordNavigator;
    private LinearLayout signUpNavigator;
    private Button login;
    private ImageView facebookLogin, googleLogin, twitterLogin;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpNavigator = (LinearLayout) findViewById(R.id.signUpNavigator);
        login = findViewById(R.id.loginFinal);

        forgotPasswordNavigator = findViewById(R.id.forgot_password_navigator);

        facebookLogin = findViewById(R.id.facebookLogin);
        googleLogin = findViewById(R.id.googleLogin);
        twitterLogin = findViewById(R.id.twitterLogin);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);



        signUpNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
            }
        });


        forgotPasswordNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ForgotPasswordActivity.class);
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
            }
        });


    }
}
