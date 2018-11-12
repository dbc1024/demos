package com.ectrip.ticket.model.venuemarketing;

/**
 * Description:产品审核记录(竹筏审核记录表)
 *
 * @author Jzhenhua<br>
 *         Date 2011-10-26
 */
public class Productaudit implements java.io.Serializable {

	// Database's Fields
	private Long iproductauditid; // 审核ID
	private String auditdate; // 日期
	private Long itickettypeid;// 产品ID
	private Long iemployeeid;// 操作人编号
	private String operadate;// 操作时间
	private Long istate; // 审核状态0:未审核,1:审核通过,2:审核未通过
	private Long operatype;// 操作类型0:提交,1:审核

	// not database' fields
	private String stritickettypeid; // 产品名称
	private String striemployeeid; // 操作人姓名

	// Constructors

	/** default constructor */
	public Productaudit() {
	}

	/** full constructor */
	public Productaudit(Long iproductauditid, String auditdate,
						Long itickettypeid, Long iemployeeid, String operadate, Long istate) {
		this.iproductauditid = iproductauditid;
		this.auditdate = auditdate;
		this.itickettypeid = itickettypeid;
		this.iemployeeid = iemployeeid;
		this.operadate = operadate;
		this.istate = istate;
	}

	// Property accessors

	public Long getIproductauditid() {
		return this.iproductauditid;
	}

	public void setIproductauditid(Long iproductauditid) {
		this.iproductauditid = iproductauditid;
	}

	public String getAuditdate() {
		return this.auditdate;
	}

	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public String getOperadate() {
		return this.operadate;
	}

	public void setOperadate(String operadate) {
		this.operadate = operadate;
	}

	public Long getIstate() {
		return this.istate;
	}

	public void setIstate(Long istate) {
		this.istate = istate;
	}

	public Long getOperatype() {
		return operatype;
	}

	public void setOperatype(Long operatype) {
		this.operatype = operatype;
	}

	public String getStritickettypeid() {
		return stritickettypeid;
	}

	public void setStritickettypeid(String stritickettypeid) {
		this.stritickettypeid = stritickettypeid;
	}

	public String getStriemployeeid() {
		return striemployeeid;
	}

	public void setStriemployeeid(String striemployeeid) {
		this.striemployeeid = striemployeeid;
	}

}