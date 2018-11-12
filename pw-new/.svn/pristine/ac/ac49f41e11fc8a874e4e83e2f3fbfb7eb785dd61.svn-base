package com.ectrip.ec.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Userjf entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Userjf implements java.io.Serializable {

	// Fields
	@Id
	private Long seq;	//���
	private String usid;	//�û�ID
	private Long jfyear;	//���
	private String jftype;	//�������
	private Long points;	//����
	private Long isvalid;	//�Ƿ�����
	private String dtmakedate;	//����ʱ��

	// Constructors

	/** default constructor */
	public Userjf() {
	}

	/** minimal constructor */
	public Userjf(Long seq, String usid, Long points,
			Long isvalid) {
		this.seq = seq;
		this.usid = usid;
		this.points = points;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public Userjf(Long seq, String usid, Long jfyear,
			String jftype, Long points, Long isvalid,
			String dtmakedate) {
		this.seq = seq;
		this.usid = usid;
		this.jfyear = jfyear;
		this.jftype = jftype;
		this.points = points;
		this.isvalid = isvalid;
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

	public Long getJfyear() {
		return this.jfyear;
	}

	public void setJfyear(Long jfyear) {
		this.jfyear = jfyear;
	}

	public String getJftype() {
		return this.jftype;
	}

	public void setJftype(String jftype) {
		this.jftype = jftype;
	}

	public Long getPoints() {
		return this.points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Long getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Long isvalid) {
		this.isvalid = isvalid;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}