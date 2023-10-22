package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.template.androidbasicapp.R;
import com.template.androidbasicapp.databinding.FragmentPreferencesDemoBinding;
import com.template.androidbasicapp.ui.viewmodel.PreferencesDemoViewModel;

/**
 * 機能のTop画面
 */
public class PreferencesDemoFragment extends Fragment {

    private FragmentPreferencesDemoBinding binding;

    private PreferencesDemoViewModel viewModel;

    public PreferencesDemoFragment() {
        super(R.layout.fragment_preferences_demo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PreferencesDemoViewModel.class);
        binding = FragmentPreferencesDemoBinding.bind(view);
        final TextView textView = binding.textSlideshow;
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}