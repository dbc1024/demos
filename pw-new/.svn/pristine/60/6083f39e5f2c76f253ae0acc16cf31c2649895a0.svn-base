package com.ectrip.ticket.afcset.controller;

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
import com.ectrip.base.enums.SBLX;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.service.IEsbaccessequiptabService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created By Jzhenhua,Time 2011-10-04 10:26<br>
 * 准进设备管理Action
 * 
 * @author lenovo
 */
@RestController
@RequestMapping("accessequip")
@Api(tags = "票务管理-票务检票设置-园门管理-准进设备管理相关接口")
public class EsbaccessequiptabController extends BaseController{

	private static final Logger LOGGER = LogManager.getLogger(EsbaccessequiptabController.class);
	
	@Autowired
	private SysService sysService;
	@Autowired
	private IEsbaccessequiptabService esbaccessequiptabService;
	
	/**
	 * 准进设备分页列表查询
	 */
	@GetMapping(value = "v1/list")
	@ApiOperation(value = "准进设备分页列表查询")
	public ResponseBean accessequipViewList(@RequestParam(required=false) Long iscenicid, @RequestParam(required=false) Long igardengateid, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
		
		if(null==pageSize) {
			pageSize=20;
		}
		if(null==page) {
			page=1;
		}
		
		
		try {
			//获取当前登录用户
	    	Esfemployeetab employee = sysService.currentUser();
			String scenics = "";
			if (employee.getCompanytype().equals("02")) {
				scenics = employee.getScenics();
				if (scenics == null || scenics.equals("")) {
					return new ResponseBean(ERROR_CODE_400, "esfemployeetab.scenics.required");
				}
			}

			PaginationSupport ps = this.esbaccessequiptabService.getAccessequip(iscenicid, igardengateid, scenics, pageSize, page, null);

			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("准进设备分页列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "准进设备分页列表查询异常", "异常信息："+ e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * Describe:服务商的设备类型下拉列表
	 */
	@GetMapping(value = "v1/equiptypeSelect/{iscenicid}")
	@ApiOperation(value = "服务商的设备类型下拉列表查询")
	public ResponseBean getAllEsbequiptype(@PathVariable Long iscenicid) {
		try {
			List list = this.esbaccessequiptabService.getesbequiptypeJSON(iscenicid);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", list);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("服务商的设备类型下拉列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "服务商的设备类型下拉列表查询异常", "异常信息："+ e.getMessage());
		}
	}
	
	/**
	 * 准进设备详情
	 * 
	 * @return
	 */
	@GetMapping(value = "v1/detail/{iaccessequipid}")
	@ApiOperation(value = "准进设备详情查询")
	public ResponseBean accessequipDetail(@PathVariable Long iaccessequipid) {

		try {
			
			Esbaccessequiptab esbaccessequiptab = this.esbaccessequiptabService.getAccessequipById(iaccessequipid);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", esbaccessequiptab);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("准进设备详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "准进设备详情查询异常", "异常信息："+ e.getMessage());
		}
	}
	
	/**
	 * 删除准进设备
	 * 
	 */
	@GetMapping(value = "v1/delete/{iaccessequipid}")
	@ApiOperation(value = "删除准进设备")
	public ResponseBean delAccessequip(@PathVariable Long iaccessequipid) {
		
		try {
			// 获取当前登录用户
			Esfemployeetab employee = sysService.currentUser();

			Syslog syslog = new Syslog();
			syslog.setIemployeeid(employee.getIemployeeid());
			syslog.setSzemployeename(employee.getSzemployeename());
			this.esbaccessequiptabService.delAccessequip(iaccessequipid, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("删除准进设备异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "删除准进设备异常", "异常信息："+ e.getMessage());
		}
		return new ResponseBean(SUCCESS_CODE_200, "操作成功");

	}
	
	
	/**
	 *新增准进设备
	 */
	@PostMapping(value = "v1/add")
	@ApiOperation(value = "新增准进设备")
	public ResponseBean addAccessequip(@RequestBody Esbaccessequiptab esbaccessequiptab) {
		try {
			// 获取当前登录用户
			Esfemployeetab employee = sysService.currentUser();

			Syslog syslog = new Syslog();
			syslog.setIemployeeid(employee.getIemployeeid());
			syslog.setSzemployeename(employee.getSzemployeename());

			// 判断在同一服务商下，售票窗口与准进设备的IP不能重复
			if (esbaccessequiptabService.getGardsxipaddress(esbaccessequiptab.getId().getIscenicid(), esbaccessequiptab.getSzipaddress())) {
				return new ResponseBean(ERROR_CODE_400, "该MAC地址已经在售票窗口或者准进设备中添加了，请重新输入!");
			}
			boolean isuse = esbaccessequiptabService.volidateSole(new String[]{},new Long[]{},new String[]{"szipaddress"}, new String[]{esbaccessequiptab.getSzipaddress()}, "Esbaccessequiptab");
			if(!isuse){
				return new ResponseBean(ERROR_CODE_400, "该MAC地址已经在售票窗口或者准进设备中添加了，请重新输入!");
			}
			// 设置编号
			esbaccessequiptab.getId().setIaccessequipid(this.esbaccessequiptabService.getMaxId());

			// 持久操作
			this.esbaccessequiptabService.addAccessequip(esbaccessequiptab, syslog);

		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("新增准进设备异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增准进设备异常", "异常信息："+ e.getMessage());
		}
		return new ResponseBean(SUCCESS_CODE_200, "操作成功");
	}
	
	/**
	 * 修改准进设备
	 */
	@PostMapping(value = "v1/update")
	@ApiOperation(value = "新增准进设备")
	public ResponseBean updateAccessequip(@RequestBody Esbaccessequiptab esbaccessequiptab) {
		try {
			// 获取当前登录用户
			Esfemployeetab employee = sysService.currentUser();

			Syslog syslog = new Syslog();
			syslog.setIemployeeid(employee.getIemployeeid());
			syslog.setSzemployeename(employee.getSzemployeename());

			boolean isuse = this.esbaccessequiptabService.checkipAddress(esbaccessequiptab);
			if(!isuse){
				return new ResponseBean(ERROR_CODE_400, "该MAC地址已经在售票窗口或者准进设备中添加了，请重新输入!");
			}
			Esbaccessequiptab bs = this.esbaccessequiptabService.getviewquiptab(esbaccessequiptab.getId().getIaccessequipid(), esbaccessequiptab.getId().getIscenicid(), esbaccessequiptab.getId().getIgardengateid());
		
			if (!bs.getSzipaddress().equals(esbaccessequiptab.getSzipaddress())) {
				if (esbaccessequiptabService.getGardsxipaddress(esbaccessequiptab.getId().getIscenicid(), esbaccessequiptab.getSzipaddress())) {
					return new ResponseBean(ERROR_CODE_400, "该MAC地址已经在售票窗口或者准进设备中添加了，请重新输入!");
				}
			}
		
			// 持久
			this.esbaccessequiptabService.updateAccessequip(esbaccessequiptab,syslog);

		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("新增准进设备异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增准进设备异常", "异常信息："+ e.getMessage());
		}
		return new ResponseBean(SUCCESS_CODE_200, "操作成功");
	}
	
	
	/**
	 * 
	 * 设备状态信息列表查询
	 */
	@GetMapping("v1/statusList")
    @ApiOperation(value = "设备状态信息列表查询")
	public ResponseBean showListstatusaccess(@RequestParam(required=false) Long iscenicid, @RequestParam(required=false) String result, 
			@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){

		if(null==pageSize) {pageSize = PAGE_SIZE;}
		if(null==page) {page = 1;}
		
		if(result ==null || result.equals("")){
			result="01";
		}
		
		try {
			// 获取当前登录用户
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			
			String scenics = "";
			if (esfemployeetab.getCompanytype().equals("02")) {
				scenics = esfemployeetab.getScenics();
				if(scenics==null||scenics.equals("")){
					return new ResponseBean(ERROR_CODE_400, "esfemployeetab.scenics.required");
				}
			}
			
			// 获取当前登录用户所管理的服务商
			List providers = this.esbaccessequiptabService.findListesbticket(scenics);
//			getRequest().setAttribute("prdlist", providers);
			
//			List lxlist = sysService.retrieve("SBLX");
//			getRequest().setAttribute("lxlist", this.sysparService.retrieve("SBLX"));
			
			if(null != iscenicid){
				scenics = iscenicid.toString();
			}else{
				if(providers!=null&&providers.size()>0){
					scenics = ((Esbscenicareatab)providers.get(0)).getIscenicid().toString();
				}
			}
			
			PaginationSupport ps = esbaccessequiptabService.searchListAccessstatus(scenics, result, pageSize, page, null);
			
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("设备状态信息列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "设备状态信息列表查询异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	
	/**
	 * 设备类型下拉列表
	 */
	@GetMapping("v1/selectOfSBLX")
    @ApiOperation(value = "设备类型下拉列表接口")
	public ResponseBean selectOfSBLX() {
		
		try {
			List lxlist = sysService.retrieve("SBLX");
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", lxlist);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("设备类型下拉列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "设备类型下拉列表查询异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	
	
}
