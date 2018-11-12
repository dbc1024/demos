package com.ectrip.base.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 	服务商类别
 * 原系统中其pmky='PDTP'
 */
public enum PDTP {
	
	PDTP1("01", "****", "1", "景区", null),
	PDTP2("02", "01", "2", "观光车", "01"),
	PDTP3("03", "01", "2", "竹筏", "01"),
	PDTP4("04", "01", "2", "剧院", "01"),
	PDTP5("05", "01", "2", "索道", "01"),
	PDTP6("06", "****", "1", "酒店", null),
	PDTP7("07", "****", "1", "旅行社", null),
	PDTP8("08", "****", "1", "超市", null),
	PDTP9("09", "****", "1", "租赁公司",null),
	PDTP10("10", "****", "1", "租车",null),
	PDTP11("11", "10", "2", "成都出发？？九寨沟三天用车",null),
	PDTP12("169", "10", "2", "成都出发？？九寨沟三天用车",null),
	PDTP13("229", "10", "2", "九寨沟+黄龙+花湖+九曲黄河第一湾五天用车",null);
	
	private String pmcd;
	private String spmcd;
	private String systp;
	private String pmva;
	private String pmvb;
	private PDTP(String pmcd, String spmcd, String systp, String pmva, String pmvb) {
		this.pmcd = pmcd;
		this.spmcd = spmcd;
		this.systp = systp;
		this.pmva = pmva;
		this.pmvb = pmvb;
	}
	
	public static List<String>  getPmcdByPmcdOrSpmcd(String pmcd,String spmcd) {
		ArrayList<String> arrayList = new ArrayList<>();
		PDTP[] values = PDTP.values();
		for (PDTP pdtp : values) {
			String pmcd2 = pdtp.pmcd;
			String spmcd2 = pdtp.spmcd;
			if(pmcd.equals(pmcd2) || spmcd.equals(spmcd2)) {
				arrayList.add(pdtp.pmcd);
			}
		}
		return arrayList;
	}
	public String getPmcd() {
		return pmcd;
	}
	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}
	public String getSpmcd() {
		return spmcd;
	}
	public void setSpmcd(String spmcd) {
		this.spmcd = spmcd;
	}
	public String getSystp() {
		return systp;
	}
	public void setSystp(String systp) {
		this.systp = systp;
	}
	public String getPmva() {
		return pmva;
	}
	public void setPmva(String pmva) {
		this.pmva = pmva;
	}
	public String getPmvb() {
		return pmvb;
	}
	public void setPmvb(String pmvb) {
		this.pmvb = pmvb;
	}
	
}
