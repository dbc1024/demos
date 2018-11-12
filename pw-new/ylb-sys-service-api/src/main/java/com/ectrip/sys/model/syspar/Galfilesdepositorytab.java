package com.ectrip.sys.model.syspar;

import java.util.Date;

/**
 * Galfilesdepositorytab entity. @author MyEclipse Persistence Tools
 * �ļ��洢
 */

public class Galfilesdepositorytab implements java.io.Serializable {

	// Fields

	private Long ifilesdepositoryid;//�ļ�Id
	private String szsourcefilename;//ԭʼ����
	private String szserverfilename;//�洢����
	private String szextendedname;//��չ��
	private String bsfilebinary;//�ļ���Ϣ
	private String szfileproperty;//�ļ�����
	private String szfilepath;//�ļ�·��
	private Long iemployee;//�ϴ���
	private Date dtdepositorydate;//�ϴ�����
	private Date dtinvalidationdate;//ʧЧ����
	private Long bydepository;//�洢��ʶ
	private String szmemo;//��ע
	private Long iversion;//�汾

	// Constructors

	/** default constructor */
	public Galfilesdepositorytab() {
	}

	/** minimal constructor */
	public Galfilesdepositorytab(String szsourcefilename,
			String szserverfilename, String szextendedname,
			String szfileproperty, String szfilepath, Long iemployee,
			Date dtdepositorydate, Date dtinvalidationdate,
			Long bydepository, String szmemo, Long iversion) {
		this.szsourcefilename = szsourcefilename;
		this.szserverfilename = szserverfilename;
		this.szextendedname = szextendedname;
		this.szfileproperty = szfileproperty;
		this.szfilepath = szfilepath;
		this.iemployee = iemployee;
		this.dtdepositorydate = dtdepositorydate;
		this.dtinvalidationdate = dtinvalidationdate;
		this.bydepository = bydepository;
		this.szmemo = szmemo;
		this.iversion = iversion;
	}

	/** full constructor */
	public Galfilesdepositorytab(String szsourcefilename,
			String szserverfilename, String szextendedname,
			String bsfilebinary, String szfileproperty, String szfilepath,
			Long iemployee, Date dtdepositorydate,
			Date dtinvalidationdate, Long bydepository, String szmemo,
			Long iversion) {
		this.szsourcefilename = szsourcefilename;
		this.szserverfilename = szserverfilename;
		this.szextendedname = szextendedname;
		this.bsfilebinary = bsfilebinary;
		this.szfileproperty = szfileproperty;
		this.szfilepath = szfilepath;
		this.iemployee = iemployee;
		this.dtdepositorydate = dtdepositorydate;
		this.dtinvalidationdate = dtinvalidationdate;
		this.bydepository = bydepository;
		this.szmemo = szmemo;
		this.iversion = iversion;
	}

	// Property accessors

	public Long getIfilesdepositoryid() {
		return this.ifilesdepositoryid;
	}

	public void setIfilesdepositoryid(Long ifilesdepositoryid) {
		this.ifilesdepositoryid = ifilesdepositoryid;
	}

	public String getSzsourcefilename() {
		return this.szsourcefilename;
	}

	public void setSzsourcefilename(String szsourcefilename) {
		this.szsourcefilename = szsourcefilename;
	}

	public String getSzserverfilename() {
		return this.szserverfilename;
	}

	public void setSzserverfilename(String szserverfilename) {
		this.szserverfilename = szserverfilename;
	}

	public String getSzextendedname() {
		return this.szextendedname;
	}

	public void setSzextendedname(String szextendedname) {
		this.szextendedname = szextendedname;
	}

	public String getBsfilebinary() {
		return this.bsfilebinary;
	}

	public void setBsfilebinary(String bsfilebinary) {
		this.bsfilebinary = bsfilebinary;
	}

	public String getSzfileproperty() {
		return this.szfileproperty;
	}

	public void setSzfileproperty(String szfileproperty) {
		this.szfileproperty = szfileproperty;
	}

	public String getSzfilepath() {
		return this.szfilepath;
	}

	public void setSzfilepath(String szfilepath) {
		this.szfilepath = szfilepath;
	}

	public Long getIemployee() {
		return this.iemployee;
	}

	public void setIemployee(Long iemployee) {
		this.iemployee = iemployee;
	}

	public Date getDtdepositorydate() {
		return this.dtdepositorydate;
	}

	public void setDtdepositorydate(Date dtdepositorydate) {
		this.dtdepositorydate = dtdepositorydate;
	}

	public Date getDtinvalidationdate() {
		return this.dtinvalidationdate;
	}

	public void setDtinvalidationdate(Date dtinvalidationdate) {
		this.dtinvalidationdate = dtinvalidationdate;
	}

	public Long getBydepository() {
		return this.bydepository;
	}

	public void setBydepository(Long bydepository) {
		this.bydepository = bydepository;
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