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
import android.widget.TextView;

import com.volunteerx.app.R;
import com.volunteerx.app.api.APIInterface;
import com.volunteerx.app.api.ServiceGenerator;
import com.volunteerx.app.models.Response;
import com.volunteerx.app.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.internal.EverythingIsNonNull;

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener {

    //Constants
    private final Context mContext = SignUpActivity.this;
    private static final String TAG = "SignUpActivity";

    //Widgets
    private TextView login;
    private Button signUp;
    private ImageView facebookLogin, googleLogin, twitterLogin;
    private EditText dataEt, passwordEt, usernameEt;

    //var
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

    private void userSignUp() {
        Log.d(TAG, "userSignUp: Sign up process started");

        data = dataEt.getText().toString().trim();
        password = passwordEt.getText().toString().trim();
        username = usernameEt.getText().toString().trim();

//        if (isEmailValid(data)) {
//            dataType = EMAIL_ENTRY;
//        }
//        else if (isValidMobile(data)) {
//            dataType = PHONE_ENTRY;
//        }
//        else { //handle error message
//
//        }

        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);

        User user = new User(username, data, password, dataType);

        Call<Response> call = apiInterface.createUser(
                user.getUserName(),
                data,
                user.getPassword(),
                dataType
        );

        call.enqueue(new Callback<Response>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d(TAG, "onResponse: " + response.body().getMessage());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

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
                userSignUp();
            }
            break;
        }
    }


}
