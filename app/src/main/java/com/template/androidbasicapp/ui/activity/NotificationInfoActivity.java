package com.template.androidbasicapp.ui.activity;

import static com.template.androidbasicapp.common.AppNotificationManager.EXTRA_KEY_NOTIFICATION_ID;
import static com.template.androidbasicapp.common.AppNotificationManager.EXTRA_KEY_TITLE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.material.snackbar.Snackbar;
import com.template.androidbasicapp.AppLogger;
import com.template.androidbasicapp.common.AppNotificationManager;
import com.template.androidbasicapp.databinding.ActivityNotificationInfoBinding;

public class NotificationInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityNotificationInfoBinding binding =
                ActivityNotificationInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final AppNotificationManager manager =
                new AppNotificationManager(this);
        final int notificationId = getIntent().getIntExtra(EXTRA_KEY_NOTIFICATION_ID, -1);
        final String title = getIntent().getStringExtra(EXTRA_KEY_TITLE);

        final String message = "notificationId: " + notificationId + " title: " + title;
        AppLogger.d(message);

        if (notificationId != -1 && !TextUtils.isEmpty(title)) {
            manager.cancelNotification(notificationId);
            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_INDEFINITE).show();
        }
    }
}