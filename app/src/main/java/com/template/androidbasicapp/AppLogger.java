package com.template.androidbasicapp;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;

/**
 * ログ出力用のクラス
 */
public final class AppLogger {

    private AppLogger() {
    }

    public static void i(@NonNull final String msg) {
        if (!BuildConfig.DEBUG) return;
        final Pair<String, String> data = createMessage(msg);
        Log.i(data.first, data.second);
    }

    public static void v(@NonNull final String msg) {
        if (!BuildConfig.DEBUG) return;
        final Pair<String, String> data = createMessage(msg);
        Log.v(data.first, data.second);
    }

    public static void d(@NonNull final String msg) {
        if (!BuildConfig.DEBUG) return;
        final Pair<String, String> data = createMessage(msg);
        Log.d(data.first, data.second);
    }

    public static void w(@NonNull final String msg) {
        if (!BuildConfig.DEBUG) return;
        final Pair<String, String> data = createMessage(msg);
        Log.w(data.first, data.second);
    }

    public static void w(@NonNull final String msg, @NonNull final Throwable throwable) {
        if (!BuildConfig.DEBUG) return;
        final Pair<String, String> data = createMessage(msg);
        Log.w(data.first, data.second, throwable);
    }

    public static void e(@NonNull final String msg) {
        if (!BuildConfig.DEBUG) return;
        final Pair<String, String> data = createMessage(msg);
        Log.e(data.first, data.second);
    }

    public static void e(@NonNull final String msg, @NonNull final Throwable throwable) {
        if (!BuildConfig.DEBUG) return;
        final Pair<String, String> data = createMessage(msg);
        Log.e(data.first, data.second, throwable);
    }

    private static Pair<String, String> createMessage(@NonNull final String msg) {
        final StackTraceElement stack = Thread.currentThread().getStackTrace()[4];
        final String fullName = stack.getClassName();
        final String tag = fullName.substring(fullName.lastIndexOf(".") + 1);
        final String message = '<' + stack.getMethodName() + ":" + stack.getLineNumber() + "> " + msg;
        return new Pair<>(tag, message);
    }
}