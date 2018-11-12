package com.ectrip.ec.model.order;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SeatordertabId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SeatordertabId implements java.io.Serializable {

	// Fields
	@Column
	private Long seq;
	@Column
	private String orid;
	@Column
	private Long iscenicid;//服务商
	
	@Column
	private Long orderlistid;
	@Column
	private Long zorderlistid;

	// Constructors

	/** default constructor */
	public SeatordertabId() {
	}

	/** full constructor */
	public SeatordertabId(Long seq, String orid,Long iscenicid, Long orderlistid,
			Long zorderlistid) {
		this.seq = seq;
		this.orid = orid;
		this.orderlistid = orderlistid;
		this.iscenicid=iscenicid;
		this.zorderlistid = zorderlistid;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

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

	public Long getZorderlistid() {
		return this.zorderlistid;
	}

	public void setZorderlistid(Long zorderlistid) {
		this.zorderlistid = zorderlistid;
	}
	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SeatordertabId))
			return false;
		SeatordertabId castOther = (SeatordertabId) other;

		return ((this.getSeq() == castOther.getSeq()) || (this.getSeq() != null
				&& castOther.getSeq() != null && this.getSeq().equals(
				castOther.getSeq())))
				&& ((this.getOrid() == castOther.getOrid()) || (this.getOrid() != null
						&& castOther.getOrid() != null && this.getOrid()
						.equals(castOther.getOrid())))
				&& ((this.getOrderlistid() == castOther.getOrderlistid()) || (this
						.getOrderlistid() != null
						&& castOther.getOrderlistid() != null && this
						.getOrderlistid().equals(castOther.getOrderlistid())))
				&& ((this.getZorderlistid() == castOther.getZorderlistid()) || (this
						.getZorderlistid() != null
						&& castOther.getZorderlistid() != null && this
						.getZorderlistid().equals(castOther.getZorderlistid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSeq() == null ? 0 : this.getSeq().hashCode());
		result = 37 * result
				+ (getOrid() == null ? 0 : this.getOrid().hashCode());
		result = 37
				* result
				+ (getOrderlistid() == null ? 0 : this.getOrderlistid()
						.hashCode());
		result = 37
				* result
				+ (getZorderlistid() == null ? 0 : this.getZorderlistid()
						.hashCode());
		return result;
	}

}
