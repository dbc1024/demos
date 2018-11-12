package com.ectrip.hqyt.model.request;

import java.util.List;

public class GuaranteeLykRequest {


    private String orid;//订单号

    private List<ProductRequest> products;//产品列表

    private Double totalMoney;//总金额

    private String usid;//用户编号

    private String issuerId;//卖家ID

    private String username;//用户姓名

    private String phone;//手机

    private String consumeTimeLimit; //消费截止日期
    
    private LegalPersonRequest receiver;
    
    private String title;

    private String businessType;//业务类型
    private String splitAgreementNo;//分润协议编号
    private String splitAgreementName;//分润协议名称
    private String tourCardNo;//旅游卡卡号
    private String tourCardCode;//旅游卡代码
    private String tourCardName;//旅游卡名称
    private String bookName;//游客名称
    private String bookIdCard;//游客身份证号
    private String bookPhone;//游客手机号
    private boolean historyTourCard;//是否为历史旅游卡

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getSplitAgreementNo() {
        return splitAgreementNo;
    }

    public void setSplitAgreementNo(String splitAgreementNo) {
        this.splitAgreementNo = splitAgreementNo;
    }

    public String getSplitAgreementName() {
        return splitAgreementName;
    }

    public void setSplitAgreementName(String splitAgreementName) {
        this.splitAgreementName = splitAgreementName;
    }

    public String getTourCardNo() {
        return tourCardNo;
    }

    public void setTourCardNo(String tourCardNo) {
        this.tourCardNo = tourCardNo;
    }

    public String getTourCardCode() {
        return tourCardCode;
    }

    public void setTourCardCode(String tourCardCode) {
        this.tourCardCode = tourCardCode;
    }

    public String getTourCardName() {
        return tourCardName;
    }

    public void setTourCardName(String tourCardName) {
        this.tourCardName = tourCardName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookIdCard() {
        return bookIdCard;
    }

    public void setBookIdCard(String bookIdCard) {
        this.bookIdCard = bookIdCard;
    }

    public String getBookPhone() {
        return bookPhone;
    }

    public void setBookPhone(String bookPhone) {
        this.bookPhone = bookPhone;
    }

    public boolean isHistoryTourCard() {
        return historyTourCard;
    }

    public void setHistoryTourCard(boolean historyTourCard) {
        this.historyTourCard = historyTourCard;
    }

    public String getTitle() {
    	return title;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }
    public LegalPersonRequest getReceiver() {
        return receiver;
    }

    public void setReceiver(LegalPersonRequest receiver) {
        this.receiver = receiver;
    }

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public List<ProductRequest> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequest> products) {
        this.products = products;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getConsumeTimeLimit() {
        return consumeTimeLimit;
    }

    public void setConsumeTimeLimit(String consumeTimeLimit) {
        this.consumeTimeLimit = consumeTimeLimit;
    }

}
