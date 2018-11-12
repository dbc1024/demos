package com.ectrip.ticket.sale.service.OnlinePayment.model;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by cxh on 2016/09/26.
 */
public enum OrderType {
    PAY(1,"支付单"),
    REFUND(2,"退订单");
    private int status;

    private String name;

    OrderType(int status,String name){
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取枚举对象
     * @param status
     * @return
     */
    public static OrderType typeOf(int status) {
        //枚举结果
        OrderType[] orderTypes = OrderType.values();
        for (OrderType orderType : orderTypes) {
            if (orderType.getStatus() == status) {
                return orderType;
            }
        }
        throw new IllegalArgumentException("Invalid OrderType type: " + status);
    }

    /**
     * 枚举转成 Map
     * @return Map
     */
    public static Map<Integer, String> convertToMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(OrderType orderType : OrderType.values()) {
            map.put(orderType.getStatus(), orderType.getName());
        }
        return map;
    }
}
