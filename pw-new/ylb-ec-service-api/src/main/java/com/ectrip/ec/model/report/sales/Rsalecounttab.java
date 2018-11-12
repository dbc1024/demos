package com.ectrip.ec.model.report.sales;

import java.math.BigDecimal;

/**
 * Rsalecounttab entity. @author MyEclipse Persistence Tools
 * ��ƱԱ��Ʊ
 */

public class Rsalecounttab implements java.io.Serializable {

	// Fields

	private Long id;//����
	private String empid;//�û���
	private String szemployeename;//�û����� 
	private String rdate;//��
	private String rmonth;//��
	private String ryear;//��
	private String times;//����
	private String titype;//ʱ������(01�꣬02�£�03��)
	private Long iscenicid;//��˾���
	private String szscenicname;//��˾����
	private Long numb;//����
	private Double mont;//���
	private Long itickettypeid;//��Ʒ���
	private String sztickettypename;//��Ʒ����
	private String tickettype;//Ʊ����
	private String skfs;//�տʽ
	private String skfsname;//�տʽ����
	private Long isa;//��Ⱥ������
	private Long isb;//վ��ID
	private Double dua;//����
	private Double dub;  //
	private String notea;//��Ʊ/��Ʊ���ͣ�01��Ʊ02��Ʊ04��¼��
	private String noteb;//��Ⱥ��������
	private Long isc;	//��Ʊ����ID
	private Long isd;  //ҵ��ID
	private Long ise;	//�˴�
	private Long isf;  //��Դ��ID
	private Double duc;
	private Double dud;
	private Double due;//�Ż�����
	private Double duf;//�Żݽ��
	private String notec;//վ������
	private String noted;
	private String notee;//��Դ������
	private String notef; //�ͻ�ID

	// Constructors

	/** default constructor */
	public Rsalecounttab() {
	}

	/** minimal constructor */
	public Rsalecounttab(Long id, String rdate, String rmonth,
			String ryear, String times) {
		this.id = id;
		this.rdate = rdate;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.times = times;
	}

	
	

	public Rsalecounttab(Long id, String empid, String szemployeename,
			String rdate, String rmonth, String ryear, String times,
			String titype, Long iscenicid, String szscenicname, Long numb,
			Double mont, Long itickettypeid, String sztickettypename,
			String tickettype, String skfs, String skfsname, Long isa,
			Long isb, Double dua, Double dub, String notea, String noteb,
			Long isc, Long isd, Long ise, Long isf, Double duc, Double dud,
			Double due, Double duf, String notec, String noted, String notee,
			String notef) {
		super();
		this.id = id;
		this.empid = empid;
		this.szemployeename = szemployeename;
		this.rdate = rdate;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.times = times;
		this.titype = titype;
		this.iscenicid = iscenicid;
		this.szscenicname = szscenicname;
		this.numb = numb;
		this.mont = mont;
		this.itickettypeid = itickettypeid;
		this.sztickettypename = sztickettypename;
		this.tickettype = tickettype;
		this.skfs = skfs;
		this.skfsname = skfsname;
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

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getSzemployeename() {
		return this.szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
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

	public Double getMont() {
		return this.mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
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

	public String getTickettype() {
		return this.tickettype;
	}

	public void setTickettype(String tickettype) {
		this.tickettype = tickettype;
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

	public String getSkfs() {
		return skfs;
	}

	public void setSkfs(String skfs) {
		this.skfs = skfs;
	}

	public String getSkfsname() {
		return skfsname;
	}

	public void setSkfsname(String skfsname) {
		this.skfsname = skfsname;
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