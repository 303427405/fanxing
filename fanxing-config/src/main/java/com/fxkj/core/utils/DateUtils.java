package com.fxkj.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static SimpleDateFormat formatter;

	public static String longDate(Date aDate) {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(aDate);
	}

	public static String longDate() {
		formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Calendar calendar = Calendar.getInstance();
		return formatter.format(calendar.getTime());
	}

	public static String shortDate(Date date) {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static String formatDate(Date date) {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(date);
	}

	/**
	 * 计算时间差，返回 毫秒
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getMistimingDate(Date startDate, Date endDate) {
		long between = (endDate.getTime() - startDate.getTime()) / 1000;// 除以1000是为了转换成毫秒
		return between;
	}

	public static int getBetweenDays(String t1, String t2) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			int betweenDays = 0;
			Date d1 = format.parse(t1);
			Date d2 = format.parse(t2);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(d1);
			c2.setTime(d2);
			// 保证第二个时间一定大于第一个时间
			if (c1.after(c2)) {
				c1 = c2;
				c2.setTime(d1);
			}
			int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
			betweenDays = c2.get(Calendar.DAY_OF_YEAR)
					- c1.get(Calendar.DAY_OF_YEAR);
			for (int i = 0; i < betweenYears; i++) {
				c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
				betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
			}
			return betweenDays;
		} catch (ParseException e) {
			// TODO: handle exception
		}
		return 0;

	}

	public static int compareDate(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static Date formatTime(String createTime) {
		Date date = new Date();
		try {
			long msgCreateTime = Long.parseLong(createTime) * 1000L;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = format.parse(format.format(new Date(msgCreateTime)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
