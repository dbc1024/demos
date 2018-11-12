package com.ectrip.ticket.sale.service.OnlinePayment.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.enums.InvoiceStatus;
import com.ectrip.hqyt.model.request.HqytPayCodeRequest;
import com.ectrip.hqyt.model.request.QueryOrderRequest;
import com.ectrip.hqyt.model.response.HqytPayCodeResponse;
import com.ectrip.hqyt.model.response.JSONInvoice;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.log.ExceptionLog;
import com.ectrip.ticket.model.log.InfoLog;
import com.ectrip.ticket.model.log.InterfaceLog;
import com.ectrip.ticket.model.log.InterfaceType;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.AddAndPayBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.AddBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.AddUpacpBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.PayBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.QueryBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.SavePaymentOrderRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.SearchPaymentOrderRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.AddAndPayBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.AddBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.AddUpacpBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.PayBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.QueryBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.SavePaymentOrderResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.SearchPaymentOrderResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.service.iservice.IOnlinePaymentService;

/**
 * Created by cxh on 2016/07/20.
 */
@Component
public class OnlinePaymentWebService {
	
	private static SysService sysService;
	
	private static EcService ecService;

    private static IOnlinePaymentService onlinePaymentService;
    @Autowired
	public void setOnlinePaymentService(IOnlinePaymentService onlinePaymentService) {
		OnlinePaymentWebService.onlinePaymentService = onlinePaymentService;
	}
    @Autowired
	public void setSysService(SysService sysService) {
		OnlinePaymentWebService.sysService = sysService;
	}
    @Autowired
	public void setEcService(EcService ecService) {
		OnlinePaymentWebService.ecService = ecService;
	}
	
	
	
