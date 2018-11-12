package com.ectrip.base.util;

public class Response {
    public String ver;// 版本号
    public String code;// 返回代码
    public String describe;// 返回描述

    public Response() {
        super();
    }

    public Response(String version, String code, String describe) {
        super();
        this.ver = version;
        this.code = code;
        this.describe = describe;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
