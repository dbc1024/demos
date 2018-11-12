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
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.order.service.OrderService;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.ec.order.service.iservice.ITZOrderListService;
import com.ectrip.ec.order.service.iservice.IYOrderService;
import com.ectrip.sys.model.syspar.Orderlog;

@RestController
@RequestMapping(value = "tZOrderList")
public class TZOrderListController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TZOrderListController.class);
	@Autowired
	private ITZOrderListService tZOrderListService;
	/**
	 * 获取TZorderlist信息
	 * @param orderlistid
	 * @param orid
	 * @param iscenicid
	 * @return
	 */
	@GetMapping(value = "/v1/updateYOrder")
	public List<TZorderlist> getTZorderlist(@RequestParam("orderlistid") Long orderlistid,@RequestParam("orid")String orid,@RequestParam("iscenicid")Long iscenicid) {
		return tZOrderListService.getTZorderlist(orderlistid,orid,iscenicid);
	}
	
}
