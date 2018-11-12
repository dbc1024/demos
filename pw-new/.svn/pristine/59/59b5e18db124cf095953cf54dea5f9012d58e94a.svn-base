package com.ectrip.ticket.model.reserveorder;

/**
 * Reserveorderinfo entity. @author MyEclipse Persistence Tools
 */

public class Reserveorderinfo implements java.io.Serializable {

	// Fields

	private String orid;//订单号或者票号
	private String datajson;//数据JSON格式
	private String personname;//预约人
	private String dtmakedate;//操作时间
	//为做报表新增字段
	private String yydate;//日期
	private String rztime;//起始时间
	private String ldtime;//截止时间

	//非数据库字段
	private ReserveInfo reserveInfo;//datajson 转换的对象

	// Constructors

	/** default constructor */
	public Reserveorderinfo() {
	}

	/** minimal constructor */
	public Reserveorderinfo(String orid, String datajson,
							String personname) {
		this.orid = orid;
		this.datajson = datajson;
		this.personname = personname;
	}

	/** full constructor */
	public Reserveorderinfo(String orid, String datajson,
							String personname, String dtmakedate) {
		this.orid = orid;
		this.datajson = datajson;
		this.personname = personname;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public String getDatajson() {
		return this.datajson;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public void setDatajson(String datajson) {
		this.datajson = datajson;
	}

	public String getPersonname() {
		return this.personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public ReserveInfo getReserveInfo() {
		return reserveInfo;
	}

	public void setReserveInfo(ReserveInfo reserveInfo) {
		this.reserveInfo = reserveInfo;
	}

	public String getYydate() {
		return yydate;
	}

	public void setYydate(String yydate) {
		this.yydate = yydate;
	}

	public String getRztime() {
		return rztime;
	}

	public void setRztime(String rztime) {
		this.rztime = rztime;
	}

	public String getLdtime() {
		return ldtime;
	}

	public void setLdtime(String ldtime) {
		this.ldtime = ldtime;
	}
}