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
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.order.service.OrderService;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.ec.order.service.iservice.ITOrderListService;
import com.ectrip.ec.order.service.iservice.IYOrderService;
import com.ectrip.sys.model.syspar.Orderlog;

@RestController
@RequestMapping(value = "tOrderList")
public class TOrderListController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TOrderListController.class);
	@Autowired
	private ITOrderListService tOrderListService;
	
	/**
	 * 更新TOrderList信息
	 * @param tOrderList
	 * @return
	 */
	@PostMapping(value = "/v1/updateTOrderList")
	public Boolean updateTOrderList(@RequestBody TOrderlist tOrderList) {
		boolean flag = true;
		try {
			tOrderListService.updateTOrderList(tOrderList);
		} catch (Exception e) {
			LOGGER.error("修改YOrder订单信息异常："+StringUtil.toString_02(e));
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 查找TOrderList
	 * @param tOrderList
	 * @return
	 */
	@GetMapping(value = "/v1/findTOrderList")
	public List<TOrderlist> findTOrderList(@RequestParam("orid") String orid,@RequestParam("iscenicid") Long iscenicid) {
		try {
			return tOrderListService.findTOrderList(orid,iscenicid);
		} catch (Exception e) {
			LOGGER.info("查找TOrderList订单信息异常："+StringUtil.toString_02(e));
			e.printStackTrace();
			return null;
		}
	}
}
