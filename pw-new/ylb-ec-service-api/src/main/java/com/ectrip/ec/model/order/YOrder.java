package com.ectrip.ec.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Y_ORDER")
public class YOrder implements java.io.Serializable {

	// Fields

	@Id
	private YOrderId id;
	
	@Transient
	private String orid;//非数据库字段
	@Transient
	private String iscenicid;//非数据库字段
	@Transient
	private String szscenicname;//非数据库字段
	@Transient
	private String ortp;//非数据库字段
	private String scenictype;//服务商类别 01 景区 06酒店 07 旅行社 08 超市 09 租赁公司
	private String usid;//游客编号
	private Long ibusinessid;//业务id
	private String sztravelbillno;//行程单号
	private Long iregionalid;//客源地id
	private String tdlx;//团队性质
	private String ddzt;//订单状态
	private String dtstartdate;//游览开始日期
	private String dtenddate;//游览结束日期
	private String dyusid;//导游id
	private String ornm;//领票人姓名
	private String orzj;//领票人证件类别
	private String orhm;//领票人证件号码
	private String faxno;//传真号
	private String orph;//领票人电话
	private Double mont;//订单金额
	private Double yhamnt;//优惠金额
	private Double zfmont;//支付的金额
	private Double youfei;//邮费
	private String forcedrefund;//强行退票原因
	private String fempid;//强制退订员
	private String backautime;//后台审核日期
	private String bbackauditname;//后台订单审核人
	private String bbackautinote;//后台审核意见
	private Long isa;//备用整数字段
	private Long isb;
	private Long isc;
	private Long isd;
	private Long ise;
	private Long isf;
	private Long isg;
	private Long ish;//消耗积分(年)
	private Long isi;//消耗积分(月)
	private Long isj;
	private String notea;//备用字符串字段
	private String notej;
	private String notei;
	private String noteh;
	private String noteg;
	private String notef;
	private String notee;
	private String noted;
	private String notec;
	private String noteb;
	//退款时间
	private String tpdate;
	//退款金额
	private Double tpmont;
	
	//退票方式
	private String tpfs;
	private Double tpsx;
	@Transient
	private String rstate;//退款状态
	@Transient
	private String rdatetime;//退款时间
	// Constructors

	/** default constructor */
	public YOrder() {
	}

	/** minimal constructor */
	public YOrder(YOrderId id, String scenictype, String usid,
			Long ibusinessid, String dtstartdate, String dtenddate,
			String ornm, String orzj, String orhm, Double mont, Double zfmont,String rstate,String rdatetime) {
		this.id = id;
		this.scenictype = scenictype;
		this.usid = usid;
		this.ibusinessid = ibusinessid;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.ornm = ornm;
		this.orzj = orzj;
		this.orhm = orhm;
		this.mont = mont;
		this.zfmont = zfmont;
		this.rstate = rstate;
		this.rdatetime = rdatetime;
	}

