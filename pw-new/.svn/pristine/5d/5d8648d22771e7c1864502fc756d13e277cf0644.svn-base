package com.ectrip.ec.articlemanager.controller;

import java.util.List;

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
import com.ectrip.ec.articlemanager.service.iservice.IArticleManagerService;
import com.ectrip.ec.articlemanager.service.iservice.IColumnManagerService;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.model.articlemanager.Columnmanagertab;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("columnManager")
@Api(tags = "电商管理-网站信息管理-栏目管理相关接口")
public class ColumnManagerController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(ColumnManagerController.class);
	
	@Autowired
	private IColumnManagerService columnManagerService;
	
	@Autowired
	private IArticleManagerService articleManagerService;
	
	@Autowired
	SysparService sysService;
	
	
	/**
	 * 栏目列表查询
	 */
	@GetMapping("v1/list")
    @ApiOperation(value = "栏目列表查询接口")
	public ResponseBean showAllcolumns(@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
		if(null==pageSize) {pageSize = PAGE_SIZE;}
		if(null==page) {page = 1;}
		
		try {
			PaginationSupport ps = columnManagerService.showlistColumns(pageSize, page, null);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("栏目列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "栏目列表查询异常", "异常信息："+ e.getMessage());
		}
	}

	/**
	 * 栏目详情查询
	 */
	@GetMapping("v1/detail/{cmid}")
    @ApiOperation(value = "栏目详情查询接口")
	public ResponseBean columnDetail(@PathVariable Long cmid) {
		
		try {
			Columnmanagertab column = columnManagerService.viewColumn(cmid);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", column);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("栏目详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "栏目详情查询异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	
	/**
     * 新增栏目
     */
    @PostMapping("v1/add")
    @ApiOperation(value = "新增栏目接口")
    public ResponseBean addColumn(@RequestBody Columnmanagertab column) {
    	Syslog syslog = new Syslog();
    	
    	try {
    		Esfemployeetab esfemployeetab = sysService.currentUser();
    		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
        	columnManagerService.insertColumn(column, syslog);
        	return new ResponseBean(SUCCESS_CODE_200, "栏目添加成功，欢迎使用"+ WebContant.DOMAINAME);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("栏目详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增栏目异常", "异常信息："+ e.getMessage());
		}
    }
    
    
    /**
     * 修改栏目
     */
    @PostMapping("v1/update")
    @ApiOperation(value = "修改栏目接口")
    public ResponseBean updateColumn(@RequestBody Columnmanagertab column) {
    	Syslog syslog = new Syslog();
    	
    	try {
    		Esfemployeetab esfemployeetab = sysService.currentUser();
    		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
    		columnManagerService.updateColumn(column, syslog);
        	return new ResponseBean(SUCCESS_CODE_200, "栏目修改成功，欢迎使用"+ WebContant.DOMAINAME);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("栏目详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "栏目修改异常", "异常信息："+ e.getMessage());
		}
    }
	
    
    
    /**
	 * 删除栏目
	 */
	@GetMapping("v1/delete/{cmid}")
    @ApiOperation(value = "删除栏目接口")
	public ResponseBean deleteColumn(@PathVariable Long cmid) {
		Syslog syslog = new Syslog();
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());
			
			Columnmanagertab column = new Columnmanagertab();
			column.setCmid(cmid);
			columnManagerService.deleteColumn(column, syslog);
			return new ResponseBean(SUCCESS_CODE_200, "删除栏目成功，欢迎使用"+ WebContant.DOMAINAME);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("栏目详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "栏目详情查询异常", "异常信息："+ e.getMessage());
		}
	}
	
	/**
	 * 栏目下拉列表
	 */
	@GetMapping("v1/select")
    @ApiOperation(value = "栏目下拉列表接口")
	public ResponseBean columnSelect() {
		
		try {
			List columns = articleManagerService.showAllColumns();
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", columns);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("栏目下拉列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "栏目下拉列表查询异常", "异常信息："+ e.getMessage());
		}
		
	}
	
}
