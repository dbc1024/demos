package com.ectrip.ec.order.controller;

import java.util.List;

import javax.persistence.Entity;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.LOrderId;
import com.ectrip.ec.model.order.LOrderlist;
import com.ectrip.ec.order.service.iservice.ILOrderService;
import com.ectrip.shiro.ResponseBean;

@RestController
@RequestMapping(value="lOrder")
public class LOrderController {
	@Autowired
	private ILOrderService lOrderService;
	/**
	 * 保存Lorder订单
	 * @param lorder
	 * @return
	 */
	@PutMapping(value="v1/saveLorder")
	public void saveLorder(@RequestBody LOrder lorder) {
		lOrderService.saveLorder(lorder);
	}
	/**
	 * 根据orid获取LOrder信息
	 * @param lorder
	 * @return
	 */
	@GetMapping(value="v1/getLorderByOrid")
	public List<LOrder> getLorderByOrid(@RequestParam String orid) {
		return lOrderService.getLorderByOrid(orid);
	}
	
	/**
	 * 根据id获取LOrder信息
	 * @param lorder
	 * @return
	 */
	@PostMapping(value="v1/getLorderByLOrderId")
	public Object getLorderByLOrderId(@RequestBody LOrderId lorderId) {
		return lOrderService.getLorderByLOrderId(lorderId);
	}
	/**
	 * 更新LOrder实体
	 * @param lorder
	 */
	@PostMapping(value="v1/updateLOrderId")
	public void updateLOrder(@RequestBody LOrder lorder) {
		lOrderService.updateLOrder(lorder);
	}
	
}
