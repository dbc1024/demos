package com.ectrip.sys.message.controller;

import java.security.Principal;

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

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.employee.service.IEsfEmployeeTabService;
import com.ectrip.sys.message.service.iservice.IWebinfotabService;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Webinfotab;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("webinfo")
@Api(tags = "电商管理-监管督查-站内短信公告")
public class WebinfotabController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(WebinfotabController.class);
	

	@Autowired
	private IEsfEmployeeTabService employeeService;
	@Autowired
	private IWebinfotabService webinfoService;
	
	@GetMapping("v1/list")
	@ApiOperation("站内短信公告列表查询")
	public ResponseBean showAllwebInfo(@RequestParam(required=false) Integer flag, @RequestParam(required=false) String employeeid,
			@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
		
		if(null==pageSize) {pageSize = PAGE_SIZE;}
		if(null==page) {page = 1;}
		if(null==flag) {flag = 0;}
		
		try {
			
			PaginationSupport ps = webinfoService.showListwebinfos(flag, employeeid, pageSize, page, null);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("站内短信公告列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "站内短信公告列表查询异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	
	@GetMapping("v1/detail/{seq}")
	@ApiOperation("站内短信公告明细")
	public ResponseBean webInfoDetail(@PathVariable Long seq, Principal user) {
		
		try {
			
			Esfemployeetab employeeInfo = employeeService.getEmployeeInfoByEmpId(user.getName());
			
			Webinfotab webinfo = webinfoService.viewWebinfotab(seq);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", webinfo);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("站内短信公告明细查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "站内短信公告明细查询异常", "异常信息："+ e.getMessage());
		}
	}
	
	@PostMapping("v1/add")
	@ApiOperation("新增站内短信公告")	
	public ResponseBean addWebInfo(@RequestBody Webinfotab webinfo) {
		try {
			Syslog syslog = new Syslog();
			webinfoService.addWebinfotab(webinfo, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("新增站内短信公告异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增站内短信公告异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功");
		
	}
	
	@PostMapping("v1/update")
	@ApiOperation("修改站内短信公告")	
	public ResponseBean updateWebInfo(@RequestBody Webinfotab webinfo) {
		try {
			Syslog syslog = new Syslog();
			webinfoService.updateWebinfotab(webinfo, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("修改站内短信公告异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "修改站内短信公告异常", "异常信息："+ e.getMessage());
		}
		
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功");
	}
	
	
	@GetMapping("v1/delete/{seq}")
	@ApiOperation("删除站内短信公告")
	public ResponseBean deleteWebInfo(@PathVariable Long seq) {
		
		try {
			Syslog syslog = new Syslog();
			Webinfotab webinfo = new Webinfotab();
			webinfo.setSeq(seq);
			
			webinfoService.delWebinfotab(webinfo, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("删除站内短信公告异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "删除站内短信公告异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功");
	}
	
}
