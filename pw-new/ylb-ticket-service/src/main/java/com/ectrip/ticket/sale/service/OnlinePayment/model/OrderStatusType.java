package com.ectrip.ticket.sale.service.OnlinePayment.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxh on 2016/09/26.
 */
public enum OrderStatusType {
    NOPAY("F","未支付"),
    PAY("Y","已支付"),
    WAIT("W","处理中");

    private String code;

    private String name;

    OrderStatusType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取枚举对象
     * @param code
     * @return
     */
    public static OrderStatusType typeOf(String code) {
        //枚举结果
        OrderStatusType[] orderStatusTypes = OrderStatusType.values();
        for (OrderStatusType orderStatusType : orderStatusTypes) {
            if (orderStatusType.getCode().equalsIgnoreCase(code)) {
                return orderStatusType;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatusType type: " + code);
    }

    /**
     * 枚举转成 Map
     * @return Map
     */
    public static Map<String, String> convertToMap() {
        Map<String, String> map = new HashMap<String, String>();
        for(OrderStatusType orderStatusType : OrderStatusType.values()) {
            map.put(orderStatusType.getCode(), orderStatusType.getName());
        }
        return map;
    }
}
