package com.ectrip.base.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 	产品种类
 *  	原系统中其pmky='PRTP'
 *
 */
public enum PRTP {

	PRTP1("0001", "01", "2", "门票", "01"), 
	PRTP2("151", "01", "2", "通行卡", "01"), 
	PRTP3("150", "01", "2", "居民卡", "01"), 
	PRTP4("0042", "01", "2", "晚会", "01"), 
	PRTP5("0041", "01", "2", "餐票", "01"), 
	PRTP6("0040", "01", "2", "VIP年票", null), 
	PRTP7("0014", "01", "2", "年卡", "01"), 
	PRTP8("0013", "01", "2", "员工卡证", "01"), 
	PRTP9("0012", "01", "2", "船票", "03"), 
	PRTP10("0101", "01", "2", "一卡通消费项目", null), 
	PRTP11("0100", "01", "2", "一卡通卡片类型", "01"), 
	PRTP12("0099", "01", "2", "导游票", "01"),
	PRTP13("0002", "01", "2", "车票", "02"),
	PRTP14("0003", "01", "2", "竹筏", "03"),
	PRTP15("0004", "01", "2", "剧院票", "04"),
	PRTP16("0005", "01", "2", "索道票", "05"),
	PRTP17("0010", "01", "2", "套票", "01"),
	PRTP18("0011", "06", "2", "会议室", "06"),
	PRTP19("168", "06", "2", "三人间", "06"),
	PRTP20("120", "06", "2", "产品附加服务", "06"),
	PRTP21("119", "06", "2", "服务商附加服务", "06"),
	PRTP22("0031", "06", "2", "别墅", "06"),
	PRTP23("0030", "06", "2", "公寓", "06"),
	PRTP24("0029", "06", "2", "套间", "06"),
	PRTP25("0028", "06", "2", "六人间", "06"),
	PRTP26("0027", "06", "2", "四人间", "06"),
	PRTP27("0026", "06", "2", "标准间", "06"),
	PRTP28("0025", "06", "2", "大床间", "06"),
	PRTP29("0024", "06", "2", "双人间", "06"),
	PRTP30("0023", "06", "2", "单间", "06"),
	PRTP31("0032", "07", "2", "自助游", "07"),
	PRTP32("233", "07", "2", "跟团游", null),
	PRTP33("231", "07", "2", "自驾游", null),
	PRTP34("230", "07", "2", "周边短线游", null),
	PRTP35("0034", "07", "2", "出境游", "07"),
	PRTP36("0033", "07", "2", "国内游", "07"),
	PRTP37("0037", "08", "2", "本地特产", "08"),
	PRTP38("0039", "08", "2", "特色小吃", "08"),
	PRTP39("0038", "08", "2", "旅游纪念品", "08"),
	PRTP40("234", "09", "2", "租赁", null),
	PRTP41("169", "10", "2", "成都出发？？九寨沟三天用车", null),
	PRTP42("220", "10", "2", "成都出发？？九寨沟+黄龙或牟尼沟景区三天用车", null),
	PRTP43("221", "10", "2", "成都出发？？九寨沟四天用车[二次进沟]", null),
	PRTP44("222", "10", "2", "成都出发？？九寨沟+黄龙或牟尼沟景区四天用车[二次进沟]", null),
	PRTP45("223", "10", "2", "九黄机场单项接送机服务", null),
	PRTP46("224", "10", "2", "九寨沟三天用车", null),
	PRTP47("225", "10", "2", "九寨沟+黄龙或牟尼沟景区三天用车", null),
	PRTP48("226", "10", "2", "九寨沟四天用车[二次进沟]", null),
	PRTP49("227", "10", "2", "九寨沟+黄龙或牟尼沟景区四天用车", null),
	PRTP50("228", "10", "2", " 九寨沟+黄龙或牟尼沟景区四天用车[二次进沟]", null),
	PRTP51("229", "10", "2", " 九寨沟+黄龙+花湖+九曲黄河第一湾五天用车", null),
	PRTP52("232", "10", "2", " 保险", null),
	PRTP53("01", "****", "1", " 景区", null),
	PRTP54("06", "****", "1", " 酒店", null),
	PRTP55("07", "****", "1", " 线路", null),
	PRTP56("08", "****", "1", " 商品", null),
	PRTP57("09", "****", "1", " 租赁公司", null),
	PRTP58("10", "****", "1", " 租车", null);


	private String pmcd;
	private String spmcd;
	private String systp;
	private String pmva;
	private String pmvb;

	private PRTP(String pmcd, String spmcd, String systp, String pmva, String pmvb) {
		this.pmcd = pmcd;
		this.spmcd = spmcd;
		this.systp = systp;
		this.pmva = pmva;
		this.pmvb = pmvb;
	}

	public static List<String> getPmcdByPmvb(String pmvb) {
		ArrayList<String> arrayList = new ArrayList<>();
		PRTP[] values = PRTP.values();
		for (PRTP prtp : values) {
			String pmvb2 = prtp.pmvb;
			if(pmvb.equals(pmvb2)) {
				arrayList.add(prtp.pmcd);
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
