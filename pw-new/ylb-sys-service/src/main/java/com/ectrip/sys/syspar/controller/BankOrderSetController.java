package com.ectrip.sys.syspar.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.employee.controller.GALCompanyInfoController;
import com.ectrip.sys.employee.service.impl.EsfEmployeeTabService;
import com.ectrip.sys.syspar.service.IBankOrderSetService;
import com.ectrip.sys.syspar.service.ISysparService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 银行排序管理
 * @author huangyuqi
 */
@RestController
@RequestMapping("bank")
@Api(tags = "系统模块-银行相关接口")
public class BankOrderSetController {
	private static final Logger LOGGER = LogManager.getLogger(GALCompanyInfoController.class);
	@Autowired
	private IBankOrderSetService bankordersetService;
	private ISysparService sysparService;
	@Autowired
	private EsfEmployeeTabService employeeService;
	

	/**
	 * 获取银行排序列表
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2012-3-28
	 */
	@PostMapping("v1/bankOrderSetList")
	@ApiOperation(value = "获取银行排序列表")
	public ResponseBean BankOrderSetList(@RequestParam int page,@RequestParam int pageSize){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			ps = bankordersetService.queryBankOrderList(page, pageSize, null);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "接口异常";
			LOGGER.info("获取银行排序列表异常", e.getCause());
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}

	/**
	 * 新增银行排序
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2012-3-28
	 *//*
	public String AddBankOrderSet(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		this.strutsAction = ADD;
		bankorder = new Bankorder();
		BankorderId bid  = new BankorderId();
		bid.setRwid(1L);
		bid.setColid(1L);
		bankorder.setId(bid);
		
		List bankList = bankordersetService.queryBankList();
		getRequest().setAttribute("bankList", bankList);
		return SUCCESS;
	}

	*//**
	 * 修改银行排序
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2012-3-28
	 *//*
	public String EditBankOrderSet(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		this.strutsAction = EDIT;
		
		if(bankorder.getBankid()==null || "".equals(bankorder.getBankid())){
			this.addActionError(getText("error.bankorder.bankid.required"));//银行编号 不能为空
			return INPUT;
		}
		
		bankorder = bankordersetService.getBankOrder(bankorder.getBankid());
		return SUCCESS;
	}
	*//**
	 * 删除银行排序
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2012-3-28
	 *//*
	public String DeleteBankOrderSet(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		this.strutsAction = DELETE;
		if(bankorder.getBankid()==null || "".equals(bankorder.getBankid())){
			this.addActionError(getText("error.bankorder.bankid.required"));//银行编号 不能为空
			return INPUT;
		}
		bankorder = bankordersetService.getBankOrder(bankorder.getBankid());
		return SUCCESS;
	}
	*//**
	 * 查看银行排序
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2012-3-28
	 *//*
	public String ViewBankOrderSet(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		this.strutsAction = VIEW;
		if(bankorder.getBankid()==null || "".equals(bankorder.getBankid())){
			this.addActionError(getText("error.bankorder.bankid.required"));//银行编号 不能为空
			return INPUT;
		}
		bankorder = bankordersetService.getBankOrder(bankorder.getBankid());
		return SUCCESS;
	}

	*//**
	 * 保存银行排序
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2012-3-28
	 *//*
	public String SaveBankOrderset(){
		Esfemployeetab esfemployeetab=null;
		if(ActionContext.getContext()
				.getSession().get("employee")==null || "".equals(ActionContext.getContext()
						.getSession().get("employee"))){
			return "login";
		}else{
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
			.getSession().get("employee");
		}
		
		if(ADD==strutsAction || EDIT == strutsAction){
			if(bankorder.getBankid()==null || "".equals(bankorder.getBankid())){
				this.addActionError(getText("error.bankorder.bankid.required"));//银行编号 不能为空
				return INPUT;
			}
			if(bankorder.getId().getRwid()==null || "".equals(bankorder.getId().getRwid())){
				this.addActionError(getText("error.bankorder.id.rwid"));//行 不能为空
				return INPUT;
			}
			if(bankorder.getId().getColid()==null || "".equals(bankorder.getId().getColid())){
				this.addActionError(getText("error.bankorder.id.colid"));//列数 不能为空
				return INPUT;
			}
		}else if(DELETE == strutsAction){
			if(bankorder.getBankid()==null || "".equals(bankorder.getBankid())){
				this.addActionError(getText("error.bankorder.bankid.required"));//银行编号 不能为空
				return INPUT;
			}
		}
		
		Syslog syslog = new Syslog();
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());//后台操作人
		
		if(ADD==strutsAction){
			//判断是否已经存在
			boolean isuse = bankordersetService.queryBankOrderIsUse(bankorder.getId().getRwid(), bankorder.getId().getColid());
			if(isuse){
				this.addActionError(getText("error.bankorder.isuse"));//已经存在了
				return INPUT;
			}else{
				Sysparv5 syspar = new Sysparv5();
				Sysparv5Id sysid = new Sysparv5Id();
				sysid.setPmcd(bankorder.getBankid());
				sysid.setPmky("BANK");
				syspar = (Sysparv5)this.genericService.get(Sysparv5.class, sysid);
				bankorder.setBankname(syspar.getPmva());
				
				bankordersetService.addBankOrder(bankorder, syslog);
				this.addActionMessage(getText("success.bankorder.add")+WebContant.DOMAINAME);
				
			}
		}else if(EDIT == strutsAction){
			Bankorder bank = bankordersetService.getBankOrder(bankorder.getBankid());
			//表示没有改变行数与列数
			if(bank.getId().getRwid().equals(bankorder.getId().getRwid()) && bank.getId().getColid().equals(bankorder.getId().getColid())){
				this.addActionMessage(getText("success.bankorder.edit")+WebContant.DOMAINAME);
			}else{
				//判断是否已经存在
				boolean isuse = bankordersetService.queryBankOrderIsUse(bankorder.getId().getRwid(), bankorder.getId().getColid());
				if(isuse){
					this.addActionError(getText("error.bankorder.isuse"));//已经存在了
					return INPUT;
				}else{
					Sysparv5 syspar = new Sysparv5();
					Sysparv5Id sysid = new Sysparv5Id();
					sysid.setPmcd(bankorder.getBankid());
					sysid.setPmky("BANK");
					syspar = (Sysparv5)this.genericService.get(Sysparv5.class, sysid);
					bankorder.setBankname(syspar.getPmva());
					
					bankordersetService.updateBankOrder(bankorder, syslog);
					this.addActionMessage(getText("success.bankorder.edit")+WebContant.DOMAINAME);
				}
			}
			
		}else if(DELETE == strutsAction){
			bankorder = bankordersetService.getBankOrder(bankorder.getBankid());
			bankordersetService.deleteBankOrder(bankorder, syslog);//删除操作
			this.addActionMessage(getText("success.bankorder.delete")+WebContant.DOMAINAME);
		}
		
		return SUCCESS;
	}

*/}

