package com.commonutils.kotyox.net.exception;

/**
 * Created by wei.
 * Date: 2019-04-28 21:33
 * Description:
 */
public class ApiException extends RuntimeException {

    private int errorCode;
    private String errorMsg;

    public ApiException(int errorCode) {
        this.errorCode = errorCode;
    }

    public ApiException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ApiException(Throwable throwable, int errorCode, String errorMsg) {
        super(throwable);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ApiException(Throwable throwable, int errorCode) {
        super(throwable);
        this.errorCode = errorCode;
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
