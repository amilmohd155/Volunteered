package com.volunteerx.app.startup;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;

import mehdi.sakout.fancybuttons.FancyButton;

import static com.volunteerx.app.utils.Constants.INVITE_LOGIN;
import static com.volunteerx.app.utils.Constants.WIZARD;

public class SignUpFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "SignUpFragment";

    //Var
    private ClickListener listener;
    private String userData, fullName, password;

    //UI
    private ViewGroup mContainer;
    private TextView tvLoginInvite, tvTNC;
    private EditText etUserData, etFullName, etPassword;

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

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        tvLoginInvite = view.findViewById(R.id.tv_login_invite);
        final FancyButton signUpBtn = view.findViewById(R.id.btn_sign_up);
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

        userData = etUserData.getText().toString();
        fullName = etFullName.getText().toString();
        password = etPassword.getText().toString();

        listener.buttonClick(WIZARD);

    }
}
