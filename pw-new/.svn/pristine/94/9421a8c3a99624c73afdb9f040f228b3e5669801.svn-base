package com.ectrip.ec.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.model.order.LOrderlist;
import com.ectrip.ec.order.service.iservice.ILorderListService;
@RestController
@RequestMapping(value="lorderList")
public class LOrderListController {
	@Autowired
	private ILorderListService lorderListService;
	/**
	 * 保存LorderList订单
	 * @param lorder
	 * @return
	 */
	@PutMapping(value="v1/saveLorderList")
	public void saveLorderList(@RequestBody LOrderlist lorderList) {
		lorderListService.saveLorderList(lorderList);
	}
	
	/**
	 * 根据ids获取LorderList订单
	 * @param lorder
	 * @return
	 */
	@GetMapping(value="v1/getLOrderlistByids")
	public List<LOrderlist> getLOrderlistByids(@RequestParam String orid,@RequestParam Long iscenicid) {
		return lorderListService.getLOrderlistByids(orid,iscenicid);
	}
}
