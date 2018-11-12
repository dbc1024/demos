package com.ectrip.sys.model.syspar;

/**
 * Esbequiptypetab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Esbequiptypetab implements java.io.Serializable {

	// Fields

	private Long iequiptypeid;
	private Long iscenicid;
	private String szequiptypemodel;
	private String szequiptypename;
	private String szcommmode;
	private Long bycategorytype;
	private Long byisuse;
	private String szmemo;

	// Constructors

	/** default constructor */
	public Esbequiptypetab() {
	}

	/** minimal constructor */
	public Esbequiptypetab(Long iequiptypeid, String szequiptypemodel,
			String szequiptypename, String szcommmode, Long bycategorytype,
			Long byisuse) {
		this.iequiptypeid = iequiptypeid;
		this.szequiptypemodel = szequiptypemodel;
		this.szequiptypename = szequiptypename;
		this.szcommmode = szcommmode;
		this.bycategorytype = bycategorytype;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Esbequiptypetab(Long iequiptypeid, Long iscenicid,
			String szequiptypemodel, String szequiptypename, String szcommmode,
			Long bycategorytype, Long byisuse, String szmemo) {
		this.iequiptypeid = iequiptypeid;
		this.iscenicid = iscenicid;
		this.szequiptypemodel = szequiptypemodel;
		this.szequiptypename = szequiptypename;
		this.szcommmode = szcommmode;
		this.bycategorytype = bycategorytype;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
	}

	// Property accessors

	public Long getIequiptypeid() {
		return this.iequiptypeid;
	}

	public void setIequiptypeid(Long iequiptypeid) {
		this.iequiptypeid = iequiptypeid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzequiptypemodel() {
		return this.szequiptypemodel;
	}

	public void setSzequiptypemodel(String szequiptypemodel) {
		this.szequiptypemodel = szequiptypemodel;
	}

	public String getSzequiptypename() {
		return this.szequiptypename;
	}

	public void setSzequiptypename(String szequiptypename) {
		this.szequiptypename = szequiptypename;
	}

	public String getSzcommmode() {
		return this.szcommmode;
	}

	public void setSzcommmode(String szcommmode) {
		this.szcommmode = szcommmode;
	}

	public Long getBycategorytype() {
		return this.bycategorytype;
	}

	public void setBycategorytype(Long bycategorytype) {
		this.bycategorytype = bycategorytype;
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

}