package com.template.androidbasicapp.ui.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.template.androidbasicapp.R;
import com.template.androidbasicapp.databinding.FragmentMapsBinding;

public class MapsFragment extends Fragment {
    private static final String TAG = "MapsFragment";
    private FragmentMapsBinding binding;

    public MapsFragment() {
        super(R.layout.fragment_maps);
    }

    private final OnMapReadyCallback callback = googleMap -> {
        final float latitude = MapsFragmentArgs.fromBundle(getArguments()).getLatitude();
        final float longitude = MapsFragmentArgs.fromBundle(getArguments()).getLongitude();
        Log.d(TAG, "latitude: " + latitude + " longitude: " + longitude);
        final LatLng sydney = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMapsBinding.bind(view);
        final SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}