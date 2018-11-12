package com.ectrip.ticket.provider.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.ticket.provider.service.IBusinessService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("ordercs")
@Api(tags = "系统管理-景区服务商管理-业务类型管理")
public class BusinessController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(BusinessController.class);
	@Autowired
	private IBusinessService businessService;

	@GetMapping(value = "v1/businessSelect")
	@ApiOperation(value = "业务类型下拉列表")
    public ResponseBean businessSelect() {
		try {
			
			List businessList = businessService.businessList();
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", businessList);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("业务类型下拉列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "业务类型下拉列表查询异常", "异常信息："+ e.getMessage());
		}
		
	}
}
