package com.ectrip.ticket.sale.service.OnlinePayment.model.response;

/**
 * Created by cxh on 2016/07/20.
 */
public class QueryBillResponse extends Response{

    public String oto;//oto标识

    public String orid;//订单号

    public String userid;//用户ID

    public String stationid;//站点ID

    public String winid;//窗口ID

    public double amount;//金额

    public String status;//订单状态

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStationid() {
        return stationid;
    }

    public void setStationid(String stationid) {
        this.stationid = stationid;
    }

    public String getWinid() {
        return winid;
    }

    public void setWinid(String winid) {
        this.winid = winid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QueryBillResponse{" +
                "oto='" + oto + '\'' +
                ", orid='" + orid + '\'' +
                ", userid='" + userid + '\'' +
                ", stationid='" + stationid + '\'' +
                ", winid='" + winid + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
