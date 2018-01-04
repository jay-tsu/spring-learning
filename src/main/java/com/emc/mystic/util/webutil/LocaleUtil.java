package com.emc.mystic.util.webutil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;

public class LocaleUtil {
    private static final Logger logger = LogManager.getLogger();
    public static final Locale DEFAULT_LOCALE = Locale.US;

    public static Locale getLocale(String locale) {
        if (locale == null) {
            return DEFAULT_LOCALE;
        }

        locale.replaceAll("-", "_");
        String[] lc = locale.split("_");
        if (lc.length != 2) {
            return DEFAULT_LOCALE;
        } else {
            return new Locale(lc[0], lc[1]);
        }
    }

    public static Locale getLocale(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String lang = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mystic_lang")) {
                    lang = cookie.getValue();
                    break;
                }
            }
        }

        if (lang == null) {
            lang = req.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        }

        return StringUtils.hasText(lang) ? getLocale(lang) : DEFAULT_LOCALE;
    }

    public static String getMessage(String bundleName, String key, HttpServletRequest req) {
        return getMessage(bundleName, key, getLocale(req));
    }

    public static String getMessage(String bundleName, String key, String locale) {
        return getMessage(bundleName, key, getLocale(locale));
    }

    public static String getMessage(String bundleName, String key, Locale locale) {
        logger.trace("Get localized message for bundle " + bundleName + ", key " + key + ", locale " + locale);
        if (!StringUtils.hasText(bundleName) || !StringUtils.hasText(key)) {
            return key;
        }

        if (locale == null) {
            locale = DEFAULT_LOCALE;
        }

        String locMsg = getBundle(bundleName, locale).getString(key);
        if (locMsg == null) {
            String enMsg = getBundle(bundleName, DEFAULT_LOCALE).getString(key);
            return enMsg == null ? key : enMsg;
        } else {
            return locMsg;
        }
    }

    private static ResourceBundle getBundle(String bundleName, Locale locale) {
        try {
            return ResourceBundle.getBundle(bundleName, locale, new UTF8Control());
        } catch (MissingResourceException e1) {
            logger.info("No " + locale + " resource bundle for " + bundleName);
            return null;
        }
    }

    public static String getLocaliedMsg(Object bean, RequestParameters locale) throws Exception {
        return getLocaliedMsg(bean, getLocale(locale.getRequest()));

    }

    public static String getLocaliedMsg(Object bean, Locale locale) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        String reValue = null;

        for (PropertyDescriptor pd : pds) {
            Method methodGetProp = pd.getReadMethod();
            Localizable l10n = AnnotationUtils.findAnnotation(methodGetProp, Localizable.class);
            if (l10n != null) {
                String key = LocaleUtil.getLocalizationKey(bean, methodGetProp, l10n);
                String localizedMsg = LocaleUtil.getMessage(l10n.bundle(), key, locale);
                reValue = LocaleUtil.getLocalizedMsgWithParams(bean, methodGetProp, l10n, localizedMsg);
            }
        }
        return reValue;
    }

    public static String getLocalizationKey(Object bean, Method reader, Localizable l10n) throws Exception {
        String simpleKey = getLocalizationKeyWithoutPrefix(bean, reader, l10n);
        return simpleKey == null ? null :
                StringUtils.hasText(l10n.prefix()) ? l10n.prefix() + "." + simpleKey : simpleKey;
    }

    private static String getLocalizationKeyWithoutPrefix(Object bean, Method reader, Localizable l10n) throws Exception {
        if (bean == null || reader == null || l10n == null) {
            return null;
        }

        if (StringUtils.hasText(l10n.keyprop())) {
            String keyProp = l10n.keyprop();
            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(bean.getClass(), keyProp);
            if (pd == null || pd.getReadMethod() == null) {
                logger.error("Class " + bean.getClass().getSimpleName() + " does not have a property called " + keyProp);
                return null;
            }
            else {
                return pd.getReadMethod().invoke(bean).toString();
            }
        }
        else {
            return reader.invoke(bean).toString();
        }
    }

    public static List<String> getLocalizationParams(Object bean, Method reader, Localizable l10n) throws Exception {
        if (bean == null || reader == null || l10n == null) {
            return null;
        }

        if (StringUtils.hasText(l10n.param())) {
            String param = l10n.param();
            PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(bean.getClass(), param);
            if (pd == null || pd.getReadMethod() == null) {
                logger.error("Class " + bean.getClass().getSimpleName() + " does not have a property called " + param);
                return null;
            }
            else {
                Object paramsObj = pd.getReadMethod().invoke(bean);
                if(paramsObj == null)
                    return null;
                if (paramsObj instanceof List<?>)
                    return (List<String>)paramsObj;
                else
                    return Arrays.asList(((String)paramsObj).split("\\|"));
            }
        }
        else {
            return null;
        }
    }

    public static String getLocalizedMsgWithParams(Object bean, Method reader, Localizable l10n, String localizedMsg) throws Exception{
        List<String> lst = getLocalizationParams(bean, reader, l10n);
        if(lst == null || lst.size() == 0)
            return localizedMsg;
        String newlocalizedMsg = localizedMsg;
        try {
            //newlocalizedMsg = newlocalizedMsg.replaceAll("'", "''");
            String[] arr = (String[])lst.toArray(new String[lst.size()]);
            newlocalizedMsg = MessageFormat.format(localizedMsg, arr);
            if(newlocalizedMsg.contains("{0}")) {
                logger.error("Message format failed with localizedMsg: " + localizedMsg
                        + " and parameters: " + lst + ". Try to replace localizedMsg with parameters again.");
                newlocalizedMsg = localizedMsg;
                for (int i =0; i < lst.size(); i++) {
                    newlocalizedMsg = newlocalizedMsg.replaceFirst("\\{" + i + "\\}", String.valueOf(lst.get(i)));
                }
            }
        }catch(Exception e){
            logger.error("Message format failed with localizedMsg: " + localizedMsg);
            newlocalizedMsg = localizedMsg;
        }
        return newlocalizedMsg;
    }
}
