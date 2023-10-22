package com.template.androidbasicapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}