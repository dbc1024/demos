package com.ectrip.sys.model.balance;

/**
 * Zhifu entity. @author MyEclipse Persistence Tools
 */

public class Zhifu implements java.io.Serializable {

	// Fields

	private String payid;
	private String orid;
	private String usid;
	private Double mont;
	private String orti;
	private String bank;
	private Integer isok;
	private String bankorid;
	private String banktime;
	private String des;
	private Double tdmont;
	private Integer isdz;
	private Integer seq;
	private Double yhpoint;
	private Integer yhseq;

	// Constructors

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/** default constructor */
	public Zhifu() {
	}

	/** minimal constructor */
	public Zhifu(String payid, String orid, Double mont, String orti, String bank, Integer isok, Double tdmont) {
		this.payid = payid;
		this.orid = orid;
		this.mont = mont;
		this.orti = orti;
		this.bank = bank;
		this.isok = isok;
		this.tdmont = tdmont;
	}

	/** full constructor */
	public Zhifu(String payid, String orid, String usid, Double mont, String orti, String bank, Integer isok,
			String bankorid, String banktime, String des, Double tdmont, Integer isdz,Double yhpoint,Integer yhseq) {
		this.payid = payid;
		this.orid = orid;
		this.usid = usid;
		this.mont = mont;
		this.orti = orti;
		this.bank = bank;
		this.isok = isok;
		this.bankorid = bankorid;
		this.banktime = banktime;
		this.des = des;
		this.tdmont = tdmont;
		this.isdz = isdz;
		this.yhpoint = yhpoint;
		this.yhseq = yhseq;
	}

	// Property accessors

	public String getPayid() {
		return this.payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Double getMont() {
		return this.mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
	}

	public String getOrti() {
		return this.orti;
	}

	public void setOrti(String orti) {
		this.orti = orti;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Integer getIsok() {
		return this.isok;
	}

	public void setIsok(Integer isok) {
		this.isok = isok;
	}

	public String getBankorid() {
		return this.bankorid;
	}

	public void setBankorid(String bankorid) {
		this.bankorid = bankorid;
	}

	public String getBanktime() {
		return this.banktime;
	}

	public void setBanktime(String banktime) {
		this.banktime = banktime;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Double getTdmont() {
		return this.tdmont;
	}

	public void setTdmont(Double tdmont) {
		this.tdmont = tdmont;
	}

	public Integer getIsdz() {
		return this.isdz;
	}

	public void setIsdz(Integer isdz) {
		this.isdz = isdz;
	}

	public Double getYhpoint() {
		return yhpoint;
	}

	public void setYhpoint(Double yhpoint) {
		this.yhpoint = yhpoint;
	}

	public Integer getYhseq() {
		return yhseq;
	}

	public void setYhseq(Integer yhseq) {
		this.yhseq = yhseq;
	}

}