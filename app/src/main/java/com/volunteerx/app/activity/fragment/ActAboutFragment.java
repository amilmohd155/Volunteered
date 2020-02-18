/*
 * *
 *  * Created by Amil Muhammed Hamza on 1/30/20 10:18 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 1/30/20 10:18 PM
 *
 */

package com.volunteerx.app.activity.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.volunteerx.app.R;
import com.volunteerx.app.binder.ButtonBinder;
import com.volunteerx.app.binder.PeopleBinder;
import com.volunteerx.app.models.ButtonModel;
import com.volunteerx.app.models.PeopleListModel;
import com.volunteerx.app.profile.binder.EmptyStateBinder;
import com.volunteerx.app.profile.binder.FlwHeaderItemBinder;
import com.volunteerx.app.utils.GridImageAdapter;
import com.volunteerx.app.utils.UniversalImageLoader;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

import mva2.adapter.HeaderSection;
import mva2.adapter.ItemSection;
import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.NestedSection;
import mva2.adapter.decorator.Decorator;

public class ActAboutFragment extends Fragment implements OnMapReadyCallback {

    private MultiViewAdapter adapter;

    private MapView mapView;
    private GoogleMap map;
    private RecyclerView rvMembersList;
    private GridView gridView;
    private UniversalImageLoader universalImageLoader;


    public ActAboutFragment() {
    }

    public static ActAboutFragment newInstance() {

        Bundle args = new Bundle();

        ActAboutFragment fragment = new ActAboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MultiViewAdapter();

        universalImageLoader = new UniversalImageLoader(getContext());
        ImageLoader.getInstance().init(universalImageLoader.getConfig());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_act_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.mv_location);
        rvMembersList = view.findViewById(R.id.rv_members_list);
        gridView = view.findViewById(R.id.grid_view);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        setRvMembersList();
        setGridView();
    }

    private void setGridView() {

        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("http://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        imgURLs.add("http://images.unsplash.com/photo-1535498730771-e735b998cd64?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");

        gridView.setNumColumns(2);
        gridView.setHorizontalSpacing(1);
        gridView.setVerticalSpacing(1);

        GridImageAdapter adapter = new GridImageAdapter(getContext(), R.layout.layout_grid_mediaview, "", imgURLs);

        gridView.setAdapter(adapter);

    }

    private void setRvMembersList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMembersList.setLayoutManager(layoutManager);
        rvMembersList.setAdapter(adapter);

        PeopleBinder peopleBinder = new PeopleBinder(getContext(), Glide.with(this));
        FlwHeaderItemBinder headerItemBinder = new FlwHeaderItemBinder();
        ButtonBinder buttonBinder = new ButtonBinder(getContext());

        adapter.registerItemBinders(headerItemBinder, peopleBinder, buttonBinder, new EmptyStateBinder(R.layout.layout_empty_followers)); // must change empty layout

        //Common Section
        ItemSection<ButtonModel> seeAllButtonSection = new ItemSection<>(new ButtonModel("See all"));

        //admin section
        NestedSection adminNest = new NestedSection();
        ListSection<PeopleListModel> adminSection = new ListSection<>();
        HeaderSection<String> headerSection = new HeaderSection<>("Creators and Moderators");

        adminSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                true,
                1));

        adminSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                false,
                1));

        adminSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                false,
                1));

        adminNest.addSection(headerSection);
        adminNest.addSection(adminSection);
        if (adminSection.size() > 3) adminNest.addSection(seeAllButtonSection); //Change section to actual data list

        //Followers Section
        NestedSection followersNest = new NestedSection();
        ListSection<PeopleListModel> followSection = new ListSection<>();
        HeaderSection<String> followerHeaderSection = new HeaderSection<>("People you follow");

        followSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                true,
                1));

        followSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                true,
                1));


        followersNest.addSection(followerHeaderSection);
        followersNest.addSection(followSection);
        if (followSection.size() > 2) followersNest.addSection(seeAllButtonSection);

        //Members Section
        NestedSection membersNest = new NestedSection();
        ListSection<PeopleListModel> memberSection = new ListSection<>();
        HeaderSection<String> mHeaderSection = new HeaderSection<>("Members");

        memberSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                false,
                1));


        memberSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                false,
                1));


        memberSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                false,
                1));


        memberSection.add(new PeopleListModel("Jane Doe",
                "janeDoe155",
                "https://i.postimg.cc/NGdGgv28/da2f9881304925-5cfb6ffe0984e.png",
                false,
                1));

        membersNest.addSection(mHeaderSection);
        membersNest.addSection(memberSection);
        if (memberSection.size() > 3) membersNest.addSection(seeAllButtonSection);


        adapter.addSection(adminNest);
        adapter.addSection(followersNest);
        adapter.addSection(membersNest);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapter = null;
        universalImageLoader = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
