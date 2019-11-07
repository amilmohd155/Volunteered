package com.angeltek.volunteered_trail.ping;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.utils.ClickInterface;

public class LocationErrorFragment extends Fragment {

    private static final String TAG = "LocationErrorFragment";

    private ClickInterface clickInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: fragment started");
        View view = inflater.inflate(R.layout.fragment_fine_location_error, container, false);

        Button enableBtn = view.findViewById(R.id.btn_enable);

        enableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInterface.buttonClick();
            }
        });

        return view;

    }

    public void setClickInterface(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


}
