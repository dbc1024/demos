package com.ectrip.ticket.sale.service.OnlinePayment.service;

import java.net.InetAddress;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.HttpUtil;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.cytterminal.DataTool;
import com.ectrip.ec.model.cytterminal.DataTrans;
import com.ectrip.ec.model.order.PaymentBill;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.sale.service.OnlinePayment.model.AlipayExtraConfig;
import com.ectrip.ticket.sale.service.OnlinePayment.model.WeixinExtraConfig;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.OrderChargeQueryRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.OrderChargeRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.OrderChargeQueryResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.OrderChargeResponse;

/**
 * Created by cxh on 2016/06/24.
 */
public class BarPayService {

    private static final String PAYBACKURL = "http://"+ WebContant.GetKeyValue("DOMAIN")+"/payment/paymentBack.action";
    //    private static final String PAYBACKURL = "http://192.168.3.22:8080/payment/paymentBack.action";
    public static OrderChargeResponse orderPay(PaymentBill bill,String authCode,String paymenChannel,Sysparv5 sys,String newUrl) {
        try {
            //判断是否输入域名
            if(newUrl == null || newUrl.length()<1){
                newUrl = PAYBACKURL;
            }else{
                newUrl = "http://"+ newUrl+"/payment/paymentBack.action";
            }

            OrderChargeRequest request = new OrderChargeRequest();
            request.setObject("charge");
            request.setOrderNo(bill.getPayCode());
            request.setDevCode(bill.getWinCode());
            request.setChannelCode(paymenChannel);
            request.setAmount(bill.getMoney());
            request.setClientIp(InetAddress.getLocalHost().getHostAddress());
            request.setCurrency("CNY");
            request.setSubject("门票");
            request.setBody("门票");
            if(paymenChannel.equalsIgnoreCase("ALIPAY_BAR")){
                AlipayExtraConfig config = new AlipayExtraConfig();
                config.setAuth_code(authCode);
                request.setExtra(JSON.toJSONString(config));
            }else if(paymenChannel.equalsIgnoreCase("WX_BAR_T") || paymenChannel.equalsIgnoreCase("WX_BAR")){
                WeixinExtraConfig config = new WeixinExtraConfig();
                config.setAuth_code(authCode);
                request.setExtra(JSON.toJSONString(config));
            }else if(paymenChannel.equalsIgnoreCase("ALIPAY_QR")){
                AlipayExtraConfig config = new AlipayExtraConfig();
                config.setSuccess_url(newUrl);
                request.setExtra(JSON.toJSONString(config));
            }else if(paymenChannel.equalsIgnoreCase("WX_QR_T") || paymenChannel.equalsIgnoreCase("WX_QR")){
                WeixinExtraConfig config = new WeixinExtraConfig();
                config.setSuccess_url(newUrl);
                request.setExtra(JSON.toJSONString(config));
            }
            String json = DataTool.envelopeData(request, sys.getPmvb(), sys.getPmvc());
            String result = HttpUtil.httpPost(sys.getPmva(), "orderCharge", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(sys.getPmvb(), dataTrans.getData(), dataTrans.getSigned())){
                    OrderChargeResponse response = JSON.parseObject(dataTrans.getData(), OrderChargeResponse.class);
                    return response;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 订单接口查询
     * @param orderCode
     * @param sys
     * @return
     */
    public static OrderChargeQueryResponse orderQuery(String orderCode,Sysparv5 sys){
        try{
            OrderChargeQueryRequest request = new OrderChargeQueryRequest();
            request.setObject("charge");
            request.setChargeCode(orderCode);
            String json = DataTool.envelopeData(request, sys.getPmvb(), sys.getPmvc());
            String result = HttpUtil.httpPost(sys.getPmva(), "orderQuery", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(sys.getPmvb(), dataTrans.getData(), dataTrans.getSigned())){
                    OrderChargeQueryResponse response = JSON.parseObject(dataTrans.getData(), OrderChargeQueryResponse.class);
                    return response;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
