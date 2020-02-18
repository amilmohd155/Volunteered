/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/2/20 10:29 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/2/20 10:29 PM
 *
 */

package com.volunteerx.app.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volunteerx.app.R;
import com.volunteerx.app.forum.ForumsFragment;
import com.volunteerx.app.location.NearbyFragment;
import com.volunteerx.app.utils.IOonBackPressed;
import com.volunteerx.app.utils.SectionsStatePagerAdapter;

import static com.volunteerx.app.utils.Constants.FORUM_VIEW;
import static com.volunteerx.app.utils.Constants.HOME_VIEW;
import static com.volunteerx.app.utils.Constants.NEARBY_VIEW;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements IOonBackPressed, OnFragmentInteractionListener {


//    private OnFragmentInteractionListener mListener;
    private ViewPager viewPager;

    public MainFragment(){
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //add argument
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.view_pager);

        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getChildFragmentManager());

        Runnable runnable = () -> {

            adapter.addFragment(NearbyFragment.newInstance(), "NearbyFragment");
            adapter.addFragment(HomeFragment.newInstance(), "HomeFragment");
            adapter.addFragment(ForumsFragment.newInstance(), "ForumsFragment");

        };

        runnable.run();

        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    @Override
    public boolean onBackPressed() {
        if (viewPager.getCurrentItem() != 1) {
            viewPager.setCurrentItem(1, true);
            return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(int interactionType) {
        switch (interactionType) {
            case NEARBY_VIEW: viewPager.setCurrentItem(0);
                break;
            case FORUM_VIEW:  viewPager.setCurrentItem(2);
                break;
            case HOME_VIEW: viewPager.setCurrentItem(1);
        }
    }

}
