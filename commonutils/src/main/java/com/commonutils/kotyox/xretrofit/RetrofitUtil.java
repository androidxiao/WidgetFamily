package com.commonutils.kotyox.xretrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import com.commonutils.kotyox.net.Logger;
import com.commonutils.kotyox.net.uploaddown.ProgressListener;
import com.commonutils.kotyox.net.uploaddown.ProgressResponseBody;
import com.commonutils.kotyox.utils.GsonUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wei.
 * Date: 2019-04-18 22:00
 * Description:
 */
public class RetrofitUtil {

    private static int S_REQ_TIMEOUT = 30;
    private static Context sContext = null;

    public RetrofitUtil() {
    }

    public static void init(Context context) {
        init(context, 30);
    }

    public static void init(Context context, int REQ_TIMEOUT) {
        sContext = context;
        S_REQ_TIMEOUT = REQ_TIMEOUT;
    }

    public static Retrofit getRetrofit(String baseUrl) {
        Retrofit.Builder restrofitBuilder = buildRetrofitBuilder(baseUrl);
        OkHttpClient client = buildOkHttpClient();
        restrofitBuilder.client(client);
        return restrofitBuilder.build();
    }

    @NonNull
    private static Retrofit.Builder buildRetrofitBuilder(String baseUrl) {
        Gson gson = GsonUtils.getGson();
        return (new Retrofit.Builder()).baseUrl(baseUrl).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson));
    }

    @NonNull
    public static OkHttpClient buildOkHttpClient() {
        okhttp3.OkHttpClient.Builder builder = buildOkHttpClientBuilder();
        builder.connectTimeout((long)S_REQ_TIMEOUT, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        return client;
    }

    @NonNull
    private static okhttp3.OkHttpClient.Builder buildOkHttpClientBuilder() {
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new Logger());
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);
        builder.addInterceptor(new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder().header("Accept", "application/json").build();
                Response response = chain.proceed(request);
                return response;
            }
        });
        File cacheFile = new File(sContext.getCacheDir(), sContext.getPackageName());
        Cache cache = new Cache(cacheFile, 104857600L);
        builder.cache(cache);
        return builder;
    }

    public static Retrofit getUploadRetrofit(String baseUrl) {
        Retrofit.Builder restrofitBuilder = buildRetrofitBuilder(baseUrl);
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        OkHttpClient client = builder.build();
        restrofitBuilder.client(client);
        return restrofitBuilder.build();
    }

    public static Retrofit getDownloadRetrofit(String baseUrl, final ProgressListener progressListener) {
        Retrofit.Builder restrofitBuilder = buildRetrofitBuilder(baseUrl);
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), progressListener)).build();
            }
        });
        OkHttpClient client = builder.build();
        restrofitBuilder.client(client);
        return restrofitBuilder.build();
    }
}
