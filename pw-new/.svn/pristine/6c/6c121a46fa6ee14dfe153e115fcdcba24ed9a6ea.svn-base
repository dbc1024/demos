package com.ectrip.base.enums;
/**
 * 设备状态相关参数
 * 原系统中其pmky='SBZT'
 *
 */
public enum SBZT {

	SBZT1("00", "总状态"),
	SBZT2("01", "条码阅读器"),
	SBZT3("02", "读卡器"),
	SBZT4("03", "指纹仪"),
	SBZT5("04", "身份证读卡器");
	
	private String pmcd;//参数码
	private String pmva;//参数值
	
	
	private SBZT(String pmcd, String pmva) {
		this.pmcd = pmcd;
		this.pmva = pmva;
	}
	
	public static String getPmvaByPmcd(String pmcd) {
		SBZT[] values = SBZT.values();
		
		for (SBZT sbzt : values) {
			String pmcd2 = sbzt.pmcd;
			if(pmcd.equals(pmcd2)) {
				return sbzt.pmva;
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
