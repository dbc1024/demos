package com.ectrip.hqyt.model.request;

public class QueryPreBinding {
    private String merchantId;
    private String merTxnNo;
    private String accNo;
    private String customerNm;
    private String certifId;
    private String phoneNo;
    private String legalPersonId;
    private String notifyUrl;
    private String returnUrl;
    public String getMerchantId() {
        return merchantId;
    }
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerTxnNo() {
        return merTxnNo;
    }

    public void setMerTxnNo(String merTxnNo) {
        this.merTxnNo = merTxnNo;
    }

    public String getAccNo() {
        return accNo;
    }
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }
    public String getCustomerNm() {
        return customerNm;
    }
    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }
    public String getCertifId() {
        return certifId;
    }
    public void setCertifId(String certifId) {
        this.certifId = certifId;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getLegalPersonId() {
        return legalPersonId;
    }
    public void setLegalPersonId(String legalPersonId) {
        this.legalPersonId = legalPersonId;
    }
    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
    public String getReturnUrl() {
        return returnUrl;
    }
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
