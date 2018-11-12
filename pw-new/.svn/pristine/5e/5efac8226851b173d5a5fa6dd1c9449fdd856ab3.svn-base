package com.ectrip.base.util;

import java.io.BufferedInputStream;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 * 所有底层的方法
 * 
 * @author 李进
 * 
 * @date 2008-08-21
 */

public class Tools {
	private static String hexString = "0123456789ABCDEF";

	private static Log log = LogFactory.getLog(Tools.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*System.out.println();
		String todayinit = "select * from SEQMAXNO where SEQNAME ='" + "ZHFU_SEQ_7"
		+ "' and orda = to_char(sysdate,'YYYY-MM-DD')";
		String ls_sgr = "SEQMAXNO";
		System.out.println("todayinit="+todayinit);
		todayinit = "select count(*) as a from user_tables where table_name = '" + ls_sgr
		+ "'";
		String insrseq = "insert into  SEQMAXNO ( SEQNAME,ORDA) values ('"+ls_sgr+"',to_char(sysdate,'YYYY-MM-DD'))";
		System.out.println("todayinit="+insrseq);
		String ls_str = Tools.ticketMakeMd5("AW3A56WA9011");
		System.out.println("no:" + ls_str);

		System.out.println(" Check: " + checkTicketMd5(ls_str));
		
*/
		
		 String[] dt = new String[8];
			dt[0]="06:30";
			dt[1]="07:30";
			dt[2]="08:20";
			dt[3]=";:20";
			dt[4]="12:20";
			dt[5]="13:10";
			dt[6]="14:10";
			dt[7]="15:00";
			
			System.out.println( dt[0].compareTo( Tools.getNowTime().substring(0,5))); 
	}

	// ******************************* 异常、日志处理 *******************************
	public static void printException(Exception e) {
		synchronized (System.err) {
			System.err.println(e.getMessage());
			StackTraceElement[] trace = e.getStackTrace();
			for (int i = 0; i < trace.length; i++) {
				System.err.println("\tat " + trace[i]);
			}
			log.error(e.getMessage());
			for (int i = 0; i < trace.length; i++) {
				log.error("\tat " + trace[i]);
			}
		}
	}

	public static void print(Object s) {
		System.out.println(s);
		log.info(s);
	}

	/**
	 * 取当前时间
	 * 
	 * @return
	 */
	public static String getSecNowString() {

		String nowString = "";
		String monthString = "";
		String dayString = "";
		String hourString = "";
		String minString = "";
		String secString = "";

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		nowString = nowString + now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		if (month < 10) {
			monthString = "0" + month;

		} else {
			monthString = "" + month;

		}
		int day = now.get(Calendar.DATE);
		if (day < 10) {
			dayString = "0" + day;

		} else {
			dayString = "" + day;

		}

		nowString = nowString + monthString + dayString;

		// 新增时间
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			hourString = "0" + hour;

		} else {
			hourString = "" + hour;

		}

		nowString = nowString + hourString;
		int min = now.get(Calendar.MINUTE);
		if (min < 10) {
			minString = "0" + min;

		} else {
			minString = "" + min;

		}
		nowString = nowString + minString;
		int sec = now.get(Calendar.SECOND);
		if (sec < 10) {
			secString = "0" + sec;

		} else {
			secString = "" + sec;

		}
		nowString = nowString + secString;

