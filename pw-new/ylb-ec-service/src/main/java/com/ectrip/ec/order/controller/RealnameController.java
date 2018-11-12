package com.ectrip.ec.order.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.order.service.iservice.IRealnameService;
import com.ectrip.shiro.ResponseBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("realname")
@Api(tags = "实名制管理相关接口")
public class RealnameController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(RealnameController.class);

	@Autowired
	private IRealnameService realnameService;
	
	
	/**
     * 订单查询实名制信息列表
     */
    @GetMapping("v1/listByOrid")
    @ApiOperation(value = "订单查询实名制信息列表接口")
	public ResponseBean realnameListByOrid(@RequestParam String orid, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
    	
    	if(null==pageSize) {pageSize = PAGE_SIZE;}
		if(null==page) {page = 1;}
		
		PaginationSupport ps = null;
		try {
			ps = realnameService.realnameList(orid, "", pageSize, page, null);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("订单查询实名制信息异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "订单查询实名制信息异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
	}
	
}
