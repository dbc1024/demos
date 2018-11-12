package com.ectrip.sys.model.employee;

import javax.persistence.Embeddable;

/**
 * CompanyscenicId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class CompanyscenicId implements java.io.Serializable {

	// Fields

	private Long icompanyinfoid;
	private Long iscenicid;

	// Constructors

	/** default constructor */
	public CompanyscenicId() {
	}

	/** full constructor */
	public CompanyscenicId(Long icompanyinfoid, Long iscenicid) {
		this.icompanyinfoid = icompanyinfoid;
		this.iscenicid = iscenicid;
	}

	// Property accessors

	public Long getIcompanyinfoid() {
		return this.icompanyinfoid;
	}

	public void setIcompanyinfoid(Long icompanyinfoid) {
		this.icompanyinfoid = icompanyinfoid;
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
		if (!(other instanceof CompanyscenicId))
			return false;
		CompanyscenicId castOther = (CompanyscenicId) other;

		return ((this.getIcompanyinfoid() == castOther.getIcompanyinfoid()) || (this
				.getIcompanyinfoid() != null
				&& castOther.getIcompanyinfoid() != null && this.getIcompanyinfoid()
				.equals(castOther.getIcompanyinfoid())))
				&& ((this.getIscenicid() == castOther.getIscenicid()) || (this
						.getIscenicid() != null
						&& castOther.getIscenicid() != null && this.getIscenicid().equals(
						castOther.getIscenicid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getIcompanyinfoid() == null ? 0 : this.getIcompanyinfoid()
						.hashCode());
		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		return result;
	}

}