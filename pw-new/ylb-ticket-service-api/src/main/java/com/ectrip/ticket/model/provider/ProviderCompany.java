package com.ectrip.ticket.model.provider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chenxinhao on 17/1/12.
 */
@Entity
@Table
public class ProviderCompany implements Serializable{
	@Id
    private Long seq;//主键

    private Long hqytId;//环球雅途ID

    private String applicationName; // 应用名称 例如：圈子旅行 quanzi

    private String adminLogin; // 商户管理员登录名

    private String providerType;//行业类别

    private String companyName;//企业名称

    private String website;//企业网站

    private String address;//企业地址

    private String fax;//传真
    
    @Column(name="registration_Number")
    private String registrationNumber;//工商注册号
    
    @Column(name="organization_Code")
    private String organizationCode;//组织机构代码

    private String corporate;// 法人
    
    @Column(name="corporate_Identity")
    private String corporateIdentity;// 法人身份证
    
    @Column(name="business_Contact")
    private String businessContact;// 业务联系人
    
    @Column(name="business_Tel")
    private String businessTel;// 业务联系电话
    
    @Column(name="businessContact_Email")
    private String businessContactEmail;// 业务联系邮箱
    
    @Column(name="business_QQ")
    private String businessQQ;//业务联系QQ
    
    @Column(name="customer_Contact")
    private String customerContact;// 客服联系人
    
    @Column(name="customer_Tel")
    private String customerTel;// 客服联系电话
    
    @Column(name="customerContact_Email")
    private String customerContactEmail;// 客服联系邮箱
    
    @Column(name="customer_QQ")
    private String customerQQ;//客服联系QQ
    
    @Column(name="contract_StartTime")
    private String contractStartTime;// 合约起始时间
    
    @Column(name="contract_EndTime")
    private String contractEndTime;// 合约结束时间

    private Integer bankId;//结算银行

    private String city;// 城市

    private String subbranch;// 支行

    private String settleBankAccount;// 银行帐号

    private String settleBankAccountName;// 银行开户名

    private Integer settlementDays;//结算周期

    private Integer settlementRate;//结算费率

    private Integer status;//状态 -1:禁用  0:未开户  1: 已开户

    private String type;//01:旅行社  02:服务商

    private Long iscenicid;//服务商ID

    private String usid;//用户ID

    private String merchantKey;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getHqytId() {
        return hqytId;
    }

    public void setHqytId(Long hqytId) {
        this.hqytId = hqytId;
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

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistration_Number(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
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

    public String getBusinessQQ() {
        return businessQQ;
    }

    public void setBusinessQQ(String businessQQ) {
        this.businessQQ = businessQQ;
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

    public String getCustomerQQ() {
        return customerQQ;
    }

    public void setCustomerQQ(String customerQQ) {
        this.customerQQ = customerQQ;
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

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
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

    public Integer getSettlementDays() {
        return settlementDays;
    }

    public void setSettlementDays(Integer settlementDays) {
        this.settlementDays = settlementDays;
    }

    public Integer getSettlementRate() {
        return settlementRate;
    }

    public void setSettlementRate(Integer settlementRate) {
        this.settlementRate = settlementRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIscenicid() {
        return iscenicid;
    }

    public void setIscenicid(Long iscenicid) {
        this.iscenicid = iscenicid;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }
}
