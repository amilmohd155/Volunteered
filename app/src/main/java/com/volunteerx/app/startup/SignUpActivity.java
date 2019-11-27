package com.volunteerx.app.startup;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.Constants;

import static com.volunteerx.app.utils.Constants.EMAIL_ENTRY;
import static com.volunteerx.app.utils.Constants.PHONE_ENTRY;

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener {

    //Constants
    private final Context mContext = SignUpActivity.this;

    //Widgets
    private TextView login;
    private Button signUp;
    private ImageView facebookLogin, googleLogin, twitterLogin;
    private EditText dataEt, passwordEt, usernameEt;
    private String data, password, username;
    private int dataType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        signUp = findViewById(R.id.signUpFinal);
        login = findViewById(R.id.logIn);

        facebookLogin = findViewById(R.id.facebookLogin);
        googleLogin = findViewById(R.id.googleLogin);
        twitterLogin = findViewById(R.id.twitterLogin);

        dataEt = findViewById(R.id.signupData);
        passwordEt = findViewById(R.id.signUpPassword);
        usernameEt = findViewById(R.id.signUpUsername);

        login.setOnClickListener(this);
        signUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logIn: {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SignUpActivity.this).toBundle());
            }
            break;
            case R.id.signUpFinal: {
                data = dataEt.getText().toString();
                if (isEmailValid(data)) {
                    dataType = EMAIL_ENTRY;
                }
                else if (isValidMobile(data)) {
                    dataType = PHONE_ENTRY;
                }
                else { //handle error message

                }
            }
            break;
        }
    }

    private boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

}
