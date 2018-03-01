package com.emc.mystic.service.exception;

public class LogBundleServiceException extends ServiceException {
    public enum ErrorCode {
        INVLIAD_LOG_BUNDLE_TYPE;  // # 1

        public int getValue() {
            return ordinal() + 1;
        }
    }


    public LogBundleServiceException(String msg) {
        super(msg);
    }

    public LogBundleServiceException(String msg, int errorCode) {
        super(msg, errorCode);
    }

    public LogBundleServiceException(String msg, int errorCode, Object... args) {
        super(msg, errorCode, args);
    }

    public LogBundleServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public LogBundleServiceException(String msg, Throwable cause, int errorCode) {
        super(msg, cause, errorCode);
    }
}
