package com.ectrip.sys.message.controller;

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
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.message.service.iservice.IContMessageService;
import com.ectrip.sys.model.syspar.Contmessage;
import com.ectrip.sys.syspar.service.ISysparService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("contMessage")
@Api(tags = "电商管理-手机短信-短信发送控制相关接口")
public class ContMessageController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(ContMessageController.class);

    @Autowired
    private ISysparService sysparService;
    @Autowired
    private IContMessageService contMessageService;

    private ResponseBean responseBean;
    /**
     * 获取短信发送控制列表
     * @return
     */
    @GetMapping("v1/list")
    @ApiOperation(value = "获取短信发送控制列表")
    public ResponseBean showAllContMessage(@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
    	
    	if(null==pageSize) {pageSize = PAGE_SIZE;}
		if(null==page) {page = 1;}
    	
    	try {
    		PaginationSupport ps = contMessageService.showALLContMessage(page, pageSize, null);
    		
    		return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("获取短信发送控制列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "获取短信发送控制列表查询异常", "异常信息："+ e.getMessage());
		}
		
    }
    
    
    /**
	 * 
	 * 短信发送控制信息明细
	 */
    @GetMapping("v1/detail/{conid}")
    @ApiOperation(value = "短信发送控制信息明细")
	public ResponseBean contMessageDetail(@PathVariable Long conid) {
    	
    	try {
			Contmessage contMessage = contMessageService.viewContMessage(conid);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", contMessage);
		} catch (Exception e) {
			
			e.printStackTrace();
            LOGGER.error("短信发送控制信息明细查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "短信发送控制信息明细查询异常", "异常信息："+ e.getMessage());
		}
	}
    
    

    /**
	 * 
	 * 新增短信发送控制信息
	 */
    @PostMapping("v1/add")
    @ApiOperation(value = "新增短信发送控制信息")
    public ResponseBean addContmessage(@RequestBody Contmessage message) {
    	
    	boolean isuse = contMessageService.volidateSole(new String[]{},new Long[]{},new String[]{"controlpoin"}, new String[]{message.getControlpoin()}, "Contmessage");
		if(!isuse){
			return new ResponseBean(ERROR_CODE_400, "message.controlpoin.ishouvon");
		}
		
		contMessageService.addContMessage(message);
		return new ResponseBean(SUCCESS_CODE_200, "success.message.add"+ WebContant.DOMAINAME);
    }
    
    
    /**
	 * 
	 * 修改短信发送控制信息
	 */
    @PostMapping("v1/update")
    @ApiOperation(value = "修改短信发送控制信息")
    public ResponseBean updateContmessage(@RequestBody Contmessage message) {
    	
    	Contmessage cont = (Contmessage)contMessageService.get(Contmessage.class, message.getConid());
		if(!cont.getControlpoin().equals(message.getControlpoin())){
			boolean isuse = contMessageService.volidateSole(new String[]{},new Long[]{},new String[]{"controlpoin"}, new String[]{message.getControlpoin()}, "Contmessage");
			if(!isuse){
				return new ResponseBean(ERROR_CODE_400, "message.controlpoin.ishouvon");
			}
		}
		
		contMessageService.editContMessage(message);
		return new ResponseBean(SUCCESS_CODE_200, "success.message.edit"+ WebContant.DOMAINAME);
    }
    
    

    /**
   	 * 
   	 * 短信发送控制信息明细
   	 */
   @GetMapping("v1/delete/{conid}")
   @ApiOperation(value = "删除短信发送控制信息")
   public ResponseBean deleteContMessage(@PathVariable Long conid) {

	   contMessageService.delContMessage(conid);
		return new ResponseBean(SUCCESS_CODE_200, "success.message.del"+ WebContant.DOMAINAME);
   }


}
