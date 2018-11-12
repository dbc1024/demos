package com.ectrip.ticket.model.warehouse;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Kcstocksbilltab entity. @author MyEclipse Persistence Tools
 */

public class Kcstocksbilltab implements java.io.Serializable {

	// Fields

	private String szstocksbillid;
	private String szstocksbillcode;   //单据编号
	private Long bystockswayid;			//操作方式
	private Long istationinid;			//入库仓库
	private Long istationoutid;			//出库仓库
	private Long ihandler;			//经手人
	private Long imaker;			//制单人
	private Long iauditor;			//审核人
	private String dtmakedate;			//制单日期
	private Long bybillstate;			//单据状态
	private String szmemo;			//备注
	private Long int1;			//
	private Long int2;			//
	private String note1;			//
	private String note2;			//

	//非数据库字段
	private String empid;


	// Constructors

	/** default constructor */
	public Kcstocksbilltab() {
	}

	/** minimal constructor */
	public Kcstocksbilltab(String szstocksbillid, String szstocksbillcode,
						   Long bystockswayid, Long imaker, String dtmakedate) {
		this.szstocksbillid = szstocksbillid;
		this.szstocksbillcode = szstocksbillcode;
		this.bystockswayid = bystockswayid;
		this.imaker = imaker;
		this.dtmakedate = dtmakedate;
	}

	/** full constructor */
	public Kcstocksbilltab(String szstocksbillid, String szstocksbillcode,
						   Long bystockswayid, Long istationinid,
						   Long istationoutid, Long ihandler, Long imaker,
						   Long iauditor, String dtmakedate, Long bybillstate,
						   String szmemo, Long int1, Long int2, String note1,
						   String note2) {
		this.szstocksbillid = szstocksbillid;
		this.szstocksbillcode = szstocksbillcode;
		this.bystockswayid = bystockswayid;
		this.istationinid = istationinid;
		this.istationoutid = istationoutid;
		this.ihandler = ihandler;
		this.imaker = imaker;
		this.iauditor = iauditor;
		this.dtmakedate = dtmakedate;
		this.bybillstate = bybillstate;
		this.szmemo = szmemo;
		this.int1 = int1;
		this.int2 = int2;
		this.note1 = note1;
		this.note2 = note2;
	}

	// Property accessors

	public String getSzstocksbillid() {
		return this.szstocksbillid;
	}

	public void setSzstocksbillid(String szstocksbillid) {
		this.szstocksbillid = szstocksbillid;
	}

	public String getSzstocksbillcode() {
		return this.szstocksbillcode;
	}

	public void setSzstocksbillcode(String szstocksbillcode) {
		this.szstocksbillcode = szstocksbillcode;
	}

	public Long getBystockswayid() {
		return this.bystockswayid;
	}

	public void setBystockswayid(Long bystockswayid) {
		this.bystockswayid = bystockswayid;
	}

	public Long getIstationinid() {
		return this.istationinid;
	}

	public void setIstationinid(Long istationinid) {
		this.istationinid = istationinid;
	}

	public Long getIstationoutid() {
		return this.istationoutid;
	}

	public void setIstationoutid(Long istationoutid) {
		this.istationoutid = istationoutid;
	}

	public Long getIhandler() {
		return this.ihandler;
	}

	public void setIhandler(Long ihandler) {
		this.ihandler = ihandler;
	}

	public Long getImaker() {
		return this.imaker;
	}

	public void setImaker(Long imaker) {
		this.imaker = imaker;
	}

	public Long getIauditor() {
		return this.iauditor;
	}

	public void setIauditor(Long iauditor) {
		this.iauditor = iauditor;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getBybillstate() {
		return this.bybillstate;
	}

	public void setBybillstate(Long bybillstate) {
		this.bybillstate = bybillstate;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
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

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

}