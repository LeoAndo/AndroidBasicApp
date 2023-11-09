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
import com.template.androidbasicapp.databinding.FragmentUiDemoBinding;

public class UiDemoFragment extends Fragment {
    private FragmentUiDemoBinding binding;

    public UiDemoFragment() {
        super(R.layout.fragment_ui_demo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentUiDemoBinding.bind(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}