package com.ectrip.base.enums;

/**
 * 短信发送控制相关参数
 * 原系统中其pmky='MESG'
 *
 */
public enum MESG {
	
	MESG1("0001", "订单保存"),
	MESG2("0002", "订单支付"),
	MESG3("0003", "订单审核"),
	MESG4("0004", "订单修改"),
	MESG5("0005", "停排退订"),
	MESG6("0006", "强制退订"),
	MESG7("0007", "申请单支付"),
	MESG8("0008", "申请单审核成功"),
	MESG9("0009", "申请单审核失败"),
	MESG10("0010", "注册验证码"),
	MESG11("0011", "全单退订"),
	MESG12("0012", "邮件"),
	MESG15("0015", "找回密码"),
	MESG16("0016", "酒店订单支付"),
	MESG17("0021", "分社下单"),
	MESG18("0025", "旅游卡购票");

	
	private String pmcd;//参数码
	private String pmva;//参数值
	
	
	
	private MESG(String pmcd, String pmva) {
		this.pmcd = pmcd;
		this.pmva = pmva;
	}
	
	public static String getPmvaByPmcd(String pmcd) {
		MESG[] values = MESG.values();
		
		for (MESG mesg : values) {
			String pmcd2 = mesg.pmcd;
			if(pmcd2.equals(pmcd)) {
				
				return mesg.pmva;
			}
		}
		
		return "";
	}
	
	public String getPmcd() {
		return pmcd;
	}
	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}
	public String getPmva() {
		return pmva;
	}
	public void setPmva(String pmva) {
		this.pmva = pmva;
	}
	
	
}
