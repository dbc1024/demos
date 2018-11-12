package com.ectrip.ticket.sale.service.cytterminal.model;

/**
 * CyttasktabId entity. @author MyEclipse Persistence Tools
 */

public class CyttasktabId implements java.io.Serializable {

	// Fields

	private String orid;
	private String sign;

	// Constructors

	/** default constructor */
	public CyttasktabId() {
	}

	/** full constructor */
	public CyttasktabId(String orid, String sign) {
		this.orid = orid;
		this.sign = sign;
	}

	// Property accessors

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CyttasktabId))
			return false;
		CyttasktabId castOther = (CyttasktabId) other;

		return ((this.getOrid() == castOther.getOrid()) || (this.getOrid() != null
				&& castOther.getOrid() != null && this.getOrid().equals(
				castOther.getOrid())))
				&& ((this.getSign() == castOther.getSign()) || (this.getSign() != null
						&& castOther.getSign() != null && this.getSign()
						.equals(castOther.getSign())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrid() == null ? 0 : this.getOrid().hashCode());
		result = 37 * result
				+ (getSign() == null ? 0 : this.getSign().hashCode());
		return result;
	}

}