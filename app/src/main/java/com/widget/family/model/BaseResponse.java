package com.widget.family.model;

import java.io.Serializable;

/**
 * Created by wei.
 * Date: 2019-04-25 23:21
 * Description:
 */
public class BaseResponse<T> implements Serializable {

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
