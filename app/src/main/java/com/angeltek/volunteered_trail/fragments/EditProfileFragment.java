package com.angeltek.volunteered_trail.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.angeltek.volunteered_trail.R;

//todo complete layout, connect media to change dp and coverP.
public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

    ImageView closeBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        closeBtn = (ImageView) view.findViewById(R.id.edit_profile_close);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
                else Log.d(TAG, "onClick: NPE on getActivity()");
            }
        });

        return view;
    }


}
