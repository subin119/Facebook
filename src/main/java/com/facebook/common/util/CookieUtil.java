package com.facebook.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static void addCookie(
			HttpServletResponse response, String key, String value) {
		Cookie cookie = new Cookie(key, value);
		response.addCookie(cookie);
	}
	
	public static void removeCookie(
			HttpServletResponse response, String key) {
		Cookie cookie = new Cookie(key, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	public static String getCookie(
			HttpServletRequest request, String key) {
		return getCookie(request, key, "");
	}
	
	public static String getCookie(
			HttpServletRequest request, String key, String defaultValue) {
		Cookie[] cookies = request.getCookies();
		
		for (Cookie cookie : cookies) {
			if ( cookie.getName().equals(key) ) {
				if ( cookie.getValue() == null ) {
					return defaultValue;
				}
				else {
					return cookie.getValue();
				}
			}
		}
		
		return defaultValue;
	}
	
}
