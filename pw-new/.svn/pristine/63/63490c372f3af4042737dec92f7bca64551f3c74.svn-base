package com.ectrip.ticket.model.log;

import java.text.SimpleDateFormat;

/**
 * @author wenqiang.luo date:15-11-13
 */
public class ExceptionLog extends BaseLog {

    private static final long serialVersionUID = -644474675210017107L;

    /** 交互描述 */
    private String description;

    /** 异常信息 */
    private String message;

    /** 堆栈信息 */
    private Throwable stackInfo;

    /* get and set */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getStackInfo() {
        return stackInfo;
    }

    public void setStackInfo(Throwable stackInfo) {
        this.stackInfo = stackInfo;
    }

    /**
     * 日志对象属性值序列
     *
     * @return 序列日志信息
     */
    @Override
    public String serialize() {
        //日志记录时间    日志描述信息  异常的message
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return BaseLog.serialize(sdf.format(datetime), description, message);
    }

    /**
     * 异常日志统一输出方法
     * @param description 异常描述
     * @param cause 异常信息
     */
    public static void printExceptionLog(String description, Throwable cause) {
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setDescription(description);
        exceptionLog.setMessage(cause.getMessage());
        exceptionLog.setStackInfo(cause);

        ApplicationLog<ExceptionLog> applicationLog = new ApplicationLog<ExceptionLog>();
        applicationLog.setLogType(LogType.EXCEPTION_LOG);
        applicationLog.setLogInfo(exceptionLog);
        //日志打印
        applicationLog.printLog();
    }
}
