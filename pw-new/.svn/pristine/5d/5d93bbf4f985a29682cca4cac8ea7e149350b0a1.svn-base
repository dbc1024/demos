package com.ectrip.tourcard.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.ectrip.tourcard.feign.service.SysService;
import com.ectrip.tourcard.model.CardQuery;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardOrder;
import com.ectrip.tourcard.service.ITourCardOrderService;
import com.ectrip.tourcard.service.ITourCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "tourcard")
@Api(tags = "旅游卡相关接口")
public class TourcardController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(TourcardController.class);
	
	@Autowired
	private ITourCardService tourCardService;
	@Autowired
	private ITourCardOrderService tourCardOrderService;
	@Autowired
	private SysService sysService;

	@PostMapping(value = "v1/list")
	@ApiOperation(value = "旅游卡列表查询")
	public ResponseBean getList(@RequestBody(required=false) CardQuery cardQuery, @RequestParam(required=false) Integer pageSize,@RequestParam(required=false) Integer page){
		
		if (pageSize == null) {
			pageSize = PAGE_SIZE;
		}
		if (page == null) {
			page = 1;
		}
		
		if(cardQuery == null){
			cardQuery = new CardQuery();
			cardQuery.setUsePeriod(-1L);
			cardQuery.setPeriodType(0L);
		}
		
		PaginationSupport ps = null;
		try {
			ps = tourCardService.getTourCardList(cardQuery, page, pageSize, null);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("旅游卡列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "旅游卡列表查询异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(200, "操作成功", ps);
	}
	
	@GetMapping(value = "v1/get/{cardId}")
	@ApiOperation(value = "根据ID获取旅游卡")
	public ResponseBean getTourdCardById(@PathVariable Long cardId){
		
		TourCard tourCard = null;
		try {
			tourCard = tourCardService.getTourCard(cardId);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("根据ID获取旅游卡异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "根据ID获取旅游卡异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(200, "操作成功", tourCard);
	}
	
	@PostMapping(value = "v1/save")
	@ApiOperation(value = "保存旅游卡信息")
	public ResponseBean saveTourCard(@RequestBody TourCard tourCard){
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			Syslog syslog = new Syslog();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());
			
			List<TourCard> cardList = tourCardService.getTourCardByName(tourCard.getName());
			if(cardList != null && cardList.size()!=0){
				return new ResponseBean(ERROR_CODE_400, "旅游卡名称已存在");
			}
			
			List<TourCard> list = tourCardService.getTourCardByProfitNum(tourCard.getProfitNum());
			if(list != null && list.size()!=0){
				return new ResponseBean(ERROR_CODE_400, "分润协议号已被使用");
			}
			
			
			// 得到最大主键
			long maxId = tourCardService.getMaxPk("id", "TourCard");
			
			tourCard.setId(maxId + 1);	//设置ID
			tourCard.setCode(this.createCode(maxId+1));	//生成卡代码
			tourCard.setStatus(0L);	//删除(-1)/下架(0)/上架(1)
			tourCard.setPeriodStartDate("0");
			
			
			tourCardService.insertTourCard(tourCard, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("新增旅游卡异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "新增旅游卡异常", "异常信息："+ e.getMessage());
		}
		
		
		return new ResponseBean(200, "保存成功");
	}
	
	@DeleteMapping(value = "v1/delete/{cardId}")
	@ApiOperation(value = "根据ID删除旅游卡")
	public ResponseBean deleteTourdCard(@PathVariable Long cardId){
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			Syslog syslog = new Syslog();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());
			
			TourCard tourCard = tourCardService.getTourCard(cardId);
			tourCard.setStatus(-1L);//逻辑删除
			
			List<TourCardOrder> orderList = tourCardOrderService.getOrderByCode(tourCard.getCode());
			if(orderList != null && orderList.size()!=0){
				return new ResponseBean(ERROR_CODE_400, "已存在关联的销售订单,不可删除");
			}
			
			syslog.setNote("delete");
			tourCardService.updateTourCard(tourCard, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("删除旅游卡异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "删除旅游卡异常", "异常信息："+ e.getMessage());
		}
		
		
		return new ResponseBean(200, "操作成功");
	}
	
	@PutMapping(value = "v1/update")
	@ApiOperation(value = "修改旅游卡信息")
	public ResponseBean updateTourCard(@RequestBody TourCard tourCard){
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			Syslog syslog = new Syslog();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());
			
			if(tourCard.getStatus()==1){
				return new ResponseBean(ERROR_CODE_400, "此产品已上架,不可修改！");
			}
			
			TourCard oldTourCard = tourCardService.getTourCard(tourCard.getId());
			if("1".equals(oldTourCard.getPeriodStartDate())){
				return new ResponseBean(ERROR_CODE_400, "此产品曾上架,不可修改！");
			}
			
			List<TourCard> cardList = tourCardService.getTourCardByName(tourCard.getName());
			if(cardList != null && cardList.size()==1){
				if(cardList.get(0).getId().intValue()!=tourCard.getId().intValue()) {
					return new ResponseBean(ERROR_CODE_400, "旅游卡名称已存在");
				}
			}
			
			List<TourCard> list = tourCardService.getTourCardByProfitNum(tourCard.getProfitNum());
			if(list != null && list.size()==1){
				if(list.get(0).getId().intValue()!=tourCard.getId().intValue()) {
					return new ResponseBean(ERROR_CODE_400, "分润协议号已被使用");
				}
			}
			
			syslog.setNote("edit");
			tourCardService.updateTourCard(tourCard, syslog);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("修改旅游卡异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "修改旅游卡异常", "异常信息："+ e.getMessage());
		}
		
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功");
	}
	
	@PutMapping(value = "v1/upDown/{cardId}/{operation}")
	@ApiOperation(value = "旅游卡上下架操作")
	public ResponseBean upDown(@PathVariable Long cardId, @ApiParam("上架传up，下架传down") @PathVariable String operation){
		
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			
			Syslog syslog = new Syslog();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());
			
			if("up".equals(operation)) {
				TourCard tourCard = tourCardService.getTourCard(cardId);
				tourCard.setStatus(1L);
				tourCard.setPeriodStartDate("1");//表示进行过上架操作
				
				syslog.setNote("up");
				tourCardService.updateTourCard(tourCard,syslog);
				
				return new ResponseBean(SUCCESS_CODE_200, "操作成功");
			}else if ("down".equals(operation)) {
				TourCard tourCard = tourCardService.getTourCard(cardId);
				tourCard.setStatus(0L);
				
				syslog.setNote("down");
				tourCardService.updateTourCard(tourCard, syslog);
				
				return new ResponseBean(SUCCESS_CODE_200, "操作成功");
			}else {
				return new ResponseBean(ERROR_CODE_400, "请传入正确的参数");
			}
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("修改旅游卡异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "修改旅游卡异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	
	
	public String createCode(Long id){
		
		String[] str = {"0000000","000000","00000","0000","000","00","0",""};
		String code = str[id.toString().length()-1] + id;
		
		return code;
	}
}
