package com.volunteerx.app.startup;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;

import mehdi.sakout.fancybuttons.FancyButton;

import static com.volunteerx.app.utils.Constants.FP_INIT;
import static com.volunteerx.app.utils.Constants.INVITE_SIGN_UP;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private ViewGroup mContainer;

    //UI
    LinearLayout llUserData, llPassword;
    EditText etUserData, etPassword;
    FancyButton btnLogin;
    ImageButton ibGoogle, ibFB, ibTwitter;
    TextView tvForgotPassword, tvSignUpInvite;

    //Interface
    ClickListener listener;

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

        View view = inflater.inflate(R.layout.fragment_login, container, false);

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

        tvForgotPassword.setOnClickListener(this);
        tvSignUpInvite.setOnClickListener(this);


        String inviteText = "<Font color=#000>Don't have an account?</Font><Font color=#28a8ff> Sign up</Font>";
        tvSignUpInvite.setText(Html.fromHtml(inviteText));


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
        }
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
}
