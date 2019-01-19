package com.hqyt;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author: CSZ 991587100@qq.com
 * @date: 2019/1/10 14:33
 */
public class JsonTest {

    @Test
    public void StringToJsonObject(){

        String str = "{'type':'zip','url':'http:192.168.31.2/upload/aaaaa.img','name':'营业执照（三证合一.jpg'}";

        JSONObject jsonObject = JSONObject.parseObject(str);

        System.out.println(jsonObject);
    }
}
