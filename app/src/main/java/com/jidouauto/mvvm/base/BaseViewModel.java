package com.jidouauto.mvvm.base;

import android.arch.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author leosun
 * Created by Leosun on 2019/1/8 18:27
 */
public class BaseViewModel extends ViewModel {

    private CompositeDisposable disposables;

    @Override
    protected void onCleared() {
        if (null != disposables) {
            disposables.clear();
        }
        super.onCleared();
    }

    public CompositeDisposable getDisposables() {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        return disposables;
    }
}
