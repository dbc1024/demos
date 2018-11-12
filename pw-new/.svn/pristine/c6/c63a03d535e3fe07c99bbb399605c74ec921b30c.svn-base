package com.ectrip.ticket.provider.controller;

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
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.provider.service.IEsbticketStationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@RestController
@RequestMapping(value="esbticketStation")
@Api(tags="票务管理-售票站点接口")
public class EsbticketStationController {
	private static final Logger LOGGER = LogManager.getLogger(EsbticketStationController.class);
	@Autowired
	private IEsbticketStationService esbticketStationService;
	
	@Autowired
	private SysService sysService;
	
	@GetMapping(value="/v1/getlistEsbticket")
	@ApiOperation(value="显示所有的门票站点信息 ，并根据名称查询")
	public ResponseBean getlistEsbticket(String szstationname,@ApiParam("实际传入服务商id")@RequestParam(required=false) String scenics,@RequestParam(required=true) int pageSize,@RequestParam(required=true) int startIndex) {
		ResponseBean responseBean = new ResponseBean();
		int code=200;
		String msg="请求成功";
		PaginationSupport esbticketList = null;
		Syslog syslog=new Syslog();
		Esfemployeetab currentUser = sysService.currentUser();
		syslog.setIemployeeid(currentUser.getIemployeeid());
		try {
			esbticketList = esbticketStationService.getlistEsbticket(szstationname, scenics, pageSize, startIndex,null);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求失败";
		}
		syslog.setNote("get");
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(esbticketList);
		return responseBean;
	}
	
	@PostMapping(value="/v1/insertEsbticket")
	@ApiOperation(value="添加门票站点")
	public ResponseBean insertEsbticket(@RequestBody Esbticketstationtab esbticket) {
		Esfemployeetab esfemployeetab = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code=200;
		String msg="请求成功";
		try {
			esbticketStationService.insertEsbticket(esbticket, syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求失败";
		}
		syslog.setNote("insert");
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	
	@PutMapping(value="/v1/updateEsbticket")
	@ApiOperation(value="修改门票站点")
	public ResponseBean updateEsbticket(@RequestBody Esbticketstationtab esbticket) {
		Esfemployeetab esfemployeetab = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code=200;
		String msg="请求成功";
		try {
			esbticketStationService.updateEsbticket(esbticket, syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	
	@DeleteMapping(value="/v1/deleteEsbticket")
	@ApiOperation(value="删除门票站点")
	public ResponseBean deleteEsbticket(@RequestBody Esbticketstationtab esbticket) {
		Esfemployeetab esfemployeetab = sysService.currentUser();
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		ResponseBean responseBean = new ResponseBean();
		int code=200;
		String msg="请求成功";
		try {
			esbticketStationService.deleteEsbticket(esbticket, syslog);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		return responseBean;
	}
	
	@GetMapping(value="/v1/getviewEsbticketById")
	@ApiOperation(value="根据Id查看门票站点")
	public ResponseBean getviewEsbticketById(Long iticketstationid) {
		Esbticketstationtab viewEsbticket = null;
		ResponseBean responseBean = new ResponseBean();
		int code=200;
		String msg="请求成功";
		try {
			viewEsbticket = esbticketStationService.getviewEsbticket(iticketstationid);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(viewEsbticket);
		return responseBean;
	}
	
	@GetMapping(value="/v1/findListesbticket")
	@ApiOperation(value="显示服务商信息")
	public ResponseBean findListesbticket(String scenictype,String isonlyjq) {
		Esfemployeetab currentUser = sysService.currentUser();
		ResponseBean responseBean = new ResponseBean();
		int code=200;
		String msg="请求成功";
		List listesbticket = null;
		try {
			listesbticket = esbticketStationService.findListesbticket(currentUser, scenictype, 0, isonlyjq);
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(listesbticket);
		return responseBean;
	}
	
	@GetMapping(value="/v1/showAllesbticket")
	@ApiOperation(value="显示所有售票站点信息")
	public ResponseBean showAllesbticket() {
		ResponseBean responseBean = new ResponseBean();
		int code=200;
		String msg="请求成功";
		List allesbticket = null;
		try {
			allesbticket = esbticketStationService.showAllesbticket();
		} catch (Exception e) {
			e.printStackTrace();
			code=400;
			msg="请求失败";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(allesbticket);
		return responseBean;
	}
}
