/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 8:33 PM
 *
 */

package com.volunteerx.app.startup_old;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.volunteerx.app.R;

import static com.volunteerx.app.utils.CheckerClass.isEmailValid;
import static com.volunteerx.app.utils.CheckerClass.isValidMobile;
import static com.volunteerx.app.utils.Constants.EMAIL_ENTRY;
import static com.volunteerx.app.utils.Constants.PHONE_ENTRY;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    //constants
    private final Context mContext = LoginActivity.this;
    private final int EMAIL = 1;
    private final int USERNAME = 0;

    //Widgets
    private TextView forgotPasswordNavigator;
    private LinearLayout signUpNavigator;
    private Button loginBtn;
    private ImageView facebookLogin, googleLogin, twitterLogin;
    private EditText userDataEt, passwordEt;

    //var
    private String userData, password;
    private int userDataType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpNavigator = findViewById(R.id.signUpNavigator);
        forgotPasswordNavigator = findViewById(R.id.forgot_password_navigator);
        loginBtn = findViewById(R.id.loginFinal);
        facebookLogin = findViewById(R.id.facebookLogin);
        googleLogin = findViewById(R.id.googleLogin);
        twitterLogin = findViewById(R.id.twitterLogin);

        //setOnClickListener
        loginBtn.setOnClickListener(this);
        signUpNavigator.setOnClickListener(this);

        userDataEt = findViewById(R.id.loginEmail);
        passwordEt = findViewById(R.id.loginPassword);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginFinal:
                loginSubmit();
                break;
            case R.id.signUpNavigator: {
                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
            }
                break;
            case R.id.forgot_password_navigator: {
                Intent intent = new Intent(mContext, ForgotPasswordActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
            }
                break;
        }
    }

    private void loginSubmit() {

        Log.d(TAG, "loginSubmit: logging started");

        userData = userDataEt.getText().toString();
        password = passwordEt.getText().toString();

        if (isEmailValid(userData)) {
            userDataType = EMAIL_ENTRY;
        }else if (isValidMobile(userData)){
            userDataType = PHONE_ENTRY;
        }else {
            //handle error message;
        }

    }


}
