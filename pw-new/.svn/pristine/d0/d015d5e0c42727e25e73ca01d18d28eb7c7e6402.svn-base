package com.ectrip.sys.model.syspar;

import javax.persistence.Embeddable;

/**
 * Sysparv5Id entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class Sysparv5Id implements java.io.Serializable {

	// Fields

	private String pmky;
	private String pmcd;

	// Constructors

	/** default constructor */
	public Sysparv5Id() {
	}

	/** full constructor */
	public Sysparv5Id(String pmky, String pmcd) {
		this.pmky = pmky;
		this.pmcd = pmcd;
	}

	// Property accessors

	public String getPmky() {
		return this.pmky;
	}

	public void setPmky(String pmky) {
		this.pmky = pmky;
	}

	public String getPmcd() {
		return this.pmcd;
	}

	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Sysparv5Id))
			return false;
		Sysparv5Id castOther = (Sysparv5Id) other;

		return ((this.getPmky() == castOther.getPmky()) || (this.getPmky() != null && castOther.getPmky() != null && this.getPmky().equals(castOther.getPmky())))
				&& ((this.getPmcd() == castOther.getPmcd()) || (this.getPmcd() != null && castOther.getPmcd() != null && this.getPmcd().equals(
						castOther.getPmcd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getPmky() == null ? 0 : this.getPmky().hashCode());
		result = 37 * result + (getPmcd() == null ? 0 : this.getPmcd().hashCode());
		return result;
	}

}