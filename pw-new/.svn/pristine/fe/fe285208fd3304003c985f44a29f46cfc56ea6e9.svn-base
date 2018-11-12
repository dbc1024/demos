package com.ectrip.ticket.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONStringer;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.util.Debug;
import com.ectrip.base.util.ResultBean;
import com.ectrip.ticket.cyt.common.util.Base64;
import com.ectrip.ticket.cyt.common.util.ConstUtils;
import com.ectrip.ticket.cyt.common.util.XMLParseUtil;
import com.ectrip.ticket.cyt.model.response.ResponseBody;
import com.ectrip.ticket.cyt.model.response.ResponseHeader;
import com.ectrip.ticket.sale.service.AutoSaleService;
import com.ectrip.ticket.service.util.EncryptUtil;
import com.ectrip.ticket.service.util.ResultBeanToJson;


/**
 * 对外的SERVICE； WebService地址：以实际为准 WebService接口:public String skiService (String
 * data); 请求与响应格式均为：{"data":"","sign":"","signType":"MD5"}
 * 
 * @author lijin
 * 
 */
public class AutoWebGetTicket {
	
	@Autowired
	private ConstUtils constUtils;
	/**
	 * 所有
	 */

	public static String MD5KEY = "029124EDC5E180631627E3AE44108724";
	public static String SECURITYTYPE = "MD5";

	public static final AutoGetTicket autoGetTicket = new AutoGetTicket();

	public static final AutoSaleService autoSaleService = new AutoSaleService();

