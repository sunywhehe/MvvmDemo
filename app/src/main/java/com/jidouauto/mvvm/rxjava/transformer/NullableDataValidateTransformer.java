package com.jidouauto.mvvm.rxjava.transformer;

import com.jidouauto.lib.rxhelper.NullableData;
import com.jidouauto.lib.rxhelper.Validator;
import io.reactivex.*;
import org.reactivestreams.Publisher;

class NullableDataValidateTransformer<T extends NullableData<? extends Validator>> implements ObservableTransformer<T, T>,
        FlowableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T> {

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .map(resp -> {
                    if (resp.isNotNull()) {
                        resp.get().validate();
                    }
                    return resp;
                });
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream
                .map(resp -> {
                    if (resp.isNotNull()) {
                        resp.get().validate();
                    }
                    return resp;
                });
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .map(resp -> {
                    if (resp.isNotNull()) {
                        resp.get().validate();
                    }
                    return resp;
                });
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .map(resp -> {
                    if (resp.isNotNull()) {
                        resp.get().validate();
                    }
                    return resp;
                });
    }
}
