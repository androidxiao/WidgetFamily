package com.commonutils.kotyox.net.viewmodel;

import android.arch.lifecycle.LiveData;

import com.commonutils.kotyox.net.data.Resource;

/**
 * Created by wei.
 * Date: 2019-04-25 23:46
 * Description:
 */
public abstract class BaseViewModelT<T> extends BaseViewModel{

    public BaseViewModelT() {
    }

    public abstract LiveData<Resource<T>> getModels();
}
