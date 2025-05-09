package com.template.androidbasicapp.exception;

import androidx.annotation.NonNull;

/**
 * ベースとなるアプリケーション固有のException
 */
public class AppException extends Exception {
    private final AppExceptionType type;

    public AppException(@NonNull final String message, @NonNull final AppExceptionType type) {
        super(message);
        this.type = type;
    }

    public AppExceptionType getType() {
        return type;
    }
}
