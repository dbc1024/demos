package com.ectrip.ec.order.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.order.service.OrderService;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.sys.model.syspar.Orderlog;

/**
* @ClassName: TOrderController  
* @Description: TODO 处理TOrder和TOrderList相关逻辑 
* @author jiyong  
* @date 2018年5月21日  
*
 */
@RestController
@RequestMapping(value = "tOrder")
public class TOrderController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TOrderController.class);
	@Autowired
	private IOrderService orderService;
	/**
	* @Title: getTOrderInfoByOridAndIscenicid  
	* @Description: TODO 根据订单id和景区服务商id查询TOrder信息  
	* @param @param orId 订单id
	* @param @param iscenicid 景区服务商id
	* @param @return    参数  
	* @return TOrder    返回类型   景区出票信息订单表
	* @throws
	 */
	@GetMapping(value = "/v1/getTOrderInfoByOridAndIscenicid")
	public TOrder getTOrderInfoByOridAndIscenicid(@RequestParam("orId")String orId,@RequestParam("iscenicid")String iscenicid) {
		TOrder tOrder = null;
		try {
			tOrder = orderService.getTOrder(orId, iscenicid);
		} catch (Exception e) {
			LOGGER.error("查询TOrder信息异常："+StringUtil.toString_02(e));
		}
		return tOrder;
	}
	
	/**
	* @Title: updateTOrder  
	* @Description: TODO 修改TOrder订单信息  
	* @param @param tOrder 景区出票订单信息表
	* @param @return    参数  
	* @return Boolean    返回类型  返回是否修改成功
	* @throws
	 */
	@PostMapping(value = "/v1/updateTOrder")
	public Boolean updateTOrder(@RequestBody TOrder tOrder) {
		boolean flag = true;
		try {
			Orderlog log = new Orderlog();
			orderService.updateTOrder(tOrder, log);
		} catch (Exception e) {
			LOGGER.error("修改TOrder订单信息异常："+StringUtil.toString_02(e));
			flag = false;
		}
		return flag;
	}
	/**
	* @Title: getTOrderListMapInfoByOridAndIscenicid  
	* @Description: TODO 根据订单id和景区服务商id查询景区订单出票明细信息  
	* @param @param orId 订单编号
	* @param @param iscenicid 景区id
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/v1/getTOrderListMapInfoByOridAndIscenicid")
	public List<Map> getTOrderListMapInfoByOridAndIscenicid(@RequestParam("orId")String orId,@RequestParam("iscenicid")String iscenicid){
		List<Map> tOrderMapList = null;
		try {
			tOrderMapList = orderService.getTOrderMapList(orId, iscenicid);
		} catch (Exception e) {
			LOGGER.error("查询TOrderList订单明细异常："+StringUtil.toString_02(e));
		}
		return tOrderMapList;
	}
	
	/**
	* @Title: getTZOrderMapList  
	* @Description: TODO 获取订单拆分信息 
	* @param @param orId 订单id
	* @param @param iscenicid 景区id
	* @param @param orderlistid 订单明细id
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/v1/getTZOrderMapList")
	public List<Map> getTZOrderMapList(@RequestParam("orId")String orId,@RequestParam("iscenicid")String iscenicid,@RequestParam("orderlistid")String orderlistid){
		List<Map> tzOrderMapList = null;
		try {
			tzOrderMapList = orderService.getTZOrderMapList(orId, iscenicid, orderlistid);
		} catch (Exception e) {
			LOGGER.error("查询TZOrderList订单拆分明细异常："+StringUtil.toString_02(e));
		}
		return tzOrderMapList;
	}
}
