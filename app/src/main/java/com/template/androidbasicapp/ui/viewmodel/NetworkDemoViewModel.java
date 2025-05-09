package com.template.androidbasicapp.ui.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.template.androidbasicapp.data.ApiClient;
import com.template.androidbasicapp.data.GithubApiService;
import com.template.androidbasicapp.exception.AppException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkDemoViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<NetworkDemoUiState> uiState = new MutableLiveData<>();
    @NonNull
    private final GithubApiService githubApiService;
    @NonNull
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public NetworkDemoViewModel() {
        githubApiService = ApiClient.getGithubApiService();
    }

    @NonNull
    public LiveData<NetworkDemoUiState> getUiState() {
        return uiState;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
    }

    public void searchRepositories() {
        uiState.postValue(new NetworkDemoUiState.Loading());
        executorService.execute(() -> {
            try {
                var call = githubApiService.searchRepositories("android", "stars");
                var response = ApiClient.dataOrThrow(call);
                // 画面に表示したいデータを作成する
                var data = response.toString();
                uiState.postValue(new NetworkDemoUiState.Success(data));
            } catch (AppException e) {
                Log.e("MainViewModel", "error: ", e);
                uiState.postValue(new NetworkDemoUiState.Failure(e));
            }
        });
    }
}