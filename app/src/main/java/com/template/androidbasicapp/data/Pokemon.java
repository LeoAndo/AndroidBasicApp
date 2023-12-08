package com.template.androidbasicapp.data;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class Pokemon {
    @NonNull
    private final String name;
    @NonNull
    private final PokemonType type;

    public Pokemon(@NonNull String name, @NonNull PokemonType type) {
        this.name = name;
        this.type = type;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public PokemonType getType() {
        return type;
    }
}
