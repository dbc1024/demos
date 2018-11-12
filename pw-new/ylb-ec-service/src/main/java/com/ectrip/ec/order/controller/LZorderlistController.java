package com.ectrip.ec.order.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.model.order.LZorderlist;
import com.ectrip.ec.order.service.iservice.ILZorderlistService;
import com.ectrip.ec.order.service.iservice.IOrderService;
@RestController
@RequestMapping(value="lzorderlist")
public class LZorderlistController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LZorderlistController.class);
	@Autowired
	private ILZorderlistService lZorderlistService;
	
	@Autowired
	private IOrderService orderService;

	/**
	 * 根据参数获取LZorderlist表信息
	 * @param orid
	 * @param iscenicid
	 * @param orderlistid
	 * @return
	 */
	@GetMapping(value="v1/getLZorderlistByParam")
	public List getLZorderlistByParam(@RequestParam String orid,@RequestParam Long iscenicid,@RequestParam Long orderlistid) {
		return lZorderlistService.getLZorderlistByParam(orid,iscenicid,orderlistid);
	}
	
	/**
	 * 保存LZorderlist实例
	 * @param orid
	 * @param iscenicid
	 * @param orderlistid
	 * @return
	 */
	@PutMapping(value="v1/saveLZorderlist")
	public void saveLZorderlist(@RequestBody LZorderlist lzorderlist) {
		lZorderlistService.saveLZorderlist(lzorderlist);
	}
	
	/**
	* @Title: getTZOrderMapListByOrIdAndIscenicid  
	* @Description: TODO 查询网上订单出票明细信息  
	* @param @param orId
	* @param @param iscenicid
	* @param @return    参数  
	* @return List<Map>    返回类型  
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/v1/getTZOrderMapListByOrIdAndIscenicid")
	public List<Map> getTZOrderMapListByOrIdAndIscenicid(@RequestParam("orId")String orId,@RequestParam("iscenicid")String iscenicid){
		List<Map> tzOrderMapListByOrIdAndIscenicid = null;
		try {
			tzOrderMapListByOrIdAndIscenicid = orderService.getTZOrderMapListByOrIdAndIscenicid(orId, iscenicid);
		} catch (Exception e) {
			LOGGER.error("查询网上订单出票明细信息表："+StringUtil.toString_02(e));
		}
		return tzOrderMapListByOrIdAndIscenicid;
	}
}
