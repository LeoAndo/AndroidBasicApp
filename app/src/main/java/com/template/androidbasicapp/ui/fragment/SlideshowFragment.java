package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.template.androidbasicapp.R;
import com.template.androidbasicapp.databinding.FragmentSlideshowBinding;
import com.template.androidbasicapp.ui.viewmodel.SlideshowViewModel;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    private SlideshowViewModel viewModel;

    public SlideshowFragment() {
        super(R.layout.fragment_slideshow);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.bind(view);
        final TextView textView = binding.textSlideshow;
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}