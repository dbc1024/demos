package com.ectrip.sys.authcode.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.ectrip.base.util.Parameter;
import com.ectrip.base.util.UTF8PostMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxinhao on 2017/2/25.
 */
public class HttpUtil {

    /**
     * ��ȥ�����еĿ�ֵ��ǩ������
     *
     * @param sArray ǩ��������
     * @return ȥ����ֵ��ǩ�����������ǩ��������
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type") || key.equalsIgnoreCase("class")) {
                continue;
            }
            result.put(key.toLowerCase(), value);
        }

        return result;
    }

    private static final int CONNECTION_TIME = 1000 * 15; //����������5�룺1000 * 5

    /**
     *
     * Describe:
     * @author:huhaopeng
     * @param url
     * @param params
     * @return
     * @throws Exception
     * return:String
     * Date:2014-3-24
     */
    public static String httpPost(String url, Map<String,String> map) throws Exception {
        String response = "";
        HttpClient client = new HttpClient();
        PostMethod postMethod = new UTF8PostMethod(url);

        postMethod.addParameter("Connection", "Keep-Alive");
        postMethod.addParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        postMethod.addParameter("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, CONNECTION_TIME);
        List<Parameter> params = new ArrayList<Parameter>();
        for (Map.Entry<String,String> m : map.entrySet()){
            params.add(new Parameter(m.getKey(),m.getValue()));
        }
        postMethod.setRequestBody(buildNameValuePair(params));

        int statusCode = client.executeMethod(postMethod);
        if (statusCode == HttpStatus.SC_OK) {
            String responseCharSet = postMethod.getResponseCharSet();
            System.out.println(responseCharSet);
            System.out.println(postMethod.getResponseBodyAsString());
            System.out.println(new String(postMethod.getResponseBodyAsString().getBytes("ISO8859-1"),responseCharSet));
            System.out.println(new String(postMethod.getResponseBodyAsString().getBytes("GBK"),responseCharSet));
            response = new String(postMethod.getResponseBodyAsString().getBytes("GBK"),responseCharSet);
        } else {
            System.out.println("Post Method StatusCode:" + statusCode);
        }
        postMethod.releaseConnection();
        client = null;
        return response;
    }

    /**
     * ��װHttpPost��Name-Value������
     *
     * @param list
     * @return
     */
    private static NameValuePair[] buildNameValuePair(List<Parameter> list) {
        int length = list.size();
        NameValuePair[] pais = new NameValuePair[length];
        for (int i = 0; i < length; i++) {
            Parameter param = list.get(i);
            pais[i] = new NameValuePair(param.getName(), param.getValue());
        }
        return pais;
    }
}
