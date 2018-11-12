package com.ectrip.ticket.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.ticket.provider.service.ICrowdKindService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("crowdKind")
@Api(tags = "人群种类相关接口")
public class CrowdKindController extends BaseController{
	
	@Autowired
	private ICrowdKindService crowdkindService;

	@GetMapping(value = "v1/crowdKindListByIds")
	@ApiOperation(value = "根据票种id集合获取人群种类信息(供其他服务调用)")
	public List crowdKindListByIds(@ApiParam("icrowdkindids数据格式[1,2,3,4]") @RequestParam String icrowdkindids) {
		
		return crowdkindService.getCrowdKindListByIds(icrowdkindids);
	}
}
