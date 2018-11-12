package com.ectrip.ticket.salesmanager.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.salesmanager.service.IOpcemployeecardtabService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("employeecard")
@Api(tags = "票务管理-票务检票设置-员工卡证管理相关接口")
public class OpcemployeecardtabController extends BaseController{

	@Autowired
	private SysService sysService;
	
	@Autowired
	private IOpcemployeecardtabService opcemployeecardtabService;
	
	
    /**
     * 员工卡证分页列表查询
     */
	@GetMapping(value = "v1/list")
	@ApiOperation(value = "员工卡证管理分页列表查询")
    public ResponseBean opcemployeecardViewList(@RequestParam(required=false) Integer flag, @RequestParam(required=false) String queryString, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
		if(null==pageSize) {
			pageSize=20;
		}
		if(null==page) {
			page=1;
		}
		
		if(null==flag) {
			flag=0;
		}
		
		Esfemployeetab employee = sysService.currentUser();
        if(queryString!=null&&!queryString.equals("")){
            if(flag==0){
                Pattern p = Pattern.compile("^[0-9]+$");
                boolean b = p.matcher(queryString).matches();
                if(b==false){
                    return new ResponseBean(ERROR_CODE_400, "员工编号只能为整数");
                }
            }
        }
        PaginationSupport ps = opcemployeecardtabService.getOpcemployeecard(flag,queryString,employee.getScenics(),employee.getIcompanyinfoid().toString(),pageSize, page, null);
        return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
    }
}
