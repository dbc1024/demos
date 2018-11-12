package com.ectrip.ticket.sale.service.cytterminal.service;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.MapToResultBean;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.ValidateUtil;
import com.ectrip.ec.model.cytterminal.CombineOrderInfo;
import com.ectrip.ec.model.cytterminal.ConsumeOrderInfo;
import com.ectrip.ec.model.cytterminal.OrderInfo;
import com.ectrip.ec.model.cytterminal.QueryOrderInfo;
import com.ectrip.ec.model.cytterminal.response.QueryOrderResponse;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.sale.service.cytterminal.client.CytTerminalClient;
import com.ectrip.ticket.sale.service.cytterminal.model.Cyttasktab;
import com.ectrip.ticket.sale.service.cytterminal.model.CyttasktabId;
import com.ectrip.ticket.sale.service.cytterminal.service.iservice.ICytSaleService;

@Service("cytSaleMainService")
public class CytSaleMainService extends GenericService {

	@Autowired
	private ICytSaleService cytSaleService;
	
	@Autowired
	private CytTerminalClient cytTerminalClient;
	
	
	/**
	 * Describe:
	 * @author:chenxinhao
	 * @param orid
	 * @param iscenicid
	 * @param iticketwinid
	 * @param iemployeeid
	 * @param sign
	 * @param numb
	 * @param saleType
	 * @param yzp
	 * @param cash
	 * @return
	 * return:ResultBean
	 * Date:2015-11-2
	 */
	public ResultBean consumeOrder(String orid, Long iscenicid,Long iticketwinid, Long iemployeeid, String sign,
								   Long numb,int saleType,String yzp, String cash){
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });

		//基础数据验证与填充
		ConsumeOrderInfo consumeOrderInfo = new ConsumeOrderInfo();
		QueryOrderInfo queryOrderInfo = new QueryOrderInfo();
		String[] mess = consumeOrderCheck(orid, iscenicid, iticketwinid, iemployeeid,
				sign, numb, consumeOrderInfo,queryOrderInfo);
		if(mess != null){
			rs.addRow(mess);
			return rs;
		}
		//获取订单详情
		OrderInfo orderInfo = findOrderInfo(queryOrderInfo);
		if(orderInfo == null){
			rs.addRow(new String[] { "false", "查询订单明细出错" });
			return rs;
		}
		Long priceId = Long.parseLong(orderInfo.getDetails().get(0).get("resourceId").toString());
		//查找未完成任务
		Cyttasktab cytTask = this.cytSaleService.findCytTask(orid);
		if(cytTask != null){//有未完成任务
			Long taskNumb = cytTask.getNumb();
			consumeOrderInfo.setSign(cytTask.getId().getSign());
			if(taskNumb.longValue() != numb.longValue()){//2次提交数量不一致，不可操作
				rs.addRow(new String[] { "false", "该订单有未完成任务，请先把数量修改成["+taskNumb+"]后再次出票" });
				return rs;
			}
		}else{//新建任务
			cytTask = new Cyttasktab();
			CyttasktabId id = new CyttasktabId(orid, sign);
			cytTask.setId(id);
			cytTask.setNumb(numb);
			cytTask.setPriceid(priceId);
			cytTask.setUsid(orderInfo.getSaleName());
			if(numb > Long.parseLong(orderInfo.getCount())){
				rs.addRow(new String[] { "false", "消费数量不能大于剩余数量" });
				return rs;
			}
		}
		//组装临时订单
		CombineOrderInfo combineOrderInfo = new CombineOrderInfo();
		combineOrderInfo.setOrid(orid);
		combineOrderInfo.setIscenicid(iscenicid);
		combineOrderInfo.setNumb(numb);
		combineOrderInfo.setOrderInfo(orderInfo);
		try {
			combinationOrder(combineOrderInfo);
		} catch (Exception e) {
			e.printStackTrace();
			rs.addRow(new String[] { "false", "组装订单异常："+e.getMessage() });
			return rs;
		}
		//保存出票数据并请求畅游通消费通知
		ResultBean rs1 = null;
		try {
			rs1 = cytSaleService.consumeOrder(saleType,consumeOrderInfo, combineOrderInfo, iscenicid, iticketwinid, iemployeeid, yzp, cash);
		} catch (Exception e) {
			//修改出票任务
			cytTask.setStatus(0L);
			cytTask.setDtmakedate(Tools.getDayTimes());
			this.cytSaleService.saveOrUpdate(cytTask);
			e.printStackTrace();
			rs.addRow(new String[] { "false", "保存凭证数据异常："+e.getMessage() });
			return rs;
		}
		//修改出票任务
		cytTask.setStatus(1L);
		cytTask.setDtmakedate(Tools.getDayTimes());
		this.cytSaleService.saveOrUpdate(cytTask);
		return rs1;
	}

	/**
	 * *
	 * Describe:订单查询
	 * @see com.ectrip.sale.service.cytterminal.iservice.ICytSaleService#queryCytOrder(java.lang.String, java.lang.Long)
	 * @param queryMessage
	 * @param iscenicid
	 * @return
	 * @author chenxinhao
	 * Date:2015-10-29
	 */
	public ResultBean queryCytOrder(String queryMessage,Long iscenicid,int saleType){
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });

		QueryOrderInfo queryOrderInfo = new QueryOrderInfo();
		//订单列表查询验证(查询条件验证)
		String[] mess = queryOrderCheck(queryMessage,iscenicid,queryOrderInfo);
		if(mess == null){//无错误信息
			//调用畅游通接口查询订单
			Map<String,String> map = cytTerminalClient.queryOrder(queryOrderInfo);
			//状态码 1000 表示成功
			String code = map.get("code");
			if(!StringUtils.isBlank(code) && "1000".equals(code)){
				String data = map.get("data");//返回数据集，JSON格式
				QueryOrderResponse queryOrderResponse = JSON.parseObject(data, QueryOrderResponse.class);
				if(queryOrderResponse != null){
					List<OrderInfo> orderlist = queryOrderResponse.getOrderInfos();//订单列表
					List<Map> result = new ArrayList<Map>();//返回售票软件的订单结果集
					if(orderlist != null && !orderlist.isEmpty()){
						for (OrderInfo orderInfo : orderlist) {
							Map order = new HashMap();
							//验证返回数据正确性
							mess = orderInfoCheck(queryOrderInfo,orderInfo,order,saleType);
							//只有可消费的订单才保存，其他订单过滤
							if(mess == null){
								if(order != null && order.size()>0){
									if(queryOrderInfo.getMethod().equalsIgnoreCase("ORDER")){
										order.put("CHECKPWD", "true");//只有查询方式为订单号的订单才需要密码验证
									}
									result.add(order);
								}
							}else{
								rs.addRow(mess);
								return rs;
							}
						}
					}
					return MapToResultBean.toResultBean(result);
				}else{
					rs.addRow(new String[] { "false", "queryOrderResponse返回为空" });
					return rs;
				}
			}else{
				rs.addRow(new String[] { "false", map.get("describe") });
				return rs;
			}
		}else{//查询条件错误信息返回
			rs.addRow(mess);
			return rs;
		}
	}

	/**
	 *
	 * Describe:查看订单详情
	 * @author:chenxinhao
	 * @param orid
	 * @param iscenicid
	 * @param iscenicid  1:售票软件,2:自助出票
	 * @return
	 * return:ResultBean
	 * Date:2015-10-29
	 * @throws Exception
	 */
	public ResultBean queryOrderDetail(String orid,Long iscenicid,int saleType) throws Exception{
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });

		QueryOrderInfo queryOrderInfo = new QueryOrderInfo();
		//订单列表查询验证(查询条件验证)
		String[] mess = queryOrderCheck(orid,iscenicid,queryOrderInfo);
		if(mess == null){//无错误信息
			//调用畅游通接口查询订单
			Map<String,String> map = cytTerminalClient.queryOrder(queryOrderInfo);
			//状态码 1000 表示成功
			String code = map.get("code");
			if(!StringUtils.isBlank(code) && "1000".equals(code)){
				String data = map.get("data");//返回数据集，JSON格式
				QueryOrderResponse queryOrderResponse = JSON.parseObject(data, QueryOrderResponse.class);
				if(queryOrderResponse != null){
					List<OrderInfo> orderlist = queryOrderResponse.getOrderInfos();//订单列表
					List<Map> result = new ArrayList<Map>();//返回售票软件的订单结果集
					if(orderlist != null && !orderlist.isEmpty()){
						for (OrderInfo orderInfo : orderlist) {
							Map order = new HashMap();
							mess = orderDetailsCheck(orderInfo,order,saleType);
							if(mess == null){
								Long sign = cytSaleService.getSequence("consume_sequence");
								order.put("SIGN", queryOrderInfo.getOtoCode()+sign.toString());
								Cyttasktab cytTask = this.cytSaleService.findCytTask(orid);
								if(cytTask != null){
									order.put("NUMB", cytTask.getNumb());
								}
								result.add(order);
							}else{
								rs.addRow(mess);
								return rs;
							}
						}
					}
					return MapToResultBean.toResultBean(result);
				}else{
					rs.addRow(new String[] { "false", "queryOrderResponse返回为空" });
					return rs;
				}
			}else{
				rs.addRow(new String[] { "false", map.get("describe") });
				return rs;
			}
		}else{//查询条件错误信息返回
			rs.addRow(mess);
			return rs;
		}
	}

	/**
	 *
	 * Describe:闸机检票查询订单专用
	 * @author:chenxinhao
	 * @param posId  闸机ID
	 * @param orid	订单号
	 * @return
	 * return:OrderInfo
	 * Date:2015-11-2
	 */
	public OrderInfo findOrderByOrderId(String posId,String orid){
		//根据POSID查找服务商ID
		Esbscenicareatab provider = cytSaleService.queryProviderByPosId(posId);
		if(provider != null){
			//判断是否能直接出票
			Hotelprovider scen = (Hotelprovider) cytSaleService.get(Hotelprovider.class, provider.getIscenicid());
			if(scen == null || scen.getInoteger3() != 1) return null;

			QueryOrderInfo queryOrderInfo = new QueryOrderInfo();
			//订单列表查询验证(查询条件验证)
			String[] mess = queryOrderCheck(orid,provider.getIscenicid(),queryOrderInfo);
			if(mess == null){//无错误信息
				//调用畅游通接口查询订单
				Map<String,String> map = cytTerminalClient.queryOrder(queryOrderInfo);
				//状态码 1000 表示成功
				String code = map.get("code");
				if(!StringUtils.isBlank(code) && "1000".equals(code)){
					String data = map.get("data");//返回数据集，JSON格式
					QueryOrderResponse queryOrderResponse = JSON.parseObject(data, QueryOrderResponse.class);
					if(queryOrderResponse != null){
						List<OrderInfo> orderlist = queryOrderResponse.getOrderInfos();//订单列表
						if(orderlist != null && !orderlist.isEmpty()){
							OrderInfo orderInfo = orderlist.get(0);
							if(!orderInfo.getStatus().equals("PRINTSUCCESS")
									&& !orderInfo.getStatus().equals("CONSUMED")){
								return null;
							}
							orderInfo.setIscenicid(provider.getIscenicid().toString());
							return orderInfo;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 *
	 * Describe:获取订单数据
	 * @author:chenxinhao
	 * @param orid
	 * @param totalCount
	 * @return
	 * return:Map
	 * Date:2015-11-3
	 */
	public Map checkOTOOrder(String orid,Long totalCount){
		Map resultMap = new HashMap();
		List orderList = cytSaleService.findOTOOrder(orid);
		if(orderList != null && !orderList.isEmpty()){
			if(orderList.size() == 1){
				Map map = (Map) orderList.get(0);
				Long count = Long.parseLong(map.get("numb").toString());
				if(totalCount.longValue() != count.longValue()){//一个订单且数量不等于总数量，则为部分出票订单
					resultMap.put("status", "false");
					resultMap.put("msg", "该订单存在部分出票信息，无法直接刷码入园");
					return resultMap;
				}else{//已出票订单
					map.put("status", "consume");
					return map;
				}
			}else{//存在多条订单数据，则为部分出票订单
				resultMap.put("status", "false");
				resultMap.put("msg", "该订单存在部分出票信息，无法直接刷码入园");
				return resultMap;
			}
		}else{//未出票订单
			resultMap.put("status", "true");
			return resultMap;
		}
	}

	/**
	 *
	 * Describe:出票时查询订单数据
	 * @author:chenxinhao
	 * @param queryOrderInfo
	 * @return
	 * return:OrderInfo
	 * Date:2015-11-3
	 */
	private OrderInfo findOrderInfo(QueryOrderInfo queryOrderInfo){
		Map<String,String> map = cytTerminalClient.queryOrder(queryOrderInfo);
		//状态码 1000 表示成功
		String code = map.get("code");
		if(!StringUtils.isBlank(code) && "1000".equals(code)){
			String data = map.get("data");//返回数据集，JSON格式
			QueryOrderResponse queryOrderResponse = JSON.parseObject(data, QueryOrderResponse.class);
			if(queryOrderResponse != null){
				List<OrderInfo> orderlist = queryOrderResponse.getOrderInfos();//订单列表
				if(orderlist != null && !orderlist.isEmpty()){
					return orderlist.get(0);
				}
			}
		}
		return null;
	}

	/**
	 *
	 * Describe:订单详情数据校验与组装
	 * @author:chenxinhao
	 * @param orderInfo
	 * @param order
	 * @return
	 * return:String[]
	 * Date:2015-10-30
	 */
	private String[] orderDetailsCheck(OrderInfo orderInfo,Map order,int saleType){
		String status = orderInfo.getStatus();//订单状态
		String visitDate = orderInfo.getVisitDate();//产品消费日期
		List<Map> details = orderInfo.getDetails();//畅游通推送的订单明细
		if(details != null && !details.isEmpty()){
			Map detailsMap = details.get(0);
			String resourceId = detailsMap.get("resourceId") == null
					? "" : detailsMap.get("resourceId").toString();//价格ID
			String password = detailsMap.get("password") == null
					? "" : detailsMap.get("password").toString();//取票密码
			String paymentType = detailsMap.get("paymentType") == null
					? "" : detailsMap.get("paymentType").toString();//支付方式
			if(StringUtils.isBlank(resourceId)
					|| StringUtils.isBlank(password) || StringUtils.isBlank(paymentType)){
				return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]明细参数缺失，请联系工作人员" };
			}
			boolean b = false;
			if((status.equalsIgnoreCase("PRINTSUCCESS") && paymentType.equalsIgnoreCase("PREPAY")) ||
					(status.equalsIgnoreCase("NOTPAYED") && paymentType.equalsIgnoreCase("CASHPAY"))){
				b = true;
			}else{
				Cyttasktab cytTask = this.cytSaleService.findCytTask(orderInfo.getId());
				if(cytTask != null){
					b = true;
				}
			}
			//网上支付和现付的订单才能读取
			if(b){
				//查找价格
				Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.cytSaleService.get(Edmcrowdkindpricetab.class, Long.parseLong(resourceId));
				if(price != null){
					if(Double.parseDouble(orderInfo.getPrice()) != price.getMactualsaleprice().doubleValue()){
						return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]产品价格异常" };
					}
				}else{
					return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]产品不存在" };
				}
				//查找用户
				Custom custom = (Custom) this.cytSaleService.get(Custom.class, orderInfo.getSaleName());
				if(custom == null){
					return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]用户不存在，请联系工作人员" };
				}
				//查找服务商
				Edmtickettypetab ticket = (Edmtickettypetab) this.cytSaleService.get(Edmtickettypetab.class, price.getItickettypeid());
				if(saleType == 2 && !ticket.getBymaketicketway().equals("00")){
					return new String[] { "false","该订单存在非打印票，不能在自助出票机出票" };
				}
				Esbscenicareatab provider = (Esbscenicareatab) this.cytSaleService.get(Esbscenicareatab.class,ticket.getIscenicid());
				Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) this.cytSaleService.get(Edpcrowdkindtab.class, price.getIcrowdkindid());
				String enddate = Tools.getDate(visitDate, ticket.getValidityday().intValue()-1);
				putValue(order, new String[] {"ORID","DTSTARTDATE","DTENDDATE","ISCENICID","ITICKETTYPEID","SZTICKETTYPENAME","ICROWDKINDPRICEID",
								"ICROWDKINDID","SZCROWDKINDNAME","PRICE","NUMB","YHNUMB","YHAMNT","AMNT","BYMAKETICKETWAY","BYMEDIATYPE","VERIFYPART"},
						new String[] {orderInfo.getId(),visitDate,enddate,provider.getIscenicid().toString(),ticket.getItickettypeid().toString(),
								ticket.getSztickettypename(),price.getIcrowdkindpriceid().toString(),price.getIcrowdkindid().toString(),crowdkind.getSzcrowdkindname(),
								orderInfo.getPrice(),orderInfo.getCount(),"0","0",orderInfo.getMoney(),ticket.getBymaketicketway(),ticket.getBymediatype(),orderInfo.getVerifyPart()});
				return null;
			}else{
				return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]不是网上支付与现付订单，不可出票" };
			}
		}else{
			return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]明细参数缺失，请联系工作人员" };
		}
	}

	/**
	 *
	 * Describe:畅游通返回结果数据验证
	 * @author:chenxinhao
	 * @param orderInfo
	 * @param order
	 * @return
	 * return:String[]
	 * Date:2015-10-29
	 */
	private String[] orderInfoCheck(QueryOrderInfo queryOrderInfo,OrderInfo orderInfo,Map order,int saleType){
		String status = orderInfo.getStatus();//订单状态
		List<Map> details = orderInfo.getDetails();//畅游通推送的订单明细
		if(details != null && !details.isEmpty()){
			Map detailsMap = details.get(0);
			String resourceId = detailsMap.get("resourceId") == null
					? "" : detailsMap.get("resourceId").toString();//价格ID
			String password = detailsMap.get("password") == null
					? "" : detailsMap.get("password").toString();//取票密码
			String paymentType = detailsMap.get("paymentType") == null
					? "" : detailsMap.get("paymentType").toString();//支付方式
			if(StringUtils.isBlank(resourceId)
					|| StringUtils.isBlank(password) || StringUtils.isBlank(paymentType)){
				return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]明细参数缺失，请联系工作人员" };
			}
			boolean b = false;
			if((status.equalsIgnoreCase("PRINTSUCCESS") && paymentType.equalsIgnoreCase("PREPAY")) ||
					(status.equalsIgnoreCase("NOTPAYED") && paymentType.equalsIgnoreCase("CASHPAY"))){
				b = true;
			}else{
				Cyttasktab cytTask = this.cytSaleService.findCytTask(orderInfo.getId());
				if(cytTask != null){
					b = true;
				}
			}
			//网上支付和现付的订单才能读取
			if(b){
				//自助取票机不能出现付订单
				if(saleType == 2 && status.equalsIgnoreCase("NOTPAYED") && paymentType.equalsIgnoreCase("CASHPAY")){
					return null;
				}
				//查找价格
				Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.cytSaleService.get(Edmcrowdkindpricetab.class, Long.parseLong(resourceId));
				if(price != null){
					if(Double.parseDouble(orderInfo.getPrice()) != price.getMactualsaleprice().doubleValue()){
						return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]产品价格异常" };
					}
				}else{
					return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]产品不存在" };
				}
				//查找用户
				Custom custom = (Custom) this.cytSaleService.get(Custom.class, orderInfo.getSaleName());
				if(custom == null){
					return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]用户不存在，请联系工作人员" };
				}
				//查找服务商
				Edmtickettypetab ticket = (Edmtickettypetab) this.cytSaleService.get(Edmtickettypetab.class, price.getItickettypeid());
				Esbscenicareatab provider = (Esbscenicareatab) this.cytSaleService.get(Esbscenicareatab.class,ticket.getIscenicid());
				//转换订单状态和支付方式
				if(status.equalsIgnoreCase("PRINTSUCCESS") && paymentType.equalsIgnoreCase("PREPAY")){//网上支付
					putValue(order,new String[]{"DDZT","ZFFS","STRZFFS"},new String[]{"02","08","OTA预付款支付"});
				}
				if(status.equalsIgnoreCase("NOTPAYED") && paymentType.equalsIgnoreCase("CASHPAY")){//现付
					putValue(order,new String[]{"DDZT","ZFFS","STRZFFS"},new String[]{"00","09","OTA景区现付"});
				}
				//填充其他订单数据
				putValue(order,new String[]{"USID","LNAME","CORPNAME","ISCENICID","SZSCENICNAME","ORID","IBUSINESSID","JSFZ","MONT"},
						new String[]{custom.getUsid(),custom.getLname(),custom.getCorpname(),provider.getIscenicid().toString(),
								provider.getSzscenicname(),orderInfo.getId(),custom.getIbusinessid().toString(),custom.getNote2(),orderInfo.getMoney()});
				if("ORDER".equals(queryOrderInfo.getMethod()) || saleType == 2){
					order.put("NOTEA", password);
				}
				return null;
			}else{
				return null;
			}
		}else{
			return new String[] { "false", "畅游通订单["+orderInfo.getId()+"]明细参数缺失，请联系工作人员" };
		}
	}

	/**
	 *
	 * Describe:map批量赋值
	 * @author:chenxinhao
	 * @param map
	 * @param keys
	 * @param values
	 * return:void
	 * Date:2015-11-3
	 */
	private void putValue(Map map,String[] keys,String[] values){
		int length = keys.length;
		for(int i=0;i<length;i++){
			map.put(keys[i], values[i]);
		}
	}
	/**
	 *
	 * Describe:订单列表查询验证
	 * @author:chenxinhao
	 * @param queryMessage
	 * @param iscenicid
	 * @param queryOrderInfo
	 * @return
	 * return:String[]
	 * Date:2015-10-29
	 */
	private String[] queryOrderCheck(String queryMessage,Long iscenicid,QueryOrderInfo queryOrderInfo){
		//判断参数格式
		if(queryMessage != null && !"".equals(queryMessage)){
			if(queryMessage.length() == 11){//手机号码
				if(ValidateUtil.isMobile(queryMessage)){
					queryOrderInfo.setMethod("PHONE");
					queryOrderInfo.setPhone(queryMessage);
				}else{
					return new String[] {"false","输入的手机号格式不正确"};
				}
			}else if(queryMessage.length() == 17){//订单号
				queryOrderInfo.setMethod("ORDER");
				queryOrderInfo.setOrderId(queryMessage);
			}else if(queryMessage.length() == 15 || queryMessage.length() == 18){//身份证
				queryOrderInfo.setMethod("ID");
				queryOrderInfo.setCredentials(queryMessage);
			}else{
				return new String[] {"false","输入订单号或身份证或手机号码的长度不正确"};
			}
		}else{
			return new String[] {"false","请输入订单号或身份证或手机号码"};
		}
		//判断系统参数是否存在
		Sysparv5 v5 = this.cytSaleService.findSyspar("CYTT", "01");
		if(v5 == null || v5.getIsa() == 1L){
			return new String[] {"false","CYTT参数未设置，或该系统有公网地址，无法使用该功能"};
		}
		v5 = this.cytSaleService.findSyspar("CYTT", "02");
		if(v5 == null || StringUtils.isBlank(v5.getPmvb())){
			return new String[] {"false","CYTT参数未设置，消费终端MAC地址未设置"};
		}else{
			queryOrderInfo.setDevId(v5.getPmvb());
		}
		//判断企业编码是否存在
		String otoCode = this.cytSaleService.findOTOCode(iscenicid);
		if(StringUtils.isBlank(otoCode)){
			return new String[] {"false","未找到企业编码设置"};
		}else{
			queryOrderInfo.setOtoCode(otoCode);
		}
		return null;
	}

	private String[] consumeOrderCheck(String orid, Long iscenicid,Long iticketwinid, Long iemployeeid, String sign,
									   Long numb,ConsumeOrderInfo consumeOrderInfo,QueryOrderInfo queryOrderInfo){
		if(StringUtils.isBlank(orid)) return new String[] {"false","订单号不能为空"};
		if(iscenicid == null) return new String[] {"false","服务商ID不能为空"};
		if(iticketwinid == null) return new String[] {"false","窗口ID不能为空"};
		if(iemployeeid == null) return new String[] {"false","售票员ID不能为空"};
		if(StringUtils.isBlank(sign)) return new String[] {"false","订单号不能为空"};
		if(numb == null || numb <=0) return new String[] {"false","消费数量必须大于0"};
		//判断系统参数是否存在
		Sysparv5 v5 = this.cytSaleService.findSyspar("CYTT", "01");
		if (v5 == null || v5.getIsa() == 1L) {
			return new String[] { "false", "CYTT参数未设置，或该系统有公网地址，无法使用该功能" };
		}
		v5 = this.cytSaleService.findSyspar("CYTT", "02");
		if (v5 == null || StringUtils.isBlank(v5.getPmvb())) {
			return new String[] { "false", "CYTT参数未设置，消费终端MAC地址未设置" };
		} else {
			consumeOrderInfo.setDevId(v5.getPmvb());
			queryOrderInfo.setDevId(v5.getPmvb());
		}
		// 判断企业编码是否存在
		String otoCode = this.cytSaleService.findOTOCode(iscenicid);
		if (StringUtils.isBlank(otoCode)) {
			return new String[] { "false", "未找到企业编码设置" };
		} else {
			consumeOrderInfo.setOtoCode(otoCode);
			queryOrderInfo.setOtoCode(otoCode);
		}
		consumeOrderInfo.setOrid(orid);
		consumeOrderInfo.setSign(sign);
		consumeOrderInfo.setPassword("");
		consumeOrderInfo.setVerifyPassword("FALSE");
		consumeOrderInfo.setConsumeCount(numb.toString());
		queryOrderInfo.setMethod("ORDER");
		queryOrderInfo.setOrderId(orid);
		return null;
	}

	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };// 用来将字节转换成16进制表示的字符
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();// MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2];// 每个字节用 16 进制表示的话，使用两个字符， 所以表示成 16
			// 进制需要 32 个字符
			int k = 0;// 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) {// 从第一个字节开始，对 MD5 的每一个字节// 转换成 16
				// 进制字符的转换
				byte byte0 = tmp[i];// 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中高 4 位的数字转换,// >>>
				// 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf];// 取字节中低 4 位的数字转换

			}
			s = new String(str);// 换后的结果转换为字符串

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return s;
	}

	private void combinationOrder(CombineOrderInfo combineOrderInfo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException{
		OrderInfo orderInfo = combineOrderInfo.getOrderInfo();
		Long priceId = Long.parseLong(orderInfo.getDetails().get(0).get("resourceId").toString());
		String paymentType = orderInfo.getDetails().get(0).get("paymentType").toString();
		String ddzt = "02";
		String zffs = "08";
		if(paymentType.equalsIgnoreCase("CASHPAY")){
			ddzt = "00";
			zffs = "09";
		}
		Custom custom = (Custom) this.cytSaleService.get(Custom.class, orderInfo.getSaleName());
		Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.cytSaleService
				.get(Edmcrowdkindpricetab.class, priceId);
		Edmtickettypetab ticket = (Edmtickettypetab) this.cytSaleService.get(
				Edmtickettypetab.class, price.getItickettypeid());
		Esbscenicareatab provider = (Esbscenicareatab) this.cytSaleService.get(
				Esbscenicareatab.class, ticket.getIscenicid());
		String orid = combineOrderInfo.getOrid();
		String rzti = orderInfo.getVisitDate();
		String ldti = Tools.getDate(rzti, ticket.getValidityday().intValue()-1);
		MOrder morder = combineOrderInfo.getMorder();
		YOrder yorder = combineOrderInfo.getYorder();
		TOrder torder = combineOrderInfo.getTorder();
		List<TOrderlist> tlist = combineOrderInfo.getTlist();
		List<TZorderlist> tzlist = combineOrderInfo.getTzlist();
		List<YOrderlist> ylist = combineOrderInfo.getYlist();
		List<YZorderlist> yzlist = combineOrderInfo.getYzlist();
		String newOrid = this.getMaxNo("777");
		//组装MOrder
		morder.setOrid(newOrid);
		morder.setSrid(orid);
		morder.setStdt(rzti);
		morder.setOrtp("01");// 预订单
		morder.setUsid(custom.getUsid());
		morder.setOrda(Tools.getDays());
		morder.setOrti(Tools.getNowTime());
		morder.setBank("92");
		morder.setBankdata(Tools.getDays());
		morder.setBanktime(Tools.getNowTime());
		morder.setPayorid("");
		morder.setIsjl(0L);// 非奖励
		morder.setDdzt(ddzt);
		morder.setZffs(zffs);
		morder.setZfusid(custom.getUsid());
		morder.setIsa(0l);
		morder.setIsb(0l);
		morder.setIsc(0l);
		morder.setIsd(0l);
		morder.setIse(0l);
		morder.setIsf(0l);
		morder.setIsg(0l);
		morder.setIsh(0l);
		morder.setIsi(0l);
		morder.setIsj(0l);
		morder.setNotea(ddzt);
		//组装TOrder
		torder.setIregionalid(1L);
		torder.setId(new TOrderId(newOrid, ticket.getIscenicid()));
		torder.setDdzt(ddzt);
		torder.setUsid(custom.getUsid());
		torder.setOrfl("02");
		torder.setIbusinessid(custom.getIbusinessid());
		torder.setSzscenicname(provider.getSzscenicname());
		torder.setScenictype(provider.getScenictype());
		torder.setDtstartdate(rzti);
		torder.setDtenddate(rzti);
		torder.setYhamnt(0.0);
		torder.setIsa(0l);
		torder.setIsb(0l);
		torder.setIsc(0l);
		torder.setIsjfjf(0L);
		torder.setIsd(0l);
		torder.setIse(0l);
		torder.setIsf(0l);
		torder.setIsg(0l);
		torder.setIsh(0l);
		torder.setIsi(0l);
		torder.setIsj(0l);
		torder.setOrnm(orderInfo.getPersonName());
		torder.setNotea(orderInfo.getDetails().get(0).get("password").toString());
		torder.setOrzj("01");
		torder.setOrhm(orderInfo.getCredentials());
		torder.setOrph("");
		//组装TOrderlist
		TOrderlist tl = new TOrderlist();
		tl.setId(new TOrderlistId(1L, newOrid,
				provider.getIscenicid()));
		tl.setIscenicid(provider.getIscenicid().toString());
		tl.setItickettypeid(ticket.getItickettypeid());
		tl.setIcrowdkindpriceid(price.getIcrowdkindpriceid());
		tl.setPric(Double.parseDouble(orderInfo.getPrice()));
		tl.setDtstartdate(rzti);
		tl.setDtenddate(ldti);
		tl.setNumb(combineOrderInfo.getNumb());
		tl.setYhamnt(0.0);
		tl.setYhnumb(new Long(0));
		tl.setIcrowdkindid(new Long(price.getIcrowdkindid()));
		tl.setSztickettypename(ticket.getSztickettypename());
		tl.setAmnt(tl.getPric() * tl.getNumb());
		tl.setIsa(0l);
		tl.setIsb(0l);
		tl.setIsc(0l);
		tl.setIsd(0l);
		tl.setIse(0l);
		tl.setIsf(0l);
		tl.setIsg(0l);
		tl.setIsh(0l);
		tl.setIsi(0l);
		tl.setIsj(0l);
		tlist.add(tl);
		//组装YOrderlist
		YOrderlist yl = new YOrderlist();
		yl.setId(new YOrderlistId(1L, newOrid,
				provider.getIscenicid()));
		yl.setIscenicid(provider.getIscenicid().toString());
		yl.setItickettypeid(ticket.getItickettypeid());
		yl.setIcrowdkindpriceid(price.getIcrowdkindpriceid());
		yl.setPric(Double.parseDouble(orderInfo.getPrice()));
		yl.setDtstartdate(rzti);
		yl.setDtenddate(ldti);
		yl.setNumb(combineOrderInfo.getNumb());
		yl.setYhamnt(0.0);
		yl.setYhnumb(new Long(0));
		yl.setIcrowdkindid(new Long(price.getIcrowdkindid()));
		yl.setSztickettypename(ticket.getSztickettypename());
		yl.setAmnt(yl.getPric() * yl.getNumb());
		yl.setIsa(0l);
		yl.setIsb(0l);
		yl.setIsc(0l);
		yl.setIsd(0l);
		yl.setIse(0l);
		yl.setIsf(0l);
		yl.setIsg(0l);
		yl.setIsh(0l);
		yl.setIsi(0l);
		yl.setIsj(0l);
		ylist.add(yl);
		torder.setYhamnt(0.0);
		torder.setMont(tl.getAmnt());
		torder.setZfmont(tl.getAmnt());
		//组装YOrder
		yorder.setIregionalid(1L);
		yorder.setId(new YOrderId(newOrid, ticket.getIscenicid()));
		yorder.setDdzt(ddzt);
		yorder.setUsid(custom.getUsid());
		yorder.setIbusinessid(custom.getIbusinessid());
		yorder.setSzscenicname(provider.getSzscenicname());
		yorder.setScenictype(provider.getScenictype());
		yorder.setDtstartdate(rzti);
		yorder.setDtenddate(rzti);
		yorder.setYhamnt(0.0);
		yorder.setIsa(0l);
		yorder.setIsb(0l);
		yorder.setIsc(0l);
		yorder.setIsd(0l);
		yorder.setIse(0l);
		yorder.setIsf(0l);
		yorder.setIsg(0l);
		yorder.setIsh(0l);
		yorder.setIsi(0l);
		yorder.setIsj(0l);
		yorder.setOrnm(orderInfo.getPersonName());
		yorder.setNotea(orderInfo.getDetails().get(0).get("password").toString());
		yorder.setOrzj("01");
		yorder.setOrhm(orderInfo.getCredentials());
		yorder.setOrph("");
		yorder.setYhamnt(0.0);
		yorder.setMont(yl.getAmnt());
		yorder.setZfmont(yl.getAmnt());
		//组装TZorderlist
		if(ticket.getBycategorytype().equals("0010")){
			List<Edmticketcomposepricetab> sonlist = cytSaleService.finSonPrice(priceId);
			for(int i = 0;i < sonlist.size(); i++){
				Edmticketcomposepricetab chai = sonlist.get(i);
				Edmtickettypetab sonTicket = (Edmtickettypetab) cytSaleService.get(Edmtickettypetab.class, chai.getItickettypeid());
				TZorderlist zorderlist = new TZorderlist();
				zorderlist.setId(new TZorderlistId(new Long(i++), tl.getId().getOrderlistid(),
						newOrid, provider.getIscenicid()));
				zorderlist.setItickettypeid(ticket.getItickettypeid());// 主票ID
				zorderlist.setIztickettypeid(chai.getItickettypeid());// 子票ID
				zorderlist.setIcrowdkindpriceid(chai.getIcrowdkindpriceid());// 票价ID
				zorderlist.setIcrowdkindid(new Long(price.getIcrowdkindid()));// 人群种类
				zorderlist.setZyhamnt(0.0);
				zorderlist.setZyhnumb(new Long(0));
				zorderlist.setDtstartdate(rzti + " " + "00:00:00");
				zorderlist.setDtenddate(Tools.getDate(rzti,
						Integer.parseInt(sonTicket.getValidityday().toString()) - 1) + " " + "23:59:59");
				zorderlist.setTripid(new Long(0));
				zorderlist.setIvenueareaid(new Long(0));
				zorderlist.setIvenueid(new Long(0));
				zorderlist.setIvenueseatsid(new Long(0));
				zorderlist.setZpric(chai.getMactualsaleprice());
				zorderlist.setZnumb(chai.getNumb() * tl.getNumb());
				zorderlist.setZamnt(chai.getMactualsaleprice() * zorderlist.getZnumb());
				zorderlist.setIsa(0l);
				zorderlist.setIsb(0l);
				zorderlist.setIsc(0l);
				zorderlist.setIsd(0l);
				zorderlist.setIse(0l);
				zorderlist.setIsf(0l);
				zorderlist.setIsg(0l);
				zorderlist.setIsh(0l);
				zorderlist.setIsi(0l);
				zorderlist.setIsj(0l);
				tzlist.add(zorderlist);
				YZorderlist yzl = new YZorderlist();
				yzl.setId(new YZorderlistId(new Long(i++), yl.getId().getOrderlistid(),
						newOrid, provider.getIscenicid()));
				yzl.setItickettypeid(ticket.getItickettypeid());// 主票ID
				yzl.setIztickettypeid(chai.getItickettypeid());// 子票ID
				yzl.setIcrowdkindpriceid(chai.getIcrowdkindpriceid());// 票价ID
				yzl.setIcrowdkindid(new Long(price.getIcrowdkindid()));// 人群种类
				yzl.setZyhamnt(0.0);
				yzl.setZyhnumb(new Long(0));
				yzl.setDtstartdate(rzti + " " + "00:00:00");
				yzl.setDtenddate(Tools.getDate(rzti,
						Integer.parseInt(sonTicket.getValidityday().toString()) - 1) + " " + "23:59:59");
				yzl.setTripid(new Long(0));
				yzl.setIvenueareaid(new Long(0));
				yzl.setIvenueid(new Long(0));
				yzl.setIvenueseatsid(new Long(0));
				yzl.setZpric(chai.getMactualsaleprice());
				yzl.setZnumb(chai.getNumb() * tl.getNumb());
				yzl.setZamnt(chai.getMactualsaleprice() * yzl.getZnumb());
				yzl.setIsa(0l);
				yzl.setIsb(0l);
				yzl.setIsc(0l);
				yzl.setIsd(0l);
				yzl.setIse(0l);
				yzl.setIsf(0l);
				yzl.setIsg(0l);
				yzl.setIsh(0l);
				yzl.setIsi(0l);
				yzl.setIsj(0l);
				yzlist.add(yzl);
			}
		}else{
			TZorderlist zorderlist = new TZorderlist();
			zorderlist.setId(new TZorderlistId(1L,
					tl.getId().getOrderlistid(), newOrid,
					provider.getIscenicid()));
			zorderlist.setItickettypeid(ticket.getItickettypeid());// 主票ID
			zorderlist.setIztickettypeid(ticket.getItickettypeid());// 子票ID
			zorderlist.setIcrowdkindpriceid(price.getIcrowdkindpriceid());// 票价ID
			zorderlist.setIcrowdkindid(price.getIcrowdkindid());// 人群种类
			zorderlist.setDtstartdate(rzti+ " " + "00:00:00");
			zorderlist.setDtenddate(ldti+ " " + "23:59:59");
			zorderlist.setTripid(new Long(0));
			zorderlist.setIvenueid(new Long(0));
			zorderlist.setIvenueareaid(new Long(0));
			zorderlist.setIvenueseatsid(new Long(0));
			zorderlist.setIsa(0l);
			zorderlist.setIsb(0l);
			zorderlist.setIsc(0l);
			zorderlist.setIsd(0l);
			zorderlist.setIse(0l);
			zorderlist.setIsf(0l);
			zorderlist.setIsg(0l);
			zorderlist.setIsh(0l);
			zorderlist.setIsi(0l);
			zorderlist.setIsj(0l);
			zorderlist.setZnumb(tl.getNumb());
			zorderlist.setZamnt(tl.getPric() * zorderlist.getZnumb());
			zorderlist.setZpric(tl.getPric());
			zorderlist.setZyhamnt(0.0);
			zorderlist.setZyhnumb(new Long(0));
			tzlist.add(zorderlist);
			YZorderlist yzl = new YZorderlist();
			yzl.setId(new YZorderlistId(1L,
					yl.getId().getOrderlistid(), newOrid,
					provider.getIscenicid()));
			yzl.setItickettypeid(ticket.getItickettypeid());// 主票ID
			yzl.setIztickettypeid(ticket.getItickettypeid());// 子票ID
			yzl.setIcrowdkindpriceid(price.getIcrowdkindpriceid());// 票价ID
			yzl.setIcrowdkindid(price.getIcrowdkindid());// 人群种类
			yzl.setDtstartdate(rzti+ " " + "00:00:00");
			yzl.setDtenddate(ldti+ " " + "23:59:59");
			yzl.setTripid(new Long(0));
			yzl.setIvenueid(new Long(0));
			yzl.setIvenueareaid(new Long(0));
			yzl.setIvenueseatsid(new Long(0));
			yzl.setIsa(0l);
			yzl.setIsb(0l);
			yzl.setIsc(0l);
			yzl.setIsd(0l);
			yzl.setIse(0l);
			yzl.setIsf(0l);
			yzl.setIsg(0l);
			yzl.setIsh(0l);
			yzl.setIsi(0l);
			yzl.setIsj(0l);
			yzl.setZnumb(yl.getNumb());
			yzl.setZamnt(yl.getPric() * yzl.getZnumb());
			yzl.setZpric(yl.getPric());
			yzl.setZyhamnt(0.0);
			yzl.setZyhnumb(new Long(0));
			yzlist.add(yzl);
		}

		morder.setTpfs("00");
		morder.setMont(torder.getMont());
		morder.setIsallcp(new Long(0));
		morder.setYhamnt(0.0);
		morder.setZfmont(torder.getZfmont());
	}
}

