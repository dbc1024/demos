package com.ectrip.base.enums;

/**
* @ClassName: CPFS  
* @Description: TODO 出票方式  
* @author jiyong  
* @date 2018年5月21日  
*
*/
public enum CPFS {

	CPFS00("00","现场打印"),
	CPFS01("01","现场激活"),
	CPFS02("02","身份证出票"),
	CPFS03("03","自选票号");
	private CPFS(String pmcd, String pmva) {
		this.pmcd = pmcd;
		this.pmva = pmva;
	}
	private String pmcd;//参数码
	private String pmva;//参数值
	public static String getPmvaByPmcd(String pmcd) {
		CPFS[] values = CPFS.values();
		for (CPFS ckfs : values) {
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
