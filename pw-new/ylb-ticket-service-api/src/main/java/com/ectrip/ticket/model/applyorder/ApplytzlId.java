package com.ectrip.ticket.model.applyorder;


/**
 * ApplytzlId entity. @author MyEclipse Persistence Tools
 */

public class ApplytzlId  implements java.io.Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = -4850089525401831529L;

	private Long zorderlistid;//明细流水子表id
	private Long orderlistid;//明细流水id
	private Long iscenicid;//服务商id
	private String aorid;//订单号

	public ApplytzlId() {
	}

	public ApplytzlId(Long zorderlistid, Long orderlistid, String aorid,
					  Long iscenicid) {
		this.zorderlistid = zorderlistid;
		this.orderlistid = orderlistid;
		this.aorid = aorid;
		this.iscenicid = iscenicid;
	}

	// Fields    
	public Long getZorderlistid() {
		return zorderlistid;
	}
	public void setZorderlistid(Long zorderlistid) {
		this.zorderlistid = zorderlistid;
	}
	public Long getOrderlistid() {
		return orderlistid;
	}
	public void setOrderlistid(Long orderlistid) {
		this.orderlistid = orderlistid;
	}
	public String getAorid() {
		return aorid;
	}
	public void setAorid(String aorid) {
		this.aorid = aorid;
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
		if (!(other instanceof ApplytzlId))
			return false;
		ApplytzlId castOther = (ApplytzlId) other;

		return ((this.getZorderlistid() == castOther.getZorderlistid()) || (this
				.getZorderlistid() != null
				&& castOther.getZorderlistid() != null && this.getZorderlistid()
				.equals(castOther.getZorderlistid())))
				&& ((this.getOrderlistid() == castOther.getOrderlistid()) || (this
				.getOrderlistid() != null
				&& castOther.getOrderlistid() != null && this.getOrderlistid()
				.equals(castOther.getOrderlistid())))
				&& ((this.getAorid() == castOther.getAorid()) || (this.getAorid() != null
				&& castOther.getAorid() != null && this.getAorid().equals(
				castOther.getAorid())))
				&& ((this.getIscenicid() == castOther.getIscenicid()) || (this
				.getIscenicid() != null
				&& castOther.getIscenicid() != null && this.getIscenicid().equals(
				castOther.getIscenicid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getZorderlistid() == null ? 0 : this.getZorderlistid().hashCode());
		result = 37 * result
				+ (getOrderlistid() == null ? 0 : this.getOrderlistid().hashCode());
		result = 37 * result + (getAorid() == null ? 0 : this.getAorid().hashCode());
		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		return result;
	}

}