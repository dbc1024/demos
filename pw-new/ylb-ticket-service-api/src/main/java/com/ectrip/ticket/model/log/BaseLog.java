package com.ectrip.ticket.model.log;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志基础类
 */
public abstract class BaseLog implements Serializable {

    private static final long serialVersionUID = 5551674970300507707L;

    /** 日志记录时间 */
    protected Date datetime;

    /**
     * construct
     */
    public BaseLog() {
        this.datetime = new Date();
    }

    /**
     * 日志对象属性值序列
     * @return 序列日志信息
     */
    public abstract String serialize();

    /**
     * 获取日志分隔符
     * @return \t
     */
    public static String getSeparateCharacter() {
        return "\t";
    }

    /**
     * 日志参数序列化
     * @param params 日志参数
     * @return 日志序列化字符串
     */
    public static String serialize(String... params) {
        StringBuilder logBuilder = new StringBuilder();
        if (params != null && params.length > 0) {
            logBuilder.append(params[0]);

            for (int i = 1; i < params.length; i++) {
                logBuilder.append(BaseLog.getSeparateCharacter()).append(params[i]);
            }
        }
        return logBuilder.toString();
    }
}
