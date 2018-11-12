package com.ectrip.ticket.sale.service.OnlinePayment.model.request;

/**
 * Created by cxh on 2016/06/17.
 */
public class OrderChargeRequest extends Request {

    /** 接口请求类型 charge */
    private String object;

    /** 商户订单号 */
    private String orderNo;

    /** 设备代码 */
    private String devCode;

    /** 支付渠道代码 */
    private String channelCode;

    /** 金额(分) */
    private Long amount;

    /** 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1 */
    private String clientIp;

    /** 三位 ISO 货币代码，目前仅支持人民币 cny */
    private String currency;

    /** 商品的标题 */
    private String subject;

    /** 商品的描述信息 */
    private String body;

    /** 特定渠道发起交易时需要的额外参数 */
    private String extra;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "OrderChargeRequest{" +
                "object='" + object + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", devCode='" + devCode + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", amount=" + amount +
                ", clientIp='" + clientIp + '\'' +
                ", currency='" + currency + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
