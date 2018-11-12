package com.ectrip.ec.model.report;

import java.math.BigDecimal;

/**
 * ��Ʊƾ֤��ʷ. @author MyEclipse Persistence Tools
 */
public class Historysalesvouchertab implements java.io.Serializable {

	// Fields

	private Long hisid; // ������ʷƾ֤ID
	private Long iscenicid; // �����̱��
	private Long iticketwinid; // ��Ʊ����ID
	private Long ibusinessid; // ҵ������ID
	private Long byprintinvoice; // �Ƿ��ӡ
	private Long bysplitway; // ���˷�ʽ
	private Long bisreturn; // ������Ʊ
	private String bysalesvouchertype; // ����ƾ֤����
	private String sznetorderno; // ���϶�����
	private Long iforcedrefundid; // ǿ����Ʊ���
	private Long ireginoalid; // ��Դ��ID
	private Long isalesmanid; // ��ƱԱ���
	private String icustomersid; // �ͻ�ID
	private String dyusid; // ���α��
	private String tdlx; // �Ŷ�����
	private Double iaccounreceicable; // Ӧ�տ�
	private Double iacceptmoney; // ʵ�տ�
	private Double igivechange; // ����
	private Long ihandler; // ������
	private Long ipayear; // �տ���
	private Long imaker; // �Ƶ���
	private Long iauditor; // �����
	private Long iyear; // ��
	private Long imonth; // ��
	private Long iday; // ��
	private String dtmakedate; // �Ƶ�����
	private String dtauditdate; // �������
	private String bysalesvoucherstate; // ����ƾ֤״̬
	private Long bispay; // �Ƿ񽻿�
	private Long bispayee; // �Ƿ�ɿ�

	// Constructors

	/** default constructor */
	public Historysalesvouchertab() {
	}

	/** minimal constructor */
	public Historysalesvouchertab(Long hisid, Long iscenicid,
			Long iticketwinid, Long ibusinessid,
			Long isalesmanid) {
		this.hisid = hisid;
		this.iscenicid = iscenicid;
		this.iticketwinid = iticketwinid;
		this.ibusinessid = ibusinessid;
		this.isalesmanid = isalesmanid;
	}

	/** full constructor */
	public Historysalesvouchertab(Long hisid, Long iscenicid,
			Long iticketwinid, Long ibusinessid,
			Long byprintinvoice, Long bysplitway,
			Long bisreturn, String bysalesvouchertype,
			String sznetorderno, Long iforcedrefundid,
			Long ireginoalid, Long isalesmanid,
			String icustomersid, String dyusid, String tdlx,
			Double iaccounreceicable, Double iacceptmoney, Double igivechange,
			Long ihandler, Long ipayear, Long imaker,
			Long iauditor, Long iyear, Long imonth,
			Long iday, String dtmakedate, String dtauditdate,
			String bysalesvoucherstate, Long bispay, Long bispayee) {
		this.hisid = hisid;
		this.iscenicid = iscenicid;
		this.iticketwinid = iticketwinid;
		this.ibusinessid = ibusinessid;
		this.byprintinvoice = byprintinvoice;
		this.bysplitway = bysplitway;
		this.bisreturn = bisreturn;
		this.bysalesvouchertype = bysalesvouchertype;
		this.sznetorderno = sznetorderno;
		this.iforcedrefundid = iforcedrefundid;
		this.ireginoalid = ireginoalid;
		this.isalesmanid = isalesmanid;
		this.icustomersid = icustomersid;
		this.dyusid = dyusid;
		this.tdlx = tdlx;
		this.iaccounreceicable = iaccounreceicable;
		this.iacceptmoney = iacceptmoney;
		this.igivechange = igivechange;
		this.ihandler = ihandler;
		this.ipayear = ipayear;
		this.imaker = imaker;
		this.iauditor = iauditor;
		this.iyear = iyear;
		this.imonth = imonth;
		this.iday = iday;
		this.dtmakedate = dtmakedate;
		this.dtauditdate = dtauditdate;
		this.bysalesvoucherstate = bysalesvoucherstate;
		this.bispay = bispay;
		this.bispayee = bispayee;
	}

	// Property accessors

	public Long getHisid() {
		return this.hisid;
	}

