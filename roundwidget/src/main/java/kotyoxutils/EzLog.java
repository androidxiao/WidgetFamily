package kotyoxutils;

import android.util.Log;

import family.widget.com.roundwidget.BuildConfig;

public class EzLog {

    public static final String TAG = "ez";

    public static void d(String s) {
        if(BuildConfig.DEBUG) {
            Log.d(TAG, "d: --->" + s);
        }
    }
}
