package com.template.androidbasicapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;

import com.template.androidbasicapp.AppLogger;
import com.template.androidbasicapp.R;
import com.template.androidbasicapp.databinding.FragmentUiDemoBinding;

public class UiDemoFragment extends Fragment {
    private static final String TAG = "UiDemoFragment";
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
        AppLogger.d("IN");
        binding = FragmentUiDemoBinding.bind(view);
    }

    /**
     * ActivityのonPauseメソッドで処理するようなコードはここに書く
     */
    @Override
    public void onPause() {
        super.onPause();
        AppLogger.d("IN");
    }

    /**
     * ActivityのonDestroyメソッドで処理するようなコードはここに書く
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppLogger.d("IN");
        binding = null;
    }
}