package com.ectrip.ticket.model.stock;

/**
 * Stocksalevolumetab entity. @author MyEclipse Persistence Tools
 */

public class Stocksalevolumetab implements java.io.Serializable {

	// Fields

	private Long sid;
	private Long seq;
	private String dtmakedate;
	private Long stocknumb;
	private Long intextone;
	private Long intexttwo;
	private Long intextthree;
	private Long intextfour;
	private String charextone;
	private String charexttwo;
	private String charextthree;
	private String charextfour;

	// Constructors

	/** default constructor */
	public Stocksalevolumetab() {
	}

	/** minimal constructor */
	public Stocksalevolumetab(Long sid, Long seq,
			String dtmakedate, Long stocknumb) {
		this.sid = sid;
		this.seq = seq;
		this.dtmakedate = dtmakedate;
		this.stocknumb = stocknumb;
	}

	/** full constructor */
	public Stocksalevolumetab(Long sid, Long seq,
			String dtmakedate, Long stocknumb, Long intextone,
			Long intexttwo, Long intextthree,
			Long intextfour, String charextone, String charexttwo,
			String charextthree, String charextfour) {
		this.sid = sid;
		this.seq = seq;
		this.dtmakedate = dtmakedate;
		this.stocknumb = stocknumb;
		this.intextone = intextone;
		this.intexttwo = intexttwo;
		this.intextthree = intextthree;
		this.intextfour = intextfour;
		this.charextone = charextone;
		this.charexttwo = charexttwo;
		this.charextthree = charextthree;
		this.charextfour = charextfour;
	}

	// Property accessors

	public Long getSid() {
		return this.sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getStocknumb() {
		return this.stocknumb;
	}

	public void setStocknumb(Long stocknumb) {
		this.stocknumb = stocknumb;
	}

	public Long getIntextone() {
		return this.intextone;
	}

	public void setIntextone(Long intextone) {
		this.intextone = intextone;
	}

	public Long getIntexttwo() {
		return this.intexttwo;
	}

	public void setIntexttwo(Long intexttwo) {
		this.intexttwo = intexttwo;
	}

	public Long getIntextthree() {
		return this.intextthree;
	}

	public void setIntextthree(Long intextthree) {
		this.intextthree = intextthree;
	}

	public Long getIntextfour() {
		return this.intextfour;
	}

	public void setIntextfour(Long intextfour) {
		this.intextfour = intextfour;
	}

	public String getCharextone() {
		return this.charextone;
	}

	public void setCharextone(String charextone) {
		this.charextone = charextone;
	}

	public String getCharexttwo() {
		return this.charexttwo;
	}

	public void setCharexttwo(String charexttwo) {
		this.charexttwo = charexttwo;
	}

	public String getCharextthree() {
		return this.charextthree;
	}

	public void setCharextthree(String charextthree) {
		this.charextthree = charextthree;
	}

	public String getCharextfour() {
		return this.charextfour;
	}

	public void setCharextfour(String charextfour) {
		this.charextfour = charextfour;
	}

}