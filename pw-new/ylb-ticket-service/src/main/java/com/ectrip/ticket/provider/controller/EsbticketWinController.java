package com.ectrip.ticket.provider.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.provider.service.IEsbticketWinService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags="票务管理-售票窗口接口")
@RequestMapping(value="ticketWin")
public class EsbticketWinController {
	
	private static final Logger LOGGER = LogManager.getLogger(EsbticketWinController.class);

	@Autowired
	private IEsbticketWinService esbticketWinService;
	@Autowired
	private SysService sysService;
	
	@GetMapping(value="/v1/getlistTicketWin")
	@ApiOperation(value="显示所有的售票窗口信息  根据窗口名称,服务商id查询")
	public ResponseBean getlistTicketWin(String szticketwinname,@ApiParam("多个id以逗号分隔")@RequestParam(required=false) String scenics,@RequestParam(required=true)int pageSize,@RequestParam(required=true) int startIndex) {
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport data = null;
		try {
			data = esbticketWinService.getlistTicketWin(szticketwinname, scenics, pageSize, startIndex, null);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("显示所有的售票窗口信息接口异常",e.getCause());
			code = 400;
			msg = "请求异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(data);
		return responseBean;
	}
	
	@PostMapping(value="/v1/insertTicketWin")
	@ApiOperation(value="新增售票窗口")
	public ResponseBean insertTicketWin(@RequestBody Esbticketwintab ticketwin) {
		Esfemployeetab currentUser = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(currentUser.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			 esbticketWinService.insertTicketWin(ticketwin,syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	
	@PutMapping(value="/v1/updateTicketWin")
	@ApiOperation(value="修改售票窗口")
	public ResponseBean updateTicketWin(@RequestBody Esbticketwintab ticketwin) {
		Esfemployeetab currentUser = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(currentUser.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			 esbticketWinService.updateTicketWin(ticketwin, syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	
	
	@DeleteMapping(value="/v1/deleteTicketWin")
	@ApiOperation(value="删除售票窗口")
	public ResponseBean deleteTicketWin(@RequestBody Esbticketwintab ticketwin) {
		Esfemployeetab currentUser = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(currentUser.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			esbticketWinService.deleteTicketWin(ticketwin, syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	
	@GetMapping(value="/v1/getEsbticketWinByIticketWinId")
	@ApiOperation(value="根据售票窗口ID查看售票窗口信息")
	public ResponseBean getEsbticketWinByIticketWinId(Long ticketwinId) {
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		Esbticketwintab ticketWin = null;
		try {
			ticketWin = esbticketWinService.getviewEsbticketWin(ticketwinId);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(ticketWin);
		return responseBean;
	}
	
	
	@GetMapping(value="/v1/getEsbticketWinByIscenicid")
	@ApiOperation(value="根据服务商ID查看售票窗口信息")
	public ResponseBean getEsbticketWinByIscenicid(@ApiParam("多个id以逗号分隔")@RequestParam String iscenicid) {
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		List<Esbticketwintab> Listesbticketwin  = null;
		try {
			Listesbticketwin = esbticketWinService.findListesbticket(iscenicid);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(Listesbticketwin);
		return responseBean;
	}
 
	@GetMapping(value="/v1/getWinPermission")
	@ApiOperation(value="获取销售权限列表 根据窗口Id查询权限")
	public ResponseBean getWinPermission(Long iticketWinId,@RequestParam(required=true) int starIndex,@RequestParam(required=true)int pageSize) { 
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport winPermissionList =null;
		try {
			winPermissionList= esbticketWinService.getWinPermissionByIticketWinId(iticketWinId, starIndex,pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(winPermissionList);
		return responseBean;
	}
	
	//销售权限相关操作=============================================
	
	@PostMapping(value="/v1/insertTicketWinLimits")
	@ApiOperation(value="新增窗口销售权限")
	public ResponseBean insertTicketWinLimits(@RequestParam(required=true) Long Iticketwinid,@RequestParam @ApiParam("价格id,多个以\",\"分隔") String Icrowdkindpriceids) { 
		Esfemployeetab currentUser = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(currentUser.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			esbticketWinService.addWinPermission(Iticketwinid,Icrowdkindpriceids,syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	/*
	 * 修改窗口销售权限
	 */
	@PutMapping(value="/v1/updateTicketWinLimits")
	@ApiOperation(value="修改窗口销售权限")
	public ResponseBean updateTicketWinLimits(@RequestParam(required=true) Long Iticketwinid,@RequestParam @ApiParam("价格id,多个以\",\"分隔") String Icrowdkindpriceids) { 
		Esfemployeetab currentUser = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(currentUser.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			esbticketWinService.updateTicketWinLimits(Iticketwinid,Icrowdkindpriceids,syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	/*
	 * 删除窗口销售权限
	 */
	@DeleteMapping(value="/v1/deleteTicketWinLimits")
	@ApiOperation(value="通过主键删除窗口销售权限")
	public ResponseBean deleteTicketWinLimits(@RequestParam @ApiParam("窗口权限表主键id")  Long iticketwinlimitsId) { 
		Esfemployeetab currentUser = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(currentUser.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			esbticketWinService.deleteTicketWinLimits(iticketwinlimitsId,syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
}
