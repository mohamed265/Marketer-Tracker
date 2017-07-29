/**
 * @author mohamed265
 * Created On : Mar 25, 2017 1:14:05 PM
 */
package com.mohamed265.marketertracker.util;

import java.util.Date;

/**
 * @author mohamed265
 *
 */
public class DateFactory {

	public static Date getDate() {
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}

	public static String getCurrentDateAsString() {
		Date date = new Date();
		return (date.getYear() + 1900) + "" + (date.getMonth() + 1) + "" + date.getDate() + "" + date.getHours() + ""
				+ date.getMinutes();

	}

}
