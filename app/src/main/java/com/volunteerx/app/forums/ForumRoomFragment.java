package com.volunteerx.app.forums;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.volunteerx.app.R;

//Todo make recyclerview with image, video, document support
public class ForumRoomFragment extends Fragment {

    private static final String TAG = "ForumRoomFragment";

    //Constants

    //Variables

    //Widgets
    private ImageView backButton;
    private ImageView infoButton;
    private EditText messageEntry;
    private ImageButton imageButton, docButton, cameraButton, sendButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forum_room, container, false);

        backButton = (ImageView) view.findViewById(R.id.back_btn);
        infoButton = (ImageView) view.findViewById(R.id.forum_info_btn);
        messageEntry = (EditText) view.findViewById(R.id.message_text);
        imageButton = (ImageButton) view.findViewById(R.id.img_btn);
        docButton = (ImageButton) view.findViewById(R.id.doc_btn);
        cameraButton = (ImageButton) view.findViewById(R.id.camera_btn);
        sendButton = (ImageButton) view.findViewById(R.id.send_btn);


        //handles send button and other button behaviors
        messageEntry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() != 0) {
                    imageButton.setVisibility(View.GONE);
                    docButton.setVisibility(View.GONE);
                    cameraButton.setVisibility(View.GONE);
                    sendButton.setVisibility(View.VISIBLE);
                }
                else {
                    sendButton.setVisibility(View.GONE);
                    imageButton.setVisibility(View.VISIBLE);
                    docButton.setVisibility(View.VISIBLE);
                    cameraButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

    private void setupForumRecyclerView() {

    }
}
