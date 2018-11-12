package com.ectrip.ticket.model.log;

import java.text.SimpleDateFormat;

/**
 * 接口交互日志信息
 * @author wenqiang.luo date:15-11-11
 */
public class InterfaceLog extends BaseLog {

    private static final long serialVersionUID = -4336248631860854977L;

    /** 接口类型 */
    private InterfaceType interfaceType;

    /** 接口方法 */
    private String interfaceMethod;

    /** 请求报文 */
    private String requestData;

    /** 返回报文 */
    private String responseData;

    /** 交互描述 */
    private String description;

    /* get and set */

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getInterfaceMethod() {
        return interfaceMethod;
    }

    public void setInterfaceMethod(String interfaceMethod) {
        this.interfaceMethod = interfaceMethod;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 日志对象属性值序列
     *
     * @return 序列日志信息
     */
    @Override
    public String serialize() {
        //接口交互时间    接口交互描述  接口类型    接口方法    请求报文    返回报文
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return BaseLog.serialize(sdf.format(datetime),
                description, interfaceType.getName(), interfaceMethod, requestData, responseData);
    }

    /**
     * 接口日志统一输出方法
     * @param interfaceLog 接口日志对象
     */
    public static void printInterfaceLog(InterfaceLog interfaceLog) {
        if (interfaceLog != null) {
            ApplicationLog<InterfaceLog> applicationLog = new ApplicationLog<InterfaceLog>();
            applicationLog.setLogType(LogType.INTERFACE_LOG);
            applicationLog.setLogInfo(interfaceLog);
            //日志打印
            applicationLog.printLog();
        }
    }
}
