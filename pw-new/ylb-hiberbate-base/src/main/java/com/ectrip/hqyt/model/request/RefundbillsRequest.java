package com.ectrip.hqyt.model.request;

/**
 * Created by chenxinhao on 17/1/13.
 */
public class RefundbillsRequest {

    private Long id;//环球雅途订单号

    private String refundOrid;//退订单号

    private Double refundMoney;//退订金额

    private String reason;//退订理由

    private String memo;//描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefundOrid() {
        return refundOrid;
    }

    public void setRefundOrid(String refundOrid) {
        this.refundOrid = refundOrid;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
