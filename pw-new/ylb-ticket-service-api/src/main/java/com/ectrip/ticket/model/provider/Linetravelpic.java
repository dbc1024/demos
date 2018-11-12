package com.ectrip.ticket.model.provider;

import java.io.Serializable;

public class Linetravelpic implements Serializable {

	/**
	 * 行程图片表
	 * LINETRAVELPIC.java
	 * liujianwen
	 * 20122012-7-3
	 */
	private static final long serialVersionUID = 1L;

	private Long linetravelpicid;

	private Long linetravelid;
	private Long  upid;
	private String dtmakedate;

	public Linetravelpic(){}



	public Linetravelpic(Long linetravelpicid, Long linetravelid, Long upid) {
		super();
		this.linetravelpicid = linetravelpicid;
		this.linetravelid = linetravelid;
		this.upid = upid;
	}



	public Long getLinetravelpicid() {
		return linetravelpicid;
	}

	public void setLinetravelpicid(Long linetravelpicid) {
		this.linetravelpicid = linetravelpicid;
	}

	public Long getLinetravelid() {
		return linetravelid;
	}

	public void setLinetravelid(Long linetravelid) {
		this.linetravelid = linetravelid;
	}

	public Long getUpid() {
		return upid;
	}

	public void setUpid(Long upid) {
		this.upid = upid;
	}

	public String getDtmakedate() {
		return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

}

