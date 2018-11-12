package com.ectrip.ticket.cyt.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.WebContant;
import com.ectrip.sys.SysparServiceApi;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;

@Component
public class ConstUtils {
    public static String KEY = "OTOECTRIP";
    public static String supportCYT;
    public static String supportJPCP;
    public static String HQYTKEY = "";
    public static String HQYT5 = "";
    
    @Autowired
	private  SysparServiceApi sysparServiceApi;
    
    public  String getHQYT5() {
    	String HQYT5 = "";
    	 Sysparv5 v5 = sysparServiceApi.findOne("HQYT","0001");
         if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
         	HQYT5 = v5.getPmvb()+"|"+v5.getPmvc();
         }else{
         	HQYT5 = WebContant.GetKeyValue("CYTKey");
         }
         
         return HQYT5;
    }
    
    public  String getHQYTKEY() {
    	String HQYTKEY = "";
    	Sysparv5 v5 = sysparServiceApi.findOne("HQYT","0005");
        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
            HQYTKEY = v5.getPmvb();
        }else{
        	HQYTKEY = WebContant.GetKeyValue("CYTKey");
        }
        return HQYTKEY;
    }
    
    public  String getsupportJPCP() {
    	String supportJPCP = "";
    	Sysparv5 v5 = sysparServiceApi.findOne("HQYT","0006");
        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
            supportJPCP = v5.getPmvb();
        }else{
            supportJPCP = WebContant.GetKeyValue("supportJPCP");
        }
        
        return supportJPCP;
    }
    
    public  String getsupportCYT() {
    	String supportCYT = "";
    	Sysparv5 v5 = sysparServiceApi.findOne("CYTU","0005");
        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
            supportCYT = v5.getPmvb();
        }else{
            supportCYT = WebContant.GetKeyValue("supportCYT");
        }
        
        return supportCYT;
    }
    
    public  String getKEY() {
    	String KEY = "";
    	Sysparv5 v5 = sysparServiceApi.findOne("CYTU","0004");
        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
            KEY = v5.getPmvb();
        }else{
            KEY = WebContant.GetKeyValue("CYTKey");
        }
        
        return KEY;
    }
