package com.ectrip.ec.order.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
import com.ectrip.ec.model.cyt.CYTDto;
import com.ectrip.ec.model.order.MOrder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.order.dao.idao.ITOrderDAO;
import com.ectrip.ec.order.service.OrderService;
import com.ectrip.ec.order.service.iservice.IOrderService;

@RestController
public class OrderController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private ITOrderDAO tOrderDAO;

	@RequestMapping("/order/getTOrderlists")
	public List<TOrderlist> getTOrderlists(String orid, Long iscenicid) throws IllegalAccessException, InvocationTargetException{
		
		return orderService.getTOrderlists(orid, iscenicid);
	}
	
	@RequestMapping("/order/getTOrderlistByOrid")
	public List<TOrderlist> getTOrderlistByOrid(String orid){
		
		return orderService.getTOrderlists(orid);
	}
	
	@RequestMapping("/order/getTOrderByOrid")
	public List<Map> getTOrderByOrid(String orid){
		
		return orderService.getTOrder(orid);
	}
	

	@RequestMapping("/order/getTOrdersByOrid")
	public List<TOrder> getTOrdersByOrid(String orid){
		
		return orderService.getTOrdersByOrid(orid);
	}
	
	@GetMapping("/order/getMOrderList")
	public List getMOrderList(@RequestParam("orid")String orid){

		
		return orderService.getMOrderList(orid);
	}
	
	@RequestMapping("/order/editOrderSankeCenterl")
	public Map editOrderSankeCenterl(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid,
			String iscenicid, String stdt, String ibusiness, String usid) throws Exception{
		
		return orderService.editOrderSankeCenterl(orderlistInfo, neworderlist, orids, oldorid, iscenicid, stdt, ibusiness, usid);
	}
	/**
	* @Title: editOrderSankeCenterl  
	* @Description: TODO 散客退订接口
	* @param @param orderlistInfo 订单出票信息
	* @param @param neworderlist 新订单信息
	* @param @param orids 订单信息
	* @param @param oldorid 原订单id
	* @param @param iscenicid 景区id
	* @param @param stdt 游览日志
	* @param @param ibusiness 业务类型
	* @param @param usid 用户id
	* @param @return
	* @param @throws Exception    参数  
	* @return Map    返回类型  
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/order/editOrderSankeCenter")
	public Map editOrderSankeCenterl(@RequestParam("orderlistInfo")String orderlistInfoVal, @RequestParam(name="neworderlist",required=false)String neworderlistVal, @RequestParam("orids")String oridsVal, @RequestParam("oldorid")String oldorid,
			@RequestParam("iscenicid")String iscenicid, @RequestParam("stdt")String stdt, @RequestParam("ibusiness")String ibusiness, @RequestParam("usid")String usid) throws Exception{
		Map editOrderSankeCenter = null;
		try {
			List<TOrderlist> orderlistInfo = null;
			if(orderlistInfoVal != null && !"".equals(orderlistInfoVal)) {
				orderlistInfo =  JSON.parseArray(orderlistInfoVal, TOrderlist.class);
			}
			List<OrderPojo> neworderlist = null;
			if(neworderlistVal != null && !"".equals(neworderlistVal)) {
				neworderlist = JSON.parseArray(neworderlistVal, OrderPojo.class);
			}
			JSONArray jsonArray = (JSONArray) JSON.parseObject(oridsVal).get("orids");
			String[] orids = new String[] {jsonArray.get(0).toString(),jsonArray.get(1).toString()};
			editOrderSankeCenter = orderService.editOrderSankeCenter(orderlistInfo, neworderlist, orids, oldorid, iscenicid, stdt, ibusiness, usid);
		} catch (Exception e) {
			LOGGER.error("退订接口异常："+StringUtil.toString_02(e));
			editOrderSankeCenter = new HashMap();
			editOrderSankeCenter.put("result", false);
		}
		return editOrderSankeCenter;
	}
	
	@RequestMapping("/order/getYOrderListChildList")
	public List getYOrderListChildList(String orid, String iscenicid){
		return orderService.getYOrderListChildList(orid, iscenicid);
	}
	
	@RequestMapping("/order/getYOrderChildList")
	public List getYOrderChildList(String orid){
		return orderService.getYOrderChildList(orid);
		
	}
	
	
	@PostMapping("/order/getRealnemeList")
	public List getRealnemeList(@RequestParam("iscenicid")String iscenicid,@RequestParam("idcard")String idcard) {
		return tOrderDAO.getRealnemeList(iscenicid, idcard);
	}
	
	
	@PostMapping(value="/ec/order/getTOrderByIdcard")
	public String getTOrderByIdcard(@RequestParam("iscenicid") Long iscenicid,@RequestParam("idcard") String idcard) {
		return tOrderDAO.getOrderIdByIdcard(iscenicid, idcard);
	}
	
	
	@GetMapping(value = "/order/getMorder/")
	public MOrder getMorderInfo(@RequestParam("orid") String orid) {
		return tOrderDAO.getMorderInfo(orid);
	}
	
	@GetMapping(value = "/order/getMorderListInfo/")
	public List<MOrder> getMorderListInfo(@RequestParam("orid")String orid){
		return orderService.getMorderListInfo(orid);
	}

	/**
	* @Title: getOrderInfo  
	* @Description: TODO 根据条件查询订单信息
	* @param @param param 订单号 	手机号 	身份证号
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value="/order/v1/getOrderListInfo")
	public List<Map> getOrderListInfo(@RequestParam("param")String param,@RequestParam("msql") String msql){
		List<Map> orderListInfo = null;
		try {
			orderListInfo = orderService.getOrderListInfo(param,msql);
		}catch (Exception e) {
			LOGGER.error("查询订单信息"+StringUtil.toString_02(e));
		}
		return orderListInfo;
	}
	

	@PostMapping(value="order/findTorderValues")
	public List<TOrder> findTorderValues(@RequestParam("idcard")String idcard){
		return orderService.findTorderValues(idcard);
	}
	
	@GetMapping("/order/getTOrdersList")
	public List<TOrder> getTOrdersList(@RequestParam("orid") String orid) {
		return orderService.getTOrdersList(orid);
	}
	
	@PostMapping(value="/order/getMorder")
	public MOrder getMorder(@RequestParam("orid") String orid) {
		return orderService.getMorder(orid);
	}
	
	
	@PostMapping(value="/order/updateTorder")
	public void updateTorder(@RequestBody TOrder torder) {
		orderService.updateTOrder(torder);
	}
	
	@PostMapping("/order/getTOrderInfos")
	public List<Map<String, Object>> getTOrderInfos(@RequestParam("orid") String orid, @RequestParam("iscenicids") String iscenicids){
		return orderService.getTOrderInfos(orid, iscenicids);
	}
	
	@PostMapping("/order/getZOrderInfos")
	public List<Map<String, Object>> getZOrderInfos(@RequestParam("orid") String orid, @RequestParam("iscenicids") String iscenicids,@RequestParam("orderlistid") Long orderlistid){
		return orderService.getZOrderInfos(orid, iscenicids, orderlistid);
	}
	
	@PostMapping("/order/getSatordertabList")
	public List getSatordertabList(@RequestParam("orderId")String orderId,@RequestParam("iscenicid") String iscenicid,@RequestParam("orderlistid") String orderlistid,@RequestParam("zorderlistid") String zorderlistid) {
		return orderService.getSatordertab(orderId,  iscenicid,  orderlistid,  zorderlistid);
	}

	
	
	/**
	 * 获取torder信息
	 * @param orid
	 * @param scenicId
	 * @return
	 */
	@GetMapping("/order/getTOrderInfo")
	public TOrder getTOrderInfo(@RequestParam("orid") String orid,@RequestParam("scenicId") String scenicId) {
		TOrder tOrder = null;
		try {
			tOrder = orderService.getTOrder(orid, scenicId);
		} catch (Exception e) {
			LOGGER.error(StringUtil.toString_02(e));
		}
		return tOrder;
	}
	/**
	 * 更新订单状态
	 * @param orid
	 * @param ddzt
	 */
	@RequestMapping(value = "order/v1/updateOrderDdzt")
	public void updateOrderDdzt(@RequestParam("orid")String orid,@RequestParam("ddzt")String ddzt){
		orderService.updateOrderDdzt(orid,ddzt);
	}
	
	/**
	* @Title: getYOrderInfoById  
	* @Description: TODO 根据YOrder对象信息 
	* @param @param orId
	* @param @param iscenicid
	* @param @return    参数  
	* @return YOrder    返回类型  
	* @throws
	 */
	@GetMapping(value = "/order/v1/getYOrderInfoById")
	public YOrder getYOrderInfoById(@RequestParam("orId")String orId,@RequestParam("iscenicid")Long iscenicid) throws Exception {
		
		return orderService.getYOrderInfoById(orId, iscenicid);
	}
	
	/**
	* @Title: updateTOrder  
	* @Description: TODO 更新YOrder信息
	* @param @param tOrder
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	@PostMapping(value = "/order/v1/updateYOrder")
	public Boolean updateYOrder(@RequestBody YOrder yOrder) {
		Boolean flag = true;
		try {
			orderService.updateYOrder(yOrder);
		} catch (Exception e) {
			flag = false;
			LOGGER.error("更新YOrder信息失败");
		}
		return flag;
	}
	/**
	* @Title: updateMOrder  
	* @Description: TODO 修改MOrder主订单信息 
	* @param @param morder
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	@PostMapping(value = "/order/v1/updateMOrder")
	public Boolean updateMOrder(@RequestBody MOrder morder) {
		Boolean flag = true;
		try {
			orderService.updateMOrder(morder, null);
		} catch (Exception e) {
			flag = false;
			LOGGER.error("更新MOder信息失败："+StringUtil.toString_02(e));
		}
		return flag;
	}
	
	@RequestMapping("/order/getTOrderForListByOrid")
	public List<TOrder> getTOrderForListByOrid(@RequestParam("orid") String orid){
		List<TOrder> tOrderForListByOrid = null;
		try {
			tOrderForListByOrid = orderService.getTOrderForListByOrid(orid);
		} catch (Exception e) {
			LOGGER.error("获取订单服务商信息异常："+StringUtil.toString_02(e));
		}
		return tOrderForListByOrid;
	}
	
	@PostMapping("/order/getTorderauto")
	public List<Map> getTorderauto(@RequestParam("carno") String carno, @RequestParam("iscenicid") Long iscenicid) {
		return orderService.getTorderauto(carno, iscenicid);
	}
	
	
	@PostMapping("/order/getTzOrderList")
	public List<TZorderlist> getTzOrderList(@RequestParam("orid")String orid,@RequestParam("iscenicids") String iscenicids){
		return orderService.getTzOrderList(orid, iscenicids);
	}

	@PostMapping("/order/getTzOrderLists")
	public List<Map> getTzOrderLists(@RequestParam("orid")String orid,@RequestParam("iscenicids") String iscenicids){
		return orderService.getTzOrderLists(orid, iscenicids);
	}
	
	@PostMapping("/order/updateT_order")
	public ResultBean updateT_order(@RequestParam("orid")String orid, @RequestParam("iscenicid")Long iscenicid,
			@RequestParam("iemployeeid") Long iemployeeid,@RequestParam("mont") Double mont) {
		return orderService.updateT_order(orid, iscenicid, iemployeeid, mont);
	}
}
