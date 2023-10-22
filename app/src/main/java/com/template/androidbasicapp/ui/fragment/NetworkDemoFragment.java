package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.template.androidbasicapp.BuildConfig;
import com.template.androidbasicapp.databinding.FragmentNetworkDemoBinding;

import com.template.androidbasicapp.R;
import com.template.androidbasicapp.ui.viewmodel.NetworkDemoViewModel;

/**
 * 機能のTop画面
 */
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
        viewModel.getText().observe(getViewLifecycleOwner(), binding.textHome::setText);

        if (!BuildConfig.FLAVOR.equals("simple")) {
            binding.btnNextFragment.setOnClickListener(v -> {
                NavHostFragment.findNavController(this).navigate(
                        NetworkDemoFragmentDirections.actionNetworkDemoFragmentToSecondFragment(binding.textHome.getText().toString())
                );
            });
        } else {
            binding.btnNextFragment.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}