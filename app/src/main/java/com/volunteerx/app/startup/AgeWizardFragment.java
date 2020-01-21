/*
 * *
 *  * Created by Amil Muhammed Hamza on 12/25/19 9:32 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/14/19 8:58 PM
 *
 */

package com.volunteerx.app.startup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.volunteerx.app.R;
import com.volunteerx.app.api.APIInterface;
import com.volunteerx.app.api.ServiceGenerator;
import com.volunteerx.app.api.model.PureErrorResponse;
import com.volunteerx.app.utils.SharedPrefManager;

import java.util.Calendar;
import java.util.Date;

import mehdi.sakout.fancybuttons.FancyButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgeWizardFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "AgeWizardFragment";

    //Var
    private String strDateOfBirthDB;


    //UI
    private ViewGroup mContainer;
    DatePicker datePicker;
    EditText etDateOfBirth;
    TextView tvAge;
    FancyButton nextBtn;


    public AgeWizardFragment() {
    }

    public static AgeWizardFragment newInstance() {
        return new AgeWizardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: age wizard created");

        View view = inflater.inflate(R.layout.fragment_age_wizard, container, false);

        mContainer = container;

        etDateOfBirth = view.findViewById(R.id.et_date_of_birth);
        tvAge = view.findViewById(R.id.tv_age);
        datePicker = view.findViewById(R.id.datePicker);
        nextBtn = view.findViewById(R.id.btn_next);

        nextBtn.setOnClickListener(this);

        getDOB();

        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == nextBtn.getId()) {

            storeDOB();

        }

    }

    private void storeDOB() {

        final int userID = SharedPrefManager.getInstance(getContext()).getUserId();

        APIInterface apiInterface = ServiceGenerator.createService(APIInterface.class);

        Call<PureErrorResponse> call = apiInterface.setDOB(
                userID,
                strDateOfBirthDB
        );

        call.enqueue(new Callback<PureErrorResponse>() {
            @Override
            public void onResponse(Call<PureErrorResponse> call, Response<PureErrorResponse> response) {
                if (!response.body().getError()) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(mContainer.getId(), CharacterWizardFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                }else {
                    //error
                }
            }

            @Override
            public void onFailure(Call<PureErrorResponse> call, Throwable t) {
                //TODO store failed upload and do background save
            }
        });

    }

    private void getDOB() {

        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        datePicker.setMaxDate(new Date().getTime());

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                (datePicker, year, month, dayOfMonth) -> {
                    strDateOfBirthDB = year + "-" + (++month) + "-" + dayOfMonth;
                    final String strDOB = dayOfMonth + "/" + ++month + "/" + year;
                    etDateOfBirth.setText(strDOB);
                    String strAge = (currentYear - year) + " years";
                    tvAge.setText(strAge);

                    if (currentYear - year < 12 ) nextBtn.setEnabled(false);
                    else nextBtn.setEnabled(true);

                });

    }
}
