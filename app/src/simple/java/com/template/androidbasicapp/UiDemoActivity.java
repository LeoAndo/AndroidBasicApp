package com.template.androidbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class UiDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_demo);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, UiDemoActivity.class);
        context.startActivity(starter);
    }
}