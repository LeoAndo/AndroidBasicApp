package com.template.androidbasicapp.data;


import androidx.annotation.NonNull;

public final class Item {
    private String title;

    public Item(@NonNull final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}