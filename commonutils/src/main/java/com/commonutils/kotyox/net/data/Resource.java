package com.commonutils.kotyox.net.data;

import android.support.annotation.NonNull;

/**
 * Created by wei.
 * Date: 2019/4/16 下午9:58
 * Description:
 */
public class Resource<T> {

    @NonNull
    public final Status status;
    @NonNull
    public final T data;
    @NonNull
    public String errorMessage;
    @NonNull
    public Throwable mThrowable;

    public Resource(@NonNull Status status, @NonNull T data, @NonNull String errorMessage) {
        this.status = status;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Resource(@NonNull Status status, @NonNull T data, @NonNull Throwable throwable) {
        this.status = status;
        this.data = data;
        this.mThrowable = throwable;
    }

    public static <T> Resource success(@NonNull T data) {
        return new Resource(Status.SUCCESS, data, (String) null);
    }

    public static <T> Resource<T> error(String msg, @NonNull T data) {
        return new Resource(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> error(Throwable throwable, @NonNull T data) {
        return new Resource(Status.ERROR, data, throwable);
    }

    public static <T> Resource<T> loading(@NonNull T data) {
        return new Resource(Status.LOADING, data, (String) null);
    }
}
