package com.ectrip.hqyt.client;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.util.DataTool;
import com.ectrip.base.util.DataTrans;
import com.ectrip.base.util.HttpUtil;
import com.ectrip.hqyt.model.request.BalanceRequest;
import com.ectrip.hqyt.model.request.ClientRequest;
import com.ectrip.hqyt.model.request.ConsumeOrderRequest;
import com.ectrip.hqyt.model.request.GuaranteeLykRequest;
import com.ectrip.hqyt.model.request.GuaranteeRequest;
import com.ectrip.hqyt.model.request.HqytPayCodeRequest;
import com.ectrip.hqyt.model.request.LegalPersonRequest;
import com.ectrip.hqyt.model.request.MerchantRequest;
import com.ectrip.hqyt.model.request.PayOrderRequest;
import com.ectrip.hqyt.model.request.ProductRequest;
import com.ectrip.hqyt.model.request.QueryBankInfoRequest;
import com.ectrip.hqyt.model.request.QueryOrderRequest;
import com.ectrip.hqyt.model.request.QueryParameterRequest;
import com.ectrip.hqyt.model.request.QueryPreBinding;
import com.ectrip.hqyt.model.request.QueryRateInfoRequest;
import com.ectrip.hqyt.model.request.QueryRefundOrderRequest;
import com.ectrip.hqyt.model.request.RefundbillsRequest;
import com.ectrip.hqyt.model.response.BalanceResponse;
import com.ectrip.hqyt.model.response.ConsumeResponse;
import com.ectrip.hqyt.model.response.CreateClientResponse;
import com.ectrip.hqyt.model.response.CreateMerchantsResponse;
import com.ectrip.hqyt.model.response.GuaranteeResponse;
import com.ectrip.hqyt.model.response.HqytPayCodeResponse;
import com.ectrip.hqyt.model.response.JSONBalance;
import com.ectrip.hqyt.model.response.JSONBank;
import com.ectrip.hqyt.model.response.JSONClient;
import com.ectrip.hqyt.model.response.JSONInvoice;
import com.ectrip.hqyt.model.response.JSONMerchant;
import com.ectrip.hqyt.model.response.JSONParameter;
import com.ectrip.hqyt.model.response.JSONProduct;
import com.ectrip.hqyt.model.response.JSONRate;
import com.ectrip.hqyt.model.response.JSONRefundBill;
import com.ectrip.hqyt.model.response.ProductResponse;
import com.ectrip.hqyt.model.response.QueryBankResponse;
import com.ectrip.hqyt.model.response.QueryParameterResponse;
import com.ectrip.hqyt.model.response.QueryRateResponse;
import com.ectrip.hqyt.model.response.RefundbillsResponse;

/**
 * Created by chenxinhao on 17/1/14.
 */
public class HqytClient {

	public static String url = "http://127.0.0.1:8089/service/payService.do";
    
    public static String appPayUrl = "https://www.upaypal.cn/business-platform.integration.alipay/tp/alipay";

    public static String key = "HQYTECTRIP1234&!@";

    public static String identity = "";
    public static String APP_DOWNLOAD_URL = "";

    //private ISysparService sysparService;// 系统参数表

    /*static {
        InputStream inStream = HqytClient.class.getClassLoader().getResourceAsStream("hqyt.properties");
        Properties prop = new Properties();
        try {
            prop.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = prop.getProperty("URL");
        key = prop.getProperty("KEY");
        APP_DOWNLOAD_URL = prop.getProperty("APP_DOWNLOAD_URL");
    }*/

