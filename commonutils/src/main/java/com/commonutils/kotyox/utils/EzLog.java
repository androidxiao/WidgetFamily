package com.commonutils.kotyox.utils;

import android.util.Log;

import com.commonutils.kotyox.BuildConfig;

public class EzLog {

    public static final String TAG = "ez";

    public static void d(String s) {
        if(BuildConfig.DEBUG) {
            Log.d(TAG, "d: --->" + s);
        }
    }
}
