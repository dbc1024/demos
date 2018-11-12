package com.ectrip.ticket.salesmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospsaleslimitstab;
import com.ectrip.ticket.salesmanager.service.ISaleLimitsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("employeesalelimits")
@Api(tags ="票务管理-票务销售设置-员工销售权限管理相关接口")
public class SaleLimitsController extends BaseController {
	@Autowired
	private ISaleLimitsService saleLimitsService;
	
	@GetMapping(value="v1/saleLimitView")
	@ApiOperation(value="查看销售权限列表,根据员工id或服务商id查询")
	public ResponseBean saleLimitView(Long icompanyinfoid,Long iemployeeid,@RequestParam(required=true) int page,@RequestParam(required=true) int  pageSize) {
		ResponseBean responseBean=new ResponseBean();
		int code=200;
		String msg="请求成功";
		PaginationSupport saleLimitsList = null;
		try {
			saleLimitsList = saleLimitsService.getSaleLimitsList(icompanyinfoid,iemployeeid, page, pageSize, null);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(saleLimitsList);
		return responseBean;
	}
	
	
	@GetMapping(value="v1/querySaleLimitsList")
	@ApiOperation(value="根据销售权限编号查看销售权限")
	public ResponseBean querySaleLimitsList(Long isalelimitsId) {
		ResponseBean responseBean=new ResponseBean();
		int code=200;
		String msg="请求成功";
		List querySaleLimitsList = null;
		try {
			querySaleLimitsList = saleLimitsService.querySaleLimitsList(isalelimitsId);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(querySaleLimitsList);
		return responseBean;
	}
	
	@PostMapping(value="v1/insertSalelimit")
	@ApiOperation(value="增加销售权限")
	public ResponseBean insertSalelimit(Long iemployeeId,@RequestParam("多个id以逗号隔开")String icrowdkindpriceids) {
		ResponseBean responseBean=new ResponseBean();
		Syslog syslog = new Syslog();
		int code=200;
		String msg="请求成功";
		List querySaleLimitsList = null;
		try {
			saleLimitsService.insertSalelimit(iemployeeId,icrowdkindpriceids,syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(querySaleLimitsList);
		return responseBean;
	}
	
	@PutMapping(value="v1/updateSalelimit")
	@ApiOperation(value="修改销售权限")
	public ResponseBean updateSalelimit(Long iemployeeId,@RequestParam("多个id以逗号隔开")String icrowdkindpriceids) {
		ResponseBean responseBean=new ResponseBean();
		Syslog syslog = new Syslog();
		int code=200;
		String msg="请求成功";
		List querySaleLimitsList = null;
		try {
			saleLimitsService.updateSalelimit(iemployeeId,icrowdkindpriceids,syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(querySaleLimitsList);
		return responseBean;
	}
	
	@PutMapping(value="v1/deleteSalelimit")
	@ApiOperation(value="删除销售权限")
	public ResponseBean deleteSalelimit(Long iemployeeId,Long icrowdkindpriceid) {
		ResponseBean responseBean=new ResponseBean();
		Syslog syslog = new Syslog();
		int code=200;
		String msg="请求成功";
		List querySaleLimitsList = null;
		try {
			saleLimitsService.deleteSalelimit(iemployeeId, icrowdkindpriceid,syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(querySaleLimitsList);
		return responseBean;
	}
}
