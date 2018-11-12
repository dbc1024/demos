package com.ectrip.ticket.model.provider;

/**
 * HotelsvcId entity. @author MyEclipse Persistence Tools
 */

public class HotelsvcId implements java.io.Serializable {

	// Fields

	private String svid;//服务商项
	private Long iscenicid;//服务商编号

	// Constructors

	/** default constructor */
	public HotelsvcId() {
	}

	/** full constructor */
	public HotelsvcId(String svid, Long iscenicid) {
		this.svid = svid;
		this.iscenicid = iscenicid;
	}

	// Property accessors

	public String getSvid() {
		return this.svid;
	}

	public void setSvid(String svid) {
		this.svid = svid;
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
		if (!(other instanceof HotelsvcId))
			return false;
		HotelsvcId castOther = (HotelsvcId) other;

		return ((this.getSvid() == castOther.getSvid()) || (this.getSvid() != null
				&& castOther.getSvid() != null && this.getSvid().equals(
				castOther.getSvid())))
				&& ((this.getIscenicid() == castOther.getIscenicid()) || (this
				.getIscenicid() != null
				&& castOther.getIscenicid() != null && this
				.getIscenicid().equals(castOther.getIscenicid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSvid() == null ? 0 : this.getSvid().hashCode());
		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		return result;
	}

}