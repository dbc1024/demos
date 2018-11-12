package com.ectrip.ec.model.order;

/**
 * YOrderlistId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HisyOrderlistId implements java.io.Serializable {

	// Fields

	private Long orderlistid;
	private String orid;
	private Long iscenicid;

	// Constructors

	/** default constructor */
	public HisyOrderlistId() {
	}

	/** full constructor */
	public HisyOrderlistId(Long orderlistid, String orid, Long iscenicid) {
		this.orderlistid = orderlistid;
		this.orid = orid;
		this.iscenicid = iscenicid;
	}

	// Property accessors

	public Long getOrderlistid() {
		return this.orderlistid;
	}

	public void setOrderlistid(Long orderlistid) {
		this.orderlistid = orderlistid;
	}

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
		if (!(other instanceof HisyOrderlistId))
			return false;
		HisyOrderlistId castOther = (HisyOrderlistId) other;

		return ((this.getOrderlistid() == castOther.getOrderlistid()) || (this
				.getOrderlistid() != null
				&& castOther.getOrderlistid() != null && this.getOrderlistid().equals(
				castOther.getOrderlistid())))
				&& ((this.getOrid() == castOther.getOrid()) || (this.getOrid() != null
						&& castOther.getOrid() != null && this.getOrid().equals(
						castOther.getOrid())))
				&& ((this.getIscenicid() == castOther.getIscenicid()) || (this
						.getIscenicid() != null
						&& castOther.getIscenicid() != null && this.getIscenicid().equals(
						castOther.getIscenicid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrderlistid() == null ? 0 : this.getOrderlistid().hashCode());
		result = 37 * result + (getOrid() == null ? 0 : this.getOrid().hashCode());
		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		return result;
	}

}