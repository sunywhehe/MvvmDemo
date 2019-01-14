package com.jidouauto.mvvm.data.repository;

import com.jidouauto.mvvm.data.entity.SearchKey;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.List;

/**
 * @author leosun
 * Created by Leosun on 2019/1/8 14:45
 */
public class SearchRepository {

    private static final String TAG = SearchRepository.class.getName();

    private static SearchRepository instance = null;

    public static SearchRepository getInstance() {
        if (instance == null) {
            synchronized (SearchRepository.class) {
                if (instance == null) {
                    instance = new SearchRepository();
                }
            }
        }
        return instance;
    }

    private SearchRepository() {

    }

    public Observable<SearchKey> addComment(String searchText) {
        return LocalSearchDataSource.getInstance().add(1, searchText)
                .flatMap(searchKey -> {
                    return RemoteSearchDataSource.getInstance().add(searchKey);
                });
    }

    public Flowable<List<SearchKey>> getSearchKeys(long photoId) {
        return LocalSearchDataSource.getInstance().getSearchKeys(photoId);
    }

    /**
     * Updates a comment
     */
    public Completable update(SearchKey searchKey) {
        return LocalSearchDataSource.getInstance().update(searchKey);
    }

    public Completable updateSysPending(long photoId, boolean sysPending) {
        return LocalSearchDataSource.getInstance().updateSysPending(photoId, sysPending);
    }
}
