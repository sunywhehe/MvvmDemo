package com.jidouauto.mvvm.api;

import com.jidouauto.mvvm.data.entity.SearchKey;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author leosun
 * Created by Leosun on 2019/1/8 14:57
 */
public interface SearchApi {
    @POST("/posts")
    Observable<SearchKey> addComment(@Body SearchKey searchKey);
}
