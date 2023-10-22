package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.template.androidbasicapp.databinding.FragmentNetworkDemoBinding;

import com.template.androidbasicapp.R;
import com.template.androidbasicapp.ui.viewmodel.NetworkDemoViewModel;

public class NetworkDemoFragment extends Fragment {

    private FragmentNetworkDemoBinding binding;

    private NetworkDemoViewModel viewModel;

    public NetworkDemoFragment() {
        super(R.layout.fragment_network_demo);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NetworkDemoViewModel.class);
        binding = FragmentNetworkDemoBinding.bind(view);
        final TextView textView = binding.textHome;
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}