package com.ectrip.sys.model.syspar;

import java.util.Date;

/**
 * Galperiodsettab entity. @author MyEclipse Persistence Tools
 * �ڼ�����
 */

public class Galperiodsettab implements java.io.Serializable {

	// Fields

	private Long iperiodsetid;//����
	private Long inatureyear;//��Ȼ��
	private Long iperiodvalue;//�ڼ�ֵ
	private Date dtstartdate;//��ʼ����
	private Date dtenddate;//��������
	private Long bypoststate;//��������
	private Long byisuse;//ʹ��״̬
	private String szmemo;//��ע
	private Long iversion;//�汾

	// Constructors

	/** default constructor */
	public Galperiodsettab() {
	}

	/** full constructor */
	public Galperiodsettab(Long inatureyear, Long iperiodvalue,
			Date dtstartdate, Date dtenddate, Long bypoststate,
			Long byisuse, String szmemo, Long iversion) {
		this.inatureyear = inatureyear;
		this.iperiodvalue = iperiodvalue;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.bypoststate = bypoststate;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
	}

	// Property accessors

	public Long getIperiodsetid() {
		return this.iperiodsetid;
	}

	public void setIperiodsetid(Long iperiodsetid) {
		this.iperiodsetid = iperiodsetid;
	}

	public Long getInatureyear() {
		return this.inatureyear;
	}

	public void setInatureyear(Long inatureyear) {
		this.inatureyear = inatureyear;
	}

	public Long getIperiodvalue() {
		return this.iperiodvalue;
	}

	public void setIperiodvalue(Long iperiodvalue) {
		this.iperiodvalue = iperiodvalue;
	}

	public Date getDtstartdate() {
		return this.dtstartdate;
	}

	public void setDtstartdate(Date dtstartdate) {
		this.dtstartdate = dtstartdate;
	}

	public Date getDtenddate() {
		return this.dtenddate;
	}

	public void setDtenddate(Date dtenddate) {
		this.dtenddate = dtenddate;
	}

	public Long getBypoststate() {
		return this.bypoststate;
	}

	public void setBypoststate(Long bypoststate) {
		this.bypoststate = bypoststate;
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

}