    /**
     * 开户
     * @param request
     * @return
     */
    public JSONMerchant createMerchants(MerchantRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "createMerchants", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    CreateMerchantsResponse response = JSON.parseObject(dataTrans.getData(), CreateMerchantsResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getMerchant();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 查询余额
     * @param request
     * @return
     */
    public JSONBalance searchBalance(BalanceRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "searchBalance", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    BalanceResponse response = JSON.parseObject(dataTrans.getData(), BalanceResponse.class);
                    return response.getBalance();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 有效无效用户操作
     * @param request
     * @return
     */
    public String changeStatusOfUser(LegalPersonRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "changeStatusOfUser", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    BalanceResponse response = JSON.parseObject(dataTrans.getData(), BalanceResponse.class);
                    return  "success";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    	return "false";
    }
    
    
    
    /**
     * 查询产品
     * @param request
     * @return
     */
    public JSONProduct searchProduct(ProductRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "searchProduct", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONProduct product = JSON.parseObject(dataTrans.getData(), JSONProduct.class);
                    return product;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建产品
     * @param request
     * @return
     */
    public JSONProduct createProduct(ProductRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "createProduct", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    ProductResponse response = JSON.parseObject(dataTrans.getData(), ProductResponse.class);
                    return response.getProduct();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 散客下单
     * @param request
     * @return
     */
    public JSONInvoice createGuaranteeSk(GuaranteeRequest request) {
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "createGuaranteeSk", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    GuaranteeResponse response = JSON.parseObject(dataTrans.getData(), GuaranteeResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getInvoice();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 电商APP下单
     * @param request
     * @return
     */
    public JSONInvoice appGuaranteeSk(GuaranteeRequest request) {
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "createGuaranteeSk", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    GuaranteeResponse response = JSON.parseObject(dataTrans.getData(), GuaranteeResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getInvoice();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 电商APP旅游卡下单
     * @param request
     * @return
     */
    public JSONInvoice appGuaranteeSkLyk(GuaranteeLykRequest request) {
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "createGuaranteeSkl", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    GuaranteeResponse response = JSON.parseObject(dataTrans.getData(), GuaranteeResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getInvoice();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
    
    /**
     * 分销商下单
     * @param request
     * @return
     */
    public JSONInvoice createGuarantee(GuaranteeRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "createGuarantee", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    GuaranteeResponse response = JSON.parseObject(dataTrans.getData(), GuaranteeResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getInvoice();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 退订
     * @param request
     * @return
     */
    public JSONRefundBill refundbills(RefundbillsRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "refundbills", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    RefundbillsResponse response = JSON.parseObject(dataTrans.getData(), RefundbillsResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getRefundBill();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 订单查询
     * @param request
     * @return
     */
    public JSONInvoice queryOrder(QueryOrderRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "queryOrder", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONInvoice invoice = JSON.parseObject(dataTrans.getData(), JSONInvoice.class);
                    return invoice;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退订单查询
     * @param request
     * @return
     */
    public JSONRefundBill queryRefundOrder(QueryRefundOrderRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "queryRefundOrder", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONRefundBill refundBill = JSON.parseObject(dataTrans.getData(), JSONRefundBill.class);
                    return refundBill;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 订单核销
     * @param request
     * @return
     */
    public JSONInvoice consumeOrder(ConsumeOrderRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "consumeOrder", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    ConsumeResponse response = JSON.parseObject(dataTrans.getData(), ConsumeResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getInvoice();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 订单无密支付
     * @param request
     * @return
     */
    public JSONInvoice payOrder(PayOrderRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "payOrder", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONInvoice invoice = JSON.parseObject(dataTrans.getData(), JSONInvoice.class);
                    return invoice;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询银行列表
     * @param request
     * @return
     */
    public List<JSONBank> queryBanks(QueryBankInfoRequest request){
        try{
            System.out.println("url:"+url);
            String json = DataTool.envelopeData(request,key,identity);
            String result = HttpUtil.httpPost(url,"queryBankInfo",json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    QueryBankResponse response = JSON.parseObject(dataTrans.getData(), QueryBankResponse.class);
                    return response.getBanks();
                }
            }
        }catch (Exception e){
            System.out.println("queryBanks接口异常");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询费率列表
     * @param request
     * @return
     */
    public List<JSONRate> queryRates(QueryRateInfoRequest request){
        try{
            String json = DataTool.envelopeData(request,key,identity);
            String result = HttpUtil.httpPost(url,"queryRateInfo",json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    QueryRateResponse response = JSON.parseObject(dataTrans.getData(), QueryRateResponse.class);
                    return response.getRates();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<JSONParameter> queryParameterInfo(QueryParameterRequest request){
        try{
            String json = DataTool.envelopeData(request,key,identity);
            String result = HttpUtil.httpPost(url,"queryParameterInfo",json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    QueryParameterResponse response = JSON.parseObject(dataTrans.getData(), QueryParameterResponse.class);
                    return response.getParameters();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 修改游览日期时，同时修改结算系统consumeTimeLimit by lijun 20170614
     * @param request
     * @return
     */
    public JSONInvoice modifyHQYTConsumeTimeLimit(GuaranteeRequest request) {
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "modifyInvoiceConsumeTimeLimit", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    GuaranteeResponse response = JSON.parseObject(dataTrans.getData(), GuaranteeResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getInvoice();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
    
    /**
     * 同步用户修改信息到 结算系统 by lijun 20170615
     * @param request
     * @return
     */
    public JSONMerchant modifyLegalPerson(MerchantRequest request) {
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "modifyLegalPerson", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                	CreateMerchantsResponse response = JSON.parseObject(dataTrans.getData(), CreateMerchantsResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getMerchant();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
    
    /**
     * 散客注册 同步注册 结算
     * @param request
     * @return
     */
    public JSONClient createClient(ClientRequest request){
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "createClient", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    CreateClientResponse response = JSON.parseObject(dataTrans.getData(), CreateClientResponse.class);
                    if(!"1000".equalsIgnoreCase(response.getCode())){
                        throw new RuntimeException(response.getDescribe());
                    }
                    return response.getClient();
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 用户绑定查询列表
     * @param request
     * @return
     */
    public JSONArray qpaybindList(String request) {
        try{
            String json = DataTool.envelopeDatal(request, key, identity);
            String result = HttpUtil.httpPost(url, "qpaybindList", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONArray jsonArray = JSONArray.parseArray(dataTrans.getData());
                    return jsonArray;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 用户绑定
     * @param request
     * @return
     */
    public JSONObject legalPersonId(String request) {
        try{
            String json = DataTool.envelopeDatal(request, key, identity);
            String result = HttpUtil.httpPost(url, "legalPersonId", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONObject jsonObject = JSONObject.parseObject(dataTrans.getData());
                    return jsonObject;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 取消订单接口
     * @param request
     * @return
     */
    public JSONObject cancelOrder(String request) {
        try{
            String json = DataTool.envelopeDatal(request, key, identity);
            String result = HttpUtil.httpPost(url, "cancelOrder", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONObject jsonObject = JSONObject.parseObject(dataTrans.getData());
                    return jsonObject;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 用户绑定
     * @param request
     * @return
     */
    public JSONObject qpayCardOpen(QueryPreBinding request) {
        try{
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "qpayPreBinding", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONObject jsonObject = JSONObject.parseObject(dataTrans.getData());
                    return jsonObject;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 银行卡卡BIN查询
     * @param request
     * @return
     */
    public JSONObject qpayGetCardBank(String request) {
        try{
            String json = DataTool.envelopeDatal(request, key, identity);
            String result = HttpUtil.httpPost(url, "qpayGetCardBank", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    JSONObject jsonObject = JSONObject.parseObject(dataTrans.getData());
                    return jsonObject;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    /**
     * 环球雅图
     * @param request
     * @return
     */
    public HqytPayCodeResponse hqytPayCode(HqytPayCodeRequest request, String hqytMainUrl) {
        try{
        	
        //	hqytMainUrl改为调用方法者传入
//            if(sysparService==null)
//            {
//                sysparService=(ISysparService) SpringUtil.getBean("sysparService");
//            }
//            Sysparv5 sysv5 = sysparService.findOne("HQYT", "0002");
//            String  hqytMainUrl=sysv5.getPmvd();
            if(request!=null)
            {
                request.setRequestUrl(hqytMainUrl);
            }
            String json = DataTool.envelopeData(request, key, identity);
            String result = HttpUtil.httpPost(url, "createPayCode", json);
            DataTrans dataTrans = JSON.parseObject(result, DataTrans.class);
            if(!StringUtils.isBlank(dataTrans.getData())){
                if(DataTool.checkData(key, dataTrans.getData(), dataTrans.getSigned())){
                    HqytPayCodeResponse response = JSON.parseObject(dataTrans.getData(), HqytPayCodeResponse.class);
                    if(!"SUCCESS".equalsIgnoreCase(response.getResultCode())){
                        throw new RuntimeException(response.getReturnMsg());
                    }
                    return response;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
        /*try{
            //String json = DataTool.envelopeData(request, key, identity);
            if(sysparService==null)
            {
                sysparService=(ISysparService)SpringUtil.getBean("sysparService");
            }
            Sysparv5 sysv5 = sysparService.findOne("HQYT", "0002");
            String  hqytMainUrl=sysv5.getPmvd();
            String result="";
            String param;
            String param1="?merchantId="+request.getMerchantId()+"&outTradeNo="+request.getOutTradeNo()+"&returnUrl=www.baidu.com";;
            if(request!=null)
            {
                *//*if(request.getPayType().equals("01") || request.getPayType().equals("02"))
                {
                    param="?merchantId="+request.getMerchantId()+"&outTradeNo="+request.getOutTradeNo()+"&authCode="+request.getAuthCode();
                    result= HttpUtil.httpsGet(hqytMainUrl+"/business-platform.payment-channel.web/hqpay/gateway/micropay" +param);
                }else if(request.getPayType().equals("03"))
                {
                    result= HttpUtil.httpsGet(hqytMainUrl+"/business-platform.payment-channel.web/hqpay/gateway/alipay/precreate" +param1);
                }else if(request.getPayType().equals("04"))
                {
                    result= HttpUtil.httpsGet(hqytMainUrl+"/business-platform.payment-channel.web/hqpay/gateway/wxpay/precreate" +param1);
                }*//*
            }
            response= JSON.parseObject(result, HqytPayCodeResponse.class);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return response;*/
    }

}
