package com.volunteerx.app.ping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.volunteerx.app.R;
import com.volunteerx.app.utils.RoundedCorner;

public class FragmentPingText extends Fragment {

    private RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ping_a, container, false);

        view.setBackgroundColor(getContext().getColor(R.color.colorF6F6F6));

        relativeLayout = view.findViewById(R.id.relLayout);

        int cornerRadius = 50;
        RoundedCorner.setRoundedCorner(relativeLayout, cornerRadius, RoundedCorner.ROUNDED_TOP);

        return view;

    }
}
