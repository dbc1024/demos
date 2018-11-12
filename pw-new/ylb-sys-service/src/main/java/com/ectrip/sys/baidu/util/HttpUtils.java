package com.ectrip.sys.baidu.util;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.sys.model.baidu.pojo.DataTran;
import com.ectrip.sys.model.baidu.pojo.HttpStatusType;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;

/**
 * Created by chenxinhao on 2017/3/1.
 */
public class HttpUtils {

    private static String URL = "http://face.aip.baidu.com/";

    private static String ACCOUNT = "M9LWj6E1-ictu";

    private static String SECRET = "dy!#%489SErtHjk2";

    private static IGenericDao genericDao;

    static {
        if (genericDao == null){
            genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
        }
        Sysparv5 v5 = (Sysparv5) genericDao.get(Sysparv5.class,new Sysparv5Id("BDEC","0001"));
        if(v5 != null){
            URL = v5.getPmvb();
            ACCOUNT = v5.getPmvc();
            SECRET = v5.getPmvd();
        }
    }

    /*
    * ��ʼ������
    */
    public static DataTran invokeHttpPost(String requestUrl, String param){
        DataTran dataTran = new DataTran();
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(URL+requestUrl);
        StringRequestEntity entity = null;
        String response = "";
        String date = String.valueOf(new Date().getTime());
        try{
            postMethod.setRequestHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
            postMethod.setRequestHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
            postMethod.setRequestHeader("requestTime", date);
            postMethod.setRequestHeader("account", ACCOUNT);
            String signatureBefore = ACCOUNT + SECRET + date + param;
            String signature = DigestUtils.md5Hex(signatureBefore);
            System.out.println("signatureBefore:"+signatureBefore);
            System.out.println("signature:"+signature);
            postMethod.setRequestHeader("signature", signature);
            entity = new StringRequestEntity(param, ContentType.APPLICATION_JSON.toString(),"UTF-8");
            postMethod.setRequestEntity(entity);
            int statusCode = client.executeMethod(postMethod);
            dataTran.setCode(statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
                System.out.println("��������:"+response);
                dataTran.setData(response);
            } else {
                System.out.println("Post Method StatusCode:" + statusCode);
                dataTran.setData(HttpStatusType.typeOf(statusCode).getName());
            }
            postMethod.releaseConnection();
            client = null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataTran;
    }
}
