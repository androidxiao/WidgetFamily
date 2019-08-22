package com.commonutils.kotyox.net.exception;

/**
 * Created by wei.
 * Date: 2019-04-28 21:37
 * Description:
 */
public class ServerThrowableException extends ApiException {

    public ServerThrowableException(int errorCode) {
        super(errorCode);
    }

    public ServerThrowableException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public ServerThrowableException(Throwable throwable, int errorCode, String errorMsg) {
        super(throwable, errorCode, errorMsg);
    }

    public ServerThrowableException(Throwable throwable, int errorCode) {
        super(throwable, errorCode);
    }
}
