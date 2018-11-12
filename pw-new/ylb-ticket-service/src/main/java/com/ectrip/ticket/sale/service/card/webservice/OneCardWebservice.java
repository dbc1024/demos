package com.ectrip.ticket.sale.service.card.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.MapToResultBean;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.EcServiceApi;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.SysparServiceApi;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.sale.service.card.core.CoreUtil;
import com.ectrip.ticket.sale.service.card.core.DesUtil;
import com.ectrip.ticket.sale.service.card.core.Response;
import com.ectrip.ticket.sale.service.card.model.ConsumeRequest;
import com.ectrip.ticket.sale.service.card.model.ConsumeResponse;
import com.ectrip.ticket.sale.service.card.model.GetCardStatusRequest;
import com.ectrip.ticket.sale.service.card.model.GetCardsRequest;
import com.ectrip.ticket.sale.service.card.model.GetCardsResponse;
import com.ectrip.ticket.sale.service.card.model.GetICItemsRequest;
import com.ectrip.ticket.sale.service.card.model.GetICItemsResponse;
import com.ectrip.ticket.sale.service.card.model.GetOrderListRequest;
import com.ectrip.ticket.sale.service.card.model.GetOrderListResponse;
import com.ectrip.ticket.sale.service.card.model.GetSzticketprintnosRequest;
import com.ectrip.ticket.sale.service.card.model.GetSzticketprintnosResponse;
import com.ectrip.ticket.sale.service.card.model.PrintReceiptRequest;
import com.ectrip.ticket.sale.service.card.model.RefundRequest;
import com.ectrip.ticket.sale.service.card.model.SaleRequest;
import com.ectrip.ticket.sale.service.card.model.TestRequest;
import com.ectrip.ticket.sale.service.card.model.TestResponse;
import com.ectrip.ticket.sale.service.card.service.iservice.IOneCardService;
/**
 * 可以新建任意类作为接口入口,接口名称任意,
 * 返回类型为Object类型,传入参数为一个JSON字符串
 * @author liujianwen
 */
@Service
public class OneCardWebservice {
	@Autowired
	private EcServiceApi ecService;
	
