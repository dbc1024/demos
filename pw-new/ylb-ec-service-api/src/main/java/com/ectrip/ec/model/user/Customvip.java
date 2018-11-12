package com.ectrip.ec.model.user;

/**
 * Customvip entity. @author MyEclipse Persistence Tools
 */

public class Customvip implements java.io.Serializable {

	// Fields

	private String usid;       	//  �û���
	private String cvtype;		//	VIP����
	private Long cvnum;			//	VIP�û���������
	private String stdate;		//  ��ʼ����
	private String endate;		//	��������
	private Double cvmoney;		//  ���
	private Long byisuse;		//  �Ƿ�����
	private String szmemo;		//	��ע
	private Long cvterno1;		//  һ�깺�����
	private Long cvterno2;		//  ������� ����Ʊ���ʹ�ã�
	private Long cvterno3;		//
	private Long cvterno4;		//
	private Long cvterno5;		//
	private String cvtedest1;	//����
	private String cvtedest2;	//
	private String cvtedest3;	//
	private String cvtedest4;	//
	private String cvtedest5;	//
	
	//�������ֶ�
	
	private String pmva;

	// Constructors

	/** default constructor */
	public Customvip() {
	}

	/** minimal constructor */
	public Customvip(String usid, String cvtype, Long cvnum, String stdate, String endate, Long byisuse) {
		this.usid = usid;
		this.cvtype = cvtype;
		this.cvnum = cvnum;
		this.stdate = stdate;
		this.endate = endate;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Customvip(String usid, String cvtype, Long cvnum, String stdate, String endate,Double cvmoney, Long byisuse,
			String szmemo, Long cvterno1, Long cvterno2, Long cvterno3, Long cvterno4,
			Long cvterno5, String cvtedest1, String cvtedest2, String cvtedest3, String cvtedest4,
			String cvtedest5) {
		this.usid = usid;
		this.cvtype = cvtype;
		this.cvnum = cvnum;
		this.stdate = stdate;
		this.endate = endate;
		this.cvmoney=cvmoney;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.cvterno1 = cvterno1;
		this.cvterno2 = cvterno2;
		this.cvterno3 = cvterno3;
		this.cvterno4 = cvterno4;
		this.cvterno5 = cvterno5;
		this.cvtedest1 = cvtedest1;
		this.cvtedest2 = cvtedest2;
		this.cvtedest3 = cvtedest3;
		this.cvtedest4 = cvtedest4;
		this.cvtedest5 = cvtedest5;
	}

	// Property accessors

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getCvtype() {
		return this.cvtype;
	}

	public void setCvtype(String cvtype) {
		this.cvtype = cvtype;
	}

	public Long getCvnum() {
		return this.cvnum;
	}

	public void setCvnum(Long cvnum) {
		this.cvnum = cvnum;
	}

	public String getStdate() {
		return this.stdate;
	}

	public void setStdate(String stdate) {
		this.stdate = stdate;
	}

	public String getEndate() {
		return this.endate;
	}

	public void setEndate(String endate) {
		this.endate = endate;
	}

	public Double getCvmoney() {
		return cvmoney;
	}

	public void setCvmoney(Double cvmoney) {
		this.cvmoney = cvmoney;
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

	public Long getCvterno1() {
		return this.cvterno1;
	}

	public void setCvterno1(Long cvterno1) {
		this.cvterno1 = cvterno1;
	}

	public Long getCvterno2() {
		return this.cvterno2;
	}

	public void setCvterno2(Long cvterno2) {
		this.cvterno2 = cvterno2;
	}

	public Long getCvterno3() {
		return this.cvterno3;
	}

	public void setCvterno3(Long cvterno3) {
		this.cvterno3 = cvterno3;
	}

	public Long getCvterno4() {
		return this.cvterno4;
	}

	public void setCvterno4(Long cvterno4) {
		this.cvterno4 = cvterno4;
	}

	public Long getCvterno5() {
		return this.cvterno5;
	}

	public void setCvterno5(Long cvterno5) {
		this.cvterno5 = cvterno5;
	}

	public String getCvtedest1() {
		return this.cvtedest1;
	}

	public void setCvtedest1(String cvtedest1) {
		this.cvtedest1 = cvtedest1;
	}

	public String getCvtedest2() {
		return this.cvtedest2;
	}

	public void setCvtedest2(String cvtedest2) {
		this.cvtedest2 = cvtedest2;
	}

	public String getCvtedest3() {
		return this.cvtedest3;
	}

	public void setCvtedest3(String cvtedest3) {
		this.cvtedest3 = cvtedest3;
	}

	public String getCvtedest4() {
		return this.cvtedest4;
	}

	public void setCvtedest4(String cvtedest4) {
		this.cvtedest4 = cvtedest4;
	}

	public String getCvtedest5() {
		return this.cvtedest5;
	}

	public void setCvtedest5(String cvtedest5) {
		this.cvtedest5 = cvtedest5;
	}

	public String getPmva() {
		return pmva;
	}

	public void setPmva(String pmva) {
		this.pmva = pmva;
	}

}