package com.ectrip.ticket.sale.service.OnlinePayment.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxh on 2016/09/26.
 */
public enum ChannelType {

    ALIPAY_QR("ALIPAY_QR","支付宝扫码支付"),
    ALIPAY_BAR("ALIPAY_BAR","支付宝当面付"),
    WX_QR_T("WX_QR_T","微信扫码支付"),
    WX_BAR_T("WX_BAR_T","微信当面付"),
    UPACP("UPACP","银联"),
    ONECARD("ONECARD","一卡通支付");

    private String code;

    private String name;

    ChannelType(String code, String name) {
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
    public static ChannelType typeOf(String code) {
        //枚举结果
        ChannelType[] channelTypes = ChannelType.values();
        for (ChannelType channelType : channelTypes) {
            if (channelType.getCode().equalsIgnoreCase(code)) {
                return channelType;
            }
        }
        throw new IllegalArgumentException("Invalid ChannelType type: " + code);
    }

    /**
     * 枚举转成 Map
     * @return Map
     */
    public static Map<String, String> convertToMap() {
        Map<String, String> map = new HashMap<String, String>();
        for(ChannelType channelType : ChannelType.values()) {
            map.put(channelType.getCode(), channelType.getName());
        }
        return map;
    }
}
