package com.template.androidbasicapp.ui.viewmodel;

import androidx.annotation.NonNull;

import com.template.androidbasicapp.exception.AppException;

/**
 * UIの状態を表すインターフェース
 */
public sealed interface NetworkDemoUiState permits NetworkDemoUiState.Success, NetworkDemoUiState.Loading, NetworkDemoUiState.Failure {
    /**
     * 通信成功時の状態
     *
     * @param data 表示するデータ
     */
    record Success(@NonNull String data) implements NetworkDemoUiState {
    }

    /**
     * ローディング中の状態
     */
    record Loading() implements NetworkDemoUiState {
    }

    /**
     * 通信失敗時の状態
     */
    record Failure(@NonNull AppException exception) implements NetworkDemoUiState {
    }
}