package com.jidouauto.mvvm.rxjava;

import io.reactivex.Observable;
import com.jidouauto.mvvm.rxjava.transformer.LifecycleTransformer;

/**
 * @author eddie
 * <p>
 * 实现该接口需要提供一个观察生命周期的Observable
 * @see {@link LifecycleTransformer}
 */
public interface LifecycleSource<T> {

    /**
     * @return 一个观察生命周期的Observable
     */
    Observable<T> getLifecycleObservable();
}
