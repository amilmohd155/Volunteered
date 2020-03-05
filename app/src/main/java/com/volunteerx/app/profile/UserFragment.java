/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/11/20 12:28 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/11/20 12:28 AM
 *
 */

package com.volunteerx.app.profile;


import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.volunteerx.app.R;
import com.volunteerx.app.databinding.FragmentUserBinding;
import com.volunteerx.app.fragments.EditBioFragment;
import com.volunteerx.app.fragments.EditProfileFragment;
import com.volunteerx.app.models.User;
import com.volunteerx.app.profile.fragment.AccountBottomSheetFragment;
import com.volunteerx.app.profile.fragment.FollowStaticFragment;
import com.volunteerx.app.profile.fragment.UserActivitiesFragment;
import com.volunteerx.app.profile.fragment.UserCharacterBSFragment;
import com.volunteerx.app.profile.fragment.UserContactBSFragment;
import com.volunteerx.app.profile.fragment.UserPostFragment;
import com.volunteerx.app.profile.viewmodel.UserViewModel;
import com.volunteerx.app.utils.BottomNavigationViewHelper;
import com.volunteerx.app.utils.GlideApp;
import com.volunteerx.app.utils.SectionsStatePagerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.volunteerx.app.utils.Converter.NumberFormatting;
import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment implements View.OnClickListener, UserContactBSFragment.ElementClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "isUserFragment";
    private static final String ARG_PARAM2 = "Username";


    private static final String TAG = "UserFragment";

    //Variables
    private User user;
    private UserViewModel userViewModel;
    private boolean isUserFragment; //true :: user false:: profile
    private String username; //username given to the fragment by the calling fragment


    //UI
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;


    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment UserFragment.
     */
    public static UserFragment newInstance(boolean isUserFragment) {
        UserFragment fragment = new UserFragment();

        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isUserFragment);
//        args.putString(ARG_PARAM2, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isUserFragment = getArguments().getBoolean(ARG_PARAM1);
//            username = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentUserBinding binding;

        if (!isUserFragment) {

            return inflater.inflate(R.layout.fragment_profile, container, false); //profile fragment
        }

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        binding.setLifecycleOwner(this);
        binding.setUserViewModel(userViewModel);
        binding.setIsUserFragment(isUserFragment);
        binding.setFragment(this);

        setupUserView(binding.getRoot());

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.user_tab_container);
        tabLayout = view.findViewById(R.id.tabs_user);

        setupViewPager();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * used to setup user views
     * @param view the root view
     */
    private void setupUserView(View view) {

        setupBottomNavigationView(view);

        userViewModel.queryData();
        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                userViewModel.progressValue.postValue(View.GONE);
                this.user = user;
            }
        });

    }

    /**
     * used to setup profile views
     * @param view the root view
     */
    private void setupProfileView(View view) {

        //if following
//        genButton.setText(getContext().getString(R.string.follow));

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);


        final AppBarLayout appBarLayout = view.findViewById(R.id.app_bar_layout);

        //Showing user name on collapsed and swipe refresh
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    swipeRefreshLayout.setOnChildScrollUpCallback((parent, child) -> false);
                }

                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar.setTitle("Title");
                    swipeRefreshLayout.setOnChildScrollUpCallback((parent, child) -> true);
                    isShow = true;
                } else if(isShow) {
                    toolbar.setTitle(null);//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });


        toolbar.setNavigationOnClickListener(view1 -> {
            if (getActivity()!=null) {
                getActivity().onBackPressed();
            }
        });

    }

    /**
     * Setting up fragments(tabs)
     */
    private void setupViewPager() {

        SectionsStatePagerAdapter statePagerAdapter = new SectionsStatePagerAdapter(getChildFragmentManager());
        statePagerAdapter.addFragment(UserPostFragment.newInstance(), getResources().getString(R.string.tab_post));
        statePagerAdapter.addFragment(UserActivitiesFragment.newInstance(), getResources().getString(R.string.tab_activities));

        viewPager.setAdapter(statePagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText(R.string.tab_post);
        tabLayout.getTabAt(1).setText(R.string.tab_activities);

    }


    @Override
    public void onClick(View view) {
    }

    public void openContactBSFragment() {
        Log.d(TAG, "openContactBSFragment: clicked");
        final UserContactBSFragment ucFragment =  UserContactBSFragment.newInstance(user, isUserFragment);
        ucFragment.show(getChildFragmentManager(), ucFragment.getTag());
    }

    public void openAccountBSFragment() {
        final AccountBottomSheetFragment fragment = AccountBottomSheetFragment.newInstance(user);
        fragment.show(getChildFragmentManager(), fragment.getTag());
    }

    public void openEditFragment() {
        replaceFragment(EditProfileFragment.newInstance(user), "EditProfileFragment", getActivity().getSupportFragmentManager());
    }

    public void lookUpCharactersFragment() {
        final UserCharacterBSFragment uCFragment = UserCharacterBSFragment.newInstance(user);
        uCFragment.show(getChildFragmentManager(), uCFragment.getTag());
    }

    public void gotToStatics(int type) {
        replaceFragment(FollowStaticFragment.newInstance(type), "FollowStaticFragment", getActivity().getSupportFragmentManager());
    }

    public void openBioEdit(String bio) {
        if(bio != null && bio.isEmpty()) {
            replaceFragment(new EditBioFragment(), "EditBioFragment", getActivity().getSupportFragmentManager());
        }
    }

    /**
     * Setting up BottomNavigationView
     * @param view
     */
    private void setupBottomNavigationView(View view) {


        BottomNavigationViewEx bottomNavigationViewEx = view.findViewById(R.id.bottomNavViewBar);
        FloatingActionButton pingFab = view.findViewById(R.id.ping_fab);

        bottomNavigationViewEx.setCurrentItem(4);

        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(getContext(), bottomNavigationViewEx, pingFab, getFragmentManager());

    }

    @Override
    public void onElementClickListener(int ElementType) {
        if (ElementType == ELEMENT_TYPE_A ) {
            openEditFragment();
        }else if (ElementType == ELEMENT_TYPE_B) {
            //follow code
        }
    }
}
