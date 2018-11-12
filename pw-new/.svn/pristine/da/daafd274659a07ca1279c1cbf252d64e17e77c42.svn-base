package com.ectrip.ticket.service.model;

/**
 * DataaccepttabId entity. @author MyEclipse Persistence Tools
 */

public class DataaccepttabId implements java.io.Serializable {

	// Fields

	private String providerid;
	private String method;
	private String infodate;

	// Constructors

	/** default constructor */
	public DataaccepttabId() {
	}

	/** full constructor */
	public DataaccepttabId(String providerid, String method, String infodate) {
		this.providerid = providerid;
		this.method = method;
		this.infodate = infodate;
	}

	// Property accessors

	public String getProviderid() {
		return this.providerid;
	}

	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getInfodate() {
		return this.infodate;
	}

	public void setInfodate(String infodate) {
		this.infodate = infodate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DataaccepttabId))
			return false;
		DataaccepttabId castOther = (DataaccepttabId) other;

		return ((this.getProviderid() == castOther.getProviderid()) || (this
				.getProviderid() != null && castOther.getProviderid() != null && this
				.getProviderid().equals(castOther.getProviderid())))
				&& ((this.getMethod() == castOther.getMethod()) || (this
						.getMethod() != null && castOther.getMethod() != null && this
						.getMethod().equals(castOther.getMethod())))
				&& ((this.getInfodate() == castOther.getInfodate()) || (this
						.getInfodate() != null
						&& castOther.getInfodate() != null && this
						.getInfodate().equals(castOther.getInfodate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getProviderid() == null ? 0 : this.getProviderid()
						.hashCode());
		result = 37 * result
				+ (getMethod() == null ? 0 : this.getMethod().hashCode());
		result = 37 * result
				+ (getInfodate() == null ? 0 : this.getInfodate().hashCode());
		return result;
	}

}