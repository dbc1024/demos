package com.ectrip.base.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class HttpUtil {
    
    private static final int CONNECTION_TIME = 1000 * 60;  //正常设置在5秒：1000 * 5

    /**
     * 
     * Describe:
     * @author:huhaopeng
     * @param url
     * @param
     * @return
     * @throws Exception
     * return:String
     * Date:2014-3-24
     */
    public static String httpPost(String url,String method,String requestParam) throws Exception {  
        String response = "";  
        HttpClient client = new HttpClient();  
        //client.getHttpConnectionManager().getParams().setConnectionTimeout(60 * 1000);
        client.getHttpConnectionManager().getParams().setSoTimeout(120 * 1000);  
        
        PostMethod postMethod = new UTF8PostMethod(url);

        postMethod.addParameter("Connection", "Keep-Alive");
        postMethod.addParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
        postMethod.addParameter("Content-Type", "application/json;charset=UTF-8");
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 120 * 1000);
        
        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new Parameter("method", method));
        params.add(new Parameter("requestParam", requestParam));
        postMethod.setRequestBody(buildNameValuePair(params));  
        
        int statusCode = client.executeMethod(postMethod);  
        if (statusCode == HttpStatus.SC_OK) {
            response = postMethod.getResponseBodyAsString();
        } else {
            System.out.println("Post Method StatusCode:" + statusCode);
            throw new RuntimeException(postMethod.getResponseBodyAsString());
        }
        postMethod.releaseConnection();  
        client = null;  
        return response;  
    }
    
    /** 
     * 封装HttpPost的Name-Value参数对 
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
