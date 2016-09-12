package com.blogger.web.ui;

import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.TimeZoneAwareLocaleContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.WebUtils;

public class AngularCookieResolver extends CookieLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        parseLocaleCookieIfNecessary(request);

        return (Locale) request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
    }

    @Override
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {
        parseLocaleCookieIfNecessary(request);

        return new TimeZoneAwareLocaleContext() {
            @Override
            public Locale getLocale() {
                return (Locale) request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
            }

            @Override
            public TimeZone getTimeZone() {
                return (TimeZone) request.getAttribute(TIME_ZONE_REQUEST_ATTRIBUTE_NAME);
            }
        };
    }

    @Override
    public void addCookie(HttpServletResponse response, String cookieValue) {
        // Mandatory cookie modification for angular to support the locale
        // switching on the server side
        cookieValue = "%22" + cookieValue + "%22";
        super.addCookie(response, cookieValue);
    }

    private void parseLocaleCookieIfNecessary(HttpServletRequest request) {
        if (request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME) != null) {
            return;
        }

        Cookie cookie = WebUtils.getCookie(request, getCookieName());
        if (cookie == null) {
            return;
        }

        // Retrieve and parse cookie value.
        Locale locale = null;
        TimeZone timeZone = null;

        // format: [localePart] or [localePart timeZonePart]
        String value = cookie.getValue();

        // Remove the double quote if any
        value = StringUtils.replace(value, "%22", "");

        String localePart = value;
        String timeZonePart = null;
        int spaceIndex = localePart.indexOf(' ');
        if (spaceIndex != -1) {
            localePart = value.substring(0, spaceIndex);
            timeZonePart = value.substring(spaceIndex + 1);
        }

        if (!"-".equals(localePart)) {
            locale = StringUtils.parseLocaleString(localePart.replace('-', '_'));
        }

        if (timeZonePart != null) {
            timeZone = StringUtils.parseTimeZoneString(timeZonePart);
        }

        request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME,
                (locale != null ? locale : determineDefaultLocale(request)));

        request.setAttribute(TIME_ZONE_REQUEST_ATTRIBUTE_NAME,
                (timeZone != null ? timeZone : determineDefaultTimeZone(request)));
    }
}
