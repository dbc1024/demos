package com.ectrip.ec.model.order;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="M_ORDER")
public class MOrder implements java.io.Serializable {

	// Fields

	@Id
	private String orid;// 主订单流水号
	private String ortp;// 订单类型 01 预定 02 退票 03 增量订单
	private String usid; // 游客编号id
	private String orda; // 下单日期
	private String orti; // 下单时间
	private Long isjl; // 奖励订单
	private Double mont;// 订单金额
	private Double yhamnt;// 优惠金额
	private Double zfmont;// 支付的金额
	private String ddzt;// 订单状态 00 未支付 01已支付 02 已支付
	private Long isallcp;// 是否全部出票
	private String zffs;// 支付方式
	private String bank;// 支付银行
	private String zfusid;// 订单支付人
	private String payorid;// 支付订单号
	private String bankdata;// 支付日期
	private String banktime;// 支付时间
	private String note;// 备注
	private String srid;// 对应原订单号
	private String tpdate;//退订单时表示 退款时间,增量订单时表示增量时间 《退订已退订时要求，orda+orti 表示tpmont》
	private String tpfs;//修改方式 00 原始预定  02 出票前修改 01 出票后修改  
	private double tpsx;//退订手续费
	private double tpfl;//退订手续费率
	private double tpmont;//退订总金额  减去tpsx 才是实际退订金额
	private String stdt;//首次消费日期
	private Long isa;// 备用整数字段
	private Long isb;
	private Long isc;//出票员编号
	private Long isd;
	private Long ise;
	private Long isf;//原始订单中出票前修改标志位  是否有出票前修改
	private Long isg;
	private Long ish;//消耗积分数量()
	private Long isi;//消耗积分数量(月)
	private Long isj;//原始订单中出票后修改标志位 是否有出票后修改
	private String notea;// 备用字符串字段
	private String notej;//酒店到店时间
	private String notei;//畅游网淘宝订单
	private String noteh;//畅游网淘宝订单
	private String noteg;
	private String notef;//退订类别 00  网上自己退0订 01 出票前出票员退订 03 出票后出票员退订  04 确认停排系统退订 07 后台强制退订
	private String notee;//旅游卡卡号
	private String noted;
	private String notec;
	private String noteb;
	private String dtmakedate;
	private String ordersource;//订单来源
	private Long isDeleted;//订单是否被删除，0未删除，1已删除

	// not database Fields
	@Transient
	private String strusid; // 用户名(公司)
	@Transient
	private String striscenicid; // 服务商名
	@Transient
	private String strddzt; // 订单状态
	@Transient
	private String strzffs; // 支付方式
	@Transient
	private String strbank; // 银行

	@Transient
	private String pmcd;//订单状态 编号
	@Transient
	private String pmva;//订单状态 名称
	@Transient
	private String refundddzt; //退订订单状态

	@Transient
	private List<YOrder> yorders;
	@Transient
	private List<TOrder> torders;

	// Constructors

	/** default constructor */
	public MOrder() {
	}

	/** minimal constructor */
	public MOrder(String orid, String ortp, String usid, String orda,
			String orti, Long isjl, Double mont, Double zfmont, Long isallcp) {
		this.orid = orid;
		this.ortp = ortp;
		this.usid = usid;
		this.orda = orda;
		this.orti = orti;
		this.isjl = isjl;
		this.mont = mont;
		this.zfmont = zfmont;
		this.isallcp = isallcp;
	}

	
	// Property accessors
	

	public String getOrid() {
		return this.orid;
	}

