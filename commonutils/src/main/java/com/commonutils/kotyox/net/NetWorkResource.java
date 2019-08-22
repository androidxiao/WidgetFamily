package com.commonutils.kotyox.net;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.commonutils.kotyox.net.data.Resource;
import com.commonutils.kotyox.utils.EzLog;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wei.
 * Date: 2019/4/16 下午9:57
 * Description:
 */
public abstract class NetWorkResource<ResultType> {


    private final MediatorLiveData<Resource<ResultType>> mMediatorLiveData = new MediatorLiveData<>();

    @MainThread
    public NetWorkResource(){
        mMediatorLiveData.setValue((Resource<ResultType>) Resource.loading(null));
        fetchData();
    }

    /**
     * 拉取数据
     */
    @SuppressLint("CheckResult")
    private void fetchData(){
        createCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultType>() {
                    @Override
                    public void accept(ResultType response) throws Exception {
                        mMediatorLiveData.setValue(Resource.success(response));
                        saveResponseResult(response);
                    }
                },new Consumer<Throwable>(){

                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onFetchFailed(throwable);
//                        mMediatorLiveData.setValue((Resource<ResultType>) Resource.error(throwable.getMessage(), null));

                        mMediatorLiveData.setValue((Resource<ResultType>) Resource.error(throwable,null));
                    }
                });
    }

    @NonNull
    @MainThread
    protected abstract Observable<ResultType> createCall();

    public final LiveData<Resource<ResultType>> getAsLiveData(){
        return mMediatorLiveData;
    }

    @MainThread
    private void saveResponseResult(final ResultType response) {
        Observable.just(0)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        saveCallResult(response);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        EzLog.d("saveResponseResult--->"+throwable.getMessage());
                    }
                });
    }

    /**
     * 保存 response 到数据库或文件，作为缓存
     * @param response
     */
    @WorkerThread
    protected void saveCallResult(ResultType response){

    }

    /**
     * 接口请求异常
     * @param throwable
     */
    @MainThread
    protected void onFetchFailed(Throwable throwable){

    }
}
