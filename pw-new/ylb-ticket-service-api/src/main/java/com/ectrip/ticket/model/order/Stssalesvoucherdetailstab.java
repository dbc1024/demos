package com.ectrip.ticket.model.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Stssalesvoucherdetailstab entity.
 * 销售凭证明细表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Stssalesvoucherdetailstab implements java.io.Serializable {

	// Fields
	@Id
	private StssalesvoucherdetailstabId id;

	private Long icrowdkindpriceid;// 售价ID
	private Long itickettypeid;// 产品ID
	private Long iticketwinid;// 售票窗口ID
	private Long iplayerperticket;// 人/张
	private Long iticketnum;// 张数
	private Long iticketplayer;// 人次
	private String dtstartdate;// 启用日期
	private String dtenddate;// 失效日期
	private Long istartid;// 起始票ID
	private Long iendid;// 截止票ID
	private String szstartserial;// 起始票号
	private String szendserial;// 截止票号
	private Long ioffersschemeid;// 优惠方案ID
	private Long iamount;// 录入数量
	private Long ipresentnums;// 已退订数量
	private Long ideratenums;// 减免数量
	private Long ifactnum;// 交易数量
	private Long iuseablenessnum;// 使用数量
	private Double mactualsaleprice;// 实际售价
	private Double meventmoney;// 发生金额
	private Double mderatemoney;// 减免金额
	private Double mpresentmoney;//  已退订金额
	private Double mnominalfee;// 工本费
	private Double mdeposit;// 押金
	private Double mhandcharge;// 手续费
	private String byconsumetype;// 消费类型
	private Double iconsumenum;// 消费数量
	private Long iversion;// 版本
	private Double mtotalamount;// 总金额
	private Long itotalnumber;// 总次数
	private Long itotalminutes;// 总分钟
	private Long byisout;// 是否同步
	private String dtmakedate;// 日期时间
	private String timeStart; //开始时段
	private String timeEnd;//结束时段
	private Long timeId; //时段ID;
  
	// not database fields
	@Transient
	private String stritickettypeid; // 产品名称
	@Transient
	private String striticketwinid; // 售票窗口名称
	@Transient
	private String strioffersschemeid; // 优惠方案名称
	@Transient
	private String strbyconsumetype; // 消费类型
	@Transient
	private List comlist;//子票列表
	@Transient
	private Long isalesvoucherdetailsid;
	@Transient
	private Long isalesvoucherid;
	@Transient
	private Long iticketstationid;
	@Transient
	private String bycategorytype;
	@Transient
    private String manyouno;
	
	@Transient
	private String myzj;
	@Transient
	private String name1;
	@Transient
	private String zjno1;
	@Transient
	private String name2;
	@Transient
	private String zjno2;
	@Transient
	private String name3;
	@Transient
	private String zjno3;
	@Transient
	private List mplist;//门票列表
	// Constructors
	
	
	public List getMplist() {
		return mplist;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public void setMplist(List mplist) {
		this.mplist = mplist;
	}

	public String getManyouno() {
		return manyouno;
	}

	public void setManyouno(String manyouno) {
		this.manyouno = manyouno;
	}

	public String getMyzj() {
		return myzj;
	}

	public void setMyzj(String myzj) {
		this.myzj = myzj;
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

	public String getBycategorytype() {
		return bycategorytype;
	}

	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}

	public Long getIsalesvoucherdetailsid() {
		return isalesvoucherdetailsid;
	}

	public void setIsalesvoucherdetailsid(Long isalesvoucherdetailsid) {
		this.isalesvoucherdetailsid = isalesvoucherdetailsid;
	}

	public List getComlist() {
		return comlist;
	}

	public void setComlist(List comlist) {
		this.comlist = comlist;
	}

	/** default constructor */
	public Stssalesvoucherdetailstab() {
	}

	/** minimal constructor */
	public Stssalesvoucherdetailstab(StssalesvoucherdetailstabId id,
			Long iplayerperticket, Long iticketnum, Long iticketplayer,
			String dtstartdate, String dtenddate, Long istartid, Long iendid,
			String szstartserial, String szendserial, Long iamount,

			Long ipresentnums, Long ideratenums, Long ifactnum,
			Long iuseablenessnum, Double mactualsaleprice, Double meventmoney,
			Double mderatemoney, Double mpresentmoney, Double mnominalfee,
			Double mdeposit, Double mhandcharge, String byconsumetype,
			Double iconsumenum, Long iversion) {

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
	public Stssalesvoucherdetailstab(StssalesvoucherdetailstabId id,
			Long icrowdkindpriceid, Long itickettypeid, Long iticketwinid,
			Long iplayerperticket, Long iticketnum, Long iticketplayer,
			String dtstartdate, String dtenddate, Long istartid, Long iendid,
			String szstartserial, String szendserial, Long ioffersschemeid,
			Long iamount, Long ipresentnums, Long ideratenums, Long ifactnum,
			Long iuseablenessnum, Double mactualsaleprice, Double meventmoney,
			Double mderatemoney, Double mpresentmoney, Double mnominalfee,
			Double mdeposit, Double mhandcharge, String byconsumetype,
			Double iconsumenum, Long iversion, Double mtotalamount,
			Long itotalnumber, Long itotalminutes, Long byisout,
			String dtmakedate) {
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

	public StssalesvoucherdetailstabId getId() {
		return this.id;
	}

	public void setId(StssalesvoucherdetailstabId id) {
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

	public String getStritickettypeid() {
		return stritickettypeid;
	}

	public void setStritickettypeid(String stritickettypeid) {
		this.stritickettypeid = stritickettypeid;
	}

	public String getStriticketwinid() {
		return striticketwinid;
	}

	public void setStriticketwinid(String striticketwinid) {
		this.striticketwinid = striticketwinid;
	}

	public String getStrioffersschemeid() {
		return strioffersschemeid;
	}

	public void setStrioffersschemeid(String strioffersschemeid) {
		this.strioffersschemeid = strioffersschemeid;
	}

	public String getStrbyconsumetype() {
		return strbyconsumetype;
	}

	public void setStrbyconsumetype(String strbyconsumetype) {
		this.strbyconsumetype = strbyconsumetype;
	}

	public Long getIsalesvoucherid() {
		return isalesvoucherid;
	}

	public void setIsalesvoucherid(Long isalesvoucherid) {
		this.isalesvoucherid = isalesvoucherid;
	}

	public Long getIticketstationid() {
		return iticketstationid;
	}

	public void setIticketstationid(Long iticketstationid) {
		this.iticketstationid = iticketstationid;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}
}