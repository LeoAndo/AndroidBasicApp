package com.template.androidbasicapp.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Web API単位でインターフェースを定義する
 */
public interface GithubApiService {
    @GET("search/repositories")
    Call<GithubSearchResponse> searchRepositories(@Query("q") String q, @Query("sort") String sort);
}
