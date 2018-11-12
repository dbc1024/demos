package com.ectrip.ec.model.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="T_ZORDERLIST")
@Entity
public class TZorderlist implements java.io.Serializable {

	// Fields
	@Id
	private TZorderlistId id;
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
	private Double jsprice;//结算价
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
	
	@Transient
	private YZorderlist yzorderlist;
	public YZorderlist getYzorderlist() {
		return yzorderlist;
	}

	public void setYzorderlist(YZorderlist yzorderlist) {
		this.yzorderlist = yzorderlist;
	}

	//从联合主键中的字段拿到外边 以便查询使用
	@Transient
	private String stritickettypeid;
	@Transient
	private String stricrowdkindid;
	@Transient
	private Double tdfl;
	@Transient
	private Long sqnumber;
	@Transient
	private Double mhandcharge;
	@Transient
	private String iscenicid;
	@Transient
	private String orid;
	@Transient
	private List seatlist;
	@Transient
	private String seats;
	public List getSeatlist() {
		return seatlist;
	}

	public void setSeatlist(List seatlist) {
		this.seatlist = seatlist;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	/** default constructor */
	public TZorderlist() {
	}

	/** minimal constructor */
	public TZorderlist(TZorderlistId id, Long icrowdkindpriceid,
			Long icrowdkindid, Long itickettypeid, Long iztickettypeid,
			String dtstartdate, String dtenddate, Double zpric, Long znumb,
			Long zyhnumb, Double zyhamnt, Double zamnt) {
		this.id = id;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.icrowdkindid = icrowdkindid;
		this.itickettypeid = itickettypeid;
		this.iztickettypeid = iztickettypeid;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.zpric = zpric;
		this.znumb = znumb;
		this.zyhnumb = zyhnumb;
		this.zyhamnt = zyhamnt;
		this.zamnt = zamnt;
	}

	/** full constructor */
	public TZorderlist(TZorderlistId id, Long icrowdkindpriceid,
			Long icrowdkindid, Long itickettypeid, Long iztickettypeid,
			String dtstartdate, String dtenddate, Long tripid, Long ivenueid,
			Long ivenueareaid, Long ivenueseatsid, Double zpric,Double jsprice, Long znumb,
			Long zyhnumb, Double zyhamnt, Double zamnt, Long isa, Long isb, Long isc,
			Long isd, Long ise, Long isf, Long isg, Long ish, Long isi, Long isj,
			String notea, String notej, String notei, String noteh, String noteg,
			String notef, String notee, String noted, String notec, String noteb) {
		this.id = id;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.icrowdkindid = icrowdkindid;
		this.itickettypeid = itickettypeid;
		this.iztickettypeid = iztickettypeid;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.tripid = tripid;
		this.ivenueid = ivenueid;
		this.ivenueareaid = ivenueareaid;
		this.ivenueseatsid = ivenueseatsid;
		this.zpric = zpric;
		this.jsprice = jsprice;
		this.znumb = znumb;
		this.zyhnumb = zyhnumb;
		this.zyhamnt = zyhamnt;
		this.zamnt = zamnt;
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
	}

	// Property accessors

	public TZorderlistId getId() {
		return this.id;
	}

	public void setId(TZorderlistId id) {
		this.id = id;
	}

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public Long getIcrowdkindid() {
		return this.icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIztickettypeid() {
		return this.iztickettypeid;
	}

	public void setIztickettypeid(Long iztickettypeid) {
		this.iztickettypeid = iztickettypeid;
	}

	public String getDtstartdate() {
		return this.dtstartdate;
	}

	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}

	public String getDtenddate() {
		return this.dtenddate;
	}

	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}

