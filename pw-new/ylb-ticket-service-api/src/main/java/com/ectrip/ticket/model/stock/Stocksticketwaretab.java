package com.ectrip.ticket.model.stock;

/**
 * Stocksticketwaretab entity. @author MyEclipse Persistence Tools
 */

public class Stocksticketwaretab implements java.io.Serializable {

	// Fields

	private Long seq;    //主键
	private String sttype;//库存类型
	private Long objtype; //对象类型
	private Long pid;   //编号
	private Long stocknumber;//库存
	private String notea;  //备注
	private Long intextone;
	private Long intexttwo;
	private Long intextthree;
	private Long intextfour;
	private String charextone;
	private String charexttwo;
	private String charextthree;
	private String charextfour;

	private String stpmva;
	private String szproductname;  //名称

	// Constructors

	/** default constructor */
	public Stocksticketwaretab() {
	}

	/** minimal constructor */
	public Stocksticketwaretab(Long seq, String sttype,
							   Long objtype, Long pid, Long stocknumber) {
		this.seq = seq;
		this.sttype = sttype;
		this.objtype = objtype;
		this.pid = pid;
		this.stocknumber = stocknumber;
	}

	/** full constructor */
	public Stocksticketwaretab(Long seq, String sttype,
							   Long objtype, Long pid, Long stocknumber,
							   String notea, Long intextone, Long intexttwo,
							   Long intextthree, Long intextfour, String charextone,
							   String charexttwo, String charextthree, String charextfour) {
		this.seq = seq;
		this.sttype = sttype;
		this.objtype = objtype;
		this.pid = pid;
		this.stocknumber = stocknumber;
		this.notea = notea;
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

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getSttype() {
		return this.sttype;
	}

	public void setSttype(String sttype) {
		this.sttype = sttype;
	}

	public Long getObjtype() {
		return this.objtype;
	}

	public void setObjtype(Long objtype) {
		this.objtype = objtype;
	}

	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getStocknumber() {
		return this.stocknumber;
	}

	public void setStocknumber(Long stocknumber) {
		this.stocknumber = stocknumber;
	}

	public String getNotea() {
		return this.notea;
	}

	public void setNotea(String notea) {
		this.notea = notea;
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

	public String getSzproductname() {
		return szproductname;
	}

	public void setSzproductname(String szproductname) {
		this.szproductname = szproductname;
	}

	public String getStpmva() {
		return stpmva;
	}

	public void setStpmva(String stpmva) {
		this.stpmva = stpmva;
	}

}