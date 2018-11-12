package com.ectrip.ec.model.order;

/**
 * TRealnameId entity. @author MyEclipse Persistence Tools
 */

public class TRealnameId implements java.io.Serializable {

	// Fields

	private String orid;
	private Long orderlistid;
	private Long iscenicid;
	private Long seq;

	// Constructors

	/** default constructor */
	public TRealnameId() {
	}

	/** full constructor */
	public TRealnameId(String orid, Long orderlistid,
			Long iscenicid, Long seq) {
		this.orid = orid;
		this.orderlistid = orderlistid;
		this.iscenicid = iscenicid;
		this.seq = seq;
	}

	// Property accessors

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public Long getOrderlistid() {
		return this.orderlistid;
	}

	public void setOrderlistid(Long orderlistid) {
		this.orderlistid = orderlistid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TRealnameId))
			return false;
		TRealnameId castOther = (TRealnameId) other;

		return ((this.getOrid() == castOther.getOrid()) || (this.getOrid() != null
				&& castOther.getOrid() != null && this.getOrid().equals(
				castOther.getOrid())))
				&& ((this.getOrderlistid() == castOther.getOrderlistid()) || (this
						.getOrderlistid() != null
						&& castOther.getOrderlistid() != null && this
						.getOrderlistid().equals(castOther.getOrderlistid())))
				&& ((this.getIscenicid() == castOther.getIscenicid()) || (this
						.getIscenicid() != null
						&& castOther.getIscenicid() != null && this
						.getIscenicid().equals(castOther.getIscenicid())))
				&& ((this.getSeq() == castOther.getSeq()) || (this.getSeq() != null
						&& castOther.getSeq() != null && this.getSeq().equals(
						castOther.getSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrid() == null ? 0 : this.getOrid().hashCode());
		result = 37
				* result
				+ (getOrderlistid() == null ? 0 : this.getOrderlistid()
						.hashCode());
		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		result = 37 * result
				+ (getSeq() == null ? 0 : this.getSeq().hashCode());
		return result;
	}

}