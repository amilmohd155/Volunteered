/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/19/20 6:30 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/30/19 6:35 PM
 *
 */

package com.volunteerx.app.startup.wizard;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.volunteerx.app.R;
import com.volunteerx.app.api.APIInterface;
import com.volunteerx.app.api.ServiceGenerator;
import com.volunteerx.app.api.model.PureErrorResponse;
import com.volunteerx.app.utils.SharedPrefManager;
import com.volunteerx.app.utils.StatusColorClass;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;
import static com.volunteerx.app.utils.StatusColorClass.DARK_STATUS_BAR_ICON;

public class UserNameWizardFragment  extends Fragment implements View.OnClickListener {

    private static final String TAG = "UserNameWizardFragment";

    //Var
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
    private DocumentReference documentReference = rootRef.collection("users").document(auth.getCurrentUser().getUid());

    //UI
    private ViewGroup mContainer;
    private FancyButton nextBtn;
    private EditText etUsername;
    private ProgressBar progressBar;
    private View view;
    private CharSequence halfUsername;
    private ImageView iv_username_error;

    public UserNameWizardFragment() {
    }

    public static UserNameWizardFragment newInstance() {
        return new UserNameWizardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: username wizard created");

        return inflater.inflate(R.layout.fragment_username_wizard, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StatusColorClass.setStatusColor(getActivity(), getContext().getColor(R.color.colorWhite), DARK_STATUS_BAR_ICON);

        nextBtn = view.findViewById(R.id.btn_next);
        etUsername = view.findViewById(R.id.et_username);
        progressBar = view.findViewById(R.id.loader);
        iv_username_error = view.findViewById(R.id.iv_username_error);

        progressBar.setIndeterminateTintList(ColorStateList.valueOf(getContext().getColor(R.color.colorBlack)));

        Log.d(TAG, "onViewCreated: " + documentReference.getId());

        view.findViewById(R.id.btn_next).setOnClickListener(this);

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() >= 6 && charSequence.length() <= 60) {
                    halfUsername = charSequence;
                    nextBtn.setEnabled(true);
                }
                else nextBtn.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etUsername.setOnEditorActionListener((TextView textView, int actionId, KeyEvent keyEvent)-> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (nextBtn.isEnabled()) nextBtn.performClick();
                return true;
            }
            return false;
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_next) {
//            setupUsername();
            checkIfUsernameAvailable();
        }
    }

    private void checkIfUsernameAvailable() {
        iv_username_error.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        rootRef.collection("users").whereEqualTo("username", halfUsername.toString())
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty() && !documentReference.getId().equals(auth.getCurrentUser().getUid())) {
                            progressBar.setVisibility(View.INVISIBLE);
                            iv_username_error.setVisibility(View.VISIBLE);
                            nextBtn.setEnabled(false);
                        }else {
                            progressBar.setVisibility(View.INVISIBLE);
                            storeAvailableUsername();
                        }
                    }
                });

    }

    private void storeAvailableUsername() {
        documentReference.update("username", halfUsername.toString())
                .addOnSuccessListener(aVoid -> replaceFragment(AgeWizardFragment.newInstance(), "AgeWizardFragment", getFragmentManager()))
                .addOnFailureListener(e -> Log.e(TAG, "storeAvailableUsername: ", e ));
    }

    /**
     *
     */
    private void setupUsername() {

        progressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "setupUsername: username searching");
        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);

        Call<PureErrorResponse> call = apiInterface.usernameAvailability(halfUsername.toString());

        call.enqueue(new Callback<PureErrorResponse>() {
            @Override
            public void onResponse(Call<PureErrorResponse> call, Response<PureErrorResponse> response) {
                 if (response.body().getError()) {
                     progressBar.setVisibility(View.INVISIBLE);
                     iv_username_error.setVisibility(View.VISIBLE);
                     nextBtn.setEnabled(false);
                 }
                 else {
                     progressBar.setVisibility(View.INVISIBLE);
                     storeUsername();
                 }
            }

            @Override
            public void onFailure(Call<PureErrorResponse> call, Throwable t) {
                Snackbar.make(view, R.string.network_error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.try_again, (View view)-> setupUsername())
                        .setActionTextColor(getContext().getColor(R.color.colorWhite))
                        .show();
            }
        });
    }

    private void storeUsername() {

        final String username = halfUsername.toString();

        final int userId = SharedPrefManager.getInstance(getContext()).getUserId();

        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);

        Call<PureErrorResponse> call = apiInterface.addUsernameToDB(
                userId,
                username);

        call.enqueue(new Callback<PureErrorResponse>() {
            @Override
            public void onResponse(Call<PureErrorResponse> call, Response<PureErrorResponse> response) {
                if (!response.body().getError()) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(mContainer.getId(), AgeWizardFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                }else {
                    //error
                }
            }

            @Override
            public void onFailure(Call<PureErrorResponse> call, Throwable t) {
                Snackbar.make(view, R.string.network_error, Snackbar.LENGTH_LONG)
                        .setAction(R.string.try_again, (View view)-> setupUsername())
                        .setActionTextColor(getContext().getColor(R.color.colorWhite))
                        .show();
            }
        });

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
