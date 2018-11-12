package com.ectrip.ticket.model.afcset;

import java.math.BigDecimal;

import javax.persistence.Embeddable;


/**
 * EsbgardengatetabId entity. @author MyEclipse Persistence Tools
 */

@Embeddable
public class EsbgardengatetabId implements java.io.Serializable {

	// Fields

	private Long igardengateid;
	private Long iscenicid;

	// Constructors

	/** default constructor */
	public EsbgardengatetabId() {
	}

	
	/** full constructor */
	public EsbgardengatetabId(Long igardengateid,
			Long iscenicid) {
		this.igardengateid = igardengateid;
		this.iscenicid = iscenicid;
	}

	// Property accessors

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EsbgardengatetabId))
			return false;
		EsbgardengatetabId castOther = (EsbgardengatetabId) other;

		return ((this.getIgardengateid() == castOther.getIgardengateid()) || (this
				.getIgardengateid() != null
				&& castOther.getIgardengateid() != null && this
				.getIgardengateid().equals(castOther.getIgardengateid())))
				&& ((this.getIscenicid() == castOther
						.getIscenicid()) || (this.getIscenicid() != null
						&& castOther.getIscenicid() != null && this
						.getIscenicid().equals(
								castOther.getIscenicid())));
	}

	public Long getIgardengateid() {
		return igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIgardengateid() == null ? 0 : this.getIgardengateid()
						.hashCode());
		result = 37
				* result
				+ (getIscenicid() == null ? 0 : this
						.getIscenicid().hashCode());
		return result;
	}

}