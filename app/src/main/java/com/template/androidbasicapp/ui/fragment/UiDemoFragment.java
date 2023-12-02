package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import com.template.androidbasicapp.BuildConfig;
import com.template.androidbasicapp.R;
import com.template.androidbasicapp.databinding.FragmentUiDemoBinding;

public class UiDemoFragment extends Fragment {
    private FragmentUiDemoBinding binding;

    public UiDemoFragment() {
        super(R.layout.fragment_ui_demo);
    }

    /**
     * ActivityのonCreateメソッドで処理するようなコードはここに書く
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentUiDemoBinding.bind(view);
        // pokedex apiのポケモン画像を取得する https://pokeapi.co/
        final String pokemon = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/150.png";
        binding.imageNetwork.setImageUrl(pokemon);
        binding.imageNetworkPlaceholder.setImageUrl(null);

        if (!BuildConfig.FLAVOR.equals("simple")) {
            binding.map.setOnClickListener(v -> {
                NavHostFragment.findNavController(this).navigate(UiDemoFragmentDirections.actionUiDemoFragmentToMapsFragment(-34, 151));
            });
        } else {
            binding.map.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * ActivityのonPauseメソッドで処理するようなコードはここに書く
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * ActivityのonDestroyメソッドで処理するようなコードはここに書く
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}