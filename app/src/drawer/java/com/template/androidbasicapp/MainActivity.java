package com.template.androidbasicapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.template.androidbasicapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Nullable
    private AppBarConfiguration appBarConfiguration;
    @Nullable
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(binding.navView.getMenu())
                .setOpenableLayout(binding.drawerLayout)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        binding.appBarMain.fab.setOnClickListener(view -> {
            Snackbar.make(getWindow().getDecorView(), "Fabボタンが押されました", Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (navController == null || appBarConfiguration == null) {
            return super.onSupportNavigateUp();
        }

        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}