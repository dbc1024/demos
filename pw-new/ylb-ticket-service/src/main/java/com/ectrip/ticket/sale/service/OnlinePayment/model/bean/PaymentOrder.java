package com.ectrip.ticket.sale.service.OnlinePayment.model.bean;

import java.sql.Blob;

/**
 * Created by chenxinhao on 16/12/23.
 */
public class PaymentOrder {

    private String orid;//订单号

    private Blob message;//报文信息

    private Double mont;//支付金额

    private Long iemployeeId;//员工ID

    private Long winId;//窗口ID

    private String ddzt;//订单状态

    private String dtmakedate;//操作时间

    public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public Blob getMessage() {
        return message;
    }

    public void setMessage(Blob message) {
        this.message = message;
    }

    public Double getMont() {
        return mont;
    }

    public void setMont(Double mont) {
        this.mont = mont;
    }

    public Long getIemployeeId() {
        return iemployeeId;
    }

    public void setIemployeeId(Long iemployeeId) {
        this.iemployeeId = iemployeeId;
    }

    public Long getWinId() {
        return winId;
    }

    public void setWinId(Long winId) {
        this.winId = winId;
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
}
