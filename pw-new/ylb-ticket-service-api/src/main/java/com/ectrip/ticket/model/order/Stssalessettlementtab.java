package com.ectrip.ticket.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Stssalessettlementtab entity.
 * 销售结算表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Stssalessettlementtab implements java.io.Serializable {

	// Fields
	@Id
	private StssalessettlementtabId id;
	private String settlementdata;
	private String settlementtime;
	private String isettlementid;
	private Double msettlementmoney;
	private Long iversion;
	private String dtmakedate;
	// Constructors

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	/** default constructor */
	public Stssalessettlementtab() {
	}

	/** full constructor */
	public Stssalessettlementtab(StssalessettlementtabId id,
			String settlementdata, String settlementtime, String isettlementid,
			Double msettlementmoney, Long iversion) {
		this.id = id;
		this.settlementdata = settlementdata;
		this.settlementtime = settlementtime;
		this.isettlementid = isettlementid;
		this.msettlementmoney = msettlementmoney;
		this.iversion = iversion;
	}

	// Property accessors

	public StssalessettlementtabId getId() {
		return this.id;
	}

	public void setId(StssalessettlementtabId id) {
		this.id = id;
	}

	public String getSettlementdata() {
		return this.settlementdata;
	}

	public void setSettlementdata(String settlementdata) {
		this.settlementdata = settlementdata;
	}

	public String getSettlementtime() {
		return this.settlementtime;
	}

	public void setSettlementtime(String settlementtime) {
		this.settlementtime = settlementtime;
	}

	public String getIsettlementid() {
		return this.isettlementid;
	}

	public void setIsettlementid(String isettlementid) {
		this.isettlementid = isettlementid;
	}

	public Double getMsettlementmoney() {
		return this.msettlementmoney;
	}

	public void setMsettlementmoney(Double msettlementmoney) {
		this.msettlementmoney = msettlementmoney;
	}

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

}