package com.ectrip.ec.model.rentrl;

import java.math.BigDecimal;

/**
 * �������޼�¼ entity. @author MyEclipse Persistence Tools
 */

public class Carticketrecordtab implements java.io.Serializable {

	// Fields

	private Long seq; //seq
	private String usid; //�û�id
 	private Long loanday; //����
	private String startdate;  //��ʼ����
	private String enddate; //��������
	private Double paymoney; //֧�����
	private Long status; //״̬(�ѻ���0 δ�� 1 ��ʱδ��ȡ 2 ����δ�黹 3����黹 4 δ��ȡ5 )
	private String dtmakedate; //��������
	private Double overtimeprice; //������
	private Double brokenprice; //�𻵽��
	private Long itickettypeid; //��Ʒid
	private Long note1; //�Ƿ����ͨ�� (δͨ�� 0 ͨ�� 1)
	private Long note2;
	private Long note3;
	private Long note4;
	private Long note5;
	private String chartext1;//���ƺš��쳵��д
	private String chartext2;
	private String chartext3;
	private String chartext4;
	private String chartext5;

	// Constructors

	/** default constructor */
	public Carticketrecordtab() {
	}

	/** minimal constructor */
	public Carticketrecordtab(Long seq, String usid, Long loanday,
			String startdate, String enddate, Double paymoney,
			Long status, String dtmakedate, Long itickettypeid) {
		this.seq = seq;
		this.usid = usid;
		this.loanday = loanday;
		this.startdate = startdate;
		this.enddate = enddate;
		this.paymoney = paymoney;
		this.status = status;
		this.dtmakedate = dtmakedate;
		this.itickettypeid = itickettypeid;
	}

	/** full constructor */
	public Carticketrecordtab(Long seq, String usid, Long loanday,
			String startdate, String enddate, Double paymoney,
			Long status, String dtmakedate, Long note1,
			Long note2, Long note3, Long note4,
			Long note5, String chartext1, String chartext2,
			String chartext3, String chartext4, String chartext5,
			Double overtimeprice, Double brokenprice, Long itickettypeid) {
		this.seq = seq;
		this.usid = usid;
		this.loanday = loanday;
		this.startdate = startdate;
		this.enddate = enddate;
		this.paymoney = paymoney;
		this.status = status;
		this.dtmakedate = dtmakedate;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.note5 = note5;
		this.chartext1 = chartext1;
		this.chartext2 = chartext2;
		this.chartext3 = chartext3;
		this.chartext4 = chartext4;
		this.chartext5 = chartext5;
		this.overtimeprice = overtimeprice;
		this.brokenprice = brokenprice;
		this.itickettypeid = itickettypeid;
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

	public Long getLoanday() {
		return this.loanday;
	}

	public void setLoanday(Long loanday) {
		this.loanday = loanday;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Double getPaymoney() {
		return this.paymoney;
	}

	public void setPaymoney(Double paymoney) {
		this.paymoney = paymoney;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getNote1() {
		return this.note1;
	}

	public void setNote1(Long note1) {
		this.note1 = note1;
	}

	public Long getNote2() {
		return this.note2;
	}

	public void setNote2(Long note2) {
		this.note2 = note2;
	}

	public Long getNote3() {
		return this.note3;
	}

	public void setNote3(Long note3) {
		this.note3 = note3;
	}

	public Long getNote4() {
		return this.note4;
	}

	public void setNote4(Long note4) {
		this.note4 = note4;
	}

	public Long getNote5() {
		return this.note5;
	}

	public void setNote5(Long note5) {
		this.note5 = note5;
	}

	public String getChartext1() {
		return this.chartext1;
	}

	public void setChartext1(String chartext1) {
		this.chartext1 = chartext1;
	}

	public String getChartext2() {
		return this.chartext2;
	}

	public void setChartext2(String chartext2) {
		this.chartext2 = chartext2;
	}

	public String getChartext3() {
		return this.chartext3;
	}

	public void setChartext3(String chartext3) {
		this.chartext3 = chartext3;
	}

	public String getChartext4() {
		return this.chartext4;
	}

	public void setChartext4(String chartext4) {
		this.chartext4 = chartext4;
	}

	public String getChartext5() {
		return this.chartext5;
	}

	public void setChartext5(String chartext5) {
		this.chartext5 = chartext5;
	}

	public Double getOvertimeprice() {
		return this.overtimeprice;
	}

	public void setOvertimeprice(Double overtimeprice) {
		this.overtimeprice = overtimeprice;
	}

	public Double getBrokenprice() {
		return this.brokenprice;
	}

	public void setBrokenprice(Double brokenprice) {
		this.brokenprice = brokenprice;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

}