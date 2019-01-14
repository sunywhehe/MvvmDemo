package com.jidouauto.mvvm.data.repository;

import com.allen.library.RxHttpUtils;
import com.jidouauto.mvvm.api.SearchApi;
import com.jidouauto.mvvm.data.entity.SearchKey;
import io.reactivex.Observable;

/**
 * @author leosun
 * Created by Leosun on 2019/1/8 14:54
 */
public class RemoteSearchDataSource {

    private static final String TAG = RemoteSearchDataSource.class.getName();

    private static RemoteSearchDataSource instance = null;

    public static RemoteSearchDataSource getInstance() {
        if (instance == null) {
            synchronized (RemoteSearchDataSource.class) {
                if (instance == null) {
                    instance = new RemoteSearchDataSource();
                }
            }
        }
        return instance;
    }

    private RemoteSearchDataSource() {

    }

    public Observable<SearchKey> add(SearchKey searchKey) {
        return RxHttpUtils.createApi(SearchApi.class).addComment(searchKey);
    }
}
