package com.ectrip.ticket.model.warehouse;

/**
 * Icofflinechecktab entity. @author MyEclipse Persistence Tools
 */

public class Icofflinechecktab implements java.io.Serializable {

	// Fields

	private Long seq;
	private String szticketprintno;//票号
	private Long igardengateid;//圆门ID
	private Long iwicketsetid;//闸机ID
	private String dtmakedate;//检票时间，也是同步时间
	private Long istrans;//是否同步上传，0 表没有上传，1 表示已经上传
	private String errlogs;//错误原因

	// Constructors

	/** default constructor */
	public Icofflinechecktab() {
	}

	/** minimal constructor */
	public Icofflinechecktab(Long seq, String szticketprintno,
							 Long igardengateid, String dtmakedate) {
		this.seq = seq;
		this.szticketprintno = szticketprintno;
		this.igardengateid = igardengateid;
		this.dtmakedate = dtmakedate;
	}

	/** full constructor */
	public Icofflinechecktab(Long seq, String szticketprintno,
							 Long igardengateid, Long iwicketsetid,
							 String dtmakedate, Long istrans, String errlogs) {
		this.seq = seq;
		this.szticketprintno = szticketprintno;
		this.igardengateid = igardengateid;
		this.iwicketsetid = iwicketsetid;
		this.dtmakedate = dtmakedate;
		this.istrans = istrans;
		this.errlogs = errlogs;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getSzticketprintno() {
		return this.szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

	public Long getIgardengateid() {
		return this.igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIwicketsetid() {
		return this.iwicketsetid;
	}

	public void setIwicketsetid(Long iwicketsetid) {
		this.iwicketsetid = iwicketsetid;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getIstrans() {
		return this.istrans;
	}

	public void setIstrans(Long istrans) {
		this.istrans = istrans;
	}

	public String getErrlogs() {
		return this.errlogs;
	}

	public void setErrlogs(String errlogs) {
		this.errlogs = errlogs;
	}

}