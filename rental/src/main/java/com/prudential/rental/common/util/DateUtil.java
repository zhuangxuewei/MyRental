package com.prudential.rental.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DateUtil extends DateUtils {
	public static final long SECOND = 1000L;
	public static final long MINUTE = 60000L;
	public static final long HOUR = 3600000L;
	public static final long DAY = 86400000L;
	public static final long WEEK = 604800000L;
	public static final String YEAR_FORMAT = "yyyy";
	public static final String MONTH_FORMAT = "yyyy-MM";
	public static final String DAY_FORMAT = "yyyy-MM-dd";
	public static final String HOUR_FORMAT = "yyyy-MM-dd HH";
	public static final String MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String MONTH_NUMBER_FORMAT = "yyyyMM";
	public static final String DAY_NUMBER_FORMAT = "yyyyMMdd";
	public static final String HOUR_NUMBER_FORMAT = "yyyyMMddHH";
	public static final String MINUTE_NUMBER_FORMAT = "yyyyMMddHHmm";
	public static final String SECOND_NUMBER_FORMAT = "yyyyMMddHHmmss";
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final int DAY_NUM_OF_WEEK = 7;
	public static final int WORKING_DAY_OF_WEEK = 5;
	private static final String[] FORMATS_CROSS_BAR_SPACE = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
			"yyyy-MM-dd HH", "yyyy-MM-dd", "yyyy-MM", "yyyy" };

	private static final String[] FORMATS_NUMERIC = { "yyyyMMddHHmmss", "yyyyMMddHHmm", "yyyyMMddHH", "yyyyMMdd",
			"yyyyMM", "yyyy" };

	private static final String[] FORMATS_CROSS_BAR = { "yyyy-MM-dd", "yyyy-MM" };

	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

	private static Map<String, SimpleDateFormat> map = new HashMap();

	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	public static SimpleDateFormat getSimpleDateFormat(String parsePatterns) {
		SimpleDateFormat dateFormat = null;
		if (map.containsKey(parsePatterns)) {
			dateFormat = (SimpleDateFormat) map.get(parsePatterns);
		} else {
			dateFormat = new SimpleDateFormat(parsePatterns);
			map.put(parsePatterns, dateFormat);
		}
		return dateFormat;
	}

	public static String format(Date date, String parsePatterns) {
		if ((StringUtils.isEmpty(parsePatterns)) || (date == null)) {
			return null;
		}
		return getSimpleDateFormat(parsePatterns).format(date);
	}

	public static boolean beforeTime(Date time1, Date time2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(time1);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(time2);

		return calendar1.before(calendar2);
	}

	public static BigDecimal getCurrentTimeAsNumber() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		returnStr = f.format(getNow());
		return new BigDecimal(returnStr);
	}

	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getDate(Date date) {
		return getDateTime("yyyy-MM-dd", date);
	}

	public static String getDateTime(Date date) {
		return getDateTime("yyyy-MM-dd HH:mm:ss", date);
	}

	public static String getDateTime(String pattern, Date date) {
		if (date == null) {
			return "";
		}
		String formatPattern = pattern;
		if (pattern == null) {
			formatPattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatPattern, Locale.getDefault());
		String ret = formatter.format(date);
		return ret;
	}

	public static int getDaysBetween(Calendar date1, Calendar date2) {
		Calendar startDate = date1;
		Calendar endDate = date2;
		if (startDate.after(endDate)) {
			Calendar swap = startDate;
			startDate = endDate;
			endDate = swap;
		}
		int days = endDate.get(6) - startDate.get(6);
		int y2 = endDate.get(1);
		if (startDate.get(1) != y2) {
			startDate = (Calendar) startDate.clone();
			do {
				days += startDate.getActualMaximum(6);
				startDate.add(1, 1);
			} while (startDate.get(1) != y2);
		}
		return days;
	}

	public static int getDaysBetween(Date startDate, Date endDate) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(endDate);
		return getDaysBetween(cal1, cal2);
	}

	public static int getHolidays(Calendar startDate, Calendar endDate) {
		return getDaysBetween(startDate, endDate) - getWorkingDay(startDate, endDate);
	}

	public static Calendar getNextMonday(Calendar date) {
		Calendar result = date;
		do {
			result = (Calendar) result.clone();
			result.add(5, 1);
		} while (result.get(7) != 2);
		return result;
	}

	public static int getNowYearMonthDay() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		returnStr = f.format(getNow());
		return Integer.parseInt(returnStr);
	}

	public static int getWorkingDay(Calendar date1, Calendar date2) {
		int result = -1;
		Calendar startDate = date1;
		Calendar endDate = date2;
		if (startDate.after(endDate)) {
			Calendar swap = startDate;
			startDate = endDate;
			endDate = swap;
		}

		int chargeStartDate = 0;
		int chargeEndDate = 0;

		int stmp = 7 - startDate.get(7);
		int etmp = 7 - endDate.get(7);
		if ((stmp != 0) && (stmp != 6)) {
			chargeStartDate = stmp - 1;
		}
		if ((stmp != 0) && (stmp != 6)) {
			chargeEndDate = etmp - 1;
		}
		result = getDaysBetween(getNextMonday(startDate), getNextMonday(endDate)) / 7 * 5 + chargeStartDate
				- chargeEndDate;

		return result;
	}

	public static int getWorkingDay(Date startDate, Date endDate) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(endDate);
		return getWorkingDay(cal1, cal2);
	}

	public static int getYearMonth(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM", Locale.getDefault());
		String ret = formatter.format(date);
		return Integer.parseInt(ret);
	}

	public static int getYearMonthDay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		String ret = formatter.format(date);
		return Integer.parseInt(ret);
	}

	public static Date parse(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		String[] formats = null;
		if (RegexUtil.isNumeric(dateStr))
			formats = FORMATS_NUMERIC;
		else if (dateStr.contains("-")) {
			if (dateStr.contains(" "))
				formats = FORMATS_CROSS_BAR_SPACE;
			else {
				formats = FORMATS_CROSS_BAR;
			}

		}

		if (formats == null) {
			return null;
		}
		return parse(dateStr, formats);
	}

	public static Date parse(String dateStr, String[] formats) {
		SimpleDateFormat df = null;
		for (String dateFormat : formats) {
			df = new SimpleDateFormat(dateFormat);
			try {
				return df.parse(dateStr);
			} catch (ParseException e) {
				LOGGER.warn("Date {} does not match with the format {}", dateStr, dateFormat);
			}
		}
		return null;
	}

	public static long poor(Date date1, Date date2) {
		if ((null == date1) || (null == date2)) {
			return 0L;
		}
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(date1);
		calendar2.setTime(date2);
		long millisecond = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
		return millisecond;
	}
}