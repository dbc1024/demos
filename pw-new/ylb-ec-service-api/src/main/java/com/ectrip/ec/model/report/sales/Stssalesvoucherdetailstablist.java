package com.ectrip.ec.model.report.sales;

import java.math.BigDecimal;

/**
 * Stssalesvoucherdetailstablist entity. @author MyEclipse Persistence Tools
 */

public class Stssalesvoucherdetailstablist implements java.io.Serializable {

	// Fields

	private StssalesvoucherdetailstablistId id;
	private Long icrowdkindpriceid;
	private Long itickettypeid;
	private Long iticketwinid;
	private Long iplayerperticket;
	private Long iticketnum;
	private Long iticketplayer;
	private String dtstartdate;
	private String dtenddate;
	private Long istartid;
	private Long iendid;
	private String szstartserial;
	private String szendserial;
	private Long ioffersschemeid;
	private Long iamount;
	private Long ipresentnums;
	private Long ideratenums;
	private Long ifactnum;
	private Long iuseablenessnum;
	private Double mactualsaleprice;
	private Double meventmoney;
	private Double mderatemoney;
	private Double mpresentmoney;
	private Double mnominalfee;
	private Double mdeposit;
	private Double mhandcharge;
	private String byconsumetype;
	private Double iconsumenum;
	private Long iversion;
	private Double mtotalamount;
	private Long itotalnumber;
	private Long itotalminutes;
	private Long byisout;
	private String dtmakedate;

	// Constructors

	/** default constructor */
	public Stssalesvoucherdetailstablist() {
	}

	/** minimal constructor */
	public Stssalesvoucherdetailstablist(StssalesvoucherdetailstablistId id,
			Long iplayerperticket, Long iticketnum,
			Long iticketplayer, String dtstartdate, String dtenddate,
			Long istartid, Long iendid, String szstartserial,
			String szendserial, Long iamount, Long ipresentnums,
			Long ideratenums, Long ifactnum,
			Long iuseablenessnum, Double mactualsaleprice,
			Double meventmoney, Double mderatemoney, Double mpresentmoney,
			Double mnominalfee, Double mdeposit, Double mhandcharge,
			String byconsumetype, Double iconsumenum, Long iversion) {
		this.id = id;
		this.iplayerperticket = iplayerperticket;
		this.iticketnum = iticketnum;
		this.iticketplayer = iticketplayer;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.istartid = istartid;
		this.iendid = iendid;
		this.szstartserial = szstartserial;
		this.szendserial = szendserial;
		this.iamount = iamount;
		this.ipresentnums = ipresentnums;
		this.ideratenums = ideratenums;
		this.ifactnum = ifactnum;
		this.iuseablenessnum = iuseablenessnum;
		this.mactualsaleprice = mactualsaleprice;
		this.meventmoney = meventmoney;
		this.mderatemoney = mderatemoney;
		this.mpresentmoney = mpresentmoney;
		this.mnominalfee = mnominalfee;
		this.mdeposit = mdeposit;
		this.mhandcharge = mhandcharge;
		this.byconsumetype = byconsumetype;
		this.iconsumenum = iconsumenum;
		this.iversion = iversion;
	}

	/** full constructor */
	public Stssalesvoucherdetailstablist(StssalesvoucherdetailstablistId id,
			Long icrowdkindpriceid, Long itickettypeid,
			Long iticketwinid, Long iplayerperticket,
			Long iticketnum, Long iticketplayer,
			String dtstartdate, String dtenddate, Long istartid,
			Long iendid, String szstartserial, String szendserial,
			Long ioffersschemeid, Long iamount,
			Long ipresentnums, Long ideratenums,
			Long ifactnum, Long iuseablenessnum,
			Double mactualsaleprice, Double meventmoney, Double mderatemoney,
			Double mpresentmoney, Double mnominalfee, Double mdeposit,
			Double mhandcharge, String byconsumetype, Double iconsumenum,
			Long iversion, Double mtotalamount, Long itotalnumber,
			Long itotalminutes, Long byisout, String dtmakedate) {
		this.id = id;
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.itickettypeid = itickettypeid;
		this.iticketwinid = iticketwinid;
		this.iplayerperticket = iplayerperticket;
		this.iticketnum = iticketnum;
		this.iticketplayer = iticketplayer;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.istartid = istartid;
		this.iendid = iendid;
		this.szstartserial = szstartserial;
		this.szendserial = szendserial;
		this.ioffersschemeid = ioffersschemeid;
		this.iamount = iamount;
		this.ipresentnums = ipresentnums;
		this.ideratenums = ideratenums;
		this.ifactnum = ifactnum;
		this.iuseablenessnum = iuseablenessnum;
		this.mactualsaleprice = mactualsaleprice;
		this.meventmoney = meventmoney;
		this.mderatemoney = mderatemoney;
		this.mpresentmoney = mpresentmoney;
		this.mnominalfee = mnominalfee;
		this.mdeposit = mdeposit;
		this.mhandcharge = mhandcharge;
		this.byconsumetype = byconsumetype;
		this.iconsumenum = iconsumenum;
		this.iversion = iversion;
		this.mtotalamount = mtotalamount;
		this.itotalnumber = itotalnumber;
		this.itotalminutes = itotalminutes;
		this.byisout = byisout;
		this.dtmakedate = dtmakedate;
	}

