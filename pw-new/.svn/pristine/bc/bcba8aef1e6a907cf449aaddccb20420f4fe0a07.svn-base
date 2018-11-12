package com.ectrip.base.util;

import java.security.MessageDigest;
import java.util.Map;
import java.util.SortedMap;

import org.apache.commons.lang.StringUtils;

public class MD5Util {

	private MD5Util() {
		//hide default constructor
	}
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				return byteArrayToHexString(md.digest(origin.getBytes()));
			else
				return byteArrayToHexString(md.digest(origin.getBytes(charsetname)));
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static String MD5Encode(String origin) {
		return MD5Util.MD5Encode(origin,"UTF-8");
	}
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 鍒涘缓md5鎽樿,瑙勫垯鏄�:鎸夊弬鏁板悕绉癮-z鎺掑簭,閬囧埌绌哄�肩殑鍙傛暟涓嶅弬鍔犵鍚嶃��
	 */
	public static String createSign(SortedMap<String, Object> packageParams, String key, String charset) {
		StringBuilder sb = new StringBuilder();
		
		packageParams.remove("sign");
		packageParams.remove("key");

		for (Map.Entry<String,Object> entry : packageParams.entrySet()){
			if(entry.getValue() != null){
				String s = entry.getValue().toString();
				if(!StringUtils.isBlank(s)){
					sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
				}
			}
		}
//		packageParams.forEach((k,v) -> {
//			if (v != null) {
//				String s = v.toString();
//				if (!s.isEmpty()) {
//					sb.append(k).append("=").append(s).append("&");
//				}
//			}
//		});
		
		sb.append("key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), charset);
		return sign;
	}
	
	
}
