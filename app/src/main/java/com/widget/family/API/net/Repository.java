package com.widget.family.API.net;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.commonutils.kotyox.net.NetWorkResource;
import com.commonutils.kotyox.net.data.Resource;
import com.widget.family.API.XTransformer;
import com.widget.family.model.HomeListModel;

import io.reactivex.Observable;

/**
 * Created by wei.
 * Date: 2019-04-25 23:28
 * Description:
 */
public class Repository implements IRepository {

    private static class RepositoryHolder{
        public static final Repository REPOSITORY = new Repository();
    }

    public static Repository getInstance() {
        return RepositoryHolder.REPOSITORY;
    }

    @Override
    public LiveData<Resource<HomeListModel>> homeList(final int pageCount) {
        return new NetWorkResource<HomeListModel>(){

            @NonNull
            @Override
            protected Observable<HomeListModel> createCall() {
                XTransformer<HomeListModel> transformer = new XTransformer<>();
                return FetchData.getService().homeList(pageCount).compose(transformer);
            }
        }.getAsLiveData();
    }
}
