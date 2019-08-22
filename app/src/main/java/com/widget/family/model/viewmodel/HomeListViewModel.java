package com.widget.family.model.viewmodel;

import android.arch.lifecycle.LiveData;

import com.commonutils.kotyox.net.data.Resource;
import com.commonutils.kotyox.net.viewmodel.BaseViewModelT;
import com.widget.family.API.net.IRepository;
import com.widget.family.API.net.RepositoryFactory;
import com.widget.family.model.HomeListModel;

/**
 * Created by wei.
 * Date: 2019-04-25 23:55
 * Description:
 */
public class HomeListViewModel extends BaseViewModelT<HomeListModel> {

    IRepository mRepository = RepositoryFactory.createRepository();

    @Override
    public LiveData<Resource<HomeListModel>> getModels() {
        return mRepository.homeList(100000);
    }
}
