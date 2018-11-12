package com.ectrip.ec.custom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ec.custom.service.iservice.ICustomRealNameService;
import com.ectrip.ec.model.user.CustomRealName;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "customRealName")
@Api(tags = "用户实名信息相关接口")
public class CustomRealNameController {
	
	@Autowired
	private ICustomRealNameService customRealNameService;
	
	@RequestMapping("/get")
	public CustomRealName get(String id){
		return customRealNameService.get(id);
	}
		
	@RequestMapping("/oneTimePatchUserBank")
    public void oneTimePatchUserBank(){
    	customRealNameService.oneTimePatchUserBank();
    }
	
	//userIds数据形式userIds="12,541,625,51"
	@GetMapping(value = "v1/list")
	@ApiOperation(value = "根据实名用户ID集合获取实名信息列表(供其他服务调用)")
	public List getRealByUserIds(@ApiParam("userIds数据格式[12,541,625,51]") @RequestParam String userIds){
		return customRealNameService.getRealByUserIds(userIds);
	}

}
