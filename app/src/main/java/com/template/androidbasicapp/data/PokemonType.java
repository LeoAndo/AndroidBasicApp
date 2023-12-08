package com.template.androidbasicapp.data;

import androidx.annotation.NonNull;

public enum PokemonType {
    ALL("全て"),
    FIRE("ほのお"),
    WATER("みず"),
    ELECTRIC("でんき");

    @NonNull
    private final String type;

    PokemonType(@NonNull final String type) {
        this.type = type;
    }

    @NonNull
    public String getType() {
        return type;
    }
}
