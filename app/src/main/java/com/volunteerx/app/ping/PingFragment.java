/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/3/20 8:31 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/3/20 8:31 PM
 *
 */

package com.volunteerx.app.ping;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.volunteerx.app.R;
import com.volunteerx.app.utils.Permissions;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;

public class PingFragment extends Fragment implements
        OnMapReadyCallback,
        LocationErrorFragment.OnFragmentInteractionListener,
        CharacterBSFragment.OnFragmentInteractionListener {

    private static final String TAG = "PingFragment";
    private final String PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    //Var
    private int responseType = 1; //0 -- setting button || 1 -- enable button

    //UI
    private FrameLayout localContainer;
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private FloatingActionButton fabCharacter, fabNext;
    private Toolbar toolbar;
    private LinearLayout bottomSheet;

    public PingFragment() {
    }

    public static PingFragment newInstance() {

        Bundle args = new Bundle();

        PingFragment fragment = new PingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ping, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //UI
        localContainer = view.findViewById(R.id.map_container);
        fabCharacter = view.findViewById(R.id.character_fab);
        toolbar = view.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null) getActivity().onBackPressed();
        });

        fabCharacter.setOnClickListener(view1 -> {
            CharacterBSFragment fragment = CharacterBSFragment.newInstance();
            fragment.show(getChildFragmentManager(), fragment.getTag());
        });

        permissionHustle();

    }

    private void permissionHustle() {

        int type = Permissions.setupPermission(getActivity(), PERMISSION);

        if (type == 0) {

            toolbar.setVisibility(View.VISIBLE);
            fabCharacter.show();
            //initializing the map view
            initMap();

        }else {

            toolbar.setVisibility(View.GONE);
            fabCharacter.hide();
            loadFragment(LocationErrorFragment.newInstance(), getContext().getString(R.string.location_error_fragment));

            if (type == 1)
                responseType = 1;
            else if (type == 2)
                responseType = 2;

        }
    }

    private void initMap() {

        mapFragment = SupportMapFragment.newInstance();

        loadFragment(mapFragment, getContext().getString(R.string.map_fragment));
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    /**
     *
     * @param fragment f
     * @param fragmentName String as fragment name
     */
    private void loadFragment(Fragment fragment, String fragmentName) {

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction;

        fragmentTransaction = fragmentManager.beginTransaction()
                .replace(localContainer.getId(), fragment, fragmentName)
                .addToBackStack(fragmentName);
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction() {

        switch (responseType) {
            case 0: //button leads to permission settings
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                getContext().startActivity(intent);
                break;
            case 1: //button leads to runtime permission request
                permissionHustle();
                break;
        }

    }

    @Override
    public void onBSFragmentInteraction() {
        replaceFragment(new PingFragmentB(), "PingFragmentB", getFragmentManager());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
