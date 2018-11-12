package com.ectrip.ticket.model.provider;

/**
 * Esbticketemployee entity. @author MyEclipse Persistence Tools
 */

public class Esbticketemployee implements java.io.Serializable {

	// Fields

	private Long seq;
	private Long iemployeeid;
	private Long iticketstationid;
	private String szmemo;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	private Long int1;
	private Long int2;
	private Long int3;
	private Long int4;
	
	
	private String szemployeename;
	private String szstationname;

	// Constructors

	public String getSzemployeename() {
		return szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}

	public String getSzstationname() {
		return szstationname;
	}

	public void setSzstationname(String szstationname) {
		this.szstationname = szstationname;
	}

	/** default constructor */
	public Esbticketemployee() {
	}

	/** minimal constructor */
	public Esbticketemployee(Long seq, Long iemployeeid,
			Long iticketstationid) {
		this.seq = seq;
		this.iemployeeid = iemployeeid;
		this.iticketstationid = iticketstationid;
	}

	/** full constructor */
	public Esbticketemployee(Long seq, Long iemployeeid,
			Long iticketstationid, String szmemo, String note1,
			String note2, String note3, String note4, Long int1,
			Long int2, Long int3, Long int4) {
		this.seq = seq;
		this.iemployeeid = iemployeeid;
		this.iticketstationid = iticketstationid;
		this.szmemo = szmemo;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.int1 = int1;
		this.int2 = int2;
		this.int3 = int3;
		this.int4 = int4;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public Long getIticketstationid() {
		return this.iticketstationid;
	}

	public void setIticketstationid(Long iticketstationid) {
		this.iticketstationid = iticketstationid;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return this.note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return this.note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public Long getInt1() {
		return this.int1;
	}

	public void setInt1(Long int1) {
		this.int1 = int1;
	}

	public Long getInt2() {
		return this.int2;
	}

	public void setInt2(Long int2) {
		this.int2 = int2;
	}

	public Long getInt3() {
		return this.int3;
	}

	public void setInt3(Long int3) {
		this.int3 = int3;
	}

	public Long getInt4() {
		return this.int4;
	}

	public void setInt4(Long int4) {
		this.int4 = int4;
	}

}