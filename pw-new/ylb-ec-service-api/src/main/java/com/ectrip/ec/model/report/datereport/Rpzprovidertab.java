package com.ectrip.ec.model.report.datereport;



/**
 * Rpzprovidertab entity. @author MyEclipse Persistence Tools
 */
public class Rpzprovidertab  implements
		java.io.Serializable {
	private Long seq;
	private String rdate;
	private String rmonth;
	private String ryear;
	private String times;
	private String titype;
	private Long iscenicid;
	private Long ibusinessid;
	

	private String isettlementid;
	private String bysalesvouchertype;
	private Long numb;
	private Long yhnumb;
	private Double mont;
	private Double yhmont;
	private Double mhandcharge;
	private Long isa;
	private Long isb;
	private Long isc;
	private Long isd;
	private Long ise;
	private Long isf;
	private Double dua;
	private Double dub;
	private Double duc;
	private Double dud;
	private Double due;
	private Double duf;
	private String notea;
	private String noteb;
	private String notec;
	private String noted;
	private String notee;
	private String notef;
	private String dtmakedate;

	// Constructors

	/** default constructor */
	public Rpzprovidertab() {
	}

	/** minimal constructor */
	public Rpzprovidertab(Long seq, String rdate, String rmonth, String ryear,
			String times, String titype, Long iscenicid,Long ibusinessid, String isettlementid,
			String bysalesvouchertype, Long numb, Long yhnumb, Double mont,
			Double yhmont, Double mhandcharge) {
		    this.seq = seq;
	        this.rdate = rdate;
	        this.rmonth = rmonth;
	        this.ryear = ryear;
	        this.times = times;
	        this.titype=titype;
	        this.iscenicid=iscenicid;
	        this.ibusinessid=ibusinessid;
	        this.isettlementid=isettlementid;
	        this.bysalesvouchertype=bysalesvouchertype;
	        this.numb=numb;
	        this.yhnumb=yhnumb;
	        this.mont=mont;
	        this.yhmont=yhmont;
	        this.mhandcharge=mhandcharge;
	        
	}

	/** full constructor */
	public Rpzprovidertab(Long seq, String rdate, String rmonth, String ryear,
			String times, String titype, Long iscenicid,Long ibusinessid, String isettlementid,
			String bysalesvouchertype, Long numb, Long yhnumb, Double mont,
			Double yhmont, Double mhandcharge, Long isa, Long isb, Long isc,
			Long isd, Long ise, Long isf, Double dua, Double dub, Double duc,
			Double dud, Double due, Double duf, String notea, String noteb,
			String notec, String noted, String notee, String notef,
			String dtmakedate) {
		this.seq = seq;
		this.rdate = rdate;
		this.rmonth = rmonth;
		this.ryear = ryear;
		this.times = times;
		this.titype = titype;
		this.iscenicid = iscenicid;
		this.ibusinessid=ibusinessid;
		this.isettlementid = isettlementid;
		this.bysalesvouchertype = bysalesvouchertype;
		this.numb = numb;
		this.yhnumb = yhnumb;
		this.mont = mont;
		this.yhmont = yhmont;
		this.mhandcharge = mhandcharge;
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
		this.dtmakedate = dtmakedate;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getRmonth() {
		return rmonth;
	}

	public void setRmonth(String rmonth) {
		this.rmonth = rmonth;
	}

	public String getRyear() {
		return ryear;
	}

	public void setRyear(String ryear) {
		this.ryear = ryear;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getTitype() {
		return titype;
	}

	public void setTitype(String titype) {
		this.titype = titype;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public Long getIbusinessid() {
		return ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}
	public String getIsettlementid() {
		return isettlementid;
	}

	public void setIsettlementid(String isettlementid) {
		this.isettlementid = isettlementid;
	}

	public String getBysalesvouchertype() {
		return bysalesvouchertype;
	}

	public void setBysalesvouchertype(String bysalesvouchertype) {
		this.bysalesvouchertype = bysalesvouchertype;
	}

	public Long getNumb() {
		return numb;
	}

	public void setNumb(Long numb) {
		this.numb = numb;
	}

	public Long getYhnumb() {
		return yhnumb;
	}

	public void setYhnumb(Long yhnumb) {
		this.yhnumb = yhnumb;
	}

	public Double getMont() {
		return mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
	}

	public Double getYhmont() {
		return yhmont;
	}

	public void setYhmont(Double yhmont) {
		this.yhmont = yhmont;
	}

	public Double getMhandcharge() {
		return mhandcharge;
	}

	public void setMhandcharge(Double mhandcharge) {
		this.mhandcharge = mhandcharge;
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

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}
