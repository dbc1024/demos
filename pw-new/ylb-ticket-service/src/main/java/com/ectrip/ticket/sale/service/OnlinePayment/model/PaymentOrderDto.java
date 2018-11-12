package com.ectrip.ticket.sale.service.OnlinePayment.model;

/**
 * Created by chenxinhao on 16/12/23.
 */
public class PaymentOrderDto {

    private String orid;//支付号

    private String payOrid;//支付系统订单号

    private String pmsOrid;//PMS订单号

    private Double mont;//支付金额

    private String paymentType;//支付方式

    private String payStatus;//支付状态

    private String message;//报文信息

    private String ddzt;//订单状态

    private String dtmakedate;//操作时间

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public Double getMont() {
        return mont;
    }

    public void setMont(Double mont) {
        this.mont = mont;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDdzt() {
        return ddzt;
    }

    public void setDdzt(String ddzt) {
        this.ddzt = ddzt;
    }

    public String getDtmakedate() {
        return dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }

    public String getPayOrid() {
        return payOrid;
    }

    public void setPayOrid(String payOrid) {
        this.payOrid = payOrid;
    }

    public String getPmsOrid() {
        return pmsOrid;
    }

    public void setPmsOrid(String pmsOrid) {
        this.pmsOrid = pmsOrid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
}
