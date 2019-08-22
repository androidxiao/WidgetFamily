package com.widget.family.API.net;

import android.arch.lifecycle.LiveData;

import com.commonutils.kotyox.net.data.Resource;
import com.widget.family.model.HomeListModel;

/**
 * Created by wei.
 * Date: 2019-04-25 23:27
 * Description:
 */
public interface IRepository {

    LiveData<Resource<HomeListModel>> homeList(int pageCount);
}
