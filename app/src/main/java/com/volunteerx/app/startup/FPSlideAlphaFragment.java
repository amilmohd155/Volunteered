package com.volunteerx.app.startup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;

import static com.volunteerx.app.utils.CheckerClass.isEmailValid;
import static com.volunteerx.app.utils.CheckerClass.isValidMobile;
import static com.volunteerx.app.utils.Constants.FP_EMAIL;
import static com.volunteerx.app.utils.Constants.FP_PHONE;
import static com.volunteerx.app.utils.Constants.FP_USERNAME;


public class FPSlideAlphaFragment extends Fragment {

    private static final String TAG = "FPSlideAlphaFragment";

    private final Context context = getContext();

    //var
    private String userData;
    private ClickListener listener;

    //UI
    private Toolbar toolbar;
    private Button nextButton;
    private EditText etUserData;


    public FPSlideAlphaFragment() {
        //Required null constructor
    }

//    Use this factory method to create a new instance of
//    this fragment using the provided parameters.
    public static FPSlideAlphaFragment newInstance() {

        FPSlideAlphaFragment fragment = new FPSlideAlphaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //bundle values;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fp_slide_alpha, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        etUserData = view.findViewById(R.id.et_user_data);
        nextButton = view.findViewById(R.id.btn_next);

        etUserData.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE || keyEvent != null &&
                keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
                    if (keyEvent == null || !keyEvent.isShiftPressed()) {
                        setupNextButton();
                    }
                }

                return false;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupNextButton();
            }
        });

        setupBackButton();

        return view;

    }

    private void setupNextButton() {

        Log.d(TAG, "setupNextButton: going to next fragment based on userdata");

        userData = etUserData.getText().toString();
        if (!userData.isEmpty()) {
            if (isEmailValid(userData)) {
                listener.buttonClick(FP_EMAIL);
            }else if (isValidMobile(userData)) {
                listener.buttonClick(FP_PHONE);
            }else {
                listener.buttonClick(FP_USERNAME);
            }
        } else {
            try {
                Snackbar.make(getView(), "Enter something, BITCH!!!", Snackbar.LENGTH_INDEFINITE).show();
            }catch (NullPointerException e) { e.getStackTrace(); }
        }

    }

    private void setupBackButton() {

        Log.d(TAG, "setupBackButton: closing fragment");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getActivity().onBackPressed();
                }catch (NullPointerException e) {
                    e.getStackTrace();
                }
            }
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

}
