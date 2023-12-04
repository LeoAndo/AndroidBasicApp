package com.template.androidbasicapp.data;


import androidx.annotation.NonNull;

public final class Item {
    @NonNull
    private String title;

    public Item(@NonNull final String title) {
        this.title = title;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull final String title) {
        this.title = title;
    }
}