	/** full constructor */
	public YOrder(YOrderId id, String scenictype, String usid,
			Long ibusinessid, String sztravelbillno, Long iregionalid,
			String tdlx, String ddzt, String dtstartdate, String dtenddate,
			String dyusid, String ornm, String orzj, String orhm, String faxno,
			String orph, Double mont, Double yhamnt, Double zfmont,
			Double youfei, String forcedrefund, String fempid,
			String backautime, String bbackauditname, String bbackautinote,
			Long isa, Long isb, Long isc, Long isd, Long ise, Long isf,
			Long isg, Long ish, Long isi, Long isj, String notea, String notej,
			String notei, String noteh, String noteg, String notef,
			String notee, String noted, String notec, String noteb) {
		this.id = id;
		this.scenictype = scenictype;
		this.usid = usid;
		this.ibusinessid = ibusinessid;
		this.sztravelbillno = sztravelbillno;
		this.iregionalid = iregionalid;
		this.tdlx = tdlx;
		this.ddzt = ddzt;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.dyusid = dyusid;
		this.ornm = ornm;
		this.orzj = orzj;
		this.orhm = orhm;
		this.faxno = faxno;
		this.orph = orph;
		this.mont = mont;
		this.yhamnt = yhamnt;
		this.zfmont = zfmont;
		this.youfei = youfei;
		this.forcedrefund = forcedrefund;
		this.fempid = fempid;
		this.backautime = backautime;
		this.bbackauditname = bbackauditname;
		this.bbackautinote = bbackautinote;
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

	public YOrderId getId() {
		return this.id;
	}

	public void setId(YOrderId id) {
		this.id = id;
	}

	public String getScenictype() {
		return this.scenictype;
	}

	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
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

	public String getSztravelbillno() {
		return this.sztravelbillno;
	}

	public void setSztravelbillno(String sztravelbillno) {
		this.sztravelbillno = sztravelbillno;
	}

	public Long getIregionalid() {
		return this.iregionalid;
	}

	public void setIregionalid(Long iregionalid) {
		this.iregionalid = iregionalid;
	}

	public String getTdlx() {
		return this.tdlx;
	}

	public void setTdlx(String tdlx) {
		this.tdlx = tdlx;
	}

	public String getDdzt() {
		return this.ddzt;
	}

	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
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

	public String getDyusid() {
		return this.dyusid;
	}

	public void setDyusid(String dyusid) {
		this.dyusid = dyusid;
	}

	public String getOrnm() {
		return this.ornm;
	}

	public void setOrnm(String ornm) {
		this.ornm = ornm;
	}

	public String getOrzj() {
		return this.orzj;
	}

	public void setOrzj(String orzj) {
		this.orzj = orzj;
	}

	public String getOrhm() {
		return this.orhm;
	}

	public void setOrhm(String orhm) {
		this.orhm = orhm;
	}

	public String getFaxno() {
		return this.faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getOrph() {
		return this.orph;
	}

	public void setOrph(String orph) {
		this.orph = orph;
	}

	public Double getMont() {
		return this.mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
	}

	public Double getYhamnt() {
		return this.yhamnt;
	}

	public void setYhamnt(Double yhamnt) {
		this.yhamnt = yhamnt;
	}

	public Double getZfmont() {
		return this.zfmont;
	}

	public void setZfmont(Double zfmont) {
		this.zfmont = zfmont;
	}

	public Double getYoufei() {
		return this.youfei;
	}

	public void setYoufei(Double youfei) {
		this.youfei = youfei;
	}

	public String getForcedrefund() {
		return this.forcedrefund;
	}

	public void setForcedrefund(String forcedrefund) {
		this.forcedrefund = forcedrefund;
	}

	public String getFempid() {
		return this.fempid;
	}

	public void setFempid(String fempid) {
		this.fempid = fempid;
	}

	public String getBackautime() {
		return this.backautime;
	}

	public void setBackautime(String backautime) {
		this.backautime = backautime;
	}

	public String getBbackauditname() {
		return this.bbackauditname;
	}

	public void setBbackauditname(String bbackauditname) {
		this.bbackauditname = bbackauditname;
	}

	public String getBbackautinote() {
		return this.bbackautinote;
	}

	public void setBbackautinote(String bbackautinote) {
		this.bbackautinote = bbackautinote;
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

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getOrtp() {
		return ortp;
	}

	public void setOrtp(String ortp) {
		this.ortp = ortp;
	}

	public String getTpdate() {
		return tpdate;
	}

	public void setTpdate(String tpdate) {
		this.tpdate = tpdate;
	}

	public Double getTpmont() {
		return tpmont;
	}

	public void setTpmont(Double tpmont) {
		this.tpmont = tpmont;
	}

	public String getTpfs() {
		return tpfs;
	}

	public void setTpfs(String tpfs) {
		this.tpfs = tpfs;
	}

	public Double getTpsx() {
		return tpsx;
	}

	public void setTpsx(Double tpsx) {
		this.tpsx = tpsx;
	}

	public String getRstate() {
		return rstate;
	}

	public void setRstate(String rstate) {
		this.rstate = rstate;
	}

	public String getRdatetime() {
		return rdatetime;
	}

	public void setRdatetime(String rdatetime) {
		this.rdatetime = rdatetime;
	}
}
