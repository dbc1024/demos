package com.ectrip.ticket.cyt.webservice;

import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.ticket.cyt.common.util.Base64;
import com.ectrip.ticket.cyt.common.util.ConstUtils;
//import com.ectrip.cyt.common.util.Base64;
//import com.ectrip.cyt.common.util.ConstUtils;
//import com.ectrip.cyt.common.util.EncryptUtil;
import com.ectrip.ticket.cyt.service.iservice.ICytService;
import com.ectrip.ticket.cyt.webservice.iwebservice.ICYTDataService;
import com.ectrip.ticket.service.util.EncryptUtil;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OTO 接口
 * 
 * @author huhaopeng
 */
@Component
public class CYTDataService implements ICYTDataService {

	@Autowired
	private ICytService cytService;
	
	@Autowired
	private static ConstUtils constUtils;

	/**
	 * 接口入口方法 Describe:
	 * 
	 * @param method
	 * @param requestParam
	 * @return
	 * @throws Exception
	 * @author huhaopeng Date:2014-4-17
	 */
	@SuppressWarnings("static-access")
	public String doCYTRequest(String method, String requestParam)
			throws Exception {

		this.setCytService(cytService);

		System.out
				.println("---------------------------------------------------");
		System.out.println("进入OTOAction： ");
		System.out.println("请求参数   method:" + method);
		System.out.println("请求参数   requestParam:" + requestParam);
		System.out
				.println("---------------------------------------------------");

		/*----------------- OTO心跳测试 : 根据不同OTA更新心跳时间-----------------*/
        if ("testAlive".equals(method)) {
            return "alive";
        }

		String responseXml = getResponseJson(method, requestParam);

		return responseXml;
	}

