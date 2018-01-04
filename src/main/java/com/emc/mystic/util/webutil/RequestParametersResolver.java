package com.emc.mystic.util.webutil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class RequestParametersResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Object resolveArgument(MethodParameter mParam, ModelAndViewContainer c, NativeWebRequest nativeReq,
                                  WebDataBinderFactory f) throws Exception {
        RequestParameters reqParams = new RequestParameters();

        HttpServletRequest httpReq = nativeReq.getNativeRequest(HttpServletRequest.class);

        reqParams.setLocale(LocaleUtil.getLocale(httpReq).toString());

        reqParams.setUrl(httpReq.getRequestURL().toString());
        String expandStr = nativeReq.getParameter("expand");
        if (expandStr != null) {
            if (expandStr.compareToIgnoreCase("all") == 0) {
                reqParams.setExpand(-1);
            } else {
                try {
                    reqParams.setExpand(Integer.parseInt(expandStr));
                } catch (NumberFormatException e) {
                    logger.error(expandStr + " is not a valid input used for 'expand' parameter.");
                }
            }
        }

        reqParams.setRequest(httpReq);

        logger.trace("Request parameters: " + reqParams.toString());
        return reqParams;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(RequestParameters.class);
    }

}

