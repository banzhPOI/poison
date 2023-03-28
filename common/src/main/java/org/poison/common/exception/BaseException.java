package org.poison.common.exception;

public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;

    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public BaseException(String errMessage) {
        super(errMessage);
    }

    public BaseException(String errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
    }

    public BaseException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public BaseException(String errCode, String errMessage, Throwable e) {
        super(errMessage, e);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

}
