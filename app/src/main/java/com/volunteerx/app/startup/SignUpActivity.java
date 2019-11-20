package com.volunteerx.app.startup;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.volunteerx.app.R;

public class SignUpActivity extends AppCompatActivity {

    //Constants
    private final Context mContext = SignUpActivity.this;

    //Widgets
    private TextView login;
    private Button signUp;
    private ImageView facebookLogin, googleLogin, twitterLogin;
    private EditText email, password, username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        signUp = findViewById(R.id.signUpFinal);
        login = findViewById(R.id.logIn);

        facebookLogin = findViewById(R.id.facebookLogin);
        googleLogin = findViewById(R.id.googleLogin);
        twitterLogin = findViewById(R.id.twitterLogin);

        email = findViewById(R.id.signupEmail);
        password = findViewById(R.id.signUpPassword);
        username = findViewById(R.id.signUpUsername);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this).toBundle());            }
        });



    }
}
