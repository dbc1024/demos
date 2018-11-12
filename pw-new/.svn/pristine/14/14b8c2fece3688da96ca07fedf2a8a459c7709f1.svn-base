package com.ectrip.ticket.model.log;

import org.apache.commons.lang.StringUtils;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * User:Created by wei.li
 * Date: on 2015/11/20.
 * Time:11:11
 */
public class InfoLog extends BaseLog {

    private static final long serialVersionUID = 338520862982232710L;

    /** 日志信息 */
    private String message;

    public InfoLog(String message) {
        this.message = message;
    }
//get and set//////////////////////////////////////////////////////////////

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 日志对象属性值序列
     *
     * @return 序列日志信息
     */
    @Override
    public String serialize() {
        return serialize(message);
    }

    /**
     * 接口日志统一输出方法
     * @param message 信息对象
     */
    public static void infoLog(String message) {
        if (!StringUtils.isBlank(message)) {
            ApplicationLog<InfoLog> applicationLog = new ApplicationLog<InfoLog>();
            applicationLog.setLogType(LogType.INFO_LOG);
            applicationLog.setLogInfo(new InfoLog(message));
            //日志打印
            applicationLog.printLog();
        }
    }

}
