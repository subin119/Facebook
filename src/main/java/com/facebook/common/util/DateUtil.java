package com.facebook.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static String now() {
		Date now = new Date();
		SimpleDateFormat format = 
				new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss", Locale.KOREAN);
		return format.format(now);
	}
	
}
