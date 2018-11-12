package com.ectrip.sys.model.balance;

/**
 * һ��ͨ���ͽ���
 * Vipbalance entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Vipbalancetype implements java.io.Serializable {

	// Fields

	private String usid;
	private String acctype;
	private Double point;
	private Integer version;//�汾����
	// Constructors

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/** default constructor */
	public Vipbalancetype() {
	}

	/** full constructor */
	public Vipbalancetype(String usid, String acctype, Double point) {
		this.usid = usid;
		this.acctype = acctype;
		this.point = point;
	}

	// Property accessors

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getAcctype() {
		return this.acctype;
	}

	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}

	public Double getPoint() {
		return this.point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

}