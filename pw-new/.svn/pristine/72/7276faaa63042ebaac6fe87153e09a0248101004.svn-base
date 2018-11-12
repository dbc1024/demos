package com.ectrip.ec.custom.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.custom.service.iservice.ICustomService;
import com.ectrip.ec.model.user.Custom;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "ec")
@Api(tags = "用户信息相关接口")
public class CustomController {
	private static final Logger LOGGER = LogManager.getLogger(CustomController.class);
	@Autowired
	private ICustomService customService;

	@GetMapping("/custom/findByMobile")
	public Custom findByMobile(@RequestParam("mobile") String mobile){
		
		return customService.findByMobile(mobile);
	}
	
	@RequestMapping("/custom/anonymityUserCreate")
	public Custom anonymityUserCreate(@RequestParam("createType") String createType,@RequestParam("value")String value){
		
		return customService.anonymityUserCreate(createType, value);
	}
	
	//userIds数据形式userIds="12,541,625,51"
	@GetMapping(value = "/custom/v1/list")
	@ApiOperation(value = "根据用户ID集合获取用户信息列表")
	public List getCustomsByUserIds(@ApiParam("userIds数据格式[12,541,625,51]") @RequestParam String userIds) {
		return customService.getCustomsByUserIds(userIds);
	}
	
	/**
	 * 根据用户已经存在的信息，查询用户信息
	 * @return
	 */
	@PostMapping(value = "/custom/v1/getCustomByCondition")
	@ApiOperation(value = "根据用户已经存在的信息，查询用户信息")
	public List<?> getCustomByCondition(@RequestBody Custom custom){
		List<?> customList = null;
		try {
			customList = customService.queryCustomByCondition(custom);
		} catch (Exception e) {
			LOGGER.error("动态条件获取用户信息接口异常，具体异常为："+StringUtil.toString_02(e));
		}
		return customList;
	}
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 */
	@GetMapping("/custom/getCustomByUserId")
	@ApiOperation(value = "根据用户Id获取电商用户信息")
	public Custom getCustomByUserId(@RequestParam("userId")String userId) {
		Custom custom = null;
		try {
			custom = (Custom) customService.get(Custom.class, userId);
		} catch (Exception e) {
			LOGGER.error("根据用户Id获取电商用户信息，具体异常为："+StringUtil.toString_02(e));
		}
		return custom;
	}
	
	/**
	 * 根据对象删除
	 * @param userIds
	 * @return
	 */
	@DeleteMapping(value = "v1/deleteCustom")
	public void deleteCustom(@RequestBody Custom custom) {
		customService.delete(custom);
	}
	
	/**
	 * 更新Custom
	 * @param userIds
	 * @return
	 */
	@PostMapping(value = "v1/updateCustom")
	public void updateCustom(@RequestBody Custom custom) {
		customService.updateCustom(custom);
	}
}
