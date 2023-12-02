package com.template.androidbasicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.template.androidbasicapp.ui.fragment.MapsFragmentArgs;

public class MapsActivity extends AppCompatActivity {
    private static final String TAG = "MapsActivity";
    private static final String EXTRA_LATITUDE = "EXTRA_LATITUDE";
    private static final String EXTRA_LONGITUDE = "EXTRA_LONGITUDE";
    private final OnMapReadyCallback callback = googleMap -> {
        final float latitude = getIntent().getFloatExtra(EXTRA_LATITUDE, 0);
        final float longitude = getIntent().getFloatExtra(EXTRA_LONGITUDE, 0);
        Log.d(TAG, "latitude: " + latitude + " longitude: " + longitude);
        final LatLng sydney = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public static void start(@NonNull final Context context, final float latitude, final float longitude) {
        Intent starter = new Intent(context, MapsActivity.class);
        starter.putExtra(EXTRA_LATITUDE, latitude);
        starter.putExtra(EXTRA_LONGITUDE, longitude);
        context.startActivity(starter);
    }
}