	private String getResponseJson(String method, String requestParam) {
		String responseXml = null;

		if (null == method || null == requestParam || "" == method
				|| "" == requestParam) {
			return cytService
					.getErrorResponseJson(
							"99998",
							ConstUtils.ERROR_OTO_WS
									+ ConstUtils.ERROR_OTO_WS
									+ " [method] 为 null 或 [requestParam] 为 null",
							"MD5");// 当请求参数缺少时，返回错误信息
		}

		System.out
				.println("-----------------------  开始转换JSON  -------------------------");
		String data = null;
		String signed = null;
		String securityType = null;
		try {
			String jsonArr=requestParam;  
//	        jsonObj  = JSONObject.fromObject(jsonStr);  
			JSONObject jsonObject = JSONObject.parseObject(jsonArr);
			System.out
					.println("-----------------------  转换JSON成功，开始获取data，signed，securityType---------------------");
			data = jsonObject.get("data").toString();
//			System.out.println("data:" + data);

			signed = jsonObject.get("signed").toString();
//			System.out.println("signed:" + signed);

			securityType = jsonObject.get("securityType").toString();
//			System.out.println("securityType:" + securityType);

		} catch (Throwable e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace(System.out);
			return cytService.getErrorResponseJson(ConstUtils.ERROR_CODE_90001,
					ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_REQUEST_PARAM
							+ ConstUtils.ERROR_JSON_FORMAT, securityType);

		}
		System.out
				.println("--------------------  判断body中加密类型，加密后信息，MD5字符串是否缺失  --------------------");
		if (null == securityType || "".equals(securityType)) {
			// this.addActionMessage("错误信息：缺失加密类型！");
			// return ERROR;
			return cytService.getErrorResponseJson(ConstUtils.ERROR_CODE_90001,
					ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_REQUEST_PARAM
							+ ConstUtils.ERROR_JSON_PARAM_NULL, securityType);

		} else if (null == signed || "".equals(signed)) {
			// this.addActionMessage("错误信息：分销商缺失加密后信息");
			// return ERROR;
			return cytService.getErrorResponseJson(ConstUtils.ERROR_CODE_90001,
					ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_REQUEST_PARAM
							+ ConstUtils.ERROR_JSON_PARAM_NULL, securityType);

		} else if (null == data || "".equals(data)) {
			// this.addActionMessage("错误信息：提交的MD5字符串有错");
			// return ERROR;
			return cytService.getErrorResponseJson(ConstUtils.ERROR_CODE_90001,
					ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_REQUEST_PARAM
							+ ConstUtils.ERROR_JSON_PARAM_NULL, securityType);

		}
		System.out
				.println("---------------------- 根据OTA标识进行  MD5加密比较  --------------------------");
		// ----------------验证数据完整性--------------
		// md5验证 ：根据数据库查询到 OTA 对应的key 进行加密对比
		if ("MD5".equals(securityType)) {
			// 明文加密后 与 密文对比 (key: 需要根据OTA 修改)
			boolean bool = (EncryptUtil.MD5Hex(constUtils.getKEY() + data)
					.toUpperCase()).equals(signed);
			if (false == bool) {
				return cytService.getErrorResponseJson(
						ConstUtils.ERROR_CODE_90002,
						ConstUtils.ERROR_OTO_WS + " 签证验证不通过",
						securityType);
			}
		}// AES验证
		else if ("AES".equals(securityType)) {

		}

		// ------------------Base64解码-------------------
		System.out
				.println("----------------------  开始解码   --------------------------");
		byte[] xmlByte = Base64.decode(data);
		String xml = null;
		try {
			xml = new String(xmlByte, "UTF-8");
			// String result = new String(xml.getBytes("utf-8"),"GBK");
//			System.out.println(xml);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return cytService.getErrorResponseJson(ConstUtils.ERROR_CODE_90001,
					ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_REQUEST_PARAM
							+ ConstUtils.ERROR_REQUESTPARAM_DATA_BASE64_ERROR,
					securityType);

		}
		// --------------- 判断 method 参数最终调用的方法 ----------------
		try {

			System.out
					.println("----------------------  开始选择方法  ----------------------");
			System.out
					.println("    \"getProductByQunar\".equals(method)   返回的判断条件是    ："
							+ "getProductByQunar".equals(method));
			// 验证通过进入方法：
			if ("createOrder".equalsIgnoreCase(method)) {
				// 创建订单
				System.out
						.println("----------------------  进入创建订单方法  ----------------------");
				responseXml = cytService.createOrder(xml, securityType);
			} else if ("queryOrder".equalsIgnoreCase(method)) {
				System.out
						.println("----------------------  进入获取订单信息方法  ----------------------");
				// 获取订单信息（qunar）校验
				responseXml = cytService.queryOrder(xml, securityType);
			} else if ("payOrder".equalsIgnoreCase(method)) {
				System.out
						.println("----------------------  进入支付订单方法  ----------------------");
				// 支付订单
				responseXml = cytService.payOrder(xml, securityType);
			} else if ("cancelOrder".equalsIgnoreCase(method)) {
				System.out
						.println("----------------------  畅游通调用此接口OTO退订订单，已消费的订单不能退订  ----------------------");
				// 用户申请退款
				responseXml = cytService.applyOrderRefundByUser(xml,
						securityType);
			} else if("pushOrder".equalsIgnoreCase(method)){
				System.out.println("----------------------  进入同步订单方法  ----------------------");
				responseXml = cytService.pushOrder(xml,securityType);
			}else if("createComplexOrder".equalsIgnoreCase(method)){
				System.out.println("----------------------  进入剧场订单方法  ----------------------");
				responseXml = cytService.createComplexOrder(xml,securityType);
			}else if("checkPmsData".equalsIgnoreCase(method)){
                System.out.println("----------------- 进入数据校验方法 ----------------------------");
                responseXml = cytService.checkPmsData(xml,securityType);
            }else {
				// 没有此方法
				return cytService
						.getErrorResponseJson(ConstUtils.ERROR_CODE_90009,
								ConstUtils.ERROR_OTO_WS
										+ ConstUtils.ERROR_OTO_WS
										+ ConstUtils.ERROR_NOT_HAS_METHOD,
								securityType);
			}
		} catch (Exception e) {
			System.out.println("访问方法出现系统异常，异常信息："+e);
//			e.printStackTrace();
			return cytService.getErrorResponseJson(ConstUtils.ERROR_CODE_99999,
					ConstUtils.ERROR_OTO_WS
							+ ConstUtils.ERROR_CODE_99999_DEFAULT_DESC,
					securityType);
		}
		return responseXml;
	}

	// --------------- Get And Set method ---------------//
	public void setCytService(ICytService cytService) {
		this.cytService = cytService;
	}
}