	/**
	 * 当出现验证异常，调用此方法，返回异常信息 Describe: 当前仅支持 MD5
	 * 
	 * @author:huhaopeng
	 * @param code
	 * @param desc
	 * @return return:String Date:2014-4-2
	 */
	private String getErrorResponseJson(String code, String desc,
			String securityType) {

		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setCode(code);
		responseHeader.setDescribe(desc);
		ResponseBody body = new ResponseBody();
		String dataXml = null;
		try {
			dataXml = XMLParseUtil.beanToXML(body, responseHeader, "ectrip");
		} catch (Exception e1) {
			System.out
					.println("Qunar Service ： getErrorResponseJson : bean to xml 异常");
			e1.printStackTrace();
		}

		// ----------------------- Base64编译 ----------------------
		// System.out.println("返回异常信息XML:" + dataXml);
		byte[] responseByte = null;
		try {
			responseByte = dataXml.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out
					.println("Qunar Service ： getErrorResponseJson : String to xml UTF-8 Error !");
		}
		String responseData = Base64.encode(responseByte);
		// System.out.println("Base64编译后的response XML：" + responseData);

		// TODO 0 ---------------返回值(AES和MD5):MD5加密-----------------
		String responseSigned = EncryptUtil.MD5Hex(
				constUtils.getKEY() + responseData).toUpperCase();

		// ---------------返回结果转换成 拼接成 JSONString 类型-------------
		String responseJSON = "";
		if ("MD5".equals(securityType)) {
			JSONStringer stringer = new JSONStringer();
			try {
				responseJSON = stringer.object().key("data").value(responseData)
						.key("signed").value(responseSigned).key("securityType")
						.value(securityType).endObject().toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else if ("AES".equals(securityType)) {
			// AES方式加密
		}
		return responseJSON;
	}

	/**
	 * 对外服务主方法
	 * 
	 * @param method
	 * @param requestParam
	 * @return
	 * @throws Exception
	 */

	public String getMessInfo(String method, String requestParam,String url)
			throws Exception {

		if (null == method || null == requestParam || "" == method
				|| "" == requestParam) {
			return getErrorResponseJson("99998", ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_WS
					+ " [method] 为 null 或 [requestParam] 为 null", "MD5");// 当请求参数缺少时，返回错误信息
		}

		if (!method.matches("[0-9]+")) {
			return getErrorResponseJson("99997", ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_WS + " [method] 必须是数字", "MD5");// 当请求参数缺少时，返回错误信息

		}

		JSONObject jsonObject = JSONObject.parseObject(requestParam);
		System.out
				.println("-----------------------  转换JSON成功，开始获取data，signed，securityType---------------------");
		String data = jsonObject.get("data").toString();
		System.out.println("data:" + data);

		String signed = jsonObject.get("signed").toString();
		System.out.println("signed:" + signed);

		String securityType = jsonObject.get("securityType").toString();
		System.out.println("securityType:" + securityType);

		if (!dataValid(MD5KEY, data, signed, securityType)) {
			return getErrorResponseJson("99996", ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_WS + " 数据非法，解密后数值不相等", "MD5");// 当请求参数缺少时，返回错误信息

		}

		byte[] xmlByte = Base64.decode(data);
		String xml = null;
		try {
			xml = new String(xmlByte, "UTF-8");
			// data为{"status":"0001","data":"成功"} 经BASE64编码后的字符串

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();

		}

		System.out.println("后台服务调用开始");
		System.out.println("method = " + method);
		System.out.println("数据解码完成，传入真实数据为： " + xml);

		int methodint = Integer.valueOf(method).intValue();

		Debug.print("进入分支");
		String ls_rcstr = "";

		JSONObject jsonObject1 = (JSONObject) JSONObject.parse(xml);

		switch (methodint) {
		case 1: // 读取服务商信息

			return returnJson(getScenic(data));
		case 2: // 读取服务商信息

			String mac,
			ip,
			iscenicid;
			mac = jsonObject1.getString("mac");
			ip = jsonObject1.getString("ip");
			iscenicid = jsonObject1.getString("iscenicid");
			Long l_iscenicid = Long.parseLong(iscenicid);

			try {
				ResultBean rs = autoSaleService.zhuceticketwin(mac, ip,
						l_iscenicid,url);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);

		case 3: // MAC获取售票窗口及售票点信息
			JSONObject jsonObject3 = (JSONObject) JSONObject.parse(xml);
			String iscenicid3,
			mac3;
			mac3 = jsonObject3.getString("mac");

			iscenicid3 = jsonObject3.getString("iscenicid");
			Long l_iscenicid3 = Long.parseLong(iscenicid3);
			try {
				ResultBean rs = autoSaleService.getWinAndStationInfo(
						l_iscenicid3, mac3);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 4: // MAC获取售票窗口及售票点信息

			JSONObject jsonObject4 = (JSONObject) JSONObject.parse(xml);
			String empid = jsonObject4.getString("empid");
			String password = jsonObject4.getString("password");
			String iscenicid4 = jsonObject4.getString("iscenicid");
			Long l_iscenicid4 = Long.parseLong(iscenicid4);
			try {
				ResultBean rs = autoSaleService.emplogin(l_iscenicid4, empid,
						password);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);

		case 5: // 5. 获取产品价格

			Long iticketwinid = jsonObject1.getLong("iticketwinid");
			Long iemployeeid = jsonObject1.getLong("iemployeeid");
			Long ibusinessid = jsonObject1.getLong("ibusinessid");
			String stdt = jsonObject1.getString("stdt");

			try {
				ResultBean rs = autoSaleService.getTicketPrice(iticketwinid,
						iemployeeid, stdt);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 6: // 产品id获取其趟次数量控制信息
		{

			Long itickettypeid = jsonObject1.getLong("iticketwinid");
			String stdt1 = jsonObject1.getString("stdt");
			;

			try {
				// ResultBean rs =
				// autoSaleService.getTripcontrol(itickettypeid,stdt1);
				ls_rcstr = "6";// ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		}
		case 7: // 7. 保存订单

			String message = jsonObject1.getString("message");
			Long iticketwinid1 = jsonObject1.getLong("iticketwinid");
			Long iemployeeid1 = jsonObject1.getLong("iemployeeid");

			try {
				ResultBean rs = autoSaleService.saveorder(message,
						iticketwinid1, iemployeeid1,url);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 8: // 8. 支付订单并获取打印数据

			String orderno = jsonObject1.getString("orderno");
			Double yingshou = jsonObject1.getDouble("yingshou");
			Double shishou = jsonObject1.getDouble("shiShou");
			Double zhaoling = jsonObject1.getDouble("zhaoling");
			String zffs = jsonObject1.getString("zffs");
			// 李进修改 2014-12-18
			Long iticketwinid2 = jsonObject1.getLong("iticketwinid");
			Long iemployeeid2 = jsonObject1.getLong("iemployeeid");

			try {
				ResultBean rs = autoSaleService.payOrder(orderno, yingshou,
						shishou, zhaoling, iticketwinid2, iemployeeid2, zffs);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 9:
			Long iemployeeid9 = jsonObject1.getLong("iemployeeid");
			String stdt9 = jsonObject1.getString("stdt");
			try {
				ResultBean rs = autoSaleService.saleReport(iemployeeid9, stdt9, stdt9);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 100: // 取票登陆

			String md5str100 = jsonObject1.getString("md5str");
			String iscenicid100 = jsonObject1.getString("iscenicid");
			String userid100 = jsonObject1.getString("userid");
			String password100 = jsonObject1.getString("password");
			try {
				ResultBean rs = autoSaleService.saleautoservice(md5str100,
						userid100, password100, 1L, iscenicid100);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 101: // 获取订单

			String md5str101 = jsonObject1.getString("md5str");
			String userid101 = jsonObject1.getString("userid");
			String password101 = jsonObject1.getString("password");
			String iscenicid101 = jsonObject1.getString("iscenicid");
			String mac101 = jsonObject1.getString("mac");
			String cardid = jsonObject1.getString("cardid"); // 证件号码
			try {
				ResultBean rs = autoSaleService.saleautoservice(md5str101,
						userid101, password101, 2L, cardid, iscenicid101,
						mac101);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
				System.out.println(ls_rcstr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 102: // 获取订单明细

			String md5str102 = jsonObject1.getString("md5str");
			String userid102 = jsonObject1.getString("userid");
			String password102 = jsonObject1.getString("password");
			String iscenicid102 = jsonObject1.getString("iscenicid");
			String orid102 = jsonObject1.getString("orid");
			try {
				ResultBean rs = autoSaleService.saleautoservice(md5str102,
						userid102, password102, 3L, orid102, iscenicid102);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 103: // 保存订单

			String md5str103 = jsonObject1.getString("md5str");
			String orid103 = jsonObject1.getString("orid");
			String iscenicid103 = jsonObject1.getString("iscenicid");
			String iticketwinid103 = jsonObject1.getString("iticketwinid");
			String userid103 = jsonObject1.getString("userid");
			String password103 = jsonObject1.getString("password");
			try {
				ResultBean rs = autoSaleService.saleautoservice(md5str103,
						userid103, password103, 4L, orid103, iscenicid103,
						iticketwinid103);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 104: // 打印返回

			String md5str104 = jsonObject1.getString("md5str");
			String szsalesvoucherno = jsonObject1.getString("szsalesvoucherno");
			String szticketprintno = jsonObject1.getString("szticketprintno");
			String userid104 = jsonObject1.getString("userid");
			String password104 = jsonObject1.getString("password");
			try {
				ResultBean rs = autoSaleService.saleautoservice(md5str104,
						userid104, password104, 5L, szsalesvoucherno,
						szticketprintno);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 105: // 打印回执

			String md5str105 = jsonObject1.getString("md5str");
			String iscenicid105 = jsonObject1.getString("iscenicid");
			String isalevocherid = jsonObject1.getString("isalevocherid");
			String type = jsonObject1.getString("type");
			String userid105 = jsonObject1.getString("userid");
			String password105 = jsonObject1.getString("password");
			try {
				ResultBean rs = autoSaleService.saleautoservice(md5str105,
						userid105, password105, 6L, iscenicid105,
						isalevocherid, type);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		case 106: // 打印回执

			String md5str106 = jsonObject1.getString("md5str");
			String logdatetime = jsonObject1.getString("logdatetime");
			String note = jsonObject1.getString("note");
			String brief = jsonObject1.getString("brief");
			String userid106 = jsonObject1.getString("userid");
			String password106 = jsonObject1.getString("password");
			try {
				ResultBean rs = autoSaleService.saleautoservice(md5str106,
						userid106, password106, 7L, userid106, brief, note,
						logdatetime);
				ls_rcstr = ResultBeanToJson.ResultToJson(rs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnJson(ls_rcstr);
		default:
			return getErrorResponseJson("99995", ConstUtils.ERROR_OTO_WS
					+ ConstUtils.ERROR_OTO_WS
					+ " [method] 为 null 或 [requestParam] 为 null", "MD5");// 当请求参数缺少时，返回错误信息

		}

	}

	/**
	 * 获取所有服务商
	 * 
	 * @return 失败:0,无服务商
	 *         成功:1,成功,00000000000000000000000000000000,2,1|武夷山风景名胜区;9|印象大红袍
	 *         说明:成功代码,信息,校验码,服务商数量,服务商id|服务商名称;服务商id|服务商名称
	 */

	@SuppressWarnings("finally")
	String getScenic(String requestParam) {
		ResultBean rs = autoSaleService.getscenic();
		String ls_rcstr = "";
		;
		try {
			ls_rcstr = ResultBeanToJson.ResultToJson(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls_rcstr;
	}

	/**
	 * 自助设备注册信息检测，
	 * 
	 * @param iscenicid
	 *            服务商编号
	 * @param macaddr
	 *            自助售票机MAC地址
	 * @return 失败:0,本机未授权
	 *         成功:1,成功,00000000000000000000000000000000,44|自助取票窗|1|1|景区南入口
	 *         说明:成功代码，成功信息，校验码,窗口id|窗口名称|取票点id|取票点代码|取票点名称
	 */

	@SuppressWarnings("finally")
	String CTReg(long iscenicid, String macaddr) {
		return autoGetTicket.CTReg(iscenicid, macaddr);
	}

	/**
	 * 售票员登录
	 * 
	 * @param userid
	 * @param password
	 * @return 失败:0,登陆失败 成功:1,成功,00000000000000000000000000000000,141,测试km008
	 *         说明:成功代码,成功信息,校验码,操作员id|操作员名称
	 */

	@SuppressWarnings("finally")
	String EmpLogin(Long iscenicid, String userid, String password) {
		return autoGetTicket.emplogin(iscenicid, userid, password);
	}

	/**
	 * 根据身份证和服务商编号读取服务商网上已付款的订单信息
	 * 
	 * @param idno
	 *            身份证号
	 * @param iscenicid
	 *            服务商编号
	 * @return 失败： 0,订单不存在
	 *         成功：1,成功,00000000000000000000000000000000,2,20120531000000006
	 *         |029124EDC5E180631627E3AE44108724|订单号码:20120531000000006
	 *         已支付金额:￥456
	 *         订单日期:2012-06-01|李进|全面测试分社一,中国，湖南，株洲，石峰区|2;20120531000000006
	 *         |029124EDC5E180631627E3AE44108724|订单号码:20120531000000006
	 *         已支付金额:￥456|2012-06-01|李进|全面测试分社一,中国，湖南，株洲，石峰区|2;
	 *         说明:成功代码,成功信息,校验码,
	 *         订单数,订单号|密码(当为0时不需要输入密码直接出票)|终端显示信息|导游名称|单位名称|客源地(
	 *         其中的逗号是全角)|业务id;订单号|密码|终端显示信息|导游名称|单位名称|客源地(其中的逗号是全角)|业务id
	 */

	@SuppressWarnings("finally")
	String CTCXService(String idno, Long iscenicid,String url) {
		return autoGetTicket.CTCXService(idno, iscenicid,url);
	}

	/**
	 * 当前订单出票,条码类型暂未实现
	 * 
	 * @param orid
	 *            订单号
	 * @param iscenicid
	 *            服务商id
	 * @param szstationmsg
	 *            自助售票机信息，格式:取票窗id,取票窗名称,取票点id,取票点代码,取票点名称
	 * @param szemployeemsg
	 *            售票员信息，格式:售票员id,售票员名称
	 * @param bookmsg
	 *            订单信息，格式:导游名称,单位名称,客源地,业务id
	 * @return 失败: "0,服务异常请联系管理员" 成功:
	 *         1,成功,00000000000000000000000000000000,3,1|1
	 *         W1A160378WW0C|15423433|团队景车票(全票)|流水号:15376992
	 *         (1/2)|有效期:2012.06.02
	 *         -2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖
	 *         ，宝坻县|有效票类:三日游景点票,三日游车票
	 *         |;1,1W1A160378WX75|团队景车票(全票)|流水号:导游名称:李进5376993
	 *         (2/2)|有效期:2012.06.02
	 *         -2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖
	 *         ，宝坻县|有效票类:三日游景点票,三日游车票
	 *         |;1|1W1A160378WW0C|15423433|团队景车票(全票)|流水号:15376992
	 *         (1/2)|有效期:2012.06
	 *         .02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖
	 *         ，宝坻县|有效票类:三日游景点票
	 *         ,三日游车票|;1,1W1A160378WX75|团队景车票(全票)|流水号:导游名称:李进5376993
	 *         (2/2)|有效期:2012.06
	 *         .02-2012.06.04|导游名称:李进|旅行社名称:全面测试分社一|客源地:中国，天津，天津县辖
	 *         ，宝坻县|有效票类:三日游景点票,三日游车票|
	 */

	@SuppressWarnings("finally")
	String CTQPService(String orid, Long iscenicid, String szstationmsg,
			String szemployeemsg, String bookmsg) {
		return this.autoGetTicket.CTQPService(orid, iscenicid, szstationmsg,
				szemployeemsg, bookmsg);
	}

	/**
	 * 订单撤消
	 * 
	 * @param orid
	 *            订单号
	 * @return
	 */
	String CTQPCXService(String orid) {
		return this.autoGetTicket.CTQPCXService(orid);
	}

	/**
	 * 获取订单数据
	 * 
	 * @param orid
	 *            订单号
	 * @param iscenicid
	 *            服务商id
	 * @return 成功:
	 *         成功代码,成功信息,校验码,订单数据条数,票数量|票名|人群种类|单价|生效日期|结束日期|订单信息（cancelt_order
	 *         的参数 message）; 票数量|票名|人群种类|单价|生效日期|结束日期|订单信息（cancelt_order 的参数
	 *         message）
	 *         如:1,成功,00000000000000000000000000000000,2,2|团队景车票|半票|115|
	 *         2012-06-15|2012-06-17|message;
	 *         6|团队景车票|全票|230|2012-06-15|2012-06-17|message
	 *         message格式:17,84,235,4,2012-06-19,2012-06-21#4,4,2012-06-19
	 *         00:00:00,2012-06-21 23:59:59,0&12,4,2012-06-19
	 *         00:00:00,2012-06-21 23:59:59,0&
	 * @16,82,335,3,2012-06-19,2012-06-21#1,3,2012-06-19 16:10:00,2012-06-19
	 *                                                   17:40
	 *                                                   :00,8&4,3,2012-06-19
	 *                                                   00:00:00,2012-06-21
	 *                                                   23:59
	 *                                                   :59,0&12,3,2012-06-19
	 *                                                   00:00:00,2012-06-21
	 *                                                   23:59
	 *                                                   :59,0&@6,141,10,1,2012
	 *                                                   -06-19,2012-06-20#6,1,
	 *                                                   2012-06-19
	 *                                                   00:00:00,2012-06-20
	 *                                                   23:59:59,0&@ 失败:
	 *                                                   0,此订单无数据
	 */
	@SuppressWarnings("finally")
	String getOrderlist(String orid, Long iscenicid,String url) {

		return this.autoGetTicket.getT_orderlist(orid, iscenicid,url);

	};

	/**
	 * 
	 * @param orid
	 *            订单号
	 * @param iscenicid
	 *            服务商id
	 * @param szstationmsg
	 *            自助售票机信息，格式:取票窗id|取票窗名称|取票点id|取票点代码|取票点名称
	 * @param szemployeemsg
	 *            售票员信息，格式:售票员id|售票员名称
	 * @param bookmsg
	 *            订单信息
	 * @param message
	 *            修改后新的数量信息，格式为: 新数量|message(getT_orderlist返回的参数);
	 *            新数量|message(getT_orderlist返回的参数) 如:1|message;5|message
	 * @param isqt
	 *            暂时填0
	 * @param forceemid
	 *            暂时填0
	 * @return
	 */
	String Cancelt_Order(String orid, Long iscenicid, String szstationmsg,
			String szemployeemsg, String bookmsg, String message, Long isqt,
			Long forceemid,String url) {
		return this.autoGetTicket.cancelt_order(orid, iscenicid, szstationmsg,
				szemployeemsg, bookmsg, message, isqt, forceemid,url);
	};

	/**
	 * 获取服务器时间
	 * 
	 * @return 2012-09-09 19:09:09
	 */
	String getServerTimes() {
		return this.autoGetTicket.getServerTimes();
	}

	/**
	 * 验证数据是否相等，加密方式是否正确
	 * 
	 * @param md5Key
	 * @param data
	 * @param signed
	 * @param securityType
	 * @return
	 */
	boolean dataValid(String md5Key, String data, String signed,
			String securityType) {

		try {

			// ----------------验证数据完整性--------------
			if ("MD5".equals(securityType)) {
				// 明文加密后 与 密文对比 (key:123)

				String ls_data = data + "," + md5Key;
				String meiwen = EncryptUtil.MD5Hex(ls_data);
				boolean bool = (meiwen.toUpperCase()).equalsIgnoreCase(signed);
				if (false == bool) {
					return false;
				}
				return true;
			}// AES验证
			else if ("AES".equals(securityType)) {

			}
			// 参数不正确返回false
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * JSON 返回字符串处理
	 * 
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String returnJson(String data) throws UnsupportedEncodingException {

		// String data = "1";
		// 加密类型，必须传入;
		String securityType = SECURITYTYPE;

		// 计算MD5值

		Map m = new HashMap();

		data = Base64.encode(data.getBytes("utf-8"));

		m.put("data", data);

		data = data + "," + MD5KEY;
		String signed = com.ectrip.ticket.service.util.EncryptUtil.MD5Hex(data);
		m.put("securityType", securityType);
		m.put("signed", signed);

		// System.out.println("m=" + m);
		// 把三个值全部存放在 ；
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(m);
		String requestParam = jsonObject.toJSONString();
		return requestParam;
	}

}
