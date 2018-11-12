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
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.order.service.OrderService;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.ec.order.service.iservice.IYOrderService;
import com.ectrip.sys.model.syspar.Orderlog;

@RestController
@RequestMapping(value = "yOrder")
public class YOrderController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(YOrderController.class);
	@Autowired
	private IYOrderService yOrderService;
	
	/**
	* @Title: updateTOrder  
	* @Description: TODO 修改TOrder订单信息  
	* @param @param tOrder 景区出票订单信息表
	* @param @return    参数  
	* @return Boolean    返回类型  返回是否修改成功
	* @throws
	 */
	@PostMapping(value = "/v1/updateYOrder")
	public Boolean updateYOrder(@RequestBody YOrder yOrder) {
		boolean flag = true;
		try {
			yOrderService.updateYOrder(yOrder);
		} catch (Exception e) {
			LOGGER.error("修改YOrder订单信息异常："+StringUtil.toString_02(e));
			flag = false;
		}
		return flag;
	}
}
