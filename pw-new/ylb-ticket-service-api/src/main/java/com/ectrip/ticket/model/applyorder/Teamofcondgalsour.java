package com.ectrip.ticket.model.applyorder;

import java.io.Serializable;

/**
 *
 * @author lijingrui
 */
public class Teamofcondgalsour implements Serializable {
	private Long tfgid;//主键
	private Long tfid;//成团条件id
	private Long iregionalid;//客源地id
	private String szregionalcode;//客源地编码
	private Long baifenb;//百分比
	private String note1;//备用1
	private Long inote1;//备用2

	public Teamofcondgalsour() {
		super();
	}
	public Teamofcondgalsour(Long tfgid, Long tfid, Long iregionalid,
							 String szregionalcode, Long baifenb, String note1, Long inote1) {
		super();
		this.tfgid = tfgid;
		this.tfid = tfid;
		this.iregionalid = iregionalid;
		this.szregionalcode = szregionalcode;
		this.baifenb = baifenb;
		this.note1 = note1;
		this.inote1 = inote1;
	}
	public Long getTfgid() {
		return tfgid;
	}
	public void setTfgid(Long tfgid) {
		this.tfgid = tfgid;
	}
	public Long getTfid() {
		return tfid;
	}
	public void setTfid(Long tfid) {
		this.tfid = tfid;
	}
	public Long getIregionalid() {
		return iregionalid;
	}
	public void setIregionalid(Long iregionalid) {
		this.iregionalid = iregionalid;
	}
	public String getSzregionalcode() {
		return szregionalcode;
	}
	public void setSzregionalcode(String szregionalcode) {
		this.szregionalcode = szregionalcode;
	}
	public Long getBaifenb() {
		return baifenb;
	}
	public void setBaifenb(Long baifenb) {
		this.baifenb = baifenb;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public Long getInote1() {
		return inote1;
	}
	public void setInote1(Long inote1) {
		this.inote1 = inote1;
	}



}