//    
//    static{
//        IGenericService genericService = (IGenericService) SpringUtil.getBean("genericService");
//       
//        v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("HQYT","0005"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//        	supportCYT = v5.getPmvb();
//        }else{
//        	supportCYT = WebContant.GetKeyValue("supportCYT");
//        }
//        v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("HQYT","0006"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            supportJPCP = v5.getPmvb();
//        }else{
//            supportJPCP = WebContant.GetKeyValue("supportJPCP");
//        }
//    }
//    static{
//        IGenericService genericService = (IGenericService) SpringUtil.getBean("genericService");
//        Sysparv5 v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("CYTU","0004"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            KEY = v5.getPmvb();
//        }else{
//            KEY = WebContant.GetKeyValue("CYTKey");
//        }
//        v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("CYTU","0005"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            supportCYT = v5.getPmvb();
//        }else{
//            supportCYT = WebContant.GetKeyValue("supportCYT");
//        }
//        v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("CYTU","0006"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            supportJPCP = v5.getPmvb();
//        }else{
//            supportJPCP = WebContant.GetKeyValue("supportJPCP");
//        }
//    }
//    static{
//        IGenericService genericService = (IGenericService) SpringUtil.getBean("genericService");
//        Sysparv5 v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("HQYT","0005"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            HQYTKEY = v5.getPmvb();
//        }else{
//        	HQYTKEY = WebContant.GetKeyValue("CYTKey");
//        }
//        v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("HQYT","0005"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            supportCYT = v5.getPmvb();
//        }else{
//            supportCYT = WebContant.GetKeyValue("supportCYT");
//        }
//        v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("HQYT","0006"));
//        if(v5 != null && !StringUtils.isBlank(v5.getPmvb())){
//            supportJPCP = v5.getPmvb();
//        }else{
//            supportJPCP = WebContant.GetKeyValue("supportJPCP");
//        }
//    }
	/*-----------分销商ID-----------*/
    /**
     * 分销商ID：Qunar
     */
    public static final String CUSTOM_QUNAR_= "test";

    /*--------------BASE INFO Start--------------*/
    public static final String TEST_ALIVE_ = "ok";
    /**
     * 游客：一票一人
     */
    public static final String VISIT_SHARED_ = "1";
    /**
     * 支付银行
     */
    public static final String BANKCODE= "92";//银行预付款
    public static final String BANKCODE_13= "13";//银联
    /**
     * 支付方式:预支付
     */
    public static final String ZFFS = "08";
    /**
     * 预付款类类型：增加
     */
    public static final String ADVANCE_PAYMENTS_ADD = "1";
    /**
     * 预付款类类型：减少
     */
    public static final String ADVANCE_PAYMENTS_MINUS = "-1";

    /**
     * 订单状态：已支付
     */
    public static final String ORDER_STATUS_AREADY_PAYMENTS = "02";
    /**
     * 订单状态：待支付
     */
    public static final String ORDER_STATUS_WAIT_PAYMENTS = "00";
	
	
	/*--------------BASE INFO End--------------*/
    /**
     * 提前预定时间
     */
    public static final  String LAST_BOOK_DAY = "1";
    /**
     * 获取产品信息，短信模板
     */
    public static final  String SMS = "您将会在3-5 分钟内收到电子票，在景区售票处取票入园。";
	
	
	/*-------------------- Order status -----------------------*/
    /**
     * 预付：初始订单
     */
    public static final String PREPAY_ORDER_INIT = "PREPAY_ORDER_INIT";
    /**
     * 预付：预付失败
     */
    public static final String PREPAY_ORDER_BOOK_FAILED = "PREPAY_ORDER_BOOK_FAILED";
    /**
     * 预付成功：待支付
     */
    public static final String PREPAY_ORDER_NOT_PAYED = "PREPAY_ORDER_NOT_PAYED";
    /**
     * 预付：订单已取消
     */
    public static final String PREPAY_ORDER_CANCEL = "PREPAY_ORDER_CANCEL";
    /**
     * 预付：已付款，出票中
     */
    public static final String PREPAY_ORDER_PRINTING = "PREPAY_ORDER_PRINTING";
    /**
     * 现场支付
     */
    public static final String CASHPAY_ORDER_INIT = "CASHPAY_ORDER_INIT";
    /**
     * 预付：出票失败
     */
    public static final String PREPAY_ORDER_PRINT_FAILED = "PREPAY_ORDER_PRINT_FAILED";
    /**
     * 预付：出票成功
     */
    public static final String PREPAY_ORDER_PRINT_SUCCE = "PREPAY_ORDER_PRINT_SUCCE";

    //现付：初始订单
    //现付：出票中
    //现付：出票失败
    //现付：出票成功
    //现付：订单已取消
	
	/*------------------业务异常信息-----------------*/
	/*---- public ----*/
    /**
     * 成功状态码
     */
    public static final  String ERROR_CODE_SUCCESS = "1000";
    public static final  String ERROR_CODE_SUCCESS_DESC = "请求验证成功!";
    /**
     * 供应商接口出错
     */
    public static final String ERROR_CODE_99999 = "99999";
    public static final String ERROR_CODE_99999_DEFAULT_DESC = "供应商接口异常";
    /**
     * 分销商接口出错
     */
    public static final  String ERROR_CODE_99998 = "99998";

    /*---- Web Service ----*/
    public static final String ERROR_OTA_WEB_SERVICE = "OTA Web Service异常：";

    /*---- Action ----*/
    public static final String ERROR_QUNAR_ACTION = "QunarAction 异常 ：";
    public static final String ERROR_OTO_WS = "OTO WebService 异常：";
    public static final String ERROR_JSON_FORMAT = " json 格式化失败!";
    public static final String ERROR_VISA_VALIDATE_FALSE = " 签证验证不通过";
    public static final String ERROR_CODE_90001 = "90001";
    public static final String ERROR_REQUESTPARAM_DATA_BASE64_ERROR = " requestParam.data 以 base64 解析失败 ";

    public static final String ERROR_NOT_HAS_METHOD = "不支持该接口";

    public static final String ERROR_CODE_90002 = "90002";

    public static final String ERROR_CODE_90009 = "90009";

    public static final String ERROR_QUNAR_REQUEST_PARAM = " qunar request param ";
    public static final String ERROR_OTA_REQUEST_PARAM = " OTA request param ";
    public static final String ERROR_OTO_REQUEST_PARAM = " OTO request param ";
    public static final String ERROR_WEIXIN_REQUEST_PARAM = " 微信 请求参数异常： ";
    public static final String ERROR_WEIXIN_DOUBLE = " Double 类型转换异常";

    public static final String ERROR_OTA_REQUEST_PARAM_NULL = " [method] 为 null 或 [requestParam] 为 null";
    public static final String ERROR_JSON_PARAM_NULL = " json 参数  中有null值 ! ";

    /*---- Service ----*/
    //--HEAD
    public static final String ERROR_HEADER_GETPRODUCT = "OTA获取产品信息异常：";
    public static final String ERROR_HEADER_CREATE_ORDER = "创建订单异常：";
    public static final String ERROR_HEADER_PAY_ORDER = "支付订单异常：";
    public static final String ERROR_HEADER_GET_ORDER = "获取订单信息异常：";
    public static final String ERROR_HEADER_USER_REFUND = "用户申请退款信息异常：";
    public static final String ERROR_HEADER_REFUND_SEND = "OTA退款通知异常：";
    public static final String ERROR_HEADER_PUSH_ORDER = "同步订单异常：";
    public static final String ERROR_HEADER_SEND_MESSAGE = "重发入园凭证异常：";
    public static final String ERROR_HEADER_WEIXIN_QUERY_USER_JF = "查询会员积分信息：";
    public static final String ERROR_HEADER_WEIXIN_GETUSERJFSET = "获取积分规则：";
    public static final String ERROR_HEADER_WEIXIN_MODIFYUSERJFLIST = "会员积分明细同步：";
    public static final String ERROR_HEADER_WEIXIN_GETUSERYFKLIST = "查询会员余额：";
    public static final String ERROR_HEADER_WEIXIN_MODIFYVIPBALANCE = "会员充值：";

    //--Other.getProductByQunar
    public static final String ERROR_REQUEST_BODY_METHOD_NULL = " BODY 中 [method] 为空 ";
    public static final String ERROR_GETPRODUCTBY_00002 =  " 产品ID为空";
    public static final String ERROR_GETPRODUCTBY_00003 =  " 页码数据格式出错！";

    //-Other.createOrderForBeforePaySync
    public static final String ERROR_CREATEORDER_PRODUCT_PRICE_NOT_NUMBER = " 产品售卖单价非正常数字";
    public static final String ERROR_CREATEORDER_ORDER_PRICE_NOT_NUMBER = " 订单总价非正常数字";
    public static final String ERROR_CREATEORDER_MOBILE_NULL = "取票人电话号码为空";
    public static final String ERROR_CREATEORDER_CARD_TYPE_NULL = "证件类型为空";
    public static final String ERROR_CREATEORDER_CARD_TYPE_NOT_IDCARD = "证件类型非身份证";
    public static final String ERROR_CREATEORDER_CARD_NULL = "证件号码为空";
    public static final String ERROR_CREATEORDER_DATE_FORMAT = "将 产品 开始、结束日期 转换 String类型 异常";
    public static final String ERROR_CREATEORDER_SCENIC_SHORTAGE = "供应商无对应景区信息";
    public static final String ERROR_CREATEORDER_PIAO_OVER_TIME = "此票超过当天预订截止时间，不可预订";
    public static final String ERROR_CREATEORDER_ENTER_SCENIC_TIME = "入园时间不在可预订时间范围";
    public static final String ERROR_CREATEORDER_PARSE_LONG = "Long类型转换异常";
    public static final String ERROR_CREATEORDER_CAN_T_SALE = "或 此产品不属于此OTA与供应商卖";
    public static final String ERROR_CREATEORDER_OTA_PRICE = "订单价格数据出错，请检查OTA订单价格数据是否为 单价 与 票 数 的乘积 ";
    public static final String ERROR_PUSHORDER_YOUKE_CDCARD_INVALID = "身份证号码无效，请使用有效身份证";
    public static final String ERROR_DESC_REPEAT_ORDER = "该订单已经下单，不能重复下单";
    //-Other.payOrderForBeforePaySync
    public static final String ERROR_PAYORDER_ALREADY_PAY_ORDER = " 订单状态不为 - PREPAY_ORDER_PRINTING：已付款，出票中";
    public static final String ERROR_PAYORDER_ORDER_PRICE_NOT_NUMBER =  " 订单总价非正常数字";
    public static final String ERROR_PAYORDER_ORDER_PRICE_MISS_0 = " 订单总价  小于等于  0";
    public static final String ERROR_PAYORDER_ORDER_PRICE_NOT_MACH = "订单总价与数据库中不一致";
    public static final String ERROR_DESC_ORDER_MISS_TORDER_INFO = "缺少相关TOrder信息，订单ID：";
    public static final String ERROR_DESC_ORDER_NOT_PAY_OVER_2H = "此订单已经超过2小时未支付，无法支付超时订单。";
    public static final String ERROR_DESC_ORDER_CUSTOM_OVERAGE_MISS = "分销商余额不足以支付此订单";
    public static final String ERROR_DESC_ORDER_PAY_DB_WRONG = "数据库异常-用户付款不成功";
    public static final String ERROR_DESC_ORDER_UPDATE_ORDER_STATUS_OR_SAVE_MESSAGE = "修改订单状态为”已支付“出错  或  保存短信消息异常";

    //-Other.GetOrderByQunar
    public static final String ERROR_GETORDERBY_MISS_TORDERLIST_INFO = "订单主表有数据，相关TOrderlist订单表无信息";
    public static final String ERROR_GETORDERBY_REFUND_BUFEN_PIAO_NUMBER = "部分退订，退票数不可大于订单票数";
    public static final String ERROR_GETORDERBY_REFUND_BUFEN_NOT_NUM = " 部分退订票数非数字异常";
    public static final String ERROR_GETORDERBY_STATUS_NOT_MATCH = "数据库中订单状态异常，请联系开发人员";

    //-Other.applyOrderRefundByUser
    public static final String ERROR_APPLYORDERREFUNDBYUSER_NOT_PAY_OK = "退款失败，只有预付成功的订单允许退款";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_PRICE_NOT_MUCH = " 中 原始订单金额 与 订总金额 不一致";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_PIAO_NOT_MUCH = " 中 票数 与 订单中票数不一致";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_TORDERLIST = "数据库查询 TOrderlist订单异常";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_ALREADY_REFUND = "此退款处理中，或者已处理，请重新申请";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_ALREADY_REFUND1 = "此订单已经消费或退款，不能退订！";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_SAVE_DB = "退款存入数据库  保存异常";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_NUMBER_FORMAT = "退款存入数据库时  NumberFormatException 异常";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_SUCCESS = "退订通知成功";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_VISIT_NOT_COMPLETE = "游客信息不完整";
    public static final String ERROR_APPLYORDERREFUNDBYUSER_ORDER_ALREADY_USE = "该订单已经消费";

    //-Other.noticeOrderRefundedByQunar
    public static final String ERROR_NOTICEORDERREFUNDEDBYOTA_ORDER_NOT_EXIST = "该订单不存在 或 未进行退订申请";
    public static final String ERROR_NOTICEORDERREFUNDEDBYOTA_ORDER_NOT_REFUND = "该订单未进行退订审核阶段或审核为不可退，不能退款";
    public static final String ERROR_NOTICEORDERREFUNDEDBYOTA_ORDER_STATUS_NOT_PAY = "订单状态非待支付，不能修改或取消订单";


    //-Other.pushOrderForBeforePaySync
    public static final String ERROR_PUSHORDER_YOUKE_CDCARD_NULL = " 游客存在情况，证件类型必须为身份证，证件号码不能为空";
    public static final String ERROR_PUSHORDER_ORDER_STATUS = " 订单状态不正确，无法同步信息";
    public static final String ERROR_PUSHORDER_DB_PUSH = "供应商数据库同步异常";

    //-Other.sendOrderEticket
    public static final String ERROR_SENDORDERETICKET_PHONE_NOT_MATCH = "手机号码非其本人手机号";
    public static final String ERROR_SENDORDERETICKET_NOT_PAY = "订单未支付  或  已经退款(不发送短信)";

    //--Other.public
    public static final String ERROR_DESC_GYS_NOT_EXIT = "数据库供应商不存在";
    public static final String ERROR_DESC_PRODUCT_ID_NOT_NUMBER =  " 产品ID非数字类型";
    public static final String ERROR_DESC_ORDER_IS_NULL = " 订单号为空";
    public static final String ERROR_DESC_ORDER_ID_NOT_NUMBER = "订单ID非数字类型";
    public static final String ERROR_DESC_ORDER_GYS_CODE_NULL = "供应商CODE为空";
    public static final String ERROR_DESC_ORDER_NOT_BELONG_TO_CUSTOM_AND_GYS = "订单不属于对应供应商下此分销商";
    public static final String ERROR_DESC_ORDER_PIAO_NOT_ZHENGSHU = " 订单票非整数";
    public static final String ERROR_DESC_ORDER_MISS_0 = " 订单票  小于等于  0";
    public static final String ERROR_DESC_ORDER_PRICE_NOT_INT = " 订单金额非整数";
    public static final String ERROR_DESC_STOCK_NOT_FULL = "库存不足";
    public static final String ERROR_DESC_ORDER_ID_NULL = " 订单ID为空";
    public static final String ERROR_DESC_PHONE_NOT_NUMBER = " 手机号码非数字类型";
    public static final String ERROR_DESC_PHONE_NOT_NULL = " 手机号码不能为空";
	
	/*----Qunar CODE ----*/
    /**
     * 供应商标识不存在
     */
    public static final String ERROR_CODE_90006 = "90006";
    /**
     * 产品不存在
     */
    public static final String ERROR_CODE_12001  = "12001";
    /**
     * 创建订单异常，选购产品数量 小于等于 0
     */
    public static final String ERROR_CODE_20002  = "20002";
    /**
     * 创建订单异常，选购产品数量不是整数
     */
    public static final String ERROR_CODE_20003	 = "20003";
    /**
     * 创建订单异常，出行日期为空
     */
    public static final String ERROR_CODE_20010	 = "20010";
    /**
     * 创建订单异常，您选择的出行日期格式不合法
     */
    public static final String ERROR_CODE_20011	 = "20011";
    /**
     * 创建订单异常，游客信息为空
     */
    public static final String ERROR_CODE_20015	 = "20015";
    /**
     * 创建订单异常，选购产品不存在
     */
    public static final String ERROR_CODE_20004	 = "20004";
    /**
     * 创建订单异常，购买产品的数量和游客人数不符
     */
    public static final String ERROR_CODE_20014	 = "20014";
    /**
     * 创建订单异常，该产品已经下架
     */
    public static final String ERROR_CODE_20023	 = "20023";
    /**
     * 创建订单异常，您选择的产品价格已发生变化
     */
    public static final String ERROR_CODE_20025	 = "20025";
    /**
     * 创建订单异常，创建产品快照失败
     */
    public static final String ERROR_CODE_20029	 = "20029";
    /**
     * 创建订单异常，库存不足
     */
    public static final String ERROR_CODE_20022	 = "20022";
    /**
     * 订单不存在
     */
    public static final String ERROR_CODE_13001	 = "13001";
    /**
     * 数据库订单状态不为待支付，无法支付非待支付订单
     */
    public static final String ERROR_CODE_13009 = "13009";

    public static final String ERROR_CODE_13007 = "13007";

    public static final String ERROR_CODE_15008 = "15008";

    /**
     * 创建订单异常，入住日期为空
     */
    public static final String ERROR_CODE_20032	 = "20032";
    public static final String ERROR_CODE_20032__DESC	 = "创建酒店订单异常，入住日期为空";
    /**
     * 创建订单异常，离店日期为空
     */
    public static final String ERROR_CODE_20033	 = "20033";
    public static final String ERROR_CODE_20033__DESC	 = "创建酒店订单异常，离店日期为空";
    /**
     * 创建酒店订单异常，您选择的日期格式不合法
     */
    public static final String ERROR_CODE_20034	 = "20034";
    public static final String ERROR_CODE_20034__DESC	 = "创建酒店订单异常，您选择的日期格式不合法";

    /**
     * 产品id为空
     */
    public static final String ERROR_CODE_00002	 = "20035";
    /**
     * 产品价格id为空
     */
    public static final String ERROR_CODE_20036	 = "20036";
    public static final String ERROR_CODE_20036__DESC	 = "创建酒店订单异常，产品价格id为空!";
    /**
     * 微信会员
     */
    public static final String ERROR_CODE_30000 = "30000";
    public static final String ERROR_CODE_30001 = "30001";
    public static final String ERROR_CODE_30002 = "30002";
    public static final String ERROR_CODE_30003 = "30003";
    public static final String ERROR_CODE_30004 = "30004";
    public static final String ERROR_CODE_30005 = "30005";
    public static final String ERROR_CODE_30006 = "30006";
    public static final String ERROR_CODE_30007 = "30007";
    public static final String ERROR_CODE_30008 = "30008";
    public static final String ERROR_CODE_30009 = "30009";
	
	/*---- DESC ----*/
    /**
     * 12001 : 产品不存在
     */
    public static final String ERROR_CODE_12001_DESC  = "产品不存在";
    /**
     * 20002 : 创建订单异常，选购产品数量 小于等于 0
     */
    public static final String ERROR_CODE_20002_DESC  = "创建订单异常，选购产品数量 小于等于 0";
    /**
     * 20003 : 创建订单异常，选购产品数量不是整数
     */
    public static final String ERROR_CODE_20003_DESC	 = "创建订单异常，选购产品数量不是整数";
    /**
     * 20010 : 创建订单异常，出行日期为空
     */
    public static final String ERROR_CODE_20010_DESC	 = "创建订单异常，出行日期为空";
    /**
     * 20011 : 创建订单异常，您选择的出行日期格式不合法
     */
    public static final String ERROR_CODE_20011_DESC	 = "创建订单异常，您选择的出行日期格式不合法";
    /**
     * 20015 : 创建订单异常，游客信息为空
     */
    public static final String ERROR_CODE_20015_DESC	 = "创建订单异常，游客信息为空";
    /**
     * 20004 : 创建订单异常，选购产品不存在
     */
    public static final String ERROR_CODE_20004_DESC	 = "创建订单异常，选购产品不存在";
    /**
     * 20014 : 创建订单异常，购买产品的数量和游客人数不符
     */
    public static final String ERROR_CODE_20014_DESC	 = "创建订单异常，购买产品的数量和游客人数不符";
    /**
     * 20023 : 创建订单异常，该产品已经下架
     */
    public static final String ERROR_CODE_20023_DESC	 = "创建订单异常，该产品已经下架";
    /**
     * 20025 : 创建订单异常，您选择的产品价格已发生变化
     */
    public static final String ERROR_CODE_20025_DESC	 = "创建订单异常，您选择的产品价格已发生变化";
    /**
     * 20029 : 创建订单异常，创建产品快照失败
     */
    public static final String ERROR_CODE_20029_DESC	 = "创建订单异常，创建产品快照失败";
    /**
     * 20022 : 创建订单异常，库存不足
     */
    public static final String ERROR_CODE_20022_DESC	 = "创建订单异常，库存不足";
    /**
     * 13001 : 订单不存在
     */
    public static final String ERROR_CODE_13001_DESC	 = "订单不存在";
    /**
     * 90006 : 供应商标识不存在
     */
    public static final String ERROR_CODE_90006_DESC = " 供应商标识不存在";
    /**
     * 13009 : 数据库订单状态不为待支付，无法支付非待支付订单
     */
    public static final String ERROR_CODE_13009_DESC = "数据库订单状态不为待支付，无法支付非待支付订单";

    /**
     * 微信会员
     */
    public static final String ERROR_CODE_30000_DESC = "此会员已被注册";

    public static final String ERROR_CODE_30001_DESC = "会员用户名或密码不能为空";

    public static final String ERROR_CODE_30002_DESC = "手机号码格式不正确";

    public static final String ERROR_CODE_30003_DESC = "邮箱格式不正确";

    public static final String ERROR_CODE_30004_DESC = "身份证号不能为空";

    public static final String ERROR_CODE_30005_DESC = "身份证格式不正确";

    public static final String ERROR_CODE_30006_DESC = "验证通过,会员注册信息保存失败";

    public static final String ERROR_CODE_30007_DESC = "此ID会员不存在";

    public static final String ERROR_CODE_30008_DESC = "密码不正确";

    public static final String ERROR_CODE_30009_DESC = "数据库修改会员发生错误";

    public static final String ERROR_WEIXIN_CUSTOM_ID = "用户ID：";
    public static final String ERROR_WEIXIN_PASS_NOT_MATCH = "已经存在，密码与存在的密码不一致，若确定其为您申请过的用户，请在申请用户时，输入其登录的正确密码";

    public static final String ERROR_WEIXIN_UPDATE_JF = "供应商异常，积分信息同步失败！";

    /*-----------------------  Client  ----------------------*/
    public static final String ERROR_CLIENT_REFUND_GYS_NOT_EXIT = "用户退订处理，通知去OTA异常： 供应商不存在";
    /**
     * 二维码KEY
     */
    public static final String TWOBARCODE="ectrip";
	
	
	/*----------- 微信 -------------*/

    public static final String ERROR_WEIXIN_GETORDERLIST_DATE_FORMAT = "开始时间或者结束时间日期格式不正确，参考格式：yyyy-MM-dd";
    public static final String ERROR_WEIXIN_GETORDERLIST_DATE_LOGIC = "查询开始时间不能小于结束时间，请检查";
    public static final String ERROR_WEIXIN_GETORDERLIST_USER_NOT_EXIT = "会员不存在";
    public static final String ERROR_WEIXIN_GETORDERLIST_YFK_NOT_EXIT = "用户预付款信息不存在";
	
	
	
	
	
	
	
	
	/*------------------------------------------ 内部类 ------------------------------------------*/

    /**
     *  OTA MD5 KEY
     */
