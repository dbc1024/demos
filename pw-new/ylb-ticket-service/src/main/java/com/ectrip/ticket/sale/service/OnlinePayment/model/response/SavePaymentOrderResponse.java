package com.ectrip.ticket.sale.service.OnlinePayment.model.response;

/**
 * Created by chenxinhao on 16/12/23.
 */
public class SavePaymentOrderResponse extends Response{

    private String orid;//订单号

    private String zfmont;//支付金额

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public String getZfmont() {
        return zfmont;
    }

    public void setZfmont(String zfmont) {
        this.zfmont = zfmont;
    }
}
