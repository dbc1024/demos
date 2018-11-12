package com.ectrip.ticket.model.provider;

import java.math.BigDecimal;

/**
 * Provider entity. @author MyEclipse Persistence Tools
 */

public class Provider implements java.io.Serializable {


	private Long pdno;//服务商编号
	private Long iparentid;//父编号
	private Long irootid;//根节点
	private String pdtp;//服务商类型
	private String ename;//服务商名称
	private String cname;//服务商编码
	private Long isvalid;//是否有效
	private String addr;//地址
	private Long isjd;//是否是景点
	private String telno;//电话
	private String dynetaddr;//网址

	//非数据库字段
	private String strtype;//服务类型名称
	private String strpdno;//上级服务商名称

	public Provider(Long pdno, Long iparentid, Long irootid,
					String pdtp, String ename, String cname, Long isvalid,
					String addr, Long isjd, String telno, String dynetaddr) {
		super();
		this.pdno = pdno;
		this.iparentid = iparentid;
		this.irootid = irootid;
		this.pdtp = pdtp;
		this.ename = ename;
		this.cname = cname;
		this.isvalid = isvalid;
		this.addr = addr;
		this.isjd = isjd;
		this.telno = telno;
		this.dynetaddr = dynetaddr;
	}

	public Provider() {
		super();
	}

	public Long getPdno() {
		return pdno;
	}

	public void setPdno(Long pdno) {
		this.pdno = pdno;
	}

	public Long getIparentid() {
		return iparentid;
	}

	public void setIparentid(Long iparentid) {
		this.iparentid = iparentid;
	}

	public Long getIrootid() {
		return irootid;
	}

	public void setIrootid(Long irootid) {
		this.irootid = irootid;
	}

	public String getPdtp() {
		return pdtp;
	}

	public void setPdtp(String pdtp) {
		this.pdtp = pdtp;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Long getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Long getIsjd() {
		return isjd;
	}

	public void setIsjd(Long isjd) {
		this.isjd = isjd;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getDynetaddr() {
		return dynetaddr;
	}

	public void setDynetaddr(String dynetaddr) {
		this.dynetaddr = dynetaddr;
	}

	public String getStrtype() {
		return strtype;
	}

	public void setStrtype(String strtype) {
		this.strtype = strtype;
	}

	public String getStrpdno() {
		return strpdno;
	}

	public void setStrpdno(String strpdno) {
		this.strpdno = strpdno;
	}



}