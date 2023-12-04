package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.SystemClock;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.template.androidbasicapp.BuildConfig;
import com.template.androidbasicapp.R;
import com.template.androidbasicapp.data.Item;
import com.template.androidbasicapp.databinding.FragmentUiDemoBinding;
import com.template.androidbasicapp.ui.widget.MyListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

        // リストの処理 -START
        final List<Item> items = new ArrayList<>();
        for (int i = 1; i < 29; i++) {
            items.add(new Item("鉄人" + i + "号"));
        }
        final MyListAdapter adapter = new MyListAdapter(items);
        binding.listHorizontal.setAdapter(adapter);
        // 縦向きの場合 -> LinearLayoutManager.VERTICALを指定する
        binding.listHorizontal.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter.setOnItemClickListener((item, position) -> {
            Snackbar.make(binding.getRoot(), position + " : " + item.getTitle(), Snackbar.LENGTH_LONG).show();
        });

        binding.insert.setOnClickListener(v -> {
            final String title = "鉄人" + SystemClock.elapsedRealtime() + "号";
            adapter.insertItem(0, new Item(title));
        });
        binding.update.setOnClickListener(v -> {
            adapter.updateItem(0);
        });
        binding.remove.setOnClickListener(v -> {
            adapter.removeItem(0);
        });
        // リストの処理 -END
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