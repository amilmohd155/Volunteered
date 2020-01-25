/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/25/19 9:23 PM
 *
 */

package com.volunteerx.app.startup;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.volunteerx.app.home.HomeActivity;
import com.volunteerx.app.api.model.Response;
import com.volunteerx.app.utils.ClickListener;
import com.volunteerx.app.utils.SharedPrefManager;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY;
import static com.volunteerx.app.SimpleProgressDialog.ProgressDialog.LIGHT_THEME;
import static com.volunteerx.app.utils.CheckerClass.isEmailValid;
import static com.volunteerx.app.utils.CheckerClass.isValidMobile;
import static com.volunteerx.app.utils.Constants.EMAIL_ENTRY;
import static com.volunteerx.app.utils.Constants.FP_INIT;
import static com.volunteerx.app.utils.Constants.INVITE_SIGN_UP;
import static com.volunteerx.app.utils.Constants.PASSWORD_ERROR;
import static com.volunteerx.app.utils.Constants.PHONE_ENTRY;
import static com.volunteerx.app.utils.Constants.USER_DATA_ERROR;
import static com.volunteerx.app.utils.Constants.WIZARD;
import static com.volunteerx.app.utils.UtilClass.hideSoftKeyboard;

public class LoginFragment extends Fragment implements View.OnClickListener,TextWatcher {

    private static final String TAG = "LoginFragment";

    private ViewGroup mContainer;

    //UI
    private View view;
    LinearLayout llUserData, llPassword;
    EditText etUserData, etPassword;
    FancyButton btnLogin;
    ImageButton ibGoogle, ibFB, ibTwitter;
    TextView tvForgotPassword, tvSignUpInvite;
    View userDataUnderLine, passwordUnderLine;

    //Interface
    ClickListener listener;

    //Var
    String userData, password;
    private ProgressDialog.Builder builder;
    private int userDataType;

    //required
    public LoginFragment() {
//        default null constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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

        mContainer = container;

        view = inflater.inflate(R.layout.fragment_login, container, false);

        llUserData = view.findViewById(R.id.ll_user_data);
        llPassword = view.findViewById(R.id.ll_password);
        etUserData = view.findViewById(R.id.et_user_data);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);
        tvForgotPassword = view.findViewById(R.id.tv_forgot);
        ibGoogle = view.findViewById(R.id.google_icon);
        ibFB = view.findViewById(R.id.fb_icon);
        ibTwitter = view.findViewById(R.id.twitter_icon);
        tvSignUpInvite = view.findViewById(R.id.tv_sign_invite);
        userDataUnderLine = view.findViewById(R.id.user_data_underline);
        passwordUnderLine = view.findViewById(R.id.password_underline);

        tvForgotPassword.setOnClickListener(this);
        tvSignUpInvite.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        llUserData.setOnClickListener(this);
        llPassword.setOnClickListener(this);
        etPassword.addTextChangedListener(this);
        etUserData.addTextChangedListener(this);

        String inviteText = getString(R.string.signUpInviteText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvSignUpInvite.setText(Html.fromHtml(inviteText, FROM_HTML_MODE_LEGACY));
        }else {
            tvSignUpInvite.setText(Html.fromHtml(inviteText));
        }

        etPassword.setOnEditorActionListener((TextView textView, int i, KeyEvent keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                if (btnLogin.isEnabled()) btnLogin.performClick();
                return true;
            }
            return false;
        });

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forgot:
                listener.buttonClick(FP_INIT);
                break;
            case R.id.tv_sign_invite:
                listener.buttonClick(INVITE_SIGN_UP);
                break;
            case R.id.btn_login:
                loginFunction();
                break;
            case R.id.ll_user_data:
                focusRequest(etUserData);
                break;
            case R.id.ll_password:
                focusRequest(etPassword);
                break;
        }
    }

    private void focusRequest(EditText editText) {
        editText.requestFocus();
    }

    private void loginFunction() {

        builder = new ProgressDialog.Builder(getContext())
                .setTheme(LIGHT_THEME)
                .setMessage("Loading");

        builder.build();

        if (getActivity() != null) hideSoftKeyboard(getActivity());

        Log.d(TAG, "loginFunction: logging in");
        userData = etUserData.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        userDataType = -1;

        if (isEmailValid(userData)) userDataType = EMAIL_ENTRY;
        else if (isValidMobile(userData))  userDataType = PHONE_ENTRY;
        else Log.d(TAG, "loginFunction: error format");

        if (userDataType != -1) {

            apiService();

        }
        else {

            //invalid entry error message

        }

    }

    private void apiService() {

        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);

        Call<Response> call = apiInterface.userLogin(
                userData,
                password,
                userDataType);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                builder.dismiss();

                if (!response.body().getError()) {
                    SharedPrefManager.getInstance(getContext())
                            .userLogin(response.body().getUserID());
                    //lead to HomeActivity
                    if (SharedPrefManager.getInstance(getContext()).isWizardComplete()) {
                        try {
                            getActivity().startActivity(new Intent(getContext(), HomeActivity.class));
                        }
                        catch (NullPointerException e) {
                            Log.d(TAG, "onResponse: couldn't find parent activity class");
                        }
                    }
                    else {
                        listener.buttonClick(WIZARD);
                    }
                }
                else {
                    Log.d(TAG, "onResponse: error combination");

                    if (response.body().getErrorValue() == USER_DATA_ERROR) {

                        new VolunteerXDialog.Builder(getActivity())
                                .setTitle(response.body().getMessage())
                                .setMessage("We can't find an account " + userData + ". Try another phone number or email, or if you don't have a VolunteerX account, you can sign up")
                                .setPositiveBtnText("Try again")
                                .OnPositiveClicked(Dialog::dismiss)
                                .setNegativeBtnText("Sign Up")
                                .OnNegativeClicked((Dialog dialog) -> listener.buttonClick(INVITE_SIGN_UP))
                                .isCancellable(true)
                                .build();

                    }else if (response.body().getErrorValue() == PASSWORD_ERROR) {

                        new VolunteerXDialog.Builder(getActivity())
                                .setTitle(response.body().getMessage())
                                .setMessage("The password you entered is incorrect. Please try again")
                                .setPositiveBtnText("Try Again")
                                .OnPositiveClicked((Dialog::dismiss))
                                .setNegativeBtnText("Forgot Password")
                                .OnNegativeClicked((Dialog dialog) -> listener.buttonClick(FP_INIT))
                                .isCancellable(true)
                                .build();

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Response> call,@NonNull Throwable t) {
                //Error message
                Log.d(TAG, "onFailure: failed to connect");
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
    public void onDestroyView() {
        super.onDestroyView();
        etUserData = etPassword = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (validate(new EditText[]{etUserData, etPassword})) btnLogin.setEnabled(true);
        else btnLogin.setEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
