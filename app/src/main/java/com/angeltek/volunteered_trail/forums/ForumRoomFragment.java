package com.angeltek.volunteered_trail.forums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.angeltek.volunteered_trail.R;

public class ForumRoomFragment extends Fragment {

    private static final String TAG = "ForumRoomFragment";

    //Constants

    //Variables

    //Widgets
    private ImageView backButton;
    private ImageView infoButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forum_room, container, false);

        backButton = (ImageView) view.findViewById(R.id.back_btn);
        infoButton = (ImageView) view.findViewById(R.id.forum_info_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Backpress Logic
                if (getActivity() != null) getActivity().onBackPressed();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupForumInfoFragment();
            }
        });

        return view;

    }

    private void setupForumInfoFragment() {

        Log.d(TAG, "setupForumInfoFragment: creating info fragment");
        try {

            FragmentTransaction fragmentTransaction = getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.forum_room_container, new ForumInfoFragment(), getResources().getString(R.string.forum_info_fargment))
                    .addToBackStack(null);
            fragmentTransaction.commit();

        }catch (NullPointerException ex) {
            Log.d(TAG, "setupForumInfoFragment: Error encountered in fragment Transaction" + ex);
        }

    }
}
