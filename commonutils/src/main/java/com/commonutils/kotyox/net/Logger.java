package com.commonutils.kotyox.net;


import static com.orhanobut.logger.Logger.i;

/**
 * Created by chawei on 2018/8/23.
 */

public class Logger implements okhttp3.logging.HttpLoggingInterceptor.Logger {
    public Logger() {
    }

    public void log(String message) {
        i(message, new Object[0]);
    }
}