	// Property accessors

	public StssalesvoucherdetailstablistId getId() {
		return this.id;
	}

	public void setId(StssalesvoucherdetailstablistId id) {
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

	public Long getIticketwinid() {
		return this.iticketwinid;
	}

	public void setIticketwinid(Long iticketwinid) {
		this.iticketwinid = iticketwinid;
	}

	public Long getIplayerperticket() {
		return this.iplayerperticket;
	}

	public void setIplayerperticket(Long iplayerperticket) {
		this.iplayerperticket = iplayerperticket;
	}

	public Long getIticketnum() {
		return this.iticketnum;
	}

	public void setIticketnum(Long iticketnum) {
		this.iticketnum = iticketnum;
	}

	public Long getIticketplayer() {
		return this.iticketplayer;
	}

	public void setIticketplayer(Long iticketplayer) {
		this.iticketplayer = iticketplayer;
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

	public Long getIstartid() {
		return this.istartid;
	}

	public void setIstartid(Long istartid) {
		this.istartid = istartid;
	}

	public Long getIendid() {
		return this.iendid;
	}

	public void setIendid(Long iendid) {
		this.iendid = iendid;
	}

	public String getSzstartserial() {
		return this.szstartserial;
	}

	public void setSzstartserial(String szstartserial) {
		this.szstartserial = szstartserial;
	}

	public String getSzendserial() {
		return this.szendserial;
	}

	public void setSzendserial(String szendserial) {
		this.szendserial = szendserial;
	}

	public Long getIoffersschemeid() {
		return this.ioffersschemeid;
	}

	public void setIoffersschemeid(Long ioffersschemeid) {
		this.ioffersschemeid = ioffersschemeid;
	}

	public Long getIamount() {
		return this.iamount;
	}

	public void setIamount(Long iamount) {
		this.iamount = iamount;
	}

	public Long getIpresentnums() {
		return this.ipresentnums;
	}

	public void setIpresentnums(Long ipresentnums) {
		this.ipresentnums = ipresentnums;
	}

	public Long getIderatenums() {
		return this.ideratenums;
	}

	public void setIderatenums(Long ideratenums) {
		this.ideratenums = ideratenums;
	}

	public Long getIfactnum() {
		return this.ifactnum;
	}

	public void setIfactnum(Long ifactnum) {
		this.ifactnum = ifactnum;
	}

	public Long getIuseablenessnum() {
		return this.iuseablenessnum;
	}

	public void setIuseablenessnum(Long iuseablenessnum) {
		this.iuseablenessnum = iuseablenessnum;
	}

	public Double getMactualsaleprice() {
		return this.mactualsaleprice;
	}

	public void setMactualsaleprice(Double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

	public Double getMeventmoney() {
		return this.meventmoney;
	}

	public void setMeventmoney(Double meventmoney) {
		this.meventmoney = meventmoney;
	}

	public Double getMderatemoney() {
		return this.mderatemoney;
	}

	public void setMderatemoney(Double mderatemoney) {
		this.mderatemoney = mderatemoney;
	}

	public Double getMpresentmoney() {
		return this.mpresentmoney;
	}

	public void setMpresentmoney(Double mpresentmoney) {
		this.mpresentmoney = mpresentmoney;
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

	public Double getMhandcharge() {
		return this.mhandcharge;
	}

	public void setMhandcharge(Double mhandcharge) {
		this.mhandcharge = mhandcharge;
	}

	public String getByconsumetype() {
		return this.byconsumetype;
	}

	public void setByconsumetype(String byconsumetype) {
		this.byconsumetype = byconsumetype;
	}

	public Double getIconsumenum() {
		return this.iconsumenum;
	}

	public void setIconsumenum(Double iconsumenum) {
		this.iconsumenum = iconsumenum;
	}

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

	public Double getMtotalamount() {
		return this.mtotalamount;
	}

	public void setMtotalamount(Double mtotalamount) {
		this.mtotalamount = mtotalamount;
	}

	public Long getItotalnumber() {
		return this.itotalnumber;
	}

	public void setItotalnumber(Long itotalnumber) {
		this.itotalnumber = itotalnumber;
	}

	public Long getItotalminutes() {
		return this.itotalminutes;
	}

	public void setItotalminutes(Long itotalminutes) {
		this.itotalminutes = itotalminutes;
	}

	public Long getByisout() {
		return this.byisout;
	}

	public void setByisout(Long byisout) {
		this.byisout = byisout;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}