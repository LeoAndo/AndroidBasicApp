package com.template.androidbasicapp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.template.androidbasicapp.R;

public class NetworkImageView extends androidx.appcompat.widget.AppCompatImageView {
    private static final String TAG = NetworkImageView.class.getSimpleName();

    public NetworkImageView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageUrl(@Nullable final String url) {
        Glide.with(getContext()).load(url).centerCrop().placeholder(R.drawable.ic_placeholder).into(this);
    }
}