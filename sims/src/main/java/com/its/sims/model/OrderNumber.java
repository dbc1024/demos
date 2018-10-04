package com.its.sims.model;

/**
 * Created by csz on 2017/6/27.
 */
public class OrderNumber {

    private Long id;

    private String orderDate;

    public OrderNumber() {
    }

    public OrderNumber(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
