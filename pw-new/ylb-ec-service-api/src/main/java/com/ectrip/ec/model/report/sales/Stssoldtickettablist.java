package com.ectrip.ec.model.report.sales;

/**
 * Stssoldtickettablist entity. @author MyEclipse Persistence Tools
 */

public class Stssoldtickettablist implements java.io.Serializable {

	// Fields

	private StssoldtickettablistId id;
	private Long itickettypeid;
	private Long icrowdkindid;
	private Long iscenicid;
	private String usid;
	private Long ibusinessid;
	private String dyusid;
	private Long iplayerperticket;
	private String dtenddate;
	private String dtstartdate;
	private Double mhandcharge;
	private Long ipartitionsign;
	private Long iagentno;
	private Long icardno;
	private String szticketprintno;
	private Long iserialnum;
	private String byvalidity;
	private Double mremainmoney;
	private Double mpresentmoney;
	private Double mactualsaleprice;
	private Long ipresentnum;
	private Long iremainnum;
	private Double mnominalfee;
	private Double mdeposit;
	private String byticketpurpose;
	private Double mrefundamount;
	private Double mforcerefundamount;
	private Long bisrefundbalance;
	private Double mreservedmoney;
	private String byactivation;
	private Long bisactivation;
	private Long ivaliditynum;
	private Long byvalidityunits;
	private String byconsumetype;
	private Long byisout;
	private String manyouno;
	private String myzj;
	private String name1;
	private String zjno1;
	private String name2;
	private String zjno2;
	private String name3;
	private String zjno3;
    private String dtmakedate;

	// Constructors

	public String getManyouno() {
		return manyouno;
	}

