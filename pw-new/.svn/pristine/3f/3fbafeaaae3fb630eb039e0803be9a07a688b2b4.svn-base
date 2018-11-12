package com.ectrip.base.enums;

public enum ZJTP {

	ZJTP1("01","身份证"),
	ZJTP2("02","导游证");
	
	private String pmcd;//参数码
	private String pmva;//参数值
	
	public static String getPmvaByPmcd(String pmcd) {
		ZJTP[] values = ZJTP.values();
		for (ZJTP zjtp : values) {
			
			if(pmcd.equals(zjtp.pmcd)) {
				return zjtp.pmva;
			}
		}
		
		return "";
	}
	
	private ZJTP(String pmcd, String pmva) {
		this.pmcd = pmcd;
		this.pmva = pmva;
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
