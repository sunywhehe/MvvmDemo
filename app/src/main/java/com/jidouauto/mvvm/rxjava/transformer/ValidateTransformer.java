package com.jidouauto.mvvm.rxjava.transformer;


import com.jidouauto.mvvm.rxjava.Validator;
import io.reactivex.*;
import org.reactivestreams.Publisher;

class ValidateTransformer<T extends Validator<? extends Exception>> implements ObservableTransformer<T, T>,
        FlowableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T> {

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .map(resp -> {
                    resp.validate();
                    return resp;
                });
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream
                .map(resp -> {
                    resp.validate();
                    return resp;
                });
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .map(resp -> {
                    resp.validate();
                    return resp;
                });
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .map(resp -> {
                    resp.validate();
                    return resp;
                });
    }
}
