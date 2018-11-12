package com.ectrip.ec.model.user;

/**
 * Credit entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Credit implements java.io.Serializable {

	// Fields

	private String usid;
	private Long creditnumb;

	// Constructors

	/** default constructor */
	public Credit() {
	}

	/** full constructor */
	public Credit(String usid, Long creditnumb) {
		this.usid = usid;
		this.creditnumb = creditnumb;
	}

	// Property accessors

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getCreditnumb() {
		return this.creditnumb;
	}

	public void setCreditnumb(Long creditnumb) {
		this.creditnumb = creditnumb;
	}

}