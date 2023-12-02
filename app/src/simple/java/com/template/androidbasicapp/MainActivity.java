package com.template.androidbasicapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.template.androidbasicapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // view idが「btn_network_demo」でも、bindingのフィールド名はbtnNetworkDemoのように必ずキャメルケースになる
        binding.btnNetworkDemo.setOnClickListener(v -> NetworkDemoActivity.start(this));
        binding.btnDatabaseDemo.setOnClickListener(v -> DatabaseDemoActivity.start(this));
        binding.btnPreferencesDemo.setOnClickListener(v -> PreferencesDemoActivity.start(this));
        binding.btnNotificationDemo.setOnClickListener(v -> NotificationDemoActivity.start(this));
        binding.btnUiDemo.setOnClickListener(v -> UiDemoActivity.start(this));
        binding.btnMap.setOnClickListener(v -> MapsActivity.start(this, -34, 151));

        binding.fab.setOnClickListener(view -> {
            Snackbar.make(view, "Fabボタンが押されました", Snackbar.LENGTH_SHORT).show();
        });
    }
}