package com.ectrip.ec.model.user;

import java.math.BigDecimal;

/**
 * ������Ʊ��
 * Lingpiaouser entity. @author MyEclipse Persistence Tools
 */

public class Lingpiaouser implements java.io.Serializable {

	// Fields

	private Long seq;//����
	private String usid;//�û���
	private String username;//��Ʊ������
	private String zjlb;//֤�����
	private String zjhm;//֤������
	private String mobile;//��ϵ�绰
	private String faxno;//����
	private Integer isfirst;//�Ƿ�Ĭ��
	
	//�����ݿ��ֶ�
	private String strzjlb;//֤���������

	// Constructors

	/** default constructor */
	public Lingpiaouser() {
	}

	/** minimal constructor */
	public Lingpiaouser(Long seq, String usid, String username,
			String zjlb, String zjhm, String mobile) {
		this.seq = seq;
		this.usid = usid;
		this.username = username;
		this.zjlb = zjlb;
		this.zjhm = zjhm;
		this.mobile = mobile;
	}

	/** full constructor */
	public Lingpiaouser(Long seq, String usid, String username,
			String zjlb, String zjhm, String mobile, String faxno,Integer isfirst) {
		this.seq = seq;
		this.usid = usid;
		this.username = username;
		this.zjlb = zjlb;
		this.zjhm = zjhm;
		this.mobile = mobile;
		this.faxno = faxno;
		this.isfirst = isfirst;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZjlb() {
		return this.zjlb;
	}

	public void setZjlb(String zjlb) {
		this.zjlb = zjlb;
	}

	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFaxno() {
		return this.faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public Integer getIsfirst() {
		return isfirst;
	}

	public void setIsfirst(Integer isfirst) {
		this.isfirst = isfirst;
	}

	public String getStrzjlb() {
		return strzjlb;
	}

	public void setStrzjlb(String strzjlb) {
		this.strzjlb = strzjlb;
	}

}