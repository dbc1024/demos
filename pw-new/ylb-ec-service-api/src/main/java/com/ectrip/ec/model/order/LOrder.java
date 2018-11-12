package com.ectrip.ec.model.order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="L_ORDER")
public class LOrder implements java.io.Serializable{
	@Id
	private LOrderId id;

	private String usid; // 游客编号id
	private String orda; // 下单日期
	private String orti; // 下单时间
	private String dtstartdate;//启用日期
	private String dtenddate;//失效日期
	
	private Double mont;// 订单金额
	private Double yhamnt;// 优惠金额
	private Double zfmont;// 支付的金额
	private String ddzt;// 订单状态 00 未支付  02 已支付
	private String zffs;// 支付方式
	private String bank;// 支付方式
	private String banktime;// 支付时间
	private Long iemployeeid;//出票员
	private Long iticketwinid;//售票窗口
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
	public LOrderId getId() {
		return id;
	}
	public void setId(LOrderId id) {
		this.id = id;
	}
	public String getUsid() {
		return usid;
	}
	public void setUsid(String usid) {
		this.usid = usid;
	}
	public String getOrda() {
		return orda;
	}
	public void setOrda(String orda) {
		this.orda = orda;
	}
	public String getOrti() {
		return orti;
	}
	public void setOrti(String orti) {
		this.orti = orti;
	}
	public Double getMont() {
		return mont;
	}
	public void setMont(Double mont) {
		this.mont = mont;
	}
	public Double getYhamnt() {
		return yhamnt;
	}
	public void setYhamnt(Double yhamnt) {
		this.yhamnt = yhamnt;
	}
	public Double getZfmont() {
		return zfmont;
	}
	public void setZfmont(Double zfmont) {
		this.zfmont = zfmont;
	}
	public String getDdzt() {
		return ddzt;
	}
	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}
	public String getZffs() {
		return zffs;
	}
	public void setZffs(String zffs) {
		this.zffs = zffs;
	}

	public String getBanktime() {
		return banktime;
	}
	public void setBanktime(String banktime) {
		this.banktime = banktime;
	}
	public Long getIemployeeid() {
		return iemployeeid;
	}
	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}
	public Long getIticketwinid() {
		return iticketwinid;
	}
	public void setIticketwinid(Long iticketwinid) {
		this.iticketwinid = iticketwinid;
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
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	


}