		return nowString;

	}

	public static String encodehex(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decodehex(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	// ******************************* 取时间相应的方法 *******************************

	/**
	 * @return 20080708231656859
	 */
	public static String getNowDayTimesMilliSecond() {
		StringBuffer getDayTimes = new StringBuffer();
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		getDayTimes.append(now.get(Calendar.YEAR));
		int month = now.get(Calendar.MONTH) + 1;
		if (month < 10)
			getDayTimes.append("0" + month);
		else
			getDayTimes.append(month);
		int day = now.get(Calendar.DATE);
		if (day < 10)
			getDayTimes.append("0" + day);
		else
			getDayTimes.append(day);
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if (hour < 10)
			getDayTimes.append("0" + hour);
		else
			getDayTimes.append(hour);
		int minute = now.get(Calendar.MINUTE);
		if (minute < 10)
			getDayTimes.append("0" + minute);
		else
			getDayTimes.append(minute);
		int second = now.get(Calendar.SECOND);
		if (second < 10)
			getDayTimes.append("0" + second);
		else
			getDayTimes.append(second);
		int millisecond = now.get(Calendar.MILLISECOND);
		if (millisecond < 10)
			getDayTimes.append("00" + millisecond);
		else if (millisecond >= 10 && millisecond < 100)
			getDayTimes.append("0" + millisecond);
		else
			getDayTimes.append(millisecond);
		return getDayTimes.toString();
	}

	/**
	 * @return 2008-06-25 00:00:00
	 */
	public static String getDayTimes() {
		StringBuffer getDayTimes = new StringBuffer();
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		getDayTimes.append(now.get(Calendar.YEAR) + "-");
		int month = now.get(Calendar.MONTH) + 1;
		if (month < 10)
			getDayTimes.append("0" + month + "-");
		else
			getDayTimes.append(month + "-");
		int day = now.get(Calendar.DATE);
		if (day < 10)
			getDayTimes.append("0" + day + " ");
		else
			getDayTimes.append(day + " ");
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if (hour < 10)
			getDayTimes.append("0" + hour + ":");
		else
			getDayTimes.append(hour + ":");
		int minute = now.get(Calendar.MINUTE);
		if (minute < 10)
			getDayTimes.append("0" + minute + ":");
		else
			getDayTimes.append(minute + ":");
		int second = now.get(Calendar.SECOND);
		if (second < 10)
			getDayTimes.append("0" + second);
		else
			getDayTimes.append(second);
		return getDayTimes.toString();
	}

	/**
	 * @return 20080625000000
	 */
	public static String getDayTime() {
		return getDayTimes().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
	}

	/**
	 * @return 2008
	 */
	public static String getYear() {
		return getDays().substring(0, 4);
	}

	/**
	 * @return 2008-06
	 */
	public static String getMonth() {
		return getDays().substring(0, 7);
	}

	/**
	 * @return 2008-06-25
	 */
	public static String getDays() {
		return getDayTimes().substring(0, 10);
	}

	/**
	 * @return 20080625
	 */
	public static String getDay() {
		return getDays().replaceAll("-", "");
	}

	/**
	 * @return 00:00:00
	 */
	public static String getNowTime() {
		return getDayTimes().substring(11);
	}

	/**
	 * @param thisTime
	 *            09:00:00
	 * @param minture
	 *            60
	 * @return 10:00:00
	 */
	public static String getTimeJS(String thisTime, int minture) {
		int hour = Integer.valueOf(thisTime.substring(0, 2)).intValue();
		int min = Integer.valueOf(thisTime.substring(3, 5)).intValue() + minture;
		int sec = Integer.valueOf(thisTime.substring(6)).intValue();
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		now.set(2010, 2, 22, hour, min, sec);
		String returnStr = "";
		if (now.get(Calendar.HOUR_OF_DAY) < 10) {
			returnStr = "0" + String.valueOf((now.get(Calendar.HOUR_OF_DAY)));
		} else {
			returnStr = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
		}

		if (now.get(Calendar.MINUTE) < 10) {
			returnStr = returnStr + ":0" + String.valueOf(now.get(Calendar.MINUTE));
		} else {
			returnStr = returnStr + ":" + String.valueOf(now.get(Calendar.MINUTE));
		}
		if (now.get(Calendar.SECOND) < 10) {
			returnStr = returnStr + ":0" + String.valueOf(now.get(Calendar.SECOND));
		} else {
			returnStr = returnStr + ":" + String.valueOf(now.get(Calendar.SECOND));
		}
		return returnStr;
	}

	public static Calendar getCalendar(String date) {
		String[] strdate = date.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(strdate[0]), Integer.parseInt(strdate[1]) - 1, Integer.parseInt(strdate[2]));
		return cal;
	}

	/**
	 * @param date
	 *            2008-06-25
	 * @return 1 周日 ||2 周一 ||3 周二 ||4 周三 ||5 周四 ||6 周五 ||7 周六
	 */
	public static int getDayOfWeek(String date) {
		return getCalendar(date).get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * @param date
	 *            2008-06-25
	 * @return 1 周一 ||2 周二 ||3 周三 ||4 周四 ||5 周五 ||6 周六 ||7 周日
	 */
	public static int getDayOfWeekForChina(String date) {
		if (getDayOfWeek(date) - 1 == 0) {
			return 7;
		} else {
			return getDayOfWeek(date) - 1;
		}
	}

	/**
	 * @param date
	 * @return 1~31
	 */
	public static int getDayOfMonth(String date) {
		return getCalendar(date).get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @param date
	 * @return 1~366
	 */
	public static int getDayOfYear(String date) {
		return getCalendar(date).get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * @param date
	 * @return 1~5
	 */
	public static int getDayOfWeekInMonth(String date) {
		return getCalendar(date).get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * @param beginDate
	 *            2008-06-25
	 * @param endDate
	 *            2008-06-26
	 * @return 1
	 */
	public static int getDayNumbCha(String beginDate, String endDate) {
		long second = getCalendar(endDate).getTime().getTime() - getCalendar(beginDate).getTime().getTime();
		long oneDaySecond = 1000 * 60 * 60 * 24;
		return (int) (second / oneDaySecond);
	}

	/**
	 * @param beginDate
	 *            2008-06-25
	 * @param endDate
	 *            2008-06-26
	 * @return 2
	 */
	public static int getDayNumb(String beginDate, String endDate) {
		return getDayNumbCha(beginDate, endDate) + 1;
	}

	public static String getDate(int year, int month, int day) {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		now.set(year, month - 1, day);
		String stryear = String.valueOf(now.get(Calendar.YEAR));
		String strmonth = String.valueOf((now.get(Calendar.MONTH) + 1));
		String strday = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		if (now.get(Calendar.MONTH) + 1 < 10) {
			strmonth = "0" + strmonth;
		}
		if (now.get(Calendar.DAY_OF_MONTH) < 10) {
			strday = "0" + strday;
		}
		return stryear + "-" + strmonth + "-" + strday;
	}

	/**
	 * @param days
	 *            2009-04-07
	 * @param numb
	 *            2
	 * @return String 2009-04-09
	 */
	public static String getDate(String days, int numb) {
		int year = Integer.valueOf(days.substring(0, 4)).intValue();
		int mouth = Integer.valueOf(days.substring(5, 7)).intValue();
		int day = Integer.valueOf(days.substring(8)).intValue() + numb;
		return getDate(year, mouth, day);
	}

	/**
	 * @param eday
	 *            2010-06-25
	 * @param etime
	 *            20:11
	 * @param bday
	 *            2010-06-25
	 * @param btime
	 *            19:10
	 * @return 01:10
	 */
	public static String getHourMinute(String eday, String etime, String bday, String btime) {
		String[] edays = eday.split("-");
		String[] etimes = etime.split(":");
		String[] bdays = bday.split("-");
		String[] btimes = btime.split(":");
		Calendar edate = Calendar.getInstance();
		edate.set(Integer.parseInt(edays[0]), Integer.parseInt(edays[1]) - 1, Integer.parseInt(edays[2]), Integer
				.parseInt(etimes[0]), Integer.parseInt(etimes[1]), 0);
		Calendar bdate = Calendar.getInstance();
		bdate.set(Integer.parseInt(bdays[0]), Integer.parseInt(bdays[1]) - 1, Integer.parseInt(bdays[2]), Integer
				.parseInt(btimes[0]), Integer.parseInt(btimes[1]), 0);
		long second = edate.getTime().getTime() - bdate.getTime().getTime();
		long oneHourSecond = 1000 * 60 * 60;
		long oneMinuteSecond = 1000 * 60;
		long houre = second / oneHourSecond;
		long minute = second % oneHourSecond / oneMinuteSecond;
		String returnString = "";
		if (houre < 10) {
			returnString = "0" + houre + ":";
		} else {
			returnString = houre + ":";
		}
		if (minute < 10) {
			returnString += "0" + minute;
		} else {
			returnString += minute;
		}
		return returnString;
	}

	// ******************************* MD5加密 *******************************
	private static String defaultPwdAdd = "ectrip";
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString().toUpperCase();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String md5Encode(String pwd) {
		String encodePwd = null;
		try {
			encodePwd = new String(pwd + defaultPwdAdd);
			MessageDigest md = MessageDigest.getInstance("MD5");
			encodePwd = byteArrayToHexString(md.digest(encodePwd.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encodePwd;
	}

	// ******************************* base64加解密 *******************************
	public static String base64Encode(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
		}
	}

	public static String base64Dncode(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			try {
				byte[] b = (new sun.misc.BASE64Decoder()).decodeBuffer(s);
				return new String(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	// ******************************* 获取IP地址 *******************************
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// *********************** BeanUtils Bean populate ***********************
	/**
	 * 若Map中key为customer.usid 需要压到Customer对象usid属性中去
	 * 
	 * @param bean
	 * @param properties
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void populate(Object bean, Map properties) throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> localProperties = new HashMap<String, Object>();
		Iterator<?> it = properties.entrySet().iterator();
		int isrun = 0;
		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			String ls_array[] = ((String) key).split("[.]");
			if (ls_array.length > 0) {
				localProperties.put(ls_array[ls_array.length - 1], value);
				isrun = 1;
			}
		}
		if (isrun == 1) {
			BeanUtilsBean.getInstance().populate(bean, localProperties);
		} else {
			BeanUtilsBean.getInstance().populate(bean, properties);
		}
	}

	// *********************** BLOB转换 ***********************
	/**
	 * 将二进制BLOB转换成字符串String
	 */
	public static String getStrByBlob(Blob blob) throws Exception {
		if (blob == null) {
			return "";
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedInputStream in = new BufferedInputStream(blob.getBinaryStream());
		int c;
		while ((c = in.read()) != -1) {
			out.write(c);
		}
		out.close();
		in.close();
		return out.toString();
	}

	/**
	 * 将字符串String转换成二进制BLOB
	 */
	public static Blob getBlobByStr(String str) {
		if (str == null || str.equals("")) {
			str = "";
		}
		SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		return session.getLobHelper().createBlob(str.getBytes());
	}

	// *********************** 系统根目录 建文件夹 删除文件夹 ***********************
	// 系统根目录
	public static String getRealPath() {
		return null;
//		return ServletActionContext.getServletContext().getRealPath("/");
	}

	// 建文件夹
	public static void createDirs(String dirPath) {
		try {
			File f = new File(getRealPath() + dirPath);
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author: likai
	 * @param filePath
	 * @throws Exception void
	 * @use: 删除文件
	 * 
	 */
	public static void deleteFile(String filePath) throws Exception {
		java.io.File file = new java.io.File(getRealPath() + filePath);
		if (file.exists()) {
			if (!file.isFile()) {
				throw new Exception("Exception:" + filePath + " is not a file");
			} else {
				try {
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @author: likai
	 * @param dirPath
	 * @throws Exception void
	 * @use: 删除递归删除文件夹和其下面的文件
	 * 
	 */
	public static void deleteDirs(String dirPath) throws Exception {
		java.io.File m_root = new java.io.File(getRealPath() + dirPath);
		if (m_root.exists()) {
			if (!m_root.isDirectory()) {
				throw new Exception("Exception:" + "m_root.toString()" + " is not a director");
			} else {
				try {
					myDelete(m_root);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void visitAll(java.io.File tempRoot, ArrayList m_dirs) {
		java.io.File[] dirs = tempRoot.listFiles();
		if (dirs != null) {
			List dirsList = Arrays.asList(dirs);
			if (dirsList == null) {
				try {
					tempRoot.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				m_dirs.addAll(dirsList);
				for (int i = 0; i < dirsList.size(); i++) {
					tempRoot = (java.io.File) dirsList.get(i);
					visitAll(tempRoot, m_dirs);
				}
			}
		}
	}

	private static void myDelete(java.io.File m_root) throws Exception {
		ArrayList m_dirs = new ArrayList();
		m_dirs.add(m_root);// 将指定的文件夹也加入删除列表中
		visitAll(m_root, m_dirs);
		if (m_dirs != null) {
			for (int i = m_dirs.size() - 1; i >= 0; i--) {
				java.io.File f = (java.io.File) m_dirs.remove(i);
				String fileName = f.toString();
				if (!f.delete()) {
					throw new Exception("Exception: delete file " + fileName + " false!");
				}
			}
		} else {
			throw new Exception("Exception: read file list of " + m_root.toString() + "false!");
		}
	}

	/**
	 * @author: likai
	 * @param path
	 * @param filename
	 * @param content
	 * @return boolean
	 * @throws IOException
	 * 
	 * @use: 生成具体文件
	 * 
	 */
	public static boolean mikeFile(String path, String filename, String content) throws IOException {
		boolean mikflg = false;
		FileOutputStream out = null;
		String realPath = getRealPath() + path;
		try {
			File filepath = new File(realPath);
			if (filepath.exists()) {
			} else {
				filepath.mkdirs();
				mikflg = true;
			}
			if (filename != null && content != null) {
				File filefilename = new File(realPath, filename);
				if (filefilename.exists()) {
					filefilename.delete();
				}
				filefilename.createNewFile();
				out = new FileOutputStream(filefilename);
				// byte byte0[] = content.getBytes("GBK");
				// String newContent = new String(byte0, "utf-8");
				// out.write(newContent.getBytes("utf-8"));

				StringBuffer stringBuffer = new StringBuffer(content);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out, "UTF-8");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				bufferedWriter.write(new String(stringBuffer.toString().getBytes("UTF-8"), "UTF-8"));
				bufferedWriter.flush();
				bufferedWriter.close();
				bufferedWriter = null;
				// stringBuffer.delete(0, stringBuffer.length() - 1);
				mikflg = true;
			}
		} catch (IOException ex) {
			mikflg = false;
			ex.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return mikflg;
	}

	public static boolean mikeFile1(String path, String filename, String content) throws IOException {
		boolean mikflg = false;
		FileOutputStream out = null;
		String realPath = getRealPath() + path;
		try {
			File filepath = new File(realPath);
			if (filepath.exists()) {
			} else {
				filepath.mkdirs();
				mikflg = true;
			}
			if (filename != null && content != null) {
				File filefilename = new File(realPath, filename);
				if (filefilename.exists()) {
					filefilename.delete();
				}
				filefilename.createNewFile();
				out = new FileOutputStream(filefilename);
				byte abyte0[] = content.getBytes("gb2312");
				out.write(abyte0);
				mikflg = true;
			}
			mikflg = true;
		} catch (IOException ex) {
			mikflg = false;
			ex.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return mikflg;
	}

	// *********************** 字符串替换 ***********************
	/**
	 * @param strSource原始字符串
	 * @param strFrom需要替换字符串
	 * @param strTo替换成的字符串
	 * @return String
	 */
	public static String replaceAll(String strSource, String strFrom, String strTo) {
		if (strFrom == null || strFrom.equals(""))
			return strSource;
		String strDest = "";
		int intFromLen = strFrom.length();
		int intPos;
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest + strSource.substring(0, intPos);
			strDest = strDest + strTo;
			strSource = strSource.substring(intPos + intFromLen);
		}
		return strDest + strSource;
	}

	/**
	 * @param strSource原始字符串
	 * @param strFrom需要替换字符串
	 * @param strTo替换成的字符串
	 * @return String
	 */
	public static String replaceFirst(String strSource, String strFrom, String strTo) {
		if (strFrom == null || strFrom.equals(""))
			return strSource;
		String strDest = "";
		int intFromLen = strFrom.length();
		int intPos = strSource.indexOf(strFrom);
		strDest = strDest + strSource.substring(0, intPos);
		strDest = strDest + strTo;
		strSource = strSource.substring(intPos + intFromLen);
		return strDest + strSource;
	}

	// *********************** 验证 ***********************
	// YYYY-MM-DD
	public static final String YYYY_MM_DD = "^(((((0[48]00)|(0[1-9]((0[48])|([2468][048])|([13579][26]))))-02-29)|((0[1-9][0-9][0-9])-((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30))|(((0[1-9])|(1[0-2]))-((0[1-9])|(1[0-9])|(2[0-8])))))))|((((((([13579][26])|([2468][048]))00)|([1-9][0-9]((0[48])|([13579][26])|([2468][048]))))-02-29)|(([1-9][0-9][0-9][0-9])-((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30))|(((0[1-9])|(1[0-2]))-((0[1-9])|(1[0-9])|(2[0-8])))))))|((((00((0[48])|([2468][048])|([13579][26])))-02-29)|((00((0[1-9])|([1-9][0-9])))-((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30))|(((0[1-9])|(1[0-2]))-((0[1-9])|(1[0-9])|(2[0-8])))))))$";
	// YYYYMMDD
	public static final String YYYYMMDD = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	// MM-DD
	public static final String MM_DD = "^(\\d{1,2})([-./])(\\d{1,2})$";
	// TT:TT
	public static final String TT_TT = "^(([0-1]\\d)|(2[0-4])):[0-5]\\d$";
	// 正int
	public static final String ZHENG_INTEGER = "^[1-9]\\d*|0$";
	// 正double int
	public static final String ZHENG_DOUBLE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|[1-9]\\d*|0|0.0$";
	// 匹配由数字、26个英文字母或者下划线组成的字符串
	public static final String STRING = "^\\w+$";
	// 数字字符串;
	public static final String NUMBSTRING = "^[0-9]*$";

	public static boolean validation(String str, String strpattern) {
		Pattern pattern = Pattern.compile(strpattern);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static int validationOrid(String orid) {
		if (orid.length() != 14) {
			return 0;
		} else if (validation(orid.substring(0, 8), YYYYMMDD) == false) {
			return 1;
		} else if (validation(orid.substring(8), ZHENG_INTEGER)) {
			return 2;
		} else {
			return 3;
		}
	}

	// *********************** 格式化数据 ***********************
	public static double format(double f) {
		String t = String.valueOf(f);
		int p = t.indexOf(".") > 0 ? t.indexOf(".") : t.length() - 1;
		int l = t.substring(0, p).length() - 1;
		BigDecimal b = new BigDecimal(f, new MathContext(l + 3, RoundingMode.HALF_UP));
		return b.doubleValue();
	}

	// *********************** 字符串处理返回List ***********************
	public static List getReturnList(String sStr, String sDelim) {
		int i, j;// iDelimLen, iStrLen;
		if (sStr == null || sDelim == null) {
			return null;
		}
		List returnList = new ArrayList();
		try {
			// iDelimLen = sDelim.length();
			// iStrLen = sStr.length();
			String s1[] = sStr.split("[" + sDelim + "]");
			for (i = 0; i < s1.length; i++) {
				returnList.add(new String(s1[i]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return returnList;
	}

	// *********************** 读取文件内容 ***********************
	/**
	 * @author: likai
	 * @param fileName
	 * @return String
	 * @use: 读取文件内容 适用于读比较小的文件
	 * 
	 */
	public static String fileReader(String fileName) {
		String fileContent = null;
		FileInputStream fis = null;
		FileChannel fc = null;
		try {
			File file = new File(getRealPath() + fileName);
			// File file = new File(fileName);
			if (file.exists()) {
				fis = new FileInputStream(file);
				fc = fis.getChannel();
				ByteBuffer bb = ByteBuffer.allocate((int) fc.size());
				fc.read(bb);
				bb.flip();
				fileContent = new String(bb.array());
			} else {
				fileContent = "";
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (fc != null) {
					fc.close();
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
		return fileContent;
	}

	// *********************** 字符串转码 ***********************
	// 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
	public static final String US_ASCII = "US-ASCII";
	// ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
	public static final String ISO_8859_1 = "ISO-8859-1";
	// 8位 UCS 转换格式
	public static final String UTF_8 = "UTF-8";
	// 16位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
	public static final String UTF_16BE = "UTF-16BE";
	// 16位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
	public static final String UTF_16LE = "UTF-16LE";
	// 16位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
	public static final String UTF_16 = "UTF-16";
	// 中文超大字符集
	public static final String GBK = "GBK";

	/**
	 * @param str
	 *            待转换编码的字符串
	 * @param newCharset
	 *            目标编码
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = str.getBytes();
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/**
	 * @param str
	 *            待转换编码的字符串
	 * @param oldCharset
	 *            原编码
	 * @param newCharset
	 *            目标编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String changeCharset(String str, String oldCharset, String newCharset)
			throws UnsupportedEncodingException {
		if (str != null) {
			// 用旧的字符编码解码字符串。解码可能会出现异常。
			byte[] bs = str.getBytes(oldCharset);
			// 用新的字符编码生成字符串
			return new String(bs, newCharset);
		}
		return null;
	}

	/** 将一个字符串按传入符号分段，并返回一个List集合,sStr格式为：abc&def&xyz& */
	public static List getFields(String sStr, String sDelim) {
		int i, j, iDelimLen, iStrLen;
		if (sStr == null || sDelim == null) {
			return null;
		}
		List myList = new ArrayList();
		/*
		 * String s1[] = sStr.split("[,]"); for (int ii=0; ii< s1.length; ii++) { System.out.println("ii="+ii+s1[ii]);
		 * String s2[] = s1[ii].split("[|]"); for (int iii=0; iii< s2.length; iii++) {
		 * System.out.println("iii="+iii+s2[iii]); } }
		 */

		try {
			iDelimLen = sDelim.length();
			iStrLen = sStr.length();

			String s1[] = sStr.split("[" + sDelim + "]");
			for (i = 0; i < s1.length; i++) {
				myList.add(new String(s1[i]));

			} // end of for

		} catch (Exception e) {
			System.out.println("com.ectrip.util.Tools:" + e.toString());
			return null;
		} // end of catch
		return myList;
	}

	public static String getTodayString() {

		String nowString = "";
		String monthString = "";
		String dayString = "";

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		nowString = nowString + now.get(Calendar.YEAR) + "-";
		int month = now.get(Calendar.MONTH) + 1;
		if (month < 10) {
			monthString = "0" + month;

		} else {
			monthString = "" + month;

		}
		int day = now.get(Calendar.DATE);
		if (day < 10) {
			dayString = "0" + day;

		} else {
			dayString = "" + day;

		}

		nowString = nowString + monthString + "-" + dayString;

		return nowString;

	}

	/**
	 * 取当前时间
	 * 
	 * @return
	 */

	public static String getNowString() {

		String nowString = "";
		String monthString = "";
		String dayString = "";
		String hourString = "";
		String minString = "";
		String secString = "";

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		nowString = nowString + now.get(Calendar.YEAR) + "-";
		int month = now.get(Calendar.MONTH) + 1;
		if (month < 10) {
			monthString = "0" + month;

		} else {
			monthString = "" + month;

		}
		int day = now.get(Calendar.DATE);
		if (day < 10) {
			dayString = "0" + day;

		} else {
			dayString = "" + day;

		}

		nowString = nowString + monthString + "-" + dayString;

		// 新增时间
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			hourString = "0" + hour;

		} else {
			hourString = "" + hour;

		}

		nowString = nowString + " " + hourString;
		int min = now.get(Calendar.MINUTE);
		if (min < 10) {
			minString = "0" + min;

		} else {
			minString = "" + min;

		}
		nowString = nowString + ":" + minString;
		int sec = now.get(Calendar.SECOND);
		if (sec < 10) {
			secString = "0" + sec;

		} else {
			secString = "" + sec;

		}
		nowString = nowString + ":" + secString;

		return nowString;

	}

	/**
	 * @param inputStr
	 * @param increNumb
	 * @return 例如:输入参数 02001 和 1 ,返回结果是02002
	 */
	public static String getStrAdd(String inputStr, int increNumb) {
		String returnStr = "";

		int length = inputStr.length();
		int numb = Integer.parseInt(inputStr) + 1;
		String strnumb = String.valueOf(numb);
		if ((length - strnumb.length()) == 0) {
			returnStr = strnumb;
		}
		if ((length - strnumb.length()) == 1) {
			returnStr = "0" + strnumb;
		}
		if ((length - strnumb.length()) == 2) {
			returnStr = "00" + strnumb;
		}
		return returnStr;
	}

	public static String replace(String strSource, String strFrom, String strTo) {
		java.lang.String strDest = "";
		int intFromLen = strFrom.length();
		int intPos;
		if (strSource == null)
			strSource = "";
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			strDest = strDest + strSource.substring(0, intPos);
			strDest = strDest + strTo;
			strSource = strSource.substring(intPos + intFromLen);
		}
		strDest = strDest + strSource;

		return strDest;
	}

	public static String replace(String str, boolean title) {
		StringBuffer sb = new StringBuffer();
		str = str.trim();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case 13: // \r 回车符
				sb.append("<br>");
				break;
			/*
			 * case 32: // 空格符 sb.append("&nbsp;"); break;
			 */
			case 34: // " 双引号
				sb.append("&quot;");
				break;
			case 39: // ' 单引号
				sb.append((title == true) ? "&quot;" : "''");
				break;
			case 38: // & 连接符
				sb.append("&amp;");
				break;
			case 60: // < 小于号
				sb.append("&lt;");
				break;
			case 62: // > 大于号
				sb.append("&gt;");
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		return sb.toString();
	}

	public static String getNowTimeString() {

		String nowString = "";

		String hourString = "";
		String minString = "";
		String secString = "";

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

		// 新增时间
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			hourString = "0" + hour;

		} else {
			hourString = "" + hour;

		}

		nowString = hourString;
		int min = now.get(Calendar.MINUTE);
		if (min < 10) {
			minString = "0" + min;

		} else {
			minString = "" + min;

		}
		nowString = nowString + ":" + minString;
		int sec = now.get(Calendar.SECOND);
		if (sec < 10) {
			secString = "0" + sec;

		} else {
			secString = "" + sec;

		}
		nowString = nowString + ":" + secString;

		return nowString;

	}

	/**
	 * float 四舍五入进行比较
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static boolean float45(float one, float two) {
		BigDecimal a = new BigDecimal(one);
		BigDecimal b = new BigDecimal(two);

		double af = a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		double bankaf = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return af == bankaf;
	}

	/**
	 * float 四舍五入进行比较
	 * 
	 * @param one
	 * @param two
	 * @return
	 */
	public static boolean float45(double one, double two) {
		BigDecimal a = new BigDecimal(one);
		BigDecimal b = new BigDecimal(two);

		double af = a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		double bankaf = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return af == bankaf;
	}

	/**
	 * 验证是否整数
	 * 
	 * @param target
	 * @return
	 */
	public static boolean isnum(String target) {
		Pattern p = Pattern.compile("^[0-9]*$");
		boolean b = p.matcher(target).matches();
		if (b) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Describe:验证email
	 * 
	 * @auth:yangguang
	 * @param target
	 *            验证目标
	 * @return return:boolean Date:2011-9-23
	 */
	public static boolean isEmail(String target) {
		String check = "^([\\w-\\.]+)@[\\w-.]+(\\.?[a-zA-Z]{2,4}$)";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(target);
		boolean isMatched = matcher.matches();
		if (isMatched) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Describe:验证是否是日期 日期格式 2011-09-29
	 * 
	 * @auth:yangguang
	 * @param target
	 * @return return:boolean Date:2011-9-23
	 */
	public static boolean isDate(String target) {
		String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(target);
		boolean b = m.matches();
		return b;
	}

	/**
	 * 10 进制数转换成36进制，如果　
	 * 
	 * @param p_value
	 * @param p_num
	 *            　加 1,如果为　0 表示不加。
	 * @return
	 */
	public static String ConvertTo36Text(long p_value, int p_num) {

		// System.out.println(Long.toString(p_value + p_num , 36).toUpperCase());
		return Long.toString(p_value, 36).toUpperCase();
	}
	
	/**
	 * 10 进制数转换成36进制，如果　
	 * 
	 * @param p_value
	 * @param p_num
	 *            　加 1,如果为　0 表示不加。
	 * @return
	 */
	public static String ConvertTo10Text(long p_value, int p_num) {

		// System.out.println(Long.toString(p_value + p_num , 36).toUpperCase());
		return Long.toString(p_value, 10).toUpperCase();
	}

	/**
	 * 将36进制转换为10进制 在这个方法中有判断字线串是否符合要求。
	 * 
	 * @param str36
	 * @return
	 */
	public static Long Text36ToConvert(String str36) {

		// System.out.println(Long.toString(p_value + p_num , 36).toUpperCase());
		Pattern pattern = Pattern.compile("^[a-zA-z0-9]+$");
		Matcher m = pattern.matcher(str36);

		Long l = 0L;
		if (m.matches() == false) {
			return 0L;

		} else {
			l = Long.parseLong(str36, 36);

			return l;
		}

	}

	/**
	 * @return 2008-06-25 00:00:00
	 */
	public static String getDayTimes(Calendar now) {
		StringBuffer getDayTimes = new StringBuffer();

		getDayTimes.append(now.get(Calendar.YEAR) + "-");
		int month = now.get(Calendar.MONTH) + 1;
		if (month < 10)
			getDayTimes.append("0" + month + "-");
		else
			getDayTimes.append(month + "-");
		int day = now.get(Calendar.DATE);
		if (day < 10)
			getDayTimes.append("0" + day + " ");
		else
			getDayTimes.append(day + " ");
		int hour = now.get(Calendar.HOUR_OF_DAY);
		if (hour < 10)
			getDayTimes.append("0" + hour + ":");
		else
			getDayTimes.append(hour + ":");
		int minute = now.get(Calendar.MINUTE);
		if (minute < 10)
			getDayTimes.append("0" + minute + ":");
		else
			getDayTimes.append(minute + ":");
		int second = now.get(Calendar.SECOND);
		if (second < 10)
			getDayTimes.append("0" + second);
		else
			getDayTimes.append(second);
		return getDayTimes.toString();
	}

	/**
	 * 获取时间 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param days
	 *            2009-12-13 14:18:13
	 * @param numb
	 *            2
	 * @return 2009-12-13 14:20:13 return:String Date:2011-11-24
	 */
	public static String getTimes(String days, int numb) {
		int year = Integer.valueOf(days.substring(0, 4)).intValue();

		int mouth = Integer.valueOf(days.substring(5, 7)).intValue();
		int day = Integer.valueOf(days.substring(8, 10)).intValue();
		int hour = Integer.valueOf(days.substring(11, 13)).intValue();
		int min = Integer.valueOf(days.substring(14, 16)).intValue() + numb;
		int sec = Integer.valueOf(days.substring(17)).intValue();
		Calendar now = Calendar.getInstance();
		now.set(year, mouth - 1, day, hour, min, sec);
		String stryear = String.valueOf(now.get(Calendar.YEAR));
		String strmonth = String.valueOf((now.get(Calendar.MONTH) + 1));
		String strday = String.valueOf(now.get(Calendar.DAY_OF_MONTH));

		String returnStr = "";
		returnStr = stryear + "-";
		if (now.get(Calendar.MONTH) + 1 < 10) {
			returnStr = returnStr + "0" + strmonth + "-";
		}
		if (now.get(Calendar.DAY_OF_MONTH) < 10) {
			returnStr = returnStr + "0" + strday;
		}
		if (now.get(Calendar.HOUR_OF_DAY) < 10) {
			returnStr = returnStr + " 0" + String.valueOf((now.get(Calendar.HOUR_OF_DAY)));
		} else {
			returnStr = returnStr + " " + String.valueOf(now.get(Calendar.HOUR_OF_DAY));
		}
		if (now.get(Calendar.MINUTE) < 10) {
			returnStr = returnStr + ":0" + String.valueOf(now.get(Calendar.MINUTE));
		} else {
			returnStr = returnStr + ":" + String.valueOf(now.get(Calendar.MINUTE));
		}
		if (now.get(Calendar.SECOND) < 10) {
			returnStr = returnStr + ":0" + String.valueOf(now.get(Calendar.SECOND));
		} else {
			returnStr = returnStr + ":" + String.valueOf(now.get(Calendar.SECOND));
		}

		return returnStr;

	}

	/**
	 * 生成票号的最后两位。
	 * 
	 * @param ticketNo
	 * @return
	 */
	public static String ticketMakeMd5(String ticketNo) {

		String ls_str = (ticketNo.length() > 12 ? ticketNo.substring(0, 12) : ticketNo);
		EctripMd5 md5 = new EctripMd5(ls_str);
		md5.calc();
		ls_str = (ticketNo.length() > 12 ? ticketNo.substring(0, 12) : ticketNo)
				+ md5.toString().toUpperCase().substring(0, 2);
		return ls_str;
	}

	/**
	 * 检查票号和验证是否对的。
	 * 
	 * @param ticketNo
	 * @return
	 */
	public static int checkTicketMd5(String ticketNo) {

		if (ticketNo.length() != 14)
			return -1;

		String ls_str = ticketNo.substring(0, 12);
		String ls_strtemp = ticketNo.substring(12);
		EctripMd5 md5 = new EctripMd5(ls_str);
		md5.calc();
		if (ls_strtemp.equals(md5.toString().toUpperCase().substring(0, 2)))
			return 1;
		else
			return -1;

	}
	/**
	 * 将日期转换成 Calendar
	 * @param sd 日期字符串，格式为：YYYY-MM-DD
	 * @return Calendar 对象
	 */
	public static Calendar stringToCalendar(String sd) {

		Calendar cd = Calendar.getInstance();

		int year = Integer.valueOf(sd.substring(0, 4)).intValue();
		int month = Integer.valueOf(sd.substring(5, 7)).intValue();
		int date = Integer.valueOf(sd.substring(8, 10)).intValue();
		int hour = Integer.valueOf(sd.substring(11, 13)).intValue();
		int minute = Integer.valueOf(sd.substring(14, 16)).intValue();
		int second = Integer.valueOf(sd.substring(17, 19)).intValue();
		cd.set(year, month - 1, date, hour, minute, second);

		return cd;
	}
	
}