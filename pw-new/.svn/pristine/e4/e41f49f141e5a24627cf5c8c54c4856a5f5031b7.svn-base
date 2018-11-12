package com.ectrip.ticket.model.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 系统应用日志
 * @author wenqiang.luo date:15-11-12
 */
public class ApplicationLog<T extends BaseLog> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationLog.class);
    private static final long serialVersionUID = -8573096466175468333L;

    /** 日志类型 */
    private LogType logType;

    /** 日志信息 */
    private T logInfo;

    /* get and set */

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public T getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(T logInfo) {
        this.logInfo = logInfo;
    }

    @Override
    public String toString() {
        return BaseLog.serialize(logType.getName(), logInfo.serialize());
    }

    /**
     * 日志打印 日志等级info
     */
    public void printLog() {
        logger.info(
                BaseLog.serialize(logType.getName(), logInfo.serialize())
        );
    }
}
