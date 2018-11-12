package com.ectrip.sys.model.syspar;


import java.util.Date;

/**
 * Galcustomdatetab entity. @author MyEclipse Persistence Tools
 *  �������
 */

public class Galcustomdatetab implements java.io.Serializable {

	// Fields

	private Long icustomdateid;//����
	private Long bydatetype;//��������
	private Date dttimevalue;//ʱ����ֵ
	private Long idatevalue;//������ֵ
	private Long imonthvalue;//�·���ֵ
	private Long byagolater;//ǰ������
	private Long bycalculate;//���㷽ʽ
	private Long byisuse;//ʹ��״̬
	private String szmemo;//��ע
	private Long iversion;//�汾

	// Constructors

	/** default constructor */
	public Galcustomdatetab() {
	}

	/** full constructor */
	public Galcustomdatetab(Long bydatetype, Date dttimevalue,
			Long idatevalue, Long imonthvalue,
			Long byagolater, Long bycalculate, Long byisuse,
			String szmemo, Long iversion) {
		this.bydatetype = bydatetype;
		this.dttimevalue = dttimevalue;
		this.idatevalue = idatevalue;
		this.imonthvalue = imonthvalue;
		this.byagolater = byagolater;
		this.bycalculate = bycalculate;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
	}

	// Property accessors

	public Long getIcustomdateid() {
		return this.icustomdateid;
	}

	public void setIcustomdateid(Long icustomdateid) {
		this.icustomdateid = icustomdateid;
	}

	public Long getBydatetype() {
		return this.bydatetype;
	}

	public void setBydatetype(Long bydatetype) {
		this.bydatetype = bydatetype;
	}

	public Date getDttimevalue() {
		return this.dttimevalue;
	}

	public void setDttimevalue(Date dttimevalue) {
		this.dttimevalue = dttimevalue;
	}

	public Long getIdatevalue() {
		return this.idatevalue;
	}

	public void setIdatevalue(Long idatevalue) {
		this.idatevalue = idatevalue;
	}

	public Long getImonthvalue() {
		return this.imonthvalue;
	}

	public void setImonthvalue(Long imonthvalue) {
		this.imonthvalue = imonthvalue;
	}

	public Long getByagolater() {
		return this.byagolater;
	}

	public void setByagolater(Long byagolater) {
		this.byagolater = byagolater;
	}

	public Long getBycalculate() {
		return this.bycalculate;
	}

	public void setBycalculate(Long bycalculate) {
		this.bycalculate = bycalculate;
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