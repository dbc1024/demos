package com.ectrip.ticket.model.provider;

/**
 * Hotelsvc entity. @author MyEclipse Persistence Tools
 * 酒店提供的服务
 */

public class Hotelsvc implements java.io.Serializable {

	// Fields

	private HotelsvcId id;

	// Constructors

	/** default constructor */
	public Hotelsvc() {
	}

	/** full constructor */
	public Hotelsvc(HotelsvcId id) {
		this.id = id;
	}

	// Property accessors

	public HotelsvcId getId() {
		return this.id;
	}

	public void setId(HotelsvcId id) {
		this.id = id;
	}

}