package com.ectrip.sys.model.syspar;


/**
 * Galsysparamtab entity. @author MyEclipse Persistence Tools
 * �����ֵ�
 */

public class Galsysparamtab implements java.io.Serializable {

	// Fields

	private Long iparamtypeid;//����
	private String szparamname;//����
	private String szparamvalue;//����
	private Long bcustom;//�Ƿ����Զ������
	private Long byisuse;//ʹ��״̬
	private String szmemo;//��ע
	private Long iversion;//�汾

	// Constructors

	/** default constructor */
	public Galsysparamtab() {
	}

	/** full constructor */
	public Galsysparamtab(String szparamname, String szparamvalue,
			Long bcustom, Long byisuse, String szmemo,
			Long iversion) {
		this.szparamname = szparamname;
		this.szparamvalue = szparamvalue;
		this.bcustom = bcustom;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
	}

	// Property accessors

	public Long getIparamtypeid() {
		return this.iparamtypeid;
	}

	public void setIparamtypeid(Long iparamtypeid) {
		this.iparamtypeid = iparamtypeid;
	}

	public String getSzparamname() {
		return this.szparamname;
	}

	public void setSzparamname(String szparamname) {
		this.szparamname = szparamname;
	}

	public String getSzparamvalue() {
		return this.szparamvalue;
	}

	public void setSzparamvalue(String szparamvalue) {
		this.szparamvalue = szparamvalue;
	}

	public Long getBcustom() {
		return this.bcustom;
	}

	public void setBcustom(Long bcustom) {
		this.bcustom = bcustom;
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