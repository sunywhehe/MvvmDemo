package com.jidouauto.mvvm.rxjava.transformer;

import com.jidouauto.lib.rxhelper.DataConverter;
import com.jidouauto.lib.rxhelper.NullableData;
import io.reactivex.*;
import org.reactivestreams.Publisher;

class NullableDataConvertTransformer<T extends DataConverter<NullableData<R>>, R> implements ObservableTransformer<T, R>,
        FlowableTransformer<T, R>,
        SingleTransformer<T, R>,
        MaybeTransformer<T, R> {

    private R defaultValue;

    public NullableDataConvertTransformer(R defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Publisher<R> apply(Flowable<T> upstream) {
        return upstream
                .map(resp -> {
                    NullableData<R> data = resp.convert();
                    if (data.isNull()) {
                        return defaultValue;
                    }
                    return data.get();
                });
    }

    @Override
    public MaybeSource<R> apply(Maybe<T> upstream) {
        return upstream
                .map(resp -> {
                    NullableData<R> data = resp.convert();
                    if (data.isNull()) {
                        return defaultValue;
                    }
                    return data.get();
                });
    }

    @Override
    public ObservableSource<R> apply(Observable<T> upstream) {
        return upstream
                .map(resp -> {
                    NullableData<R> data = resp.convert();
                    if (data.isNull()) {
                        return defaultValue;
                    }
                    return data.get();
                });
    }

    @Override
    public SingleSource<R> apply(Single<T> upstream) {
        return upstream
                .map(resp -> {
                    NullableData<R> data = resp.convert();
                    if (data.isNull()) {
                        return defaultValue;
                    }
                    return data.get();
                });
    }
}
