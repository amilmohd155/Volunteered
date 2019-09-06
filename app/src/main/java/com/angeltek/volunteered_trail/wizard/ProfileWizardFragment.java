package com.angeltek.volunteered_trail.wizard;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.angeltek.volunteered_trail.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class ProfileWizardFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "ProfileWizardFragment";

    //constants

    //variables

    //widgets
    private TextView dayTV;
    private TextView monthTV;
    private TextView yearTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Fragment Created");

        View view = inflater.inflate(R.layout.fragment_profile_wizard, container, false);

        LinearLayout dobLayout = (LinearLayout) view.findViewById(R.id.wizard_dob_layout);
        dayTV = (TextView) view.findViewById(R.id.wizard_dob_day);
        monthTV = (TextView) view.findViewById(R.id.wizard_dob_month);
        yearTV = (TextView) view.findViewById(R.id.wizard_dob_year);


        dobLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatePickerDialog();
            }
        });

        return view;
    }


    //problem with first time date setting
    private void setDatePickerDialog() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                this,
                year,month,day
        );
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        month = month + 1;

        Log.d(TAG, "onDateSet: " + year + " / "  +  month + " / " + dayOfMonth);

        String monthName = new DateFormatSymbols().getMonths()[month - 1];
        monthName = monthName.substring(0,3);

        dayTV.setText(String.valueOf(dayOfMonth));
        monthTV.setText(monthName);
        yearTV.setText(String.valueOf(year));

    }
}