	public Long getTripid() {
		return this.tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public Long getIvenueid() {
		return this.ivenueid;
	}

	public void setIvenueid(Long ivenueid) {
		this.ivenueid = ivenueid;
	}

	public Long getIvenueareaid() {
		return this.ivenueareaid;
	}

	public void setIvenueareaid(Long ivenueareaid) {
		this.ivenueareaid = ivenueareaid;
	}

	public Long getIvenueseatsid() {
		return this.ivenueseatsid;
	}

	public void setIvenueseatsid(Long ivenueseatsid) {
		this.ivenueseatsid = ivenueseatsid;
	}

	public Double getZpric() {
		return this.zpric;
	}

	public void setZpric(Double zpric) {
		this.zpric = zpric;
	}

	public Double getJsprice() {
		return jsprice;
	}

	public void setJsprice(Double jsprice) {
		this.jsprice = jsprice;
	}

	public Long getZnumb() {
		return this.znumb;
	}

	public void setZnumb(Long znumb) {
		this.znumb = znumb;
	}

	public Long getZyhnumb() {
		return this.zyhnumb;
	}

	public void setZyhnumb(Long zyhnumb) {
		this.zyhnumb = zyhnumb;
	}

	public Double getZyhamnt() {
		return this.zyhamnt;
	}

	public void setZyhamnt(Double zyhamnt) {
		this.zyhamnt = zyhamnt;
	}

	public Double getZamnt() {
		return this.zamnt;
	}

	public void setZamnt(Double zamnt) {
		this.zamnt = zamnt;
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

	public Long getIsc() {
		return this.isc;
	}

	public void setIsc(Long isc) {
		this.isc = isc;
	}

	public Long getIsd() {
		return this.isd;
	}

	public void setIsd(Long isd) {
		this.isd = isd;
	}

	public Long getIse() {
		return this.ise;
	}

	public void setIse(Long ise) {
		this.ise = ise;
	}

	public Long getIsf() {
		return this.isf;
	}

	public void setIsf(Long isf) {
		this.isf = isf;
	}

	public Long getIsg() {
		return this.isg;
	}

	public void setIsg(Long isg) {
		this.isg = isg;
	}

	public Long getIsh() {
		return this.ish;
	}

	public void setIsh(Long ish) {
		this.ish = ish;
	}

	public Long getIsi() {
		return this.isi;
	}

	public void setIsi(Long isi) {
		this.isi = isi;
	}

	public Long getIsj() {
		return this.isj;
	}

	public void setIsj(Long isj) {
		this.isj = isj;
	}

	public String getNotea() {
		return this.notea;
	}

	public void setNotea(String notea) {
		this.notea = notea;
	}

	public String getNotej() {
		return this.notej;
	}

	public void setNotej(String notej) {
		this.notej = notej;
	}

	public String getNotei() {
		return this.notei;
	}

	public void setNotei(String notei) {
		this.notei = notei;
	}

	public String getNoteh() {
		return this.noteh;
	}

	public void setNoteh(String noteh) {
		this.noteh = noteh;
	}

	public String getNoteg() {
		return this.noteg;
	}

	public void setNoteg(String noteg) {
		this.noteg = noteg;
	}

	public String getNotef() {
		return this.notef;
	}

	public void setNotef(String notef) {
		this.notef = notef;
	}

	public String getNotee() {
		return this.notee;
	}

	public void setNotee(String notee) {
		this.notee = notee;
	}

	public String getNoted() {
		return this.noted;
	}

	public void setNoted(String noted) {
		this.noted = noted;
	}

	public String getNotec() {
		return this.notec;
	}

	public void setNotec(String notec) {
		this.notec = notec;
	}

	public String getNoteb() {
		return this.noteb;
	}

	public void setNoteb(String noteb) {
		this.noteb = noteb;
	}

	public String getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}
	
	public Double getTdfl() {
		return tdfl;
	}
	public Double getMhandcharge() {
		return mhandcharge;
	}

	public void setMhandcharge(Double mhandcharge) {
		this.mhandcharge = mhandcharge;
	}

	public void setTdfl(Double tdfl) {
		this.tdfl = tdfl;
	}

	public Long getSqnumber() {
		return sqnumber;
	}

	public void setSqnumber(Long sqnumber) {
		this.sqnumber = sqnumber;
	}

	public String getStritickettypeid() {
		return stritickettypeid;
	}

	public void setStritickettypeid(String stritickettypeid) {
		this.stritickettypeid = stritickettypeid;
	}

	public String getStricrowdkindid() {
		return stricrowdkindid;
	}

	public void setStricrowdkindid(String stricrowdkindid) {
		this.stricrowdkindid = stricrowdkindid;
	}
	
	
}
