package com.template.androidbasicapp;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.d("IN");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppLogger.d("IN");
    }
}
