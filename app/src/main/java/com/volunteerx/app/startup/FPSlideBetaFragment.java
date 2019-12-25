package com.volunteerx.app.startup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;

import static com.volunteerx.app.utils.Constants.FP_FINAL;

public class FPSlideBetaFragment extends Fragment {

    private static final String TAG = "FPSlideBetaFragment";

    private final Context context = getContext();
    private ClickListener listener;

    //UI
    private Toolbar toolbar;
    private EditText etPassword;
    private Button resetButton;

    public FPSlideBetaFragment() {
        //Required null constructor
    }

//    Use this factory method to create a new instance of
//    this fragment using the provided parameters.
    public static FPSlideBetaFragment newInstance() {

        FPSlideBetaFragment fragment = new FPSlideBetaFragment();

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

        View view = inflater.inflate(R.layout.fragment_fp_slide_beta, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        etPassword = view.findViewById(R.id.et_password);
        resetButton = view.findViewById(R.id.reset_btn);

        resetButton.setOnClickListener((View v) -> listener.buttonClick(FP_FINAL) );

        setupBackButton();

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


    private void setupBackButton() {

        Log.d(TAG, "setupBackButton: closing fragment");
        toolbar.setNavigationOnClickListener((View v) -> {
                try {
                    getActivity().onBackPressed();
                }catch (NullPointerException e) {
                    e.getStackTrace();
                }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
