package com.template.androidbasicapp.data;

import androidx.annotation.NonNull;

import com.template.androidbasicapp.BuildConfig;
import com.template.androidbasicapp.exception.AppException;
import com.template.androidbasicapp.exception.AppExceptionType;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitやOkHttpClientのインスタンスは基本的にアプリケーション全体で1つだけ（シングルトン）にするのが推奨される。
 * 理由は以下の通り。
 * Retrofitの生成コストが高いため、複数生成は非効率。
 * RetrofitインスタンスをAPIごとに1つずつシングルトンで持つ設計が一般的。
 */
public final class ApiClient {
    private static volatile GithubApiService githubApiService;
    private static volatile OkHttpClient okHttpClient;

    private ApiClient() {
        throw new UnsupportedOperationException("ApiClient is a utility class and cannot be instantiated");
    }

    /**
     * 通信ログをLogcatで確認できるようにInterceptorを追加したOkHttpClientを取得する。
     * Logcatから「package:mine tag:okhttp.OkHttpClient」でフィリタリングすると確認しやすい。
     * <p>
     * スレッドセーフなシングルトンの実装はRoomライブラリなどの内部実装でも採用しているダブルチェックロッキングのパターンを採用した
     *
     * @return OkHttpClientのインスタンス
     */
    private static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (ApiClient.class) {
                if (okHttpClient == null) {
                    var builder = new OkHttpClient.Builder();
                    if (BuildConfig.DEBUG) { // デバッグビルドの場合は、通信ログを出力する
                        var loggingInterceptor = new HttpLoggingInterceptor();
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(loggingInterceptor);
                    }
                    okHttpClient = builder.addInterceptor(new Interceptor() {
                                @NonNull
                                @Override
                                public Response intercept(@NonNull Chain chain) throws IOException {
                                    var url = chain.request().url(); // リクエストURLを取得
                                    var host = url.host(); // ホスト名を取得

                                    if (host.equals("api.github.com")) { // github.comのAPIの場合
                                        // APIで必要なヘッダ情報は、ここで追加することも可能.
                                        return chain.proceed(chain.request().newBuilder()
                                                .addHeader("Accept", "application/vnd.github+json")
                                                .addHeader("X-GitHub-Api-Version", "2022-11-28")
                                                //.addHeader("Authorization", "Bearer TOKEN")
                                                .build());
                                    } else {
                                        // それ以外のAPIは特にヘッダを追加する必要はない
                                        return chain.proceed(chain.request());
                                    }
                                }
                            })
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public static GithubApiService getGithubApiService() {
        if (githubApiService == null) {
            synchronized (ApiClient.class) {
                if (githubApiService == null) {
                    var retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.github.com/")
                            .client(getOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    githubApiService = retrofit.create(GithubApiService.class);
                }
            }
        }
        return githubApiService;
    }

    /**
     * RetrofitのCallを実行し、レスポンスボディを取得する.<br>
     * Web API固有の処理が入る場合は、共通利用せずに、各APIでdataOrThrowメソッドを用意するのも良いかも<br>
     *
     * @param call RetrofitのCallオブジェクト
     * @param <T>  レスポンスボディの型
     * @return レスポンスボディ
     * @throws AppException 通信エラーやレスポンスエラーが発生した場合にスローされる
     */
    public static <T> T dataOrThrow(@NonNull final Call<T> call) throws AppException {
        try {
            var response = call.execute(); // 通信を実行
            var body = response.body(); // レスポンスボディを取得
            var code = response.code(); // レスポンスコードを取得
            if (response.isSuccessful() && body != null) {
                return body; // 成功時はレスポンスボディを返す
            } else { // 失敗時はレスポンスコードに応じて例外をスロー
                if (code == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    throw new AppException("Unauthorized access", AppExceptionType.Unauthorized);
                } else if (code == HttpURLConnection.HTTP_FORBIDDEN) {
                    throw new AppException("Forbidden access", AppExceptionType.Forbidden);
                } else {// 想定できていないエラーは一旦Unknownでまとめる
                    throw new AppException("error HTTP code: " + code, AppExceptionType.Unknown);
                }
            }
        } catch (IOException e) { // 通信処理に失敗した場合はIOExceptionをスロー
            var message = e.getMessage() != null ? e.getMessage() : "error message is null";
            if (e instanceof SocketTimeoutException || e instanceof SocketException) {
                throw new AppException(message, AppExceptionType.Network);
            } else {
                // 想定できていないエラーは一旦Unknownでまとめる
                throw new AppException(message, AppExceptionType.Unknown);
            }
        }
    }
}