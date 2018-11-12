package com.ectrip.ticket.service.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WEB SERVICE 中用到的所有公用的方法和类 如数字比较等. 对象减法等. 用于数字的比较
 *
 * @author LIJIN
 *
 */
public class ServiceUtil {

	public static void main(String[] args) throws Exception {

		System.out.println(ServiceUtil
				.StrLen("WEB SERVICE 中用到的所有公用的方法和类 如数字比较等. 对象减法等. 用于数字的比较"));
		System.out.println("WEB SERVICE 中用到的所有公用的方法和类 如数字比较等. 对象减法等. 用于数字的比较"
				.length());

	}


	/**
	 * 将字符串首字母转成大写。
	 *
	 * @param word
	 * @return
	 */
	public static String oneUpper(String word) {

		return word ==null || word.equals("") ? word : word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}


	/**
	 * 四舍五入方法
	 *
	 * @param f
	 * @return
	 */
	public static float formart45(float f) {
		BigDecimal a = new BigDecimal(f);
		float af = a.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		return af;
	}

	/**
	 * 难证日期，时间的正则表达式
	 *
	 * @param patternString
	 * @return
	 */
	public boolean isTimeLegal(String patternString) {

		Pattern a = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher b = a.matcher(patternString);
		if (b.matches()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 四舍五入方法
	 *
	 * @param f
	 * @return
	 */
	public static double formart45(double f) {
		BigDecimal a = new BigDecimal(f);
		double af = a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return af;
	}

	/**
	 * 四舍五入方法
	 *
	 * @param f
	 * @return
	 */
	public static double formart45(double f, int point) {
		BigDecimal a = new BigDecimal(f);
		double af = a.setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
		return af;
	}

	/**
	 * 两个Double 的减法运算,
	 *
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static Double Doubleminus(Double p1, Double p2) {

		if (p1 == null)
			return 0d;
		if (p2 == null)
			return 0d;

		return (p1 - p2);

	}

	/**
	 * 求字符串真正长度
	 *
	 * @param str
	 * @return
	 */
	public static int StrLen(String str) {
		if (str == null || str.length() <= 0) {
			return 0;
		}

		int len = 0;

		char c;
		for (int i = str.length() - 1; i >= 0; i--) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
					|| (c >= 'A' && c <= 'Z')) {
				// 字母, 数字
				len++;
			} else {
				if (Character.isLetter(c)) { // 中文
					len += 2;
				} else { // 符号或控制字符
					len++;
				}
			}
		}

		return len;
	}


}
