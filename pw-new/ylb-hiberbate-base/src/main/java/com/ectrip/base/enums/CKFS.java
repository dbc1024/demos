package com.ectrip.base.enums;

/**
* @ClassName: CKFS  
* @Description: TODO 介质类型  
* @author jiyong  
* @date 2018年5月21日  
*
 */
public enum CKFS {
	CKFS00("00","一维条码"),
	CKFS01("00","一维条码"),
	CKFS02("00","一维条码"),
	CKFS03("00","一维条码"),
	CKFS04("00","一维条码"),
	CKFS05("00","一维条码");
	private CKFS(String pmcd, String pmva) {
		this.pmcd = pmcd;
		this.pmva = pmva;
	}
	private String pmcd;//参数码
	private String pmva;//参数值
	
	public static String getPmvaByPmcd(String pmcd) {
		CKFS[] values = CKFS.values();
		for (CKFS ckfs : values) {
			if(pmcd.equals(ckfs.pmcd)) {
				return ckfs.pmva;
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
