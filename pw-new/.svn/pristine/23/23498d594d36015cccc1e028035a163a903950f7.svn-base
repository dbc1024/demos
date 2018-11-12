package com.ectrip.ticket.model.log;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口类型
 * Created by chenkai.liu on 2015/11/10.
 */
public enum InterfaceType {

    CYT_2(0, "畅游通2.0接口"),
    CYT_3(1, "畅游通3.0接口"),
    SALE(2, "售票接口"),
    PAY(3,"支付接口"),
    CHECK(4,"人脸识别");


    private int type;
    private String name;

    private InterfaceType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据数据类型获取枚举对象
     * @param type 数据类型值
     * @return 数据类型枚举
     */
    public static InterfaceType statusOf(int type) {
        //枚举结果
        InterfaceType[] interfaceTypes = InterfaceType.values();
        for (InterfaceType interfaceType : interfaceTypes) {
            if (interfaceType.getType() == type) {
                return interfaceType;
            }
        }
        throw new IllegalArgumentException("Invalid InterfaceType type: " + type);
    }

    /**
     * 枚举转换为Map
     * @return map
     */
    public static Map<Integer, String> convertToMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(InterfaceType fundType : InterfaceType.values()) {
            map.put(fundType.getType(), fundType.getName());
        }
        return map;
    }


}
