package com.ectrip.sys.syspar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.syspar.Customlog;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.service.ISyslogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "syslog")
@Api(tags = "系统模块-系统日志相关接口")
public class SyslogController {

	@Autowired
	private ISyslogService syslogService;
	
	
	/**
	 * 
	 * Describe:添加前台用户操作日志
	 * @auth:lijingrui
	 * @param customlog
	 * return:void
	 * Date:2011-10-6
	 */
	@RequestMapping("insertTomlog")
	public void insertTomlog(@RequestBody Customlog customlog) {
		syslogService.insertTomlog(customlog);
	}
	
	/**
	 * 
	 * Describe:添加后台操作日志
	 * @auth:lijingrui
	 * @param syslog
	 * return:void
	 * Date:2011-10-6
	 */
	@PostMapping(value = "v1/insertSyslog")
	@ApiOperation(value = "新增系统日志")
	public ResponseBean insertSyslog(@RequestBody Syslog syslog) {
		
		syslogService.insertSyslog(syslog);
		return new ResponseBean(400, "保存成功");
	}
	
	/**
	 * 
	 * Describe:添加订单操作日志
	 * @auth:lijingrui
	 * @param orderlog
	 * return:void
	 * Date:2011-10-6
	 */
	@PostMapping("/insertOrderlog")
	public void insertOrderlog(@RequestBody Orderlog orderlog) {
		syslogService.insertOrderlog(orderlog);
	}
}
