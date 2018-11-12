package com.ectrip.ticket.provider.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.ectrip.ticket.model.provider.Jgfz;
import com.ectrip.ticket.provider.service.IJgfzService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@RestController
@RequestMapping(value = "jgfz")
@Api(tags = "票务服务-票务信息设置-服务商价格分组管理")
public class JgfzController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(JgfzController.class);
	@Autowired
	private IJgfzService jgfzService;
	@Autowired
	private SysService sysService;
	/**
	 * 
	 * Describe:显示出所有的服务商价格分组及查询接口
	 * @author:lijingrui
	 * @return
	 * return:String
	 * Date:2014-4-14
	 */
	@ApiOperation(value = "价格分组管理列表接口及查询接口")
	@GetMapping(value = "/v1/showAllJgfz")
	public ResponseBean showAllJgfz(@RequestParam(required = false) Long iscenicid,@RequestParam(required = false) String query, @RequestParam int page,@RequestParam int pageSize,HttpServletRequest request,@RequestParam(required = false) @ApiParam(value = "标识按旅行社或用户名查询") String flag){
		ResponseBean responseBean = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			
			ps = jgfzService.checkUpJgfz(iscenicid, flag, query, pageSize, page, null);
		} catch (Exception e) {
			LOGGER.error("查询服务商分组列表接口异常，具体异常信息为：" + StringUtil.toString_02(e));
			code = 400;
			msg = "接口异常";
		}
		responseBean.setCode(code);
		responseBean.setMsg(msg);
		responseBean.setData(ps);
		return responseBean;
	}
	/**
	 * 
	 * Describe:服务商价格分组的新增、修改、删除操作
	 * @author:lijingrui
	 * @return
	 * return:String
	 * Date:2014-4-14
	 */
	@ApiOperation(value = "价格分组新增、修改、删除操作接口")
	@PostMapping(value = "/v1/saveJgfz")
	public ResponseBean saveJgfz(@RequestParam int strutsAction,@RequestBody Jgfz jgfz){
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			Syslog syslog = new Syslog();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());
			//此逻辑前端判断，后台省去
			/*if(strutsAction == ADD || strutsAction == EDIT){
				if(strutsAction==ADD){
					
					if(jgfz.getIscenicid()==null||jgfz.getIscenicid().equals("")){
						this.addActionError("请选择服务商!");
					}
					if(jgfz.getUsid()==null||jgfz.getUsid().equals("")){
						this.addActionError("请选择旅行社用户!");
					}
				}
				
				if(jgfz.getPmcd()==null||jgfz.getPmcd().equals("")){
					this.addActionError("请选择价格分组!");
				}
				if(jgfz.getByisuse()==null||jgfz.getByisuse().equals("")){
					this.addActionError("请选择使用状态!");
				}
			}
			if(hasActionErrors()){
				return INPUT;
			}*/
			
			if(strutsAction == ADD){
				boolean isuse = this.jgfzService.volidateSole(new String[]{"iscenicid"},new Long[]{jgfz.getIscenicid()},new String[]{"usid"}, new String[]{jgfz.getUsid()}, "Jgfz");
				if(!isuse){
					return new ResponseBean(400, "您选择的用户已存在此价格分组,请重新选择!");
				}
				this.jgfzService.addJgfz(jgfz, syslog);
				
			}else if(strutsAction == EDIT){
				Jgfz fz=(Jgfz)this.jgfzService.get(Jgfz.class, jgfz.getSeq());
				if(fz.getPmcd()!=jgfz.getPmcd()&&!fz.getPmcd().equals(jgfz.getPmcd())){
					boolean isuse = this.jgfzService.volidateSole(new String[]{"iscenicid"},new Long[]{jgfz.getIscenicid()},new String[]{"usid","pmcd"}, new String[]{jgfz.getUsid(),jgfz.getPmcd()}, "Jgfz");
					if(!isuse){
						return new ResponseBean(400, "您选择的用户已存在此价格分组,请重新选择!");
					}
				}
				this.jgfzService.editJgfz(jgfz, syslog);
				
			}else if(strutsAction == DELETE){
				if(jgfz.getSeq() == null){
					
					return new ResponseBean(400, "请选择服务商价格分组!");
				}
				
				this.jgfzService.delJgfz(jgfz.getSeq(), syslog);
			}
		} catch (Exception e) {
			LOGGER.error("服务商分组操作异常，具体异常信息：" + StringUtil.toString_02(e));
			return new ResponseBean(400, "接口异常");
		}
		return new ResponseBean(200, "操作成功");
	}
}
