package com.emc.mystic.rest.controller.message;

import com.emc.mystic.util.webutil.LocaleUtil;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum ClusterMessageSource {
    CLUSTER_SERVICE_START(0, "", HttpStatus.OK, ""),
    NO_RELATED_ID(1, "id_not_found", HttpStatus.INTERNAL_SERVER_ERROR, "l10n");

    public static List<ClusterMessageSource> MESSAGES = Arrays.asList(
            NO_RELATED_ID
    );

    private int errorCode;
    private String message_key;
    private HttpStatus httpStatus;
    private String bundleName;
    private List<String> params;

    ClusterMessageSource(final int errorCode, final String msg_key, final HttpStatus httpStatus, final String bundleName) {
        this.errorCode = errorCode;
        this.message_key = msg_key;
        this.httpStatus = httpStatus;
        this.bundleName = bundleName;
        this.params = null;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

//    public String getLocalizedMessage(final String locale) {
//        return LocaleUtil.getMessage(this.bundleName, this.message_key, new Locale(locale));
//    }

    public String getLocalizedMessage(final String locale, Object... args) {
        return LocaleUtil.getMessage(this.bundleName, this.message_key, new Locale(locale), args);
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getBundleName() {
        return this.bundleName;
    }

    public String getMessageKey() {
        return this.message_key;
    }
}


