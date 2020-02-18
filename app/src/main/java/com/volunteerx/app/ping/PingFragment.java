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
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.volunteerx.app.R;
import com.volunteerx.app.binder.CharacterTypeBBinder;
import com.volunteerx.app.models.CharacterModel;
import com.volunteerx.app.utils.Permissions;
import com.volunteerx.app.utils.RoundedCorner;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;

import static com.volunteerx.app.utils.Constants.CHAR_ANI;
import static com.volunteerx.app.utils.Constants.CHAR_ART;
import static com.volunteerx.app.utils.Constants.CHAR_CLD;
import static com.volunteerx.app.utils.Constants.CHAR_CVL;
import static com.volunteerx.app.utils.Constants.CHAR_DSR;
import static com.volunteerx.app.utils.Constants.CHAR_ECO;
import static com.volunteerx.app.utils.Constants.CHAR_EDU;
import static com.volunteerx.app.utils.Constants.CHAR_ENV;
import static com.volunteerx.app.utils.Constants.CHAR_HLH;
import static com.volunteerx.app.utils.Constants.CHAR_HMN;
import static com.volunteerx.app.utils.Constants.CHAR_POL;
import static com.volunteerx.app.utils.Constants.CHAR_POV;
import static com.volunteerx.app.utils.Constants.CHAR_SCI;
import static com.volunteerx.app.utils.Constants.CHAR_SCL;
import static com.volunteerx.app.utils.Constants.CHAR_WMN;
import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;

//Todo Super Incomplete  error in back press, maybe a need to change UI
public class PingFragment extends Fragment implements
        View.OnClickListener,
        OnMapReadyCallback,
        LocationErrorFragment.OnFragmentInteractionListener {

    private static final String TAG = "PingFragment";
    private final String PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    //Var
    private int responseType = 1; //0 -- setting button || 1 -- enable button
    private MultiViewAdapter adapter = new MultiViewAdapter();
    private ListSection<CharacterModel> characterSection = new ListSection<>();

    //UI
    private FrameLayout localContainer;
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private FloatingActionButton fabCharacter, fabNext;
    private Toolbar toolbar;
    private BottomSheetBehavior sheetBehavior;
    private RelativeLayout bottomSheet;
    private ImageView closeBtn;
    private RecyclerView recyclerView;

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
        fabNext = view.findViewById(R.id.next_fab);
        toolbar = view.findViewById(R.id.toolbar);
        bottomSheet = view.findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        closeBtn = view.findViewById(R.id.close_btn);
        recyclerView = view.findViewById(R.id.character_grid);

        fabCharacter.setOnClickListener(this);
        fabNext.setOnClickListener(this);
        localContainer.setOnClickListener(this);
        closeBtn.setOnClickListener(this);

        RoundedCorner.setRoundedCorner(view.findViewById(R.id.linearLayout), 20, RoundedCorner.ROUNDED_TOP);

        toolbar.setNavigationOnClickListener(v -> {
            if (getActivity() != null) getActivity().onBackPressed();
        });


        permissionHustle();
        setBottomSheet();
//        setRecyclerView();

    }

    private void setRecyclerView() {

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.addItemDecoration(new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.character_spacing)));

        recyclerView.setAdapter(adapter);

        adapter.registerItemBinders(new CharacterTypeBBinder(getContext(), Glide.with(this)));

        characterSection.add(new CharacterModel(CHAR_ANI, R.string.animal, R.drawable.art));
        characterSection.add(new CharacterModel(CHAR_ART, R.string.art, R.drawable.astronaut));
        characterSection.add(new CharacterModel(CHAR_CLD, R.string.children, R.drawable.fairy_tales));
        characterSection.add(new CharacterModel(CHAR_CVL, R.string.civil, R.drawable.fernando_nunes));
        characterSection.add(new CharacterModel(CHAR_DSR, R.string.disaster, R.drawable.art));
        characterSection.add(new CharacterModel(CHAR_ECO, R.string.economic, R.drawable.fernando_nunes));
        characterSection.add(new CharacterModel(CHAR_EDU, R.string.education, R.drawable.astronaut));
        characterSection.add(new CharacterModel(CHAR_ENV, R.string.environment, R.drawable.thought));
        characterSection.add(new CharacterModel(CHAR_HLH, R.string.health, R.drawable.car_wan));
        characterSection.add(new CharacterModel(CHAR_HMN, R.string.human, R.drawable.astronaut));
        characterSection.add(new CharacterModel(CHAR_POL, R.string.politics, R.drawable.fairy_tales));
        characterSection.add(new CharacterModel(CHAR_POV, R.string.poverty, R.drawable.astronaut));
        characterSection.add(new CharacterModel(CHAR_SCI, R.string.science, R.drawable.fernando_nunes));
        characterSection.add(new CharacterModel(CHAR_SCL, R.string.social, R.drawable.fairy_tales));
        characterSection.add(new CharacterModel(CHAR_WMN, R.string.women, R.drawable.thought));

        adapter.addSection(characterSection);
        adapter.setSelectionMode(Mode.MULTIPLE);

        characterSection.setOnSelectionChangedListener((item, isSelected, selectedItems) -> {
            if (!selectedItems.isEmpty()) {
                fabNext.show();
            }
            else fabNext.hide();

        });

    }

    private void setBottomSheet() {
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                    case BottomSheetBehavior.STATE_DRAGGING:
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            fabCharacter.hide();
                            closeBtn.setVisibility(View.VISIBLE);
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            closeBtn.setVisibility(View.GONE);
                            fabCharacter.show();
                            break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.character_fab:
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    fabCharacter.hide();
                    closeBtn.setVisibility(View.VISIBLE);
                }else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    closeBtn.setVisibility(View.GONE);
                    if ((fabNext.getVisibility() == View.VISIBLE)) {
                        fabNext.hide();
                    } else {
                        fabNext.show();
                    }
                    fabCharacter.show();
                }
                break;
            case R.id.map_container:
            case R.id.close_btn:
                if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    bottomSheet.clearFocus();
                    closeBtn.setVisibility(View.GONE);
                    if ((fabNext.getVisibility() == View.VISIBLE)) {
                        fabNext.hide();
                    } else {
                        fabNext.show();
                    }
                    fabCharacter.show();
                }
                break;
            case R.id.next_fab:
                replaceFragment(PingFragmentB.newInstance(), "PingFragmentB", getFragmentManager());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }


    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

        }
    }

}
