package com.widget.family.API;


import com.commonutils.kotyox.net.exception.ServerThrowableException;
import com.widget.family.model.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wei.
 * Date: 2019-04-28 21:28
 * Description:
 */
public class XTransformer<T> implements ObservableTransformer<BaseResponse<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseResponse<T>, T>() {
                    @Override
                    public T apply(BaseResponse<T> response) throws Exception {
                        if (response.getErrorCode() != 0) {
                            throw new ServerThrowableException(response.getErrorCode(), response.getErrorMsg());
                        }else{
                            return response.getData();
                        }
                    }
                });
    }
}
