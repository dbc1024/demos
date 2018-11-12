package com.ectrip.ticket.salesmanager.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Ordercs;
import com.ectrip.ticket.provider.service.IBusinessService;
import com.ectrip.ticket.salesmanager.service.IOrdercsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("ordercs")
@Api(tags = "票务管理-票务销售设置-订单信息参数管理")
public class OrdercsController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(OrdercsController.class);
	
	@Autowired
	private IOrdercsService ordercsService;
	
	@Autowired
	private SysService sysService;
	
	@GetMapping(value = "v1/list")
	@ApiOperation(value = "订单信息参数分页列表查询")
	public ResponseBean ordercslist(@RequestParam(required=false)Long ibusinessid, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page) {
		if(null==pageSize) {
			pageSize=20;
		}
		if(null==page) {
			page=1;
		}
		
		try {
			
			PaginationSupport ps = ordercsService.getordercsList(ibusinessid, page, pageSize, null);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("订单信息参数分页列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "订单信息参数分页列表查询异常", "异常信息："+ e.getMessage());
		}
	}
	
	
	@GetMapping(value = "v1/detail")
	@ApiOperation(value = "订单信息参数详情")
	public ResponseBean ordercsview(Long seq) {
		
		try {
			Ordercs ordercs = ordercsService.queryone(seq);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ordercs);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("订单信息参数详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "订单信息参数详情查询异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	@PostMapping(value = "v1/add")
	@ApiOperation(value = "新增订单信息参数")
	public ResponseBean addOrdercs(@RequestBody Ordercs ordercs) {
		
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			Syslog syslog = new Syslog();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());// 后台操作人
			
			if(ordercs.getZcs().trim()==null||ordercs.getZcs().trim().equals("")){
				return new ResponseBean(ERROR_CODE_400, "请填写中文参数");
			}
			if(ordercs.getIsequence()==null||ordercs.getIsequence().equals("")){
				return new ResponseBean(ERROR_CODE_400, "请填写排列顺序");
			}else{
				if(ordercs.getIsequence()<0){
					return new ResponseBean(ERROR_CODE_400, "排列顺序应大于0");
				}
			}
			List list = ordercsService.queryordercs(ordercs.getIbusinessid(),
					ordercs.getEcs());
			if (list != null && list.size() > 0) {
				return new ResponseBean(ERROR_CODE_400, "该对应字段"+ordercs.getEcs()+"在该业务下已使用，不能添加");
			}
//			Long maxseq = this.genericService.getMaxPk("seq", "Ordercs");
//			ordercs.setSeq(maxseq + 1);

			ordercsService.insertordercs(ordercs, syslog);
			
			return new ResponseBean(SUCCESS_CODE_200, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("新增订单信息参数异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增订单信息参数异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	@PostMapping(value = "v1/update")
	@ApiOperation(value = "修改订单信息参数")
	public ResponseBean updateOrdercs(@RequestBody Ordercs ordercs) {
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			Syslog syslog = new Syslog();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());// 后台操作人
			
			if(ordercs.getZcs().trim()==null||ordercs.getZcs().trim().equals("")){
				return new ResponseBean(ERROR_CODE_400, "请填写中文参数");
			}
			if(ordercs.getIsequence()==null||ordercs.getIsequence().equals("")){
				return new ResponseBean(ERROR_CODE_400, "请填写排列顺序");
			}else{
				if(ordercs.getIsequence()<0){
					return new ResponseBean(ERROR_CODE_400, "排列顺序应大于0");
				}
			}
			List list = ordercsService.queryeditordercs(
					ordercs.getIbusinessid(), ordercs.getEcs(),
					ordercs.getSeq());
			if (list != null && list.size() > 0) {
				return new ResponseBean(ERROR_CODE_400, "该对应字段"+ordercs.getEcs()+"在该业务下已使用，不能添加");
			}

			ordercsService.updateordercs(ordercs, syslog);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("修改订单信息参数异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "修改订单信息参数异常", "异常信息："+ e.getMessage());
		}
		
	}

	
	@GetMapping(value = "v1/delete")
	@ApiOperation(value = "删除订单信息参数")
	public ResponseBean deleteOrdercs(Long seq) {
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			Syslog syslog = new Syslog();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());// 后台操作人
			
			Ordercs ordercs =ordercsService.queryone(seq);
			ordercsService.deleteordercs(ordercs, syslog);
			
			return new ResponseBean(SUCCESS_CODE_200, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("删除订单信息参数异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "删除订单信息参数异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	
}
