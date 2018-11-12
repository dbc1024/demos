package com.ectrip.ec.model.report.sales;


/**
 * Rsaletickettab entity. @author MyEclipse Persistence Tools
 * �����̳�Ʊ����
 */

public class Rsaletickettab implements java.io.Serializable {

	// Fields

	private Long seq;//���
	private String rdate;//��
	private String rmonth;//��
	private String ryear;//��
	private String times;//����
	private Long iscenicid;//�����̱��
	private String szscenicname;//����������
	private Long numb;//���� �������Ż�����
	private String titype;//��������
	private Long itickettypeid;//��Ʒ���
	private String sztickettypename;//��Ʒ���� 
	private Long isa;//��Ⱥ����
	private Long isb;//վ����
	private Double dua;//����
	private Double dub;//�����ֶ�
	private String notea;//��Ⱥ��������
	private String noteb;//վ������
	private Double mont;//�����ֶ�  �����Żݽ��
	private Long isc;
	private Long isd;
	private Long ise;
	private Long isf;//�Ż�����
	private Double duc;
	private Double dud;
	private Double due;
	private Double duf;//�Żݽ��
	private String notec;
	private String noted;
	private String notee;
	private String notef;
	// Constructors

	/** default constructor */
	public Rsaletickettab() {
	}

	/** minimal constructor */
	public Rsaletickettab(Long seq, String rdate, String rmonth,
			String ryear, String times, Long iscenicid,
			String szscenicname, Long numb) {
		this.seq = seq;
		this.rdate = rdate;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.times = times;
		this.iscenicid = iscenicid;
		this.szscenicname = szscenicname;
		this.numb = numb;
	}

	/** full constructor */
	
	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public Rsaletickettab(Long seq, String rdate, String rmonth, String ryear,
			String times, Long iscenicid, String szscenicname, Long numb,
			String titype, Long itickettypeid, String sztickettypename,
			Long isa, Long isb, Double dua, Double dub, String notea,
			String noteb, Double mont, Long isc, Long isd, Long ise, Long isf,
			Double duc, Double dud, Double due, Double duf, String notec,
			String noted, String notee, String notef) {
		super();
		this.seq = seq;
		this.rdate = rdate;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.times = times;
		this.iscenicid = iscenicid;
		this.szscenicname = szscenicname;
		this.numb = numb;
		this.titype = titype;
		this.itickettypeid = itickettypeid;
		this.sztickettypename = sztickettypename;
		this.isa = isa;
		this.isb = isb;
		this.dua = dua;
		this.dub = dub;
		this.notea = notea;
		this.noteb = noteb;
		this.mont = mont;
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

	public Long getNumb() {
		return this.numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public String getTitype() {
		return this.titype;
	}

	public void setTitype(String titype) {
		this.titype = titype;
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

	public Double getMont() {
		return this.mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
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