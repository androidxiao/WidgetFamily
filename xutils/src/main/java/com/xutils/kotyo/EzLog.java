package com.xutils.kotyo;

import android.util.Log;

public class EzLog {

    public static final String TAG = "ez";

    public static  void d(String s){
        Log.d(TAG, "d: --->"+s);
    }
}
