package com.volunteerx.app.ping;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.volunteerx.app.R;
import com.volunteerx.app.binder.CharacterTypeBBinder;
import com.volunteerx.app.models.CharacterModel;
import com.volunteerx.app.utils.ClickInterface;
import com.volunteerx.app.utils.RoundedCorner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import mva2.adapter.ListSection;
import mva2.adapter.MultiViewAdapter;
import mva2.adapter.util.Mode;
import mva2.adapter.util.OnSelectionChangedListener;

//TODO incomplete
public class PingActivity extends AppCompatActivity implements OnMapReadyCallback, ClickInterface {

    private static final String TAG = "PingActivity";
    private final int cornerRadius = 20;
    private final String PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;
    final Context context = PingActivity.this;

    //Var
    private ArrayList<CharacterModel> characterModels;
    private int buttonType = 1; //0 -- setting button || 1 -- enable button

    //UI
    private FrameLayout container;
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private FloatingActionButton fabCharacter, fabNext;
    private Toolbar toolbar;
    private BottomSheetBehavior sheetBehavior;
    private LinearLayout bottomSheet;
    private RecyclerView characterGrid;
    private LocationErrorFragment errorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        Log.d(TAG, "onCreate: Starting Ping Activity");

        errorFragment = new LocationErrorFragment();

        //UI
        container = findViewById(R.id.container);
        fabCharacter = findViewById(R.id.character_fab);
        fabNext = findViewById(R.id.next_fab);
        toolbar = findViewById(R.id.toolbar);
        bottomSheet = findViewById(R.id.bottom_sheet);
        characterGrid = findViewById(R.id.character_grid);

        //Var
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        characterModels = new ArrayList<>();

        //Call
        permissionHustle();
        bottomSheetHandler();
        nextLevel();

    }

    private void nextLevel() {

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheet.setVisibility(View.GONE);
                fabNext.hide();
                loadFragment(new FragmentPingText(), context.getString(R.string.ping_text_fragment), true);

            }
        });

    }

    private void bottomSheetHandler() {

        RoundedCorner.setRoundedCorner(bottomSheet, cornerRadius, RoundedCorner.ROUNDED_TOP);
        //
        setupCharacterGrid();

        fabCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    fabCharacter.hide();
                    fabNext.show();

                }
                else{
                }
            }
        });

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
//                        fabNext.hide();
//                        fabCharacter.show();
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        fabNext.hide();
                        fabCharacter.show();
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

    }



    /**
     * Sets up the grid for character items
     */
    private void setupCharacterGrid() {

        Log.d(TAG, "setupRecyclerView: populating recyclerView with character cards");

        characterModels.add(new CharacterModel(getResources().getString(R.string.animal), R.color.colorAnimal, R.drawable.car_wan));
        characterModels.add(new CharacterModel(getResources().getString(R.string.art), R.color.colorArt, R.drawable.astronaut));
        characterModels.add(new CharacterModel(getResources().getString(R.string.children), R.color.colorChildren, R.drawable.fairy_tales));
        characterModels.add(new CharacterModel(getResources().getString(R.string.civil), R.color.colorCivil, R.drawable.thought));
        characterModels.add(new CharacterModel(getResources().getString(R.string.disaster), R.color.colorDisaster, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.economic), R.color.colorEconomics, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.education), R.color.colorEducation, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.environment), R.color.colorEnvironment, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.health), R.color.colorHealth, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.human), R.color.colorHuman, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.politics), R.color.colorPolitics, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.poverty), R.color.colorPoverty, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.science), R.color.colorScience, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.social), R.color.colorSocial, R.drawable.icon1));
        characterModels.add(new CharacterModel(getResources().getString(R.string.women), R.color.colorWomen, R.drawable.icon1));

        MultiViewAdapter adapter = new MultiViewAdapter();
        characterGrid.setLayoutManager(new GridLayoutManager(context, 3));
        characterGrid.setAdapter(adapter);


        adapter.registerItemBinders(new CharacterTypeBBinder(context));

        ListSection<CharacterModel> gridSection = new ListSection<>();
        gridSection.addAll(characterModels);

        adapter.addSection(gridSection);
        adapter.setSelectionMode(Mode.MULTIPLE);

        gridSection.setOnSelectionChangedListener(new OnSelectionChangedListener<CharacterModel>() {
            @Override
            public void onSelectionChanged(CharacterModel item, boolean isSelected, List<CharacterModel> selectedItems) {
                //Todo COde for handling the selected item
                Log.d(TAG, "onSelectionChanged: " + item.getCharacterName());
            }
        });

    }

    /**
     * Handles all the permission requests
     */
    private void permissionHustle() {

        Dexter.withActivity(PingActivity.this)
                .withPermission(PERMISSION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Log.d(TAG, "onPermissionGranted: request granted");

                        toolbar.setVisibility(View.VISIBLE);
                        fabCharacter.show();
                        //initializing the map view
                        init();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                        errorFragment = new LocationErrorFragment();
                        errorFragment.setClickInterface(PingActivity.this);
                        loadFragment(errorFragment, context.getString(R.string.location_error_fragment), false);
                        toolbar.setVisibility(View.GONE);
                        fabCharacter.hide();

                        //errorFragment with link to settings
                        if (response.isPermanentlyDenied()) {

                            buttonType = 0;

                        }//error fragment with request again callback
                        else {

                            buttonType = 1;

                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }


    private void init() {

        Log.d(TAG, "init: init staring from permission check");

        mapFragment = new SupportMapFragment();
        setupMapFragment(mapFragment);

    }

    private void setupMapFragment(SupportMapFragment mapFragment) {

        Log.d(TAG, "setupMapFragment: map processing");

        loadFragment(mapFragment, context.getString(R.string.map_fragment), true);

        mapFragment.getMapAsync(this);

    }

    /**
     *
     * @param fragment f
     * @param fragmentName String as fragment name
     * @param fragmentType for backStack fragment -- true && ! == false
     */
    private void loadFragment(Fragment fragment, String fragmentName, Boolean fragmentType) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;

        if (fragmentType) {
            fragmentTransaction= fragmentManager.beginTransaction()
                    .replace(container.getId(), fragment, fragmentName)
                    .addToBackStack(fragmentName);
            fragmentTransaction.commit();
        }else  {
            fragmentTransaction= fragmentManager.beginTransaction()
                    .replace(container.getId(), fragment, fragmentName);
            fragmentTransaction.commit();
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public void buttonClick() {
        Log.d(TAG, "buttonClick: location error|| buttonType ::" + buttonType);

        switch (buttonType) {
            case 0: //button leads to permission settings
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", PingActivity.this.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
                break;
            case 1: //button leads to runtime permission request
                permissionHustle();
                break;
        }


    }
}
