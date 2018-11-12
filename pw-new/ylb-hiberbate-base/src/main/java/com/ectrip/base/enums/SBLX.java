package com.ectrip.base.enums;

/**
 * 设备类型相关参数
 * 原系统中其pmky='SBLX'
 */
public enum SBLX {
	
	SBLX1("01", "检票终端"),
	SBLX2("02", "售票终端");

	
	private String pmcd;//参数码
	private String pmva;//参数值
	
	
	
	private SBLX(String pmcd, String pmva) {
		this.pmcd = pmcd;
		this.pmva = pmva;
	}
	
	public static String getPmvaByPmcd(String pmcd) {
		SBLX[] values = SBLX.values();
		
		for (SBLX sblx : values) {
			String pmcd2 = sblx.pmcd;
			if(pmcd.equals(pmcd2)) {
				return sblx.pmva;
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
