package com.ectrip.ticket.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.StssalesvouchertabId;

/**
 * Stssalesvouchertab entity.
 * 销售凭证表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Stssalesvouchertab implements java.io.Serializable {

	// Fields
	@Id
	private StssalesvouchertabId id;


	private Long iscenicid;// 服务商ID
	private Long iticketwinid;// 窗口ID
	private String szsalesvoucherno;// 销售凭证
	private Long ibusinessid;// 业务ID

	private Long bisintegral;// 参与积分
	private Long byprintinvoice;// 打印发票
	private Long bysplitway;// 分账方式
	private Long bisreturn;// 允许退票
	private String bysalesvouchertype;// 销售凭证类型
	private Long isticketstationid;// 对应原售票点ID
	private Long issalesvoucherid;// 对应原凭证ID
	private String forcedrefund;// 强行退票原因
	private String sztravelbillno;// 行程单号
	private Long isalesmanid;// 业务员ID
	private Long iregionalid;// 客源地ID
	private String usid;// 预订用户名
	private String tdlx;// 团队类型
	private String dyusid;// 导游ID
	private Long bypostrecord;// 是否冲正
	private Double mhandcharge;// 手续费
	private Double iaccountreceivable;// 应收款
	private Double iacceptmoney;// 实收款
	private Double igivechange;// 找零
	private Long ihandler;// 经手人
	private Long ipayeer;// 收款人
	private Long imaker;// 制单人
	private Long iauditor;// 强制退订
	private Long iyear;// 年
	private Long imonth;// 月
	private Long iday;// 日
	private String dtmakedate;// 制单日期
	private String dtauditdate;// 审核日期
	private Long bysalesvoucherstate;// 销售凭证状态
	private Long bispay;// 是否交款
	private Long bispayee;// 是否缴款
	private String ordersource;//订单来源

	// not database fields,非数据字段
	@Transient
	private Long isalesvoucherid; // 售票凭证ID
	@Transient
	private Long iticketstationid; // 售票站点ID
	@Transient
	private String strtdlx; // 团队类型名
	@Transient
	private String striscenicid; // 服务商名
	@Transient
	private String striticketwinid;// 售票窗口名
	@Transient
	private String stribusinessid;// 业务类型名
	@Transient
	private String striregionalid; // 客源地名
	@Transient
	private String strusid; // 预定用户名
	@Transient
	private String strdyusid; // 导游用户名
	@Transient
	private String strbysalesvouchertype; // 凭证类型
	@Transient
	private String strihandler; // 经手人
	@Transient
	private String stripayeer; // 收款人
	@Transient
	private String strimaker; // 制单人
	@Transient
	private String striauditor; // 审核人
	@Transient
	private Stssalesvoucherdetailstab stssalesvoucherdetailstab; // 售票明细
	@Transient
	private double jmamnt;
	@Transient
	private String isettlementid;//结算方式
	@Transient
	private String settlementid;
	
	private String ornote1;
	private String ornote2;
	private String ornote3;
	private String ornote4;
	private String ornote5;
	private String ornote6;
	private String ornote7;
	private String ornote8;
	private String ornote9;
	private String ornote10;
	public String getOrnote1() {
		return ornote1;
	}

	public void setOrnote1(String ornote1) {
		this.ornote1 = ornote1;
	}

	public String getOrnote2() {
		return ornote2;
	}

	public void setOrnote2(String ornote2) {
		this.ornote2 = ornote2;
	}

	public String getOrnote3() {
		return ornote3;
	}

	public void setOrnote3(String ornote3) {
		this.ornote3 = ornote3;
	}

	public String getOrnote4() {
		return ornote4;
	}

	public void setOrnote4(String ornote4) {
		this.ornote4 = ornote4;
	}

	public String getOrnote5() {
		return ornote5;
	}

	public void setOrnote5(String ornote5) {
		this.ornote5 = ornote5;
	}

	public String getOrnote6() {
		return ornote6;
	}

	public void setOrnote6(String ornote6) {
		this.ornote6 = ornote6;
	}

	public String getOrnote7() {
		return ornote7;
	}

	public void setOrnote7(String ornote7) {
		this.ornote7 = ornote7;
	}

	public String getOrnote8() {
		return ornote8;
	}

	public void setOrnote8(String ornote8) {
		this.ornote8 = ornote8;
	}

	public String getOrnote9() {
		return ornote9;
	}

	public void setOrnote9(String ornote9) {
		this.ornote9 = ornote9;
	}

	public String getOrnote10() {
		return ornote10;
	}

	public void setOrnote10(String ornote10) {
		this.ornote10 = ornote10;
	}
	public double getJmamnt() {
		return jmamnt;
	}

	public void setJmamnt(double jmamnt) {
		this.jmamnt = jmamnt;
	}
  
	/** default constructor */
	public Stssalesvouchertab() {
	}

	/** minimal constructor */
	public Stssalesvouchertab(StssalesvouchertabId id, Long iscenicid,
			Long iticketwinid, String szsalesvoucherno, Long bisintegral,
			Long byprintinvoice, Long bysplitway, Long bisreturn,
			String bysalesvouchertype, Long bypostrecord,
			Double iaccountreceivable, Double iacceptmoney, Double igivechange,
			Long ihandler, Long ipayeer, Long imaker, Long iauditor,
			Long iyear, Long imonth, Long iday, String dtmakedate,
			String dtauditdate, Long bysalesvoucherstate, Long bispay,
			Long bispayee,String ordersource) {
		this.id = id;
		this.iscenicid = iscenicid;
		this.iticketwinid = iticketwinid;
		this.szsalesvoucherno = szsalesvoucherno;
		this.bisintegral = bisintegral;
		this.byprintinvoice = byprintinvoice;
		this.bysplitway = bysplitway;
		this.bisreturn = bisreturn;
		this.bysalesvouchertype = bysalesvouchertype;
		this.bypostrecord = bypostrecord;
		this.iaccountreceivable = iaccountreceivable;
		this.iacceptmoney = iacceptmoney;
		this.igivechange = igivechange;
		this.ihandler = ihandler;
		this.ipayeer = ipayeer;
		this.imaker = imaker;
		this.iauditor = iauditor;
		this.iyear = iyear;
		this.imonth = imonth;
		this.iday = iday;
		this.dtmakedate = dtmakedate;
		this.dtauditdate = dtauditdate;
		this.bysalesvoucherstate = bysalesvoucherstate;
		this.bispay = bispay;
		this.bispayee = bispayee;
		this.ordersource=ordersource;
	}

	/** full constructor */
	public Stssalesvouchertab(StssalesvouchertabId id, Long iscenicid,
			Long iticketwinid, String szsalesvoucherno, Long ibusinessid,
			Long bisintegral, Long byprintinvoice, Long bysplitway,
			Long bisreturn, String bysalesvouchertype, Long isticketstationid,
			Long issalesvoucherid, String forcedrefund, String sztravelbillno,
			Long isalesmanid, Long iregionalid, String usid, String tdlx,
			String dyusid, Long bypostrecord, Double iaccountreceivable,
			Double iacceptmoney, Double igivechange, Long ihandler,
			Long ipayeer, Long imaker, Long iauditor, Long iyear, Long imonth,
			Long iday, String dtmakedate, String dtauditdate,
			Long bysalesvoucherstate, Long bispay, Long bispayee,String ordersource) {
		this.id = id;
		this.iscenicid = iscenicid;
		this.iticketwinid = iticketwinid;
		this.szsalesvoucherno = szsalesvoucherno;
		this.ibusinessid = ibusinessid;
		this.bisintegral = bisintegral;
		this.byprintinvoice = byprintinvoice;
		this.bysplitway = bysplitway;
		this.bisreturn = bisreturn;
		this.bysalesvouchertype = bysalesvouchertype;
		this.isticketstationid = isticketstationid;
		this.issalesvoucherid = issalesvoucherid;
		this.forcedrefund = forcedrefund;
		this.sztravelbillno = sztravelbillno;
		this.isalesmanid = isalesmanid;
		this.iregionalid = iregionalid;
		this.usid = usid;
		this.tdlx = tdlx;
		this.dyusid = dyusid;
		this.bypostrecord = bypostrecord;
		this.iaccountreceivable = iaccountreceivable;
		this.iacceptmoney = iacceptmoney;
		this.igivechange = igivechange;
		this.ihandler = ihandler;
		this.ipayeer = ipayeer;
		this.imaker = imaker;
		this.iauditor = iauditor;
		this.iyear = iyear;
		this.imonth = imonth;
		this.iday = iday;
		this.dtmakedate = dtmakedate;
		this.dtauditdate = dtauditdate;
		this.bysalesvoucherstate = bysalesvoucherstate;
		this.bispay = bispay;
		this.bispayee = bispayee;
		this.ordersource=ordersource;
	}

	// Property accessors

	public StssalesvouchertabId getId() {
		return this.id;
	}

	public void setId(StssalesvouchertabId id) {
		this.id = id;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIticketwinid() {
		return this.iticketwinid;
	}

	public void setIticketwinid(Long iticketwinid) {
		this.iticketwinid = iticketwinid;
	}

	public String getSzsalesvoucherno() {
		return this.szsalesvoucherno;
	}

	public void setSzsalesvoucherno(String szsalesvoucherno) {
		this.szsalesvoucherno = szsalesvoucherno;
	}

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public Long getBisintegral() {
		return this.bisintegral;
	}

	public void setBisintegral(Long bisintegral) {
		this.bisintegral = bisintegral;
	}

	public Long getByprintinvoice() {
		return this.byprintinvoice;
	}

	public void setByprintinvoice(Long byprintinvoice) {
		this.byprintinvoice = byprintinvoice;
	}

	public Long getBysplitway() {
		return this.bysplitway;
	}

	public void setBysplitway(Long bysplitway) {
		this.bysplitway = bysplitway;
	}

	public Long getBisreturn() {
		return this.bisreturn;
	}

	public void setBisreturn(Long bisreturn) {
		this.bisreturn = bisreturn;
	}

	public String getBysalesvouchertype() {
		return this.bysalesvouchertype;
	}

	public void setBysalesvouchertype(String bysalesvouchertype) {
		this.bysalesvouchertype = bysalesvouchertype;
	}

	public Long getIsticketstationid() {
		return this.isticketstationid;
	}

	public void setIsticketstationid(Long isticketstationid) {
		this.isticketstationid = isticketstationid;
	}

	public Long getIssalesvoucherid() {
		return this.issalesvoucherid;
	}

	public void setIssalesvoucherid(Long issalesvoucherid) {
		this.issalesvoucherid = issalesvoucherid;
	}

	public String getForcedrefund() {
		return this.forcedrefund;
	}

	public void setForcedrefund(String forcedrefund) {
		this.forcedrefund = forcedrefund;
	}

	public String getSztravelbillno() {
		return this.sztravelbillno;
	}

	public void setSztravelbillno(String sztravelbillno) {
		this.sztravelbillno = sztravelbillno;
	}

	public Long getIsalesmanid() {
		return this.isalesmanid;
	}

	public void setIsalesmanid(Long isalesmanid) {
		this.isalesmanid = isalesmanid;
	}

	public Long getIregionalid() {
		return this.iregionalid;
	}

	public void setIregionalid(Long iregionalid) {
		this.iregionalid = iregionalid;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getTdlx() {
		return this.tdlx;
	}

	public void setTdlx(String tdlx) {
		this.tdlx = tdlx;
	}

	public String getDyusid() {
		return this.dyusid;
	}

	public void setDyusid(String dyusid) {
		this.dyusid = dyusid;
	}

	public Long getBypostrecord() {
		return this.bypostrecord;
	}

	public void setBypostrecord(Long bypostrecord) {
		this.bypostrecord = bypostrecord;
	}

	public Double getIaccountreceivable() {
		return this.iaccountreceivable;
	}

	public void setIaccountreceivable(Double iaccountreceivable) {
		this.iaccountreceivable = iaccountreceivable;
	}

	public Double getIacceptmoney() {
		return this.iacceptmoney;
	}

	public void setIacceptmoney(Double iacceptmoney) {
		this.iacceptmoney = iacceptmoney;
	}

	public Double getIgivechange() {
		return this.igivechange;
	}

	public void setIgivechange(Double igivechange) {
		this.igivechange = igivechange;
	}

	public Long getIhandler() {
		return this.ihandler;
	}

	public void setIhandler(Long ihandler) {
		this.ihandler = ihandler;
	}

	public Long getIpayeer() {
		return this.ipayeer;
	}

	public void setIpayeer(Long ipayeer) {
		this.ipayeer = ipayeer;
	}

	public Long getImaker() {
		return this.imaker;
	}

	public void setImaker(Long imaker) {
		this.imaker = imaker;
	}

	public Long getIauditor() {
		return this.iauditor;
	}

	public void setIauditor(Long iauditor) {
		this.iauditor = iauditor;
	}

	public Long getIyear() {
		return this.iyear;
	}

	public void setIyear(Long iyear) {
		this.iyear = iyear;
	}

	public Long getImonth() {
		return this.imonth;
	}

	public void setImonth(Long imonth) {
		this.imonth = imonth;
	}

	public Long getIday() {
		return this.iday;
	}

	public void setIday(Long iday) {
		this.iday = iday;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getDtauditdate() {
		return this.dtauditdate;
	}

	public void setDtauditdate(String dtauditdate) {
		this.dtauditdate = dtauditdate;
	}

	public Long getBysalesvoucherstate() {
		return this.bysalesvoucherstate;
	}

	public void setBysalesvoucherstate(Long bysalesvoucherstate) {
		this.bysalesvoucherstate = bysalesvoucherstate;
	}

	public Long getBispay() {
		return this.bispay;
	}

	public void setBispay(Long bispay) {
		this.bispay = bispay;
	}

	public Long getBispayee() {
		return this.bispayee;
	}

	public void setBispayee(Long bispayee) {
		this.bispayee = bispayee;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public String getStriticketwinid() {
		return striticketwinid;
	}

	public void setStriticketwinid(String striticketwinid) {
		this.striticketwinid = striticketwinid;
	}

	public String getStribusinessid() {
		return stribusinessid;
	}

	public void setStribusinessid(String stribusinessid) {
		this.stribusinessid = stribusinessid;
	}

	public String getStriregionalid() {
		return striregionalid;
	}

	public void setStriregionalid(String striregionalid) {
		this.striregionalid = striregionalid;
	}

	public String getStrusid() {
		return strusid;
	}

	public void setStrusid(String strusid) {
		this.strusid = strusid;
	}

	public String getStrdyusid() {
		return strdyusid;
	}

	public void setStrdyusid(String strdyusid) {
		this.strdyusid = strdyusid;
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

	public String getStrbysalesvouchertype() {
		return strbysalesvouchertype;
	}

	public void setStrbysalesvouchertype(String strbysalesvouchertype) {
		this.strbysalesvouchertype = strbysalesvouchertype;
	}

	public String getStrtdlx() {
		return strtdlx;
	}

	public void setStrtdlx(String strtdlx) {
		this.strtdlx = strtdlx;
	}

	public String getStrihandler() {
		return strihandler;
	}

	public void setStrihandler(String strihandler) {
		this.strihandler = strihandler;
	}

	public String getStripayeer() {
		return stripayeer;
	}

	public void setStripayeer(String stripayeer) {
		this.stripayeer = stripayeer;
	}

	public String getStrimaker() {
		return strimaker;
	}

	public void setStrimaker(String strimaker) {
		this.strimaker = strimaker;
	}

	public String getStriauditor() {
		return striauditor;
	}

	public void setStriauditor(String striauditor) {
		this.striauditor = striauditor;
	}

	public Stssalesvoucherdetailstab getStssalesvoucherdetailstab() {
		return stssalesvoucherdetailstab;
	}

	public void setStssalesvoucherdetailstab(
			Stssalesvoucherdetailstab stssalesvoucherdetailstab) {
		this.stssalesvoucherdetailstab = stssalesvoucherdetailstab;
	}

	public String getIsettlementid() {
		return isettlementid;
	}

	public void setIsettlementid(String isettlementid) {
		this.isettlementid = isettlementid;
	}

	public String getSettlementid() {
		return settlementid;
	}

	public void setSettlementid(String settlementid) {
		this.settlementid = settlementid;
	}

	public Double getMhandcharge() {
		return mhandcharge;
	}

	public void setMhandcharge(Double mhandcharge) {
		this.mhandcharge = mhandcharge;
	}

	public String getOrdersource() {
		return ordersource;
	}

	public void setOrdersource(String ordersource) {
		this.ordersource = ordersource;
	}
	

}