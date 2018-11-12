package com.ectrip.sys.model.balance;

/**
 * JslistId entity. @author MyEclipse Persistence Tools
 */

public class JslistId implements java.io.Serializable {

	// Fields

	private String orid;//������
	private String pdno;//�����̱��

	// Constructors

	/** default constructor */
	public JslistId() {
	}

	/** full constructor */
	public JslistId(String orid, String pdno) {
		this.orid = orid;
		this.pdno = pdno;
	}

	// Property accessors

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getPdno() {
		return this.pdno;
	}

	public void setPdno(String pdno) {
		this.pdno = pdno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof JslistId))
			return false;
		JslistId castOther = (JslistId) other;

		return ((this.getOrid() == castOther.getOrid()) || (this.getOrid() != null
				&& castOther.getOrid() != null && this.getOrid().equals(
				castOther.getOrid())))
				&& ((this.getPdno() == castOther.getPdno()) || (this.getPdno() != null
						&& castOther.getPdno() != null && this.getPdno()
						.equals(castOther.getPdno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrid() == null ? 0 : this.getOrid().hashCode());
		result = 37 * result
				+ (getPdno() == null ? 0 : this.getPdno().hashCode());
		return result;
	}

}