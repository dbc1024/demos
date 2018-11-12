 package com.ectrip.ticket.model.centersale;

import java.io.Serializable;


public class T_orderlist implements Serializable{
	public T_orderlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String orderlistid;//��ϸ��ˮ
	private String orid;
	private String iscenicid;
	private String itickettypeid;//��ƷID
	private String icrowdkindpriceid;//�ۼ�ID
	private String icrowdkindid;//��Ⱥ����ID
	private String dtstartdate;//��������
	private String dtenddate;//ʧЧ����
	private String starttime;// �A�s�_ʼ�r�g
    private String endtime;// �A�s�Y��r�g
	private String pric;//����PRIC
	private String numb;//����NUMB
	private String yhnumb;//�Ż�����
	private String amnt;//���AMNT
	private String yhamnt;//�Żݽ��
	private String ioffersschemeid;//�Żݷ���ID
	private String jsprice;
	private String isa;
	private String isb;
	private String isc;
	private String isd;
	private String ise;
	private String isf;
	private String isg;
	private String ish;
	private String isi;
	private String isj;
	private String notea;
	private String notej;
	private String notei;
	private String noteh;
	private String noteg;
	private String notef;
	private String notee;
	private String noted;
	private String notec;
	private String noteb;
	private String bymaketicketway;
	private String szticketprintno;//����Ʊ����ʱƱ��
	public String getOrid() {
		return orid;
	}
	public void setOrid(String orid) {
		this.orid = orid;
	}
	public String getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}

	
	public String getDtstartdate() {
		return dtstartdate;
	}
	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}
	public String getDtenddate() {
		return dtenddate;
	}
	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}
	public String getOrderlistid() {
		return orderlistid;
	}
	public void setOrderlistid(String orderlistid) {
		this.orderlistid = orderlistid;
	}
	public String getItickettypeid() {
		return itickettypeid;
	}
	public void setItickettypeid(String itickettypeid) {
		this.itickettypeid = itickettypeid;
	}
	public String getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}
	public void setIcrowdkindpriceid(String icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}
	public String getIcrowdkindid() {
		return icrowdkindid;
	}
	public void setIcrowdkindid(String icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}
	public String getPric() {
		return pric;
	}
	public void setPric(String pric) {
		this.pric = pric;
	}
	public String getNumb() {
		return numb;
	}
	public void setNumb(String numb) {
		this.numb = numb;
	}
	public String getYhnumb() {
		return yhnumb;
	}
	public void setYhnumb(String yhnumb) {
		this.yhnumb = yhnumb;
	}
	public String getAmnt() {
		return amnt;
	}
	public void setAmnt(String amnt) {
		this.amnt = amnt;
	}
	public String getYhamnt() {
		return yhamnt;
	}
	public void setYhamnt(String yhamnt) {
		this.yhamnt = yhamnt;
	}
	public String getIoffersschemeid() {
		return ioffersschemeid;
	}
	public void setIoffersschemeid(String ioffersschemeid) {
		this.ioffersschemeid = ioffersschemeid;
	}
	public String getIsa() {
		return isa;
	}
	public void setIsa(String isa) {
		this.isa = isa;
	}
	public String getIsb() {
		return isb;
	}
	public void setIsb(String isb) {
		this.isb = isb;
	}
	public String getIsc() {
		return isc;
	}
	public void setIsc(String isc) {
		this.isc = isc;
	}
	public String getIsd() {
		return isd;
	}
	public void setIsd(String isd) {
		this.isd = isd;
	}
	public String getIse() {
		return ise;
	}
	public void setIse(String ise) {
		this.ise = ise;
	}
	public String getIsf() {
		return isf;
	}
	public void setIsf(String isf) {
		this.isf = isf;
	}
	public String getIsg() {
		return isg;
	}
	public void setIsg(String isg) {
		this.isg = isg;
	}
	public String getIsh() {
		return ish;
	}
	public void setIsh(String ish) {
		this.ish = ish;
	}
	public String getIsi() {
		return isi;
	}
	public void setIsi(String isi) {
		this.isi = isi;
	}
	public String getIsj() {
		return isj;
	}
	public void setIsj(String isj) {
		this.isj = isj;
	}
	public String getNotea() {
		return notea;
	}
	public void setNotea(String notea) {
		this.notea = notea;
	}
	public String getNotej() {
		return notej;
	}
	public void setNotej(String notej) {
		this.notej = notej;
	}
	public String getNotei() {
		return notei;
	}
	public void setNotei(String notei) {
		this.notei = notei;
	}
	public String getNoteh() {
		return noteh;
	}
	public void setNoteh(String noteh) {
		this.noteh = noteh;
	}
	public String getNoteg() {
		return noteg;
	}
	public void setNoteg(String noteg) {
		this.noteg = noteg;
	}
	public String getNotef() {
		return notef;
	}
	public void setNotef(String notef) {
		this.notef = notef;
	}
	public String getNotee() {
		return notee;
	}
	public void setNotee(String notee) {
		this.notee = notee;
	}
	public String getNoted() {
		return noted;
	}
	public void setNoted(String noted) {
		this.noted = noted;
	}
	public String getNotec() {
		return notec;
	}
	public void setNotec(String notec) {
		this.notec = notec;
	}
	public String getNoteb() {
		return noteb;
	}
	public void setNoteb(String noteb) {
		this.noteb = noteb;
	}
	public T_orderlist(String orderlistid, String orid, String iscenicid,
			String starttime,String endtime,String itickettypeid, String icrowdkindpriceid,
			String icrowdkindid, String dtstartdate, String dtenddate, String pric,
			String numb, String yhnumb, String amnt, String yhamnt,
			String ioffersschemeid, String isa, String isb, String isc, String isd,
			String ise, String isf, String isg, String ish, String isi, String isj,
			String notea, String notej, String notei, String noteh, String noteg,
			String notef, String notee, String noted, String notec, String noteb,String jsprice,String bymaketicketway,String szticketprintno) {
		super();
		this.orderlistid = orderlistid;
		this.orid = orid;
		this.iscenicid = iscenicid;
	
		this.itickettypeid = itickettypeid;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.icrowdkindid = icrowdkindid;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.starttime=starttime;
		this.endtime=endtime;
		this.pric = pric;
		this.numb = numb;
		this.yhnumb = yhnumb;
		this.amnt = amnt;
		this.yhamnt = yhamnt;
		this.ioffersschemeid = ioffersschemeid;
		this.isa = isa;
		this.isb = isb;
		this.isc = isc;
		this.isd = isd;
		this.ise = ise;
		this.isf = isf;
		this.isg = isg;
		this.ish = ish;
		this.isi = isi;
		this.isj = isj;
		this.notea = notea;
		this.notej = notej;
		this.notei = notei;
		this.noteh = noteh;
		this.noteg = noteg;
		this.notef = notef;
		this.notee = notee;
		this.noted = noted;
		this.notec = notec;
		this.noteb = noteb;
		this.jsprice=jsprice;
		this.bymaketicketway=bymaketicketway;
		this.szticketprintno= szticketprintno;
	}
	public String getJsprice() {
		return jsprice;
	}
	public void setJsprice(String jsprice) {
		this.jsprice = jsprice;
	}
	
	public String getSzticketprintno() {
		return szticketprintno;
	}
	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}
	public String getBymaketicketway() {
		return bymaketicketway;
	}
	public void setBymaketicketway(String bymaketicketway) {
		this.bymaketicketway = bymaketicketway;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	

}

