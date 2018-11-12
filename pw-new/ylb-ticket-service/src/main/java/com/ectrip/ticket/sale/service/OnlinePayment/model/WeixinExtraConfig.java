package com.ectrip.ticket.sale.service.OnlinePayment.model;

/**
 * 微信支付额外配置信息
 * Created by cxh on 2016/06/21.
 */
public class WeixinExtraConfig {

    /** 支付授权码 */
    private String auth_code;

    /** 支付成功的回调地址 */
    private String success_url;

    /** 取消订单的回调地址 */
    private String cancel_ur;

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getSuccess_url() {
        return success_url;
    }

    public void setSuccess_url(String success_url) {
        this.success_url = success_url;
    }

    public String getCancel_ur() {
        return cancel_ur;
    }

    public void setCancel_ur(String cancel_ur) {
        this.cancel_ur = cancel_ur;
    }

    @Override
    public String toString() {
        return "WeixinExtraConfig{" +
                "auth_code='" + auth_code + '\'' +
                ", success_url='" + success_url + '\'' +
                ", cancel_ur='" + cancel_ur + '\'' +
                '}';
    }
}
