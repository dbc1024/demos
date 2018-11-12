package com.ectrip.upload.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Attach entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="Attach")
public class Attach implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	@Id
	private Long upid;
	@Column(name="abelong")
	private String abelong;
	@Column(name="avalue")
	private String avalue;
	@Column(name="ordernumb")
	private Long ordernumb;

	// Constructors

	/** default constructor */
	public Attach() {
	}

	/** minimal constructor */
	public Attach(String abelong, Long ordernumb) {
		this.abelong = abelong;
		this.ordernumb = ordernumb;
	}

	/** full constructor */
	public Attach(String abelong, String avalue, Long ordernumb) {
		this.abelong = abelong;
		this.avalue = avalue;
		this.ordernumb = ordernumb;
	}

	// Property accessors

	public Long getUpid() {
		return this.upid;
	}

	public void setUpid(Long upid) {
		this.upid = upid;
	}

	public String getAbelong() {
		return this.abelong;
	}

	public void setAbelong(String abelong) {
		this.abelong = abelong;
	}

	public String getAvalue() {
		return this.avalue;
	}

	public void setAvalue(String avalue) {
		this.avalue = avalue;
	}

	public Long getOrdernumb() {
		return this.ordernumb;
	}

	public void setOrdernumb(Long ordernumb) {
		this.ordernumb = ordernumb;
	}

}