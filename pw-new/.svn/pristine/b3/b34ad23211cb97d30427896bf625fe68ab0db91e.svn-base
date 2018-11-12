package com.ectrip.ticket.model.afcset;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 园门同检票管理表
 * @author luoxin
 */
@Entity
@Table
public class Esbgardengatetickettab {

	@Id
	private Long seq;
	private Long igardengateid;//园门id
	private Long itickettypeoneid;//产品id
	private Long itickettypetwoid;//产品id

	//预留字段
	private Long inote1;
	private Long inote2;
	private String note1;
	private String note2;

	private String dtmakedate;

	/***非数据库字段***/
	@Transient
	private Long iscenicid;
	@Transient
	private String szscenicname;
	@Transient
	private String szgardengatename;
	@Transient
	private String sztickettypeonename;
	@Transient
	private String sztickettypetwoname;

	public Esbgardengatetickettab(){
	}

	public Esbgardengatetickettab(Long seq, Long igardengateid, Long itickettypeoneid, Long itickettypetwoid, String dtmakedate){
		this.seq = seq;
		this.igardengateid = igardengateid;
		this.itickettypeoneid = itickettypeoneid;
		this.itickettypetwoid = itickettypetwoid;
		this.dtmakedate = dtmakedate;
	}

	public Esbgardengatetickettab(Long seq, Long igardengateid, Long itickettypeoneid, Long itickettypetwoid
			, Long inote1, Long inote2, String note1, String note2, String dtmakedate){
		this.seq = seq;
		this.igardengateid = igardengateid;
		this.itickettypeoneid = itickettypeoneid;
		this.itickettypetwoid = itickettypetwoid;
		this.inote1 = inote1;
		this.inote2 = inote2;
		this.note1 = note1;
		this.note2 = note2;
		this.dtmakedate = dtmakedate;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIgardengateid() {
		return igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getItickettypeoneid() {
		return itickettypeoneid;
	}

	public void setItickettypeoneid(Long itickettypeoneid) {
		this.itickettypeoneid = itickettypeoneid;
	}

	public Long getItickettypetwoid() {
		return itickettypetwoid;
	}

	public void setItickettypetwoid(Long itickettypetwoid) {
		this.itickettypetwoid = itickettypetwoid;
	}

	public Long getInote1() {
		return inote1;
	}

	public void setInote1(Long inote1) {
		this.inote1 = inote1;
	}

	public Long getInote2() {
		return inote2;
	}

	public void setInote2(Long inote2) {
		this.inote2 = inote2;
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

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getSzgardengatename() {
		return szgardengatename;
	}

	public void setSzgardengatename(String szgardengatename) {
		this.szgardengatename = szgardengatename;
	}

	public String getSztickettypeonename() {
		return sztickettypeonename;
	}

	public void setSztickettypeonename(String sztickettypeonename) {
		this.sztickettypeonename = sztickettypeonename;
	}

	public String getSztickettypetwoname() {
		return sztickettypetwoname;
	}

	public void setSztickettypetwoname(String sztickettypetwoname) {
		this.sztickettypetwoname = sztickettypetwoname;
	}


}

