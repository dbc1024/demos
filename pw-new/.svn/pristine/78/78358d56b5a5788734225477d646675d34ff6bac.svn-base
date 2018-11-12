package com.ectrip.ticket.cyt.client.v1;

import java.io.UnsupportedEncodingException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.WebContant;
import com.ectrip.sys.SysparServiceApi;
import com.ectrip.sys.model.syspar.Sysparv5;

//import com.ectrip.ticket.client.v1.api.EctripOTOServiceSoapBindingStub;
//import com.ectrip.ticket.common.util.Base64;
//import com.ectrip.ticket.common.util.ConstUtils;
//import com.ectrip.ticket.common.util.DoXmlParamUtil;
//import com.ectrip.ticket.common.util.EncryptUtil;
//import com.ectrip.ticket.common.util.XMLParseUtil;
//import com.ectrip.ticket.model.CYTPojo;
//import com.ectrip.ticket.model.ConsumeInfo;
//import com.ectrip.ticket.model.request.NoticeOrderConsumedRequestBody;
//import com.ectrip.ticket.model.request.RefundOTOOrderRequestBody;
//import com.ectrip.ticket.model.request.RequestHeader;
//import com.ectrip.ticket.model.request.VerifyConsumeRequestBody;
//import com.ectrip.ticket.model.response.NoticeOrderConsumedResponseBody;
//import com.ectrip.ticket.model.response.RefundOTOOrderResponseBody;
//import com.ectrip.ticket.model.response.ResponseHeader;
//import com.ectrip.ticket.model.response.VerifyConsumeResponseBody;
//import com.ectrip.model.syspar.Sysparv5;
//import com.ectrip.model.syspar.Sysparv5Id;
//import com.ectrip.struts2.syspar.service.iservice.ISysparService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 调用去哪儿方法的client
 *
 * @author huhaopeng
 */
@Component(value="cytClient")
public class CYTClient {
//    private static EctripOTOServiceSoapBindingStub ssl = null;
//    private static EctripOTOServiceSoapBindingStub sslhx = null;
	@Autowired
	private SysparServiceApi sysparServiceApi;
	
// public static String CYTServiceURL;//消费通知URL
// public static String CYTHXServiceURL;//淘宝核销专用URL
//    static{
//    	 Sysparv5 v5 = sysparServiceApi.findOne("CYTU", "0001"); 
//         if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//             CYTServiceURL = v5.getPmvb();
//         }else{
//             CYTServiceURL = WebContant.GetKeyValue("CYTServiceURL");
//         }
//        v5 = sysparServiceApi.findOne("CYTU", "0002"); 
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            CYTHXServiceURL = v5.getPmvb();
//        }else{
//            String temp = WebContant.GetKeyValue("CYTHXServiceURL");
//            CYTHXServiceURL = (temp==null||"".equals(temp)?CYTServiceURL:temp);
//        }
//
//    }
    
    public String getCYTServiceURL() {
    	String CYTServiceURL = "";
    	 Sysparv5 v5 = sysparServiceApi.findOne("CYTU", "0001"); 
         if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
             CYTServiceURL = v5.getPmvb();
         }else{
             CYTServiceURL = WebContant.GetKeyValue("CYTServiceURL");
         }
         
         return CYTServiceURL;
    }
    
    public String getCYTHXServiceURL() {
    	String CYTHXServiceURL = "";
    	 Sysparv5 v5 = sysparServiceApi.findOne("CYTU", "0002"); 
        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
            CYTHXServiceURL = v5.getPmvb();
        }else{
            String temp = WebContant.GetKeyValue("CYTHXServiceURL");
            CYTHXServiceURL = (temp==null||"".equals(temp)?getCYTServiceURL():temp);
        }
        
        return CYTHXServiceURL;
    }

