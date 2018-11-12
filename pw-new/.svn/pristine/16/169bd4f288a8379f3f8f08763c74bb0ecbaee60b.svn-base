package com.ectrip.ticket.model.provider;

import javax.persistence.Embeddable;

/**
 * EdmticketcomposepricetabId entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class EdmticketcomposepricetabId implements java.io.Serializable {

	// Fields

	private Long icrowdkindpriceid; //售价id(关联售价表 Edmcrowdkindpricetab)
	private Long iticketcomposepriceid;

	// Constructors

	/** default constructor */
	public EdmticketcomposepricetabId() {
	}

	/** full constructor */
	public EdmticketcomposepricetabId(Long icrowdkindpriceid,
									  Long iticketcomposepriceid) {
		this.icrowdkindpriceid=icrowdkindpriceid;
		this.iticketcomposepriceid = iticketcomposepriceid;
	}

	// Property accessors


	public Long getIticketcomposepriceid() {
		return this.iticketcomposepriceid;
	}

	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}

	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}

	public void setIticketcomposepriceid(Long iticketcomposepriceid) {
		this.iticketcomposepriceid = iticketcomposepriceid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EdmticketcomposepricetabId))
			return false;
		EdmticketcomposepricetabId castOther = (EdmticketcomposepricetabId) other;

		return ((this.getIcrowdkindpriceid() == castOther
				.getIcrowdkindpriceid()) || (this.getIcrowdkindpriceid() != null
				&& castOther.getIcrowdkindpriceid() != null && this
				.getIcrowdkindpriceid().equals(castOther.getIcrowdkindpriceid())))
				&& ((this.getIticketcomposepriceid() == castOther
				.getIticketcomposepriceid()) || (this.getIticketcomposepriceid() != null
				&& castOther.getIticketcomposepriceid() != null && this
				.getIticketcomposepriceid().equals(
						castOther.getIticketcomposepriceid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIcrowdkindpriceid() == null ? 0 : this
				.getIcrowdkindpriceid().hashCode());
		result = 37
				* result
				+ (getIticketcomposepriceid() == null ? 0 : this
				.getIticketcomposepriceid().hashCode());
		return result;
	}

}