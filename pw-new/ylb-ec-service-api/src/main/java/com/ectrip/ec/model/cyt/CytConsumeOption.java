package com.ectrip.ec.model.cyt;

/**
 * Created by cxh on 2016/10/08.
 */
public class CytConsumeOption {

    private String orid;//订单号

    private String rzti;//开始日期

    private String ldti;//截至日期

    private String status;//状态

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
