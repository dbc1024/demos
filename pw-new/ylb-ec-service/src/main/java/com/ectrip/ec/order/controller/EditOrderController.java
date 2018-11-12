package com.ectrip.ec.order.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.order.service.iservice.IOrderService;
import com.ectrip.shiro.ResponseBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("editOrder")
@Api(tags = "网上订单-修改订单游览日期/密码")
public class EditOrderController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(EditOrderController.class);
	
	@Autowired
	private IOrderService orderService;
	
	
	/**
	 * 
	 * Describe:订单首次游览日期/密码修改订单查询入口
	 * @author:chenxinhao
	 * @return
	 * return:String
	 * Date:2012-9-6
	 */
	@GetMapping("v1/orderInfo/{orid}")
    @ApiOperation(value = "订单首次游览日期/密码修改查询接口")
	public ResponseBean ordersearch(@RequestParam String orid){
		
		try {
			List prdlist = this.orderService.getTOrderList(orid);
			if(prdlist==null||prdlist.size()==0){
				return new ResponseBean(ERROR_CODE_400, "订单号不存在或输入错误!");
			}
			
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", prdlist);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("待修改订单查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "待修改订单查询异常", "异常信息："+ e.getMessage());
		}
	}
}
