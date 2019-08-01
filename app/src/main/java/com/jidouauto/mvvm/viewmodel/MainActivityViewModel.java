package com.jidouauto.mvvm.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.jidouauto.mvvm.base.BaseViewModel;
import com.jidouauto.mvvm.data.entity.SearchKey;
import com.jidouauto.mvvm.data.repository.SearchRepository;
import com.jidouauto.mvvm.rxjava.transformer.Transformers;
import java.util.List;

/**
 * @author leosun
 * Created by Leosun on 2019/1/7 22:43
 */
public class MainActivityViewModel extends BaseViewModel {

    private static final String TAG = "MainActivityViewModel:";

    private MutableLiveData<List<SearchKey>> mSearchKeyList;

    public MainActivityViewModel() {
        mSearchKeyList = new MutableLiveData<>();
        loadSearchKeyList();
    }

    public MutableLiveData<List<SearchKey>> getSearchKeyList() {
        return mSearchKeyList;
    }

    public void addSearchKey(String searchText) {
        getDisposables().add(SearchRepository.getInstance().addComment(searchText)
                .compose(Transformers.applyIoUi())
                .subscribe(searchKey -> {
                            XLog.d(TAG + "add comment success");
                            updateSearchKey(searchKey);
                        },
                        t -> XLog.e(TAG + "add comment error:" + t.getMessage())));
    }

    private void updateSearchKey(SearchKey searchKey) {
        getDisposables().add(SearchRepository.getInstance().updateSysPending(1, false)
                .compose(Transformers.applyIoUi())
                .subscribe(() -> XLog.d(TAG + "update comment success"),
                        t -> XLog.e(TAG + "update comment error")));
    }

    private void loadSearchKeyList() {
        getDisposables().add(SearchRepository.getInstance().getSearchKeys(1)
                .compose(Transformers.applyIoUi())
                .subscribe(mSearchKeyList::setValue,
                        t -> XLog.e(TAG + "get comments error")));
    }
}
