package com.ectrip.ticket.afcset.controller;

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
import com.ectrip.shiro.ResponseBean;
import com.ectrip.ticket.afcset.service.IEsbgardengateTicketService;
import com.ectrip.ticket.model.afcset.Esbgardengatetickettab;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 园门同检票
 *
 * @author luoxin
 */
@RestController
@RequestMapping("gardengateTicket")
@Api(tags = "票务管理-票务检票设置-园门管理-园门同检票相关接口")
public class EsbgardengateTicketController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(EsbgardengateTicketController.class);

	@Autowired
	private IEsbgardengateTicketService esbgardengateTicketService;//园门同检票
	
	/**
     * 园门同检票分页列表
     * Describe:
     *
     * Date:2015-8-28
     * @author:luoxin
     */
	@GetMapping(value = "v1/list")
	@ApiOperation(value = "园门同检票分页列表")
    public ResponseBean showGardenGateTicketViewList(@RequestParam Long igardengateid, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
    	
    	if (pageSize == null) {
			pageSize = 20;
		}
		if (page == null) {
			page = 1;
		}
		
    	try {
    		 PaginationSupport ps = this.esbgardengateTicketService.showGardenGateTicketViewList(igardengateid, pageSize, page, null);
    		 return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("获取园门同检票分页列表异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "获取园门同检票分页列表异常", "异常信息："+ e.getMessage());
		}

    }
	
	/**
     * 分页查询获取同检产品
     * Describe:
     *
     * Date:2015-8-28
     * @author:luoxin
     */
	@GetMapping(value = "v1/chooseTicket")
	@ApiOperation(value = "分页查询获取同检产品")
    public ResponseBean chooseTicket(@RequestParam Long iscenicid, @RequestParam(required=false)String sztickettypename, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
    	if (null == pageSize) {
			pageSize = 20;
		}
		if (null == page) {
			page = 1;
		}
       
		try {
			PaginationSupport ps = this.esbgardengateTicketService.queryTicket(iscenicid, null, null, sztickettypename, pageSize, page, null);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("分页查询获取同检产品异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "分页查询获取同检产品异常", "异常信息："+ e.getMessage());
		}
        
    }
	
	/**
     * 新增园门同检票
     * Describe:
     *
     * Date:2015-8-28
     * @author:luoxin
     */
	@PostMapping(value = "v1/add")
	@ApiOperation(value = "新增园门同检票")
    public ResponseBean addGardenGateTicket(@RequestBody Esbgardengatetickettab gardengateticket) {
    	try {
    		esbgardengateTicketService.saveGardenGateTicket(gardengateticket);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("新增园门同检票异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增园门同检票异常", "异常信息："+ e.getMessage());
		}
    	
    	return new ResponseBean(SUCCESS_CODE_200, "操作成功");
    }
	
	/**
     * 修改园门同检票
     * Describe:
     *
     * Date:2015-8-28
     * @author:luoxin
     */
	@PostMapping(value = "v1/update")
	@ApiOperation(value = "修改园门同检票")
    public ResponseBean updateGardenGateTicket(@RequestBody Esbgardengatetickettab gardengateticket) {
    	try {
    		esbgardengateTicketService.updateGardenGateTicket(gardengateticket);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("修改园门同检票异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "修改园门同检票异常", "异常信息："+ e.getMessage());
		}
    	
    	return new ResponseBean(SUCCESS_CODE_200, "操作成功");
    }
	
	/**
     * 删除园门同检票
     * Describe:
     *
     * Date:2015-8-28
     * @author:luoxin
     */
	@GetMapping(value = "v1/delete/{seq}")
	@ApiOperation(value = "删除园门同检票")
    public ResponseBean deleteGardenGateTicket(@PathVariable Long seq) {
    	try {
    		Esbgardengatetickettab gardengateticket = (Esbgardengatetickettab) esbgardengateTicketService.get(Esbgardengatetickettab.class, seq);
    		esbgardengateTicketService.delete(gardengateticket);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("删除园门同检票异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "删除园门同检票异常", "异常信息："+ e.getMessage());
		}
    	
    	return new ResponseBean(SUCCESS_CODE_200, "操作成功");
    }
	
	
	/**
     * 查看园门同检票
     * Describe:
     *
     * Date:2015-8-28
     * @author:luoxin
     */
	@GetMapping(value = "v1/detail/{seq}")
	@ApiOperation(value = "查看园门同检票")
    public ResponseBean viewGardenGateTicket(@PathVariable Long seq){

		try {
			Esbgardengatetickettab gardengateticket = this.esbgardengateTicketService.getGardenGateTicket(seq);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", gardengateticket);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("获取园门同检票详情异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "获取园门同检票详情异常", "异常信息："+ e.getMessage());
		}
    }

}
