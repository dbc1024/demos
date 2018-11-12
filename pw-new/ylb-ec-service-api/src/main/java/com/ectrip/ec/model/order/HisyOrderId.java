package com.ectrip.ec.model.order;

/**
 * YOrderId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HisyOrderId implements java.io.Serializable {

	// Fields

	private String orid;
	private Long iscenicid;

	// Constructors

	/** default constructor */
	public HisyOrderId() {
	}

	/** full constructor */
	public HisyOrderId(String orid, Long iscenicid) {
		this.orid = orid;
		this.iscenicid = iscenicid;
	}

	// Property accessors

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HisyOrderId))
			return false;
		HisyOrderId castOther = (HisyOrderId) other;

		return ((this.getOrid() == castOther.getOrid()) || (this.getOrid() != null
				&& castOther.getOrid() != null && this.getOrid().equals(
				castOther.getOrid())))
				&& ((this.getIscenicid() == castOther.getIscenicid()) || (this
						.getIscenicid() != null
						&& castOther.getIscenicid() != null && this.getIscenicid().equals(
						castOther.getIscenicid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrid() == null ? 0 : this.getOrid().hashCode());
		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		return result;
	}

}