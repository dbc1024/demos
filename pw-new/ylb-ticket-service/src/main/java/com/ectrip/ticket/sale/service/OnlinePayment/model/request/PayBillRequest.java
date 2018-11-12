package com.ectrip.ticket.sale.service.OnlinePayment.model.request;

/**
 * Created by cxh on 2016/07/20.
 */
public class PayBillRequest extends Request {

    public String oto;//oto标识

    public String orid;//订单号

    public String amount;//金额

    public String paymentCode;//支付渠道编码

    public String authCode;//支付码

    public String getOto() {
        return oto;
    }

    public void setOto(String oto) {
        this.oto = oto;
    }

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "PayBillRequest{" +
                "oto='" + oto + '\'' +
                ", orid='" + orid + '\'' +
                ", amount='" + amount + '\'' +
                ", paymentCode='" + paymentCode + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
