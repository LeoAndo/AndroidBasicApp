package com.template.androidbasicapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.template.androidbasicapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // view idが「btn_network_demo」でも、bindingのフィールド名はbtnNetworkDemoのように必ずキャメルケースになる
        binding.btnNetworkDemo.setOnClickListener(v -> {
            NetworkDemoActivity.start(this);
        });
        binding.btnDatabaseDemo.setOnClickListener(v -> {
            DatabaseDemoActivity.start(this);
        });
        binding.btnPreferencesDemo.setOnClickListener(v -> {
            PreferencesDemoActivity.start(this);
        });

        binding.fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", v -> {

                    }).show();
        });
    }
}