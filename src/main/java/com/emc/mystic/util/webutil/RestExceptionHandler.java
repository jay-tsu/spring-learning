package com.emc.mystic.util.webutil;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger logger = LogManager.getLogger();

    private static final String UNEXPECTED_ERROR = "RuntimeException.unexpected";

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<String> runtimeExceptionHandler(final ServletWebRequest request,
                                                             final RuntimeException exception, final Locale locale) {
        HttpServletRequest servletRequest = (HttpServletRequest) request.getNativeRequest();
        logger.error("Handlering exception for request: " + servletRequest, exception);

        String errorMessage = LocaleUtil.getMessage("l10n", UNEXPECTED_ERROR, locale) + "\n";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.TEXT_PLAIN)
                .body(errorMessage + ExceptionUtils.getStackTrace(exception));
    }
}
