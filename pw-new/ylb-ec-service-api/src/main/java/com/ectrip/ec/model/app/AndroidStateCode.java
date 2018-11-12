package com.ectrip.ec.model.app;

public class AndroidStateCode {
	/**
	 * //0001:登录失败,用户不存在 0002:登录失败,密码错误  0003:无预定权限 0004:预付款不足  0000:支付成功 0100:支付失败
	 */
	
	public  final static  String LOGIN_NOUSER="0001";//
	
	public  final static  String LOGIN_PWDERROR="0002";
	
	public  final static  String LOGIN_CANTBOOK="0003";
	
	public  final static  String BOOK_BALANCE="0004";
	
	
	public final static String BOOK_SUCCESS="0000";
	
	public final static String BOOK_FAILTURE="0100";
	
	

}
