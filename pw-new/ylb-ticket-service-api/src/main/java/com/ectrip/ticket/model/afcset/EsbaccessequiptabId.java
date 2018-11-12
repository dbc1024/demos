package com.ectrip.ticket.model.afcset;

import javax.persistence.Embeddable;

/**
 * EsbaccessequiptabId entity. @author MyEclipse Persistence Tools
 */

@Embeddable
public class EsbaccessequiptabId implements java.io.Serializable {

	// Fields

	private Long iaccessequipid;
	private Long iscenicid;
	private Long igardengateid;

	// Constructors

	/** default constructor */
	public EsbaccessequiptabId() {
	}

	/** full constructor */
	public EsbaccessequiptabId(Long iaccessequipid, Long iscenicid) {
		this.iaccessequipid = iaccessequipid;
		this.iscenicid = iscenicid;
	}

	public EsbaccessequiptabId(Long iaccessequipid, Long iscenicid, Long igardengateid) {
		this.iaccessequipid = iaccessequipid;
		this.iscenicid = iscenicid;
		this.igardengateid = igardengateid;
	}

	// Property accessors

	public Long getIaccessequipid() {
		return this.iaccessequipid;
	}

	public Long getIgardengateid() {
		return igardengateid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public void setIaccessequipid(String iaccessequipid) {
		if (null != iaccessequipid && !"".equals(iaccessequipid)) {
			this.iaccessequipid = new Long(iaccessequipid);
		} else {
			this.iaccessequipid = new Long(0);
		}
	}

	public void setIaccessequipid(Long iaccessequipid) {
		this.iaccessequipid = iaccessequipid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EsbaccessequiptabId))
			return false;
		EsbaccessequiptabId castOther = (EsbaccessequiptabId) other;

		return ((this.getIaccessequipid() == castOther.getIaccessequipid()) || (this
				.getIaccessequipid() != null
				&& castOther.getIaccessequipid() != null && this
				.getIaccessequipid().equals(castOther.getIaccessequipid())))
				&& ((this.getIscenicid() == castOther.getIscenicid()) || (this
						.getIscenicid() != null
						&& castOther.getIscenicid() != null && this
						.getIscenicid().equals(castOther.getIscenicid())));
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
				+ (getIaccessequipid() == null ? 0 : this.getIaccessequipid()
						.hashCode());
		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		return result;
	}

}