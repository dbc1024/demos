package com.ectrip.ec.order.controller;

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
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.cyt.CYTPojo;
import com.ectrip.ec.model.cyt.CytConsumeOption;
import com.ectrip.ec.order.service.CytConsumeManagerService;
import com.ectrip.ec.order.service.iservice.ICytConsumeManagerService;
import com.ectrip.shiro.ResponseBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("cytConsumeManager")
@Api(tags = "电商管理-监管督查-消费通知查询制相关接口")
public class CytConsumeManagerController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(CytConsumeManagerController.class);

    @Autowired
    private ICytConsumeManagerService cytConsumeManagerService;

    /**
     * 消费通知列表查询
     */
    @PostMapping("v1/list")
    @ApiOperation(value = "消费通知列表查询")
    public ResponseBean queryList(@RequestBody(required=false) CytConsumeOption option,@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
    	if(null==pageSize) {pageSize = PAGE_SIZE;}
		if(null==page) {page = 1;}
		
		try {
			if(option == null){
	            option = new CytConsumeOption();
	            option.setRzti(Tools.getDays());
	            option.setLdti(Tools.getDays());
	        }
	        PaginationSupport ps = cytConsumeManagerService.queryCytPojos(option,pageSize,page,null);
	        return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("消费通知列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "消费通知列表查询异常", "异常信息："+ e.getMessage());
		}
        
    }
    
    
    /**
     * 消费通知详情
     */
    @GetMapping("v1/detail/{orid}")
    @ApiOperation(value = "消费通知详情查询")
    public ResponseBean getCytpojo(@PathVariable String orid){
    	
    	try {
			CYTPojo cytPojo = (CYTPojo) cytConsumeManagerService.get(CYTPojo.class, orid);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", cytPojo);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("消费通知详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "消费通知详情查询异常", "异常信息："+ e.getMessage());
		}
       
    }

    /**
     * 修改消费通知
     */
    @PostMapping("v1/update")
    @ApiOperation(value = "修改消费通知")
    public ResponseBean saveCytpojo(@RequestBody CYTPojo cytPojo){
    	try {
    		cytConsumeManagerService.updateCytpojo(cytPojo);
            return new ResponseBean(SUCCESS_CODE_200, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("修改消费通知异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "修改消费通知异常", "异常信息："+ e.getMessage());
		}
        
    }

}
