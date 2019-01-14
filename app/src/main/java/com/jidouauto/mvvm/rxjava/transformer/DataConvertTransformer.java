package com.jidouauto.mvvm.rxjava.transformer;

import com.jidouauto.mvvm.rxjava.DataConverter;
import io.reactivex.*;
import org.reactivestreams.Publisher;

class DataConvertTransformer<T extends DataConverter<R>, R> implements ObservableTransformer<T, R>,
        FlowableTransformer<T, R>,
        SingleTransformer<T, R>,
        MaybeTransformer<T, R> {

    @Override
    public Publisher<R> apply(Flowable<T> upstream) {
        return upstream
                .map(resp -> resp.convert());
    }

    @Override
    public MaybeSource<R> apply(Maybe<T> upstream) {
        return upstream
                .map(resp -> resp.convert());
    }

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream
                .map(resp -> resp.convert());
    }

    @Override
    public SingleSource<R> apply(Single<T> upstream) {
        return upstream
                .map(resp -> resp.convert());
    }
}
