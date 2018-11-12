package com.ectrip.ec.model.usernumjf;

import java.math.BigDecimal;

/**
 * Usernumjflist entity. @author MyEclipse Persistence Tools
 */

public class Usernumjflist implements java.io.Serializable {

    // Fields

    private UsernumjflistId id;
    private BigDecimal nid;
    private BigDecimal iscenicid;//�����̱��
    private BigDecimal itickettypeid2;//��ƷID
    private BigDecimal itickettypeid;//�Żݲ�ƷID
    private String etdt;//���ֿ�ʼ����
    private String stdt2;//���ֽ�������
    private BigDecimal jflb;//�������
    private Double point;//��������
    private String stdt;//��������
    private BigDecimal isvalid;
    private String zusid;//֧���û�
    private String jftp;//�������� 03�»��� 04:�����
    //�����ֶ�
    private String month;
    private int  type;//1�»��� 2����� 
    private String salesjifen;//������Ҫ�Ļ���
    private String provider;//��������
    private String surplusjifen;//��ǰʣ�����
    // Constructors

    /** default constructor */
    public Usernumjflist() {
    }

    /** minimal constructor */
    public Usernumjflist(UsernumjflistId id, BigDecimal iscenicid, BigDecimal itickettypeid2, String etdt, String stdt2, BigDecimal jflb,
	    Double point, String stdt, BigDecimal isvalid) {
	this.id = id;
	this.iscenicid = iscenicid;
	this.itickettypeid2 = itickettypeid2;
	this.etdt = etdt;
	this.stdt2 = stdt2;
	this.jflb = jflb;
	this.point = point;
	this.stdt = stdt;
	this.isvalid = isvalid;
    }

    /** full constructor */
    public Usernumjflist(UsernumjflistId id, BigDecimal nid, BigDecimal iscenicid, BigDecimal itickettypeid2, BigDecimal itickettypeid,
	    String etdt, String stdt2, BigDecimal jflb, Double point, String stdt, BigDecimal isvalid, String zusid) {
	this.id = id;
	this.nid = nid;
	this.iscenicid = iscenicid;
	this.itickettypeid2 = itickettypeid2;
	this.itickettypeid = itickettypeid;
	this.etdt = etdt;
	this.stdt2 = stdt2;
	this.jflb = jflb;
	this.point = point;
	this.stdt = stdt;
	this.isvalid = isvalid;
	this.zusid = zusid;
    }

    // Property accessors

    public UsernumjflistId getId() {
	return this.id;
    }

    public void setId(UsernumjflistId id) {
	this.id = id;
    }

    public BigDecimal getNid() {
	return this.nid;
    }

    public void setNid(BigDecimal nid) {
	this.nid = nid;
    }

    public BigDecimal getIscenicid() {
	return this.iscenicid;
    }

    public void setIscenicid(BigDecimal iscenicid) {
	this.iscenicid = iscenicid;
    }

    public BigDecimal getItickettypeid2() {
	return this.itickettypeid2;
    }

    public void setItickettypeid2(BigDecimal itickettypeid2) {
	this.itickettypeid2 = itickettypeid2;
    }

    public BigDecimal getItickettypeid() {
	return this.itickettypeid;
    }

    public void setItickettypeid(BigDecimal itickettypeid) {
	this.itickettypeid = itickettypeid;
    }

    public String getEtdt() {
	return this.etdt;
    }

    public void setEtdt(String etdt) {
	this.etdt = etdt;
    }

    public String getStdt2() {
	return this.stdt2;
    }

    public void setStdt2(String stdt2) {
	this.stdt2 = stdt2;
    }

    public BigDecimal getJflb() {
	return this.jflb;
    }

    public void setJflb(BigDecimal jflb) {
	this.jflb = jflb;
    }

    public Double getPoint() {
	return this.point;
    }

    public void setPoint(Double point) {
	this.point = point;
    }

    public String getStdt() {
	return this.stdt;
    }

    public void setStdt(String stdt) {
	this.stdt = stdt;
    }

    public BigDecimal getIsvalid() {
	return this.isvalid;
    }

    public void setIsvalid(BigDecimal isvalid) {
	this.isvalid = isvalid;
    }

    public String getZusid() {
	return this.zusid;
    }

    public void setZusid(String zusid) {
	this.zusid = zusid;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSalesjifen() {
        return salesjifen;
    }

    public void setSalesjifen(String salesjifen) {
        this.salesjifen = salesjifen;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSurplusjifen() {
        return surplusjifen;
    }

    public void setSurplusjifen(String surplusjifen) {
        this.surplusjifen = surplusjifen;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getJftp() {
		return jftp;
	}

	public void setJftp(String jftp) {
		this.jftp = jftp;
	}
    
    

}