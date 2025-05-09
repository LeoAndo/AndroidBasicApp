package com.template.androidbasicapp.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record GithubSearchResponse(@SerializedName("total_count") int totalCount,
                                   @SerializedName("incomplete_results") boolean incompleteResults,
                                   @NonNull List<Item> items) {
    record Item(long id, @NonNull String name, Owner owner, @NonNull String html_url,
                int stargazers_count, int forks_count) {
        record Owner(@NonNull String login) {
        }
    }
}