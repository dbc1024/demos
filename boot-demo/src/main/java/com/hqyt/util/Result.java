package com.hqyt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author: CSZ 991587100@qq.com
 * @date: 2018/12/25 13:47
 */
@Data
public class Result<T> {

    /** 成功统一标识码：1*/
    private String code = "1";

    /** 成功默认信息：success*/
    private String msg = "success";

    private T data;


    public static <T> Result<T> success(){

        return new Result<>();
    }

    public static <T> Result<T> success(T data){

        Result<T> result = success();
        result.setData(data);

        return result;
    }


    public static <T> Result<T> success(String msg){

        Result<T> result = success();
        result.setMsg(msg);

        return result;
    }

    public static <T> Result<T> success(String msg, T data){

        Result<T> result = success();
        result.setMsg(msg);
        result.setData(data);

        return result;
    }


    public static <T> Result<T> error(){

        Result<T> result = new Result<>();
        result.setCode("0");
        result.setMsg("未知异常，请联系管理员。");

        return result;
    }


    public static <T> Result<T> error(String msg){

        Result<T> result = error();
        result.setMsg(msg);

        return result;
    }


    public static <T> Result<T> error(String code, String msg){

        Result<T> result = error();
        result.setCode(code);
        result.setMsg(msg);

        return result;
    }


    /**
     * 服务间传输对象时
     * 可以在接口中指明泛型的具体类型
     * 也可以不指明，用此方法得到对应的实体bean
     * 建议使用前者(在接口中指明泛型的具体类型)
     * @param clazz
     * @return
     */
    public T getDataBean(Class clazz){

        T dataBean = (T) JSONObject.parseObject(JSON.toJSONString(getData()), clazz);

        return dataBean;
    }
}
