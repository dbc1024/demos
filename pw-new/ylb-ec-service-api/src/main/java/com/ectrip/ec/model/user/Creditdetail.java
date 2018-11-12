package com.ectrip.ec.model.user;

/**
 * Creditdetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Creditdetail implements java.io.Serializable {

	// Fields

	private Long cseq;
	private String usid;
	private String zusid;
	private String orid;
	private String ctype;
	private Long creditnumb;

	// Constructors

	/** default constructor */
	public Creditdetail() {
	}

	/** minimal constructor */
	public Creditdetail(Long cseq, String usid, String zusid, String ctype,
			Long creditnumb) {
		this.cseq = cseq;
		this.usid = usid;
		this.zusid = zusid;
		this.ctype = ctype;
		this.creditnumb = creditnumb;
	}

	/** full constructor */
	public Creditdetail(Long cseq, String usid, String zusid, String orid,
			String ctype, Long creditnumb) {
		this.cseq = cseq;
		this.usid = usid;
		this.zusid = zusid;
		this.orid = orid;
		this.ctype = ctype;
		this.creditnumb = creditnumb;
	}

	// Property accessors

	public Long getCseq() {
		return this.cseq;
	}

	public void setCseq(Long cseq) {
		this.cseq = cseq;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getZusid() {
		return this.zusid;
	}

	public void setZusid(String zusid) {
		this.zusid = zusid;
	}

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getCtype() {
		return this.ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public Long getCreditnumb() {
		return this.creditnumb;
	}

	public void setCreditnumb(Long creditnumb) {
		this.creditnumb = creditnumb;
	}

}