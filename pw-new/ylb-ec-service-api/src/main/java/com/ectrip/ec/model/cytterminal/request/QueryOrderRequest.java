package com.ectrip.ec.model.cytterminal.request;

import com.ectrip.ec.model.cytterminal.Request;

/**
 * @author yongkang.liao 2015/10/9.
 */
public class QueryOrderRequest extends Request{
    public String method;// 请求方式
    public String code;// 验证码
    public String phone;// 手机号码
    public String credentials;// 证件号码
    public String orderId; //订单号
    public String startDate;// 开始日期
    public String endDate;// 结束日期
    public String count;// 最大订单数量

    public String note1;//
    public String note2;//
    public String note3;//
    public String note4;//
    public String note5;//
    public String note6;//
    public String note7;//
    public String note8;//

    public QueryOrderRequest() {
    }

    public QueryOrderRequest(String method, String verifycode,
                             String telephone, String credentials, String startDate,
                             String endDate, String orderCount) {
        super();
        this.method = method;
        this.code = verifycode;
        this.phone = telephone;
        this.credentials = credentials;
        this.startDate = startDate;
        this.endDate = endDate;
        this.count = orderCount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getNote4() {
        return note4;
    }

    public void setNote4(String note4) {
        this.note4 = note4;
    }

    public String getNote5() {
        return note5;
    }

    public void setNote5(String note5) {
        this.note5 = note5;
    }

    public String getNote6() {
        return note6;
    }

    public void setNote6(String note6) {
        this.note6 = note6.replace("Ectrip", "yilvbao");
    }

    public String getNote7() {
        return note7;
    }

    public void setNote7(String note7) {
        this.note7 = note7;
    }

    public String getNote8() {
        return note8;
    }

    public void setNote8(String note8) {
        this.note8 = note8;
    }
}
