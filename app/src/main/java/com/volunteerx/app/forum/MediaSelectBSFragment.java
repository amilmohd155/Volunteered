/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/12/20 10:10 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/12/20 10:10 PM
 *
 */

package com.volunteerx.app.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.volunteerx.app.R;

public class MediaSelectBSFragment extends BottomSheetDialogFragment {

    private static final String param = "Layout Type";

    @LayoutRes
    private int layoutRes;

    public MediaSelectBSFragment() {
    }


    public static MediaSelectBSFragment newInstance(int layoutRes) {

        MediaSelectBSFragment fragment = new MediaSelectBSFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(param, layoutRes);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutRes = getArguments().getInt(param, 0);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutRes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Todo complete

        if (layoutRes == R.layout.fragment_media_bottom_sheet) { //media -- should show photos and videos(Spinner included)


        }else if (layoutRes == R.layout.fragment_document_bottom_sheet) { // document must show all documents(button to open document)



        }

    }
}
