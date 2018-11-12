package com.ectrip.ticket.model.log;

import java.text.SimpleDateFormat;

/**
 * Created by chenxinhao on 17/1/12.
 */
public class SystemLog extends BaseLog{

    private SystemLogType systemLogType;//后台日志类型

    private String method;//方法名

    private String originalData;//原始数据

    private String resultData;//修改后数据

    private String userName;//操作人

    private String description;//描述

    public SystemLogType getSystemLogType() {
        return systemLogType;
    }

    public void setSystemLogType(SystemLogType systemLogType) {
        this.systemLogType = systemLogType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String serialize() {
        //接口交互时间    接口交互描述  接口类型    接口方法  操作人  原始数据    结果数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return BaseLog.serialize(sdf.format(datetime),
                description, systemLogType.getName(), method, userName, originalData, resultData);
    }

    /**
     * 接口日志统一输出方法
     * @param systemLog 系统日志对象
     */
    public static void printSystemLog(SystemLog systemLog) {
        if (systemLog != null) {
            ApplicationLog<SystemLog> applicationLog = new ApplicationLog<SystemLog>();
            applicationLog.setLogType(LogType.SYSTEM_LOG);
            applicationLog.setLogInfo(systemLog);
            //日志打印
            applicationLog.printLog();
        }
    }
}
