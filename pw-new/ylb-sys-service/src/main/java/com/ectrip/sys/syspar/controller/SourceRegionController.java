package com.ectrip.sys.syspar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.syspar.service.ISourceRegionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "sourceRegion")
@Api(tags = "系统模块-客源地相关接口")
public class SourceRegionController {

	@Autowired
	private ISourceRegionService sourceregegionService;
	
	@GetMapping(value = "v1/getLowerRegion")
	@ApiOperation(value = "根据客源地id获取其下一级所有客源地信息")
	public ResponseBean sourceRegionJson(@RequestParam Long iregionalid){
		
		List jsonlist=sourceregegionService.SourceRegionJson(iregionalid);
		return new ResponseBean(200,"请求成功",jsonlist);
	}
	
	@GetMapping(value = "v1/getSourceregionById")
	@ApiOperation(value = "根据客源地id获取客源地信息")
	public Galsourceregiontab getSourceregionById(@RequestParam("iregionalid") Long iregionalid) {
		return (Galsourceregiontab) sourceregegionService.get(Galsourceregiontab.class, iregionalid);
		
	}
	
}
