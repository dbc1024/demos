package com.ectrip.ticket.provider.controller;

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
import com.ectrip.ticket.model.provider.Banner;
import com.ectrip.ticket.provider.service.IBannerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("banner")
@Api(tags = "电商管理-App轮播管理相关接口")
public class BannerController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(BannerController.class);
	
	@Autowired
	private IBannerService bannerService;

	/**
     * banner列表查询
     */
    @GetMapping("v1/list")
    @ApiOperation(value = "banner列表查询接口")
    public ResponseBean showBannerList(@RequestParam(required=false) String providerType, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
    	
    	if(null==pageSize) {pageSize = PAGE_SIZE;}
		if(null==page) {page = 1;}
		if(null==providerType) {providerType = "01";}
		
		try {
			PaginationSupport ps = bannerService.queryBannerList(page, pageSize, null, providerType);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("banner列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "banner列表查询异常", "异常信息："+ e.getMessage());
		}
    }
    
    
    /**
     * banner详情查询
     */
    @GetMapping("v1/detail/{id}")
    @ApiOperation(value = "banner详情查询查询接口")
    public ResponseBean showBannerList(@PathVariable Long id){
		
		try {
			Banner banner = bannerService.queryBanner(id);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", banner);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("banner详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "banner详情查询异常", "异常信息："+ e.getMessage());
		}
    }
	
    
    /**
     * banner新增
     */
    @PostMapping("v1/add")
    @ApiOperation(value = "banner新增接口")
    public ResponseBean addBanner(@RequestBody Banner banner){
    	
    	try {
    		// 得到最大主键
    		long maxId = this.bannerService.getMaxPk("id", "Banner");
    		banner.setId(maxId+1);
    		bannerService.insertBanner(banner);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("banner新增异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "banner新增异常", "异常信息："+ e.getMessage());
		}
    	
    	return new ResponseBean(SUCCESS_CODE_200, "操作成功");
    }
    
    
    /**
     * banner修改
     */
    @PostMapping("v1/update")
    @ApiOperation(value = "banner修改接口")
    public ResponseBean updateBanner(@RequestBody Banner banner){
    	
    	try {
    		bannerService.updateBanner(banner);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("banner修改异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "banner修改异常", "异常信息："+ e.getMessage());
		}
    	
    	return new ResponseBean(SUCCESS_CODE_200, "操作成功");
    }
    
    /**
     * banner删除
     */
    @GetMapping("v1/delete/{id}")
    @ApiOperation(value = "banner删除接口")
    public ResponseBean deleteBanner(@PathVariable Long id){
		
		try {
			bannerService.deleteBanner(id);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("banner删除异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "banner删除异常", "异常信息："+ e.getMessage());
		}
		
		return new ResponseBean(SUCCESS_CODE_200, "操作成功");
    }
}
