package com.ectrip.sys.model.syspar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Webinfotab entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table
public class Webinfotab implements java.io.Serializable {

	// Fields

	@Id
	private Long seq;
	private Long iemployeeid;  //发送人ID
	private Long ihadder;		//接收人ID
	private String szmemo;
	private String reminddate;
	private Long note1;
	private Long note2;
	private Long note3;
	private Long note4;
	private Long note5;
	private String boextstr1;
	private String boextstr2;
	private String boextstr3;
	private String boextstr4;
	private String boextstr5;

	//非数据库字段
	@Transient
	private String szemployeename; //发送人名称
	@Transient
	private String szempname;      //接收人名称

	// Constructors

	/** default constructor */
	public Webinfotab() {
	}

	/** minimal constructor */
	public Webinfotab(Long seq, Long iemployeeid,
					  Long ihadder, String szmemo, String reminddate) {
		this.seq = seq;
		this.iemployeeid = iemployeeid;
		this.ihadder = ihadder;
		this.szmemo = szmemo;
		this.reminddate = reminddate;
	}

	/** full constructor */
	public Webinfotab(Long seq, Long iemployeeid,
					  Long ihadder, String szmemo, String reminddate,
					  Long note1, Long note2, Long note3,
					  Long note4, Long note5, String boextstr1,
					  String boextstr2, String boextstr3, String boextstr4,
					  String boextstr5) {
		this.seq = seq;
		this.iemployeeid = iemployeeid;
		this.ihadder = ihadder;
		this.szmemo = szmemo;
		this.reminddate = reminddate;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.note5 = note5;
		this.boextstr1 = boextstr1;
		this.boextstr2 = boextstr2;
		this.boextstr3 = boextstr3;
		this.boextstr4 = boextstr4;
		this.boextstr5 = boextstr5;
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

	public Long getIhadder() {
		return this.ihadder;
	}

	public void setIhadder(Long ihadder) {
		this.ihadder = ihadder;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public String getReminddate() {
		return this.reminddate;
	}

	public void setReminddate(String reminddate) {
		this.reminddate = reminddate;
	}

	public Long getNote1() {
		return this.note1;
	}

	public void setNote1(Long note1) {
		this.note1 = note1;
	}

	public Long getNote2() {
		return this.note2;
	}

	public void setNote2(Long note2) {
		this.note2 = note2;
	}

	public Long getNote3() {
		return this.note3;
	}

	public void setNote3(Long note3) {
		this.note3 = note3;
	}

	public Long getNote4() {
		return this.note4;
	}

	public void setNote4(Long note4) {
		this.note4 = note4;
	}

	public Long getNote5() {
		return this.note5;
	}

	public void setNote5(Long note5) {
		this.note5 = note5;
	}

	public String getBoextstr1() {
		return this.boextstr1;
	}

	public void setBoextstr1(String boextstr1) {
		this.boextstr1 = boextstr1;
	}

	public String getBoextstr2() {
		return this.boextstr2;
	}

	public void setBoextstr2(String boextstr2) {
		this.boextstr2 = boextstr2;
	}

	public String getBoextstr3() {
		return this.boextstr3;
	}

	public void setBoextstr3(String boextstr3) {
		this.boextstr3 = boextstr3;
	}

	public String getBoextstr4() {
		return this.boextstr4;
	}

	public void setBoextstr4(String boextstr4) {
		this.boextstr4 = boextstr4;
	}

	public String getBoextstr5() {
		return this.boextstr5;
	}

	public void setBoextstr5(String boextstr5) {
		this.boextstr5 = boextstr5;
	}

	public String getSzemployeename() {
		return szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}

	public String getSzempname() {
		return szempname;
	}

	public void setSzempname(String szempname) {
		this.szempname = szempname;
	}

}