	public void setManyouno(String manyouno) {
		this.manyouno = manyouno;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getZjno1() {
		return zjno1;
	}

	public void setZjno1(String zjno1) {
		this.zjno1 = zjno1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getZjno2() {
		return zjno2;
	}

	public void setZjno2(String zjno2) {
		this.zjno2 = zjno2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getZjno3() {
		return zjno3;
	}

	public void setZjno3(String zjno3) {
		this.zjno3 = zjno3;
	}

	/** default constructor */
	public Stssoldtickettablist() {
	}

	/** minimal constructor */
	public Stssoldtickettablist(StssoldtickettablistId id,
			Long itickettypeid, Long icrowdkindid,
			Long iscenicid, Long iplayerperticket,
			String dtenddate, String dtstartdate, Double mhandcharge,
			String szticketprintno, Long iserialnum, String byvalidity,
			Double mremainmoney, Double mpresentmoney, Double mactualsaleprice,
			Long ipresentnum, Double mnominalfee, Double mdeposit,
			Long bisrefundbalance, String byactivation) {
		this.id = id;
		this.itickettypeid = itickettypeid;
		this.icrowdkindid = icrowdkindid;
		this.iscenicid = iscenicid;
		this.iplayerperticket = iplayerperticket;
		this.dtenddate = dtenddate;
		this.dtstartdate = dtstartdate;
		this.mhandcharge = mhandcharge;
		this.szticketprintno = szticketprintno;
		this.iserialnum = iserialnum;
		this.byvalidity = byvalidity;
		this.mremainmoney = mremainmoney;
		this.mpresentmoney = mpresentmoney;
		this.mactualsaleprice = mactualsaleprice;
		this.ipresentnum = ipresentnum;
		this.mnominalfee = mnominalfee;
		this.mdeposit = mdeposit;
		this.bisrefundbalance = bisrefundbalance;
		this.byactivation = byactivation;
	}

	/** full constructor */
	public Stssoldtickettablist(StssoldtickettablistId id,
			Long itickettypeid, Long icrowdkindid,
			Long iscenicid, String usid, Long ibusinessid,
			String dyusid, Long iplayerperticket, String dtenddate,
			String dtstartdate, Double mhandcharge, Long ipartitionsign,
			Long iagentno, Long icardno, String szticketprintno,
			Long iserialnum, String byvalidity, Double mremainmoney,
			Double mpresentmoney, Double mactualsaleprice,
			Long ipresentnum, Long iremainnum, Double mnominalfee,
			Double mdeposit, String byticketpurpose, Double mrefundamount,
			Double mforcerefundamount, Long bisrefundbalance,
			Double mreservedmoney, String byactivation,
			Long bisactivation, Long ivaliditynum,
			Long byvalidityunits, String byconsumetype, Long byisout) {
		this.id = id;
		this.itickettypeid = itickettypeid;
		this.icrowdkindid = icrowdkindid;
		this.iscenicid = iscenicid;
		this.usid = usid;
		this.ibusinessid = ibusinessid;
		this.dyusid = dyusid;
		this.iplayerperticket = iplayerperticket;
		this.dtenddate = dtenddate;
		this.dtstartdate = dtstartdate;
		this.mhandcharge = mhandcharge;
		this.ipartitionsign = ipartitionsign;
		this.iagentno = iagentno;
		this.icardno = icardno;
		this.szticketprintno = szticketprintno;
		this.iserialnum = iserialnum;
		this.byvalidity = byvalidity;
		this.mremainmoney = mremainmoney;
		this.mpresentmoney = mpresentmoney;
		this.mactualsaleprice = mactualsaleprice;
		this.ipresentnum = ipresentnum;
		this.iremainnum = iremainnum;
		this.mnominalfee = mnominalfee;
		this.mdeposit = mdeposit;
		this.byticketpurpose = byticketpurpose;
		this.mrefundamount = mrefundamount;
		this.mforcerefundamount = mforcerefundamount;
		this.bisrefundbalance = bisrefundbalance;
		this.mreservedmoney = mreservedmoney;
		this.byactivation = byactivation;
		this.bisactivation = bisactivation;
		this.ivaliditynum = ivaliditynum;
		this.byvalidityunits = byvalidityunits;
		this.byconsumetype = byconsumetype;
		this.byisout = byisout;
	}

	// Property accessors

	public StssoldtickettablistId getId() {
		return this.id;
	}

	public void setId(StssoldtickettablistId id) {
		this.id = id;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIcrowdkindid() {
		return this.icrowdkindid;
	}

	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public String getDyusid() {
		return this.dyusid;
	}

	public void setDyusid(String dyusid) {
		this.dyusid = dyusid;
	}

	public Long getIplayerperticket() {
		return this.iplayerperticket;
	}

	public void setIplayerperticket(Long iplayerperticket) {
		this.iplayerperticket = iplayerperticket;
	}

	public String getDtenddate() {
		return this.dtenddate;
	}

	public void setDtenddate(String dtenddate) {
		this.dtenddate = dtenddate;
	}

	public String getDtstartdate() {
		return this.dtstartdate;
	}

	public void setDtstartdate(String dtstartdate) {
		this.dtstartdate = dtstartdate;
	}

	public Double getMhandcharge() {
		return this.mhandcharge;
	}

	public void setMhandcharge(Double mhandcharge) {
		this.mhandcharge = mhandcharge;
	}

	public Long getIpartitionsign() {
		return this.ipartitionsign;
	}

	public void setIpartitionsign(Long ipartitionsign) {
		this.ipartitionsign = ipartitionsign;
	}

	public Long getIagentno() {
		return this.iagentno;
	}

	public void setIagentno(Long iagentno) {
		this.iagentno = iagentno;
	}

	public Long getIcardno() {
		return this.icardno;
	}

	public void setIcardno(Long icardno) {
		this.icardno = icardno;
	}

	public String getSzticketprintno() {
		return this.szticketprintno;
	}

	public void setSzticketprintno(String szticketprintno) {
		this.szticketprintno = szticketprintno;
	}

	public Long getIserialnum() {
		return this.iserialnum;
	}

	public void setIserialnum(Long iserialnum) {
		this.iserialnum = iserialnum;
	}

	public String getByvalidity() {
		return this.byvalidity;
	}

	public void setByvalidity(String byvalidity) {
		this.byvalidity = byvalidity;
	}

	public Double getMremainmoney() {
		return this.mremainmoney;
	}

	public void setMremainmoney(Double mremainmoney) {
		this.mremainmoney = mremainmoney;
	}

	public Double getMpresentmoney() {
		return this.mpresentmoney;
	}

	public void setMpresentmoney(Double mpresentmoney) {
		this.mpresentmoney = mpresentmoney;
	}

	public Double getMactualsaleprice() {
		return this.mactualsaleprice;
	}

	public void setMactualsaleprice(Double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

	public Long getIpresentnum() {
		return this.ipresentnum;
	}

	public void setIpresentnum(Long ipresentnum) {
		this.ipresentnum = ipresentnum;
	}

	public Long getIremainnum() {
		return this.iremainnum;
	}

	public void setIremainnum(Long iremainnum) {
		this.iremainnum = iremainnum;
	}

	public Double getMnominalfee() {
		return this.mnominalfee;
	}

	public void setMnominalfee(Double mnominalfee) {
		this.mnominalfee = mnominalfee;
	}

	public Double getMdeposit() {
		return this.mdeposit;
	}

	public void setMdeposit(Double mdeposit) {
		this.mdeposit = mdeposit;
	}

	public String getByticketpurpose() {
		return this.byticketpurpose;
	}

	public void setByticketpurpose(String byticketpurpose) {
		this.byticketpurpose = byticketpurpose;
	}

	public Double getMrefundamount() {
		return this.mrefundamount;
	}

	public void setMrefundamount(Double mrefundamount) {
		this.mrefundamount = mrefundamount;
	}

	public Double getMforcerefundamount() {
		return this.mforcerefundamount;
	}

	public void setMforcerefundamount(Double mforcerefundamount) {
		this.mforcerefundamount = mforcerefundamount;
	}

	public Long getBisrefundbalance() {
		return this.bisrefundbalance;
	}

	public void setBisrefundbalance(Long bisrefundbalance) {
		this.bisrefundbalance = bisrefundbalance;
	}

	public Double getMreservedmoney() {
		return this.mreservedmoney;
	}

	public void setMreservedmoney(Double mreservedmoney) {
		this.mreservedmoney = mreservedmoney;
	}

	public String getByactivation() {
		return this.byactivation;
	}

	public void setByactivation(String byactivation) {
		this.byactivation = byactivation;
	}

	public Long getBisactivation() {
		return this.bisactivation;
	}

	public void setBisactivation(Long bisactivation) {
		this.bisactivation = bisactivation;
	}

	public Long getIvaliditynum() {
		return this.ivaliditynum;
	}

	public void setIvaliditynum(Long ivaliditynum) {
		this.ivaliditynum = ivaliditynum;
	}

	public Long getByvalidityunits() {
		return this.byvalidityunits;
	}

	public void setByvalidityunits(Long byvalidityunits) {
		this.byvalidityunits = byvalidityunits;
	}

	public String getByconsumetype() {
		return this.byconsumetype;
	}

	public void setByconsumetype(String byconsumetype) {
		this.byconsumetype = byconsumetype;
	}

	public Long getByisout() {
		return this.byisout;
	}

	public void setByisout(Long byisout) {
		this.byisout = byisout;
	}

	public String getMyzj() {
		return myzj;
	}

	public void setMyzj(String myzj) {
		this.myzj = myzj;
	}

    public String getDtmakedate() {
        return dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }
}