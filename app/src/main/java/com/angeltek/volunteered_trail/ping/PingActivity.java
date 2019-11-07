package com.angeltek.volunteered_trail.ping;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;


import com.angeltek.volunteered_trail.R;
import com.angeltek.volunteered_trail.utils.ClickInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class PingActivity extends AppCompatActivity implements OnMapReadyCallback, ClickInterface {

    private static final String TAG = "PingActivity";
    private int buttonType = 1; //0 -- setting button || 1 -- enable button

    final Context context = PingActivity.this;
    private LocationErrorFragment errorFragment;

    private final String PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;

    private FrameLayout container;
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        Log.d(TAG, "onCreate: Starting Ping Activity");

        errorFragment = new LocationErrorFragment();

        container = findViewById(R.id.container);
        fab = findViewById(R.id.character_fab);
        toolbar = findViewById(R.id.toolbar);

        permissionHustle();

    }

    private void permissionHustle() {

        Dexter.withActivity(PingActivity.this)
                .withPermission(PERMISSION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Log.d(TAG, "onPermissionGranted: request granted");

                        toolbar.setVisibility(View.VISIBLE);
                        fab.show();
                        //initializing the map view
                        init();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                        errorFragment = new LocationErrorFragment();
                        errorFragment.setClickInterface(PingActivity.this);
                        loadFragment(errorFragment, context.getString(R.string.location_error_fragment), false);
                        toolbar.setVisibility(View.GONE);
                        fab.hide();

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

        loadFragment(mapFragment, context.getString(R.string.map_fragment), false);

        mapFragment.getMapAsync(this);

    }

    /**
     *
     * @param fragment f
     * @param fragmentName String as fragment name
     * @param fragmentType for backStack fragment -- false && ! == true
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
