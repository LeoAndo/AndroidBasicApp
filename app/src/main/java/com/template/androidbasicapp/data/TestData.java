package com.template.androidbasicapp.data;

import java.util.ArrayList;
import java.util.List;

public final class TestData {
    private TestData() {
    }

    public static List<Pokemon> createPokemonList() {
        final ArrayList<Pokemon> pokemonItems = new ArrayList<>();
        pokemonItems.add(new Pokemon("ヒトカゲ", PokemonType.FIRE));
        pokemonItems.add(new Pokemon("リザード", PokemonType.FIRE));
        pokemonItems.add(new Pokemon("ゼニガメ", PokemonType.WATER));
        pokemonItems.add(new Pokemon("カメール", PokemonType.WATER));
        pokemonItems.add(new Pokemon("ピカチュウ", PokemonType.ELECTRIC));
        pokemonItems.add(new Pokemon("ライチュウ", PokemonType.ELECTRIC));
        return pokemonItems;
    }
}
