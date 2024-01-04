package com.template.androidbasicapp;

import android.app.Application;

import com.template.androidbasicapp.common.AppNotificationManager;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.d("IN");
        new AppNotificationManager(this).createNotificationChannels();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppLogger.d("IN");
    }
}
