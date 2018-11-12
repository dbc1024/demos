package com.ectrip.ticket.model.venuemarketing;

/**
 * Venueseats entity. @author MyEclipse Persistence Tools
 */

public class Venueseats implements java.io.Serializable {

	// Fields

	private Long seq;
	private Long ivenueseatsid;//场馆座位ID
	private Long ivenueareaid;//场馆区域ID
	private Long ivenueid;//场馆ID
	private String szvenueseatscode;//场馆座位代码
	private String szvenueseatsname;//场馆座位名称
	private Long irowserialnum;//行数
	private Long icolumnserialnum;//列数
	private Long byisuse;//是否启用
	private String szmemo;//备注
	private Long iversion;//版本
	private Long bisdelete;//是否删除
	private Long bishistory;//是否历史数据
	private String dtmakedate;//操作时间

	//非数据字段
	private String venueidname;		//场馆名称
	private String ivenueareaname;	//场馆区域名称
	private String status;//座位状态
	// Constructors

	/** default constructor */
	public Venueseats() {
	}

	/** minimal constructor */
	public Venueseats(Long seq, Long ivenueseatsid,
					  String szvenueseatscode, String szvenueseatsname,
					  Long irowserialnum, Long icolumnserialnum,
					  Long byisuse, Long iversion, Long bisdelete,
					  Long bishistory) {
		this.seq = seq;
		this.ivenueseatsid = ivenueseatsid;
		this.szvenueseatscode = szvenueseatscode;
		this.szvenueseatsname = szvenueseatsname;
		this.irowserialnum = irowserialnum;
		this.icolumnserialnum = icolumnserialnum;
		this.byisuse = byisuse;
		this.iversion = iversion;
		this.bisdelete = bisdelete;
		this.bishistory = bishistory;
	}

	/** full constructor */
	public Venueseats(Long seq, Long ivenueseatsid,
					  Long ivenueareaid, Long ivenueid,
					  String szvenueseatscode, String szvenueseatsname,
					  Long irowserialnum, Long icolumnserialnum,
					  Long byisuse, String szmemo, Long iversion,
					  Long bisdelete, Long bishistory, String dtmakedate) {
		this.seq = seq;
		this.ivenueseatsid = ivenueseatsid;
		this.ivenueareaid = ivenueareaid;
		this.ivenueid = ivenueid;
		this.szvenueseatscode = szvenueseatscode;
		this.szvenueseatsname = szvenueseatsname;
		this.irowserialnum = irowserialnum;
		this.icolumnserialnum = icolumnserialnum;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.bisdelete = bisdelete;
		this.bishistory = bishistory;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIvenueseatsid() {
		return this.ivenueseatsid;
	}

	public void setIvenueseatsid(Long ivenueseatsid) {
		this.ivenueseatsid = ivenueseatsid;
	}

	public Long getIvenueareaid() {
		return this.ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	public Long getIvenueid() {
		return this.ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public String getSzvenueseatscode() {
		return this.szvenueseatscode;
	}

	public void setSzvenueseatscode(String szvenueseatscode) {
		this.szvenueseatscode = szvenueseatscode;
	}

	public String getSzvenueseatsname() {
		return this.szvenueseatsname;
	}

	public void setSzvenueseatsname(String szvenueseatsname) {
		this.szvenueseatsname = szvenueseatsname;
	}

	public Long getIrowserialnum() {
		return this.irowserialnum;
	}

	public void setIrowserialnum(Long irowserialnum) {
		this.irowserialnum = irowserialnum;
	}

	public Long getIcolumnserialnum() {
		return this.icolumnserialnum;
	}

	public void setIcolumnserialnum(Long icolumnserialnum) {
		this.icolumnserialnum = icolumnserialnum;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

	public Long getBisdelete() {
		return this.bisdelete;
	}

	public void setBisdelete(Long bisdelete) {
		this.bisdelete = bisdelete;
	}

	public Long getBishistory() {
		return this.bishistory;
	}

	public void setBishistory(Long bishistory) {
		this.bishistory = bishistory;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getVenueidname() {
		return venueidname;
	}

	public void setVenueidname(String venueidname) {
		this.venueidname = venueidname;
	}

	public String getIvenueareaname() {
		return ivenueareaname;
	}

	public void setIvenueareaname(String ivenueareaname) {
		this.ivenueareaname = ivenueareaname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}