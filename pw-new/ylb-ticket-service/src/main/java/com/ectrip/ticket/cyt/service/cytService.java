package com.ectrip.ticket.cyt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ticket.cyt.common.util.Base64;
import com.ectrip.ticket.cyt.common.util.ConstUtils;
import com.ectrip.ticket.cyt.common.util.EncryptUtil;
import com.ectrip.ticket.cyt.common.util.XMLParseUtil;
import com.ectrip.ticket.cyt.model.request.CancelOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.CheckPmsDataRequestBody;
import com.ectrip.ticket.cyt.model.request.CreateComplexOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.CreateOTOOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.PayOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.PushOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.QueryOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.RequestHeader;
import com.ectrip.ticket.cyt.model.response.CancelOrderResponseBody;
import com.ectrip.ticket.cyt.model.response.CheckPmsDataResponseBody;
import com.ectrip.ticket.cyt.model.response.CreateComplexOrderResponseBody;
import com.ectrip.ticket.cyt.model.response.CreateOTOOrderResponseBody;
import com.ectrip.ticket.cyt.model.response.PayOrderResponseBody;
import com.ectrip.ticket.cyt.model.response.PushOrderResponseBody;
import com.ectrip.ticket.cyt.model.response.QueryOrderResponseBody;
import com.ectrip.ticket.cyt.model.response.ResponseBody;
import com.ectrip.ticket.cyt.model.response.ResponseHeader;
import com.ectrip.ticket.cyt.service.iservice.ICytOrderService;
import com.ectrip.ticket.cyt.service.iservice.ICytService;
import com.ectrip.ticket.feign.service.EcService;

/**
 * OTO 业务类
 * @author huhaopeng
 */
@Service
public class cytService implements ICytService {
	@Autowired
	private ICytOrderService cytOrderService;
	@Autowired
	private EcService ecSevice;
	
	@Autowired
	private ConstUtils constUtils;
	/*private IOrderService orderService;
	private IPayOrderService payOrderService;*/ // 支付服务类:此处我们在支付订单使用其修改状态方法

    public String checkPmsData(String xml, String securityType){
        Map map = null;
        try{
            map = XMLParseUtil.xmlToBean(xml, new RequestHeader(),
                    new CheckPmsDataRequestBody());
        }catch (Throwable e3){
            e3.printStackTrace();
            return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
                    ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
        }
        RequestHeader rqsHeader = (RequestHeader) map.get("header");
        CheckPmsDataRequestBody body = (CheckPmsDataRequestBody) map.get("body");

        CheckPmsDataResponseBody cResponse = new CheckPmsDataResponseBody();

        try{
            String message = cytOrderService.checkPmsData(body);
            if(!StringUtils.isBlank(message)){
                cResponse.setMessage(message);
                // 返回一个封装好的Json字符串
                return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_99999, message,cResponse);
            }else{
                cResponse.setMessage("校验成功");
            }
        }catch (Throwable e){
            e.printStackTrace();
            // 22. 20029=创建订单异常，创建产品快照失败
            return getErrorResponseJson(ConstUtils.ERROR_CODE_20029, e.getMessage(), securityType);
        }

