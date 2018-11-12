package com.ectrip.ticket.model.log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxinhao on 17/1/12.
 */
public enum SystemLogType {

    SYSPAR(0, "系统参数");


    private int type;
    private String name;

    private SystemLogType(int type, String name) {
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
    public static SystemLogType statusOf(int type) {
        //枚举结果
        SystemLogType[] systemLogTypes = SystemLogType.values();
        for (SystemLogType systemLogType : systemLogTypes) {
            if (systemLogType.getType() == type) {
                return systemLogType;
            }
        }
        throw new IllegalArgumentException("Invalid SystemLogType type: " + type);
    }

    /**
     * 枚举转换为Map
     * @return map
     */
    public static Map<Integer, String> convertToMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(SystemLogType systemLogType : SystemLogType.values()) {
            map.put(systemLogType.getType(), systemLogType.getName());
        }
        return map;
    }
}
