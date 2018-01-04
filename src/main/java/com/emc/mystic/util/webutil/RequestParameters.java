package com.emc.mystic.util.webutil;

import javax.servlet.http.HttpServletRequest;

public class RequestParameters {
    private String url;
    private Integer expand = WebConstants.DEFAULT_EXPAND;
    private String locale;
    private HttpServletRequest request;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getExpand() {
        return expand;
    }

    public void setExpand(Integer expand) {
        this.expand = expand;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "url: " + getUrl() + ", expand: " + getExpand() + ", locale: " + getLocale();
    }

}