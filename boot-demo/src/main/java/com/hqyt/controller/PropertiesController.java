/**
 * 
 */
package com.hqyt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqyt.properties.ConstantAli;
import com.hqyt.properties.ConstantHqyt;
import com.hqyt.properties.ConstantWX;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* @Description: TODO
* @Author: CSZ
* @Date: 2018-08-27 09:25:19
*/
@Api(tags="配置文件属性注入相关")
@RestController
@RequestMapping("properties")
public class PropertiesController {
	
	@Autowired
    private ConstantAli constantAli;
	
	@ApiOperation("支付宝配置信息")
	@GetMapping(value = "/ali")
    public Object ali() {
		Map<String, Object> ali = new HashMap<>();
		ali.put("payUrl", constantAli.getPayUrl());
		ali.put("notityUrl", constantAli.getNotityUrl());
		
		
		return ali;
	}
	

	@ApiOperation("微信配置信息")
	@GetMapping(value = "/wx")
    public Object wx() {
		Map<String, Object> wx = new HashMap<>();
		wx.put("payUrl", ConstantWX.payUrl);
		wx.put("notityUrl", ConstantWX.notityUrl);
		
		
		return wx;
	}
	
	
	@ApiOperation("环球雅图配置信息")
	@GetMapping(value = "/hqyt")
    public Object hqyt() {
		Map<String, Object> hqyt = new HashMap<>();
		hqyt.put("payUrl", ConstantHqyt.payUrl);
		hqyt.put("notityUrl", ConstantHqyt.notityUrl);
		hqyt.put("password", ConstantHqyt.PASSWORD);
		
		
		return hqyt;
	}
}
