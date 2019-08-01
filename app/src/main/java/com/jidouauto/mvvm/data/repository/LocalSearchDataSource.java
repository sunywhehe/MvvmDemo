package com.jidouauto.mvvm.data.repository;

import com.elvishew.xlog.XLog;
import com.jidouauto.mvvm.data.db.SearchDao;
import com.jidouauto.mvvm.data.db.BaseSearchDatabase;
import com.jidouauto.mvvm.data.entity.SearchKey;
import com.jidouauto.mvvm.data.entity.SearchKeyUtils;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.List;

/**
 * @author leosun
 * Created by Leosun on 2019/1/8 14:53
 */
public class LocalSearchDataSource {

    private static final String TAG = "LocalSearchDataSource:";

    private static LocalSearchDataSource instance = null;
    private SearchDao searchDao;

    public static LocalSearchDataSource getInstance() {
        if (instance == null) {
            synchronized (LocalSearchDataSource.class) {
                if (instance == null) {
                    instance = new LocalSearchDataSource();
                }
            }
        }
        return instance;
    }

    private LocalSearchDataSource() {
        searchDao = BaseSearchDatabase.getInstance().searchDao();
    }


    /**
     * Adds a SearchKey to a given photo
     */
    public Observable<SearchKey> add(long photoId, String searchKeyText) {
        XLog.d(TAG + "creating SearchKey for photo id %s, SearchKey text %s", photoId, searchKeyText);
        SearchKey searchKey = new SearchKey(photoId, searchKeyText);

        return Observable.fromCallable(() -> {
            long rowId = searchDao.add(searchKey);
            XLog.d(TAG + "SearchKey stored " + rowId);
            return SearchKeyUtils.clone(searchKey, rowId);
        });
    }

    /**
     * Updates a SearchKey
     */
    public Completable update(SearchKey searchKey) {
        XLog.d(TAG + "updating SearchKey sync status for SearchKey id %s", searchKey.getId());

        return Completable.fromAction(() -> searchDao.update(searchKey));
    }

    /**
     * Deletes a SearchKey
     */
    public Completable delete(SearchKey searchKey) {
        XLog.d(TAG + "deleting SearchKey with id %s", searchKey.getId());

        return Completable.fromAction(() -> searchDao.delete(searchKey));
    }

    /**
     * Returns Flowable stream of SearchKeys for a given photo
     */
    public Flowable<List<SearchKey>> getSearchKeys(long photoId) {
        XLog.d(TAG + "getting SearchKeys for photo id %s", photoId);
        return searchDao.getComments(photoId);
    }

    public Completable updateSysPending(long photoId, boolean sysPending) {
        XLog.d(TAG + "updateSysPending SearchKey sync status for photoId id %s", photoId);

        return Completable.fromAction(() -> searchDao.updateSysPending(photoId, sysPending));
    }
}
