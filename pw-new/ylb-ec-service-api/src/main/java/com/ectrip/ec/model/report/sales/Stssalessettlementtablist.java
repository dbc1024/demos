package com.ectrip.ec.model.report.sales;

/**
 * Stssalessettlementtablist entity. @author MyEclipse Persistence Tools
 */

public class Stssalessettlementtablist implements java.io.Serializable {

	// Fields

	private StssalessettlementtablistId id;
	private String settlementdata;
	private String settlementtime;
	private String isettlementid;
	private Double msettlementmoney;
	private Long iversion;
    private String dtmakedate;

	// Constructors

	/** default constructor */
	public Stssalessettlementtablist() {
	}

	/** full constructor */
	public Stssalessettlementtablist(StssalessettlementtablistId id,
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

	public StssalessettlementtablistId getId() {
		return this.id;
	}

	public void setId(StssalessettlementtablistId id) {
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

    public String getDtmakedate() {
        return dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }
}