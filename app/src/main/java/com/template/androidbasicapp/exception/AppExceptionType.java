package com.template.androidbasicapp.exception;

/**
 * <a href="https://developer.mozilla.org/ja/docs/Web/HTTP/Reference/Status">...</a>
 */
public enum AppExceptionType {
    Forbidden(), // 403
    Network(), // 通信エラー
    Unauthorized(), // 401
    Unknown();  // 不明なエラー
}