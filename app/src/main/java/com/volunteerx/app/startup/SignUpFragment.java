/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 9:47 PM
 *
 */

package com.volunteerx.app.startup;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.volunteerx.app.R;
import com.volunteerx.app.SimpleProgressDialog.ProgressDialog;
import com.volunteerx.app.VolunteerXDialog.VolunteerXDialog;
import com.volunteerx.app.api.APIInterface;
import com.volunteerx.app.api.ServiceGenerator;
import com.volunteerx.app.api.model.Response;
import com.volunteerx.app.models.User;
import com.volunteerx.app.utils.ClickListener;
import com.volunteerx.app.utils.SharedPrefManager;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;

import static com.volunteerx.app.SimpleProgressDialog.ProgressDialog.LIGHT_THEME;
import static com.volunteerx.app.utils.CheckerClass.isEmailValid;
import static com.volunteerx.app.utils.CheckerClass.isValidMobile;
import static com.volunteerx.app.utils.Constants.EMAIL_ENTRY;
import static com.volunteerx.app.utils.Constants.EXISTING_USER;
import static com.volunteerx.app.utils.Constants.INVITE_LOGIN;
import static com.volunteerx.app.utils.Constants.PHONE_ENTRY;
import static com.volunteerx.app.utils.Constants.WIZARD;

public class SignUpFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private static final String TAG = "SignUpFragment";

    //Var
    private ClickListener listener;
    private String userData, fullName, password;
    private int userDataType;

    //UI
    private ViewGroup mContainer;
    private TextView tvLoginInvite, tvTNC;
    private EditText etUserData, etFullName, etPassword;
    private ProgressDialog.Builder builder;
    private User user;
    private FancyButton signUpBtn;
    private View view;

    public SignUpFragment() {
//        null constructor
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //bundle values;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        tvLoginInvite = view.findViewById(R.id.tv_login_invite);
        signUpBtn = view.findViewById(R.id.btn_sign_up);
        etUserData = view.findViewById(R.id.et_user_data);
        etFullName = view.findViewById(R.id.et_full_name);
        etPassword = view.findViewById(R.id.et_password);


        String inviteText = "<Font color=#000>Already have an account?</Font><Font color=#28a8ff> Login</Font>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvLoginInvite.setText(Html.fromHtml(inviteText, Html.FROM_HTML_MODE_COMPACT));
        }else {
            tvLoginInvite.setText(Html.fromHtml(inviteText));
        }

        tvLoginInvite.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
        etUserData.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        etFullName.addTextChangedListener(this);

        return view;

    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof ClickListener) {
            listener = (ClickListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_invite: {
                listener.buttonClick(INVITE_LOGIN);
            }break;
            case R.id.btn_sign_up: {
                signUpFunc();
            }
        }
    }

    private void signUpFunc() {

        Log.d(TAG, "signUpFunc: signing up here");

        builder = new ProgressDialog.Builder(getContext())
                .setMessage("Loading")
                .setTheme(LIGHT_THEME);

        userData = etUserData.getText().toString().trim();
        fullName = etFullName.getText().toString().trim();
        password = etPassword.getText().toString().trim();


        user = new User(fullName, password);

        if (isEmailValid(userData)) {

            userDataType = EMAIL_ENTRY;
            user.setEmailAddress(userData);

        } else if (isValidMobile(userData)) {

            userDataType = PHONE_ENTRY;
            user.setPhone(userData);

        } else {

            //dialog for retry
            return;

        }

//        listener.buttonClick(WIZARD);
        apiService();
    }

    private void apiService() {

        builder.build();

        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);

        Call<Response> call = apiInterface.createUser(
                user.getFullName(),
                userData,
                user.getPassword(),
                userDataType
        );

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                builder.dismiss();

                if (response.body().getError()) {

                    if (response.body().getErrorValue() == EXISTING_USER) {
                        Log.d(TAG, "onResponse: existing user");
                        new VolunteerXDialog.Builder(getActivity())
                                .setTitle(response.body().getMessage())
                                .setMessage("The account already exists. Please try logging in or enter a different email address or phone number.")
                                .setPositiveBtnText("Try Again")
                                .OnPositiveClicked(Dialog::dismiss)
                                .setNegativeBtnText("Log in")
                                .OnNegativeClicked((Dialog dialog) -> listener.buttonClick(INVITE_LOGIN))
                                .build();
                    }
                }
                else {
                    Log.d(TAG, "onResponse: User Id - " + response.body().getUserID());
                    SharedPrefManager.getInstance(getContext()).userLogin(response.body().getUserID());
                    listener.buttonClick(WIZARD);
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                //failure snack bar
                builder.dismiss();
                Snackbar.make(view, R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.try_again, (View view) -> apiService())
                        .setActionTextColor(getContext().getColor(R.color.color685783))
                        .show();

            }
        });

    }

    private boolean validate(EditText[] fields){
        for (EditText currentField : fields){
            if(currentField.getText().toString().length() <= 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (validate(new EditText[]{etFullName, etPassword, etUserData})) {
            signUpBtn.setEnabled(true);
        }else signUpBtn.setEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
