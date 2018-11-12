package com.ectrip.hqyt.model.response;
public class JSONClient extends JSONLegalPerson {

	private Long id;
	private String marketplaceSpecificNo;// 平台用户编号
	private String marketplaceSpecificName; // 平台用户账户
	private String marketplaceSpecificNickName; // 平台用户昵称
	private String realName;
	private String email;
	private String phone;
	private String idCard;
	private String address;
	private String registrationName;
	public String getRegistrationName() {
		return registrationName;
	}
	public void setRegistrationName(String registrationName) {
		this.registrationName = registrationName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMarketplaceSpecificNo() {
		return marketplaceSpecificNo;
	}
	public void setMarketplaceSpecificNo(String marketplaceSpecificNo) {
		this.marketplaceSpecificNo = marketplaceSpecificNo;
	}
	public String getMarketplaceSpecificName() {
		return marketplaceSpecificName;
	}
	public void setMarketplaceSpecificName(String marketplaceSpecificName) {
		this.marketplaceSpecificName = marketplaceSpecificName;
	}
	public String getMarketplaceSpecificNickName() {
		return marketplaceSpecificNickName;
	}
	public void setMarketplaceSpecificNickName(String marketplaceSpecificNickName) {
		this.marketplaceSpecificNickName = marketplaceSpecificNickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
