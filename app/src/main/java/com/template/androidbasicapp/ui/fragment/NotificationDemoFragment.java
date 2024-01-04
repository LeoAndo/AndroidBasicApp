package com.template.androidbasicapp.ui.fragment;

import static com.template.androidbasicapp.common.AppNotificationManager.DEFAULT_NOTIFICATION_ID;
import static com.template.androidbasicapp.common.AppNotificationManager.DEFAULT_PENDING_INTENT_REQUEST_CODE;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.template.androidbasicapp.AppLogger;
import com.template.androidbasicapp.R;
import com.template.androidbasicapp.common.AppNotificationManager;
import com.template.androidbasicapp.common.AppNotificationManager.NotificationPriority;
import com.template.androidbasicapp.common.AppNotificationManager.NotificationChannelId;
import com.template.androidbasicapp.databinding.FragmentNotificationDemoBinding;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class NotificationDemoFragment extends Fragment {
    private static final String TAG = NotificationDemoFragment.class.getSimpleName();
    private FragmentNotificationDemoBinding binding;

    private final ActivityResultLauncher<String> requestPermission =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
                Log.d(TAG, "granted: " + granted);
            });

    public NotificationDemoFragment() {
        super(R.layout.fragment_notification_demo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentNotificationDemoBinding.bind(view);

        final AppNotificationManager appNotificationManager = new AppNotificationManager(requireContext());
        binding.spNotificationChannel.setAdapter(
                new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,
                        Arrays.stream(NotificationChannelId.values())
                                .map(NotificationChannelId::name)
                                .collect(Collectors.toList())
                )
        );
        binding.spNotificationPriority.setAdapter(
                new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,
                        Arrays.stream(NotificationPriority.values())
                                .map(NotificationPriority::name)
                                .collect(Collectors.toList())
                )
        );

        binding.btnShowNotification.setOnClickListener(v -> {
            try {
                final int notificationId = Integer.parseInt(binding.edtNotificationId.getText().toString());
                final int requestCode = Integer.parseInt(binding.edtRequestCode.getText().toString());
                final String title = binding.edtContentTitle.getText().toString();
                final String text = binding.edtContentText.getText().toString();
                final Optional<NotificationChannelId> notificationChannelId =
                        Arrays.stream(AppNotificationManager.NotificationChannelId.values()).filter(n -> n.ordinal() == binding.spNotificationChannel.getSelectedItemPosition()).findFirst();
                if (!notificationChannelId.isPresent()) return;
                final Optional<NotificationPriority> notificationPriority =
                        Arrays.stream(NotificationPriority.values()).filter(n -> n.ordinal() == binding.spNotificationPriority.getSelectedItemPosition()).findFirst();
                if (!notificationPriority.isPresent()) return;

                appNotificationManager.showNotification(
                        notificationId,
                        requestCode,
                        notificationChannelId.get().name(),
                        title,
                        text,
                        notificationPriority.get().getPriority()
                );
            } catch (NumberFormatException e) {
                AppLogger.e(e.toString());
            }
        });

        binding.btnRandom.setOnClickListener(v -> {
            binding.edtNotificationId.setText(String.valueOf(UUID.randomUUID().hashCode()));
            binding.edtRequestCode.setText(String.valueOf(UUID.randomUUID().hashCode()));
            binding.edtContentTitle.setText("content title " + UUID.randomUUID().hashCode());
            binding.edtContentText.setText("content text " + UUID.randomUUID().hashCode());

            binding.spNotificationChannel.setSelection(new Random().nextInt(NotificationChannelId.values().length));
            binding.spNotificationPriority.setSelection(new Random().nextInt(NotificationPriority.values().length));
        });
        binding.btnDefaultNotificationId.setOnClickListener(v -> {
            binding.edtNotificationId.setText(String.valueOf(DEFAULT_NOTIFICATION_ID));
        });
        binding.btnDefaultRequestCode.setOnClickListener(v -> {
            binding.edtRequestCode.setText(String.valueOf(DEFAULT_PENDING_INTENT_REQUEST_CODE));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPermission();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            final boolean isDenied =
                    ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_DENIED;
            Log.d(TAG, "isDenied: " + isDenied);
            if (isDenied) {
                requestPermission.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }
}