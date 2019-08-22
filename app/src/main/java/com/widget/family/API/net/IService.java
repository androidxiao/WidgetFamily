package com.widget.family.API.net;

import com.widget.family.model.BaseResponse;
import com.widget.family.model.HomeListModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wei.
 * Date: 2019-04-25 23:17
 * Description:
 */
public interface IService {

    @GET("article/list/{pageCount}/json1")
    Observable<BaseResponse<HomeListModel>> homeList(@Path("pageCount") int pageCount);
}
