package com.ectrip.ec.model.report.sales;

/**
 * Stscomticketsalesdetailstabls entity. @author MyEclipse Persistence Tools
 */

public class Stscomticketsalesdetailstabls implements java.io.Serializable {

	// Fields

	private StscomticketsalesdetailstablsId id;
	private Long icrowdkindpriceid;
	private Long itickettypeid;
	private Long iztickettypeid;
	private Long tripid;
	private Long ivenueid;
	private Long ivenueareaid;
	private Long ivenueseatsid;
	private String dtstartdate;
	private String dtenddate;
	private Long isplitamount;
	private Double msplitmoney;
	private Long iversion;
	private Double msplitprice;
	private Double mhandcharge;
	private Double mderatemoney;// ������
	private Long ideratenums;//��������
    private String dtmakedate;
	// Constructors

	public Double getMderatemoney() {
		return mderatemoney;
	}

	public void setMderatemoney(Double mderatemoney) {
		this.mderatemoney = mderatemoney;
	}

	public Long getIderatenums() {
		return ideratenums;
	}

	public void setIderatenums(Long ideratenums) {
		this.ideratenums = ideratenums;
	}

	/** default constructor */
	public Stscomticketsalesdetailstabls() {
	}

	/** minimal constructor */
	public Stscomticketsalesdetailstabls(StscomticketsalesdetailstablsId id,
			Long icrowdkindpriceid, Long itickettypeid,
			String dtstartdate, String dtenddate, Long isplitamount,
			Double msplitmoney, Long iversion, Double msplitprice,
			Double mhandcharge) {
		this.id = id;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.itickettypeid = itickettypeid;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.isplitamount = isplitamount;
		this.msplitmoney = msplitmoney;
		this.iversion = iversion;
		this.msplitprice = msplitprice;
		this.mhandcharge = mhandcharge;
	}

	/** full constructor */
	public Stscomticketsalesdetailstabls(StscomticketsalesdetailstablsId id,
			Long icrowdkindpriceid, Long itickettypeid,
			Long iztickettypeid, Long tripid, Long ivenueid,
			Long ivenueareaid, Long ivenueseatsid,
			String dtstartdate, String dtenddate, Long isplitamount,
			Double msplitmoney, Long iversion, Double msplitprice,
			Double mhandcharge) {
		this.id = id;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.itickettypeid = itickettypeid;
		this.iztickettypeid = iztickettypeid;
		this.tripid = tripid;
		this.ivenueid = ivenueid;
		this.ivenueareaid = ivenueareaid;
		this.ivenueseatsid = ivenueseatsid;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.isplitamount = isplitamount;
		this.msplitmoney = msplitmoney;
		this.iversion = iversion;
		this.msplitprice = msplitprice;
		this.mhandcharge = mhandcharge;
	}

	// Property accessors

	public StscomticketsalesdetailstablsId getId() {
		return this.id;
	}

	public void setId(StscomticketsalesdetailstablsId id) {
		this.id = id;
	}

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
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

	public Long getIsplitamount() {
		return this.isplitamount;
	}

	public void setIsplitamount(Long isplitamount) {
		this.isplitamount = isplitamount;
	}

	public Double getMsplitmoney() {
		return this.msplitmoney;
	}

	public void setMsplitmoney(Double msplitmoney) {
		this.msplitmoney = msplitmoney;
	}

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

	public Double getMsplitprice() {
		return this.msplitprice;
	}

	public void setMsplitprice(Double msplitprice) {
		this.msplitprice = msplitprice;
	}

	public Double getMhandcharge() {
		return this.mhandcharge;
	}

	public void setMhandcharge(Double mhandcharge) {
		this.mhandcharge = mhandcharge;
	}

    public String getDtmakedate() {
        return dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }
}