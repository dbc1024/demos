package com.ectrip.sys.model.syspar;


/**
 * Galsysparamsubtab entity. @author MyEclipse Persistence Tools
 * �����ֵ��ӱ�
 */

public class Galsysparamsubtab implements java.io.Serializable {

	// Fields

	private Long iparamsubid;//����
	private Long irootid;//���ڵ�id
	private Long iparentid;//���ڵ�id
	private Long ilevel;//��
	private Long ilevelsequence;//�����
	private String bisleaf;//�Ƿ�Ҷ�ӽڵ�
	private String szinnerid;//������ϵid
	private String szinnercode;//������ϵ����
	private String szinnername;//������ϵ����
	private String szparamname;//����
	private String szparamvalue;//����
	private Long byisuse;//ʹ��״̬
	private String szmemo;//��ע
	private Long iversion;//�汾

	// Constructors

	/** default constructor */
	public Galsysparamsubtab() {
	}

	/** full constructor */
	public Galsysparamsubtab(Long irootid, Long iparentid,
			Long ilevel, Long ilevelsequence, String bisleaf,
			String szinnerid, String szinnercode, String szinnername,
			String szparamname, String szparamvalue, Long byisuse,
			String szmemo, Long iversion) {
		this.irootid = irootid;
		this.iparentid = iparentid;
		this.ilevel = ilevel;
		this.ilevelsequence = ilevelsequence;
		this.bisleaf = bisleaf;
		this.szinnerid = szinnerid;
		this.szinnercode = szinnercode;
		this.szinnername = szinnername;
		this.szparamname = szparamname;
		this.szparamvalue = szparamvalue;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
	}

	// Property accessors

	public Long getIparamsubid() {
		return this.iparamsubid;
	}

	public void setIparamsubid(Long iparamsubid) {
		this.iparamsubid = iparamsubid;
	}

	public Long getIrootid() {
		return this.irootid;
	}

	public void setIrootid(Long irootid) {
		this.irootid = irootid;
	}

	public Long getIparentid() {
		return this.iparentid;
	}

	public void setIparentid(Long iparentid) {
		this.iparentid = iparentid;
	}

	public Long getIlevel() {
		return this.ilevel;
	}

	public void setIlevel(Long ilevel) {
		this.ilevel = ilevel;
	}

	public Long getIlevelsequence() {
		return this.ilevelsequence;
	}

	public void setIlevelsequence(Long ilevelsequence) {
		this.ilevelsequence = ilevelsequence;
	}

	public String getBisleaf() {
		return this.bisleaf;
	}

	public void setBisleaf(String bisleaf) {
		this.bisleaf = bisleaf;
	}

	public String getSzinnerid() {
		return this.szinnerid;
	}

	public void setSzinnerid(String szinnerid) {
		this.szinnerid = szinnerid;
	}

	public String getSzinnercode() {
		return this.szinnercode;
	}

	public void setSzinnercode(String szinnercode) {
		this.szinnercode = szinnercode;
	}

	public String getSzinnername() {
		return this.szinnername;
	}

	public void setSzinnername(String szinnername) {
		this.szinnername = szinnername;
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