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

import com.volunteerx.app.R;

import java.util.Calendar;
import java.util.Date;

import mehdi.sakout.fancybuttons.FancyButton;

public class AgeWizardFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "AgeWizardFragment";

    //Var
    private ViewGroup mContainer;

    //UI
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

        setupDOB();

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

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(mContainer.getId(), CharacterWizardFragment.newInstance())
                    .addToBackStack(null)
                    .commit();

        }

    }

    private void setupDOB() {

        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        datePicker.setMaxDate(new Date().getTime());

        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String strDateOfBirth = dayOfMonth + "/" + (++month) + "/" + year;
                        etDateOfBirth.setText(strDateOfBirth);
                        String strAge = String.valueOf(currentYear - year) + " years";
                        tvAge.setText(strAge);

                        if (currentYear - year < 12 ) {

                            nextBtn.setEnabled(false);
                            nextBtn.setBackgroundColor(getContext().getColor(R.color.colorOffWhite)); //make it grey
                            nextBtn.setTextColor(getContext().getColor(R.color.colorFontMajor));

                        }
                        else {

                            nextBtn.setEnabled(true);
                            nextBtn.setBackgroundColor(getContext().getColor(R.color.colorWhite));
                            nextBtn.setTextColor(getContext().getColor(R.color.colorVolunteerX));

                        }

                    }
                });

    }
}
