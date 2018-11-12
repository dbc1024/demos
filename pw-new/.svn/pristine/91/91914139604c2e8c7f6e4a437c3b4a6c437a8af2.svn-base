package com.ectrip.ec.model.cytterminal;


public class DataTrans {
    public String data;
    public String signed;// 签名
    public String identity;// 供应商ID

    // 以下两个字段是data里面的具体实现,仅方便取值使用,在传输数据时请勿塞值!
    public Request request;// 请求
    public Response response;// 响应

    public DataTrans() {
        super();
    }

    public DataTrans(String data, String signed, String supplierIdentity) {
        super();
        this.data = data;
        this.signed = signed;
        this.identity = supplierIdentity;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