	public void setHisid(Long hisid) {
		this.hisid = hisid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIticketwinid() {
		return this.iticketwinid;
	}

	public void setIticketwinid(Long iticketwinid) {
		this.iticketwinid = iticketwinid;
	}

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public Long getByprintinvoice() {
		return this.byprintinvoice;
	}

	public void setByprintinvoice(Long byprintinvoice) {
		this.byprintinvoice = byprintinvoice;
	}

	public Long getBysplitway() {
		return this.bysplitway;
	}

	public void setBysplitway(Long bysplitway) {
		this.bysplitway = bysplitway;
	}

	public Long getBisreturn() {
		return this.bisreturn;
	}

	public void setBisreturn(Long bisreturn) {
		this.bisreturn = bisreturn;
	}

	public String getBysalesvouchertype() {
		return this.bysalesvouchertype;
	}

	public void setBysalesvouchertype(String bysalesvouchertype) {
		this.bysalesvouchertype = bysalesvouchertype;
	}

	public String getSznetorderno() {
		return this.sznetorderno;
	}

	public void setSznetorderno(String sznetorderno) {
		this.sznetorderno = sznetorderno;
	}

	public Long getIforcedrefundid() {
		return this.iforcedrefundid;
	}

	public void setIforcedrefundid(Long iforcedrefundid) {
		this.iforcedrefundid = iforcedrefundid;
	}

	public Long getIreginoalid() {
		return this.ireginoalid;
	}

	public void setIreginoalid(Long ireginoalid) {
		this.ireginoalid = ireginoalid;
	}

	public Long getIsalesmanid() {
		return this.isalesmanid;
	}

	public void setIsalesmanid(Long isalesmanid) {
		this.isalesmanid = isalesmanid;
	}

	public String getIcustomersid() {
		return this.icustomersid;
	}

	public void setIcustomersid(String icustomersid) {
		this.icustomersid = icustomersid;
	}

	public String getDyusid() {
		return this.dyusid;
	}

	public void setDyusid(String dyusid) {
		this.dyusid = dyusid;
	}

	public String getTdlx() {
		return this.tdlx;
	}

	public void setTdlx(String tdlx) {
		this.tdlx = tdlx;
	}

	public Double getIaccounreceicable() {
		return this.iaccounreceicable;
	}

	public void setIaccounreceicable(Double iaccounreceicable) {
		this.iaccounreceicable = iaccounreceicable;
	}

	public Double getIacceptmoney() {
		return this.iacceptmoney;
	}

	public void setIacceptmoney(Double iacceptmoney) {
		this.iacceptmoney = iacceptmoney;
	}

	public Double getIgivechange() {
		return this.igivechange;
	}

	public void setIgivechange(Double igivechange) {
		this.igivechange = igivechange;
	}

	public Long getIhandler() {
		return this.ihandler;
	}

	public void setIhandler(Long ihandler) {
		this.ihandler = ihandler;
	}

	public Long getIpayear() {
		return this.ipayear;
	}

	public void setIpayear(Long ipayear) {
		this.ipayear = ipayear;
	}

	public Long getImaker() {
		return this.imaker;
	}

	public void setImaker(Long imaker) {
		this.imaker = imaker;
	}

	public Long getIauditor() {
		return this.iauditor;
	}

	public void setIauditor(Long iauditor) {
		this.iauditor = iauditor;
	}

	public Long getIyear() {
		return this.iyear;
	}

	public void setIyear(Long iyear) {
		this.iyear = iyear;
	}

	public Long getImonth() {
		return this.imonth;
	}

	public void setImonth(Long imonth) {
		this.imonth = imonth;
	}

	public Long getIday() {
		return this.iday;
	}

	public void setIday(Long iday) {
		this.iday = iday;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getDtauditdate() {
		return this.dtauditdate;
	}

	public void setDtauditdate(String dtauditdate) {
		this.dtauditdate = dtauditdate;
	}

	public String getBysalesvoucherstate() {
		return this.bysalesvoucherstate;
	}

	public void setBysalesvoucherstate(String bysalesvoucherstate) {
		this.bysalesvoucherstate = bysalesvoucherstate;
	}

	public Long getBispay() {
		return this.bispay;
	}

	public void setBispay(Long bispay) {
		this.bispay = bispay;
	}

	public Long getBispayee() {
		return this.bispayee;
	}

	public void setBispayee(Long bispayee) {
		this.bispayee = bispayee;
	}

}