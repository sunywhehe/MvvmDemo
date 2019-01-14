package com.jidouauto.mvvm.data.entity;


import com.jidouauto.mvvm.http.exception.BaseException;
import com.jidouauto.mvvm.http.exception.IdentityException;
import com.jidouauto.mvvm.rxjava.Validator;

public class CodeResp implements Validator<BaseException> {
    public static final int SUCCEED = 200;
    public static final int TOKEN_EXPIRE = 999;

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public void validate() throws BaseException {
        if (getCode() == TOKEN_EXPIRE) {
            throw new IdentityException(getCode(), "token expire!");
        }
    }

    @Override
    public String toString() {
        return "CodeResp{" +
                "code=" + code +
                '}';
    }
}
