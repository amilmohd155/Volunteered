package com.volunteerx.app.startup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class UserNameWizardFragment  extends Fragment implements View.OnClickListener {

    private static final String TAG = "UserNameWizardFragment";

    //UI
    private ViewGroup mContainer;
    private FancyButton nextBtn;
    private EditText etUsername;
    private TextView tvErrorText;

    public UserNameWizardFragment() {
    }

    public static UserNameWizardFragment newInstance() {
        return new UserNameWizardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: username wizard created");

        View view = inflater.inflate(R.layout.fragment_username_wizard, container, false);

        mContainer = container;

        nextBtn = view.findViewById(R.id.btn_next);
        etUsername = view.findViewById(R.id.et_username);
        tvErrorText = view.findViewById(R.id.tv_error);

        etUsername.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        setupUsername();

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next: {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(mContainer.getId(), AgeWizardFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }break;
            case R.id.et_username: {
                getUsername();
            }break;
        }
    }

    /**
     *
     */
    private void getUsername() {

    }

    /**
     *
     */
    private void setupUsername() {

        if (usernameCheck()) {
            nextBtn.setEnabled(true);
            nextBtn.setBackgroundColor(getContext().getColor(R.color.colorButtonBGEnable));
            nextBtn.setTextColor(getContext().getColor(R.color.colorButtonTextEnabled));

        }else {
            nextBtn.setEnabled(false);
            nextBtn.setBackgroundColor(getContext().getColor(R.color.colorButtonBGDisabled));
            nextBtn.setTextColor(getContext().getColor(R.color.colorButtonTextDisabled));

            tvErrorText.setVisibility(View.VISIBLE);

        }

    }

    /**
     *
     * @return true if condition succeeds
     */
    private boolean usernameCheck() {
        return true;
        //Todo non empty unique  username required
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
