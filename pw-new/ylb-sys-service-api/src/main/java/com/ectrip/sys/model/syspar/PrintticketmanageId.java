package com.ectrip.sys.model.syspar;

import javax.persistence.Embeddable;

/**
 * PrintticketmanageId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Embeddable
public class PrintticketmanageId implements java.io.Serializable {

	// Fields

	private Long iscenicid;
	private Long ibusinessid;
	private String printno;

	// Constructors

	/** default constructor */
	public PrintticketmanageId() {
	}

	/** full constructor */
	public PrintticketmanageId(Long iscenicid, Long ibusinessid, String printno) {
		this.iscenicid = iscenicid;
		this.ibusinessid = ibusinessid;
		this.printno = printno;
	}

	// Property accessors

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public String getPrintno() {
		return this.printno;
	}

	public void setPrintno(String printno) {
		this.printno = printno;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PrintticketmanageId))
			return false;
		PrintticketmanageId castOther = (PrintticketmanageId) other;

		return ((this.getIscenicid() == castOther.getIscenicid()) || (this
				.getIscenicid() != null
				&& castOther.getIscenicid() != null && this.getIscenicid().equals(
				castOther.getIscenicid())))
				&& ((this.getIbusinessid() == castOther.getIbusinessid()) || (this
						.getIbusinessid() != null
						&& castOther.getIbusinessid() != null && this.getIbusinessid()
						.equals(castOther.getIbusinessid())))
				&& ((this.getPrintno() == castOther.getPrintno()) || (this.getPrintno() != null
						&& castOther.getPrintno() != null && this.getPrintno().equals(
						castOther.getPrintno())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIscenicid() == null ? 0 : this.getIscenicid().hashCode());
		result = 37 * result
				+ (getIbusinessid() == null ? 0 : this.getIbusinessid().hashCode());
		result = 37 * result
				+ (getPrintno() == null ? 0 : this.getPrintno().hashCode());
		return result;
	}

}