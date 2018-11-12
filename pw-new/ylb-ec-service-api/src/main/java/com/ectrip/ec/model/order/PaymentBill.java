package com.ectrip.ec.model.order;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cxh on 2016/09/18.
 */
@Entity
@Table
public class PaymentBill implements java.io.Serializable{
	@Id
    private String payCode;//֧����

    private String distributorOrderCode;//������������

    private String orid;//������

    private Long money;//���(��)

    private Long iscenicid;//������ID

    private Long orderType;//���� 1:֧��  2:�˶�

    private Long userid;//��ƱԱID

    private String username;//�û�����

    private Long winid;//����ID

    private String winname;//��������

    private String winCode;//֧�����豸����

    private String status;//״̬

    private String paymentChannel;//֧����ʽ

    private String createTime;//����ʱ��

    private String backTime;//����ʱ��

    private String dtmakedate;//����ʱ��

    private String cardNo;//���п���

    private Blob message;//��������

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getDistributorOrderCode() {
        return distributorOrderCode;
    }

    public void setDistributorOrderCode(String distributorOrderCode) {
        this.distributorOrderCode = distributorOrderCode;
    }

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getIscenicid() {
        return iscenicid;
    }

    public void setIscenicid(Long iscenicid) {
        this.iscenicid = iscenicid;
    }

    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getWinid() {
        return winid;
    }

    public void setWinid(Long winid) {
        this.winid = winid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public String getDtmakedate() {
        return dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWinname() {
        return winname;
    }

    public void setWinname(String winname) {
        this.winname = winname;
    }

    public String getWinCode() {
        return winCode;
    }

    public void setWinCode(String winCode) {
        this.winCode = winCode;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Blob getMessage() {
        return message;
    }

    public void setMessage(Blob message) {
        this.message = message;
    }
}
