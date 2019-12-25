package com.volunteerx.app.startup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.ClickListener;
import com.volunteerx.app.utils.StatusColorClass;

import static com.volunteerx.app.utils.Constants.FP_BETA;
import static com.volunteerx.app.utils.Constants.FP_EMAIL;
import static com.volunteerx.app.utils.Constants.FP_FINAL;
import static com.volunteerx.app.utils.Constants.FP_PHONE;
import static com.volunteerx.app.utils.Constants.FP_USERNAME;
import static com.volunteerx.app.utils.StatusColorClass.DARK_STATUS_BAR_ICON;


public class ForgotPasswordFragment extends Fragment implements ClickListener {

    private static final String TAG = "ForgotPasswordFragment";

    //Var
    private ViewGroup mContainer;

    //UI
    private FrameLayout flContainer;

    public ForgotPasswordFragment() {
        //empty constructor
    }

    public static ForgotPasswordFragment newInstance(){

        ForgotPasswordFragment fragment = new ForgotPasswordFragment();

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

        View view = inflater.inflate(R.layout.layout_container, container, false);
        StatusColorClass.setStatusColor(getActivity(), getContext().getColor(R.color.colorWhite), DARK_STATUS_BAR_ICON);
        mContainer = container;

        flContainer = view.findViewById(R.id.container);

        loadFragment(FPSlideAlphaFragment.newInstance(), getString(R.string.fp_slide_alpha_fragment));

//        FPSlideAlphaFragment
//        FPSlidePhoneFragment
//        FPSlideEmailFragment
//        FPSlideUsernameFragment
//        FPSlideBetaFragment

        return view;

    }

    private void loadFragment(Fragment fragment, String fragmentName) {

        Log.d(TAG, "loadFragment: loading fragments");

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction()
                .replace(flContainer.getId(), fragment)
                .addToBackStack(fragmentName);
        fragmentTransaction.commit();

    }

    @Override
    public void buttonClick(int type) {

        switch (type) {
            case FP_USERNAME: {
                loadFragment(FPSlideUsernameFragment.newInstance(), getString(R.string.fp_slide_username_fragment));
            }
            break;
            case FP_PHONE: {
                loadFragment(FPSlidePhoneFragment.newInstance(), getString(R.string.fp_slide_phone_fragment));
            }
            break;
            case FP_EMAIL: {
                loadFragment(FPSlideEmailFragment.newInstance(), getString(R.string.fp_slide_email_fragment));
            }
            break;
            case FP_BETA: {
                loadFragment(FPSlideBetaFragment.newInstance(), getString(R.string.fp_slide_beta_fragment));
            }
            break;
            case FP_FINAL: {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(this)
                        .replace(mContainer.getId(), new StartupFragment())
                        .commit();
            }
        }
    }

    @Override
    public boolean onLongClick(int args) {
        return false;
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
