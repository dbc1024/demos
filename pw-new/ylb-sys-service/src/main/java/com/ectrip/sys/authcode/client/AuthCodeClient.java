package com.ectrip.sys.authcode.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.sys.model.authcode.JsonMessage;
import com.ectrip.sys.model.authcode.OtherCodePojo;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.syspar.service.ISysparService;

/**
 * Created by chenxinhao on 2017/2/25.
 */
@Component
public class AuthCodeClient {
    private static String URL = "http://14.215.134.94:8701/";

//    private static IGenericDao genericDao;
    private static ISysparService sysparService;
    
    @Autowired
    public void setSysparService(ISysparService sysparService) {
		AuthCodeClient.sysparService = sysparService;
	}
    
//	static {
////        if (genericDao == null){
////            genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
////        }
//        Sysparv5 v5 = (Sysparv5) sysparService.get(Sysparv5.class,new Sysparv5Id("BDEC","0001"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmve())){
//            URL = v5.getPmve();
//        }
//    }

    public static String createSystemCode(JsonMessage message){
        try{
            Map<String,String> map = BeanUtils.describe(message);
            map = HttpUtil.paraFilter(map);
            Sysparv5 v5 = (Sysparv5) sysparService.get(Sysparv5.class,new Sysparv5Id("BDEC","0001"));
            if(v5 != null && !StringUtils.isBlank(v5.getPmve())){
              URL = v5.getPmve();
            }
            String json = HttpUtil.httpPost(URL+"sysCode/create.do",map);
            JSONObject object = JSON.parseObject(json);
            if(object.getString("success").equalsIgnoreCase("true")){
                String data = object.getString("data");
                JSONObject obj = JSON.parseObject(data);
                return obj.getString("signkey");
            }else{
                throw new RuntimeException(object.getString("description"));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("�ӿ��쳣:"+e.getMessage());
        }
    }

    public static String createOtherCode(String systemCode, String signKey, OtherCodePojo pojo) throws Exception{
        String data = JSON.toJSONString(pojo);
        Map map = new HashMap();
        map.put("data",DesUtil.encrypt(data,signKey));
        map.put("signed",SignUtil.getSign(signKey,data));
        map.put("systemCode",systemCode);
        Sysparv5 v5 = (Sysparv5) sysparService.get(Sysparv5.class,new Sysparv5Id("BDEC","0001"));
        if(v5 != null && !StringUtils.isBlank(v5.getPmve())){
          URL = v5.getPmve();
        }
        String json = HttpUtil.httpPost(URL+"code/add.do",map);
        JSONObject object = JSON.parseObject(json);
        if(object.getString("success").equalsIgnoreCase("true")){
            DataTrans dataTrans = JSON.parseObject(object.getString("data"),DataTrans.class);
            String decrypt = DesUtil.decrypt(dataTrans.getData(), signKey);
            JSONObject obj = JSON.parseObject(decrypt);
            return obj.getString("code");
        }else{
            throw new RuntimeException(object.getString("description"));
        }
    }
}
