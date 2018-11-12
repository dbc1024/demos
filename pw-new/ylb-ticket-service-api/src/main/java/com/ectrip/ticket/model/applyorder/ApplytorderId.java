package com.ectrip.ticket.model.applyorder;


/**
 * ApplytorderId entity. @author MyEclipse Persistence Tools
 */

public class ApplytorderId  implements java.io.Serializable {


	private static final long serialVersionUID = 2093010475385573879L;
	// Fields    

	private String aorid;//订单号
	private Long iscenicid;//服务商id

	// Constructors

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


	/** default constructor */
	public ApplytorderId() {
	}


	public ApplytorderId(String aorid, Long iscenicid) {
		this.aorid = aorid;
		this.iscenicid = iscenicid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ApplytorderId))
			return false;
		ApplytorderId castOther = (ApplytorderId) other;

		return ((this.getAorid() == castOther.getAorid()) || (this.getAorid() != null
				&& castOther.getAorid() != null && this.getAorid().equals(
				castOther.getAorid())))
				&& ((this.getIscenicid() == castOther.getIscenicid()) || (this
				.getIscenicid() != null
				&& castOther.getIscenicid() != null && this
				.getIscenicid().equals(castOther.getIscenicid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAorid() == null ? 0 : this.getAorid().hashCode());
		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		return result;
	}

}