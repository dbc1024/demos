package com.ectrip.ticket.service.model;



/**
 * 业务类型
 * @author LiuJianwen
 *
 */
//Hibernate: select e
//dmbusines0_.IBUSINESSID as IBUSINES1_31_,
//edmbusines0_.SZBUSINESSCODE as SZBUSINE2_31_,
//edmbusines0_.SZBUSINESSNAME as SZBUSINE3_31_,
//edmbusines0_.BUSINESSTYPE as BUSINESS4_31_,
//edmbusines0_.ISREGISTER as ISREGISTER31_,
//edmbusines0_.BYMARKETINGWAY as BYMARKET6_31_,
//edmbusines0_.BYWITHMEMBER as BYWITHME7_31_,
//edmbusines0_.BYCONFIRMEMBERWAY as BYCONFIR8_31_,
//edmbusines0_.BTOURISTSADDR as BTOURIST9_31_,
//edmbusines0_.BREGSALESMAN as BREGSAL10_31_,
//edmbusines0_.BTEAMNATURE as BTEAMNA11_31_,
//edmbusines0_.BSELFCOLLARSELLING as BSELFCO12_31_,
//edmbusines0_.BTICKETNOUNITED as BTICKET13_31_,
//edmbusines0_.BYISUSE as BYISUSE31_,
//edmbusines0_.SZMEMO as SZMEMO31_
//from EDMBUSINESSTAB edmbusines0_ where edmbusines0_.BYISUSE=1

public class BusinessType {

	private static final long serialVersionUID = 1L;
	private String ibusinessid;//业务类型id
	private String szbusinesscode;//业务类型代码 01散客，02旅行社 03 接待 04VIP
	private String szbusinessname;//业务类型名称
	private String businesstype;//业务种类
	private String isregister;//是否可以前台注册业务
	private String bymarketingway;//销售方式（0独立，1共享）
	private String bywithmember;//拥有签约客户(0无，1个人会员，2机构会员，3机构会员+导游，)
	private String byconfirmemberway;//确认签约客户方式（0不需要，1刷卡确认，2密码确认，3刷卡+密码确认）
	private String btouristsaddr;//是否登录客源地（0不登记，1登记）
	private String bregsalesman;//登记业务员（0不登记，1登记）
	private String bteamnature;//登记团质（0不登记，1登记）
	private String bselfcollarselling;//自领自卖（0否，1是）
	private String bticketnounited;//票规则统一（0否，1是）
	private String byisuse;//使用状态
	private String szmemo;//备注


	public BusinessType() {
	}


	public BusinessType(String ibusinessid, String szbusinesscode,
						String szbusinessname, String businesstype, String isregister,
						String bymarketingway, String bywithmember,
						String byconfirmemberway, String btouristsaddr,
						String bregsalesman, String bteamnature, String bselfcollarselling,
						String bticketnounited, String byisuse, String szmemo) {
		super();
		this.ibusinessid = ibusinessid;
		this.szbusinesscode = szbusinesscode;
		this.szbusinessname = szbusinessname;
		this.businesstype = businesstype;
		this.isregister = isregister;
		this.bymarketingway = bymarketingway;
		this.bywithmember = bywithmember;
		this.byconfirmemberway = byconfirmemberway;
		this.btouristsaddr = btouristsaddr;
		this.bregsalesman = bregsalesman;
		this.bteamnature = bteamnature;
		this.bselfcollarselling = bselfcollarselling;
		this.bticketnounited = bticketnounited;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
	}


	public String getIbusinessid() {
		return ibusinessid;
	}


	public void setIbusinessid(String ibusinessid) {
		this.ibusinessid = ibusinessid;
	}


	public String getSzbusinesscode() {
		return szbusinesscode;
	}


	public void setSzbusinesscode(String szbusinesscode) {
		this.szbusinesscode = szbusinesscode;
	}


	public String getSzbusinessname() {
		return szbusinessname;
	}


	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}


	public String getBusinesstype() {
		return businesstype;
	}


	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}


	public String getIsregister() {
		return isregister;
	}


	public void setIsregister(String isregister) {
		this.isregister = isregister;
	}


	public String getBymarketingway() {
		return bymarketingway;
	}


	public void setBymarketingway(String bymarketingway) {
		this.bymarketingway = bymarketingway;
	}


	public String getBywithmember() {
		return bywithmember;
	}


	public void setBywithmember(String bywithmember) {
		this.bywithmember = bywithmember;
	}


	public String getByconfirmemberway() {
		return byconfirmemberway;
	}


	public void setByconfirmemberway(String byconfirmemberway) {
		this.byconfirmemberway = byconfirmemberway;
	}


	public String getBtouristsaddr() {
		return btouristsaddr;
	}


	public void setBtouristsaddr(String btouristsaddr) {
		this.btouristsaddr = btouristsaddr;
	}


	public String getBregsalesman() {
		return bregsalesman;
	}


	public void setBregsalesman(String bregsalesman) {
		this.bregsalesman = bregsalesman;
	}


	public String getBteamnature() {
		return bteamnature;
	}


	public void setBteamnature(String bteamnature) {
		this.bteamnature = bteamnature;
	}


	public String getBselfcollarselling() {
		return bselfcollarselling;
	}


	public void setBselfcollarselling(String bselfcollarselling) {
		this.bselfcollarselling = bselfcollarselling;
	}


	public String getBticketnounited() {
		return bticketnounited;
	}


	public void setBticketnounited(String bticketnounited) {
		this.bticketnounited = bticketnounited;
	}


	public String getByisuse() {
		return byisuse;
	}


	public void setByisuse(String byisuse) {
		this.byisuse = byisuse;
	}


	public String getSzmemo() {
		return szmemo;
	}


	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}


	@Override
	public String toString() {
		return  szbusinessname ;
	}



}