package com.ectrip.ec.model.report.sales;

import java.math.BigDecimal;

/**
 * Rcgroupsalemonthtab entity. @author MyEclipse Persistence Tools
 * ǰ̨ע�������繺Ʊ�����ۣ������±�
 */

public class Rcgroupsalemonthtab implements java.io.Serializable {

	// Fields

	private Long seq;//����
	private String times;//����
	private String rmonth;//��
	private String ryear;//��
	private String usid;//�û����
	private String corpname;//��˾����
	private Long itickettypeid;//��ƷID
	private String sztickettypename;//��Ʒ����
	private Long numb;//����
	private Double mont;//���
	private Long isa;//��Ⱥ���� 1:���� 2:�˾���
	private Long isb;//2:������
	private Double dua;//����
	private Double dub;
	private String notea;//��Ⱥ��������
	private String noteb;//01:Ԥ�� 02:��Ʊ���˶�
	private Long isc;
	private Long isd;//0:ȫ�� 1:���� 2:�˾���
	private Long ise;
	private Long isf;
	private Double duc;
	private Double dud;
	private Double due;
	private Double duf;
	private String notec;
	private String noted;
	private String notee;
	private String notef;

	// Constructors

	/** default constructor */
	public Rcgroupsalemonthtab() {
	}

	/** minimal constructor */
	public Rcgroupsalemonthtab(Long seq, String times,
			Long itickettypeid, String sztickettypename, Long numb,
			Double mont) {
		this.seq = seq;
		this.times = times;
		this.itickettypeid = itickettypeid;
		this.sztickettypename = sztickettypename;
		this.numb = numb;
		this.mont = mont;
	}

	/** full constructor */
	

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public Rcgroupsalemonthtab(Long seq, String times, String rmonth,
			String ryear, String usid, String corpname, Long itickettypeid,
			String sztickettypename, Long numb, Double mont, Long isa,
			Long isb, Double dua, Double dub, String notea, String noteb,
			Long isc, Long isd, Long ise, Long isf, Double duc, Double dud,
			Double due, Double duf, String notec, String noted, String notee,
			String notef) {
		super();
		this.seq = seq;
		this.times = times;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.usid = usid;
		this.corpname = corpname;
		this.itickettypeid = itickettypeid;
		this.sztickettypename = sztickettypename;
		this.numb = numb;
		this.mont = mont;
		this.isa = isa;
		this.isb = isb;
		this.dua = dua;
		this.dub = dub;
		this.notea = notea;
		this.noteb = noteb;
		this.isc = isc;
		this.isd = isd;
		this.ise = ise;
		this.isf = isf;
		this.duc = duc;
		this.dud = dud;
		this.due = due;
		this.duf = duf;
		this.notec = notec;
		this.noted = noted;
		this.notee = notee;
		this.notef = notef;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getTimes() {
		return this.times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getRmonth() {
		return this.rmonth;
	}

	public void setRmonth(String rmonth) {
		this.rmonth = rmonth;
	}

	public String getRyear() {
		return this.ryear;
	}

	public void setRyear(String ryear) {
		this.ryear = ryear;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getCorpname() {
		return this.corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getSztickettypename() {
		return this.sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public Long getNumb() {
		return this.numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public Double getMont() {
		return this.mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
	}

	public Long getIsa() {
		return this.isa;
	}

	public void setIsa(Long isa) {
		this.isa = isa;
	}

	public Long getIsb() {
		return this.isb;
	}

	public void setIsb(Long isb) {
		this.isb = isb;
	}

	public Double getDua() {
		return this.dua;
	}

	public void setDua(Double dua) {
		this.dua = dua;
	}

	public Double getDub() {
		return this.dub;
	}

	public void setDub(Double dub) {
		this.dub = dub;
	}

	public String getNotea() {
		return this.notea;
	}

	public void setNotea(String notea) {
		this.notea = notea;
	}

	public String getNoteb() {
		return this.noteb;
	}

	public void setNoteb(String noteb) {
		this.noteb = noteb;
	}

	public Long getIsc() {
		return isc;
	}

	public void setIsc(Long isc) {
		this.isc = isc;
	}

	public Long getIsd() {
		return isd;
	}

	public void setIsd(Long isd) {
		this.isd = isd;
	}

	public Long getIse() {
		return ise;
	}

	public void setIse(Long ise) {
		this.ise = ise;
	}

	public Long getIsf() {
		return isf;
	}

	public void setIsf(Long isf) {
		this.isf = isf;
	}

	public Double getDuc() {
		return duc;
	}

	public void setDuc(Double duc) {
		this.duc = duc;
	}

	public Double getDud() {
		return dud;
	}

	public void setDud(Double dud) {
		this.dud = dud;
	}

	public Double getDue() {
		return due;
	}

	public void setDue(Double due) {
		this.due = due;
	}

	public Double getDuf() {
		return duf;
	}

	public void setDuf(Double duf) {
		this.duf = duf;
	}

	public String getNotec() {
		return notec;
	}

	public void setNotec(String notec) {
		this.notec = notec;
	}

	public String getNoted() {
		return noted;
	}

	public void setNoted(String noted) {
		this.noted = noted;
	}

	public String getNotee() {
		return notee;
	}

	public void setNotee(String notee) {
		this.notee = notee;
	}

	public String getNotef() {
		return notef;
	}

	public void setNotef(String notef) {
		this.notef = notef;
	}

}