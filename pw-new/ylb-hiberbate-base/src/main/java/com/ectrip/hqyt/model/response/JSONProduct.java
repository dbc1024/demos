package com.ectrip.hqyt.model.response;

public class JSONProduct {
	
	private Long id;

	/**
	 * 说明
	 */
	private String description;
	/**
	 * 外部ID
	 */
	private String externalId;
	/**
	 * 产品名称
	 */
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
