package com.ectrip.ec.model.report.datereport;

import java.math.BigDecimal;

/**
 * Rproviderlisttab entity. @author MyEclipse Persistence Tools
 */

public class Rproviderlisttab implements java.io.Serializable {

	// Fields

	private Long seq;// ����
	private String rdate;// ��
	private String rmonth;// ��
	private String ryear;// ��
	private String times;// ����
	private String titype;// �������ͣ�01�գ�02�£�03�꣩
	private Long numb;// ����  �����Żݵ�
	private Long itickettypeid;// ��Ʒ
	private String ttypename;// ��Ʒ����
	private Double mont;// �ܽ��/֧����� �����Żݵ�
	private Long iscenicid;// �����̱��
	private String szscenicname;// ����������
	private Long icrowdkindid;// ��Ⱥ����Id
	private String szcrowdkindname;// ��Ⱥ��������
	private Double pric;// ����
	private String protype;// �տʽ(00�ֽ���Ʊ01��������99δ��Ʊ�˶�������)
	private String protypename;// �տ�����

	private Long isa;// �˶�����
	private Long isb;
	private Long isc;
	private Long isd;
	private Long ise;
	private Long isf;//�Ż�����
	private Double dua;
	private Double dub;// ������
	private Double duc;
	private Double dud;
	private Double due;
	private Double duf;//�Żݽ��
	private String notea;// ��ʽ��01�˶�02�˶���
	private String noteb;// ��ʽ����
	private String notec;
	private String noted;
	private String notee;
	private String notef;

	// Constructors

	/** default constructor */
	public Rproviderlisttab() {
	}

	/** minimal constructor */
	public Rproviderlisttab(Long seq, String rdate, String rmonth, String ryear, String times, Long itickettypeid, String ttypename, Double mont, Double pric) {
		this.seq = seq;
		this.rdate = rdate;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.times = times;
		this.itickettypeid = itickettypeid;
		this.ttypename = ttypename;
		this.mont = mont;
		this.pric = pric;
	}

	/** full constructor */

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public Rproviderlisttab(Long seq, String rdate, String rmonth, String ryear, String times, String titype, Long numb, Long itickettypeid, String ttypename, Double mont, Long iscenicid,
			String szscenicname, Long icrowdkindid, String szcrowdkindname, Double pric, String protype, String protypename, Long isa, Long isb, Long isc, Long isd, Long ise, Long isf, Double dua,
			Double dub, Double duc, Double dud, Double due, Double duf, String notea, String noteb, String notec, String noted, String notee, String notef) {
		super();
		this.seq = seq;
		this.rdate = rdate;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.times = times;
		this.titype = titype;
		this.numb = numb;
		this.itickettypeid = itickettypeid;
		this.ttypename = ttypename;
		this.mont = mont;
		this.iscenicid = iscenicid;
		this.szscenicname = szscenicname;
		this.icrowdkindid = icrowdkindid;
		this.szcrowdkindname = szcrowdkindname;
		this.pric = pric;
		this.protype = protype;
		this.protypename = protypename;
		this.isa = isa;
		this.isb = isb;
		this.isc = isc;
		this.isd = isd;
		this.ise = ise;
		this.isf = isf;
		this.dua = dua;
		this.dub = dub;
		this.duc = duc;
		this.dud = dud;
		this.due = due;
		this.duf = duf;
		this.notea = notea;
		this.noteb = noteb;
		this.notec = notec;
		this.noted = noted;
		this.notee = notee;
		this.notef = notef;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getRdate() {
		return this.rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
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

	public String getTimes() {
		return this.times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getTitype() {
		return this.titype;
	}

	public void setTitype(String titype) {
		this.titype = titype;
	}

	public Long getNumb() {
		return this.numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public Long getItickettypeid() {
		return itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public String getTtypename() {
		return this.ttypename;
	}

	public void setTtypename(String ttypename) {
		this.ttypename = ttypename;
	}

	public Double getMont() {
		return this.mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getSzscenicname() {
		return this.szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public Long getIcrowdkindid() {
		return this.icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public String getSzcrowdkindname() {
		return this.szcrowdkindname;
	}

	public void setSzcrowdkindname(String szcrowdkindname) {
		this.szcrowdkindname = szcrowdkindname;
	}

	public Double getPric() {
		return this.pric;
	}

	public void setPric(Double pric) {
		this.pric = pric;
	}

	public String getProtype() {
		return this.protype;
	}

	public void setProtype(String protype) {
		this.protype = protype;
	}

	public String getProtypename() {
		return this.protypename;
	}

	public void setProtypename(String protypename) {
		this.protypename = protypename;
	}

	public Long getIsa() {
		return isa;
	}

	public void setIsa(Long isa) {
		this.isa = isa;
	}

	public Long getIsb() {
		return isb;
	}

	public void setIsb(Long isb) {
		this.isb = isb;
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

	public Double getDua() {
		return dua;
	}

	public void setDua(Double dua) {
		this.dua = dua;
	}

	public Double getDub() {
		return dub;
	}

	public void setDub(Double dub) {
		this.dub = dub;
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

	public String getNotea() {
		return notea;
	}

	public void setNotea(String notea) {
		this.notea = notea;
	}

	public String getNoteb() {
		return noteb;
	}

	public void setNoteb(String noteb) {
		this.noteb = noteb;
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