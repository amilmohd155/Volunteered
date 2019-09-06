package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.angeltek.volunteered_trail.R;

//todo complete layout, connect media to change dp and coverP.
public class EditProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "EditProfileFragment";

    private final String TXT_USERNAME = "EditUsernameFragment";
    private final String  TXT_BIO= "EditBioFragment";
    private final String TXT_EMAIL = "EditEmailFragment";
    private final String TXT_PHONE = "EditPhoneFragment";
    private final String TXT_GENDER = "EditGenderFragment";
    private final String TXT_CHARACTER = "EditCharacterFragment";


    private ImageView closeBtn;
    private TextInputEditText name, username, bio, website, emailAddress, phoneNumber, gender;
    private LinearLayout character;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        closeBtn = view.findViewById(R.id.edit_close);

        name = view.findViewById(R.id.edit_name);
        username = view.findViewById(R.id.edit_username);
        bio = view.findViewById(R.id.edit_bio);
        website = view.findViewById(R.id.edit_website);
        emailAddress = view.findViewById(R.id.edit_email);
        phoneNumber = view.findViewById(R.id.edit_phone);
        gender = view.findViewById(R.id.edit_gender);
        character = view.findViewById(R.id.edit_character);

        closeBtn.setOnClickListener(this);
        username.setOnClickListener(this);
        bio.setOnClickListener(this);
        emailAddress.setOnClickListener(this);
        phoneNumber.setOnClickListener(this);
        gender.setOnClickListener(this);
        character.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.edit_username:
                Log.d(TAG, "onClick: username");
                setupChildFragment(new EditUsernameFragment(), TXT_USERNAME);
                break;
            case R.id.edit_bio:
                setupChildFragment(new EditBioFragment(), TXT_BIO);
                break;
            case R.id.edit_email:
                setupChildFragment(new EditEmailFragment(), TXT_EMAIL);
                break;
            case R.id.edit_phone:
                setupChildFragment(new EditPhoneFragment(), TXT_PHONE);
                break;
            case R.id.edit_gender:
                setupChildFragment(new EditGenderFragment(), TXT_GENDER);
                break;
            case R.id.edit_character:
                setupChildFragment(new EditCharacterFragment(), TXT_CHARACTER);
                break;
            case R.id.edit_close:
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
                else Log.d(TAG, "onClick: NPE on getActivity()");
                break;
        }
    }

    /**
     * Sets up the child fragment based on the constant send to the fragment
     * @param fragment
     * @param fragmentName
     */
    private void setupChildFragment(Fragment fragment, String fragmentName) {

        Log.d(TAG, "setupChildFragment: edit profile child fragments::" + fragmentName);

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                .replace(R.id.master_layout, fragment, fragmentName)
                .addToBackStack(null);
        fragmentTransaction.commit();

    }
}
