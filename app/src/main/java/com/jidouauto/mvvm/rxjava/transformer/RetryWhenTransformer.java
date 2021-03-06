package com.jidouauto.mvvm.rxjava.transformer;

import android.util.Log;
import com.jidouauto.mvvm.rxjava.RetryOnError;
import io.reactivex.*;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

class RetryWhenTransformer<T> implements ObservableTransformer<T, T>,
        FlowableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T> {

    private RetryOnError mRetryOnError;
    private long mDelayMillisecond;
    private int mRetryCount;
    private Single<?> mRetryAfter;
    int currentRetry;

    public RetryWhenTransformer(RetryOnError retryOnError, int retryCount, long delayMillisecond, Single<?> retryAfter) {
        Log.d("RetryWhenTransformer", "RetryWhenTransformer: " + retryAfter);
        mRetryOnError = retryOnError;
        mRetryCount = retryCount;
        mDelayMillisecond = delayMillisecond;
        mRetryAfter = retryAfter;
    }

    public Function<Flowable<Throwable>, Publisher<?>> retryFlowableFunction() {
        return new Function<Flowable<Throwable>, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Flowable<Throwable> throwableFlowable) throws Exception {
                return throwableFlowable.flatMap(throwable -> {
                    if (mRetryOnError.isRetry(throwable)) {
                        //重试，最多重试retryCount次
                        if (currentRetry < mRetryCount) {
                            currentRetry++;
                            //尝试自动登陆
                            if (mRetryAfter == null) {
                                return Flowable.timer(mDelayMillisecond, TimeUnit.MILLISECONDS);
                            } else {
                                return Flowable.timer(mDelayMillisecond, TimeUnit.MILLISECONDS).flatMap(i -> mRetryAfter.toFlowable());
                            }
                        }
                    }
                    return Flowable.error(throwable);
                });
            }
        };
    }

    public Function<Observable<Throwable>, ObservableSource<?>> retryObservableFuncation() {
        return new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(throwable -> {
                    if (mRetryOnError.isRetry(throwable)) {
                        //重试，最多重试retryCount次
                        if (currentRetry < mRetryCount) {
                            currentRetry++;
                            //尝试自动登陆
                            if (mRetryAfter == null) {
                                return Observable.timer(mDelayMillisecond, TimeUnit.MILLISECONDS);
                            } else {
                                return Observable.timer(mDelayMillisecond, TimeUnit.MILLISECONDS).flatMap(i -> mRetryAfter.toObservable());
                            }
                        }
                    }
                    return Observable.error(throwable);
                });
            }
        };
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .retryWhen(retryFlowableFunction());
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream
                .retryWhen(retryFlowableFunction());
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.retryWhen(retryObservableFuncation());
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .retryWhen(retryFlowableFunction());
    }
}
