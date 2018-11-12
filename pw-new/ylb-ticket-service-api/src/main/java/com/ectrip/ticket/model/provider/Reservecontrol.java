package com.ectrip.ticket.model.provider;

public class Reservecontrol implements java.io.Serializable {

	// Fields

	private Long reserveid; // 预留控制ID
	private Long productcontrolid; // 控制ID
	private Long ibusinessid; // 业务ID
	private String usid; // 用户ID
	private Long reservednumber; // 预留量
	private Long reservedsalenumber; // 已售预留量

	// not database's fields
	private String stribusinessid; // 业务类型
	private String strusid; // 用户名
	private String szbusinessname;
	private String corpname;

	// Constructors

	/** default constructor */
	public Reservecontrol() {
	}

	/** minimal constructor */
	public Reservecontrol(Long reserveid, Long productcontrolid,
			Long ibusinessid, Long reservednumber, Long reservedsalenumber) {
		this.reserveid = reserveid;
		this.productcontrolid = productcontrolid;
		this.ibusinessid = ibusinessid;
		this.reservednumber = reservednumber;
		this.reservedsalenumber = reservedsalenumber;
	}
   
	/** full constructor */
	public Reservecontrol(Long reserveid, Long productcontrolid,
			Long ibusinessid, String usid, Long reservednumber,
			Long reservedsalenumber) {
		this.reserveid = reserveid;
		this.productcontrolid = productcontrolid;
		this.ibusinessid = ibusinessid;
		this.usid = usid;
		this.reservednumber = reservednumber;
		this.reservedsalenumber = reservedsalenumber;
	}

	// Property accessors

	public Long getReserveid() {
		return this.reserveid;
	}

	public void setReserveid(Long reserveid) {
		this.reserveid = reserveid;
	}

	public Long getProductcontrolid() {
		return this.productcontrolid;
	}

	public void setProductcontrolid(Long productcontrolid) {
		this.productcontrolid = productcontrolid;
	}

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getReservednumber() {
		return this.reservednumber;
	}

	public void setReservednumber(Long reservednumber) {
		this.reservednumber = reservednumber;
	}

	public Long getReservedsalenumber() {
		return this.reservedsalenumber;
	}

	public void setReservedsalenumber(Long reservedsalenumber) {
		this.reservedsalenumber = reservedsalenumber;
	}

	public String getStribusinessid() {
		return stribusinessid;
	}

	public void setStribusinessid(String stribusinessid) {
		this.stribusinessid = stribusinessid;
	}

	public String getStrusid() {
		return strusid;
	}

	public void setStrusid(String strusid) {
		this.strusid = strusid;
	}

	public String getSzbusinessname() {
		return szbusinessname;
	}

	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

}