	public Object savePaymentOrder(String data){
        SavePaymentOrderResponse res;
        try{
            res = onlinePaymentService.savePaymentOrder(JSON.parseObject(data,SavePaymentOrderRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("保存临时订单数据");
            interfaceLog.setInterfaceMethod("savePaymentOrder");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new SavePaymentOrderResponse();
            res.setCode("2001");
            res.setDescribe("保存临时订单失败");
            ExceptionLog.printExceptionLog("保存临时订单失败", e);
        }
        return res;
    }

    public Object searchPaymentOrder(String data){
        SearchPaymentOrderResponse res;
        try{
            res = onlinePaymentService.searchPaymentOrder(JSON.parseObject(data,SearchPaymentOrderRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("查询支付订单报文");
            interfaceLog.setInterfaceMethod("searchPaymentOrder");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new SearchPaymentOrderResponse();
            res.setCode("2001");
            res.setDescribe("查询支付订单报文失败");
            ExceptionLog.printExceptionLog("查询支付订单报文失败", e);
        }
        return res;
    }

    public Object addBill(String data){
        AddBillResponse res;
        try{
            res = onlinePaymentService.addBill(JSON.parseObject(data,AddBillRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("订单保存");
            interfaceLog.setInterfaceMethod("addBill");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new AddBillResponse();
            res.setCode("2001");
            res.setDescribe("订单保存失败");
            ExceptionLog.printExceptionLog("订单保存失败", e);
        }
        return res;
    }

    public Object payBill(String data,String newUrl){
        PayBillResponse res;
        try{
            //判断是否输入域名
            if(newUrl == null || newUrl.length()<1){
                newUrl = WebContant.GetKeyValue("DOMAIN");
            }

            res = onlinePaymentService.payBill(JSON.parseObject(data, PayBillRequest.class),newUrl);
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("订单支付");
            interfaceLog.setInterfaceMethod("payBill");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new PayBillResponse();
            res.setCode("2001");
            res.setDescribe("订单支付失败");
            ExceptionLog.printExceptionLog("订单支付失败", e);
        }
        return res;
    }

    public Object addAndPayBill(String data,String newUrl){
        AddAndPayBillResponse res;
        try{
            //判断是否输入域名
            if(newUrl == null || newUrl.length()<1){
                newUrl = WebContant.GetKeyValue("DOMAIN");
            }


            res = onlinePaymentService.addAndPayBill(JSON.parseObject(data, AddAndPayBillRequest.class),newUrl);
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("生成订单");
            interfaceLog.setInterfaceMethod("addAndPayBill");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new AddAndPayBillResponse();
            res.setCode("2001");
            res.setDescribe("生成订单失败");
            ExceptionLog.printExceptionLog("生成订单失败", e);
        }
        return res;
    }

    /**
     * 扫码
     * @param data
     * @return
     */
    public Object hqytPay(String data){
        HqytPayCodeResponse res;
        try{
            HqytClient hqytClient=new HqytClient();
            Sysparv5 sys = sysService.findOne("HQYT","0001");
            String merchantId=sys.getPmvb();
            HqytPayCodeRequest request =JSON.parseObject(data,HqytPayCodeRequest.class);
            request.setMerchantId(merchantId);
            Sysparv5 sysv5 = sysService.findOne("HQYT", "0002");
            String  hqytMainUrl=sysv5.getPmvd();
            res = hqytClient.hqytPayCode(request,hqytMainUrl);
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("生成订单");
            interfaceLog.setInterfaceMethod("hqytPay");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new HqytPayCodeResponse();
            res.setResultCode("2001");
            res.setReturnMsg("生成订单失败");
            ExceptionLog.printExceptionLog("生成订单失败", e);
        }
        return res;
    }

    public Object queryBill(String data){
        QueryBillResponse res;
        try{
            res = onlinePaymentService.queryBill(JSON.parseObject(data, QueryBillRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("查询订单");
            interfaceLog.setInterfaceMethod("queryBill");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new QueryBillResponse();
            res.setCode("2001");
            res.setDescribe("查询订单异常");
            ExceptionLog.printExceptionLog("查询订单异常", e);
        }
        return res;
    }

    /**
     * 查询环球支付订单状态
     * @param data
     * @return
     */
    public Object queryHqytBill(String data){
        QueryBillResponse res= new QueryBillResponse();
        try{
            //res = onlinePaymentService.queryHqytBill(JSON.parseObject(data, QueryBillRequest.class));
            HqytClient client = new HqytClient();
            JSONInvoice invoice = null;
            QueryBillRequest request=JSON.parseObject(data, QueryBillRequest.class);
            String orid=request.getOrid();
            QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
            queryOrderRequest.setOrid(orid);
            invoice = client.queryOrder(queryOrderRequest);
            InvoiceStatus invoiceStatus = invoice != null ? invoice.getStatus() : null;
            if(invoiceStatus != null && InvoiceStatus.SUCCESS.getText().equals(invoiceStatus.getText()) || InvoiceStatus.TOBECONFIRM.getText().equals(invoiceStatus.getText())){
                List<LOrder> list = ecService.getLorderByOrid(request.getOrid());
                LOrder lOrder = null;
                if (list != null && !list.isEmpty()) {
                    lOrder =list.get(0);
                }
                if (lOrder == null) {
                    InfoLog.infoLog("支付查询异常：订单[" + orid + "]不存在.");
                    res.setCode("404");
                    res.setStatus("N");
                    res.setDescribe("订单不存在");
                    return res;
                }

                if (lOrder.getZfmont().doubleValue()!=invoice.getTotalPrice().doubleValue()) {
                    InfoLog.infoLog("支付查询异常：订单[" + orid + "]，返回的订单金额与系统中的订单金额不一致.");
                    res.setCode("405");
                    res.setStatus("N");
                    res.setDescribe("订单金额不一致");
                    return res;
                }
                if (lOrder.getDdzt().equalsIgnoreCase("00")) {// //未付款状态
                    try {
                        lOrder.setDdzt("02");
                        ecService.updateLOrder(lOrder);
                        res.setCode("200");
                        res.setStatus("Y");
                        res.setDescribe("查询订单状态成功");
                        InfoLog.infoLog("订单查询：订单[" + orid + "]支付成功！");
                    } catch (Exception e) {
                        e.printStackTrace();
                        res.setStatus("N");
                        res.setDescribe("更新订单状态失败");
                        InfoLog.infoLog("付款成功，更新订单状态失败：" + e.getMessage());
                    }
                }else if(lOrder.getDdzt().equalsIgnoreCase("02"))
                {
                    res.setCode("200");
                    res.setStatus("Y");
                    res.setDescribe("查询订单状态成功");
                    InfoLog.infoLog("订单查询：订单[" + orid + "]支付成功！");
                }
            }
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("查询订单");
            interfaceLog.setInterfaceMethod("queryHqytBill");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res.setCode("500");
            res.setDescribe("查询订单异常");
            ExceptionLog.printExceptionLog("查询订单异常", e);
        }
        return res;
    }

    /**
     * 保存银联支付流水接口
     * @param data
     * @return
     */
    public Object AddUpacpBill(String data){
        AddUpacpBillResponse res;
        try{
            res = onlinePaymentService.AddUpacpBill(JSON.parseObject(data, AddUpacpBillRequest.class));
            InterfaceLog interfaceLog = new InterfaceLog();
            interfaceLog.setInterfaceType(InterfaceType.PAY);
            interfaceLog.setRequestData(data);
            interfaceLog.setResponseData(JSON.toJSONString(res));
            interfaceLog.setDescription("保存订单支付流水");
            interfaceLog.setInterfaceMethod("AddUpacpBill");
            InterfaceLog.printInterfaceLog(interfaceLog);
        }catch (Exception e){
            e.printStackTrace();
            res = new AddUpacpBillResponse();
            res.setCode("2001");
            res.setDescribe("保存订单支付流水失败");
            ExceptionLog.printExceptionLog("保存订单支付流水失败", e);
        }
        return res;
    }
}
