package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.template.androidbasicapp.R;
import com.template.androidbasicapp.databinding.FragmentDatabaseDemoBinding;
import com.template.androidbasicapp.ui.viewmodel.DatabaseDemoViewModel;
/**
 * 機能のTop画面
 */
public class DatabaseDemoFragment extends Fragment {

    private FragmentDatabaseDemoBinding binding;
    private DatabaseDemoViewModel viewModel;

    public DatabaseDemoFragment() {
        super(R.layout.fragment_database_demo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DatabaseDemoViewModel.class);
        binding = FragmentDatabaseDemoBinding.bind(view);
        final TextView textView = binding.textGallery;
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}