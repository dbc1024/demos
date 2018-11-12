package com.ectrip.ec.model.report;

/**
 * Guidenotice entity. @author MyEclipse Persistence Tools
 */

public class Guidenotice implements java.io.Serializable {

	// Fields

	private Long seq;//����
	private String szsalesvoucherno;//����ƾ֤��
	private String guidename;//��������
	private String cardno;//֤������
	private Long levels;//�ȼ�
	private Long beginaddress;//�����ص�
	private String begindate;//����ʱ��
	private Long beginemployeeid;//����ԱID
	private String beginemployeename;//����Ա����
	private Long endaddress;//����ص�
	private String enddate;//����ʱ��
	private Long endemployeeid;//����ԱID
	private String endemployeename;//����Ա����
	private String beginservicetime;//����ʼʱ��
	private String endservicetime;//�������ʱ��
	private Double totalservicetime;//�ܷ���ʱ��
	private Double servicefee;//�����
	private Double managementfee;//�����
	private Long inote1;//����
	private Long inote2;
	private Long inote3;
	private String note1;//�ο�����
	private String note2;//�����ص�����
	private String note3;//����ص�����

	// Constructors

	/** default constructor */
	public Guidenotice() {
	}

	/** minimal constructor */
	public Guidenotice(Long seq, String szsalesvoucherno) {
		this.seq = seq;
		this.szsalesvoucherno = szsalesvoucherno;
	}

	/** full constructor */
	public Guidenotice(Long seq, String szsalesvoucherno,
			String guidename, String cardno, Long levels,
			Long beginaddress, String begindate,
			Long beginemployeeid, String beginemployeename,
			Long endaddress, String enddate, Long endemployeeid,
			String endemployeename, String beginservicetime,
			String endservicetime, Double totalservicetime, Double servicefee,
			Double managementfee, Long inote1, Long inote2,
			Long inote3, String note1, String note2, String note3) {
		this.seq = seq;
		this.szsalesvoucherno = szsalesvoucherno;
		this.guidename = guidename;
		this.cardno = cardno;
		this.levels = levels;
		this.beginaddress = beginaddress;
		this.begindate = begindate;
		this.beginemployeeid = beginemployeeid;
		this.beginemployeename = beginemployeename;
		this.endaddress = endaddress;
		this.enddate = enddate;
		this.endemployeeid = endemployeeid;
		this.endemployeename = endemployeename;
		this.beginservicetime = beginservicetime;
		this.endservicetime = endservicetime;
		this.totalservicetime = totalservicetime;
		this.servicefee = servicefee;
		this.managementfee = managementfee;
		this.inote1 = inote1;
		this.inote2 = inote2;
		this.inote3 = inote3;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getSzsalesvoucherno() {
		return this.szsalesvoucherno;
	}

	public void setSzsalesvoucherno(String szsalesvoucherno) {
		this.szsalesvoucherno = szsalesvoucherno;
	}

	public String getGuidename() {
		return this.guidename;
	}

	public void setGuidename(String guidename) {
		this.guidename = guidename;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Long getLevels() {
		return this.levels;
	}

	public void setLevels(Long levels) {
		this.levels = levels;
	}

	public Long getBeginaddress() {
		return this.beginaddress;
	}

	public void setBeginaddress(Long beginaddress) {
		this.beginaddress = beginaddress;
	}

	public String getBegindate() {
		return this.begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public Long getBeginemployeeid() {
		return this.beginemployeeid;
	}

	public void setBeginemployeeid(Long beginemployeeid) {
		this.beginemployeeid = beginemployeeid;
	}

	public String getBeginemployeename() {
		return this.beginemployeename;
	}

	public void setBeginemployeename(String beginemployeename) {
		this.beginemployeename = beginemployeename;
	}

	public Long getEndaddress() {
		return this.endaddress;
	}

	public void setEndaddress(Long endaddress) {
		this.endaddress = endaddress;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Long getEndemployeeid() {
		return this.endemployeeid;
	}

	public void setEndemployeeid(Long endemployeeid) {
		this.endemployeeid = endemployeeid;
	}

	public String getEndemployeename() {
		return this.endemployeename;
	}

	public void setEndemployeename(String endemployeename) {
		this.endemployeename = endemployeename;
	}

	public String getBeginservicetime() {
		return this.beginservicetime;
	}

	public void setBeginservicetime(String beginservicetime) {
		this.beginservicetime = beginservicetime;
	}

	public String getEndservicetime() {
		return this.endservicetime;
	}

	public void setEndservicetime(String endservicetime) {
		this.endservicetime = endservicetime;
	}

	public Double getTotalservicetime() {
		return this.totalservicetime;
	}

	public void setTotalservicetime(Double totalservicetime) {
		this.totalservicetime = totalservicetime;
	}

	public Double getServicefee() {
		return this.servicefee;
	}

	public void setServicefee(Double servicefee) {
		this.servicefee = servicefee;
	}

	public Double getManagementfee() {
		return this.managementfee;
	}

	public void setManagementfee(Double managementfee) {
		this.managementfee = managementfee;
	}

	public Long getInote1() {
		return this.inote1;
	}

	public void setInote1(Long inote1) {
		this.inote1 = inote1;
	}

	public Long getInote2() {
		return this.inote2;
	}

	public void setInote2(Long inote2) {
		this.inote2 = inote2;
	}

	public Long getInote3() {
		return this.inote3;
	}

	public void setInote3(Long inote3) {
		this.inote3 = inote3;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return this.note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}
	

}