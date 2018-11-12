package com.ectrip.ec.model.cytterminal;
import java.util.List;
import java.util.Map;

public class OrderInfo {
    public String id;// 订单ID
    public String saleName;// 分销商名称
    public String date;// 下单日期
    public String status;// 订单状态
    public String count;// 订单剩余数量
    public String price;// 单价
    public String money;// 金额
    public String visitDate;// 消费日期
    public String personName;// 取票人姓名
    public String credentials;// 证件号码
    public String ticketName;// 产品名称
    public String totalCount;// 订单总数量
    public String verifyPart;// 是否部分检票
    public List<Map> details;// 订单详情


    public String iscenicid;
    public OrderInfo() {
        super();
    }

    public OrderInfo(String orderId, String saleName, String orderDate,
                     String orderStatus, String orderCount, String price,
                     String orderMoney, String visitDate, String personName,
                     String credentials, String ticketName, String orderTotalCount,
                     String canrefundPart, List<Map> orderDetail) {
        super();
        this.id = orderId;
        this.saleName = saleName;
        this.date = orderDate;
        this.status = orderStatus;
        this.count = orderCount;
        this.price = price;
        this.money = orderMoney;
        this.visitDate = visitDate;
        this.personName = personName;
        this.credentials = credentials;
        this.ticketName = ticketName;
        this.totalCount = orderTotalCount;
        this.verifyPart = canrefundPart;
        this.details = orderDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getVerifyPart() {
        return verifyPart;
    }

    public void setVerifyPart(String verifyPart) {
        this.verifyPart = verifyPart;
    }

    public List<Map> getDetails() {
        return details;
    }

    public void setDetails(List<Map> details) {
        this.details = details;
    }

    public String getIscenicid() {
        return iscenicid;
    }

    public void setIscenicid(String iscenicid) {
        this.iscenicid = iscenicid;
    }

}
