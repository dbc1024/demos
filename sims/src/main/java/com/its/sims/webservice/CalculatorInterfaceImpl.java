package com.its.sims.webservice;



import net.sf.json.JSONObject;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by csz on 2017/9/21.
 */
@WebService(endpointInterface = "com.its.sims.webservice.CalculatorInterface")
public class CalculatorInterfaceImpl implements CalculatorInterface {
    @Override
    public int add(int a, int b) {
        System.out.println("客户端调用加法，参数为："+a+","+b);

        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        System.out.println("客户端调用减法，参数为："+a+","+b);
        return a-b;
    }

    @Override
    public String tranByXml(String str) {
        System.out.println("web services调用成功");
        String result = createXml(str);
        return result;
    }


    @Override
    public String tranJsonStr(String str) {
        return createJsonObject(str).toString();
    }


    /**
     * 根据传递的字符串生成对应的xml。
     *
     * @param name
     * @return 返回xml的字符串
     */
    public String createXml(String name) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");
        sb.append("<Result>");
        sb.append("<cinamaName>"+ name +"</cinamaName>");
        sb.append("<director>安迪</director>");
        sb.append("<introduce>一部好莱坞大片，3D观影，不错！！！</introduce>");
        sb.append("<price>25</price>");
        sb.append("</Result>");
        return sb.toString();
    }

    /**
     * 创建JsonOBject对象
     *
     * @param name
     * @return 返回jsonObject对象
     */
    JSONObject createJsonObject(String name) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", name);
        String[] likes = new String[] { "music", "movie", "study" };
        jsonObj.put("hobby", likes);
        Map<String, String> ingredients = new HashMap<String, String>();
        ingredients.put("age", "23");
        ingredients.put("EnglishName", "spider man");
        ingredients.put("sex", "boy");
        ingredients.put("love", "tangwei");
        jsonObj.put("message", ingredients);
        return jsonObj;
    }

}
