package com.ectrip.ticket.service.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * 字符串压缩 主要用于WEB SERVICE 进行数据传输
 *
 * @author LIJIN
 *
 */
public class StringZip {

	public static void main(String[] args) {

		/*
		 * // TODO Auto-generated method stub double f = -0.444112222f; BigDecimal a = new BigDecimal(f); double af =
		 * a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); System.out.print(af);
		 */
		String ls_str = "压缩后的454545666666666666666666666666666666666666666666666666666666666因顷历45454545454545串压缩后的串压缩后的串压缩后的串压缩后的串";
		System.out.println(ls_str.length());
		ls_str = StringZip.zipString(ls_str);
		System.out.println(ls_str.length());
		System.out.println(StringZip.unzipString(ls_str));
	}

	/**
	 * 字符串压缩
	 *
	 * @param str
	 *            源串
	 * @return 压缩后的串
	 */
	public static String zipString(String str) {
		try {
			ByteArrayOutputStream bos = null;
			GZIPOutputStream os = null;
			byte[] bs = null;
			try {
				bos = new ByteArrayOutputStream();
				os = new GZIPOutputStream(bos);
				os.write(str.getBytes());
				os.close();
				bos.close();
				bs = bos.toByteArray();
				// com.ectrip.util.Debug.print("zipstr="+new String(bs, "iso-8859-1"));

				return new String(bs, "iso-8859-1");
			} finally {
				bs = null;
				bos = null;
				os = null;
			}
		} catch (Exception ex) {
			return str;
		}
	}

	public static String zipStringB64(String str) {
		try {
			ByteArrayOutputStream bos = null;
			GZIPOutputStream os = null;
			byte[] bs = null;
			try {
				bos = new ByteArrayOutputStream();
				os = new GZIPOutputStream(bos);
				os.write(str.getBytes());
				os.close();
				bos.close();
				bs = bos.toByteArray();
				com.ectrip.base.util.Debug.print("bs length=" + bs.length);
				String ls_str = Base64.Base64Encode(bs);
				return ls_str;
			} finally {
				bs = null;
				bos = null;
				os = null;
			}
		} catch (Exception ex) {
			return str;
		}
	}

	/**
	 * 字符串解压,
	 *
	 * @param str
	 *            压缩过的串
	 * @return 解压串
	 */
	public static String unzipString(String str) {
		ByteArrayInputStream bis = null;
		ByteArrayOutputStream bos = null;
		GZIPInputStream is = null;
		byte[] buf = null;
		try {
			bis = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
			bos = new ByteArrayOutputStream();
			is = new GZIPInputStream(bis);
			buf = new byte[1024];
			int len;
			while ((len = is.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			is.close();
			bis.close();
			bos.close();
			return new String(bos.toByteArray());
		} catch (Exception ex) {
			return str;
		} finally {
			bis = null;
			bos = null;
			is = null;
			buf = null;
		}

	}

	/**
	 * 字符串解压,
	 *
	 * @param str
	 *            压缩过的串
	 * @return 解压串
	 */
	public static String unzipB64String(String str) {
		ByteArrayInputStream bis = null;
		ByteArrayOutputStream bos = null;
		GZIPInputStream is = null;
		byte[] buf = null;
		try {
			String tempstr = new String(Base64.Base64Decode(str), "ISO-8859-1");
			// bis = new ByteArrayInputStream(tempstr.getBytes("ISO-8859-1"));
			bis = new ByteArrayInputStream(tempstr.getBytes("ISO-8859-1"));

			bos = new ByteArrayOutputStream();
			is = new GZIPInputStream(bis);
			buf = new byte[1024];
			int len;
			while ((len = is.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			is.close();
			bis.close();
			bos.close();
			return new String(bos.toByteArray());
		} catch (Exception ex) {
			return str;
		} finally {
			bis = null;
			bos = null;
			is = null;
			buf = null;
		}
	}
}
