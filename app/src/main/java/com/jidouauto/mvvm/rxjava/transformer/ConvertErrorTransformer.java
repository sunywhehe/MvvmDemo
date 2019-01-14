package com.jidouauto.mvvm.rxjava.transformer;

import com.jidouauto.mvvm.rxjava.ErrorConverter;
import io.reactivex.*;
import org.reactivestreams.Publisher;

class ConvertErrorTransformer<T> implements ObservableTransformer<T, T>,
        FlowableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T> {

    private ErrorConverter<? extends Exception> errorConverter;

    public ConvertErrorTransformer(ErrorConverter<? extends Exception> errorConverter) {
        this.errorConverter = errorConverter;
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .onErrorResumeNext(throwable -> {
                    return Flowable.error(errorConverter.convert(throwable));
                });
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream
                .onErrorResumeNext(throwable -> {
                    return Maybe.error(errorConverter.convert(throwable));
                });
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .onErrorResumeNext(throwable -> {
                    return Observable.error(errorConverter.convert(throwable));
                });
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .onErrorResumeNext(throwable -> Single.error(errorConverter.convert(throwable)));
    }
}
