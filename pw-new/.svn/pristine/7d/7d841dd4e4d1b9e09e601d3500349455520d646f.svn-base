package com.ectrip.base.util;

import java.math.BigDecimal;

public class MathUtil {

    public static long amplify2long(double v, int n){
        return new Long(amplify2int(v,n));
    }
    
	/**
     * 将浮点数放大后取整
     *
     * @param v 需放大的数字
     * @param n 10的倍数
     * @return 放大后取整的结果
     */
    public static int amplify2int(double v, int n) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.movePointRight(n).intValue();
    }
	/**
	 * 
	 * Describe:提供精确的加法运算
	 * @author:chenxinhao
	 * @param v1
	 * @param v2
	 * @return
	 * return:double
	 * Date:2014-11-4
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 
	 * Describe:提供精确的加法运算
	 * @author:chenxinhao
	 * @param v1
	 * @param v2
	 * @return 返回字符串
	 * return:String
	 * Date:2014-11-4
	 */
	public static String add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).toString();
	}
	
	/**
	 * 
	 * Describe:提供精确的减法运算
	 * @author:chenxinhao
	 * @param v1
	 * @param v2
	 * @return
	 * return:double
	 * Date:2014-11-4
	 */
	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 
	 * Describe:提供精确的减法运算
	 * @author:chenxinhao
	 * @param v1
	 * @param v2
	 * @return 返回字符串
	 * return:String
	 * Date:2014-11-4
	 */
	public static String subtract(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).toString();
	}
	
	/**
	 * 
	 * Describe:提供精确的乘法运算
	 * @author:chenxinhao
	 * @param v1
	 * @param v2
	 * @return
	 * return:double
	 * Date:2014-11-4
	 */
	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 
	 * Describe:提供精确的乘法运算
	 * @author:chenxinhao
	 * @param v1
	 * @param v2
	 * @return 返回字符串
	 * return:String
	 * Date:2014-11-4
	 */
	public static String multiply(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).toString();
	}
	
	/**
	 * 
	 * Describe:
	 * @author:chenxinhao
	 * @param v1
	 * @param v2
	 * @param scale  保留小数位数
	 * @param round_mode  取舍方式
	 * @return
	 * return:String
	 * Date:2014-11-4
	 */
	public static double divide(double v1, double v2, int scale, int round_mode){
		if(scale<0){
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, round_mode).doubleValue();
	}
	
	/**
	 * 
	 * Describe:
	 * @author:chenxinhao
	 * @param v1
	 * @param v2
	 * @param scale  保留小数位数
	 * @param round_mode  取舍方式
	 * @return
	 * return:String
	 * Date:2014-11-4
	 */
	public static String divide(String v1, String v2, int scale, int round_mode){
		if(scale<0){
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, round_mode).toString();
	} 
}
