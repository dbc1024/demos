package com.ectrip.ticket.model.log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenqiang.luo date:15-11-12
 */
public enum LogType {

    /**
     * 系统日志
     */
    SYSTEM_LOG(0, "SystemLog"),

    /**
     * 接口交互日志
     */
    INTERFACE_LOG(1, "InterfaceLog"),

    /**
     * 定时任务日志
     */
    TASK_LOG(2, "TaskLog"),

    /**
     * 异常日志
     */
    EXCEPTION_LOG(3, "ExceptionLog"),

    /**
     * 描叙日志
     */
    INFO_LOG(4,"InfoLog");

    /** 日志类型值 */
    private int type;

    /** 日志类型名 */
    private String name;

    private LogType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static LogType typeOf(int type) {
        //枚举结果
        LogType[] logTypes = LogType.values();
        for (LogType logType : logTypes) {
            if (logType.getType() == type) {
                return logType;
            }
        }
        throw new IllegalArgumentException("Invalid LogType type: " + type);
    }

    /**
     * 枚举转换为Map
     * @return map
     */
    public static Map<Integer, String> convertToMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for(LogType logType : LogType.values()) {
            map.put(logType.getType(), logType.getName());
        }
        return map;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
