package com.ectrip.ec.model.order;

import javax.persistence.Embeddable;

@Embeddable
public class LZorderlistId implements java.io.Serializable {
	private Long zorderlistid;
	private Long orderlistid;
	private String orid;
	private Long iscenicid;

	// Constructors

	/** default constructor */
	public LZorderlistId() {
	}

	public Long getZorderlistid() {
		return zorderlistid;
	}

	public void setZorderlistid(Long zorderlistid) {
		this.zorderlistid = zorderlistid;
	}

	public Long getOrderlistid() {
		return orderlistid;
	}

	public void setOrderlistid(Long orderlistid) {
		this.orderlistid = orderlistid;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	/** full constructor */
	public LZorderlistId(Long zorderlistid, Long orderlistid, String orid,
			Long iscenicid) {
		this.zorderlistid = zorderlistid;
		this.orderlistid = orderlistid;
		this.orid = orid;
		this.iscenicid = iscenicid;
	}
}

