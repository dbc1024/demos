package com.ectrip.tourcard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.tourcard.model.CardQuery;
import com.ectrip.tourcard.model.TourcardBindUserQuery;
import com.ectrip.tourcard.service.ITourcardBindUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "tourcardBindUser")
@Api(tags = "旅游卡绑定用户信息相关接口")
public class TourcardBindUserController {
	private static final Logger LOGGER = LogManager.getLogger(TourcardBindUserController.class);
	
	@Autowired
	private ITourcardBindUserService tourcardBindUserService;

	
	@PostMapping(value = "v1/list")
	@ApiOperation(value = "旅游卡绑定用户信息列表查询")
	public ResponseBean getList(@RequestBody(required=false) TourcardBindUserQuery query, @RequestParam(required=false) Integer pageSize,@RequestParam(required=false) Integer page){
		if (pageSize == null) {
			pageSize = 20;
		}
		if (page == null) {
			page = 1;
		}
		
		PaginationSupport ps = tourcardBindUserService.showlistTourcardBindUser(pageSize,page,null,query);
		
		return new ResponseBean(200, "操作成功", ps);
	}
	
	@GetMapping(value = "v1/get/{userId}")
	@ApiOperation(value = "根据userId获取旅游卡绑定用户信息")
	public ResponseBean seachTourBindUserByCondition(@PathVariable String userId){
		Map<String,Object> result = new HashMap<>();
		
		Map<Object,Object> userData;
		List tourCardDetails;
		
		try {
			//查询用户信息
			List findTourcardBindUserInfo = tourcardBindUserService.findTourcardBindUserInfo(userId);
			if(findTourcardBindUserInfo != null && findTourcardBindUserInfo.size() > 0) {
				userData = (Map<Object, Object>) findTourcardBindUserInfo.get(0);
				if(userData != null && StringUtil.isNotEmpty((String)userData.get("IDENTITYNUM"))){
					userData.put("IDENTITYNUM", StringUtil.markIdNumber(DesEncryptUtil.decrypt((String)userData.get("IDENTITYNUM"))));
				}
				
				result.put("userData", userData);
			}
			
			
			//查询旅游卡信息信息
			tourCardDetails = tourcardBindUserService.findTourcardDetailInfoByUserId(userId);
			result.put("tourCardDetails", tourCardDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取旅游卡绑定用户信息异常："+e.getMessage());
			return new ResponseBean(400, "获取旅游卡绑定用户信息异常", "异常信息:"+e.getMessage());
		}
		
		
		return new ResponseBean(200, "操作成功", result);
	}
}