//    /**
//     * 用户消费通知 Describe:
//     * @param pojo
//     * @return
//     */
//    public static boolean noticeOrderConsumed(CYTPojo pojo) {
//        try {
//
//            // 1.给请求BODY塞值
//            NoticeOrderConsumedRequestBody body = new NoticeOrderConsumedRequestBody();
//            body.getOrderInfo().setPartnerorderId(pojo.getOrid());
//            body.getOrderInfo().setOrderQuantity(pojo.getOrderQuantity());
//            body.getOrderInfo().setUseQuantity(pojo.getUseQuantity());
//            // consumeInfo电子票消费信息非必传，不传！
//            ConsumeInfo consumeInfo = new ConsumeInfo();
//            consumeInfo.setSequenceIdentity(pojo.getSign());
//            consumeInfo.setTimeConsumed(pojo.getConsumedate());
//            String json = JSON.toJSONString(consumeInfo);
//            body.getOrderInfo().setConsumeInfo(json);
//            // 2. 获取 requestParam JSON 字符串
//            String requestJSON = null;
//            try {
//                requestJSON = getRequestParam(pojo.getOrderuserid(), pojo.getOto_code(),pojo.getVersion(), body);
//            } catch (Exception e1) {
//                //				e1.printStackTrace();
//                System.out.println(e1);
//                return false;
//            }
//            //			System.out.println("请求JSON字符串：" + requestJSON);
//            // body.getOrderInfo().setRefundResult(refund.get);//退款审核结果
//            try {
//				/*String response = HttpUtil.httpPost(url
//					+ "?method=noticeOrderConsumed", requestJSON);*/
//                //			String response = CallBase.call(url, "noticeOrderConsumed", requestJSON);
//                String response = SendData(1,"noticeOrderConsumed", requestJSON);
//
//                String responseXML = getParserXML(response);
//                //				System.out.println("解析后XML:\n" + responseXML);
//                NoticeOrderConsumedResponseBody responsebody = null;
//
//                Map map = XMLParseUtil.xmlToBean(responseXML, new ResponseHeader(),
//                        new NoticeOrderConsumedResponseBody());
//
//                ResponseHeader header = (ResponseHeader) map.get("header");
//                responsebody = (NoticeOrderConsumedResponseBody) map.get("body");
//                // 1.判定header返回值，是否为1000
//                if ("1000".equals(header.getCode().trim())) {
//                    // 消费通知OTA成功！
//                    System.out.println("用户消费通知OTA成功！");
//                    return true;
//                } else {
//                    // 消费通知OTA连接成功，返回异常信息：
//                    System.out.println("用户消费通知OTA：连接成功，出现异常：");
//                    System.out.println("异常状态码：" + header.getCode().trim());
//                    System.out.println("异常详细信息：" + header.getDescribe().trim());
//                    return false;
//                }
//            } catch (Exception e) {
//                // 4. httpPost请求出现异常
//                e.printStackTrace();
//                System.out.println("用户消费通知OTA：httpPost请求出现异常:");
//                //				e.printStackTrace();
//                return false;
//            }
//        } catch (Throwable e) {
//            //			e.printStackTrace();
//            System.out.println(e);
//            return false;
//        }
//    }
//
//    /**
//     * 核销: OTO出票前调用核销接口,返回成功进行出票
//     * Describe:
//     * @author:huhaopeng
//     * @param otaOrderId String OTA订单ID
//     * @param cytOrderId String 订单ID
//     * @param orderQuantity String 订单票数
//     * @param token String 令牌信息  (淘宝专用)
//     * @param posid String 机具id  (淘宝专用)
//     * @return
//     * return:boolean
//     * Date:2014-4-28
//     */
//    public static boolean verifyConsume(String orderuserid,String oto_code,String otaOrderId,String cytOrderId, String orderQuantity, String token, String posid) {
//        try {
//            // 1.给请求BODY塞值
//            System.out.println("開始核銷:"+orderuserid+" "+ oto_code+" "+ otaOrderId+" "+ cytOrderId+ " "+ orderQuantity+ " "+ token+ " "+ posid);
//            VerifyConsumeRequestBody body = new VerifyConsumeRequestBody();
//            body.getOrderInfo().setOtaOrderId(otaOrderId);
//            body.getOrderInfo().setCytOrderId(cytOrderId);
//            body.getOrderInfo().setOrderQuantity(orderQuantity);
//            body.getOrderInfo().setToken(token);
//            body.getOrderInfo().setPosid(posid);
//
//
//            // 2. 获取 requestParam JSON 字符串
//            String requestJSON = null;
//            try {
//                requestJSON = getRequestParam(orderuserid, oto_code, "2.0.0",body);
//            } catch (Exception e1) {
//                //				e1.printStackTrace();
//                System.out.println(e1);
//                return false;
//            }
//            System.out.println("请求JSON字符串：" + requestJSON);
//            // body.getOrderInfo().setRefundResult(refund.get);//退款审核结果
//            try {
//				/*String response = HttpUtil.httpPost(url
//					+ "?method=noticeOrderConsumed", requestJSON);*/
//                //			String response = CallBase.call(url, "noticeOrderConsumed", requestJSON);
//                String response = SendData(2,"verifyConsume", requestJSON);
//
//                String responseXML = getParserXML(response);
//                System.out.println("解析后XML:\n" + responseXML);
//                VerifyConsumeResponseBody responsebody = null;
//
//                Map map = XMLParseUtil.xmlToBean(responseXML, new ResponseHeader(),
//                        new VerifyConsumeResponseBody());
//
//                ResponseHeader header = (ResponseHeader) map.get("header");
//                responsebody = (VerifyConsumeResponseBody) map.get("body");
//                // 1.判定header返回值，是否为1000
//                if ("1000".equals(header.getCode().trim())) {
//                    // 消费通知OTA成功！
//                    System.out.println("用户消费通知OTA成功！");
//                    return true;
//                } else {
//                    // 消费通知OTA连接成功，返回异常信息：
//                    System.out.println("用户消费通知OTA：连接成功，出现异常：");
//                    System.out.println("异常状态码：" + header.getCode().trim());
//                    System.out.println("异常详细信息：" + header.getDescribe().trim());
//                    return false;
//                }
//            } catch (Exception e) {
//                // 4. httpPost请求出现异常
//                System.out.println("用户消费通知OTA：httpPost请求出现异常:"+e);
//                e.printStackTrace();
//                return false;
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public static boolean RefundOTOOrder(String orderuserid,String oto_code,String oldorid,String neworid,String price,String refundnumb,String refundmont,String tpsx){
//        try {
//            RefundOTOOrderRequestBody body = new RefundOTOOrderRequestBody();
//            RefundOTOOrderRequestBody.OrderInfo refund = new RefundOTOOrderRequestBody.OrderInfo();
//            //畅游通订单ID
//            refund.setPartnerorderId(oldorid);
//            //分销商退款流水号ID
//            refund.setRefundSeq(neworid);
//            //退订张数
//            refund.setRefundQuantity(refundnumb);
//            //退订单价
//            refund.setRefundPrice(price);
//            //退订金额
//            refund.setOrderRefundPrice(refundmont);
//            //退订手续费
//            refund.setOrderRefundCharge(tpsx);
//            //退订说明
//            refund.setRefundExplain("竹筏停排");
//
//            body.setOrderInfo(refund);
//            // 2. 获取 requestParam JSON 字符串
//            String requestJSON = null;
//            try {
//                requestJSON = getRequestParam(orderuserid, oto_code, "2.0.0",body);
//            } catch (Exception e1) {
//                System.out.println(e1);
//                return false;
//            }
//            System.out.println("请求JSON字符串：" + requestJSON);
//            try {
//                String response = SendData(1,"refundOTOOrder", requestJSON);
//
//                String responseXML = getParserXML(response);
//                System.out.println("解析后XML:\n" + responseXML);
//                RefundOTOOrderResponseBody responsebody = null;
//
//                Map map = XMLParseUtil.xmlToBean(responseXML, new ResponseHeader(),
//                        new RefundOTOOrderResponseBody());
//
//                ResponseHeader header = (ResponseHeader) map.get("header");
//                responsebody = (RefundOTOOrderResponseBody) map.get("body");
//                // 1.判定header返回值，是否为1000
//                if ("1000".equals(header.getCode().trim())) {
//                    // 退订通知OTA成功！
//                    System.out.println("用户退订通知OTA成功！");
//                    return true;
//                } else {
//                    // 退订通知OTA连接成功，返回异常信息：
//                    System.out.println("用户退订通知OTA：连接成功，出现异常：");
//                    System.out.println("异常状态码：" + header.getCode().trim());
//                    System.out.println("异常详细信息：" + header.getDescribe().trim());
//                    return false;
//                }
//            }catch (Exception e) {
//                // 4. 退订通知请求出现异常
//                System.out.println("用户退订通知OTA：请求出现异常:"+e);
//                e.printStackTrace();
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 获取请求requestParam 转换后的JSON字符串 Describe:
//     *
//     * @author:huhaopeng
//     * @param
//     *
//     * @param body
//     *            请求Body
//     * @return return:String Date:2014-3-29
//     */
//    private static String getRequestParam(String createUser, String supplierIdentity,String version, Object body)
//            throws Exception {
//
//        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        // 1.构建一个header，将其和body一起封装转换成xml
//
//        // ----------------将 xml bean 对象转换成 String(xml:respXml)-----------------
//        String respXml = null;
//        respXml = XMLParseUtil.beanToXML(body, DoXmlParamUtil.getOTARequestHeader(createUser, supplierIdentity,version),"ectrip");
//        //		System.out.println("response XML: \n" + respXml);
//
//        // -----------------------Base64编译----------------------
//        //		System.out.println("请求的XML:" + respXml);
//        byte[] responseByte = null;
//        responseByte = respXml.getBytes("utf-8");
//        String responseData = Base64.encode(responseByte);
//        //		System.out.println("Base64编译后的response XML：" + responseData);
//
//        // ---------------请求值(AES和MD5):MD5加密-----------------
//        String responseSigned = EncryptUtil.MD5Hex(
//                ConstUtils.KEY + responseData).toUpperCase();
//
//        // ---------------请求结果转换成 拼接成 JSONString 类型-------------
//        String requestJSON = null;
//        JSONObject jo = new JSONObject();
//        jo.put("data", responseData);
//        jo.put("signed", responseSigned);
//        jo.put("securityType", "MD5");
//        //		JSONStringer stringer = new JSONStringer();
//        //		requestJSON = stringer.object().key("data").value(responseData).key(
//        //				"signed").value(responseSigned).key("securityType")
//        //				.value("MD5").endObject().toString();
//
//        return jo.toJSONString();
//    }
//
//    /**
//     * 发送 web service 请求
//     * Describe:
//     * @author:huhaopeng
//     * @param method
//     * @param requestJson
//     * @return
//     * return:String
//     * Date:2014-4-28
//     */
//    private static String SendData(int type,String method, String requestJson) {
//        String responseString = null;
//        if(getStub(type) != null){
//            try {
//                responseString = getStub(type).doOTORequest(method, requestJson);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        return responseString;
//    }
//
//    /**
//     * 解析返回字符串 Describe:
//     *
//     * @author:huhaopeng
//     * @param xmlStr
//     * @return return:String xml字符串 null ： 解析异常 Date:2014-4-2
//     */
//    public static String getParserXML(String xmlStr) {
//        //		System.out.println("response JSON: " + xmlStr);
//        //		System.out
//        //		.println("-----------------------  开始转换JSON  -------------------------");
//        String data = null;
//        String signed = null;
//        String securityType = null;
//        try {
//
//            //			JSONObject jsonObject = JSONObject.fromObject(xmlStr);
//            JSONObject jsonObject = JSON.parseObject(xmlStr);
//            System.out
//                    .println("-----------------------  转换JSON成功，开始获取data，signed，securityType---------------------");
//            data = jsonObject.get("data").toString();
//            signed = jsonObject.get("signed").toString();
//            securityType = jsonObject.get("securityType").toString();
//
//            //			System.out
//            //			.println("-----------------json获取的请求参数如下：------------------");
//            //			System.out.println("data:" + data);
//            //			System.out.println("signed:" + signed);
//            //			System.out.println("securityType:" + securityType);
//
//        } catch (Exception e) {
//            //			e.printStackTrace();
//            System.out.println("Client ： OTA 返回信息，JSON解析异常:"+e);
//            return null;
//        }
//
//		/*--------------------- MD5解密 -----------------------*/
//        if ("MD5".equals(securityType)) {
//            // 明文加密后 与 密文对比 (key:123)
//            boolean bool = (EncryptUtil.MD5Hex(ConstUtils.KEY
//                    + data).toUpperCase()).equals(signed);
//            if (false == bool) {
//                System.out.println("Client OTA 返回信息， 签证验证不通过");
//                return null;
//            }
//        }// AES验证
//        else if ("AES".equals(securityType)) {
//
//        }
//
//        // ------------------Base64解码-------------------
//        System.out
//                .println("----------------------  开始解码  --------------------------");
//        byte[] xmlByte = Base64.decode(data);
//        String xml = null;
//        try {
//            xml = new String(xmlByte, "UTF-8");
//            // String result = new String(xml.getBytes("utf-8"),"GBK");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
//        return xml;
//    }
//    private static EctripOTOServiceSoapBindingStub getStub(int type){
//        if(type == 1){
//            if(ssl == null){
//                javax.xml.rpc.Service service = null;
//                java.net.URL endpointURL;
//                try {
//                    endpointURL = new java.net.URL(CYTServiceURL);
//                    ssl = new EctripOTOServiceSoapBindingStub(endpointURL, service);
//                    ssl.setMaintainSession(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return ssl;
//        }else{
//            if(sslhx == null){
//                javax.xml.rpc.Service service = null;
//                java.net.URL endpointURL;
//                try {
//                    endpointURL = new java.net.URL(CYTHXServiceURL);
//                    sslhx = new EctripOTOServiceSoapBindingStub(endpointURL, service);
//                    sslhx.setMaintainSession(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return sslhx;
//        }
//    }
}
