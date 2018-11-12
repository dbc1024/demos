package com.ectrip.ticket.model.guide;

import java.math.BigDecimal;

/**
 * Stssguidecount entity. @author MyEclipse Persistence Tools
 */

/**
 *
 * 导游工作量表
 */
public class Stssguidecount implements java.io.Serializable {

	// Fields

	private Long seq;
	private String orid;
	private String szprintno;
	private String dstimes;
	private String dyname;
	private String zjhm;
	private String dtstartdate;
	private String dtenddate;
	private Double times;
	private Double prices;
	private Double fwprice;
	private Double managementfee;
	private Long int1;
	private Long int2;
	private Long int3;
	private Long int4;
	private Long int5;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	private String note5;

	// Constructors

	/** default constructor */
	public Stssguidecount() {
	}

	/** minimal constructor */
	public Stssguidecount(String orid) {
		this.orid = orid;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getSzprintno() {
		return szprintno;
	}

	public void setSzprintno(String szprintno) {
		this.szprintno = szprintno;
	}

	public String getDstimes() {
		return dstimes;
	}

	public void setDstimes(String dstimes) {
		this.dstimes = dstimes;
	}

	public String getDyname() {
		return dyname;
	}

	public void setDyname(String dyname) {
		this.dyname = dyname;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getDtstartdate() {
		return dtstartdate;
	}

	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}

	public String getDtenddate() {
		return dtenddate;
	}

	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}

	public Double getTimes() {
		return times;
	}

	public void setTimes(Double times) {
		this.times = times;
	}

	public Double getPrices() {
		return prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	public Double getFwprice() {
		return fwprice;
	}

	public void setFwprice(Double fwprice) {
		this.fwprice = fwprice;
	}

	public Double getManagementfee() {
		return managementfee;
	}

	public void setManagementfee(Double managementfee) {
		this.managementfee = managementfee;
	}

	public Long getInt1() {
		return int1;
	}

	public void setInt1(Long int1) {
		this.int1 = int1;
	}

	public Long getInt2() {
		return int2;
	}

	public void setInt2(Long int2) {
		this.int2 = int2;
	}

	public Long getInt3() {
		return int3;
	}

	public void setInt3(Long int3) {
		this.int3 = int3;
	}

	public Long getInt4() {
		return int4;
	}

	public void setInt4(Long int4) {
		this.int4 = int4;
	}

	public Long getInt5() {
		return int5;
	}

	public void setInt5(Long int5) {
		this.int5 = int5;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public String getNote5() {
		return note5;
	}

	public void setNote5(String note5) {
		this.note5 = note5;
	}

	public Stssguidecount(Long seq, String orid, String szprintno,
						  String dstimes, String dyname, String zjhm, String dtstartdate,
						  String dtenddate, Double times, Double prices, Double fwprice,
						  Double managementfee, Long int1, Long int2, Long int3, Long int4,
						  Long int5, String note1, String note2, String note3, String note4,
						  String note5) {
		super();
		this.seq = seq;
		this.orid = orid;
		this.szprintno = szprintno;
		this.dstimes = dstimes;
		this.dyname = dyname;
		this.zjhm = zjhm;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.times = times;
		this.prices = prices;
		this.fwprice = fwprice;
		this.managementfee = managementfee;
		this.int1 = int1;
		this.int2 = int2;
		this.int3 = int3;
		this.int4 = int4;
		this.int5 = int5;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.note5 = note5;
	}




}