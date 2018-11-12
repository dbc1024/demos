package com.ectrip.ec.model.user;

/**
 * DaoyouId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DaoyouId implements java.io.Serializable {

	// Fields

	private String usid;
	private String dyusid;

	// Constructors

	/** default constructor */
	public DaoyouId() {
	}

	/** full constructor */
	public DaoyouId(String usid, String dyusid) {
		this.usid = usid;
		this.dyusid = dyusid;
	}

	// Property accessors

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getDyusid() {
		return this.dyusid;
	}

	public void setDyusid(String dyusid) {
		this.dyusid = dyusid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DaoyouId))
			return false;
		DaoyouId castOther = (DaoyouId) other;

		return ((this.getUsid() == castOther.getUsid()) || (this.getUsid() != null
				&& castOther.getUsid() != null && this.getUsid().equals(
				castOther.getUsid())))
				&& ((this.getDyusid() == castOther.getDyusid()) || (this
						.getDyusid() != null
						&& castOther.getDyusid() != null && this.getDyusid()
						.equals(castOther.getDyusid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsid() == null ? 0 : this.getUsid().hashCode());
		result = 37 * result
				+ (getDyusid() == null ? 0 : this.getDyusid().hashCode());
		return result;
	}

}