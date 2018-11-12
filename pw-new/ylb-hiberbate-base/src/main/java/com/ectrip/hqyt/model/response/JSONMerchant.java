package com.ectrip.hqyt.model.response;

public class JSONMerchant extends JSONLegalPerson {

	private Long id;
	private String applicationName; // 应用名称 例如：圈子旅行 quanzi
	private String adminLogin; // 商户管理员登录名
	private String adminPassword; // 商户管理员登录密码
	private String organizationName;// 平台商户名称
	private String organizationCode;// 组织机构代码
	private String registrationNumber;// 工商注册号
	private String notifyUrl;
	private String refundNotifyUrl;
	private String city;// 城市
	private String subbranch;// 支行
	private String settleBankAccount;// 银行帐号
	private String settleBankAccountName;// 银行开户名
	private String merchantKey;
	private Integer settlementDays;// 结算周期
	private Integer paymentDays;// 交易付款有效期
	private Integer paymentHours; // 交易付款有效小时
	private Integer paymentMinutes; // 交易付款有效分账
	private String industryCategory;// 行业类别
	private String website;// 网址
	private String correspondenceAddress;// 通讯地址
	private String corporate;// 法人
	private String corporateIdentity;// 法人身份证
	private String contractStartTime;// 合约起始时间
	private String contractEndTime;// 合约结束时间
	private String businessContact;// 业务联系人
	private String businessTel;// 业务联系电话
	private String businessContactEmail;// 业务联系邮箱
	private String technicalContact;// 技术联系人
	private String technicalTel;// 技术联系电话
	private String technicalContactEmail;// 技术联系邮箱
	private String customerContact;// 客服联系人
	private String customerTel;// 客服联系电话
	private String customerContactEmail;// 客服联系邮箱
	private String corporateIdentityCertificate;// 法人身份证照
	private String businessLicense;// 营业执照
	private String settlementAgreement;// 结算协议
	private Integer withdrawalDays;
	private Integer withdrawalHours; // 交易退款有效小时
	private Integer withdrawalMinutes; // 交易退款分钟
	private Boolean now; // 是否支持即时交易
	private Boolean vouch; // 是否支持担保交易
	private JSONBank bank;//银行
	private JSONRate rate;//结算费率
	private JSONParameter parameter;//行业类别
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getAdminLogin() {
		return adminLogin;
	}
	public void setAdminLogin(String adminLogin) {
		this.adminLogin = adminLogin;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getRefundNotifyUrl() {
		return refundNotifyUrl;
	}
	public void setRefundNotifyUrl(String refundNotifyUrl) {
		this.refundNotifyUrl = refundNotifyUrl;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSubbranch() {
		return subbranch;
	}
	public void setSubbranch(String subbranch) {
		this.subbranch = subbranch;
	}
	public String getSettleBankAccount() {
		return settleBankAccount;
	}
	public void setSettleBankAccount(String settleBankAccount) {
		this.settleBankAccount = settleBankAccount;
	}
	public String getSettleBankAccountName() {
		return settleBankAccountName;
	}
	public void setSettleBankAccountName(String settleBankAccountName) {
		this.settleBankAccountName = settleBankAccountName;
	}
	public String getMerchantKey() {
		return merchantKey;
	}
	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}
	public Integer getSettlementDays() {
		return settlementDays;
	}
	public void setSettlementDays(Integer settlementDays) {
		this.settlementDays = settlementDays;
	}
	public Integer getPaymentDays() {
		return paymentDays;
	}
	public void setPaymentDays(Integer paymentDays) {
		this.paymentDays = paymentDays;
	}
	public Integer getPaymentHours() {
		return paymentHours;
	}
	public void setPaymentHours(Integer paymentHours) {
		this.paymentHours = paymentHours;
	}
	public Integer getPaymentMinutes() {
		return paymentMinutes;
	}
	public void setPaymentMinutes(Integer paymentMinutes) {
		this.paymentMinutes = paymentMinutes;
	}
	public String getIndustryCategory() {
		return industryCategory;
	}
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCorrespondenceAddress() {
		return correspondenceAddress;
	}
	public void setCorrespondenceAddress(String correspondenceAddress) {
		this.correspondenceAddress = correspondenceAddress;
	}
	public String getCorporate() {
		return corporate;
	}
	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}
	public String getCorporateIdentity() {
		return corporateIdentity;
	}
	public void setCorporateIdentity(String corporateIdentity) {
		this.corporateIdentity = corporateIdentity;
	}
	public String getContractStartTime() {
		return contractStartTime;
	}
	public void setContractStartTime(String contractStartTime) {
		this.contractStartTime = contractStartTime;
	}
	public String getContractEndTime() {
		return contractEndTime;
	}
	public void setContractEndTime(String contractEndTime) {
		this.contractEndTime = contractEndTime;
	}
	public String getBusinessContact() {
		return businessContact;
	}
	public void setBusinessContact(String businessContact) {
		this.businessContact = businessContact;
	}
	public String getBusinessTel() {
		return businessTel;
	}
	public void setBusinessTel(String businessTel) {
		this.businessTel = businessTel;
	}
	public String getBusinessContactEmail() {
		return businessContactEmail;
	}
	public void setBusinessContactEmail(String businessContactEmail) {
		this.businessContactEmail = businessContactEmail;
	}
	public String getTechnicalContact() {
		return technicalContact;
	}
	public void setTechnicalContact(String technicalContact) {
		this.technicalContact = technicalContact;
	}
	public String getTechnicalTel() {
		return technicalTel;
	}
	public void setTechnicalTel(String technicalTel) {
		this.technicalTel = technicalTel;
	}
	public String getTechnicalContactEmail() {
		return technicalContactEmail;
	}
	public void setTechnicalContactEmail(String technicalContactEmail) {
		this.technicalContactEmail = technicalContactEmail;
	}
	public String getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getCustomerContactEmail() {
		return customerContactEmail;
	}
	public void setCustomerContactEmail(String customerContactEmail) {
		this.customerContactEmail = customerContactEmail;
	}
	public String getCorporateIdentityCertificate() {
		return corporateIdentityCertificate;
	}
	public void setCorporateIdentityCertificate(String corporateIdentityCertificate) {
		this.corporateIdentityCertificate = corporateIdentityCertificate;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getSettlementAgreement() {
		return settlementAgreement;
	}
	public void setSettlementAgreement(String settlementAgreement) {
		this.settlementAgreement = settlementAgreement;
	}
	public Integer getWithdrawalDays() {
		return withdrawalDays;
	}
	public void setWithdrawalDays(Integer withdrawalDays) {
		this.withdrawalDays = withdrawalDays;
	}
	public Integer getWithdrawalHours() {
		return withdrawalHours;
	}
	public void setWithdrawalHours(Integer withdrawalHours) {
		this.withdrawalHours = withdrawalHours;
	}
	public Integer getWithdrawalMinutes() {
		return withdrawalMinutes;
	}
	public void setWithdrawalMinutes(Integer withdrawalMinutes) {
		this.withdrawalMinutes = withdrawalMinutes;
	}
	public Boolean getNow() {
		return now;
	}
	public void setNow(Boolean now) {
		this.now = now;
	}
	public Boolean getVouch() {
		return vouch;
	}
	public void setVouch(Boolean vouch) {
		this.vouch = vouch;
	}

	public JSONBank getBank() {
		return bank;
	}

	public void setBank(JSONBank bank) {
		this.bank = bank;
	}

	public JSONRate getRate() {
		return rate;
	}

	public void setRate(JSONRate rate) {
		this.rate = rate;
	}

	public JSONParameter getParameter() {
		return parameter;
	}

	public void setParameter(JSONParameter parameter) {
		this.parameter = parameter;
	}
}
