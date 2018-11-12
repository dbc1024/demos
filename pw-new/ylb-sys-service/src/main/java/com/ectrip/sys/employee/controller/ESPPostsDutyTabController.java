package com.ectrip.sys.employee.controller;

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
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.employee.service.IESPPostsDutyTabService;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.employee.Esppoststab;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 岗位职责管理
 * @author lijingrui
 */
@RestController
@Api(tags = "系统服务模块-岗位职责管理相关接口")
@RequestMapping(value = "postsDuty")
public class ESPPostsDutyTabController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(ESPPostsDutyTabController.class);
	@Autowired
	private IESPPostsDutyTabService espdutypostService;
	
	/**
	* @Title: viewListpost_duty  
	* @Description: TODO 查询岗位职责信息 需要调整  
	* @param @param ipostsid
	* @param @return    参数  
	* @return ResponseBean    返回类型  
	* @throws
	 */
	@GetMapping(value = "/v1/listEspduty")
	@ApiOperation(value = "岗位 职责列表显示")
	public ResponseBean viewListpost_duty(@RequestParam("ipostsid")Long ipostsid) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			// 查询岗位信息
			Esppoststab esppoststab = (Esppoststab) this.espdutypostService.get(Esppoststab.class, ipostsid);
			// 根据岗位类别查询职责列表
			List<Espdutytab> dutylist = this.espdutypostService.getAllpost(esppoststab.getPosttype());

			// 查询某个岗位的职责列表
			List<?> list = this.espdutypostService.reviterId(esppoststab.getIpostsid());
			//显示与岗位 关联的职责信息
			List lst = this.espdutypostService.revisterRegid(esppoststab.getIpostsid());
			
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
			LOGGER.info("查询部门列表信息异常:"+StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(null);
		return result;
	}
	
	/**
	 * 描述：获取后台按钮权限（只针对旅游卡）还需调整
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/v1/viewListBtnPostDuty")
	@ApiOperation(value = "岗位 按钮职责列表显示")
	public ResponseBean viewListBtnPostDuty(Long ipostsid){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			//获取当前用户的岗位（角色）
			Esppoststab esppoststab = (Esppoststab) this.espdutypostService.get(Esppoststab.class,ipostsid);
			//获取旅游卡按钮的所有权限
			List<Espdutytab> dutylist = this.espdutypostService.getTourcardBtnDuty();
			//获取当前用户拥有的旅游卡按钮权限
			List<Espdutytab> list = this.espdutypostService.reviterTourcardId(esppoststab.getIpostsid());
			
			List lst=this.espdutypostService.revisterTourcardRegid(esppoststab.getIpostsid());
		} catch (RuntimeException e) {
			code = 400;
			msg = "接口异常";
			LOGGER.info("查询部门列表信息异常:"+StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(null);
		return result;
	}
	
	/*public String esppostdutySave(){
		Syslog syslog = new Syslog();
		
		
		this.espdutypostService.updates(esppoststab, postduty, idutyids,syslog);
		this.addActionMessage(getText("success.esppoststabpostduty.add")+"����ӭʹ��"+WebContant.DOMAINAME);
		return SUCCESS;
	}*/
}
