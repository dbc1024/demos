package com.ectrip.ticket.model.provider;

import java.math.BigDecimal;

/**
 * Secenicpicture entity. @author MyEclipse Persistence Tools
 */

public class Secenicpicture implements java.io.Serializable {

	// Fields

	private Long isecenicpictureid;//图库Id
	private Long iscenicid;//服务商Id
	private Long itickettypeid;//产品Id
	private Long upid;//图片Id

	// Constructors

	/** default constructor */
	public Secenicpicture() {
	}

	/** full constructor */
	public Secenicpicture(Long isecenicpictureid, Long iscenicid,
						  Long itickettypeid, Long upid) {
		this.isecenicpictureid = isecenicpictureid;
		this.iscenicid = iscenicid;
		this.itickettypeid = itickettypeid;
		this.upid = upid;
	}

	// Property accessors

	public Long getIsecenicpictureid() {
		return this.isecenicpictureid;
	}

	public void setIsecenicpictureid(Long isecenicpictureid) {
		this.isecenicpictureid = isecenicpictureid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getUpid() {
		return this.upid;
	}

	public void setUpid(Long upid) {
		this.upid = upid;
	}

}