        // 返回一个封装好的Json字符串
        return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_SUCCESS, ConstUtils.ERROR_CODE_SUCCESS_DESC,
                cResponse);
    }

	public String createComplexOrder(String xml, String securityType){

		// --------------xml string转换成 schema 的数据对象---------------

		Map map = null;
		try {
			map = XMLParseUtil.xmlToBean(xml, new RequestHeader(),
                    new CreateComplexOrderRequestBody());
		} catch (Throwable e3) {
			e3.printStackTrace();
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
					ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
		}
		RequestHeader rqsHeader = (RequestHeader) map.get("header");
		CreateComplexOrderRequestBody body = (CreateComplexOrderRequestBody) map
				.get("body");

		// --------------------业务--------------------
		CreateComplexOrderResponseBody cResponse = new CreateComplexOrderResponseBody();
		// 校验订单信息异常！ false：出错-返回错误码 / true： 成功
		// 校验成功！
		// 根据分销商ID获取分销商信息
//		Custom custom = customDao.getCustionById(rqsHeader.getCreateUser());// qunar分销商ID
		String orid = "";
		try {
			//增加订单： 返回一个生成的订单号
			String token = body.getOrderInfo().getToken();
			orid = cytOrderService.createComplexOrder(body, rqsHeader.getCreateUser(),(token==null || "".equals(token))?"999":"888");
			// 系统生成供应商ID
			cResponse.getOrderInfo().setPartnerorderId(orid);
			// 订单状态码： 初始状态
			cResponse.getOrderInfo().setOrderStatus(ConstUtils.PREPAY_ORDER_NOT_PAYED);
			
			String zffs = null;
			String ddzt = null;
			if(ConstUtils.PREPAY_ORDER_PRINTING.equals(body.getOrderInfo().getOrderStatus())){
				//创建订单并支付
				zffs = "08";//改为OTA预付款支付
				ddzt = "02";//已经支付
			}else if(ConstUtils.CASHPAY_ORDER_INIT.equals(body.getOrderInfo().getOrderStatus())){
				//改为OTA景区现付
				zffs = "09";
				ddzt = "00";//状态为未支付
			}
			if(zffs != null){//当时已支付的状态是要改订单状态
				String payid = "";// 支付订单号
				// 2. 修改订单状态为已支付 ：最终版本为调用专门方法来修改状态
//				int flag = payOrderService.updateOrderStatus(orid, payid,
//						new Double(body.getOrderInfo().getOrderPrice())/100 + "", ConstUtils.BANKCODE, 0,
//						ConstUtils.ORDER_STATUS_AREADY_PAYMENTS, null,
//						ConstUtils.ZFFS, "", rqsHeader.getCreateUser());
				int flag = ecSevice.updateOrderStatus(orid, payid,
						new Double(body.getOrderInfo().getOrderPrice())/100 + "", ConstUtils.BANKCODE, 0,
						ddzt, null,
						zffs, "", rqsHeader.getCreateUser());
				if(flag != 1){
					return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_HEADER_PAY_ORDER
							+ ConstUtils.ERROR_DESC_ORDER_PAY_DB_WRONG, securityType);
				}else{
					cResponse.getOrderInfo().setOrderStatus("09".equals(zffs)? ConstUtils.PREPAY_ORDER_NOT_PAYED:"PREPAY_ORDER_PRINT_SUCCESS");
					return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_SUCCESS, ConstUtils.ERROR_CODE_SUCCESS_DESC,
							cResponse);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
//			throw new RuntimeException(e);
			// 22. 20029=创建订单异常，创建产品快照失败
			return getErrorResponseJson(ConstUtils.ERROR_CODE_20029, e.getMessage(), securityType);
		}

		// 返回一个封装好的Json字符串
		return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_SUCCESS, ConstUtils.ERROR_CODE_SUCCESS_DESC,
				cResponse);
	
	}
	
	public String createOrder(String xml, String securityType) {
		// --------------xml string转换成 schema 的数据对象---------------

		Map map = null;
		try {
			map = XMLParseUtil.xmlToBean(xml, new RequestHeader(),
                    new CreateOTOOrderRequestBody());
		} catch (Throwable e3) {
			e3.printStackTrace();
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
					ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
		}
		RequestHeader rqsHeader = (RequestHeader) map.get("header");
		CreateOTOOrderRequestBody body = (CreateOTOOrderRequestBody) map
				.get("body");

		// --------------------业务--------------------
		CreateOTOOrderResponseBody cResponse = new CreateOTOOrderResponseBody();
		// 校验订单信息异常！ false：出错-返回错误码 / true： 成功
		// 校验成功！
		// 根据分销商ID获取分销商信息
//		Custom custom = customDao.getCustionById(rqsHeader.getCreateUser());// qunar分销商ID
		String orid = "";
		try {
			//增加订单： 返回一个生成的订单号
			String token = body.getOrderInfo().getToken();
			orid = cytOrderService.addOrder(body, rqsHeader.getCreateUser(),(token==null || "".equals(token))?"999":"888");
			// 系统生成供应商ID
			cResponse.getOrderInfo().setPartnerorderId(orid);
			// 订单状态码： 初始状态
			cResponse.getOrderInfo().setOrderStatus(ConstUtils.PREPAY_ORDER_NOT_PAYED);
			
			String zffs = null;
			String ddzt = null;
			if(ConstUtils.PREPAY_ORDER_PRINTING.equals(body.getOrderInfo().getOrderStatus())){
				//创建订单并支付
				zffs = "08";//改为OTA预付款支付
				ddzt = "02";//已经支付
			}else if(ConstUtils.CASHPAY_ORDER_INIT.equals(body.getOrderInfo().getOrderStatus())){
				//改为OTA景区现付
				zffs = "09";
				ddzt = "00";//状态为未支付
			}
			if(zffs != null){//当时已支付的状态是要改订单状态
				String payid = "";// 支付订单号
				// 2. 修改订单状态为已支付 ：最终版本为调用专门方法来修改状态
//				int flag = payOrderService.updateOrderStatus(orid, payid,
//						new Double(body.getOrderInfo().getOrderPrice())/100 + "", ConstUtils.BANKCODE, 0,
//						ConstUtils.ORDER_STATUS_AREADY_PAYMENTS, null,
//						ConstUtils.ZFFS, "", rqsHeader.getCreateUser());
				int flag = ecSevice.updateOrderStatus(orid, payid,
						new Double(body.getOrderInfo().getOrderPrice())/100 + "", ConstUtils.BANKCODE, 0,
						ddzt, null,
						zffs, "", rqsHeader.getCreateUser());
				if(flag != 1){
					return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_HEADER_PAY_ORDER
							+ ConstUtils.ERROR_DESC_ORDER_PAY_DB_WRONG, securityType);
				}else{
					cResponse.getOrderInfo().setOrderStatus("09".equals(zffs)? ConstUtils.PREPAY_ORDER_NOT_PAYED:"PREPAY_ORDER_PRINT_SUCCESS");
					return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_SUCCESS, ConstUtils.ERROR_CODE_SUCCESS_DESC,
							cResponse);
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
//			throw new RuntimeException(e);
			// 22. 20029=创建订单异常，创建产品快照失败
			return getErrorResponseJson(ConstUtils.ERROR_CODE_20029, e.getMessage(), securityType);
		}

		// 返回一个封装好的Json字符串
		return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_SUCCESS, ConstUtils.ERROR_CODE_SUCCESS_DESC,
				cResponse);
	}

	@SuppressWarnings( { "unchecked", "static-access" })
	public String payOrder(String xml, String securityType) {
		// --------------xml string转换成 schema 的数据对象---------------

		Map map = null;
		try {
			map = XMLParseUtil.xmlToBean(xml, new RequestHeader(),
                    new PayOrderRequestBody());
		} catch (Throwable e1) {
			e1.printStackTrace();
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
					ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
		}

		RequestHeader rqsHeader = (RequestHeader) map.get("header");
		PayOrderRequestBody body = (PayOrderRequestBody) map
				.get("body");

		// --------------------业务--------------------
		// -------------支付校验通过：--------------
		Double orderPrice = new Double(body.getOrderInfo().getOrderPrice()) / 100;
		try {
			String orid = body.getOrderInfo().getPartnerOrderId();
			System.out.println("from TOrder t where t.id.orid = ?");
			List<TOrder> ts = cytOrderService.find("from TOrder t where t.id.orid = ?", new String[]{orid});
			if (ts.size() == 0) {
				// TOrder中缺少应有数据，保存订单业务异常或其他异常导致
				return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
			}
			String payid = "";// 支付订单号
			// 2. 修改订单状态为已支付 ：最终版本为调用专门方法来修改状态
			int flag = ecSevice.updateOrderStatus(orid, payid,
					orderPrice + "", ConstUtils.BANKCODE, 0,
					ConstUtils.ORDER_STATUS_AREADY_PAYMENTS, null,
					ConstUtils.ZFFS, "", rqsHeader.getCreateUser());
			if(flag != 1){
				return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_HEADER_PAY_ORDER
						+ ConstUtils.ERROR_DESC_ORDER_PAY_DB_WRONG, securityType);
			}
		} catch (Throwable e) {
			e.printStackTrace();
            if(!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")){
                return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, e.getMessage(), securityType);
            }else{
                return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_HEADER_PAY_ORDER
                        + ConstUtils.ERROR_DESC_ORDER_UPDATE_ORDER_STATUS_OR_SAVE_MESSAGE, securityType);
            }
		}
		// TODO 3. 同步供应商

		// ----------------Body数据--------------
		PayOrderResponseBody payResponse = new PayOrderResponseBody();

		payResponse.getOrderInfo().setPartnerorderId(body.getOrderInfo().getPartnerOrderId());// body.getOrderInfo().getPartnerOrderId()
		payResponse.getOrderInfo().setOrderStatus("PREPAY_ORDER_PRINT_SUCCESS");// body.getOrderInfo().getOrderStatus()
		
		// 返回一个封装好的Json字符串
		return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_SUCCESS, ConstUtils.ERROR_CODE_SUCCESS_DESC,
				payResponse);
	}

	@SuppressWarnings("unchecked")
	public String queryOrder(String xml, String securityType) {

		// --------------xml string转换成 schema 的数据对象---------------

		Map map = null;
		try {
			map = XMLParseUtil.xmlToBean(xml, new RequestHeader(), new QueryOrderRequestBody());
		} catch (Throwable e) {
			e.printStackTrace();
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
					ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
		}

		RequestHeader rqsHeader = (RequestHeader) map.get("header");
		QueryOrderRequestBody body = (QueryOrderRequestBody) map
				.get("body");

		// --------------------业务(模拟数据)--------------------
		MOrder order = (MOrder) cytOrderService.get(MOrder.class, body.getPartnerOrderId());
//		MOrder order = mOrderDao.getMOrder(body.getPartnerOrderId());
		QueryOrderResponseBody gResponse = new QueryOrderResponseBody();
		// 需要进入Dao层访问数据库得到信息(暂无数据库)
		String orid =body.getPartnerOrderId();
		List<TOrderlist> toList = cytOrderService.find("from TOrderlist t where t.id.orid=?",new String[]{orid});
		if (null != toList && toList.size() > 0) {
			TOrderlist to = toList.get(0);
			gResponse.getOrderInfo()
					.setPartnerorderId(body.getPartnerOrderId());// body.getPartnerOrderId()
			// 00 未付款 02已支付 11已出票 （不用： 06 退订已退款）
			if ("00".equals(order.getDdzt())) {
				gResponse.getOrderInfo().setOrderStatus(
						"PREPAY_ORDER_NOT_PAYED");//：预订成功，待支付
				gResponse.getOrderInfo().setOrderQuantity(
						to.getNumb().toString());
																	// /
				// FALSE
				gResponse.getOrderInfo().setUseQuantity("0");// 已消费票数

			} else if ("02".equals(order.getDdzt())) {
				gResponse.getOrderInfo().setOrderStatus(
						"PREPAY_ORDER_PRINTING");// 已付款
				gResponse.getOrderInfo().setOrderQuantity(
						to.getNumb().toString());
				// FALSE
				gResponse.getOrderInfo()
						.setUseQuantity("0");// 已消费票数

			} else if ("98".equals(order.getDdzt())
					|| "06".equals(order.getDdzt()) || "27".equals(order.getDdzt())) {// 98. 订单已取消 06. 订单已退款   27全退
				gResponse.getOrderInfo().setOrderStatus(
						"PREPAY_ORDER_REFUNDED");// 订单已退订
				gResponse.getOrderInfo().setOrderQuantity(
						to.getNumb().toString());
																	// /
				// FALSE
				gResponse.getOrderInfo().setUseQuantity("0");// 已消费票数

			} else if("11".equals(order.getDdzt())){
				gResponse.getOrderInfo().setOrderStatus(
				"PREPAY_ORDER_CONSUMED");// 订单已消费
	         	gResponse.getOrderInfo().setOrderQuantity(
				to.getNumb().toString());
	         	
	         	gResponse.getOrderInfo().setUseQuantity(
						to.getNumb().toString());
			} else {// 数据库异常，订单状态异常
				return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_GETORDERBY_STATUS_NOT_MATCH, securityType);
			}

		} else {
			// 查询YOrderlist 时出现异常 主订单表中有信息，但是此订单表中未找到信息
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_HEADER_GET_ORDER
					+ ConstUtils.ERROR_GETORDERBY_MISS_TORDERLIST_INFO, securityType);
		}

		// 返回一个封装好的Json字符串
		return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_SUCCESS, ConstUtils.ERROR_CODE_SUCCESS_DESC,
				gResponse);
	}

	@SuppressWarnings("unchecked")
	public String applyOrderRefundByUser(String xml, String securityType) {
		// --------------xml string转换成 schema 的数据对象---------------

		Map map = null;
		try {
			map = XMLParseUtil.xmlToBean(xml, new RequestHeader(),
                    new CancelOrderRequestBody());
		} catch (Throwable e2) {
			e2.printStackTrace();
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
					ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
		}

		RequestHeader rqsHeader = (RequestHeader) map.get("header");
		CancelOrderRequestBody body = (CancelOrderRequestBody) map
				.get("body");

		// --------------------业务(模拟数据)--------------------
		// -------------申请退款成功： 将数据加入 用户退款表中！--------------
		//Galcompanyinfotab resultCompany = ticketProductDao
		//.getCompanyByCode(rqsHeader.getSupplierIdentity());
		// 退款信息保存
		boolean resultCode = false;
		try {
			double tdsx = Double.parseDouble(body.getOrderInfo().getOrderRefundCharge())/100;
			String orid = body.getOrderInfo().getPartnerorderId();
			int refundquantity =Integer.parseInt(body.getOrderInfo().getRefundQuantity());
			// ----------------退订---------------
			resultCode = cytOrderService.refund(orid, refundquantity,tdsx);

			// 退款信息保存成功失败信息
			if (resultCode == false) {
				return getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_HEADER_USER_REFUND
						+ ConstUtils.ERROR_APPLYORDERREFUNDBYUSER_SAVE_DB, securityType);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
//			getErrorResponseJson(ConstUtils.ERROR_CODE_99999, ConstUtils.ERROR_HEADER_USER_REFUND
//					+ ConstUtils.ERROR_APPLYORDERREFUNDBYUSER_NUMBER_FORMAT, securityType);
		}

		// 需要进入Dao层访问数据库得到信息(暂无数据库)
		CancelOrderResponseBody appResponse = new CancelOrderResponseBody();

		appResponse.setMessage(resultCode+"");

		// 返回一个封装好的Json字符串
		return getResponseJson(securityType, rqsHeader, ConstUtils.ERROR_CODE_SUCCESS, ConstUtils.ERROR_CODE_SUCCESS_DESC,
				appResponse);
	}

	/**
	 * 当出现验证异常，调用此方法，返回异常信息 Describe: 当前仅支持 MD5
	 * 
	 * @author:huhaopeng
	 * @param code
	 * @param desc
	 * @return return:String Date:2014-4-2
	 */
	public String getErrorResponseJson(String code, String desc,
			String securityType) {


		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setCode(code);
		responseHeader.setDescribe(desc);
		ResponseBody body = new ResponseBody();
		String dataXml = null;
		try {
			dataXml = XMLParseUtil.beanToXML(body, responseHeader, "ectrip");
		} catch (Throwable e1) {
			System.out
					.println("Qunar Service ： getErrorResponseJson : bean to xml 异常");
			e1.printStackTrace();
		}

		// ----------------------- Base64编译 ----------------------
//		System.out.println("返回异常信息XML:" + dataXml);
		byte[] responseByte = null;
		try {
			responseByte = dataXml.getBytes("utf-8");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out
					.println("Qunar Service ： getErrorResponseJson : String to xml UTF-8 Error !");
		}
		String responseData = Base64.encode(responseByte);
//		System.out.println("Base64编译后的response XML：" + responseData);

		//TODO 0 ---------------返回值(AES和MD5):MD5加密-----------------
		String responseSigned = EncryptUtil.MD5Hex(
                constUtils.getKEY() + responseData).toUpperCase();

		// ---------------返回结果转换成 拼接成 JSONString 类型-------------
		String responseJSON = "";
		if ("MD5".equals(securityType)) {
			/*JSONStringer stringer = new JSONStringer();
			responseJSON = stringer.object().key("data").value(responseData)
					.key("signed").value(responseSigned).key("securityType")
					.value(securityType).endObject().toString();*/
			Map<String,String> data = new HashMap<String,String>();
			data.put("data", responseData);
			data.put("signed", responseSigned);
			data.put("securityType", securityType);
			responseJSON = JSON.toJSONString(data);
		} else if ("AES".equals(securityType)) {
			// AES方式加密
		}
		return responseJSON;
	}

	/**
	 * 取地级市 Describe:
	 * 
	 * @author:huhaopeng
	 * @param szmemo
	 *            地域全称
	 * @return return:String 如果为 null 说明此地址情况异常,请返回供应商数据库 客源地 异常信息
	 */
	public String getPrefectureLevelCity(String szmemo) {
		String address = null;
		/*
		 * 景区地址：product.getSzmemo() 直辖市情况（北京、上海、天津、重庆）取第二级
		 */
		String[] products = szmemo.split(",");
		if (products.length > 2) {
			if (products[1].equals("北京") || products[1].equals("上海")
					|| products[1].equals("天津") || products[1].equals("重庆")) {
				address = products[1];
			} else {// 如果非地级市，则说明此为3级以上城市市
				address = products[2];
			}
		}

		return address;
	}
	
	/**
	 * 获取封装好的JSON字符串 Describe:
	 * 
	 * @author:huhaopeng
	 * @param securityType
	 * @param xpu
	 * @param rqsHeader
	 * @param errorCode
	 * @param errorDesc
	 * @param cResponse
	 * @return return:String Date:2014-3-31
	 * @throws Exception
	 */
	private String getResponseJson(String securityType,
			RequestHeader rqsHeader, String errorCode, String errorDesc,
			ResponseBody cResponse) {

		ResponseHeader rspHeader = new ResponseHeader(rqsHeader
				.getApplication(), rqsHeader.getProcessor(), rqsHeader
				.getVersion(), rqsHeader.getBodyType(), rqsHeader
				.getCreateUser(), rqsHeader.getCreateTime(), errorCode,
				errorDesc);

		// ----------------将 xml bean 对象转换成 String(xml:respXml)-----------------
		String respXml = null;
		try {
			respXml = XMLParseUtil.beanToXML(cResponse, rspHeader, "ectrip");
		} catch (Throwable e1) {
			e1.printStackTrace();
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
					ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
		}
//		System.out.println("response XML: \n" + respXml);

		// ----------------------- Base64编译 ----------------------
//		System.out.println("返回XML:" + respXml);
		byte[] responseByte = null;
		try {
			responseByte = respXml.getBytes("utf-8");
		} catch (Throwable e) {
			e.printStackTrace();
			System.out
					.println("QunarService 158 line : String to xml UTF-8 Error !");
		}
		String responseData = Base64.encode(responseByte);
//		System.out.println("Base64编译后的response XML：" + responseData);

		//TODO 1 ---------------返回值(AES和MD5):MD5加密-----------------
		String responseSigned = EncryptUtil.MD5Hex(
                constUtils.getKEY() + responseData).toUpperCase();

		// ---------------返回结果转换成 拼接成 JSONString 类型-------------
		String responseJSON = "";
		if ("MD5".equals(securityType)) {

			/*JSONStringer stringer = new JSONStringer();
			responseJSON = stringer.object().key("data").value(responseData)
					.key("signed").value(responseSigned).key("securityType")
					.value(securityType).endObject().toString();*/
			Map<String,String> data = new HashMap<String,String>();
			data.put("data", responseData);
			data.put("signed", responseSigned);
			data.put("securityType", securityType);
			responseJSON = JSON.toJSONString(data);
		} else if ("AES".equals(securityType)) {
			// AES方式加密
		}
		return responseJSON;
	}
	public String pushOrder(String xml, String securityType) {
		// --------------xml string转换成 schema 的数据对象---------------
//		System.out.println("进入 OTA Service 的 xml: \n" + xml);

		Map map = null;
		try {
			map = XMLParseUtil.xmlToBean(xml, new RequestHeader(),
                    new PushOrderRequestBody());
		} catch (Throwable e1) {
			e1.printStackTrace();
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
					ConstUtils.ERROR_CODE_99999_DEFAULT_DESC, securityType);
		}
		RequestHeader rqsHeader = (RequestHeader) map.get("header");
		PushOrderRequestBody body = (PushOrderRequestBody) map.get("body");
		// --------------------业务--------------------
		
		/*-------------------- 验证成功 --------------------*/
		// 修改订单
        boolean isOK = false;
        try {
            isOK = cytOrderService.synchronOrder(body);
        }catch (Exception e){
            if(!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")){
                return getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
                        e.getMessage(), securityType);
            }else{
                return getErrorResponseJson(ConstUtils.ERROR_CODE_99998,
                        ConstUtils.ERROR_HEADER_PUSH_ORDER
                                + ConstUtils.ERROR_PUSHORDER_DB_PUSH, securityType);
            }
        }
		if (isOK == false) {
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99998,
					ConstUtils.ERROR_HEADER_PUSH_ORDER
							+ ConstUtils.ERROR_PUSHORDER_DB_PUSH, securityType);
		}
		Long orderNum = null;
		try {
			orderNum = Long.parseLong(body.getOrderInfo()
					.getPartnerOrderId());
		} catch (Throwable e) {
			e.printStackTrace();
			return getErrorResponseJson(ConstUtils.ERROR_CODE_99998,
					ConstUtils.ERROR_HEADER_PAY_ORDER
							+ ConstUtils.ERROR_DESC_ORDER_ID_NOT_NUMBER,
					securityType);

		}
		// 构建BODY
		PushOrderResponseBody pResponse = new PushOrderResponseBody();
		// 返回的订单号
		pResponse.getOrderInfo().setPartnerorderId(orderNum.toString());

		// 返回一个封装好的Json字符串
		return getResponseJson(securityType, rqsHeader,
				ConstUtils.ERROR_CODE_SUCCESS,
				ConstUtils.ERROR_CODE_SUCCESS_DESC, pResponse);
	}

}

