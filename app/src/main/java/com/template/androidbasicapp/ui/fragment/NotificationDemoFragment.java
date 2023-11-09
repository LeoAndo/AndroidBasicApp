package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.template.androidbasicapp.R;
import com.template.androidbasicapp.databinding.FragmentNotificationDemoBinding;

public class NotificationDemoFragment extends Fragment {
    private FragmentNotificationDemoBinding binding;

    public NotificationDemoFragment() {
        super(R.layout.fragment_notification_demo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentNotificationDemoBinding.bind(view);
        binding.message.setText("Hello, NotificationDemoFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}