package com.ectrip.ec.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="L_ZORDERLIST")
public class LZorderlist implements java.io.Serializable {
	@Id
	private LZorderlistId id;
	private Long icrowdkindpriceid;
	private Long icrowdkindid;
	private Long itickettypeid;
	private Long iztickettypeid;
	private String dtstartdate;
	private String dtenddate;
	private Long tripid;
	private Long ivenueid;
	private Long ivenueareaid;
	private Long ivenueseatsid;
	private Double zpric;

	private Long znumb;
	private Long zyhnumb;
	private Double zyhamnt;
	private Double zamnt;
	private Long isa;
	private Long isb;
	private Long isc;
	private Long isd;
	private Long ise;
	private Long isf;
	private Long isg;
	private Long ish;
	private Long isi;
	private Long isj;
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
	public Long getIsg() {
		return isg;
	}
	public void setIsg(Long isg) {
		this.isg = isg;
	}
	public Long getIsh() {
		return ish;
	}
	public void setIsh(Long ish) {
		this.ish = ish;
	}
	public Long getIsi() {
		return isi;
	}
	public void setIsi(Long isi) {
		this.isi = isi;
	}
	public Long getIsj() {
		return isj;
	}
	public void setIsj(Long isj) {
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
	public LZorderlistId getId() {
		return id;
	}
	public void setId(LZorderlistId id) {
		this.id = id;
	}
	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}
	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}
	public Long getIcrowdkindid() {
		return icrowdkindid;
	}
	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}
	public Long getItickettypeid() {
		return itickettypeid;
	}
	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}
	public Long getIztickettypeid() {
		return iztickettypeid;
	}
	public void setIztickettypeid(Long iztickettypeid) {
		this.iztickettypeid = iztickettypeid;
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
	public Long getTripid() {
		return tripid;
	}
	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}
	public Long getIvenueid() {
		return ivenueid;
	}
	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}
	public Long getIvenueareaid() {
		return ivenueareaid;
	}
	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}
	public Long getIvenueseatsid() {
		return ivenueseatsid;
	}
	public void setIvenueseatsid(Long ivenueseatsid) {
		this.ivenueseatsid = ivenueseatsid;
	}
	public Double getZpric() {
		return zpric;
	}
	public void setZpric(Double zpric) {
		this.zpric = zpric;
	}
	
	public Long getZnumb() {
		return znumb;
	}
	public void setZnumb(Long znumb) {
		this.znumb = znumb;
	}
	public Long getZyhnumb() {
		return zyhnumb;
	}
	public void setZyhnumb(Long zyhnumb) {
		this.zyhnumb = zyhnumb;
	}
	public Double getZyhamnt() {
		return zyhamnt;
	}
	public void setZyhamnt(Double zyhamnt) {
		this.zyhamnt = zyhamnt;
	}
	public Double getZamnt() {
		return zamnt;
	}
	public void setZamnt(Double zamnt) {
		this.zamnt = zamnt;
	}


}

