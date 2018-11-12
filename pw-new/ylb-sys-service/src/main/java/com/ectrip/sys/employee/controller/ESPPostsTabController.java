package com.ectrip.sys.employee.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.employee.service.IESPPostsTabService;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.service.ISysparService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ��λ����
 * @author lijingrui
 */
@RestController
@Api(tags = "系统模块相关-岗位信息管理相关接口")
@RequestMapping(value = "post")
public class ESPPostsTabController{
	
	private static final Logger LOGGER = LogManager.getLogger(GALCompanyInfoController.class);
	@Autowired
	private IESPPostsTabService esppostService;
	
	@Autowired
	private ISysparService sysparService;   
	
	/**
	 * 
	 * Describe:��ȡ��λ�б�
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-10-13
	 */
	@GetMapping(value = "/v1/showAllListEsppost")
	@ApiOperation(value = "分页查看岗位信息")
	public ResponseBean showAllListEsppost(@RequestParam int pageSize,@RequestParam int page,@RequestParam(required = false) String szpostsname,@RequestParam(required = false) String posttype) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			ps = esppostService.getlistEspposts(szpostsname,posttype, pageSize,
					page, null);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "接口异常";
			LOGGER.info("分页查看岗位信息异常", e.getCause());
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}
	
	/*
	public String findEsppost() {
		url=new StringBuffer();
		url.append(getRequest().getContextPath() + getRequest().getServletPath());
		if (page == null || page.equals("")) {
			page = "1";
		}
		if (esppoststab == null || esppoststab.equals("")) {
			esppoststab = new Esppoststab();
		}else{
			url.append("?esppoststab.szpostsname ="+esppoststab.getSzpostsname());
			url.append("&esppoststab.posttype="+esppoststab.getPosttype());
		}
		//��λ����
		getRequest().setAttribute("gwList", this.sysparService.retrieve("GWTP"));
		
		ps = esppostService.getlistEspposts(esppoststab.getSzpostsname(),esppoststab.getPosttype(), pageSize,
				Integer.parseInt(page), url.toString());
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:��Ӹ�λ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-10-13
	 *//*
	public String preformAddEsppoststab() {
		this.strutsAction = ADD;
		
		//��λ����
		getRequest().setAttribute("gwList", this.sysparService.retrieve("GWTP"));
		
		return SUCCESS;
	}

	*//**
	 * 
	 * Describe:�޸ĸ�λ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-10-13
	 *//*
	public String preformEditEsppoststab() {
		this.strutsAction = EDIT;
		esppoststab = (Esppoststab) this.genericService.get(Esppoststab.class, esppoststab.getIpostsid());
		//��λ����
		getRequest().setAttribute("gwList", this.sysparService.retrieve("GWTP"));
		return SUCCESS;
	}

	*//**
	 * 
	 * Describe:ɾ����λ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-10-13
	 * @throws Exception 
	 *//*
	public String preformDelEsppoststab() throws Exception {
		this.strutsAction = DELETE;
		esppoststab =this.esppostService.findEspposts(esppoststab.getIpostsid());
		return SUCCESS;
	}

	*//**
	 * 
	 * Describe:�鿴��λ
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-10-13
	 * @throws Exception 
	 *//*
	public String preformViewEsppoststab() throws Exception {
		this.strutsAction = VIEW;
		esppoststab = esppostService.findEspposts(esppoststab.getIpostsid());
		return SUCCESS;
	}

	*//**
	 * 
	 * Describe:���� �޸� ɾ�� ����
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-10-13
	 *//*
	public String saveEsppoststab() {
		Syslog syslog = new Syslog();
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		
		
		if (strutsAction == ADD) {
			boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"szpostscode"}, new String[]{esppoststab.getSzpostscode()}, "Esppoststab");
			if(!isuse){
				this.addActionError(getText("esppoststab.szpostscode.ishouvon"));
				return INPUT;
			}
			esppoststab.setSzcreator(esfemployeetab.getEmpid());   //������
			this.esppostService.insertEspposts(esppoststab,syslog);
			this.addActionMessage(getText("success.esppoststab.add")+"����ӭʹ��"+WebContant.DOMAINAME);
		} else if (strutsAction == EDIT) {
			Esppoststab esp=(Esppoststab) this.genericService.get(Esppoststab.class, esppoststab.getIpostsid());
			if(!esppoststab.getSzpostscode().equals(esp.getSzpostscode())){
				boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"szpostscode"}, new String[]{esppoststab.getSzpostscode()}, "Esppoststab");
				if(!isuse){
					this.addActionError(getText("esppoststab.szpostscode.ishouvon"));
					return INPUT;
				}
			}
			this.esppostService.updateEspposts(esppoststab,syslog);
			this.addActionMessage(getText("success.esppoststab.edit")+"����ӭʹ��"+WebContant.DOMAINAME);
		} else if (strutsAction == DELETE) {
			esppoststab=(Esppoststab) this.genericService.get(Esppoststab.class, esppoststab.getIpostsid());
			if(esppostService.getEsfdeptpost(esppoststab.getIpostsid())){
				this.addActionError(getText("esppoststab.esfdeptpoststab"));
				return INPUT;
			}
			esppostService.deleteEspposts(esppoststab,syslog);
			this.addActionMessage(getText("success.esppoststab.del")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		return SUCCESS;
	}

	*//**
	 * 
	 * Describe:��֤
	 * @auth:lijingrui
	 * return:void
	 * Date:2011-10-13
	 *//*
	public void validateSaveEsppoststab() {
		if(strutsAction==ADD||strutsAction==EDIT){
			if (getEsppoststab() == null) {
				this.addActionError("errors.inputError");
			} else {
				if (getEsppoststab().getSzpostscode() == null
						|| getEsppoststab().getSzpostscode().equals("")) {  //��λ����
					this.addActionError(getText("errors.esppoststab.szpostscode.required"));
				}
				if (getEsppoststab().getSzpostsname() == null
						|| getEsppoststab().getSzpostsname().equals("")) {  //��λ����
					this.addActionError(getText("errors.esppoststab.szpostsname.required"));
				}
				if(getEsppoststab().getSzpostscode()!=null&&!getEsppoststab().getSzpostscode().equals("")){
					if(getEsppoststab().getSzpostscode().length()>20){      //�ж�����ĸ�λ���볤��
						this.addActionError(getText("errors.esppoststab.szpostscode.length"));
					}
				}
				if (getEsppoststab().getSzpostsname() != null
						&&!getEsppoststab().getSzpostsname().equals("")) {  //�ж�����ĸ�λ���Ƴ���
					if(getEsppoststab().getSzpostsname().length()>50){
						this.addActionError(getText("errors.esppoststab.szpostsname.length"));
					}
				}
			}
		}
		
	}

	public IESPPostsTabService getEsppostService() {
		return esppostService;
	}

	public void setEsppostService(IESPPostsTabService esppostService) {
		this.esppostService = esppostService;
	}

	public Esppoststab getEsppoststab() {
		return esppoststab;
	}

	public void setEsppoststab(Esppoststab esppoststab) {
		this.esppoststab = esppoststab;
	}

	public PaginationSupport getPs() {
		return ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public ISysparService getSysparService() {
		return sysparService;
	}

	public void setSysparService(ISysparService sysparService) {
		this.sysparService = sysparService;
	}

*/}
