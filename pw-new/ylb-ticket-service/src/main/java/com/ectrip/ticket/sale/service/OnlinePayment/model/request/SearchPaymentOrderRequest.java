package com.ectrip.ticket.sale.service.OnlinePayment.model.request;

/**
 * Created by chenxinhao on 16/12/23.
 */
public class SearchPaymentOrderRequest extends Request{

    private String orid;//支付号

    private String rzti;//开始日期

    private String ldti;//截止日期

    private String empid;//售票员登录名

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public String getRzti() {
        return rzti;
    }

    public void setRzti(String rzti) {
        this.rzti = rzti;
    }

    public String getLdti() {
        return ldti;
    }

    public void setLdti(String ldti) {
        this.ldti = ldti;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }
}
