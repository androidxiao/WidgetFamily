package com.widget.family;

import android.app.Application;

import com.commonutils.kotyox.utils.GsonUtils;
import com.commonutils.kotyox.xretrofit.RetrofitUtil;

/**
 * Created by wei.
 * Date: 2019-04-25 23:04
 * Description:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initGson();
        RetrofitUtil.init(this);
    }

    private void initGson() {
        GsonUtils.initLongDate();
    }


}
