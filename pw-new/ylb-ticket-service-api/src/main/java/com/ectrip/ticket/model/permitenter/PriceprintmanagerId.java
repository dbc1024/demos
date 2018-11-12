package com.ectrip.ticket.model.permitenter;

import javax.persistence.Embeddable;

/**
 * PriceprintmanagerId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PriceprintmanagerId implements java.io.Serializable {

	// Fields

	private Long icrowdkindpriceid;//价格ID
	private String printno;//打印编码

	// Constructors

	/** default constructor */
	public PriceprintmanagerId() {
	}

	/** full constructor */
	public PriceprintmanagerId(Long icrowdkindpriceid, String printno) {
		this.icrowdkindpriceid = icrowdkindpriceid;
		this.printno = printno;
	}

	// Property accessors

	public Long getIcrowdkindpriceid() {
		return this.icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public String getPrintno() {
		return this.printno;
	}

	public void setPrintno(String printno) {
		this.printno = printno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PriceprintmanagerId))
			return false;
		PriceprintmanagerId castOther = (PriceprintmanagerId) other;

		return ((this.getIcrowdkindpriceid() == castOther
				.getIcrowdkindpriceid()) || (this.getIcrowdkindpriceid() != null
				&& castOther.getIcrowdkindpriceid() != null && this
				.getIcrowdkindpriceid()
				.equals(castOther.getIcrowdkindpriceid())))
				&& ((this.getPrintno() == castOther.getPrintno()) || (this
						.getPrintno() != null && castOther.getPrintno() != null && this
						.getPrintno().equals(castOther.getPrintno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIcrowdkindpriceid() == null ? 0 : this
						.getIcrowdkindpriceid().hashCode());
		result = 37 * result
				+ (getPrintno() == null ? 0 : this.getPrintno().hashCode());
		return result;
	}

}
