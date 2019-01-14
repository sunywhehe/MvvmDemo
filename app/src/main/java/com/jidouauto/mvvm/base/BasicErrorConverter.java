package com.jidouauto.mvvm.base;

import com.google.gson.JsonParseException;
import com.jidouauto.mvvm.http.exception.BaseException;
import com.jidouauto.mvvm.http.exception.DataException;
import com.jidouauto.mvvm.http.exception.NetworkException;
import com.jidouauto.mvvm.http.exception.UnknowException;
import com.jidouauto.mvvm.rxjava.ErrorConverter;
import retrofit2.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class BasicErrorConverter implements ErrorConverter<BaseException> {

    public static final ErrorConverter INSTANCE = new BasicErrorConverter();

    /**
     * 将某个错误类型转换成特定的错误类型方便统一处理
     *
     * @param e 错误类型
     * @return base exception
     */
    @Override
    public BaseException convert(Throwable e) {
        if (e instanceof UnknownHostException
                || e instanceof ConnectException
                || e instanceof SocketTimeoutException
                || e instanceof HttpException
                || e instanceof IOException) {
            return new NetworkException(-1, e);
        } else if (e instanceof JsonParseException) {
            return new DataException(-1, e);
        } else if (e instanceof BaseException) {
            return (BaseException) e;
        } else {
            return new UnknowException(UnknowException.UNKNOW_CODE, e);
        }
    }
}
