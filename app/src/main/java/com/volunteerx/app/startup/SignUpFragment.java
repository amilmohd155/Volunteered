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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.volunteerx.app.R;
import com.volunteerx.app.SimpleProgressDialog.ProgressDialog;
import com.volunteerx.app.VolunteerXDialog.VolunteerXDialog;
import com.volunteerx.app.api.APIInterface;
import com.volunteerx.app.api.ServiceGenerator;
import com.volunteerx.app.api.model.Response;
import com.volunteerx.app.databinding.FragmentSignUpBinding;
import com.volunteerx.app.firebase.FirebaseMethods;
import com.volunteerx.app.models.User;
import com.volunteerx.app.profile.viewmodel.UserViewModel;
import com.volunteerx.app.startup.model.SigningUser;
import com.volunteerx.app.startup.viewModel.SigningViewModel;
import com.volunteerx.app.startup.wizard.UserNameWizardFragment;
import com.volunteerx.app.startup.wizard.WizardFragment;
import com.volunteerx.app.utils.CheckerClass;
import com.volunteerx.app.utils.ClickListener;
import com.volunteerx.app.utils.SharedPrefManager;

import java.util.HashMap;
import java.util.Map;

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
import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;
import static com.volunteerx.app.utils.UtilClass.hideSoftKeyboard;

public class SignUpFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private static final String TAG = "SignUpFragment";

    //Var
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private DocumentReference documentReference;

    private SigningViewModel signingViewModel;


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
        auth  = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        builder = new ProgressDialog.Builder(getContext())
                .setMessage("Loading")
                .setTheme(LIGHT_THEME);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentSignUpBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        signingViewModel = new ViewModelProvider(this).get(SigningViewModel.class);

        binding.setUserViewModel(signingViewModel);
        binding.setLifecycleOwner(this);

        signingViewModel.getTask().observe(getViewLifecycleOwner(), authResultTask -> {
            Log.d(TAG, "onCreateView: something please");
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvLoginInvite = view.findViewById(R.id.tv_login_invite);
        signUpBtn = view.findViewById(R.id.btn_sign_up);
        etUserData = view.findViewById(R.id.et_user_data);
        etFullName = view.findViewById(R.id.et_full_name);
        etPassword = view.findViewById(R.id.et_password);


        String inviteText = getString(R.string.loginInviteText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvLoginInvite.setText(Html.fromHtml(inviteText, Html.FROM_HTML_MODE_LEGACY));
        }else {
            tvLoginInvite.setText(Html.fromHtml(inviteText));
        }

        tvLoginInvite.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
        etUserData.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        etFullName.addTextChangedListener(this);

        etPassword.setOnEditorActionListener((TextView textView, int i, KeyEvent keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                if (signUpBtn.isEnabled()) signUpBtn.performClick();
                return true;
            }
            return false;
        });

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
//                signUpFunc();
            }
        }
    }

    private void signUpFunc() {

        Log.d(TAG, "signUpFunc: signing up here");
        if (getActivity() != null) hideSoftKeyboard(getActivity());

        builder = new ProgressDialog.Builder(getContext())
                .setMessage("Loading")
                .setTheme(LIGHT_THEME);

        builder.build();

        userData = etUserData.getText().toString().trim();
        fullName = etFullName.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (isEmailValid(userData)) {
//            createUserWithEmail();

        } else if (isValidMobile(userData)) {

        } else {

            builder.dismiss();

            //dialog for retry
            new VolunteerXDialog.Builder(getActivity())
                    .setMessage("Invalid entry")
                    .setPositiveBtnText("Try again")
                    .OnPositiveClicked(Dialog::dismiss)
                    .isCancellable(true)
                    .build();

        }

    }

    private void createUserWithEmail() {
        auth.createUserWithEmailAndPassword(userData, password).addOnCompleteListener(getActivity() ,task -> {
           if (task.isSuccessful()) {
               Log.d(TAG, "createUserWithEmail:success");

               User user = new User(null,
                       null,
                       "",
                       "",
                       userData,
                       fullName,
                       "",
                       "",
                       "",
                       "",
                       0,
                       0,
                       0,
                       false
               );

               db.collection("users").document(auth.getCurrentUser().getUid()).set(user)
                       .addOnSuccessListener(aVoid -> {
                           builder.dismiss();



                           Log.d(TAG, "createUserWithEmail: DocumentSnapshot added with ID:" );
                           SharedPrefManager.getInstance(getContext()).wizardCompleted(false);
                           replaceFragment(UserNameWizardFragment.newInstance(), "UserNameWizardFragment", getParentFragment().getFragmentManager());

                       }).addOnFailureListener(e -> {
                           builder.dismiss();
                           Log.w(TAG, "createUserWithEmail: error adding document", e);
               });


           }else {
               Log.w(TAG, "createUserWithEmail:failure", task.getException());

               builder.dismiss();

               Toast.makeText(getContext(), "Authentication failed. " + task.getException(),
                       Toast.LENGTH_SHORT).show(); //handle exceptions

           }
        });
    }

    private boolean validate(EditText[] fields){
        for (EditText currentField : fields){
            if(currentField.getText().toString().isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (validate(new EditText[]{etFullName, etPassword, etUserData}) && etPassword.getText().toString().length() >= 8) {
            signUpBtn.setEnabled(true);
        }else signUpBtn.setEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
