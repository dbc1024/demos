package com.ectrip.ticket.cyt.common.util;

import com.alibaba.fastjson.JSON;
import com.ectrip.hqyt.model.enums.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class JSONUtil {

    /**
     * 返回客户端json格式
     * @param status 状态码 0，成功， >0失败，参考ResponseStatus.java
     * @param data 传输数据对象
     * @param message 错误消息
     * @return
     */
    public static String toJsonStr(int status, Object data, String message){
        Map map = new HashMap();
        map.put("status", status);
        if(data != null)
            map.put("data", data);
        if(StringUtil.isNotEmpty(message))
            map.put("message", message);
        return JSON.toJSONString(map);
    }

    public static String toJsonStr(int status, Object data){
        return toJsonStr(status, data, null);
    }

    public static String toJsonStr(int status, String message){
        return toJsonStr(status, null, message);
    }

    /**
     * 返回成功状态的json
     * @param data
     * @return
     */
    public static String toSuccessJsonStr(Object data){
        return toJsonStr(ResponseStatus.SUCCESS.value(), data, null);
    }

    /**
     * 返回失败状态的json
     * @param message
     * @return
     */
    public static String toErrorJsonStr(String message){
        return toJsonStr(ResponseStatus.ERROR.value(), null, message);
    }
}