//	public class Md5Key{
//		/**
//		 * 去哪儿  MD5 KEY
//		 */
//		public static final String QUNAR = "DINGYOU";
//		/**
//		 * OTO MD5 KEY
//		 */
////		public static final String OTO = "OTOECTRIP";
//		/**
//		 * 通用方法 MD5 KEY (测试用)
//		 */
//		public static final String TONGYONG = "TEST";
//		
//	}



    public class Qunar{
        /**
         * 应用标识
         */
        public static final String HEADER_APPLICATION = "Qunar.Menpiao.Agent";
        /**
         * 处理程序
         */
        public static final String HEADER_PROCESSOR = "SupplierDataExchangeProcessor";
        /**
         * 版本号
         */
        public static final String HEADER_VERSION = "CYW_TEST 2.0";
        /**
         * 创建用户（畅游网）
         */
        public static final String HEADER_CREATE_USER = "ChangYouTong";//畅游通 : changyoutong

        /**
         * qunar 头信息
         * @author huhaopeng
         */
        public class Header{
            public static final String NAMESPACE_REQUEST = "http://piao.qunar.com/2013/QMenpiaoRequestSchema";
            public static final String NAMESPACE_RESPONSE = "http://piao.qunar.com/2013/QMenpiaoResponseSchema";
            public static final String ATTRIBUTE_XSI = "http://piao.qunar.com/2013/QMenpiaoRequestSchema QMRequestDataSchema-2.0.0.xsd";
            public static final String ATTRIBUTE_XMLNS = "http://www.w3.org/2001/XMLSchema-instance";
        }

    }
}

