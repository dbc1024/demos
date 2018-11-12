package com.ectrip.ticket.provider.controller;

import java.security.Principal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.provider.service.ITicketXgzService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 退票规则管理
 * @author jy
 *
 */
@Controller
@Api(tags = "票务服务-票务信息设置-退票规则信息管理")
@RequestMapping(value = "ticketXgz")
public class TicketXgzController extends BaseController{
	@Autowired
	private ITicketXgzService ticketxgzService;
	@Autowired
	private SysService sysService;
	private Logger LOGGER = LogManager.getLogger(this.getClass());
	/**
	 * 查询景区退票规则信息
	 * @param scenics 景区Id
	 * @param pageSize 页面数量
	 * @param page 当前页
	 * @return
	 */
	@GetMapping(value = "list")
	@ApiOperation(value = "获取退票规则信息列表")
	public ResponseBean listTicketXgzInfo(Principal user,@RequestParam(required = false) String scenics,@RequestParam int pageSize,@RequestParam int page) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			Esfemployeetab esfemployeetab = sysService.currentUser();
			LOGGER.info("开始获取退票规则信息列表");
			//景区企业员工
			if (esfemployeetab.getCompanytype().equals("02")) {
				scenics = esfemployeetab.getScenics();
				if (scenics == null || scenics.equals("")) {
					code = 400;
					msg = "该景区员工，没有属于的企业";
				}else {
					ps = ticketxgzService.showAllticketxgz(scenics, pageSize, page, null);
				}
			}else {
				//平台企业员工
				ps = ticketxgzService.showAllticketxgz(scenics, pageSize, page, null);
				
			}
			LOGGER.info("结束获取退票规则信息列表");
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "接口异常";
			LOGGER.error("获取退票规则信息列表,具体异常："+StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}
	/**
	 * 
	 * Describe:保存，修改 ，刪除
	 * 
	 * @auth:lijingrui
	 * @return return:String Date:Nov 18, 2011
	 */
	@GetMapping(value = "act")
	@ApiOperation(value = "退票规则信息 新增 修改 刪除")
	public ResponseBean saveTicketxgz(@ApiParam("flag 1：新增 2：修改 3：刪除") @RequestParam int flag,Principal user,@RequestBody Ticketxgz ticketgz) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			Syslog syslog = new Syslog();
			Esfemployeetab esfemployeetab = sysService.currentUser();
			syslog.setIemployeeid(esfemployeetab.getIemployeeid());
			if (flag == ADD) {
				boolean isuse = this.ticketxgzService.volidateSole(
						new String[] { "iscenicid", "itickettypeid", "xyjs4" },
						new Long[] { ticketgz.getIscenicid(), ticketgz.getItickettypeid(),
								ticketgz.getXyjs4() }, new String[] {}, new String[] {}, "Ticketxgz");
				if (!isuse) {
					code = 400;
					msg = "退票规则已存在";
				}else {
					this.ticketxgzService.insertticketxgz(ticketgz, syslog);
				}
			}
			if (flag == EDIT) {
				Ticketxgz xgz = (Ticketxgz) this.ticketxgzService
						.get(Ticketxgz.class, ticketgz.getGzid());
				if (!xgz.getIscenicid().equals(ticketgz.getIscenicid())
						|| !xgz.getItickettypeid().equals(ticketgz.getItickettypeid())
						|| !xgz.getXyjs4().equals(ticketgz.getXyjs4())) {
					boolean isuse = this.ticketxgzService.volidateSole(
							new String[] { "iscenicid", "itickettypeid", "xyjs4" },
							new Long[] { ticketgz.getIscenicid(), ticketgz.getItickettypeid(),
									ticketgz.getXyjs4() }, new String[] {}, new String[] {},
							"Ticketxgz");
					if (!isuse) {
						code = 400;
						msg = "退票规则已存在";
					}else {
						this.ticketxgzService.updateticketxgz(ticketgz, ticketgz.getChangebackrate(), syslog);
					}
				}
			}
			if (flag == DELETE) {
				this.ticketxgzService.deleteticketxgz(ticketgz, syslog);
			}
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
			LOGGER.error("操作退票信息异常,具体异常："+StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
