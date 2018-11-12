package com.ectrip.ec.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Userjflist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Userjflist implements java.io.Serializable {

	// Fields
	@Id
	private Long seq;	//���
	private String usid;	//�û�ID
	private Long jflb;		//�������1 ��ʾ���ӣ�-1 ��ʾ����
	private String sdate;	//ʱ��
	private Long jfgz;	//���ֹ���
	private Long points;	//����
	private String dtmakedate;	//����ʱ��
	private String orid;
	private String wxseq;

	// Constructors

	/** default constructor */
	public Userjflist() {
	}

	/** minimal constructor */
	public Userjflist(Long seq, String usid, Long jflb,
			String sdate, Long jfgz, Long points) {
		this.seq = seq;
		this.usid = usid;
		this.jflb = jflb;
		this.sdate = sdate;
		this.jfgz = jfgz;
		this.points = points;
	}

	/** full constructor */
	public Userjflist(Long seq, String usid, Long jflb,
			String sdate, Long jfgz, Long points, String dtmakedate) {
		this.seq = seq;
		this.usid = usid;
		this.jflb = jflb;
		this.sdate = sdate;
		this.jfgz = jfgz;
		this.points = points;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getJflb() {
		return this.jflb;
	}

	public void setJflb(Long jflb) {
		this.jflb = jflb;
	}

	public String getSdate() {
		return this.sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public Long getJfgz() {
		return this.jfgz;
	}

	public void setJfgz(Long jfgz) {
		this.jfgz = jfgz;
	}

	public Long getPoints() {
		return this.points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getWxseq() {
		return wxseq;
	}

	public void setWxseq(String wxseq) {
		this.wxseq = wxseq;
	}

}