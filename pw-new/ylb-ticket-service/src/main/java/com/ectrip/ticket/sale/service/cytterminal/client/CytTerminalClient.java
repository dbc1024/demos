package com.ectrip.ticket.sale.service.cytterminal.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.DataTool;
import com.ectrip.base.util.HttpUtil;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.cytterminal.ConsumeOrderInfo;
import com.ectrip.ec.model.cytterminal.QueryOrderInfo;
import com.ectrip.ec.model.cytterminal.request.ConsumeOrderRequest;
import com.ectrip.ec.model.cytterminal.request.QueryOrderRequest;
import com.ectrip.sys.SysparServiceApi;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.cyt.common.util.ConstUtils;

@Component
public class CytTerminalClient {
	
	 @Autowired
	 private static  SysparServiceApi sysparServiceApi;
	 
	 @Autowired
	private ConstUtils constUtils;

//    public static String CYTTerminalURL;//畅游通域名
//    static{
//        IGenericService genericService = (IGenericService) SpringUtil.getBean("genericService");
//        Sysparv5 v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("CYTU","0003"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            CYTTerminalURL = v5.getPmvb();
//        }else{
//            CYTTerminalURL = WebContant.GetKeyValue("CYTTerminalURL");
//        }
//    }
	
	public static String getCYTTerminalURL() {
		String CYTTerminalURL = "";
		  Sysparv5 v5 = sysparServiceApi.findOne("CYTU","0003");
	      if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
	          CYTTerminalURL = v5.getPmvb();
	      }else{
	          CYTTerminalURL = WebContant.GetKeyValue("CYTTerminalURL");
	      }
	      
	      return CYTTerminalURL;
	}

    //订单查询
    public  Map<String,String> queryOrder(QueryOrderInfo queryOrderInfo){
        Map<String,String> map = new HashMap<String, String>();
        try {
            QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
            queryOrderRequest.setVer("2.0.0");
            queryOrderRequest.setVerType("CT");
            queryOrderRequest.setDevType("WDEV");
            queryOrderRequest.setDevId(queryOrderInfo.getDevId());
            queryOrderRequest.setMethod(queryOrderInfo.getMethod());
            queryOrderRequest.setCode(queryOrderInfo.getCode());
            queryOrderRequest.setPhone(queryOrderInfo.getPhone());
            queryOrderRequest.setCredentials(queryOrderInfo.getCredentials());
            queryOrderRequest.setOrderId(queryOrderInfo.getOrderId());
            queryOrderRequest.setStartDate(queryOrderInfo.getStartDate());
            queryOrderRequest.setEndDate(queryOrderInfo.getEndDate());
            queryOrderRequest.setCount(queryOrderInfo.getCount());
            String json = DataTool.envelopeData(queryOrderRequest, constUtils.getKEY(), queryOrderInfo.getOtoCode());
            System.out.println("订单查询请求："+json);
            try {
                String responseJson = HttpUtil.httpPost(getCYTTerminalURL(), "queryOrder", json);
                System.out.println(responseJson);
                JSONObject obj = JSON.parseObject(responseJson);
                String data = obj.getString("data");
                if(!StringUtils.isBlank(data)){
                    System.out.println(DataTool.getSign(constUtils.getKEY(), data) + ">>>>" + obj.getString("signed"));
                    if(DataTool.getSign(constUtils.getKEY(), data).equals(obj.getString("signed"))){
                        System.out.println("查询订单成功");
                        System.out.println("data:"+data);
                        JSONObject dataObj = JSON.parseObject(data);
                        String code = dataObj.getString("code");
                        String describe = dataObj.getString("describe");
                        map.put("code", code);
                        map.put("describe", describe);
                        if("1000".equals(code)){
                            map.put("data", obj.getString("data"));
                        }else{
                            System.out.println("用户查询订单：连接成功，出现异常：");
                            System.out.println("异常状态码：" + code);
                            System.out.println("异常详细信息：" + describe);
                        }
                    }else{
                        System.out.println("用户查询订单：连接成功，出现异常：签名错误");
                    }
                }else{
                    map.put("code", "-1");
                    map.put("describe", "data数据为空");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                map.put("code", "-1");
                map.put("describe", "系统请求畅游通异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-1");
            map.put("describe", "系统异常");
        }
        return map;
    }

    //消费通知
    public  Map<String,String> consumeOrder(ConsumeOrderInfo orderInfo){
        Map<String,String> map = new HashMap<String, String>();
        try {
            ConsumeOrderRequest consumeOrderRequest = new ConsumeOrderRequest();
            consumeOrderRequest.setVer("2.0.0");
            consumeOrderRequest.setVerType("CT");
            consumeOrderRequest.setDevType("WDEV");
            consumeOrderRequest.setDevId(orderInfo.getDevId());
            consumeOrderRequest.setId(orderInfo.getOrid());
            consumeOrderRequest.setPassword(orderInfo.getPassword());
            consumeOrderRequest.setVerifyPassword(orderInfo.getVerifyPassword());
            consumeOrderRequest.setConsumeCount(orderInfo.getConsumeCount());
            consumeOrderRequest.setSequenceIdentity(orderInfo.getSign());
            consumeOrderRequest.setTimeConsumed(Tools.getDayTimes());
            String json = DataTool.envelopeData(consumeOrderRequest, constUtils.getKEY(), orderInfo.getOtoCode());
            System.out.println("消费请求："+json);
            try {
                String responseJson = HttpUtil.httpPost(getCYTTerminalURL(), "consumeOrder", json);
                JSONObject obj = JSON.parseObject(responseJson);
                String data = obj.getString("data");
                if(!StringUtils.isBlank(data)){
                    if(DataTool.getSign(constUtils.getKEY(), obj.getString("data")).equals(obj.getString("signed"))){
                        System.out.println("用户消费成功");
                        System.out.println("data:"+data);
                        JSONObject dataObj = JSON.parseObject(data);
                        String code = dataObj.getString("code");
                        String describe = dataObj.getString("describe");
                        map.put("code", code);
                        map.put("describe", describe);
                        if("1000".equals(code)){
                            map.put("data", obj.getString("data"));
                        }else{
                            System.out.println("用户消费：连接成功，出现异常：");
                            System.out.println("异常状态码：" + code);
                            System.out.println("异常详细信息：" + describe);
                        }
                    }else{
                        System.out.println("用户消费：连接成功，出现异常：签名错误");
                    }
                }else{
                    map.put("code", "-1");
                    map.put("describe", "data数据为空");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                map.put("code", "-1");
                map.put("describe", "系统请求异常");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", "-1");
            map.put("describe", "系统异常");
        }
        return map;
    }
}

