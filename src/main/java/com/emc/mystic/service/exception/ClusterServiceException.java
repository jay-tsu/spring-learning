package com.emc.mystic.service.exception;

public class ClusterServiceException extends ServiceException {
    public ClusterServiceException(String msg) {
        super(msg);
    }

    public ClusterServiceException(String msg, int errorCode) {
        super(msg, errorCode);
    }

    public ClusterServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ClusterServiceException(String msg, Throwable cause, int errorCode) {
        super(msg, cause, errorCode);
    }
}
