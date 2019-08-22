package com.widget.family.API.net;

import com.commonutils.kotyox.xretrofit.RetrofitUtil;
import com.widget.family.API.API;

import retrofit2.Retrofit;

/**
 * Created by wei.
 * Date: 2019-04-25 23:33
 * Description:
 */
public class FetchData {

    public static IService getService() {
        Retrofit retrofit = RetrofitUtil.getRetrofit(API.Base_Url);
        IService service = retrofit.create(IService.class);
        return service;
    }
}
