package com.dbc.user.util;

import java.util.HashMap;

/**
 * @author: CSZ
 * @date: 2018/11/13 12:38
 */
public class Result extends HashMap<String, Object> {



    /**
     * 执行成功状态码
     */
    public final static int CODE_SUCCESS = 1;
    /**
     * 执行失败状态码
     */
    public final static int CODE_ERROR = 0;

    public Result() {
        put("code", CODE_SUCCESS);
        put("msg", "success");
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.put("data", data);

        return result;
    }

    public static Result success() {
        Result result = new Result();

        return result;
    }

    public static Result success(String msg, String code) {
        Result result = new Result();
        result.put("msg", msg);
        result.put("code", code);
        return result;
    }

    public static Result success(Object data, String msg, String code) {
        Result result = success(msg, code);
        result.put("data", data);

        return result;
    }


    public static Result error() {
        return error(CODE_ERROR, "系统内部异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(CODE_ERROR, msg);
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

}
