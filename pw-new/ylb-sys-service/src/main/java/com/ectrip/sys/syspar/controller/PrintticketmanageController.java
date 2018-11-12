package com.ectrip.sys.syspar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.sys.syspar.service.IPrinttickemanageService;

import io.swagger.annotations.Api;
@RestController
@RequestMapping(value = "printticket")
@Api(tags = "系统模块-门票打印设置")
public class PrintticketmanageController extends BaseController{
	
	@Autowired
	private IPrinttickemanageService printticketmanageService;
	
	/**
	* @Title: getPrintticketmanageList  
	* @Description: TODO 根据景区服务商Id和业务Id查询打印设置信息
	* @param @param scenicId 景区服务商id
	* @param @param ibusinessId 业务Id
	* @param @return    参数  
	* @return List<?>    返回类型  
	* @throws
	 */
	@GetMapping(value = "v1/getPrintticketmanageList")
	public List<?> getPrintticketmanageList(@RequestParam(name="scenicId") Long scenicId,@RequestParam(name="ibusinessId")Long ibusinessId){
		List<?> queryPrintList = printticketmanageService.queryPrintListByScenicIdAndIbusinessId(scenicId, ibusinessId);
		return queryPrintList;
	}
}
