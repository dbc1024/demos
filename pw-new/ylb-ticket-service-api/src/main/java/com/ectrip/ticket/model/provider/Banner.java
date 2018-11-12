package com.ectrip.ticket.model.provider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**酒店和景区轮播图*/
@Entity
public class Banner {

	@Id
	private Long id;

	private Long providerId;	//对应服务商id

	private String title;	//轮播图标题

	private String url;	//轮播图存储路径


	/*以下字段不存表，动态获取*/
	@Transient
	private String providerName;	//服务商名称
	@Transient
	private String providerType;	//服务商类型：01景区；06酒店




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}



}
