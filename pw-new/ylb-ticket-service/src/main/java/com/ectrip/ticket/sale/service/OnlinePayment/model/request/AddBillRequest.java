package com.ectrip.ticket.sale.service.OnlinePayment.model.request;

/**
 * Created by cxh on 2016/07/20.
 */
public class AddBillRequest extends Request {

    public String oto;//订单标识

    public String orid;//订单号

    public String userid;//用户ID

    public String stationid;//站点ID

    public String winid;//窗口ID

    public String amount;//金额

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AddBillRequest{" +
                "oto='" + oto + '\'' +
                ", orid='" + orid + '\'' +
                ", userid='" + userid + '\'' +
                ", stationid='" + stationid + '\'' +
                ", winid='" + winid + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
