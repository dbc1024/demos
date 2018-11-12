package com.hqyt.httpTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author: CSZ
 * @date: 2018/10/22 16:33
 * @description:
 */
public class HttpTest {

    @Test
    public void test1() {
        //6216613100020468529
        String urlstr = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=6221886712000985403&cardBinCheck=true";
        StringBuffer response = new StringBuffer();

        try {
            URL url = new URL(urlstr);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String inputLine;
            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(response);


        JSONObject parseObject = JSONObject.parseObject(response.toString());
        System.out.println(parseObject);
        System.out.println(parseObject.get("bank"));

    }
}
