package com.jidouauto.mvvm.http.exception;

/**
 * @author leosun
 */
public class MsgException extends CodeException {
    public MsgException(int code) {
        super(code);
    }

    public MsgException(int code, String message) {
        super(code, message);
    }

    public MsgException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public MsgException(int code, Throwable cause) {
        super(code, cause);
    }
}
