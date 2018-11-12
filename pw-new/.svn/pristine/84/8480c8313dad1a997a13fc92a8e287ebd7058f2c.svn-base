package com.ectrip.ticket.permitenter.controller;

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

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.WebContant;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;
import com.ectrip.ticket.permitenter.service.iservice.IOpwwicketSetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 检票设置管理
 * @author lijingrui
 */
@RestController
@RequestMapping("opwwicketSet")
@Api(tags = "票务管理-票务检票设置-检票设置管理相关接口")
public class OpwwicketSetController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(OpwwicketSetController.class);

	@Autowired
	private SysService sysService;
	@Autowired
	private IOpwwicketSetService opwwicketService;
	
	/**
    * 检票设置信息分页列表查询
    */
	@GetMapping(value = "v1/list")
	@ApiOperation(value = "检票设置信息分页列表查询")
   public ResponseBean showAllOpwwicket(@RequestParam(required=false) Long iscenicid, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
		if(null==pageSize) {
			pageSize=20;
		}
		if(null==page) {
			page=1;
		}
	   
	   try {
		   	Esfemployeetab esfemployeetab = sysService.currentUser();
			String scenics = "";
			if (esfemployeetab.getCompanytype().equals("02")) {
				scenics = esfemployeetab.getScenics();
				if(scenics==null||scenics.equals("")){
					return new ResponseBean(ERROR_CODE_400, "esfemployeetab.scenics.required");
				}
			}
		
		    if(iscenicid != null){
			   scenics = iscenicid.toString();
		    }
		
		    PaginationSupport ps = this.opwwicketService.getlistwicketset(scenics,pageSize, page, null);
		    
		    return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		    
		} catch (Exception e) {
			
			e.printStackTrace();
            LOGGER.error("检票设置信息分页列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "检票设置信息分页列表查询异常", "异常信息："+ e.getMessage());
		}
		
   }
	
    /**
    *
    * 检票设置信息预新增接口
    */
	@GetMapping(value = "v1/preAdd")
	@ApiOperation(value = "检票设置信息预新增接口")
   public ResponseBean preAddOpwwicket(){
		
		Map<String, Object> data = new HashMap<>();
      
		try {
			//消费方式
	       List xfList = sysService.retrieve("XFSH");
	       data.put("xfList", xfList);
	       //检票控制方式
	       List jpList = sysService.retrieve("JPSH");
	       data.put("jpList", jpList);
	       //检票通行方式
	       List kpList = sysService.retrieve("KPSH");
	       data.put("kpList", kpList);
	       //身份识别类型
	       List sfList = sysService.retrieve("SFWP");
	       data.put("sfList", sfList);
	       //票号处理
	       List phList = sysService.retrieve("PHSP");
	       data.put("phList", phList);
	       //检票前置条件
	       List jpqzList = sysService.retrieve("JPQZ");
	       data.put("jpqzList", jpqzList);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("检票设置信息预新增接口异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "检票设置信息预新增接口异常", "异常信息："+ e.getMessage());
		}

       return new ResponseBean(SUCCESS_CODE_200, "操作成功", data);
   }
   
    /**
    *
    * 检票设置详情
    */
	@GetMapping(value = "v1/detail/{iwicketsetid}")
	@ApiOperation(value = "检票设置详情查询")
   public ResponseBean opwwicketDetail(@PathVariable Long iwicketsetid){
		
		try {
			Opwwicketsettab wicketset = opwwicketService.getviewOpwicketID(iwicketsetid);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", wicketset);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("检票设置详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "检票设置详情查询异常", "异常信息："+ e.getMessage());
		}
		
   }
	
	/**
     * 根据票类型的服务商查出该服务商的所有园门
     */
	@GetMapping(value = "v1/gateSelect/{itickettypeid}")
	@ApiOperation(value = "园门下拉列表")
    public ResponseBean showGardenGateList(@PathVariable Long itickettypeid){
    	try {
    		List list = opwwicketService.getGardengate(itickettypeid);
            return new ResponseBean(SUCCESS_CODE_200, "操作成功", list);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("园门下拉列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "园门下拉列表查询异常", "异常信息："+ e.getMessage());
		}
        
    }
	
	
	/**
    *
    * 保存 检票设置信息
    */
	@PostMapping(value = "v1/add")
	@ApiOperation(value = "新增检票设置信息")
   public ResponseBean saveOpwwicket(@RequestBody Opwwicketsettab wicketset){
       
		try {
			Syslog syslog = new Syslog();
	       Esfemployeetab esfemployeetab = sysService.currentUser();
	       syslog.setIemployeeid(esfemployeetab.getIemployeeid());
	       
	       if(wicketset.getIssettimeticket()==0){
	       	wicketset.setIstimeticket(0L);
	       }
	       
	       //wicketset.setMsingleconsume(Double.parseDouble(msingleconsume));
	       //根据园门id、产品id、子产品id判断存在
	       if(opwwicketService.showgradeTicket(wicketset.getIgardengateid(), wicketset.getItickettypeid(), wicketset.getIzticktypeid())){
	           return new ResponseBean(ERROR_CODE_400, "wicketset.igardengateid.itickettypeid.izticktypeid.required");
	       }
	       if (wicketset.getIgardengateid()==null||wicketset.getIgardengateid().equals("")||wicketset.getIgardengateid().equals("-1")) {
	           return new ResponseBean(ERROR_CODE_400, "wicketset.igardengateid.is.null");
	       }
	       if (wicketset.getItickettypeid()==null||wicketset.getItickettypeid().equals("")||wicketset.getItickettypeid().equals("-1")) {
	           return new ResponseBean(ERROR_CODE_400, "wicketset.itickettypeid.is.null");
	       }
	       if (wicketset.getIzticktypeid()==null||wicketset.getIzticktypeid().equals("")||wicketset.getIzticktypeid().equals("-1")) {
	           return new ResponseBean(ERROR_CODE_400, "wicketset.izticket.is.null");
	       }
	       
	       opwwicketService.insertwicketset(wicketset, syslog);
	       
	       return new ResponseBean(SUCCESS_CODE_200, "success.wicketset.add"+ "，欢迎使用"+WebContant.DOMAINAME);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("新增检票设置信息异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增检票设置信息异常", "异常信息："+ e.getMessage());
		}
		
   }
   
	
	
	/**
    *
    * 修改检票设置信息
    */
	@PostMapping(value = "v1/update")
	@ApiOperation(value = "修改检票设置信息")
   public ResponseBean updateOpwwicket(@RequestBody Opwwicketsettab wicketset){
		try {
		   Syslog syslog = new Syslog();
	       Esfemployeetab esfemployeetab = sysService.currentUser();
	       syslog.setIemployeeid(esfemployeetab.getIemployeeid());
	       
	       if(wicketset.getIssettimeticket()==0){
	       	wicketset.setIstimeticket(0L);
	       }
	       
	       
//		       wicketset.setMsingleconsume(Double.parseDouble(msingleconsume));
	       this.opwwicketService.updatewicketset(wicketset, syslog);
	       return new ResponseBean(SUCCESS_CODE_200, "success.wicketset.edit"+ "，欢迎使用"+WebContant.DOMAINAME);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("修改检票设置信息异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "修改检票设置信息异常", "异常信息："+ e.getMessage());
		}
       
       
	}
	
	
	/**
    *
    * 删除检票设置信息
    */
	@GetMapping(value = "v1/delete/{iwicketsetid}")
	@ApiOperation(value = "删除检票设置信息")
   public ResponseBean deleteOpwwicket(@PathVariable Long iwicketsetid){
		try {
		   Syslog syslog = new Syslog();
	       Esfemployeetab esfemployeetab = sysService.currentUser();
	       syslog.setIemployeeid(esfemployeetab.getIemployeeid());
	       
	       Opwwicketsettab wicketset = new Opwwicketsettab();
	       wicketset.setIwicketsetid(iwicketsetid);
	       this.opwwicketService.deletewicketset(wicketset, syslog);
	       return new ResponseBean(SUCCESS_CODE_200, "success.wicketset.del"+ "，欢迎使用"+WebContant.DOMAINAME);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("删除检票设置信息异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "删除检票设置信息异常", "异常信息："+ e.getMessage());
		}
       
       
	}
}
