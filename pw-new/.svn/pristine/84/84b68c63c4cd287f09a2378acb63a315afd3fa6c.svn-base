package com.ectrip.ticket.model.provider;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Edmbusinesstab entity. @author MyEclipse Persistence Tools
 * 业务类型
 */
@Entity
@Table
public class Edmbusinesstab implements java.io.Serializable {

	// Fields
	@Id
	private Long ibusinessid;//业务类型id
	private String szbusinesscode;//业务类型代码
	private String szbusinessname;//业务类型名称
	private String businesstype;//业务种类
	private Integer isregister;//是否可以前台注册业务
	private Integer bymarketingway;//销售方式（0独立，1共享）
	private Integer bywithmember;//拥有签约客户(0无，1个人会员，2机构会员，3机构会员+导游，)
	private Integer byconfirmemberway;//确认签约客户方式（0不需要，1刷卡确认，2密码确认，3刷卡+密码确认）
	private Integer btouristsaddr;//是否登录客源地（0不登记，1登记）
	private Integer bregsalesman;//登记业务员（0不登记，1登记）
	private Integer bteamnature;//登记团质（0不登记，1登记）
	private Integer bselfcollarselling;//自领自卖（0否，1是）
	private Integer bticketnounited;//票规则统一（0否，1是）
	private Integer byisuse;//使用状态
	private String szmemo;//备注

	//数据库中不存在的字段
	@Transient
	private String strbusinesstype;//业务种类名称


	/** default constructor */
	public Edmbusinesstab() {
	}

	/** minimal constructor */
	public Edmbusinesstab(Long ibusinessid, String szbusinesscode,
						  String szbusinessname, String businesstype, Integer isregister,
						  Integer bymarketingway, Integer bywithmember,
						  Integer byconfirmemberway, Integer btouristsaddr,
						  Integer bregsalesman, Integer bteamnature,
						  Integer bselfcollarselling, Integer bticketnounited,
						  Integer byisuse) {
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
	}

	/** full constructor */
	public Edmbusinesstab(Long ibusinessid, String szbusinesscode,
						  String szbusinessname, String businesstype, Integer isregister,
						  Integer bymarketingway, Integer bywithmember,
						  Integer byconfirmemberway, Integer btouristsaddr,
						  Integer bregsalesman, Integer bteamnature,
						  Integer bselfcollarselling, Integer bticketnounited,
						  Integer byisuse, String szmemo) {
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

	// Property accessors

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public String getSzbusinesscode() {
		return this.szbusinesscode;
	}

	public void setSzbusinesscode(String szbusinesscode) {
		this.szbusinesscode = szbusinesscode;
	}

	public String getSzbusinessname() {
		return this.szbusinessname;
	}

	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}

	public String getBusinesstype() {
		return this.businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public Integer getIsregister() {
		return this.isregister;
	}

	public void setIsregister(Integer isregister) {
		this.isregister = isregister;
	}

	public Integer getBymarketingway() {
		return this.bymarketingway;
	}

	public void setBymarketingway(Integer bymarketingway) {
		this.bymarketingway = bymarketingway;
	}

	public Integer getBywithmember() {
		return this.bywithmember;
	}

	public void setBywithmember(Integer bywithmember) {
		this.bywithmember = bywithmember;
	}

	public Integer getByconfirmemberway() {
		return this.byconfirmemberway;
	}

	public void setByconfirmemberway(Integer byconfirmemberway) {
		this.byconfirmemberway = byconfirmemberway;
	}

	public Integer getBtouristsaddr() {
		return this.btouristsaddr;
	}

	public void setBtouristsaddr(Integer btouristsaddr) {
		this.btouristsaddr = btouristsaddr;
	}

	public Integer getBregsalesman() {
		return this.bregsalesman;
	}

	public void setBregsalesman(Integer bregsalesman) {
		this.bregsalesman = bregsalesman;
	}

	public Integer getBteamnature() {
		return this.bteamnature;
	}

	public void setBteamnature(Integer bteamnature) {
		this.bteamnature = bteamnature;
	}

	public Integer getBselfcollarselling() {
		return this.bselfcollarselling;
	}

	public void setBselfcollarselling(Integer bselfcollarselling) {
		this.bselfcollarselling = bselfcollarselling;
	}

	public Integer getBticketnounited() {
		return this.bticketnounited;
	}

	public void setBticketnounited(Integer bticketnounited) {
		this.bticketnounited = bticketnounited;
	}

	public Integer getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Integer byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public String getStrbusinesstype() {
		return strbusinesstype;
	}

	public void setStrbusinesstype(String strbusinesstype) {
		this.strbusinesstype = strbusinesstype;
	}

}