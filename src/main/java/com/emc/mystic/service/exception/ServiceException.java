package com.emc.mystic.service.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = 5358683245923127425L;
    private int errorCode;
    private List<Object> arguments;

    /**
     * Exception in an RestController implementation.
     * @param msg the text of the handler
     * @see Exception
     * @see HttpStatus
     */
    public ServiceException(final String msg) {
        super(msg);
    }

    /**
     * Exception in an OData service implementation.
     * @param msg the text of the handler
     * @param errorCode the error code of the handler as defined by the OData standard
     * @see Exception
     * @see HttpStatus
     */
    public ServiceException(final String msg, final int errorCode) {
        this(msg);
        this.errorCode = errorCode;
    }

    /**
     * Exception in an OData service implementation.
     * @param msg the text of the handler
     * @param errorCode the error code of the handler as defined by the OData standard
     * @see Exception
     * @see HttpStatus
     */
    public ServiceException(final String msg, final int errorCode, Object... args) {
        this(msg);
        this.errorCode = errorCode;
        this.arguments = Arrays.asList(args);
    }

    /**
     * Exception in an OData service implementation.
     * @param msg the text of the handler
     * @param cause the cause of this handler
     * @see Exception
     * @see HttpStatus
     * @see Throwable#getCause()
     */
    public ServiceException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    /**
     * Exception in an OData service implementation.
     * @param msg the text of the handler
     * @param cause the cause of this handler
     * @param errorCode the error code of the handler as defined by the OData standard
     * @see Exception
     * @see HttpStatus
     * @see Throwable#getCause()
     */
    public ServiceException(final String msg, final Throwable cause, final int errorCode) {
        this(msg, cause);
        this.errorCode = errorCode;
    }

    /**
     * This method will return the error code specified by the application. The default is null.
     * @return the applications error code.
     */
    public int getErrorCode() {
        return errorCode;
    }

    public Object[] getExceptionArguments() {
        return arguments.toArray();
    }
}
