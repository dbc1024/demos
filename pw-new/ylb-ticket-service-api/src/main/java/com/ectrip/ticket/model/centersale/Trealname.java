package com.ectrip.ticket.model.centersale;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="T_REALNAME")
@Entity
public class Trealname implements Serializable {
	public Trealname() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	private String seq;
	private String orid;
	private String itickettypeid;
	private String iscenicid;
	private String icrowdkindid;
	private String cname;
	private String idcard;
	private String zjtp;
	private String ischild;
	private String mbnumber;

	public Trealname(String seq, String orid, String itickettypeid,
			String iscenicid, String icrowdkindid, String cname, String idcard,
			String zjtp, String ischild, String mbnumber) {
		super();
		this.seq=seq;
		this.orid=orid;
		this.itickettypeid=itickettypeid;
		this.iscenicid=iscenicid;
		this.icrowdkindid=icrowdkindid;
		this.cname=cname;
		this.idcard=idcard;
		this.zjtp=zjtp;
		this.ischild=ischild;
		this.mbnumber=mbnumber;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(String itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getIcrowdkindid() {
		return icrowdkindid;
	}

	public void setIcrowdkindid(String icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getZjtp() {
		return zjtp;
	}

	public void setZjtp(String zjtp) {
		this.zjtp = zjtp;
	}

	public String getIschild() {
		return ischild;
	}

	public void setIschild(String ischild) {
		this.ischild = ischild;
	}

	public String getMbnumber() {
		return mbnumber;
	}

	public void setMbnumber(String mbnumber) {
		this.mbnumber = mbnumber;
	}

}
