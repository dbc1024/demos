package com.ectrip.tourcard.controller;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.tourcard.model.CardDetailQuery;
import com.ectrip.tourcard.model.TourCardOrder;
import com.ectrip.tourcard.service.ITourCardOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "tourCardOrder")
@Api(tags = "旅游卡订单相关接口")
public class TourCardOrderController {

	@Autowired
	private ITourCardOrderService tourCardOrderService;
	
	@PostMapping(value = "v1/list")
	@ApiOperation(value = "旅游卡销售订单列表查询")
	public ResponseBean orderList(@RequestBody(required=false) TourCardOrder tourCardOrder, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
		try {
			if (pageSize == null) {
				pageSize = 20;
			}
			if (page == null) {
				page = 1;
			}
			
			String csti = tourCardOrder.getCsti();
			String ceti = tourCardOrder.getCeti();
			String fsti = tourCardOrder.getFsti();
			String feti = tourCardOrder.getFeti();
			
			
			// 校验时间格式
            if (!StringUtils.isEmpty(csti))
            {
                //csti= Tools.getDays();
                if(!validateDate(csti)) {
                	return new ResponseBean(400, "error.rzti.duplicate");//"起始日期输入有错误，请输入yyyy-mm-dd格式的有效日期"
                }
            }
            if (!StringUtils.isEmpty(ceti)){
                //ceti=Tools.getDays();
                if (!validateDate(ceti)) {
                	return new ResponseBean(400, "error.ldti.duplicate");//"截止日期输入有错误，请输入yyyy-mm-dd格式的有效日期"
                }
            }
            // 判断起止日期的大小
            if (!StringUtils.isEmpty(csti) && !StringUtils.isEmpty(ceti) && csti.compareTo(ceti) > 0) {
                return new ResponseBean(400, "error.date.rzti.big.ldti");//起始日期不能大于截止日期
            }

            if (StringUtils.isEmpty(fsti)) {
                fsti=Tools.getDays();
            }
            if (!validateDate(fsti)) {
                return new ResponseBean(400, "error.rzti.duplicate");//"起始日期输入有错误，请输入yyyy-mm-dd格式的有效日期"
            }

            if (StringUtils.isEmpty(feti)) {
                feti=Tools.getDays();
            }
            if (!validateDate(feti)) {
                return new ResponseBean(400, "error.ldti.duplicate");//"截止日期输入有错误，请输入yyyy-mm-dd格式的有效日期"
            }
            // 判断起止日期的大小
            if (fsti.compareTo(feti) > 0) {
                return new ResponseBean(400, "error.date.rzti.big.ldti");//起始日期不能大于截止日期
            }
            //
            PaginationSupport ps = tourCardOrderService.findOrderList(tourCardOrder, pageSize,page, null);
            
            return new ResponseBean(200, "操作成功", ps);
        }catch (Exception e)
        {
            e.printStackTrace();
            return new ResponseBean(400, "出错了,快去联系管理员吧!");
        }
		
	}
	
	
	@GetMapping(value = "v1/get/{orderId}")
	@ApiOperation(value = "根据ID获取旅游卡订单详情")
    public ResponseBean findOrderById(@PathVariable String orderId){
        
		TourCardOrder tourCardOrder = tourCardOrderService.findOrderByID(orderId);
		
		if(tourCardOrder != null){
            tourCardOrder.setMarkedIdentityNum(StringUtil.markIdNumber(DesEncryptUtil.decrypt(tourCardOrder.getIdentityNum())));
        }
		
		return new ResponseBean(200, "操作成功", tourCardOrder);
    }
	
	
	/**
     * 验证日期格式
     * @param date
     * @return
     */
    private boolean validateDate(String date) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
        boolean b = pattern.matcher(date).matches();
        return b;
    }
}