	@Autowired
	private SysparServiceApi sysparServiceApi;
	
	
	//    private 	IcPayWebService  ipws = new IcPayWebService();
	public Object test(String data){
		TestRequest spr = JSON.parseObject(data, TestRequest.class);
		System.out.println(spr.getTargetClass());
		System.out.println(spr.getMethod());
		//		ISkiSaleService is = (ISkiSaleService) SpringUtil.getBean("skiSaleService");
		//		Object obj = is.findSkiPrice(spr.getTourDate()
		//				, spr.getJgfz(), Long.parseLong(spr.getIbusinessid()),
		//				Long.parseLong(spr.getWindowid()), 
		//				Long.parseLong(spr.getEmpid()));
		TestResponse skr = new TestResponse();
		skr.setCode("0000");
		skr.setDescribe("我收到值了:");
		return skr;
	}
	public Object getCards(String request){
		GetCardsResponse res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.getCards(JSON.parseObject(request, GetCardsRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetCardsResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
		}
		return res;
	}
	/**
	 * Describe:获取唯一票号
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-3
	 */
	public Object getSzticketprintnos(String request){
		GetSzticketprintnosResponse res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.getSzticketprintnos(JSON.parseObject(request, GetSzticketprintnosRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetSzticketprintnosResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
		}
		return res;
	}
	/**
	 * Describe:根据信息价格id,日期获取充值项目列表
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-3
	 */
	public Object getICItems(String request){
		GetICItemsResponse res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.getICItems(JSON.parseObject(request, GetICItemsRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetICItemsResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
		}
		return res;

	}
	/**
	 * Describe:根据卡号查询卡状态
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-6
	 */
	public Object cardIsExist(String request){
		Response res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.cardIsExist(JSON.parseObject(request, GetCardStatusRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetICItemsResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
		}
		return res;

	}
	/**
	 * Describe:售卡
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-7
	 */
	public Object sale(String request){
		Response res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.sale(JSON.parseObject(request, SaleRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new Response();
			res.setCode("2001");
			res.setDescribe("出错");
		}
		return res;
	}
	/**
	 * Describe:充值
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-7
	 */
	public Object recharge(String request){
		Response res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.recharge(JSON.parseObject(request, SaleRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new Response();
			res.setCode("2001");
			res.setDescribe("出错");
		}
		return res;

	}
	/**
	 * 
	 * Describe:模糊查询用户 getCustoms
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-7
	 */
	public Object getCustoms(String request){
		Response res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.getCustoms(JSON.parseObject(request, GetICItemsRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new Response();
			res.setCode("2001");
			res.setDescribe("出错");
		}
		return res;
	}
	/**
	 * Describe:挂失 解挂
	 * @author liujianwen
	 * @param request
	 * return:Object
	 * Date:2016-5-11
	 */
	public Object reportTheLoss(String request){
		Response res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.reportTheLoss(JSON.parseObject(request, GetICItemsRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetICItemsResponse();
			res.setCode("2001");
			res.setDescribe("出错");
		}
		return res;
	}
	/**
	 * Describe:补卡
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-13
	 */
	public Object replaceCard(String request){
		Response res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.replaceCard(JSON.parseObject(request, SaleRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new Response();
			res.setCode("2001");
			res.setDescribe("出错");
		}
		return res;
	}

	public Object refundedCard(String request){
		Response res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.refundedCard(JSON.parseObject(request, SaleRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new Response();
			res.setCode("2001");
			res.setDescribe("出错");
		}
		return res;
	}
	/**
	 * Describe: 获取消费项目列表
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-13
	 */
	public Object getConsumePrices(String request){
		GetCardsResponse res;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.getConsumePrices(JSON.parseObject(request, GetCardsRequest.class));
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetCardsResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
		}
		return res;
	}

	/**
	 * Describe:消费
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-7
	 */
	public Object consume(String request){
		Response res = null;
		try {
			ConsumeRequest  q = JSON.parseObject(request, ConsumeRequest.class);
			if(isNumRight(q.getSzticketprintno())){
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.consume(q);
			}else{
				res = new Response();
				res.setCode("2001");
				res.setDescribe("用户编号不合法!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = new Response();
			res.setCode("2001");
			res.setDescribe("出错");
		}
		return res;
	}

	/**
	 * 闸机消费
	 * 总体对外检票方法 ticketstr="园门ID,票号" 根据票号返回检票规则 本方法已经修改，为支持畅游通，做了修改，不影响以前程序
	 *
	 * @param ticketstr 检票用的票号
	 * @param md5str    准确性校验方法
	 * @return rs rs说明： 第一个值 状态参数 -1 无效票 -2 退订 -3 对应园门不能刷该票 -4 未到检票时间 -5 已过有效期
	 * -9 已检过 -10 刷票过快 -99 已挂失 1 登记指纹 2 验证指纹 3 检票成功 请进 98
	 * 该身份证不止一张票，不能用身份证检票 99 放行卡放行
	 * <p/>
	 * 第二个值 票名称 第三个值 单价 第四个值 人群ID 第五个值 人群名称 6 有效开始时间 7 有效截至时间 8 指纹信息 9
	 * 图像路径 10 语音路径 11 竹筏seq 12 竹筏趟次名称 13 14 15 16 17 18 19 20 21 检票流水
	 * 22 检票返回次数 23 是否该票所有园门已检完 1 检完 0 未检完 最后一位返回检票类别 23 票务 21 导游 22员工
	 */
	public static ResultBean checkTicket(String ... ticketstr){
		ResultBean bean = new ResultBean();
		bean.setColumnCount(1);
		bean.setColumnNames(new String[]{"values"});
		bean.addRow(new String[]{"100"});//100表示一卡通
		try {
			String strs[] = ticketstr[0].split(",");
			ConsumeRequest request = new ConsumeRequest();
			request.setIaccessequipid(strs[0]);//闸机id
			request.setSwiping("01");//刷卡消费
			request.setSzticketprintno(strs[1]);
			request.setNote("闸机消费");
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			ConsumeResponse res = (ConsumeResponse) service.consume(request);
			Map<String,String> value = new HashMap<String,String>();
			if("0000".endsWith(res.getCode())){//请进
				Map cus = (Map) res.getCustom();
				double balance = Double.parseDouble(cus.get("balance").toString());
				double balanceType = Double.parseDouble(cus.get("balanceType").toString());
				value.put("status", "3");
				value.put("message", "消费￥"+cus.get("consume")+",余额￥"+CoreUtil.sswr2(balanceType+balance));
				bean.addRow(new String[]{JSON.toJSONString(value)});
			}else if("2021".endsWith(res.getCode())){//余额不足
				Map cus = (Map) res.getCustom();
				double balance = Double.parseDouble(cus.get("balance").toString());
				double balanceType = Double.parseDouble(cus.get("balanceType").toString());
				value.put("status", res.getCode());
				value.put("message", "余额不足！现金余额￥"+CoreUtil.sswr2(balance)+",赠送余额：￥"+CoreUtil.sswr2(balanceType));
				bean.addRow(new String[]{JSON.toJSONString(value)});
			}else{
				value.put("status", res.getCode());
				value.put("message", res.getDescribe());
				bean.addRow(new String[]{JSON.toJSONString(value)});
			}
		} catch (Throwable e) {
			e.printStackTrace();
			bean.addRow(new String[]{"-11"});
			bean.addRow(new String[]{"出错"});
		}
		return bean;

	}
	/**
	 * 查询操作记录
	 * @param request
	 * @return PosLoginResponse res
	 */
	public Object getOrderList(String request){
		Response res = null;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.getOrderList(JSON.parseObject(request, GetOrderListRequest.class));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetOrderListResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
			return res;
		}
	}
	private static boolean isNumRight(String num){
		return num.matches("\\d+") 
				&& Integer.parseInt(num.substring(0, 2))>15 
				&& Math.abs(DesUtil.calc_crc(num.substring(0, num.length()-1).getBytes())%9) 
				== Byte.parseByte(num.substring(num.length()-1,num.length()));
	}
	/**
	 * 
	 * Describe:是否是一卡通票号
	 * @author liujianwen
	 * @param szticketprintno
	 * @return
	 * return:boolean
	 * Date:2016-6-17
	 */
	public  boolean isOneCard(String ticketstr){
		try {
			String strs[] = ticketstr.split(",");
			if(isNumRight(strs[1])){
				/*IOrderService service = (IOrderService) SpringUtil.getBean("orderService");
				return service.get(Custom.class, strs[1]) != null;*/
				return ecService.getCustomByUserId(strs[1]) != null;
			}else{
				return false;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Describe:重打印小票
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Object
	 * Date:2016-5-24
	 */
	public Object printReceipt(String request){
		Response res = null;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.printReceipt(JSON.parseObject(request, PrintReceiptRequest.class));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetOrderListResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
			return res;
		}
	}
	public Object getConsumeByOrid(String request){
		Response res = null;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.getConsumeByOrid(JSON.parseObject(request, PrintReceiptRequest.class));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetOrderListResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
			return res;
		}
	}
	public Object getRechargeByOrid(String request){
		Response res = null;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.getRechargeByOrid(JSON.parseObject(request, PrintReceiptRequest.class));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetOrderListResponse();
			res.setCode("2001");
			res.setDescribe("查询出错");
			return res;
		}
	}
	public Object refund(String request){
		Response res = null;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.refund(JSON.parseObject(request, RefundRequest.class));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetOrderListResponse();
			res.setCode("2001");
			res.setDescribe("出错");
			return res;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object printHistoryConsumes(String request){
		Response res = null;
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			res = service.printHistoryConsumes(JSON.parseObject(request, GetOrderListRequest.class));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetOrderListResponse();
			res.setCode("2001");
			res.setDescribe("出错");
			return res;
		}
	}
	/**
	 * Describe:闸机调用
	 * @author liujianwen
	 * @param Iaccessequipid
	 * @return
	 * return:ResultBean
	 * Date:2016-6-20
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResultBean getConsumePricesByIaccessequipid(String gardenId, String accid, String jsonInfo){
		try {
			IOneCardService service = (IOneCardService) SpringUtil.getBean("oneCardService");
			return MapToResultBean.toResultBean((List<Map>) service.getConsumePricesByIaccessequipid(Long.parseLong(accid)));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		String str = "1,89283979379793";//
		System.out.println(str.matches("\\d+,\\d{1,16}"));
	}
}

