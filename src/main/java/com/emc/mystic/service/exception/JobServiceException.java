package com.emc.mystic.service.exception;

public class JobServiceException extends ServiceException {
    public JobServiceException(String msg) {
        super(msg);
    }

    public JobServiceException(String msg, int errorCode) {
        super(msg, errorCode);
    }

    public JobServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public JobServiceException(String msg, Throwable cause, int errorCode) {
        super(msg, cause, errorCode);
    }
}