	public MOrder(String orid, String ortp, String usid, String orda,
			String orti, Long isjl, Double mont, Double yhamnt, Double zfmont,
			String ddzt, Long isallcp, String zffs, String bank, String zfusid,
			String payorid, String bankdata, String banktime, String note,
			String srid, String tpdate, String tpfs, double tpsx, double tpfl,
			double tpmont, String stdt, Long isa, Long isb, Long isc, Long isd,
			Long ise, Long isf, Long isg, Long ish, Long isi, Long isj,
			String notea, String notej, String notei, String noteh,
			String noteg, String notef, String notee, String noted,
			String notec, String noteb,String ordersource) {
		super();
		this.orid = orid;
		this.ortp = ortp;
		this.usid = usid;
		this.orda = orda;
		this.orti = orti;
		this.isjl = isjl;
		this.mont = mont;
		this.yhamnt = yhamnt;
		this.zfmont = zfmont;
		this.ddzt = ddzt;
		this.isallcp = isallcp;
		this.zffs = zffs;
		this.bank = bank;
		this.zfusid = zfusid;
		this.payorid = payorid;
		this.bankdata = bankdata;
		this.banktime = banktime;
		this.note = note;
		this.srid = srid;
		this.tpdate = tpdate;
		this.tpfs = tpfs;
		this.tpsx = tpsx;
		this.tpfl = tpfl;
		this.tpmont = tpmont;
		this.stdt = stdt;
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
		this.ordersource=ordersource;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getOrtp() {
		return this.ortp;
	}

	public void setOrtp(String ortp) {
		this.ortp = ortp;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getOrda() {
		return this.orda;
	}

	public void setOrda(String orda) {
		this.orda = orda;
	}

	public String getOrti() {
		return this.orti;
	}

	public void setOrti(String orti) {
		this.orti = orti;
	}

	public Long getIsjl() {
		return this.isjl;
	}

	public void setIsjl(Long isjl) {
		this.isjl = isjl;
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

	public String getDdzt() {
		return this.ddzt;
	}

	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}

	public Long getIsallcp() {
		return this.isallcp;
	}

	public void setIsallcp(Long isallcp) {
		this.isallcp = isallcp;
	}

	public String getZffs() {
		return this.zffs;
	}

	public void setZffs(String zffs) {
		this.zffs = zffs;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getZfusid() {
		return this.zfusid;
	}

	public void setZfusid(String zfusid) {
		this.zfusid = zfusid;
	}

	public String getPayorid() {
		return this.payorid;
	}

	public void setPayorid(String payorid) {
		this.payorid = payorid;
	}

	public String getBankdata() {
		return this.bankdata;
	}

	public void setBankdata(String bankdata) {
		this.bankdata = bankdata;
	}

	public String getBanktime() {
		return this.banktime;
	}

	public void setBanktime(String banktime) {
		this.banktime = banktime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSrid() {
		return this.srid;
	}

	public void setSrid(String srid) {
		this.srid = srid;
	}

	public String getTpdate() {
		return tpdate;
	}

	public void setTpdate(String tpdate) {
		this.tpdate = tpdate;
	}

	public String getTpfs() {
		return tpfs;
	}

	public void setTpfs(String tpfs) {
		this.tpfs = tpfs;
	}

	public java.lang.Double getTpsx() {
		return tpsx;
	}

	public void setTpsx(Double tpsx) {
       if(tpsx==null){		
    	   this.tpsx=0.00;
       }else{
		
		this.tpsx = tpsx;
       }
	}

	public Double getTpfl() {
		return tpfl;
	}

	public void setTpfl(Double tpfl) {
		if(tpfl==null){
			this.tpfl=0.00;
		}else{
		   this.tpfl = tpfl;
		} 
	}

	public Double getTpmont() {
		return tpmont;
	}

	public void setTpmont(Double tpmont) {
		if(tpmont==null){
			this.tpmont=0.00;
		}else{
		   this.tpmont = tpmont;
		}
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

	public String getStrusid() {
		return strusid;
	}

	public void setStrusid(String strusid) {
		this.strusid = strusid;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public List<YOrder> getYorders() {
		return yorders;
	}

	public void setYorders(List<YOrder> yorders) {
		this.yorders = yorders;
	}

	public List<TOrder> getTorders() {
		return torders;
	}

	public void setTorders(List<TOrder> torders) {
		this.torders = torders;
	}

	public String getStrddzt() {
		return strddzt;
	}

	public void setStrddzt(String strddzt) {
		this.strddzt = strddzt;
	}

	public String getStrzffs() {
		return strzffs;
	}

	public void setStrzffs(String strzffs) {
		this.strzffs = strzffs;
	}

	public String getStrbank() {
		return strbank;
	}

	public void setStrbank(String strbank) {
		this.strbank = strbank;
	}

	public String getStdt() {
		return stdt;
	}

	public void setStdt(String stdt) {
		this.stdt = stdt;
	}

	public String getPmcd() {
		return pmcd;
	}

	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}

	public String getPmva() {
		return pmva;
	}

	public void setPmva(String pmva) {
		this.pmva = pmva;
	}

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public String getOrdersource() {
		return ordersource;
	}

	public void setOrdersource(String ordersource) {
		this.ordersource = ordersource;
	}

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getRefundddzt() {
		return refundddzt;
	}

	public void setRefundddzt(String refundddzt) {
		this.refundddzt = refundddzt;
	}
}
